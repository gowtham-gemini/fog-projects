package com.assistanz.fogpanel

import grails.transaction.Transactional

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2AsyncClient;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AvailabilityZone;
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.BundleTask;
import com.amazonaws.services.ec2.model.CreateImageRequest;
import com.amazonaws.services.ec2.model.CreateImageResult;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteKeyPairRequest;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DeleteSnapshotRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.DeregisterImageRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.ec2.model.DescribeBundleTasksResult;
import com.amazonaws.services.ec2.model.DescribeImageAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeImageAttributeResult;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeReservedInstancesOfferingsResult;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.ImageAttributeName;
import com.amazonaws.services.ec2.model.ImportKeyPairRequest;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.ImportKeyPairResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Region as AwsRegion;
import com.amazonaws.services.ec2.model.KeyPair;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.ReservedInstancesOffering;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.VolumeType as AwsVolumeType;
import com.amazonaws.AmazonClientException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Transactional
class VirtualMachineService {

    def springSecurityService
    
    AwsAuthService awsAuthService;
    
    def getVMList(currentRegionId) {
        try {
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();            
            def user = springSecurityService.currentUser;                        
            def currentRegion = Region.findById(currentRegionId)
            if(currentRegion != null){
                String endPoint = "https://" + currentRegion.getEndPoint();                 
                ec2.setEndpoint(endPoint);
                DescribeInstancesResult describeInstanceResult = ec2.describeInstances();
                for(Reservation reservation : describeInstanceResult.getReservations()) {
                    for(Instance instance :reservation.getInstances()) {
                        def virtualMachine = VirtualMachine.findByReferenceId(instance.getInstanceId())
                        if(virtualMachine) {
                            virtualMachine.state = instance.getState().getName()
                            virtualMachine.publicDnsName = instance.getPublicDnsName()
                            virtualMachine.publicIpAddress = instance.getPublicIpAddress()
                            virtualMachine.save(flush:true)
                            
                            def volumes = Volume.findByVirtualMachine(virtualMachine); 
                            if(instance.getBlockDeviceMappings() != null && volumes == null) {
                                def blockDeviceMappingResult = instance.getBlockDeviceMappings();
                                for(def bdm : blockDeviceMappingResult) {   
                                    
                                    def volumesRequest = new DescribeVolumesRequest();
                                    volumesRequest.withVolumeIds(bdm.getEbs().getVolumeId());
                                    def volumesResult = ec2.describeVolumes(volumesRequest);
                                    
                                    def awsVolumes = volumesResult.getVolumes();                                            
                                    for(def awsVolume : awsVolumes) {
                                        def volume = new Volume();
                                        volume.referenceId = bdm.getEbs().getVolumeId() 
                                        volume.name = "root"
                                        volume.snapshotId = awsVolume.getSnapshotId()
                                        volume.size = awsVolume.getSize()
                                        volume.status = awsVolume.getState()
                                        volume.isDeleteOnTermination = bdm.getEbs().getDeleteOnTermination()
                                        volume.iops = awsVolume.getIops()
                                        volume.encrypted = awsVolume.getEncrypted()
                                        volume.region = currentRegion
                                        volume.zone = Zone.findByName(awsVolume.getAvailabilityZone());
                                        volume.createdAt = new Date();
                                        
                                        //TODO:
                                        def volumeTypeId;
                                        if(awsVolume.getVolumeType().equals("gp2")) {
                                          volumeTypeId = 1;
                                        } else if (awsVolume.getVolumeType().equals("io1")) {
                                          volumeTypeId = 2;
                                        } else {
                                          volumeTypeId = 3;
                                        }
                                        volume.volumeType = VolumeType.findById(volumeTypeId)  
                                        volume.virtualMachine = virtualMachine
                                        volume.account = user.account
                                        volume.user = user

                                        volume.save(flush: true)
                                        println("Inside block mapping saved succesfully --------------------");
                                    }                                                                        
                                }
                            }                            
                        } 
    //                        else if(checkState == null && instance.getState().getName() != "terminated") {
    //                            
    //                            def regionObj = Region.findByName(awsRegion.getRegionName())
    //                            def virtualMachine = new VirtualMachine()
    //                            def image = Images.findByReferenceId(instance.getImageId())
    //                            Console.print("image" + image)
    //                            def flavor = Flavors.findByName(instance.getInstanceType())
    //                            def subnet = Subnet.findByReferenceId(instance.getSubnetId())
    //                            def sshKey = SSHKeys.findByName(instance.getKeyName())
    //                            def vpc = Vpc.findByReferenceId(instance.getVpcId())
    //                            virtualMachine.name = "test"
    //                            virtualMachine.referenceId = instance.getInstanceId()
    //                            virtualMachine.architecture = instance.getArchitecture()
    //                            virtualMachine.hypervisor = instance.getHypervisor()
    //                            virtualMachine.rootDeviceType = instance.getRootDeviceType()
    //                            virtualMachine.rootDeviceName = instance.getRootDeviceName()
    //                            virtualMachine.privateDnsName = instance.getPublicDnsName()
    //                            virtualMachine.publicDnsName = instance.getPublicDnsName()
    //                            virtualMachine.privateIpAddress = instance.getPrivateIpAddress()
    //                            virtualMachine.publicIpAddress = instance.getPublicIpAddress()
    //                            virtualMachine.kernelId = instance.getKernelId()
    //                            virtualMachine.ramdiskId = instance.getRamdiskId()
    //                            virtualMachine.ebsOptimized = instance.getEbsOptimized()
    //                            virtualMachine.monitoringEnabled = instance.getMonitoring()
    //                            virtualMachine.launchTime = new java.sql.Timestamp(instance.getLaunchTime().getTime());
    //                            virtualMachine.region = regionObj
    //                            virtualMachine.image = image
    //                            virtualMachine.flavor = flavor
    //                            virtualMachine.state = instance.getState().getName()
    //                            virtualMachine.account = user.account
    //                            virtualMachine.user = user            
    //                            virtualMachine.vpc = vpc
    //                            virtualMachine.subnet = subnet
    //                            
    //                            if(sshKey) {
    //                                virtualMachine.sshkeys = sshKey
    //                            } else {
    //                                def sshKeys = new SSHKeys();
    //                                DescribeKeyPairsResult keyPairs = ec2.describeKeyPairs();
    //                                for(def key : keyPairs.getKeyPairs()) {
    //                                    sshKeys.name = key.getKeyName()
    //                                    sshKeys.fingerprint = key.getKeyFingerprint()
    //                                    System.out.println("getKeyFingerprint"   + key.getKeyFingerprint());
    //                                    System.out.println( "getKeyName"              +   key.getKeyName());
    //                                }
    //                                
    //                                sshKeys.isDefault = false;
    //                                sshKeys.publicKey = "public key";
    //                                sshKeys.privateKey = "private Key";
    //                                sshKeys.account = user.account;
    //                                sshKeys.region = regionObj;
    //                                sshKeys.user = user;
    //                                sshKeys.createdAt = new Date();
    //                                sshKeys.save(flush:true);
    //                                virtualMachine.sshkeys = sshKeys
    //                            }
    //                            
    //                            
    //                            DescribeSecurityGroupsResult  describeSecurityGroupsResult = ec2.describeSecurityGroups();
    //                            for(def awsSecurityGroup : describeSecurityGroupsResult.getSecurityGroups()){
    //                                def securityGroup = SecurityGroup.findByReferenceId(awsSecurityGroup.getGroupId())
    //                                System.out.println("securityGroup  : " + awsSecurityGroup.getGroupId());
    //                                virtualMachine.securityGroup = securityGroup 
    //                            }
    //                            Console.print("finished")
    //                            virtualMachine.save(flush: true)                        
    //                            if (!virtualMachine.save()) {
    //                                virtualMachine.errors.allErrors.each { println it }
    //                            }  
    //                        }
                    }
                } 
            }     
            
            ArrayList list = new ArrayList()
            if(currentRegion != null) {
                def virtualMachines = VirtualMachine.findAllByRegionAndDeleted(currentRegion, false)
                for(def virtualMachine : virtualMachines) {
                    HashMap vmDetails = new HashMap()
                    vmDetails.put("id",virtualMachine.id)
                    vmDetails.put("name",virtualMachine.name)
                    vmDetails.put("referenceId",virtualMachine.referenceId)
                    vmDetails.put("architecture",virtualMachine.architecture)
                    vmDetails.put("hypervisor",virtualMachine.hypervisor)
                    vmDetails.put("rootDeviceType",virtualMachine.rootDeviceType)
                    vmDetails.put("rootDeviceName",virtualMachine.rootDeviceName)
                    vmDetails.put("privateDnsName",virtualMachine.privateDnsName)
                    vmDetails.put("publicDnsName",virtualMachine.publicDnsName)
                    vmDetails.put("privateIpAddress",virtualMachine.privateIpAddress)
                    vmDetails.put("publicIpAddress",virtualMachine.publicIpAddress)
                    vmDetails.put("kernelId",virtualMachine.kernelId)
                    vmDetails.put("ramdiskId",virtualMachine.ramdiskId)
                    vmDetails.put("state",virtualMachine.state)
                    vmDetails.put("ebsOptimized",virtualMachine.ebsOptimized)
                    vmDetails.put("monitoringEnabled",virtualMachine.monitoringEnabled)
                    vmDetails.put("launchTime",virtualMachine.launchTime)
                    vmDetails.put("monitoringEnabled",virtualMachine.monitoringEnabled)
                    vmDetails.put("image",Images.findById(virtualMachine.image.id)?.name)
                    vmDetails.put("flavor",Flavors.findById(virtualMachine.flavor.id)?.name)
                    vmDetails.put("vpcId",Vpc.findByReferenceId(virtualMachine.vpc.referenceId)?.referenceId)
                    vmDetails.put("sshKey",SSHKeys.findById(virtualMachine.sshkeys.id)?.name)
                    list.add(vmDetails)
                }
            }                        
            return list
            
        } catch(Exception ex) {
            throw ex
        }
        
                        
    }
    
    def createVM(requestData) {
        
        try { 
            
            def user = springSecurityService.currentUser; 
                   
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();  
                        
            def instanceName = requestData.name
            def region = Region.findById(requestData.regionId)
            def flavor = Flavors.findById(requestData.flavor)
            def image = Images.findByReferenceId(requestData.image)
            def vpc = Vpc.findById(requestData.vpc)
            def subnet = Subnet.findById(requestData.subnet)
            def sshKey = SSHKeys.findByName(requestData.keypair)
            def securityGroup = SecurityGroup.findById(requestData.securityGroup)            
            def volumeType = requestData.volumeType
            def rootSize = requestData.rootSize
            def iops = requestData.iops

            // request for new on demand instance
            RunInstancesRequest instancesRequest = new RunInstancesRequest()
            instancesRequest.withImageId(image.getReferenceId())
            //instancesRequest.withImageId("ami-146e2a7c")
            instancesRequest.withInstanceType(flavor.getName())
            instancesRequest.withMinCount(1)
            instancesRequest.withMaxCount(1)
            instancesRequest.withKeyName(sshKey.getName())            
            instancesRequest.withMonitoring(false)
            instancesRequest.withSecurityGroupIds(securityGroup.getReferenceId())
            instancesRequest.withSubnetId(subnet.getReferenceId())
            
             // Create the block device mapping to describe the root partition.
            BlockDeviceMapping blockDeviceMapping = new BlockDeviceMapping();
            blockDeviceMapping.setDeviceName("/dev/xvda");
            blockDeviceMapping.setVirtualName("My Device Block");            
            
            // Set the attributes
            EbsBlockDevice ebs = new EbsBlockDevice();
            ebs.setVolumeSize(rootSize);
            
            //TODO: change volumetypes hardcoded
            if(volumeType.equals(1)) {
                ebs.setVolumeType(AwsVolumeType.Gp2); 
            } else if (volumeType.equals(2)) {
                ebs.setVolumeType(AwsVolumeType.Io1);  
                ebs.setIops(iops);
            } else {
                ebs.setVolumeType(AwsVolumeType.Standard);    
            }

             //TODO: change snapshotId hardcoded
            ebs.setSnapshotId("snap-f518b274");
            blockDeviceMapping.setEbs(ebs);           
            
            // Add the block device mapping to the block list.
            ArrayList<BlockDeviceMapping> blockDeviceMappings = new ArrayList<BlockDeviceMapping>();
            blockDeviceMappings.add(blockDeviceMapping);            
            instancesRequest.setBlockDeviceMappings(blockDeviceMappings);

            def endPoint = "https://" + region.getEndPoint();            
            ec2.setEndpoint(endPoint);
            RunInstancesResult instancesResult = ec2.runInstances(instancesRequest);
            System.out.println(instancesResult.getReservation().getReservationId());

            /// Find newly created instance id
            String instanceId=null;
            DescribeInstancesResult describeInstancesResult = ec2.describeInstances();
            List<Reservation> reservations = describeInstancesResult.getReservations();       
            System.out.println(" ------- Reservation List size " + reservations.size() + " ---------");
            for(Reservation reservation :  reservations){
                if(reservation != null ){
                    for(Instance  instance :  reservation.getInstances()){                                        
                        if (instance.getState().getName().equals("pending")) {
                            instanceId = instance.getInstanceId();
                        }                         
                    }                
                }            
            }        

            System.out.println("New Instance ID :" + instanceId);              

            /// Creating Tag for New Instance ////
            println("Creating Tags for New Instance");
            CreateTagsRequest crt = new CreateTagsRequest();
            ArrayList<Tag> arrTag = new ArrayList<Tag>();
            arrTag.add(new Tag().withKey("Name").withValue(instanceName));
            crt.setTags(arrTag);

            ArrayList<String> arrInstances = new ArrayList<String>();
            arrInstances.add(instanceId);
            crt.setResources(arrInstances);
            ec2.createTags(crt);                
            
            DescribeInstancesResult describeInstancesResult1 = ec2.describeInstances();
            List<Reservation> reservations1 = describeInstancesResult1.getReservations();       
            System.out.println(" ------- Reservation List size " + reservations1.size() + " ---------");
            for(Reservation reservation :  reservations1){
                if(reservation != null ){
                    for(Instance instance :  reservation.getInstances()){ 
                        
                        if(instance.getInstanceId().equals(instanceId)) {
                          def virtualMachine = new VirtualMachine()
                        
                            virtualMachine.name = instanceName
                            virtualMachine.referenceId = instance.getInstanceId()
                            virtualMachine.architecture = instance.getArchitecture()
                            virtualMachine.hypervisor = instance.getHypervisor()
                            virtualMachine.rootDeviceType = instance.getRootDeviceType()
                            virtualMachine.rootDeviceName = instance.getRootDeviceName()
                            virtualMachine.privateDnsName = instance.getPublicDnsName()
                            virtualMachine.publicDnsName = instance.getPublicDnsName()
                            virtualMachine.privateIpAddress = instance.getPrivateIpAddress()
                            virtualMachine.publicIpAddress = instance.getPublicIpAddress()
                            virtualMachine.kernelId = instance.getKernelId()
                            virtualMachine.ramdiskId = instance.getRamdiskId()
                            virtualMachine.ebsOptimized = instance.getEbsOptimized()
                            virtualMachine.monitoringEnabled = instance.getMonitoring()
                            virtualMachine.launchTime = new java.sql.Timestamp(instance.getLaunchTime().getTime());

                            virtualMachine.region = region
                            virtualMachine.image = image
                            virtualMachine.flavor = flavor
                            virtualMachine.state = instance.getState().getName()
                            virtualMachine.account = user.account
                            virtualMachine.user = user            
                            virtualMachine.vpc = vpc
                            virtualMachine.subnet = subnet
                            virtualMachine.sshkeys = sshKey
                            virtualMachine.securityGroup = securityGroup
                            virtualMachine.createdAt = new Date()

                            virtualMachine.save(flush: true)                        
                            if (!virtualMachine.save()) {
                                virtualMachine.errors.allErrors.each { println it }
                            }  
                        }                         
                    }
                }
            }    
     
            ArrayList<ArrayList<String>> createVMResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createVMResponse.add(item)
            
            return createVMResponse            
        } 
        catch (AmazonClientException ex) {
            ArrayList<ArrayList<String>> createVMResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_FAILURE);
            println("Exception " +ex.localizedMessage)
            String[] exception = ex.localizedMessage.split("Service");
            item.put("message", exception[0].substring(0, exception[0].length()-1));
            createVMResponse.add(item)            
            return createVMResponse 
        } 
        catch (Exception ex) {
            throw ex;
        }    
    }
    
    def viewServer(regionId, referenceId) {
        
        AmazonEC2 ec2 = awsAuthService.authenticateEC2();            
        def user = springSecurityService.currentUser;                        
        def currentRegion = Region.findById(regionId)
        def virtualMachine = VirtualMachine.findByReferenceId(referenceId)
        if(currentRegion != null && virtualMachine != null){
            String endPoint = "https://" + currentRegion.getEndPoint();                 
            ec2.setEndpoint(endPoint);
            
            DescribeInstancesRequest instanceRequest = new DescribeInstancesRequest();
            instanceRequest.withInstanceIds(referenceId);
            DescribeInstancesResult describeInstancesResult = ec2.describeInstances(instanceRequest);
            List<Reservation> reservations = describeInstancesResult.getReservations();       
            for(Reservation reservation :  reservations){
                if(reservation != null ){
                    for(Instance  instance :  reservation.getInstances()) {                                        
                        virtualMachine.state = instance.getState().getName()
                        virtualMachine.save(flush:true)
                    }                
                }            
            }                        
        }    
                               
        ArrayList<ArrayList<String>> VMinfo = new ArrayList<ArrayList<String>>();
        
        if(virtualMachine != null){
            
            HashMap vmDetails = new HashMap()
            vmDetails.put("id",virtualMachine.id)
            vmDetails.put("name",virtualMachine.name)
            vmDetails.put("referenceId",virtualMachine.referenceId)
            vmDetails.put("architecture",virtualMachine.architecture)
            vmDetails.put("hypervisor",virtualMachine.hypervisor)
            vmDetails.put("rootDeviceType",virtualMachine.rootDeviceType)
            vmDetails.put("rootDeviceName",virtualMachine.rootDeviceName)
            vmDetails.put("privateDnsName",virtualMachine.privateDnsName)
            vmDetails.put("publicDnsName",virtualMachine.publicDnsName)
            vmDetails.put("privateIpAddress",virtualMachine.privateIpAddress)
            vmDetails.put("publicIpAddress",virtualMachine.publicIpAddress)
            vmDetails.put("kernelId",virtualMachine.kernelId)
            vmDetails.put("ramdiskId",virtualMachine.ramdiskId)
            vmDetails.put("state",virtualMachine.state)
            vmDetails.put("ebsOptimized",virtualMachine.ebsOptimized)
            vmDetails.put("monitoringEnabled",virtualMachine.monitoringEnabled)
            vmDetails.put("launchTime",virtualMachine.launchTime)
            vmDetails.put("monitoringEnabled",virtualMachine.monitoringEnabled)
            vmDetails.put("image",Images.findById(virtualMachine.image.id)?.name)
            vmDetails.put("flavor",Flavors.findById(virtualMachine.flavor.id)?.name)
            vmDetails.put("vpcId",Vpc.findByReferenceId(virtualMachine.vpc.referenceId)?.referenceId)
            vmDetails.put("sshKey",SSHKeys.findById(virtualMachine.sshkeys.id)?.name)
            
            VMinfo.add(vmDetails)          
        }
        
        return VMinfo        
        
    }
    
    def start(requestData) {   
        try { 
            def user = springSecurityService.currentUser;
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();                                 
            def currentRegion = Region.findById(requestData.regionId)
            def virtualMachine = VirtualMachine.findByReferenceId(requestData.referenceId)
            if(currentRegion != null && virtualMachine != null){
                String endPoint = "https://" + currentRegion.getEndPoint();                 
                ec2.setEndpoint(endPoint);
                
                StartInstancesRequest startInstancesRequest = new StartInstancesRequest();
                List<String> instanceIds = new ArrayList<String>();
                instanceIds.add(requestData.referenceId);
                startInstancesRequest.setInstanceIds(instanceIds);
                StartInstancesResult startInstancesResult = ec2.startInstances(startInstancesRequest);
            }  
       
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            vmJob.add(item);
            
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def stop(requestData) { 
        try {
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();            
            def user = springSecurityService.currentUser;                        
            def currentRegion = Region.findById(requestData.regionId)
            def virtualMachine = VirtualMachine.findByReferenceId(requestData.referenceId)
            if(currentRegion != null && virtualMachine != null){
                String endPoint = "https://" + currentRegion.getEndPoint();                 
                ec2.setEndpoint(endPoint);
                
                StopInstancesRequest stopInstancesRequest = new StopInstancesRequest();
                List<String> instanceIds = new ArrayList<String>();
                instanceIds.add(requestData.referenceId);
                stopInstancesRequest.setInstanceIds(instanceIds);
                StopInstancesResult stopInstanceResult = ec2.stopInstances(stopInstancesRequest);
            }
            
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            vmJob.add(item);
            
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }   
    } 
        
    def reboot(requestData) {  
        
        try{
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>(); 
            
            def user = springSecurityService.currentUser;
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();                                 
            def currentRegion = Region.findById(requestData.regionId)
            def virtualMachine = VirtualMachine.findByReferenceId(requestData.referenceId)
            if(currentRegion != null && virtualMachine != null){
                String endPoint = "https://" + currentRegion.getEndPoint();                 
                ec2.setEndpoint(endPoint);
                
                RebootInstancesRequest rebootInstancesRequest = new RebootInstancesRequest();        
                List<String> instanceIds = new ArrayList<String>();
                instanceIds.add(requestData.referenceId);
                rebootInstancesRequest.setInstanceIds(instanceIds);
                ec2.rebootInstances(rebootInstancesRequest);
            }
                        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            vmJob.add(item);
            
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }    
    
    def terminate(requestData) {     
        try {
            
            def user = springSecurityService.currentUser; 
                        
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>();                         
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();                                 
            def currentRegion = Region.findById(requestData.regionId)
            def virtualMachine = VirtualMachine.findByReferenceId(requestData.referenceId)
            if(currentRegion != null && virtualMachine != null){
                String endPoint = "https://" + currentRegion.getEndPoint();                 
                ec2.setEndpoint(endPoint);
                
                TerminateInstancesRequest terminateInstanceRequest = new TerminateInstancesRequest();
                List<String> instanceIds = new ArrayList<String>();
                instanceIds.add(requestData.referenceId);
                terminateInstanceRequest.setInstanceIds(instanceIds);
                TerminateInstancesResult terminatedInstancesResult = ec2.terminateInstances(terminateInstanceRequest); 
            }
                
            virtualMachine.deleted = true;
            virtualMachine.deletedAt = new Date();
            
            virtualMachine.save(flush: true); 
            if (virtualMachine.hasErrors()) {
                throw new ValidationException(virtualMachine.errors.allErrors);
            } 
            def volumes = Volume.findByVirtualMachine(virtualMachine);
            for(Volume volume : volumes) {
                volume.virtualMachine = null;
                volume.deleted = true;
                volume.deletedAt = new Date();
                volume.save(flush: true);
            }

            item.put("result", GeneralConstants.RESULT_SUCCESS);
            vmJob.add(item);                       
                        
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
}
