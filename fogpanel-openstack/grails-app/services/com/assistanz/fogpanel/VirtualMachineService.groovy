package com.assistanz.fogpanel

import com.assistanz.openstack.OpenStackServer;
import java.util.Arrays;
import java.util.List;
import com.assistanz.cloud.config.ConfigLoader
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.compute.RebootType;
import org.openstack4j.model.compute.Action;
import org.openstack4j.api.Builders;
import org.openstack4j.model.image.Image;
import org.openstack4j.model.compute.Address;
//import org.openstack4j.model.compute.Addresses;
import org.openstack4j.model.compute.SecGroupExtension;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.compute.actions.RebuildOptions;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.IP;
import org.springframework.context.MessageSource
import grails.transaction.Transactional
import org.openstack4j.model.compute.VNCConsole;
import org.codehaus.groovy.grails.commons.ApplicationHolder

@Transactional
class VirtualMachineService {

    def springSecurityService
    InvoiceService invoiceService
    OpenStackAuthService openStackAuthService    
    MessageSource messageSource
    def list(referenceId) {
        
        try { 
            def role = springSecurityService.getPrincipal().getAuthorities()          
            def user = springSecurityService.currentUser;
            ArrayList<ArrayList<String>> VMList = new ArrayList<ArrayList<String>>();
            
            if(role.iterator().next() == "ROLE_ADMIN" )  {
                println("admin 3")
                def instanceList = VirtualMachine.findAllWhere(deleted: false)                 
                if(instanceList) {
                    for(int i=0; i < instanceList.size(); i++) {
                        HashMap item = new HashMap();
                        def instance = instanceList[i];
                          def instanceSize = instance.flavor.name + " | " + 
                            instance.flavor.memory + " MB RAM " + "| " + 
                            instance.flavor.vcpus + " VCPU | " + 
                            instance.flavor.rootgb + " GB Disk ";
                        
                        item.put("name", instance.name);
                        item.put("keypair", instance.keypair.name == null ? "null" : instance.keypair.name);
                        item.put("referenceId", instance.referenceId);
                        item.put("state", instance.status);         
                        item.put("account", instance.account.accountIdentifier);                                                         
                        item.put("zone", instance.zone.name);
                        item.put("imageId", instance.image.referenceId);
                        item.put("image", instance.image.name);
                        item.put("flavorId", instance.flavor.referenceId);
                        item.put("flavor", instance.flavor.name);
                        item.put("instanceSize", instanceSize);   
                        VMList.add(item)   
                    }
                }
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") { 
                println("user2")
                ConfigLoader configLoader = ConfigLoader.getInstance();            
                Properties props = configLoader.getProperties();
                OSClient os = openStackAuthService.authenticate();               
                
                if(os) {    
                
                    List<? extends Server> vms = os.compute().servers().listAll(true);
                    HashMap<String,String> openStackVMList = new HashMap<String,String>();
                    for (Server vm : vms) {                    
                    openStackVMList.put(vm.getId(),"referenceId");

                    def instance = VirtualMachine.findWhere(referenceId: vm.getId(), deleted: false, user: user) 
                    if(instance) {                                        
                        instance.status = vm.getStatus() 
                        instance.save(flush: true)
                    
                        HashMap item = new HashMap();
                        item.put("name", instance.name);
                        item.put("keypair", instance.keypair.name == null ? "null" : instance.keypair.name);
                        item.put("referenceId", instance.referenceId);
                        item.put("state", instance.status);
                        item.put("powerState", vm.getPowerState());

                        def monitoringDevice = MonitoringDevice.findWhere(virtualMachine: instance, deleted: false)
                        def instanceShutOff;
                        if(vm.getPowerState() == "Shutdown" || vm.getPowerState() == "4") {
                            instanceShutOff = false;
                            if(monitoringDevice) {
                                instanceShutOff = true;
                            }

                        }
                    
                        def instanceSize = instance.flavor.name + " | " + 
                                       instance.flavor.memory + " MB RAM " + "| " + 
                                       instance.flavor.vcpus + " VCPU | " + 
                                       instance.flavor.rootgb + " GB Disk ";
                        item.put("instanceShutOff", instanceShutOff)
                        item.put("zone", vm.getAvailabilityZone());
                        item.put("imageId", instance.image.referenceId);
                        item.put("image", instance.image.name);
                        item.put("flavorId", instance.flavor.referenceId);
                        item.put("flavor", instance.flavor.name);
                        item.put("instanceSize", instanceSize);
                        item.put("task", vm.getTaskState());
                        if(instance.status == "Erro") {
                        
                        } else {   
                            if(vm.getPowerState() == "1") {
                                Map<String, List<? extends Address>> address = vm.getAddresses().getAddresses();
                                def ips = "";
                                def totalAddress = address.entrySet().size();
                                def i=1;
                                def ipAddressString = "";
                                ArrayList<ArrayList<String>> ipAddrList = new ArrayList<ArrayList<String>>();
                                for(Map.Entry<String, List<? extends Address>> entrySetAdd: address.entrySet()) {
                                     for(Address address1 : entrySetAdd.getValue()) {  
                                        if(address1.getType().equals("fixed")) {
    //                                             ipAddrList.add("Fixed IP : " + address1.getAddr()); 
                                          ipAddrList.add(address1.getAddr()); 
                                        } else if(address1.getType().equals("floating")) {
    //                                              ipAddrList.add("Floating IP : " +address1.getAddr());  
                                           ipAddrList.add(address1.getAddr());  
                                        }
                                     }
                                 }

                                 def j =0;
                                 for(String ipAddr: ipAddrList) {                                       
                                    j++;
                                    ipAddressString += ipAddr;
                                    if(ipAddrList.size() != j) {
                                        ipAddressString += ", ";
                                    }                                       
                                 }                                    
                                 item.put("ip", ipAddressString);

                                } else {
                                     item.put("ip", "");
                                }
                            } 
                            VMList.add(item)                             
                        }
                    }
                }
            } 
        return VMList
            
        } catch (Exception ex) {   
            throw ex;
        }
    }
            
    def vmListForAttachement() {
        try {
            ArrayList<ArrayList<String>> instanceListResponse = new ArrayList<ArrayList<String>>();
            def user = springSecurityService.currentUser;
            
            def instanceList = VirtualMachine.withCriteria {
                eq('deleted', false)  
                eq('user', user)
            }
            for(def instance: instanceList) {
                HashMap<String,String> item = new HashMap<String,String>();
                item.put("referenceId", instance.referenceId)
                item.put("name", instance.name)
                instanceListResponse.add(item);
            }
            
            return instanceListResponse;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def count() {
        try {
            
            ArrayList<ArrayList<String>> instanceCount = new ArrayList<ArrayList<String>>();
            
            def totalinstance = VirtualMachine.withCriteria { 
                eq('deleted', false)  
            }.size();
            
            def runningInstance = VirtualMachine.withCriteria {
                eq('deleted', false)
                eq('status', "ACTIVE")  
            }.size();
            
            def stoppedInstance = VirtualMachine.withCriteria {
                eq('deleted', false)
                eq('status', "SHUTOFF")  
            }.size();
            
            println("totalinstance: "+totalinstance)
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("totalInstance", totalinstance);
            item.put("runningInstance", runningInstance);
            item.put("stoppedInstance", stoppedInstance);
            instanceCount.add(item);
            
            return instanceCount;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def getLog(referenceId) {
        
        try { 
            
            def user = springSecurityService.currentUser;
            OSClient os = openStackAuthService.authenticate();  
            
            ArrayList<ArrayList<String>> VMinfo = new ArrayList<ArrayList<String>>();
            
            HashMap item = new HashMap();
            item.put("result", "OK");
            item.put("instanceLogInfo", os.compute().servers().getConsoleOutput(referenceId, 100));
            
            VMinfo.add(item)          
        
            return VMinfo
            
        } catch (Exception ex) {
            throw ex;
        }
    }
       
    def viewServer(referenceId) {
        
        def user = springSecurityService.currentUser;
        OSClient os = openStackAuthService.authenticate();  
        
        
        def instance = VirtualMachine.findByReferenceId(referenceId)
        Server vm = os.compute().servers().get(referenceId);
        println("Vm List" + vm.toString())    
        instance.status = vm.getStatus()
        instance.save(flush: true)
            
        
        ArrayList<ArrayList<String>> VMinfo = new ArrayList<ArrayList<String>>();
            
        HashMap item = new HashMap();
        item.put("name", instance.name);
        item.put("keypair", instance.keypair.name);
        item.put("referenceId", instance.referenceId);
        item.put("state", instance.status);
        item.put("powerState", vm.getPowerState());
        item.put("zone", vm.getAvailabilityZone());
        item.put("createdOn", vm.created);
        item.put("updatedOn", vm.updated);
          
        def monitoringDevice = MonitoringDevice.findWhere(virtualMachine: instance, deleted: false)
        def instanceShutOff;
        println("vm.getPowerState()"+vm.getPowerState())
        if(vm.getPowerState() == "Shutdown" || vm.getPowerState() == "4") {
            instanceShutOff = false;
            if(monitoringDevice) {
                instanceShutOff = true;
            }  
        }
        println("instanceShutOff"+instanceShutOff)
        item.put("instanceShutOff", instanceShutOff)
        item.put("isMonitoringEnabled", monitoringDevice ? true : false)
        item.put("image", instance.image.name);
        item.put("flavor", instance.flavor.name);   
        item.put("flavorMemory", instance.flavor.memory);   
        item.put("flavorVpcu", instance.flavor.vcpus);   
        item.put("flavorRootDisk", instance.flavor.rootgb);           
        item.put("task", vm.getTaskState());
        VNCConsole console = os.compute().servers().getVNCConsole(instance.referenceId, VNCConsole.Type.NOVNC);
        item.put("consoleUrl", console.getURL());
        item.put("instanceLogInfo", os.compute().servers().getConsoleOutput(instance.referenceId, 100));
        if(instance.status == "Erro") {

        } else {
              if(vm.getPowerState() == "1") {
                 Map<String, List<? extends Address>> address = vm.getAddresses().getAddresses();
                def ips = "";
                def totalAddress = address.entrySet().size();
                def i=1;
                def ipAddressString = "";
                def privateIPAddressString = "";
                def floatingIPAddressString = "";
                
                ArrayList<ArrayList<String>> ipAddrList = new ArrayList<ArrayList<String>>();
                ArrayList<ArrayList<String>> floatingIPList = new ArrayList<ArrayList<String>>();
                ArrayList<ArrayList<String>> privateIPList = new ArrayList<ArrayList<String>>();
                
                for(Map.Entry<String, List<? extends Address>> entrySetAdd: address.entrySet()) {
                     for(Address address1 : entrySetAdd.getValue()) {  
                        if(address1.getType().equals("fixed")) {
                            ipAddrList.add(address1.getAddr()); 
                            privateIPList.add(address1.getAddr())
//                          ipAddrList.add(address1.getAddr()); 
                        } else if(address1.getType().equals("floating")) {
                             ipAddrList.add(address1.getAddr());  
                             floatingIPList.add(address1.getAddr())
//                           ipAddrList.add(address1.getAddr());  
                        }
                     }
                 }

                 def index =0;  
                 def j =0;
                 def k =0;
                 
                 for(String ipAddr: ipAddrList) {                                       
                    j++;
                    ipAddressString += ipAddr;
                    if(ipAddrList.size() != j) {
                        ipAddressString += ", ";
                    }                                       
                 } 
                 
                
                 for(String ipAddr: privateIPList) {                                       
                    index++;
                    privateIPAddressString += ipAddr;
                    if(privateIPList.size() != index) {  
                        privateIPAddressString += ", ";
                    }                                       
                 }    
                 
                 for(String ipAddr: floatingIPList) {                                       
                     k++;
                    floatingIPAddressString += ipAddr;
                    if(floatingIPList.size() !=  k) {
                        floatingIPAddressString += ", ";
                    }                                       
                 }
                 item.put("ip", ipAddressString);
                 item.put("privateIPs", privateIPAddressString);
                 item.put("floatingIPs", floatingIPAddressString);                 

                } else {
                  item.put("ip", "");
              }
              
        }

        VMinfo.add(item)          
        
        return VMinfo
        
        
    }
       
    def createVM(requestData) {
        
        try { 
        
            def user = springSecurityService.currentUser; 
                   
            OSClient os = openStackAuthService.authenticate();  
            
            List<String> networkList = new ArrayList<String>();
            List<String> securityGroupList = new ArrayList<String>();
            
            for(def selectedNetwork: requestData.networks) {
                
                networkList.add(selectedNetwork);
            }
            
            for(def selectedSecurityGroup : requestData.securityGroups) {
                securityGroupList.add(selectedSecurityGroup)
            }

            // Create a Server Model Object

            ServerCreate sc = Builders.server().name(requestData.name).flavor(requestData.flavor)
                                .image(requestData.image)
                                .networks(networkList)
                                .keypairName(requestData.keypair)
                                .securityGroups(securityGroupList)
                                .availabilityZone(requestData.availabilityZone)
                                .build();

            // Boot the Server
            Server vm = os.compute().servers().boot(sc);

            def virtualMachine = new VirtualMachine()
            virtualMachine.referenceId = vm.getId()
            virtualMachine.name = requestData.name
            virtualMachine.flavor = Flavors.findByReferenceId(requestData.flavor)
            virtualMachine.image = Images.findByReferenceId(requestData.image)
            virtualMachine.status = vm.getVmState()
            virtualMachine.account = user.account
            virtualMachine.user = user
            virtualMachine.zone = Zone.findByName(requestData.availabilityZone)
            virtualMachine.createdAt = new Date()
            virtualMachine.keypair = SSHKeys.findByName(requestData.keypair)
            virtualMachine.billingType = requestData.billingType
            virtualMachine.save(flush: true)
            
            for(def iteratedSecurityGroup: requestData.securityGroups) {
                
                def securityGroup = SecurityGroup.findByName(iteratedSecurityGroup);
                
                def virtualMachineSecurityGroups = new VirtualMachineSecurityGroups();
                virtualMachineSecurityGroups.createdAt = new Date();
                virtualMachineSecurityGroups.securityGroup = securityGroup;
                virtualMachineSecurityGroups.virtualMachine = virtualMachine;
                virtualMachineSecurityGroups.save(flush: true)
                
            }

 
            for(def iteratedNetwork: requestData.networks) {
                
                def network = Network.findByReferenceId(iteratedNetwork);
                def virtualMachineNetworks = new VirtualMachineNetworks();
                virtualMachineNetworks.createdAt = new Date();
                virtualMachineNetworks.virtualMachine = virtualMachine;
                virtualMachineNetworks.network = network;
                virtualMachineNetworks.save(flush: true)
            }
     
            ArrayList<ArrayList<String>> createVMResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createVMResponse.add(item)
                     
            def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
            def imageCost = virtualMachine.image.cost;
            def price = ""
            def cost = FlavorCostInfo.findWhere(zone:virtualMachine.zone, flavor:Flavors.findByReferenceId(requestData.flavor))?.runningCost  
            
            if(virtualMachine.billingType == "monthly") {
                price = cost * 720
            } else {
                price = cost
            }
            if(!invoice) {
                invoice = invoiceService.createDraftInvoice(user.account)             
            }            
            def invoiceItem = invoiceService.addInvoiceItem(invoice, BillableItem.get(1), virtualMachine.billingType, requestData.name, virtualMachine.referenceId, requestData.flavor, price, virtualMachine.zone, null)    
            
            if(virtualMachine.image.isVMSnapshot == false) {
                def imageInvoiceItem = invoiceService.addInvoiceItem(invoice, BillableItem.get(10), "monthly", virtualMachine.image.name, virtualMachine.image.referenceId, null, imageCost, null, null) 
            }    
            
            return createVMResponse            
        } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    
    
    def start(String virtualMachineId) {   
        try { 
            def user = springSecurityService.currentUser;
            OSClient os = openStackAuthService.authenticate();  
            
            os.compute().servers().action(virtualMachineId, Action.START);
       
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
    
    def stop(String virtualMachineId) { 
        try {
            
            OSClient os = openStackAuthService.authenticate();  
            
            os.compute().servers().action(virtualMachineId, Action.STOP);
            
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
    
    def changeInstanceActions(requestData) {
        
        try {
            
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();    
            
            def user = springSecurityService.currentUser;

            OSClient os = openStackAuthService.authenticate();  
            
            ActionResponse actionResponse = os.compute().servers().action(requestData.virtualMachineId, Action.valueOf(requestData.actionType));
            
            if(actionResponse.isSuccess() == true) {
      
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                vmJob.add(item);
            
            } else{
                
            }
            
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
            OSClient os = openStackAuthService.authenticate();  
            
            ActionResponse actionResponse = os.compute().servers().reboot(requestData.virtualMachineId, RebootType.valueOf(requestData.rebootType));
                        
            if(actionResponse.isSuccess() == true) {
                       
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                vmJob.add(item);
            
            } else {
                
                println("Fault message"+actionResponse.getFault());
            }
            
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }      
      
    def delete(requestData) {     
        try {
            
            def user = springSecurityService.currentUser;

            OSClient os = openStackAuthService.authenticate();  
                        
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>(); 
                        
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(requestData.referenceId);
            
            
            def monitoringDevice = MonitoringDevice.findWhere(virtualMachine: virtualMachine, deleted: false)
            
            //Dependency check            
            if(monitoringDevice) {
                
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                Object[] vmArgs = new Object[2];
                vmArgs[0] = virtualMachine.name
                item.put("message", messageSource.getMessage('user.vm.device.cannotDelete', vmArgs, new Locale(defaultLanguage.value)));
                
                item.put("result", GeneralConstants.DEVICE_ENABLED);
                vmJob.add(item);
                
            } else {
                
                ActionResponse actionResponse = os.compute().servers().delete(requestData.referenceId);
                
                if(actionResponse != null && actionResponse.isSuccess()) {
                    
                    virtualMachine.keypair = null;
                    virtualMachine.deleted = true;
                    virtualMachine.deletedAt = new Date();

                    for(Volume volume : virtualMachine.volumes) {
                        volume.virtualMachine = null;
                        volume.save(flush: true);
                    }

                    def flaotingList = FloatingIp.withCriteria {
                        eq('virtualMachine', virtualMachine)
                    }

                    for(def  floatingIp : flaotingList) {

                        floatingIp.fixedIpAddress = null;
                        floatingIp.virtualMachine = null;
                        floatingIp.portId = null;
                        floatingIp.routerId = null;
                        floatingIp.save(flush: true);

                    }

                    virtualMachine.save(flush: true); 
                    if (virtualMachine.hasErrors()) {
                        throw new ValidationException(virtualMachine.errors.allErrors);
                    } 

                    item.put("result", GeneralConstants.RESULT_SUCCESS);
                    vmJob.add(item);
                    
                }
                
                
            }
                        
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    
    def takeVMSnapshot(requestData) {
       try {
            
            def user = springSecurityService.currentUser;
            
            OSClient os = openStackAuthService.authenticate();  
            
            String imageId = os.compute().servers().createSnapshot(requestData.referenceId, requestData.name);
            println("image id" + imageId)
            println("billingType" + requestData.billingType)
            def billingType = requestData.billingType !=null ? requestData.billingType : "hourly"
            def vm = VirtualMachine.findByReferenceId(requestData.referenceId);             
            
            Image image = os.images().get(imageId);
            
            def vmsnap = new Images()
            vmsnap.name = image.getName()
            vmsnap.referenceId = image.getId()
            vmsnap.description = image.getName()
            vmsnap.size = image.getSize()
            vmsnap.status = image.getStatus()
            vmsnap.minRam = image.getMinDisk()
            vmsnap.minDisk = image.getMinRam()
            vmsnap.createdAt = image.getCreatedAt()
            vmsnap.containerFormat = image.getContainerFormat()
            vmsnap.diskFormat = image.getDiskFormat()
            vmsnap.isProtected = image.isProtected()
            vmsnap.isPublic = image.isPublic()
            vmsnap.checksum = image.getChecksum()
            
            vmsnap.isVMSnapshot = true
            vmsnap.virtualMachine = vm

            vmsnap.user = vm?.user
            vmsnap. account = vm?.account
            vmsnap.billingType = billingType
            vmsnap.save(flush: true)
            if (!vmsnap.save()) {
                vmsnap.errors.allErrors.each { println it }
            }
            
            def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
            def price = 0  
            def cost = MiscellaneousOfferRegionCost.findWhere(miscellaneousOffer:MiscellaneousOffer.get(1), region:Region.get(requestData.regionId))?.cost
            
            if(billingType == "monthly") {
                price = cost * 720
            } else {
                price = cost
            }
            if(!invoice) {
                invoice = invoiceService.createDraftInvoice(user.account) 
            } 
            invoiceService.addInvoiceItem(invoice, BillableItem.get(13), billingType, vmsnap.name, vmsnap.referenceId, null, price, null, null)
            
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            vmJob.add(item);
            
            return vmJob;
            
           
       }  catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
        
    }
    
    def resizeInstance(requestData) {
        
        try {
            
            ArrayList<ArrayList<String>> resizeResponse = new ArrayList<ArrayList<String>>(); 
            
            def user = springSecurityService.currentUser;
            
            OSClient os = openStackAuthService.authenticate(); 
            
            println("requestData.instanceId"+requestData.instanceId)
            println("requestData.flavorId"+requestData.flavorId)
            
            ActionResponse actionResponse = os.compute().servers().resize(requestData.instanceId, requestData.flavorId);
            
            def flavor = Flavors.findByReferenceId(requestData.flavorId);
            
            if(actionResponse.isSuccess() == true) {
                
                def virtualMachine = VirtualMachine.findByReferenceId(requestData.instanceId);
                virtualMachine.flavor = flavor;
                virtualMachine.save(flush: true);
                       
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                resizeResponse.add(item);
            
            } else {
                           
            }
                      
            return resizeResponse;
            
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def rebuildInstance(requestData) {
        
        try {
            
            ArrayList<ArrayList<String>> resizeResponse = new ArrayList<ArrayList<String>>(); 
            
            def user = springSecurityService.currentUser;
 
            OSClient os = openStackAuthService.authenticate();  
            
            ActionResponse actionResponse = os.compute().servers().rebuild(requestData.instanceId, RebuildOptions.create().image(requestData.imageId).name(requestData.instanceName));
            
            if(actionResponse.isSuccess() == true) {
                
                def image = Images.findByReferenceId(requestData.imageId);
                
                def virtualMachine = VirtualMachine.findByReferenceId(requestData.instanceId);
                virtualMachine.name = requestData.instanceName;
                virtualMachine.image = image;
                virtualMachine.save(flush: true);
                       
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                resizeResponse.add(item);
            
            } else {
                           
            }
                      
            return resizeResponse;
            
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def getAttchedVolumeList(serverId) {
        
        try {
            def virtualMachine = VirtualMachine.findByReferenceId(serverId);
            
            ArrayList<ArrayList<String>> attchedVolumeList = new ArrayList<ArrayList<String>>();
                      
            for(def volume : virtualMachine.volumes) {
                HashMap item = new HashMap();
                item.put("referenceId", volume.referenceId);
                item.put("name", volume.name);
                item.put("size", volume.size);
                
                attchedVolumeList.add(item);
            }
            
            return attchedVolumeList;
            
        } catch (Exception ex) {          
            throw ex;
        } 
        
    }
    
    def vmNicList(serverId) {
        
        try {
            
            ArrayList<ArrayList<String>> nicList = new ArrayList<ArrayList<String>>();
            
            def user = springSecurityService.currentUser;
 
            OSClient os = openStackAuthService.authenticate();  
            
            List<? extends Port> ports = os.networking().port().list();
                        
            for(Port port : ports) {
                
                HashMap item = new HashMap();
                
                String deviceId = port.getDeviceId();
                
                if(deviceId != null && !deviceId.isEmpty() && deviceId != "null" && deviceId.equals(serverId)) {
                
                    for(IP ip : port.getFixedIps()) {
                        item.put("ipAddress",ip.getIpAddress());
                        item.put("subnetId",ip.getSubnetId());
                    }

                    //Get network 
                    def network = Network.findByReferenceId(port.getNetworkId());
                    
                    item.put("referenceId",port.getId());
                    item.put("name",port.getName());
                    item.put("network",network);
                    item.put("macAddress",port.getMacAddress());
                    item.put("status",port.getState());
                    item.put("adminState",port.isAdminStateUp());
                    nicList.add(item);
                }
            }
            return nicList;
            
            
        } catch (Exception ex) {          
            throw ex;
        } 
        
    }
    
    def getVMCount() {
        try {
            def user = springSecurityService.currentUser;
            ArrayList<ArrayList<String>> instanceCount = new ArrayList<ArrayList<String>>();
            
            def totalinstance = VirtualMachine.withCriteria { 
                eq('deleted', false)
                eq('user', user)
            }.size();
            
            def runningInstance = VirtualMachine.withCriteria {
                eq('deleted', false)
                eq('status', "ACTIVE")  
                eq('user', user)
            }.size();
            
            def stoppedInstance = VirtualMachine.withCriteria {
                eq('deleted', false)
                eq('status', "SHUTOFF")  
                eq('user', user)
            }.size();
            def runningAndStoppedInstances = runningInstance + stoppedInstance;
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("totalInstance", totalinstance);
            item.put("runningAndStoppedInstances", runningAndStoppedInstances);
            item.put("runningInstance", runningInstance);
            item.put("stoppedInstance", stoppedInstance);
            item.put("vmLimit", 10);
            instanceCount.add(item);
            return instanceCount;
        } catch(Exception ex) {
            throw ex;
        }
    }
    
}
