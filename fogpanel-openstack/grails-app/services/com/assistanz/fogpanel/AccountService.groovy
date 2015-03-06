package com.assistanz.fogpanel

import com.assistanz.fogpanel.paymentgateway.*;
import grails.converters.deep.JSON
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import com.assistanz.fogpanel.GeneralConstants;
import com.assistanz.fogpanel.MailTemplateManager;
import com.assistanz.fogpanel.Account;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.assistanz.fogpanel.MailManager;
import com.assistanz.fogpanel.NotificationService
import java.text.SimpleDateFormat
import java.util.regex.Pattern
import javax.servlet.http.HttpSession
import java.util.regex.Matcher
import org.codehaus.groovy.grails.commons.ApplicationHolder
//import com.assistanz.cloud.cloudstack.ssh.CSSSHService
import com.assistanz.fogpanel.PaymentService;
import org.springframework.context.MessageSource
import org.apache.commons.logging.LogFactory;
import com.assistanz.openstack.OpenStackServer;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.model.identity.Role as OpenstackRole;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.identity.User as OpenstackUser;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.compute.Keypair;
import com.assistanz.cloud.config.ConfigLoader;
import com.assistanz.fogpanel.GenerateKeyPair;
import com.assistanz.fogpanel.Config
import grails.transaction.Transactional
import com.fogpanel.openstackuserapi.HttpConnector
import org.openstack4j.model.compute.QuotaSet as ComputeQuotaSet;
import org.openstack4j.model.network.NetQuota;
import org.openstack4j.model.storage.block.QuotaSet as CinderQuotaSet;


@Transactional
class AccountService {

    MessageSource messageSource
    def springSecurityService; 
    def paymentService
    NotificationService notificationService
    ConfigService configService;
    KeypairService keypairService;
    //    LicenseValidationService licenseValidationService
    
    private static final log = LogFactory.getLog(this)

    def virtualMachineServer() {
        
    }
    
    
    def resourceCount() {
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()    
            def totalAccount = 0;
            def retailAccount = 0;
            def trialAccount = 0;                  
            if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {                       
                totalAccount = Account.withCriteria {               
                    ne("accountType", AccountType.values()[2])                        
                }     
                def virtualMachines = VirtualMachine.findAllWhere(deleted: false, user: user) 
                def volumes = Volume.findAllWhere(deleted: false, user: user)          
                def images = Images.withCriteria {                    
                    eq('deleted', false)                                                                                               
                } 
                
                ArrayList<ArrayList<String>> countList = new ArrayList<ArrayList<String>>();                
                HashMap<String,String> item = new HashMap<String,String>();
                item.put("vmCount", virtualMachines.size());
                item.put("volumeCount", volumes.size());
                item.put("imageCount", images.size());   
                item.put("privateKeyExistsInDB", keypairService.isPrivateKeyExistsInDB());
                countList.add(item);                 
                return countList
            }
        } catch (ValidationException ex) {
            throw ex                
        } catch (LicenseExpiredException ex) {
            throw ex
        } catch (NullPointerException ex){
            throw ex
            Console.print(ex);
        } catch (Exception ex){
            throw ex
        }
    }
    
    def validateUser(String email) {          
        try {
            
            //def result = Account.findByEmail(email);
            def invitation = Invitation.findByName(userName);
            def userNamePatern = "^[a-zA-Z0-9-@.]{1,200}";                
            Pattern pattern = Pattern.compile(userNamePatern)
            Matcher matchContent = pattern.matcher(userName);               
            def matchResult = matchContent.matches();
            ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>(); 
            HashMap<String,String> item = new HashMap<String,String>();    
            if(invitation) {
                item.put("result", "exist");
                arrayList.add(item);
            } else if(matchResult == false || matchResult == "false") {
                item.put("result", "invalid");
                arrayList.add(item);
            } else if (matchResult == true || matchResult == "true") {
                item.put("result", "OK");
                arrayList.add(item);
            }                                                                            
            return arrayList;
        } catch (ValidationException ex) {
            throw ex                
        } catch (LicenseExpiredException ex) {
            throw ex
        } catch (NullPointerException ex){
            throw ex
            Console.print(ex);
        } catch (Exception ex){
            throw ex
        }
    }
    
    def getResourceCount() {
        try {
           
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            def account;
            
//            if(accountId) {
//               account = Account.get(accountId)
//            } else {
                account = user.account;
//            }
            
            def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
          
            ArrayList countList = new ArrayList(); 
            HashMap countItem = new HashMap();  
                                    
            def totalRunningVm =  VirtualMachine.withCriteria {
                eq('deleted', false)
//                eq('status', "ACTIVE") 
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
//                if(accountId) {
//                    eq('owner', Account.get(accountId))
//                } else {
                    eq('user', user)
//                }
            }
            def totalContainer =  Container.withCriteria {
                eq('deleted', false)                
                eq('account', user.account)
            }
            
            
            def totalContainerCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
                or {
                    eq("billableItem", BillableItem.get(15))  
//                    or {
//                        eq("billableItem", BillableItem.get(13))
//                    }
                }
                projections {
                     sum("totalAmount")
                }
            }
            
            def totalRunningVmCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
                or {
                    eq("billableItem", BillableItem.get(1))  
//                    or {
//                        eq("billableItem", BillableItem.get(13))
//                    }
                }
                projections {
                     sum("totalAmount")
                }
            }
            
            
            def totalNetworkCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
                or {
                    eq("billableItem", BillableItem.get(4))  
                    or {
                        eq("billableItem", BillableItem.get(11))
                        or {
                            eq("billableItem", BillableItem.get(12))
                        }
                    }
                }
                projections {
                     sum("totalAmount")
                }
            }        
             println("invoice 1" + invoice)
             println("network cost 2" + totalNetworkCost)           
            def totalVolumes = Volume.withCriteria {
                eq('deleted', false)                
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
//                if(accountId) {
//                    eq('owner', Account.get(accountId))
//                } else {
                    eq('user', user)
//                }
            }
            
            def totalVolumeCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                } 
                or {
                    eq("billableItem", BillableItem.get(2))  
//                    or {
//                        eq("billableItem", BillableItem.get(14))
//                    }
                }
                projections {
                     sum("totalAmount")
                 }
            }
            
            def totalNetwork = Network.withCriteria {
                eq('deleted', false)
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
//                if(accountId) {    
//                    eq('account', Account.get(accountId))
//                } else {
                    eq('account', user.account)
//                }
//                isNull("vpc") 
            }                       
            
            def totalVolumeSnapshot = VolumeSnapshot.withCriteria {
                eq('deleted', false)
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
//                if(accountId) {
//                    eq('owner', Account.get(accountId))
//                } else {
                    eq('user', user)
//                }
            }
            
            def volumeSnapshotCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
                eq("billableItem", BillableItem.get(3))
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
                projections {
                     sum("totalAmount")
                 }
            }
            
            def totalVMSnapshot = Images.withCriteria {
                eq('deleted', false)
                eq('isVMSnapshot', true)
                
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
//                if(accountId) {
//                    eq('owner', Account.get(accountId))
//                } else {
                    eq('account', user.account)
//                }
            }
            
            
            def vmSnapshotCost = InvoiceItem.withCriteria {    
                eq("invoice", invoice)
                eq("billableItem", BillableItem.get(13))
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }   
                projections {
                     sum("totalAmount")
                 }
            }                             
            def floatingIPCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
                eq("billableItem", BillableItem.get(14))
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
                projections {
                     sum("totalAmount")
                 }
            }
            
            def totalStoppedVm =  VirtualMachine.withCriteria {
                eq('deleted', false)
                eq('status', "Stopped")
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
//                if(accountId) {
//                    eq('owner', Account.get(accountId))
//                } else {
                    eq('user', user)
//                }
            }
            
//            def totalDestroyedVm =  VirtualMachine.withCriteria {
//                eq('deleted', false)
//                eq('state', "Destroyed")
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
//                if(accountId) {
//                    eq('owner', Account.get(accountId))
//                } else {
//                    eq('user', user)
//                }
//            }
            
            
            
            ArrayList zoneCountList = new ArrayList(); 
            
//            def zoneList = Zone.findAll()
//            for(def zone: zoneList) {
//                
//                HashMap zoneCountItem = new HashMap();  
//                zoneCountItem.put("zone", zone.aliasName)
//                zoneCountItem.put("type", zone.networkType)
//                                
//                def runningVm =  VirtualMachine.withCriteria {
//                    eq('deleted', false)
//                    eq('state', "Running")
//                    eq('zone', zone)
//                    if(accountId) {
//                        eq('owner', Account.get(accountId))
//                    } else {
//                        eq('user', user)
//                    }
//                }
//                
//                def snapCost = InvoiceItem.withCriteria {
//                    eq("invoice", invoice)
//                    eq("billableItem", BillableItem.get(3))
//                    eq('zone', zone)
//                    projections {
//                            sum("totalAmount")
//                    }
//                }
//                
//                def volumeCost = InvoiceItem.withCriteria {
//                    eq("invoice", invoice)
//                    eq('zone', zone)
//                    or {
//                        eq("billableItem", BillableItem.get(2))  
//                        or {
//                            eq("billableItem", BillableItem.get(14))
//                        }
//                    }
//                    projections {
//                         sum("totalAmount")
//                     }
//                }
//                
//                def runningVmCost = InvoiceItem.withCriteria {
//                    eq("invoice", invoice)
//                    eq('zone', zone)
//                    or {
//                        eq("billableItem", BillableItem.get(1))  
//                        or {
//                            eq("billableItem", BillableItem.get(13))
//                        }
//                    }
//                    projections {
//                         sum("totalAmount")
//                     }
//                }
//                
//                
//                def network = Network.withCriteria {
//                    eq('deleted', false)
//                    eq('zone', zone)
//                    if(accountId) {
//                        eq('account', Account.get(accountId))
//                    } else {
//                        eq('account', user.account)
//                    }
//                    isNull("vpc") 
//                }
//                
//                def vpc = VPC.withCriteria {
//                    eq('deleted', false)
//                    eq('zone', zone)
//                    if(accountId) {
//                        eq('account', Account.get(accountId))
//                    } else {
//                        eq('account', user.account)
//                    }   
//                }
//            
                def stoppedVm =  VirtualMachine.withCriteria {
                    eq('deleted', false)
                    eq('status', "SHUTOFF")
//                    eq('zone', zone)
//                    if(accountId) {
//                        eq('owner', Account.get(accountId))
//                    } else {
                        eq('user', user)
//                    }
                }
//                
//                def destroyedVm =  VirtualMachine.withCriteria {
//                    eq('deleted', false)
//                    eq('state', "Destroyed")
//                    eq('zone', zone)
//                    if(accountId) {
//                        eq('owner', Account.get(accountId))
//                    } else {
//                        eq('user', user)
//                    }
//                }
//                
//                def volumes = Volume.withCriteria {
//                    eq('deleted', false)
//                    eq('type', "DATADISK")
//                    eq('zone', zone)
//                    if(accountId) {
//                        eq('owner', Account.get(accountId))
//                    } else {
//                        eq('user', user)
//                    }
//                }
//                
//                def snapshot = Snapshot.withCriteria {
//                    eq('deleted', false)
//                    eq('zone', zone)
//                    if(accountId) {
//                        eq('owner', Account.get(accountId))
//                    } else {
//                        eq('user', user)
//                    }
//                }
//                if(runningVmCost[0] == "null" || runningVmCost[0] == null) {
//                    zoneCountItem.put("runningVmCost", 0 )
//                } else {
//                    zoneCountItem.put("runningVmCost", Math.ceil(runningVmCost[0] * 100d) / 100d)
//                }
//                if(volumeCost[0] == "null" || volumeCost[0] == null) {
//                    zoneCountItem.put("volumeCost", 0 )
//                } else {
//                    zoneCountItem.put("volumeCost", Math.ceil(volumeCost[0] * 100d) / 100d )
//                }
//                if(snapCost[0] == "null" || snapCost[0] == null) {
//                    zoneCountItem.put("snapCost", 0 )
//                } else {
//                    zoneCountItem.put("snapCost", Math.ceil(snapCost[0] * 100d) / 100d)
//                }
//                zoneCountItem.put("runningVm", runningVm.size() + stoppedVm.size() + destroyedVm.size())
//                zoneCountItem.put("stoppedVm", stoppedVm.size())
//                zoneCountItem.put("storage", volumes.size())
//                zoneCountItem.put("snapshot", snapshot.size())
//                zoneCountItem.put("network", network.size())
//                zoneCountItem.put("vpc", vpc.size())
//                zoneCountItem.put("networkCost", 0 )
//                 countItem.put("VpcCost", 0)
//                
//                def totalZoneBandwidth = 0;
//                def totalZoneBandwidthCost = 0;
//            
//                def totalZoneBandCost = InvoiceItem.withCriteria {
//                    eq("invoice", invoice)
//                    eq("billableItem", BillableItem.get(5))
//                    if(zoneReferenceId && zoneReferenceId != "All") {
//                        eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                    }
//                    projections {
//                         sum("totalAmount")
//                         sum("usageUnits")
//                    }
//                }
//            
//                if(totalZoneBandCost[0][0] == "null" || totalZoneBandCost[0][0] == null) {
//                    totalZoneBandwidthCost += 0;
//                } else {
//                    totalZoneBandwidthCost += totalZoneBandCost[0][0];
//                }
//                if(totalZoneBandCost[0][1] == "null" || totalZoneBandCost[0][1] == null) {
//                    totalZoneBandwidth += 0
//                } else {
//                    totalZoneBandwidth += totalZoneBandCost[0][1]
//                }
//
//                def zoneVirtualMachineList = VirtualMachine.withCriteria {
//                    eq('deleted', false)
//                    eq('zone', zone)
//                    if(accountId) {
//                        eq('owner', Account.get(accountId))
//                    } else {
//                        eq('user', user)
//                    } 
//                }
//                for(def zoneVirtualMachine : zoneVirtualMachineList) {
//
//                    double planBandwidth = 1.0 * zoneVirtualMachine.computingOffer.bandWidth
//
//                    def bandWidth = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone :zoneVirtualMachine.zone);
//                    
//                    def bandWidthCost;
//                    
//                    if(bandWidth) {
//                        bandWidthCost = bandWidth.cost
//                    } else {
//                        bandWidthCost = 0
//                    }
//                    
//                    def usageUnits = (zoneVirtualMachine.networkWrite / 1048576) - planBandwidth
//                    def vmband = zoneVirtualMachine.networkWrite / 1048576
//                    if(vmband > planBandwidth) {
//                        double usageAmount = usageUnits * bandWidthCost                          
//                        totalZoneBandwidthCost = totalZoneBandwidthCost + usageAmount
//                        totalZoneBandwidth = totalZoneBandwidth + usageUnits
//                    }
//                }   
//                
//                zoneCountItem.put("bandwidth", totalZoneBandwidth)
//                zoneCountItem.put("bandwidthCost", totalZoneBandwidthCost )
//                
//                zoneCountList.add(zoneCountItem)
//            }
              
//            if(zoneReferenceId && zoneReferenceId != "All") {
//                countItem.put("zoneName", Zone.findByReferenceZoneId(zoneReferenceId)?.aliasName)
//            }
//            countItem.put("zoneCount", zoneCountList)
            countItem.put("totalRunningVm", totalRunningVm.size() + totalStoppedVm.size())
            countItem.put("totalContainer", totalContainer.size())
            
            
            if(totalRunningVmCost[0] == "null" || totalRunningVmCost[0] == null) {
                    countItem.put("totalRunningVmCost", 0 )
            } else {
                countItem.put("totalRunningVmCost", Math.ceil(totalRunningVmCost[0] * 100d) / 100d)
            }
            if(totalContainerCost[0] == "null" || totalContainerCost[0] == null) {
                    countItem.put("totalContainerCost", 0 )
            } else {
                countItem.put("totalContainerCost", Math.ceil(totalContainerCost[0] * 100d) / 100d)
            }                                    
            
            if(totalVolumeCost[0] == "null" || totalVolumeCost[0] == null) {
                countItem.put("totalVolumeCost", 0 )   
            } else {
                countItem.put("totalVolumeCost", Math.ceil(totalVolumeCost[0] * 100d) / 100d)
            }                
            
            if(totalNetworkCost[0] == "null" || totalNetworkCost[0] == null) {
                countItem.put("totalNetworkCost", 0 )
                println("network cost is 1") 
            } else {
                countItem.put("totalNetworkCost", Math.ceil(totalNetworkCost[0] * 100d) / 100d)
                println("network cost is 2 :    " + Math.ceil(totalNetworkCost[0] * 100d) / 100d) 
            }   
            
            
            
            if(vmSnapshotCost[0] == "null" || vmSnapshotCost[0] == null) {
                countItem.put("totalVMSnapshotCost", 0 )
            } else {
                countItem.put("totalVMSnapshotCost", Math.ceil(vmSnapshotCost[0] * 100d) / 100d)
            }   
            if(floatingIPCost[0] == "null" || floatingIPCost[0] == null) {
                countItem.put("floatingIPCost", 0 )
            } else {
                countItem.put("floatingIPCost", Math.ceil(floatingIPCost[0] * 100d) / 100d)
            }                                       
            
            if(volumeSnapshotCost[0] == "null" || volumeSnapshotCost[0] == null) {
                countItem.put("volumeSnapshotCost", 0 )
            } else {
                countItem.put("volumeSnapshotCost", Math.ceil(volumeSnapshotCost[0] * 100d) / 100d)
            }                                        
            
            ResourceLimit resourceLimit = ResourceLimit.findByAccount(account);
            countItem.put("vmLimit", resourceLimit.instances)
            countItem.put("volumeLimit", resourceLimit.volumes)
            countItem.put("networkLimit", resourceLimit.network)            
            countItem.put("publicIPLimit", resourceLimit.floatingIp);      
            countItem.put("publicIPUsed", FloatingIp.findAllWhere(deleted: false, account:account).size())
            countItem.put("securityGroups", resourceLimit.securityGroups);  
            countItem.put("securityGroupsUsed", SecurityGroup.findAllWhere(deleted: false, account:account).size());  
            countItem.put("ram", resourceLimit.ram);                                          
            countItem.put("cores", resourceLimit.cores);  
            
            def coresUsed = 0;
            def ramUsed = 0;
            // looping instances by account
            for(def vm : totalRunningVm) {
                
                coresUsed += vm.flavor.vcpus;
                ramUsed += vm.flavor.memory;
            }
            countItem.put("coresUsed", coresUsed);
            countItem.put("ramUsed", ramUsed);
            
            def gigabytesUsed = 0;
            countItem.put("gigabytes", resourceLimit.gigabytes);    
            
            // looping volumes and volume snapshot and get total GB used
            for(def volume : totalVolumes) {
                gigabytesUsed += volume.size
            }
            for(def volumeSnapshot :totalVolumeSnapshot ) {
                gigabytesUsed += volumeSnapshot.size
            }                                  
            countItem.put("gigabytesUsed", gigabytesUsed);
            countItem.put("vloumeSnapshotsLimit", resourceLimit.snapshots); 
            
            
            countItem.put("network", resourceLimit.network);             
            
            countItem.put("currency", currency)            
            countItem.put("totalStoppedVm", totalStoppedVm.size())
            countItem.put("totalStorage", totalVolumes.size())
            countItem.put("totalVolumeSnapshot", totalVolumeSnapshot.size())
            countItem.put("totalVMSnapshot", totalVMSnapshot.size())                                                                     
            countItem.put("totalNetwork", totalNetwork.size())
//            countItem.put("totalVpc", totalVpc.size())
//            countItem.put("totalVpcCost", 0)
                                    
            def totalBandwidth = 0;
            def totalBandwidthCost = 0;
            
            def totalBandCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
                eq("billableItem", BillableItem.get(5))
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
                projections {
                     sum("totalAmount")
                     sum("usageUnits")
                }
            }
            
//            if(totalBandCost[0][0] == "null" || totalBandCost[0][0] == null) {
//                totalBandwidthCost += 0;
//            } else {
//                totalBandwidthCost += totalBandCost[0][0];
//            }
//            if(totalBandCost[0][1] == "null" || totalBandCost[0][1] == null) {
//                totalBandwidth += 0   
//            } else {
//                totalBandwidth += totalBandCost[0][1]
//            }   
            
//            def virtualMachineList = VirtualMachine.withCriteria {
//                eq('deleted', false)
//                if(zoneReferenceId && zoneReferenceId != "All") {
//                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
//                }
//                if(accountId) {
//                    eq('owner', Account.get(accountId))
//                } else {
//                    eq('user', user)
//                } 
//            }
//            for(def virtualMachine : virtualMachineList) {
//                
//                double planBandwidth = 1.0 * virtualMachine.computingOffer.bandWidth
//
//                def bandWidth = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone : virtualMachine.zone);
//                    
//                def bandWidthCost;
//
//                if(bandWidth) {
//                    bandWidthCost = bandWidth.cost
//                } else {
//                    bandWidthCost = 0
//                }
//
//                def usageUnits = (virtualMachine.networkWrite / 1048576) - planBandwidth
//                def vmband = virtualMachine.networkWrite / 1048576
//                if(vmband > planBandwidth) {
//                    double usageAmount = usageUnits * bandWidthCost                          
//                    totalBandwidthCost = totalBandwidthCost + usageAmount
//                    totalBandwidth = totalBandwidth + usageUnits
//                }
//            }
            
//            countItem.put("totalBandwidthCost", totalBandwidthCost )
//            countItem.put("totalBandwidth", totalBandwidth)
            
            countList.add(countItem)
            
            return countList    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def getInvitation() {
        try {
            ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>(); 
            def invotationList = Invitation.findAll();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            for(int i = 0; i < invotationList.size(); i++) {
                def currentItem = invotationList[i]
                HashMap<String,String> item = new HashMap<String,String>();                
                item.put("id", currentItem.id);
                item.put("name", currentItem.name);                
                item.put("email", currentItem.email);
                item.put("status", currentItem.status);
                item.put("createdDate", dateFormat.format(currentItem.createdDate).toString());
                item.put("type", currentItem.type);
                item.put("name", currentItem.name);   
                arrayList.add(item)
            } 
            return arrayList;
        } catch (ValidationException ex) {
            Console.print(ex);
            [ex] as JSON
        } catch (LicenseExpiredException ex) {
            [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
            Console.print(ex);
            [ex] as JSON
        } catch (Exception ex){
            Console.print(ex);
            [ex] as JSON
        }
    }
    def updateInvitation(String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)  
            def invitation = Invitation.get(requestData.invitationId);
            if(invitation) {                                               
                ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();                                
                def signupType = ""
                if(invitation.type == "retail") {                    
                    signupType = "retailSignup"    
                } else {
                    signupType = "trialSignup"                    
                } 
                Date date = new Date();
                def time = new Timestamp(date.getTime())                                                                                                         
                def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
                def verifyLink = applicationUrl + "/account/invitationsignup" + "?" + "invitationEmail=" + invitation.email + "&" +"invitationName="+invitation.name;                
                invitation.link = verifyLink;                
                invitation.createdDate = time;                
                invitation.save(flush:true);
                if (!invitation.save()) {
                    throw new ValidationException(invitation.errors.allErrors)                                          
                }   
                def orgName = Config.findByName("organisation.name").value;
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                tempalteMap.put("name" , invitation.name)
                tempalteMap.put("verifyLink" , verifyLink)              
                notificationService.send(invitation.email.toString(), "invitation.subject.ftl", tempalteMap, "invitation.ftl")                                                        

                HashMap<String,String> item = new HashMap<String,String>();
                item.put("result", "OK");
                arrayList.add(item);
                return arrayList
            }
        } catch (ValidationException ex) {
            throw ex                
        } catch (LicenseExpiredException ex) {
            throw ex
        } catch (NullPointerException ex){
            throw ex
            Console.print(ex);
        } catch (Exception ex){
            throw ex
        }
        
    }
    def sendSignupInvitation(String requestBody) {
        try {               
            def requestData = JSON.parse(requestBody)                                    
            ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();                                
            def signupType = ""
            Date date = new Date();
            def time = new Timestamp(date.getTime())      
            def promoCode = Promotion.findAll();
            for(int i = 0; i < requestData.invitationInfo.length(); i++) {                
                def currentRequistItem =  requestData.invitationInfo[i]
                if(currentRequistItem.signupType == "retail") {                    
                    signupType = "retailSignup"    
                } else {
                    if(!promoCode) {
                        throw new Exception("promoCodeMissing")
                    } else {
                        signupType = "trialSignup"       
                    }                                 
                } 
                def invitation = Invitation.findByName(currentRequistItem.userName);
                if(invitation) {                    
                    throw new Exception("duplicateEmail")
                } else {                                    
                    def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
                    def verifyLink = applicationUrl + "/account/invitationsignup" + "?" + "invitationEmail=" + currentRequistItem.email + "&" +"invitationName="+currentRequistItem.userName;                 
                    def newInvitation = new Invitation()
                    newInvitation.email = currentRequistItem.email;                
                    newInvitation.link = verifyLink;
                    newInvitation.status = "in progress";
                    newInvitation.createdDate = time;
                    newInvitation.type = currentRequistItem.signupType;
                    newInvitation.name = currentRequistItem.userName;
                    newInvitation.save(flush:true);
                    if (!newInvitation.save()) {
                        throw new ValidationException(newInvitation.errors.allErrors)                                          
                    }    
                    def orgName = Config.findByName("organisation.name").value;
                    Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                    tempalteMap.put("name" , currentRequistItem.userName)
                    tempalteMap.put("verifyLink" , verifyLink)   
                    notificationService.send(currentRequistItem.email.toString(), "invitation.subject.ftl", tempalteMap, "invitation.ftl") 
                }     
            }
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("result", "OK");
            arrayList.add(item);
            return arrayList
            
        } catch (ValidationException ex) {
            throw ex                
        } catch (LicenseExpiredException ex) {
            throw ex
        } catch (NullPointerException ex){
            throw ex
            Console.print(ex);
        } catch (Exception ex){
            throw ex
        }
    }
    def registerAccount(String requestBody) {
        try {
            
            def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
            
            
            ConfigLoader configLoader = ConfigLoader.getInstance();
           
            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();
            
            Project project;
                                                                       
                       
            Config billingPeriodDays =  Config.findByName(Config.BILLING_PERIOD_DAYS);
            Config signupCardVerificationEnabled =  Config.findByName(Config.SIGNUP_CARD_VERIFICATION_ENABLED);
            
            Config creditCardProcessing =  Config.findByName(Config.CARD_PROCESSING_ENABLED);
    
            Config vmLimitNo =  Config.findByName(Config.INSTANCE_LIMIT);
            Config storageLimitNo =  Config.findByName(Config.STORAGE_LIMIT);
            Config snapLimitNo =  Config.findByName(Config.SNAPSHOT_LIMIT);
            Config bandLimitNo =  Config.findByName(Config.BANDWIDTH_LIMIT);
            //            println("reee"+requestData.accountType)
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
            Date date = new Date();
            def time = new Timestamp(date.getTime())
            def token = requestData.userName + date.toString()
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(token.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            
            Account.withTransaction {
                def account = new Account()
                account.firstName = requestData.firstName
                account.lastName = requestData.lastName
                account.email = requestData.email
                account.accountType = requestData.accountType;  
                account.uuid = "creating";  
                def defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                account.preferredLanguage = defaultLanguage.value
                if(requestData.accountType == "RETAIL") { 
                    Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
                    account.creditLimit = Double.parseDouble(retailCreditLimit.value); 
                    account.promotionalCode = requestData.promotionalCode
                    def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                    if(promotionalCode) {
                        account.credit = promotionalCode.value
                    } else {
                        account.credit = 0
                    }
                } else if(requestData.accountType == "TRIAL") {
                    Config trialCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_CREDIT_LIMIT);
                    account.creditLimit = Double.parseDouble(trialCreditLimit.value);
                    
                    def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                    if(promotionalCode) {
                        account.promotionalCode = requestData.promotionalCode
                        account.credit = promotionalCode.value
                    } else {                        
                        throw new InvalidPromotionalCodeException("invalid promotional code"); 
                    } 
                }
                account.accountIdentifier =  requestData.accountIdentifier
                Calendar vmsnapdate = Calendar.getInstance();
                vmsnapdate.add(Calendar.DATE, -1);
                account.signUpDate = time
                account.vmSnapUsageDate = vmsnapdate.getTime()
                account.streetAddress = requestData.address
                account.streetAddress1 = requestData.address1
                account.city = requestData.city
                account.zip = requestData.zip
                account.state = State.findById(requestData.state)
                account.country = Country.findById(requestData.country)
                account.companyName =requestData.companyName
                account.phoneNumber = requestData.phoneNumber
                account.billingStreetAddress = requestData.address
                account.billingCity = requestData.city
                account.billingZip = requestData.zip
                account.billingState = State.findById(requestData.state)
                account.billingCountry = Country.findById(requestData.country)
                account.billingPhoneNumber = requestData.phoneNumber
                account.totalAmount = 0
                account.totalPayable = 0    
                account.totalPaid = 0
                account.previousPaid = 0
                account.lateFee = false
                account.penaltyFee = 0
                account.previousBalance = 0 
                account.status = "NOT_VERIFIED"
                if(requestData.accountType == "RETAIL") {
                    if(creditCardProcessing.value =="true" && signupCardVerificationEnabled.value == "true") {
                        def card = saveCard("CARD-A"+requestData.email ,requestData.cardNumber, Integer.parseInt(requestData.expiryMonth), requestData.expiryYear, requestData.cardType, requestData.cvv)                           
                        account.cardExpiryMonth = Double.parseDouble(requestData.expiryMonth)
                        account.cardExpiryYear = Double.parseDouble(requestData.expiryYear.toString())
                        account.defaultCard = card.UUID
                        account.cardVerified = true  
                    }
                }
                //save default domain
                def defaultDomain = Domain.findByIsDefault(true)
                account.domain = defaultDomain;
                if (account.validate()) {
                    
                    //create project in OpenStack 
                    project = os.identity().projects().create(Builders.project().domainId(defaultDomain.referenceId).name(account.accountIdentifier).enabled(true).build());
                    //                    //save account 
                    account.uuid = project.getId();
                    
                    // save default quotset 
                    ResourceLimit resourceLimit = new ResourceLimit();
                    ComputeQuotaSet computeQuotaSet = os.compute().quotaSets().get(project.getId());
                    NetQuota novaQuota = os.networking().quotas().get(project.getId());
                    CinderQuotaSet cinderQuotaSet = os.blockStorage().quotaSets().get(project.getId());
                    
                    // setting the default value from openstack to fogpanel
                    resourceLimit.ram = computeQuotaSet.ram
                    resourceLimit.instances = computeQuotaSet.instances
                    resourceLimit.cores = computeQuotaSet.cores
                    resourceLimit.metadataItems = computeQuotaSet.metadataItems
                    resourceLimit.injectedFiles = computeQuotaSet.injectedFiles
                    resourceLimit.injectedFileContentBytes = computeQuotaSet.injectedFileContentBytes
                    resourceLimit.securityGroups = computeQuotaSet.securityGroups
                    resourceLimit.securityGroupRules = computeQuotaSet.securityGroupRules

                    resourceLimit.floatingIp = novaQuota.floatingIP
                    resourceLimit.network = novaQuota.network
                    resourceLimit.port = novaQuota.port
                    resourceLimit.router = novaQuota.router
                    resourceLimit.subnet = novaQuota.subnet

                    resourceLimit.gigabytes = cinderQuotaSet.gigabytes
                    resourceLimit.snapshots = cinderQuotaSet.snapshots
                    resourceLimit.volumes = cinderQuotaSet.volumes

                    resourceLimit.save(flush:true);
                    
                    account.resourceLimit = resourceLimit;
                    
                    account.save(flush:true);
                    if (!account.save()) {
                        throw new ValidationException(account.errors.allErrors);
                    }
                    
                    // setting account to ResourceLimit
                    resourceLimit.account = account;
                    resourceLimit.save(flush:true);
                    
                    if(requestData.accountType == "RETAIL") {
                        def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                        if(promotionalCode) {
                            Credit credit = new Credit();    
                            credit.account = account;
                            credit.date = time;
                            credit.description = "Retail signup credit";
                            credit.amount = promotionalCode.value;
                            credit.relId = "0"
                            credit.save(flush:true, failOnError:true); 
                        }
                    } else if(requestData.accountType == "TRIAL") {
                        Config trialCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_CREDIT_LIMIT);
                        def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                        Credit credit = new Credit();    
                        credit.account = account;
                        credit.date = time;
                        credit.description = "free signup credit";
                        credit.amount = promotionalCode.value; 
                        credit.relId = "0"
                        credit.save(flush:true, failOnError:true); 
                    }
                    Calendar calendar = Calendar.getInstance(); 
                    Config linkActiveTime =  Config.findByName(Config.LINK_ACTIVE_TIME);
                    calendar.add(Calendar.HOUR, Integer.parseInt(linkActiveTime.value));
                    User user = new User();
                    user.account = account;
                    user.token = hashtext.toString();
                    user.username = account.accountIdentifier;  
                    user.failureCount = 0;
                    user.password = requestData.password; 
                    user.enabled = true;
                    user.tokenExpiryDate = calendar.getTime() 
                    user.accountExpired = false;
                    user.accountLocked = false;
                    user.passwordExpired = false;
                    user.firstName = requestData.firstName
                    user.lastName = requestData.lastName
                    user.email = requestData.email
                    
                    // save default domain
                    user.domain = defaultDomain;
                    if (user.validate()) {
                        
                        //create user to OpenStack
                        //def userPassword = springSecurityService.encodePassword(requestData.password);
                                                
                        OpenstackUser openstackUser = os.identity().users().create(Builders.user().tenantId(project.getId())
                            .domainId(defaultDomain.referenceId) 
                            .name(account.accountIdentifier)
                            .password(requestData.password).email(requestData.email).enabled(true).build());
                        
                        def openStackRoleName = "_member_";
                        OpenstackRole currentRole = os.identity().roles().getByName(openStackRoleName); 
                        def roleId = currentRole.getId();
                        
                        //Assign role to current user
                        os.identity().roles().addUserRole(project.getId(), openstackUser.getId(), roleId);
                        
                        user.uuid = openstackUser.getId();
                        user.save(flush:true, failOnError:true);
                        
                        server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), user.uuid, requestData.password, null);
                        OSClient osUser = server.authenticate();  
                        
                        GenerateKeyPair generateKeyPair = new GenerateKeyPair();
                        def generatedPrivateKey = generateKeyPair.getPrivateKey();
                        def generatedPublicKey = generateKeyPair.getPublicKey();
                        
                        Keypair kp = osUser.compute().keypairs().create("defaultkeypair", generatedPublicKey);
                        //                      
                        def sshKeys = new SSHKeys();
                        sshKeys.name = "defaultkeypair";
                        sshKeys.isDefault = true;
                        sshKeys.publicKey = generatedPublicKey;
                        sshKeys.privateKey = generatedPrivateKey;
                        sshKeys.fingerprint = kp.getFingerprint();
                        sshKeys.account = account;
                        sshKeys.user = user;
                        sshKeys.createdAt = new Date();
                        sshKeys.save(flush:true);
                        
                        def role = Role.findByAuthority("ROLE_PAID_USER");
                        UserRole userRole = new UserRole();
                        userRole.user = user;
                        userRole.role = role;
                        userRole.save(flush:true, failOnError:true);                           
                        def verifyLink = applicationUrl + "/account/confirmAccount/" + hashtext.toString()
                         
                        Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                        tempalteMap.put("userMail" , account.accountIdentifier)       
                        tempalteMap.put("firstName" , account.firstName)                               
                        tempalteMap.put("verifyLink" , verifyLink) 
                        
                        //                        def response =
                        //                                csAccService.createAccount("0", account.email, account.firstName,
                        //                                        account.lastName, account.accountIdentifier, cloudPasswordHashtext.toString(), null);

                                    
                        if(requestData.accountType == "RETAIL") { 
                        
                            HashMap<String,String> optional = new HashMap<String,String>();  
                            optional.put("account", new String(account.accountIdentifier));
                            //                            optional.put("domainid", new String(account.domain.referenceId));
                            optional.put("max", "-1");
                            
                            //                            def pStorageLimit = csLimitService.updateResourceLimit("10", optional)
                            //                            def sStorgemLimit = csLimitService.updateResourceLimit("11", optional)
                        } else if(requestData.accountType == "TRIAL") {                                                                                                            
                            HashMap<String,String> optional = new HashMap<String,String>();  
                            optional.put("account", new String(account.accountIdentifier));
                            optional.put("max", "-1");
                                                        
                        }


                        HashMap<String,String> optional = new HashMap<String,String>();
                        optional.put("account", account.accountIdentifier);
                        
                        def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                        if(promotionalCode) {
                            promotionalCode.uses = promotionalCode.uses +1;
                            promotionalCode.save(flush:true, failOnError:true);
                        }                               

                        notificationService.send(requestData.email.toString(), "accountActiviation.subject.ftl", tempalteMap, "accountActiviation.ftl")                                                  

                    } else {
                        throw new ValidationException(user.errors.allErrors);
                    }
                } else {
                    throw new ValidationException(account.errors.allErrors);
                }
                log.info("Account: ${account.id} is signed up")
            }  
        } catch (CardValidationException ex) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            def language = defaultLanguage.value                        
            throw new CardValidationException(messageSource.getMessage('signup.card.validationfailed', null, new Locale(language)));        
        } catch (InvalidPromotionalCodeException ex) {            
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            def language = defaultLanguage.value                        
            throw new InvalidPromotionalCodeException(messageSource.getMessage('signup.promotion.invalid', null, new Locale(language)));        
        } catch (Exception ex) {
            throw ex;            
        }          
    }
    
    
    def customRegistration(String userName, String password) {
        try {
            
            def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
            
            
            ConfigLoader configLoader = ConfigLoader.getInstance();
           
            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();
            
            Project project;
                                                                       
                       
            Config billingPeriodDays =  Config.findByName(Config.BILLING_PERIOD_DAYS);
            Config signupCardVerificationEnabled =  Config.findByName(Config.SIGNUP_CARD_VERIFICATION_ENABLED);
            
            Config creditCardProcessing =  Config.findByName(Config.CARD_PROCESSING_ENABLED);
    
            Config vmLimitNo =  Config.findByName(Config.INSTANCE_LIMIT);
            Config storageLimitNo =  Config.findByName(Config.STORAGE_LIMIT);
            Config snapLimitNo =  Config.findByName(Config.SNAPSHOT_LIMIT);
            Config bandLimitNo =  Config.findByName(Config.BANDWIDTH_LIMIT);
            //            println("reee"+requestData.accountType)
            // convert string to json object
  
            def accountType = "RETAIL";
            Date date = new Date();
            def time = new Timestamp(date.getTime())
            def token = userName + date.toString()
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(token.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            
            Account.withTransaction {
                def account = new Account()
                account.firstName = userName
                account.lastName = userName
                account.email = userName + "@gmail.com"
                account.accountType = "RETAIL";  
                account.uuid = "creating";  
                def defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                account.preferredLanguage = defaultLanguage.value
                if(accountType == "RETAIL") { 
                    Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
                    account.creditLimit = Double.parseDouble(retailCreditLimit.value); 
                    account.credit = 0
                } else if(accountType == "TRIAL") {
                    Config trialCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_CREDIT_LIMIT);
                    account.creditLimit = Double.parseDouble(trialCreditLimit.value);
                    
                    def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                    if(promotionalCode) {
                        account.promotionalCode = requestData.promotionalCode
                        account.credit = promotionalCode.value
                    } else {                        
                        throw new InvalidPromotionalCodeException("invalid promotional code"); 
                    } 
                }
                account.accountIdentifier =  userName
                Calendar vmsnapdate = Calendar.getInstance();
                vmsnapdate.add(Calendar.DATE, -1);
                account.signUpDate = time
                account.vmSnapUsageDate = vmsnapdate.getTime()
                account.streetAddress = "Rkr st."
                account.streetAddress1 = ""
                account.city = "coimbatore"
                account.zip = "641001"
                account.state = State.findById(1312)
                account.country = Country.findById(100)
                account.companyName = "Assistanz"
                account.phoneNumber = "9999900001"
                account.billingStreetAddress = "Rkr st"
                account.billingCity = "coimbatore"
                account.billingZip = "641001"
                account.billingState = State.findById(1312)
                account.billingCountry = Country.findById(100)
                account.billingPhoneNumber = "9999900001"
                account.totalAmount = 0
                account.totalPayable = 0    
                account.totalPaid = 0
                account.previousPaid = 0
                account.lateFee = false
                account.penaltyFee = 0
                account.previousBalance = 0 
                account.status = "ACTIVE"
                if(accountType == "RETAIL") {
                    if(creditCardProcessing.value =="true" && signupCardVerificationEnabled.value == "true") {
//                        def card = saveCard("CARD-A"+requestData.email ,requestData.cardNumber, Integer.parseInt(requestData.expiryMonth), requestData.expiryYear, requestData.cardType, requestData.cvv)                           
//                        account.cardExpiryMonth = Double.parseDouble(requestData.expiryMonth)
//                        account.cardExpiryYear = Double.parseDouble(requestData.expiryYear.toString())
//                        account.defaultCard = card.UUID
//                        account.cardVerified = true  
                    }
                }
                //save default domain
                def defaultDomain = Domain.findByIsDefault(true)
                account.domain = defaultDomain;
                if (account.validate()) {
                    
                    //create project in OpenStack 
                    project = os.identity().projects().create(Builders.project().domainId(defaultDomain.referenceId).name(account.accountIdentifier).enabled(true).build());
                    //                    //save account 
                    account.uuid = project.getId();
                    println("project.getId(): "+project.getId())
                    // save default quotset 
                    ResourceLimit resourceLimit = new ResourceLimit();
                    ComputeQuotaSet computeQuotaSet = os.compute().quotaSets().get(project.getId());
                    NetQuota novaQuota = os.networking().quotas().get(project.getId());
                    CinderQuotaSet cinderQuotaSet = os.blockStorage().quotaSets().get(project.getId());
                    
                    // setting the default value from openstack to fogpanel
                    resourceLimit.ram = computeQuotaSet.ram
                    resourceLimit.instances = computeQuotaSet.instances
                    resourceLimit.cores = computeQuotaSet.cores
                    resourceLimit.metadataItems = computeQuotaSet.metadataItems
                    resourceLimit.injectedFiles = computeQuotaSet.injectedFiles
                    resourceLimit.injectedFileContentBytes = computeQuotaSet.injectedFileContentBytes
                    resourceLimit.securityGroups = computeQuotaSet.securityGroups
                    resourceLimit.securityGroupRules = computeQuotaSet.securityGroupRules

                    resourceLimit.floatingIp = novaQuota.floatingIP
                    resourceLimit.network = novaQuota.network
                    resourceLimit.port = novaQuota.port
                    resourceLimit.router = novaQuota.router
                    resourceLimit.subnet = novaQuota.subnet

                    resourceLimit.gigabytes = cinderQuotaSet.gigabytes
                    resourceLimit.snapshots = cinderQuotaSet.snapshots
                    resourceLimit.volumes = cinderQuotaSet.volumes

                    resourceLimit.save(flush:true);
                    
                    account.resourceLimit = resourceLimit;
                    
                    account.save(flush:true);
                    
                    if (!account.save()) {
                        throw new ValidationException(account.errors.allErrors);
                    }
                    
                    // setting account to ResourceLimit
                    resourceLimit.account = account;
                    resourceLimit.save(flush:true);
                
                    Calendar calendar = Calendar.getInstance(); 
                    Config linkActiveTime =  Config.findByName(Config.LINK_ACTIVE_TIME);
                    calendar.add(Calendar.HOUR, Integer.parseInt(linkActiveTime.value));
                    User user = new User();
                    user.account = account;
                    user.token = hashtext.toString();
                    user.username = account.accountIdentifier;  
                    user.failureCount = 0;
                    user.password = password; 
                    user.enabled = true;
                    user.tokenExpiryDate = calendar.getTime() 
                    user.accountExpired = false;
                    user.accountLocked = false;
                    user.passwordExpired = false;
                    user.firstName = userName
                    user.lastName = userName
                    user.email = userName+"@gmail.com"
                    
                    // save default domain
                    user.domain = defaultDomain;
                    if (user.validate()) {
                        
                        //create user to OpenStack
                        //def userPassword = springSecurityService.encodePassword(requestData.password);
                                                
                        OpenstackUser openstackUser = os.identity().users().create(Builders.user().tenantId(project.getId())
                            .domainId(defaultDomain.referenceId) 
                            .name(account.accountIdentifier)
                            .password(password).email(userName+"@gmail.com").enabled(true).build());
                        
                        println("openstackUser: "+openstackUser)
                        
                        def openStackRoleName = "_member_";
                        OpenstackRole currentRole = os.identity().roles().getByName(openStackRoleName); 
                        def roleId = currentRole.getId();
                        
                        //Assign role to current user
                        os.identity().roles().addUserRole(project.getId(), openstackUser.getId(), roleId);
                        
                        user.uuid = openstackUser.getId();
                        user.save(flush:true, failOnError:true);
                        
                        if (!user.save()) {
                            throw new ValidationException(user.errors.allErrors);
                        }
                        
                        server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), user.uuid, password, null);
                        OSClient osUser = server.authenticate();  
                        
                        GenerateKeyPair generateKeyPair = new GenerateKeyPair();
                        def generatedPrivateKey = generateKeyPair.getPrivateKey();
                        def generatedPublicKey = generateKeyPair.getPublicKey();
                        
                        Keypair kp = osUser.compute().keypairs().create("defaultkeypair", generatedPublicKey);
                        //                      
                        def sshKeys = new SSHKeys();
                        sshKeys.name = "defaultkeypair";
                        sshKeys.isDefault = true;
                        sshKeys.publicKey = generatedPublicKey;
                        sshKeys.privateKey = generatedPrivateKey;
                        sshKeys.fingerprint = kp.getFingerprint();
                        sshKeys.account = account;
                        sshKeys.user = user;
                        sshKeys.createdAt = new Date();
                        sshKeys.save(flush:true);
                        
                        def role = Role.findByAuthority("ROLE_PAID_USER");
                        UserRole userRole = new UserRole();
                        userRole.user = user;
                        userRole.role = role;
                        userRole.save(flush:true, failOnError:true);                           
                        def verifyLink = applicationUrl + "/account/confirmAccount/" + hashtext.toString()
                         
                        Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                        tempalteMap.put("userMail" , account.accountIdentifier)       
                        tempalteMap.put("firstName" , account.firstName)                               
                        tempalteMap.put("verifyLink" , verifyLink) 
                        
                        //                        def response =
                        //                                csAccService.createAccount("0", account.email, account.firstName,
                        //                                        account.lastName, account.accountIdentifier, cloudPasswordHashtext.toString(), null);

                                    
                        if(accountType == "RETAIL") { 
                        
                            HashMap<String,String> optional = new HashMap<String,String>();  
                            optional.put("account", new String(account.accountIdentifier));
                            //                            optional.put("domainid", new String(account.domain.referenceId));
                            optional.put("max", "-1");
                            
                            //                            def pStorageLimit = csLimitService.updateResourceLimit("10", optional)
                            //                            def sStorgemLimit = csLimitService.updateResourceLimit("11", optional)
                        } 

                        HashMap<String,String> optional = new HashMap<String,String>();
                        optional.put("account", account.accountIdentifier);
                                                    

//                        notificationService.send(requestData.email.toString(), "accountActiviation.subject.ftl", tempalteMap, "accountActiviation.ftl")                                                  

                    } else {
                        throw new ValidationException(user.errors.allErrors);
                    }
                } else {
                    throw new ValidationException(account.errors.allErrors);
                }
                log.info("Account: ${account.id} is signed up")
            }  
        } catch (CardValidationException ex) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            def language = defaultLanguage.value                        
            throw new CardValidationException(messageSource.getMessage('signup.card.validationfailed', null, new Locale(language)));        
        } catch (InvalidPromotionalCodeException ex) {            
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            def language = defaultLanguage.value                        
            throw new InvalidPromotionalCodeException(messageSource.getMessage('signup.promotion.invalid', null, new Locale(language)));        
        } catch (Exception ex) {
            throw ex;            
        }          
    }
    
    def saveCard(uuid ,cardNumber, expiryMonth, expiryYear, cardType, cvv) { 
        try {
            def card =  paymentService.saveCard(uuid , cardNumber, expiryMonth, expiryYear, cardType, cvv)
            return card
        } catch (Exception ex) {
            throw ex;
            
            Console.print(ex);
            
            throw new CardValidationException("CARD_VERIFICATION_FAILED");
        }
    } 
    
    def count() {
        try {  
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()    
            def totalAccount = 0;
            def retailAccount = 0;
            def trialAccount = 0;                
            if(role.iterator().next().toString().equals( "ROLE_ADMIN") )  {   

                totalAccount = Account.withCriteria {               
                    ne("accountType", AccountType.values()[2])                        
                }     
                retailAccount = Account.findAllWhere(accountType: AccountType.values()[1]);    
                trialAccount =  Account.findAllWhere(accountType: AccountType.values()[0]);            
                ArrayList<ArrayList<String>> accountList = new ArrayList<ArrayList<String>>();                
                HashMap<String,String> account = new HashMap<String,String>();
                account.put("totalAccount", totalAccount.size());
                account.put("retailAccount", retailAccount.size());
                account.put("trialAccount", trialAccount.size());   
                accountList.add(account);                 
                return accountList
            }           
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
    
    def list(String email, String userName) {
        try {  
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            
            if((email == "null" || email == null) && (userName == "null" || userName == null)) {                      
                
                def user = springSecurityService.currentUser
                def role = springSecurityService.getPrincipal().getAuthorities()    
                if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
                    def result = Account.findAll();                       
                    ArrayList<ArrayList<String>> accountList = new ArrayList<ArrayList<String>>();            
                    for(int i=0; i < result.size(); i++) {
                        def item = result[i]; 
                        if(item.accountIdentifier != user.account.accountIdentifier) {
                            HashMap account = new HashMap();
                            account.put("id", item.id);
                            account.put("uuid", item.uuid);
                            account.put("userName", item.accountIdentifier);
                            account.put("lastName", item.lastName);
                            account.put("firstName", item.firstName);
                            account.put("domainName", item.domain?.name);
                            account.put("city", item.city);
                            account.put("streetAddress", item.streetAddress);
                            account.put("streetAddress2", item.streetAddress1);
                            account.put("state", item.state.stateName);
                            account.put("zip", item.zip);
                            account.put("country", item.country.countryName);
                            account.put("billingCountry", item.billingCountry.countryName);
                            account.put("billingCity", item.billingCity);
                            account.put("billingAddress", item.billingStreetAddress);
                            account.put("companyName", item.companyName);
                            account.put("billingState", item.billingState.stateName);
                            account.put("billingZip", item.billingZip);
                            account.put("phoneNumber", item.phoneNumber);
                            account.put("billingPhoneNumber", item.billingPhoneNumber);
                            account.put("email", item.email);
                            account.put("cardVerified", item.cardVerified);
                            account.put("accountType", item.accountType);
                            account.put("status", item.status); 
                            account.put("totalPaid", item.totalPaid);
                            account.put("totalPayable", item.totalPayable);
                            account.put("creditLimit", item.creditLimit);
                            account.put("currency", currency);  
                            account.put("userCount", User.countByAccount(item));  
                                                     
                            def invoice = Invoice.findWhere(account: item, status: InvoiceStatus.values()[6])
                            if(invoice) {
                                account.put("invoice", "yes"); 
                            } else {
                                account.put("invoice", "no"); 
                            }
                            def lockuser = User.findByUsername(item.accountIdentifier)
                            if(lockuser) {
                                account.put("locked", lockuser.accountLocked); 
                            } else {
                                account.put("locked", false); 
                            }
                            
                            accountList.add(account);
                        }                        
                        
                    }
                    return accountList
                }
            } else if((email != "null" || email != null) && (userName == "null" || userName == null)) {                
                //def result = Account.findByEmail(email);
                def emailPatern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
                
                Pattern pattern = Pattern.compile(emailPatern)
                Matcher matchContent = pattern.matcher(email);              
                def matchResult = matchContent.matches();
                
                //                if(result) {
                //                    return [GeneralConstants.RESULT_FAILURE]
                //                } else 
                if(matchResult == false || matchResult == "false") {
                    return [GeneralConstants.RESULT_FALSE]
                } else if (matchResult == true || matchResult == "true") {
                    return [GeneralConstants.RESULT_TRUE]
                }
            } else if((email == "null" || email == null) && (userName != "null" || userName != null)) {
                
                def defaultDomain = Domain.findByIsDefault(true);
                def result = Account.findWhere(accountIdentifier: userName, domain: defaultDomain);
                def validationStatus = userName.matches("[a-zA-Z0-9-@. ]*");
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                ArrayList<ArrayList<String>> userExists = new ArrayList<ArrayList<String>>();  
                HashMap<String,String> item = new HashMap<String,String>();
                Object[] domainArgs = new Object[2];
                domainArgs[0] = defaultDomain.name
                
                if(result) {
                    item.put("result", GeneralConstants.RESULT_FAILURE);
                    item.put("msg", messageSource.getMessage('common.username.alreadyexists.in.domain', domainArgs, new Locale(defaultLanguage.value)));
                    userExists.add(item);
                    return userExists
                } else if(validationStatus == false || validationStatus == "false" || userName.size() < 5) {
                    item.put("result", GeneralConstants.RESULT_FALSE);
                    userExists.add(item);
                    return userExists
                } else if(validationStatus == true || validationStatus == "true" && userName.size() >= 5) {
                    item.put("result", GeneralConstants.RESULT_TRUE);
                    userExists.add(item);
                    return userExists
                }
            }
            
        } catch (Exception ex) {
            throw ex;
        }         
    }
    
    def getAccountFromId(id) {
        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        
        def account = Account.get(id);
        ArrayList<ArrayList<String>> accountInfo = new ArrayList<ArrayList<String>>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        HashMap accountItem = new HashMap();
        accountItem.put("id", account.id);
        accountItem.put("userName", account.accountIdentifier);
        accountItem.put("accountIdentifier", account.accountIdentifier);
        accountItem.put("activationDate", account.activationDate);
        accountItem.put("signUpDate", account.signUpDate);
        accountItem.put("status", "active");
        accountItem.put("city", account.city);
        accountItem.put("streetAddress", account.streetAddress);
        accountItem.put("streetAddress2", account.streetAddress1);
        accountItem.put("state", account.state.stateName);
        accountItem.put("zip", account.zip);
        accountItem.put("country", account.country.countryName);
        accountItem.put("countryId", account.country.id);
        accountItem.put("stateId", account.state.id);
        accountItem.put("lastName", account.lastName);
        accountItem.put("email", account.email); 
        accountItem.put("firstName", account.firstName);
        accountItem.put("companyName", account.companyName);
        accountItem.put("billingCountry", account.billingCountry.countryName);
        accountItem.put("billingCity", account.billingCity);
        accountItem.put("billingAddress", account.billingStreetAddress);
        accountItem.put("billingState", account.billingState.stateName);
        accountItem.put("billingZip", account.billingZip);
        accountItem.put("phoneNumber", account.phoneNumber);
        accountItem.put("billingPhoneNumber", account.billingPhoneNumber);
        accountItem.put("credit", account.credit);
        accountItem.put("accountType", account.accountType);   
        accountItem.put("status", account.status); 
        accountItem.put("domainName", account.domain?.name);
        if(account.nextBillingData == null || account.nextBillingData == "null" || account.nextBillingData == "") {
            accountItem.put("nextBillingData", "-"); 
        } else {
            accountItem.put("nextBillingData", dateFormat.format(account.nextBillingData).toString()); 
        }
        accountItem.put("signUpDate", dateFormat.format(account.signUpDate).toString());
        accountItem.put("totalPaid", account.totalPaid);
        accountItem.put("totalPayable", account.totalPayable);
        accountItem.put("creditLimit", account.creditLimit);
        accountItem.put("cardVerified", account.cardVerified);
        accountItem.put("currency", currency); 
                
        
        def refundCriteria = Refund.createCriteria()
        def ref = refundCriteria.get {
            and{
                eq("account", account)  
            }                              
            projections {
                sum("amount")
            } 
        }
                
        if(ref == "null" || ref == null) {
            accountItem.put("refundAmount", 0);
        } else {
            accountItem.put("refundAmount", ref); 
        } 
                
        def payCountCriteria = Payments.createCriteria()
        def payCount = payCountCriteria.get {
            and{
                eq("account", account)  
            }  
            and {
                eq("paymentStatus", GeneralConstants.PAYMENT_RESULT_SUCCESS)
            }
            projections {
                count("id")
            } 
        }
        accountItem.put("payCount", payCount); 
        
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.add(Calendar.DATE, -1);   
        startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        startDateCalendar.set(Calendar.MINUTE, 00);
        startDateCalendar.set(Calendar.SECOND, 00);
        startDateCalendar.set(Calendar.MILLISECOND, 00);   
                
        Calendar toDateCalendar = Calendar.getInstance();
        toDateCalendar.add(Calendar.DATE, -1);
        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        toDateCalendar.set(Calendar.MINUTE, 59);
        toDateCalendar.set(Calendar.SECOND, 59);
        toDateCalendar.set(Calendar.MILLISECOND, 999);          
            
        Date fromDate = startDateCalendar.getTime()
        Date toDate = toDateCalendar.getTime()
                
        def daily = null;
        //                def offeringUsageCriteria = OfferingUsage.createCriteria()
        //                daily = offeringUsageCriteria.get {
        //                    eq("usageType", UsageType.values()[1])
        //                    and { ge("startDate", fromDate) and { le("startDate", toDate) } 
        //                        and {
        //                            eq("account", account)
        //                        }
        //                    }
        //                    projections {
        //                        sum("cost")
        //                    } 
        //                }
                                            
        if(daily == "null" ||daily == null) {
            accountItem.put("daily", 0);
        } else {
            accountItem.put("daily",  Math.ceil(daily * 100d) / 100d);  
        } 
                
        def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                
        def currentVmList = InvoiceItem.findAllWhere(invoice: invoice, billableItem:BillableItem.get(1))
        def totalBandwidth = 0;
        def totalBandwidthCost = 0;
        def runningVmCost = 0;
        def stopVmCost = 0;
        def totalDiskCost = 0;
        def totalSnapCost = 0;
            
        def bandwidthCostCriteria = InvoiceItem.createCriteria()
        def bandCost = bandwidthCostCriteria.list {
            eq("invoice", invoice)
            and {
                eq("billableItem", BillableItem.get(5))
            }
        }
        for(def bandItem: bandCost) {
            totalBandwidth += bandItem.usageUnits
            totalBandwidthCost += bandItem.totalAmount
        }
                
        def diskCostCriteria = InvoiceItem.createCriteria()
        def diskCost = diskCostCriteria.list {
            eq("invoice", invoice)
            and {
                eq("billableItem", BillableItem.get(2))
            }
            projections {
                sum("totalAmount")
            }
        }
                
        if(diskCost[0] == null || diskCost[0] == "null") {
            totalDiskCost =  0
        } else {
            totalDiskCost =  diskCost[0]
        }  
                                
        def snapCriteria = InvoiceItem.createCriteria()
        def snapCost = snapCriteria.list {
            eq("invoice", invoice)
            and {
                eq("billableItem", BillableItem.get(3))
            }
            projections {
                sum("totalAmount")
            }
        }
                
                
        if(snapCost[0] == null || snapCost[0] == "null") {
            totalSnapCost =  0
        } else {
            totalSnapCost =  snapCost[0]
        }
        Config minHour =  Config.findByName(Config.INSTANCE_MINIMUM_COST_APPLICABLE_HOUR);
        HashMap<String,String> planList = new HashMap<String,String>(); 
        //                 double readGB = virtualMachine.networkRead / 1048576
        //                for(def currentVm: currentVmList) {
        //                    
        //                    def computingOffer = ComputingOffer.findByOfferReferenceId(currentVm.referencePlanId)
        //                    def virtualMachine = VirtualMachine.findWhere(referenceId: currentVm.referenceItemId, computingOffer: computingOffer);
        //                    
        //                    if(virtualMachine) {
        //                        boolean blnExists = planList.containsKey(currentVm.referenceItemId);
        //                        if(blnExists.toString() == "false" || blnExists == false) {
        //                            if(virtualMachine.zone.networkType == "Basic") {
        //                                double planBandwidth = 1.0 * virtualMachine.computingOffer.bandWidth
        //
        //                                def bandWidthCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone :virtualMachine.computingOffer.zone);
        //                                def bandWidthCostValue = bandWidthCost ? bandWidthCost.cost : 0
        //                                
        //                                def usageUnits = (virtualMachine.networkWrite / 1048576) - planBandwidth
        //                                def vmband = virtualMachine.networkWrite / 1048576
        //                                if(vmband > planBandwidth) {
        //                                    double usageAmount = usageUnits * bandWidthCostValue                          
        //                                    totalBandwidthCost = totalBandwidthCost + usageAmount
        //                                    totalBandwidth = totalBandwidth + usageUnits
        //                                }
        //                            }
        //                        }
        //                        planList.put(currentVm.referenceItemId ,"referenceId");
        //                    }
        //                    
        //                    def oldComputeroffer = ComputingOffer.findByOfferReferenceId(currentVm.referencePlanId)
        //                
        //                    def stopInstanceUsageCriteria = null;     
        ////                        stopInstanceUsageCriteria = OfferingUsage.createCriteria()
        ////                        def stopInstanceHours = stopInstanceUsageCriteria.get {
        ////                            eq("usageType", UsageType.values()[2])
        ////                            and {
        ////                              eq("offeringId", currentVm.referencePlanId)  and { eq("virtualMachineId", currentVm.referenceItemId) }
        ////                              and { ge("startDate", invoice.previousInvoiceDate) and { le("startDate", invoice.date) } }
        ////                            }
        ////                            projections {
        ////                                sum("hours")
        ////                            }
        ////                        }
        //                        if(stopInstanceHours == null || stopInstanceHours == "null") {
        //
        //                        } else {
        //                            double hours = stopInstanceHours - currentVm.usageUnits;
        //                            if(hours >= Double.parseDouble(minHour.value)) {
        //                                def mincost = ComputingOfferZoneCost.findWhere(computingOffer: oldComputeroffer).minimumCost 
        //                                stopVmCost = stopVmCost + (hours * mincost)
        //                            }
        //                        }
        //                
        //                        runningVmCost = runningVmCost + currentVm.totalAmount
        //                }
                
        accountItem.put("totalBandwidth", Math.round(totalBandwidth*100)/100);
        accountItem.put("runningVmCost", Math.ceil(runningVmCost * 100d) / 100d);
        accountItem.put("totalBandwidthCost",  Math.ceil(totalBandwidthCost * 100d) / 100d);
        accountItem.put("totalDiskCost", Math.ceil(totalDiskCost * 100d) / 100d);
        accountItem.put("totalSnapCost", Math.ceil(totalSnapCost * 100d) / 100d);
        accountItem.put("stopVmCost", Math.ceil(stopVmCost * 100d) / 100d);
                
        if(invoice) {
            accountItem.put("invoice", "yes");
            accountItem.put("nextBillingData", dateFormat.format(account.nextBillingData).toString()); 
            accountItem.put("invoiceId", invoice.id);
            accountItem.put("currentDue", invoice.currentDue);
            accountItem.put("totalAmount", invoice.totalAmount);
            accountItem.put("previousBalance", invoice.previousBalance);
            accountItem.put("payments", invoice.payment);
            accountItem.put("credit", account.credit);
            accountItem.put("dailyDate", dateFormat.format(fromDate).toString());
            accountItem.put("paymentPeriod", dateFormat.format(invoice.previousInvoiceDate).toString() + " - " + dateFormat.format(invoice.date).toString());
        } else {
            accountItem.put("nextBillingData", "-"); 
            accountItem.put("invoice", "no");
            accountItem.put("invoiceId", "-");
            accountItem.put("currentDue", 0);
            accountItem.put("totalAmount", 0);
            accountItem.put("previousBalance", 0);
            accountItem.put("payments", 0);
            accountItem.put("credit", account.credit);
            accountItem.put("paymentPeriod", "-");
            accountItem.put("dailyDate", "-");
        }
        
        if(account.totalPayable < 0) {
            accountItem.put("refund", "yes");   
        } else {
            accountItem.put("refund", "no");    
        }
        accountInfo.add(accountItem)
        return accountInfo;             
    }
    
    def getCurrentAccount() {
        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        try {
            def result = springSecurityService.currentUser 
            def caccount = result.account
            
            def userDateFormat = ""
            if(result.dateFormat) {
                userDateFormat = result.dateFormat
            } else {
                Config dateFormat =  Config.findByName(Config.DATE_FORMAT);
                userDateFormat = dateFormat.value
            }            
            SimpleDateFormat dateFormat = new SimpleDateFormat(userDateFormat);
            //            def result = Account.findByAccountIdentifier(name);            
            ArrayList<ArrayList<String>> account = new ArrayList<ArrayList<String>>();
            HashMap accountItem = new HashMap();
            accountItem.put("dateFormat", result.dateFormat);               
            accountItem.put("id", result.account.id);
            accountItem.put("dateFormat", result.dateFormat);         
            accountItem.put("accountName", result.account.accountIdentifier);
            accountItem.put("userName", result.username);
            accountItem.put("domainName", result.account.domain?.name);
            accountItem.put("activationDate", result.account.activationDate);
            accountItem.put("signUpDate", dateFormat.format(result.account.signUpDate).toString());
            accountItem.put("status", "active");
            accountItem.put("city", result.account.city);
            accountItem.put("streetAddress", result.account.streetAddress);
            accountItem.put("streetAddress2", result.account.streetAddress1);
            accountItem.put("state", result.account.state.stateName);
            accountItem.put("zip", result.account.zip);
            accountItem.put("country", result.account.country.countryName);
            accountItem.put("countryId", result.account.country.id);
            accountItem.put("countryCallingCode", result.account.country.callingCode);
            accountItem.put("singleSignOnUrl", ApplicationHolder.getApplication().config.openstack.singleSignOnUrl);
            accountItem.put("cloudPassword", "");
                
            accountItem.put("stateId", result.account.state.id);
            accountItem.put("lastName", result.account.lastName);
            accountItem.put("email", result.account.email); 
            accountItem.put("firstName", result.account.firstName);
            accountItem.put("companyName", result.account.companyName);
            accountItem.put("billingCountry", result.account.billingCountry.countryName);
            accountItem.put("billingCountryId", result.account.billingCountry.id);
            accountItem.put("billingCountryCallingCode", result.account.billingCountry.callingCode);
                
            accountItem.put("billingCity", result.account.billingCity);
            accountItem.put("billingAddress", result.account.billingStreetAddress);
            accountItem.put("billingAddress2", result.account.billingStreetAddress1);
                
            accountItem.put("billingState", result.account.billingState.stateName);
            accountItem.put("billingStateId", result.account.billingState.id);
            accountItem.put("accountType", result.account.accountType.name());
               
            def cloudHealth = getCloudHealth(result);
                
            accountItem.put("cloudHealth", cloudHealth);
            accountItem.put("billingZip", result.account.billingZip);
            accountItem.put("phoneNumber", result.account.phoneNumber);
            accountItem.put("billingPhoneNumber", result.account.billingPhoneNumber);
            accountItem.put("cardVerified", result.account.cardVerified);
            accountItem.put("currency", currency);
               
                
            Calendar startDateCalendar = Calendar.getInstance();
            startDateCalendar.add(Calendar.DATE, -1); 
            startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
            startDateCalendar.set(Calendar.MINUTE, 00);
            startDateCalendar.set(Calendar.SECOND, 00);
            startDateCalendar.set(Calendar.MILLISECOND, 00);   
                
            Calendar toDateCalendar = Calendar.getInstance();
            toDateCalendar.add(Calendar.DATE, -1);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);          
            
            Date fromDate = startDateCalendar.getTime()
            Date toDate = toDateCalendar.getTime()
                
            def daily = null;
            //                def offeringUsageCriteria = OfferingUsage.createCriteria()
            //                daily = offeringUsageCriteria.get {
            //                    and { ge("startDate", fromDate) and { le("startDate", toDate) } 
            //                        and {
            //                            eq("account", result.account)
            //                        }
            //                    }
            //                    projections {
            //                        sum("cost")
            //                    } 
            //                }
                                            
            if(daily == "null" ||daily == null) {
                accountItem.put("daily", 0);
            } else {
                accountItem.put("daily",  Math.ceil(daily * 100d) / 100d);  
            } 
                
            def invoice = Invoice.findWhere(account: result.account, status: InvoiceStatus.values()[6])
                                
            def currentVmList = InvoiceItem.findAllWhere(invoice: invoice, billableItem:BillableItem.get(1))
            def totalBandwidth = 0;
            def totalBandwidthCost = 0;
            def runningVmCost = 0;
            def stopVmCost = 0;
            def totalDiskCost = 0;
            def totalSnapCost = 0;
            
            def bandwidthCostCriteria = InvoiceItem.createCriteria()
            def bandCost = bandwidthCostCriteria.list {
                eq("invoice", invoice)
                and {
                    eq("billableItem", BillableItem.get(5))
                }
            }
            for(def bandItem: bandCost) {
                totalBandwidth += bandItem.usageUnits
                totalBandwidthCost += bandItem.totalAmount
            }
                
            def diskCostCriteria = InvoiceItem.createCriteria()
            def diskCost = diskCostCriteria.list {
                eq("invoice", invoice)
                and {
                    eq("billableItem", BillableItem.get(2))
                }
                projections {
                    sum("totalAmount")
                }
            }
                
            if(diskCost[0] == null || diskCost[0] == "null") {
                totalDiskCost =  0
            } else {
                totalDiskCost =  diskCost[0]
            }
                                
            def snapCriteria = InvoiceItem.createCriteria()
            def snapCost = snapCriteria.list {
                eq("invoice", invoice)
                and {
                    eq("billableItem", BillableItem.get(3))
                }
                projections {
                    sum("totalAmount")
                }
            }
                
                
            if(snapCost[0] == null || snapCost[0] == "null") {
                totalSnapCost =  0
            } else {
                totalSnapCost =  snapCost[0]
            }
            Config minHour =  Config.findByName(Config.INSTANCE_MINIMUM_COST_APPLICABLE_HOUR);
            HashMap<String,String> planList = new HashMap<String,String>(); 
            //                 double readGB = virtualMachine.networkRead / 1048576
            //                for(def currentVm: currentVmList) {
            //                    
            //                    def computingOffer = ComputingOffer.findByOfferReferenceId(currentVm.referencePlanId)
            //                    def virtualMachine = VirtualMachine.findWhere(referenceId: currentVm.referenceItemId, computingOffer: computingOffer);
            //                    
            //                    if(virtualMachine) {
            //                        boolean blnExists = planList.containsKey(currentVm.referenceItemId);
            //                        if(blnExists.toString() == "false" || blnExists == false) {
            //                            
            //                            if(virtualMachine.zone.networkType == "Basic") {
            //                                double planBandwidth = 1.0 * virtualMachine.computingOffer.bandWidth
            //
            //                                def bandWidthCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone :virtualMachine.computingOffer.zone);
            //                                def bandWidthCostValue = bandWidthCost ? bandWidthCost.cost : 0
            //                                                            
            //                                def usageUnits = (virtualMachine.networkWrite / 1048576) - planBandwidth
            //                                def vmband = virtualMachine.networkWrite / 1048576
            //                                if(vmband > planBandwidth) {
            //                                    double usageAmount = usageUnits * bandWidthCostValue                          
            //                                    totalBandwidthCost = totalBandwidthCost + usageAmount
            //                                    totalBandwidth = totalBandwidth + usageUnits
            //                                }
            //                            }
            //                        }
            //                        planList.put(currentVm.referenceItemId ,"referenceId");
            //                    }
            //                    
            //                    def oldComputeroffer = ComputingOffer.findByOfferReferenceId(currentVm.referencePlanId)
            //                
            //                    def stopInstanceUsageCriteria = null;
            ////                        stopInstanceUsageCriteria = OfferingUsage.createCriteria()
            ////                        def stopInstanceHours = stopInstanceUsageCriteria.get {
            ////                            eq("usageType", UsageType.values()[2])
            ////                            and {
            ////                              eq("offeringId", currentVm.referencePlanId)  and { eq("virtualMachineId", currentVm.referenceItemId) }
            ////                              and { ge("startDate", invoice.previousInvoiceDate) and { le("startDate", invoice.date) } }
            ////                            }
            ////                            projections {
            ////                                sum("hours")
            ////                            }
            ////                        }
            //                        if(stopInstanceHours == null || stopInstanceHours == "null") {
            //
            //                        } else {
            //                            double hours = stopInstanceHours - currentVm.usageUnits;
            //                            if(hours >= Double.parseDouble(minHour.value)) {
            //                                def mincost = ComputingOfferZoneCost.findWhere(computingOffer: oldComputeroffer).minimumCost 
            //                                stopVmCost = stopVmCost + (hours * mincost)
            //                            }
            //                        }
            //                
            //                        runningVmCost = runningVmCost + currentVm.totalAmount
            //                }
                
            accountItem.put("totalBandwidth", Math.round(totalBandwidth*100)/100);
            accountItem.put("runningVmCost", Math.ceil(runningVmCost * 100d) / 100d);
            accountItem.put("totalBandwidthCost",  Math.ceil(totalBandwidthCost * 100d) / 100d);
            accountItem.put("totalDiskCost", Math.ceil(totalDiskCost * 100d) / 100d);
            accountItem.put("totalSnapCost", Math.ceil(totalSnapCost * 100d) / 100d);
            accountItem.put("stopVmCost", Math.ceil(stopVmCost * 100d) / 100d);
                
            // add credit limit 0 condition 
                
            accountItem.put("maxCreditLimit", result.account.creditLimit);
            if(result.account.creditLimit == 0) {
                def creditLimit = result.account.totalPayable / result.account.creditLimit
                accountItem.put("creditLimit", 0);
            } else {
                def creditLimit = result.account.totalPayable / result.account.creditLimit
                accountItem.put("creditLimit", Math.ceil(creditLimit * 100));
            }
                
            if(invoice) {
                accountItem.put("invoice", "yes");
                accountItem.put("nextBillingData", dateFormat.format(result.account.nextBillingData).toString()); 
                accountItem.put("invoiceId", invoice.id);
                accountItem.put("currentDue", invoice.currentDue);
                accountItem.put("totalAmount", invoice.totalAmount);
                accountItem.put("previousBalance", invoice.previousBalance);
                accountItem.put("payments", invoice.payment);
                accountItem.put("credit", result.account.credit);
                accountItem.put("dailyDate", dateFormat.format(fromDate).toString());
                accountItem.put("paymentPeriod", dateFormat.format(invoice.previousInvoiceDate).toString() + " - " + dateFormat.format(invoice.date).toString());
            } else {
                    
                def invoiceCriteria = Invoice.createCriteria()
                def invoiceList = invoiceCriteria.list {
                    eq("account", result.account) 
                    order("id", "desc")
                }  
                def finalInvoice = invoiceList[0]
                
                if(result.account.status.name() == "CANCELED") {
                    accountItem.put("invoice", "yes");
                    accountItem.put("nextBillingData", ""); 
                    accountItem.put("invoiceId", finalInvoice.id);
                    accountItem.put("currentDue", finalInvoice.currentDue);
                    accountItem.put("totalAmount", finalInvoice.totalAmount);
                    accountItem.put("previousBalance", finalInvoice.previousBalance);
                    accountItem.put("payments", finalInvoice.payment);
                    accountItem.put("credit", result.account.credit);
                    accountItem.put("dailyDate", "");
                    accountItem.put("paymentPeriod", dateFormat.format(finalInvoice.previousInvoiceDate).toString() + " - " + dateFormat.format(finalInvoice.date).toString());
                } else {
                
                    accountItem.put("nextBillingData", ""); 
                    accountItem.put("invoice", "no");
                    accountItem.put("invoiceId", "-");
                    accountItem.put("currentDue", 0);
                    accountItem.put("totalAmount", 0);
                    accountItem.put("previousBalance", 0);
                    accountItem.put("payments", 0);
                    accountItem.put("credit", result.account.credit);
                    accountItem.put("paymentPeriod", "");
                    accountItem.put("dailyDate", "");
                }
            }
            account.add(accountItem)
            return account;                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    
    def getCloudHealth(user) {
        
        Calendar fromDateCalendar = Calendar.getInstance(); 
        fromDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        fromDateCalendar.set(Calendar.MINUTE, 00);
        fromDateCalendar.set(Calendar.SECOND, 00);
        fromDateCalendar.set(Calendar.MILLISECOND, 000);                   
        Date fromDate = fromDateCalendar.getTime()

        Calendar toDateCalendar = Calendar.getInstance(); 
        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        toDateCalendar.set(Calendar.MINUTE, 59);
        toDateCalendar.set(Calendar.SECOND, 59);
        toDateCalendar.set(Calendar.MILLISECOND, 999);                   
        Date toDate = toDateCalendar.getTime()
        
     
        def cloudMaintenanceCriteria = CloudMaintenance.createCriteria()
        def cloudMaintenance = cloudMaintenanceCriteria.list {
            ge("date", fromDate)
            and { 
                le("date", toDate) 
                and {
                    eq("deleted", false) 
                }
            } 
        }
        if(!cloudMaintenance) {
            return "false"
        }
        //        def vmList = VirtualMachine.findAllWhere(owner: user.account, deleted:false)
        //        
        //        if(vmList.size() == 0) {
        //            return "no vm";
        //        }
        //        for(def maintenceDate: cloudMaintenance) {
        //            for(def vm: vmList) {
        //                for(Iterator k = maintenceDate.zones.iterator();k.hasNext();) {
        //                    def zone = k.next();
        //                    if(vm.zone.referenceZoneId == zone.referenceZoneId) {
        //                        return "true";
        //                    }     
        //                }
        //            }
        //        }
        return "false";
        
    }
    
    def resendconfirmMail(requestBody) {
        ArrayList<ArrayList<String>> forgotPassword = new ArrayList<ArrayList<String>>();  
        HashMap<String,String> item = new HashMap<String,String>(); 
        try {
            def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
            def requestData = JSON.parse(requestBody);
            Date date = new Date();                            
            def time = new Timestamp(date.getTime())
            def token = requestData.email + date.toString()
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(token.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }  
                        
            def user = User.findByUsername(requestData.email)
            user.token = hashtext.toString();
            Calendar calendar = Calendar.getInstance(); 
            Config linkActiveTime =  Config.findByName(Config.LINK_ACTIVE_TIME);
            calendar.add(Calendar.HOUR, Integer.parseInt(linkActiveTime.value));
            user.tokenExpiryDate = calendar.getTime() 
            user.save(flush:true); 
            
            def verifyLink = applicationUrl + "/account/confirmAccount/" + hashtext.toString()
            Map tempalteMap = notificationService.getDefaultMailTemplateMap()
            tempalteMap.put("userName" , user.account.firstName)
            tempalteMap.put("verifyLink" , verifyLink)

            notificationService.send(user.account.email.toString(), "verificationEmail.subject.ftl", tempalteMap, "verificationEmail.ftl")
            item.put("Result",  GeneralConstants.RESULT_SUCCESS);
            forgotPassword.add(item);
            return forgotPassword
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    def updateMailTemplate(String requestBody) {
        try {     
            def requestData = JSON.parse(requestBody);
            def mailTemplate =  MailTemplate.get(requestData.id);    
            ArrayList<ArrayList<String>> mailTemplateList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            mailTemplate.title = requestData.title;
            mailTemplate.content = requestData.content;
            mailTemplate.subject = requestData.subject;
            mailTemplate.hasHeader = requestData.hasHeader;
            mailTemplate.hasFooter = requestData.hasFooter;
            //            mailTemplate.hasSignature = requestData.hasSignature;
            
            mailTemplate.save(flush: true);
            if (mailTemplate.hasErrors()) {
                throw new ValidationException(mailTemplate.errors.allErrors);
            }  
            item.put("Result",  GeneralConstants.RESULT_SUCCESS);
            item.put("title",  mailTemplate.title);
            
            mailTemplateList.add(item); 
            return mailTemplateList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
        
    }  
    def forgotPasswordMail(String requestBody) {
        try {          
            def requestData = JSON.parse(requestBody);
            def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
            
            def defaultDomain = Domain.findByIsDefault(true);
            User user = User.findWhere(username: requestData.email, domain: defaultDomain);
            def language = "";
            if(user) {
                if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
                    Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                    language = defaultLanguage.value
                } else {
                    language = user.account.preferredLanguage
                }
            } else {
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                language = defaultLanguage.value
            }            
            
            ArrayList<ArrayList<String>> forgotPassword = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            if(user) {
                if(user.account.status.name() == "ACTIVE") {
                    Date date = new Date();                            
                    def time = new Timestamp(date.getTime())
                    def token = requestData.email + date.toString()
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] messageDigest = md.digest(token.getBytes());
                    BigInteger number = new BigInteger(1, messageDigest);
                    String hashtext = number.toString(16);
                    // Now we need to zero pad it if you actually want the full 32 chars.
                    while (hashtext.length() < 32) {
                        hashtext = "0" + hashtext;
                    }                
                    user.token = hashtext.toString();
                    Calendar calendar = Calendar.getInstance(); 
                    Config linkActiveTime =  Config.findByName(Config.LINK_ACTIVE_TIME);
                    calendar.add(Calendar.HOUR, Integer.parseInt(linkActiveTime.value));
                    user.tokenExpiryDate = calendar.getTime() 
                    user.save(flush:true); 
                    
                    def verifyLink = applicationUrl + "/account/resetPassword/" + hashtext.toString()
                    Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                    tempalteMap.put("userName" , user.account.firstName)
                    tempalteMap.put("verifyLink" , verifyLink)
                              
                    notificationService.send(user.account.email.toString(), "forgotPassword.subject.ftl", tempalteMap, "forgotPassword.ftl")
                    item.put("Result",  messageSource.getMessage('common.ok', null, new Locale(language)));
                    forgotPassword.add(item);                    
                } else {
                    item.put("Result",  messageSource.getMessage('common.account.resetPassword.error', null, new Locale(language)));
                    forgotPassword.add(item);
                }                
            } else {
                item.put("Result", messageSource.getMessage('common.account.resetPassword.noUser', null, new Locale(language)));
                forgotPassword.add(item);
            }
            return forgotPassword;
            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def sendSettingsInfoMail(String requestBody) {
        try {        
            def requestData = JSON.parse(requestBody);
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
            def templateName = ""
            def subject = ""
            
            ArrayList<ArrayList<String>> sendMailResultList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            if(user) {  
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()                                
                if(requestData.type == "accountSettingGeneralInfoUpdate") {                    
                    tempalteMap.put("firstName",  requestData.firstName)
                    tempalteMap.put("lastName", requestData.lastName)
                    tempalteMap.put("address1", requestData.streetAddress)
                    tempalteMap.put("address2",requestData.streetAddress1)
                    tempalteMap.put("city", requestData.city)
                    tempalteMap.put("country", requestData.country)
                    tempalteMap.put("stat", requestData.state)
                    tempalteMap.put("phone", requestData.phoneNumber)
                    tempalteMap.put("zip", requestData.zip)     
                    tempalteMap.put("userName", user.username)     
                    templateName = "accountSettingGeneralInfoUpdate.ftl"
                    subject = "accountSettingGeneralInfoUpdate.subject.ftl"
                } else if(requestData.type == "accountSettingBillingInfoUpdate") {                     
                    tempalteMap.put("address1", requestData.billingStreetAddress)
                    tempalteMap.put("address2",requestData.billingStreetAddress1)
                    tempalteMap.put("email", requestData.email)
                    tempalteMap.put("city", requestData.city)
                    tempalteMap.put("country", requestData.country)
                    tempalteMap.put("stat", requestData.billingState)
                    tempalteMap.put("phone", requestData.billingPhoneNumber)
                    tempalteMap.put("zip", requestData.billingZip)                          
                    templateName = "accountSettingBillingInfoUpdate.ftl"
                    subject = "accountSettingBillingInfoUpdate.subject.ftl"
                }                                                  
                
                notificationService.send(user.account.email.toString(), subject, tempalteMap, templateName)
                item.put("Result",  GeneralConstants.RESULT_SUCCESS);
                sendMailResultList.add(item);                              
            } else {
                item.put("Result",  "No user found");
                sendMailResultList.add(item);
            }
            return sendMailResultList;            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    def update(String requestBody) {
        try { 
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser
            ArrayList<ArrayList<String>> AccountResultList = new ArrayList<ArrayList<String>>();  
            HashMap<String,String> optional = new HashMap<String,String>();
            def oldAccount = Account.findById(requestData.id); 
            if(requestData.type == "general") {                                
                oldAccount.firstName = requestData.firstName;
                oldAccount.lastName = requestData.lastName;
                
                oldAccount.streetAddress = requestData.street
                
                oldAccount.streetAddress1 = requestData.street1    
                oldAccount.city = requestData.city
                
                oldAccount.country = Country.findById(requestData.country)            
                oldAccount.state = State.findById(requestData.state)    
               
                oldAccount.phoneNumber = requestData.phone
                oldAccount.zip = requestData.zip                     
                optional.put("firstName",  oldAccount.firstName);
                optional.put("lastName",  oldAccount.lastName);
                
                optional.put("streetAddress",  oldAccount.streetAddress);
                optional.put("streetAddress1",  oldAccount.streetAddress1);
                optional.put("city",  oldAccount.city);
                optional.put("country",  oldAccount.country);
                optional.put("state",  oldAccount.state);                
                optional.put("phoneNumber",  oldAccount.phoneNumber);      
                optional.put("zip",  oldAccount.zip);      
                user.dateFormat = requestData.dateFormat
                user.save(flush: true)             
                if (user.hasErrors()) {
                    throw new ValidationException(user.errors.allErrors);
                }
            } else if(requestData.type == "billingInfo") {                
                oldAccount.billingStreetAddress = requestData.street
                oldAccount.billingStreetAddress1 = requestData.street1                
                oldAccount.companyName = requestData.companyName;
                oldAccount.billingCity = requestData.city
                oldAccount.billingZip = requestData.zip       
                oldAccount.billingPhoneNumber = requestData.phone                       
                oldAccount.billingState = State.findById(requestData.state)
                oldAccount.billingCountry = Country.findById(requestData.country)
                oldAccount.email = requestData.email;
                
                optional.put("billingStreetAddress",  oldAccount.billingStreetAddress);
                optional.put("billingStreetAddress1",  oldAccount.billingStreetAddress1);
                optional.put("billingCity",  oldAccount.billingCity);
                optional.put("billingZip",  oldAccount.billingZip);
                optional.put("billingPhoneNumber",  oldAccount.billingPhoneNumber);
                optional.put("billingState",  oldAccount.billingState);
                optional.put("billingCountry",  oldAccount.billingCountry);
                optional.put("billingCompanyName",  oldAccount.companyName);   
                optional.put("email",  oldAccount.email);
            } else {
                throw new AccountValidationException("failed");  
            }         
            oldAccount.save(flush: true)
             
            if (oldAccount.hasErrors()) {
                throw new ValidationException(oldAccount.errors.allErrors);
            }
            optional.put("result",  "OK");
            AccountResultList.add(optional)
            
            log.info("${requestData.type} info update for account ${oldAccount.id}") 
                        
            return AccountResultList;
        } catch (AccountValidationException ex) {
            def user = springSecurityService.currentUser
            def language;
            def role = springSecurityService.getPrincipal().getAuthorities()                
            if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                language = defaultLanguage.value
            } else {
                language = user.account.preferredLanguage
            }                                            
            throw new AccountValidationException(messageSource.getMessage('signup.accountType.invalid', null, new Locale(language)));
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
     
    def cardVerified() {
        def currentUser = springSecurityService.currentUser 
        def role = springSecurityService.getPrincipal().getAuthorities()

        ArrayList<ArrayList<String>> cardVerification = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 

        if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            if(currentUser.account.cardVerified == false || currentUser.account.cardVerified == "false") {
                if(currentUser.account.accountType == AccountType.values()[0]) {
                    item.put("cardVerified",  GeneralConstants.RESULT_SUCCESS);
                    item.put("defaultCardEnabled", currentUser.account.cardVerified.toString());
                    cardVerification.add(item);
                } else {
                    item.put("cardVerified",  GeneralConstants.RESULT_FAILURE);
                    item.put("defaultCardEnabled", currentUser.account.cardVerified.toString());
                    cardVerification.add(item); 
                } 
            } else if(currentUser.account.cardVerified == true || currentUser.account.cardVerified == "true") {
                item.put("cardVerified",  GeneralConstants.RESULT_SUCCESS);
                item.put("defaultCardEnabled", currentUser.account.cardVerified.toString());
                cardVerification.add(item);
            } 
            return cardVerification;
        }
    }
    
    
    def enableAccount(requestBody) {
        
        ArrayList<ArrayList<String>> enableAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        Date date = new Date()
        def time = new Timestamp(date.getTime())               
        //        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        //        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        //        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        def requestData = JSON.parse(requestBody)
        
        def account = Account.get(Integer.parseInt(requestData.accountId));
        def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
        try {  
            def user = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            

            if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
                
                
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                throw new Exception("access denied");
            }
            log.info("Account: ${account.id} is enabled by user ${user.id}")
            return enableAccount
        } catch (Exception ex) {
            log.info("Account: ${requestData.accountId}  enable failed due to ${ex}")
            throw ex;   
        } 
    }
    
    
    def suspendAccount(requestBody) {
        
        ArrayList<ArrayList<String>> suspendAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        Date date = new Date()
        def time = new Timestamp(date.getTime())              
        //        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        //        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        //        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
        try {  
            def user = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            def account = Account.get(Integer.parseInt(requestData.accountId));

            if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
                
                
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                throw new Exception("access denied");
            }
            log.info("Account: ${account.id} is suspended by user ${user.id}")
            return suspendAccount
        } catch (Exception ex) {
            throw ex;   
        } 
    }
    
    def disableAccount(requestBody) {
        
        ArrayList<ArrayList<String>> disableAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        Date date = new Date()
        def time = new Timestamp(date.getTime())              
        //        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        //        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        //        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        try {  
            def user = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            def account = Account.get(Integer.parseInt(requestData.accountId));

            if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
                
               
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                throw new Exception("access denied");
            }
            log.info("Account: ${account.id} is disabled by user ${user.id}")    
            return disableAccount
        } catch (Exception ex) {
            throw ex;   
        } 
    }
    
    
    def cancelAccount(requestBody) {
       
        try {  
            def user = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
                def account = Account.get(Integer.parseInt(requestData.accountId));
                return processForCancelAccount(account)
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                return processForCancelAccount(user.account)
            }
        
        }catch (Exception ex){
            throw ex;   
        } 
    }
    
    def processForCancelAccount(account) { 
        def user = springSecurityService.currentUser 
        ArrayList<ArrayList<String>> calcelAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        Date date = new Date()
        def time = new Timestamp(date.getTime())        
        //        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        //        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        //        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        try {  
           
            
        }catch (Exception ex){
            throw ex;   
        }
    }
    def sendMail(account, emailTemplateName) {        
        def template = ""
        def subject = ""
        if(emailTemplateName == "accountCancelled") {
            template = "accountCancelled.ftl"
            subject = "accountCancelled.subject.ftl"
        } else {
            template = "accountClose.ftl"
            subject = "accountClose.subject.ftl"

        }        
        
        Map tempalteMap = notificationService.getDefaultMailTemplateMap() 
        def user = User.findByAccount(account);
        tempalteMap.put("user", user)
        notificationService.send(account.email, subject, tempalteMap, template)                    
    }            
    def cleanCancelAccount() {   
        
        log.info("CancelAccountClean Cron Executed");
    }
    
    def deleteCancelAccountResources(account) {
        def vmList = VirtualMachine.findAllWhere(owner: account, deleted: false)
        def user = User.findByUsername(account.accountIdentifier)
        def snapList = Snapshot.findAllWhere(user: user, deleted: false)
        
        //        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        //        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        //        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        //        
        //        CloudStackServer server =
        //                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        //                
        //        CSVirtualMachineService csVirtualMachineService = new CSVirtualMachineService(server);
        
        //        CSSnapshotService csSnapshotService = new CSSnapshotService(server);
        
        for(def vm: vmList) {
            
            vm.deleted = true
            vm.save(flush:true);
            //            def response =  csVirtualMachineService.destroyVirtualMachine(vm.referenceId); 
        }
        
        for(def snap: snapList) {
            
            snap.deleted = true
            snap.save(flush:true);
            //            def response =  csSnapshotService.deleteSnapshot(snap.snapshotReferenceId);  
        }
        
        def ipList = VmIp.findAllWhere(account: account, inUse: true) 
        
        for(def ip : ipList) {
            ip.inUse = false
            ip.save(flush:true);
        }
        
        
    }
    
    def removeVMForClosedAccount(account) {
        
        //        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        //        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        //        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        //        
        //        CloudStackServer server =
        //                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        //                
        //        CSVirtualMachineService csVirtualMachineService = new CSVirtualMachineService(server);
        //        
        //        def vmList = VirtualMachine.findAllWhere(owner:account);
        
        for(def vm: vmList) {
            try {
                //                def response =  csVirtualMachineService.destroyVirtualMachine(vm.referenceId);
                log.info("Cancel account vm:${vm.id} deleted")
            } catch (Exception ex) {
                log.info("Cancel account instance clean failed due to ${ex}")
            } 
        }
        removeDiskForClosedAccount(account)
    }
    
    def removeDiskForClosedAccount(account) {
        
        
        
        log.info("Cancel Account Clean last run for account:${account.accountIdentifier}");
    }
            
    def resetPasswordForAccount(requestBody) {
       
        ArrayList<ArrayList<String>> passAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();  
        
        try {  
            
            def currentAcc = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            def requestData = JSON.parse(requestBody)
                        
            HttpConnector userAPIConnector = new HttpConnector()
            
            ConfigLoader configLoader = ConfigLoader.getInstance();

            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();        
            
            if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {

                def account = Account.get(Integer.parseInt(requestData.accountId));
                
                //                account.password = requestData.confirmPassword;
                //                account.save(flush:true);
                
                def user = User.findByUsername(account.accountIdentifier)
                                
                String data = '{ "user": {"password": "'+ requestData.confirmPassword +'"}}'
                    
                def response = userAPIConnector.simplePatch(new URL(props.get(Config.OPENSTACK_ENDPOINT_URL)+"/users/"+user.uuid), data, os.getToken().getId())

                if(response == "Success") {
                     
                    user.password = requestData.confirmPassword;
                    user.save(flush:true);
                    log.info("Reset pasword for account:${account.id} by user ${currentAcc.id}")
                    item.put("result",  GeneralConstants.RESULT_SUCCESS);
                    passAccount.add(item);

                    return passAccount

                } else {
                    
                    return response     
                }   
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                throw new Exception("access denied");
            }
           
        } catch (Exception ex) {
            throw ex;   
        } 
    }
    
    def customResetPassword(username, password) {
        
        ConfigLoader configLoader = ConfigLoader.getInstance();
        
         User user = User.findByUsername(username);  
        
        Properties props = configLoader.getProperties();
        HttpConnector userAPIConnector = new HttpConnector()
        String data = '{ "user": {"password": "'+ password +'"}}'
        
        if(props.get(Config.OPENSTACK_ENDPOINT_URL) != null 
            && props.get(Config.OPENSTACK_ADMIN_UUID) != null 
            && props.get(Config.OPENSTACK_ADMIN_PASSWORD) != null ) {
            
            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
            
            OSClient os = server.authenticate();        
            
            // for admin the uuid is empty
            if(user.uuid != null && !user.uuid.empty) {
                
                def response = userAPIConnector.simplePatch(new URL(props.get(Config.OPENSTACK_ENDPOINT_URL)+"/users/"+user.uuid), data, os.getToken().getId())
                
                println("password response "+response);
                if(response == "Success") {
                    
                    return true;
                } else {
                    return false;
                }
            } else {
                
                return true;
            }
            
            
        } else {
            return true;
        }
        
        
    }
    
    
    def resetPasswordForAdminUser(requestBody) {
        
        ArrayList<ArrayList<String>> passAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();  
        
        try {  
            def currentAcc = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {

                               
                def user = User.get(Long.parseLong(requestData.userId))
                user.password = requestData.confirmPassword;
                user.save(flush:true);
                log.info("Reset pasword for admin:${user.id} by user ${currentAcc.id}")
                item.put("result",  GeneralConstants.RESULT_SUCCESS);
                passAccount.add(item);
                
                return passAccount
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                throw new Exception("access denied");
            }
           
        }catch (Exception ex){
            throw ex;   
        } 
    }
    
    def lockAccount(requestBody) {
        
        ArrayList<ArrayList<String>> lockAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();  
        //        def currentUser = springSecurityService.currentUser 
        def role = springSecurityService.getPrincipal().getAuthorities()
        // convert string to json object
        def requestData = JSON.parse(requestBody)
        Date date = new Date()
        def time = new Timestamp(date.getTime())     
        if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
            
            def account = Account.get(Integer.parseInt(requestData.accountId));
            
            def user = User.findByUsername(account.accountIdentifier)
            user.accountLocked = true
            user.save(flush: true);
            
            notificationService.send(account.email.toString(), "Account Notification", "<h1>Your account is Locked</h1>");

            def log = new Log()
            log.account = account
            log.user = User.findByUsername(account.accountIdentifier)
            log.date = time
            log.type = LogType.values()[3]
            log.mailSend = false
            log.description = "Account " + account.accountIdentifier + " is locked"
            log.save(flush: true);
               
            item.put("result",  GeneralConstants.RESULT_SUCCESS);
            lockAccount.add(item);
            
            return lockAccount
                
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            throw new Exception("access denied");
        }
        
        
    }
    
    def unLockAccount(requestBody) {
        
        ArrayList<ArrayList<String>> unLockAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();  
        //        def currentUser = springSecurityService.currentUser 
        def role = springSecurityService.getPrincipal().getAuthorities()
        def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
        // convert string to json object
        def requestData = JSON.parse(requestBody)
        Date date = new Date()
        def time = new Timestamp(date.getTime())     
        if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
            
            def account = Account.get(Integer.parseInt(requestData.accountId));
            
            def user = User.findByUsername(account.accountIdentifier)
            user.accountLocked = false
            user.save(flush: true);                        
            Map tempalteMap = notificationService.getDefaultMailTemplateMap()   
            tempalteMap.put("user", user)
            notificationService.send(account.email.toString(), "loginUnlock.subject.ftl", tempalteMap, "loginUnlock.ftl")              
            def log = new Log()
            log.account = account
            log.user = User.findByUsername(account.accountIdentifier)
            log.date = time
            log.type = LogType.values()[3]
            log.mailSend = true
            log.description = "Account " + account.accountIdentifier + " is UnLocked"
            log.save(flush: true);
               
            item.put("result",  GeneralConstants.RESULT_SUCCESS);
            unLockAccount.add(item);
            
            return unLockAccount
                
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            throw new Exception("access denied");
        }
    }
    
    def listAdminUser() {
        def role = springSecurityService.getPrincipal().getAuthorities()
        def currentUser = springSecurityService.currentUser 
        
        ArrayList<ArrayList<String>> adminUsers = new ArrayList<ArrayList<String>>();            
        
        
        
        if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
             
            def userRoleList = UserRole.findAllByRole(Role.get(1))
            
            for(def userRole: userRoleList) {
                
                def adminUser = userRole.user
                if(adminUser.accountExpired == false) {
                    HashMap<String,String> item = new HashMap<String,String>();  
                    item.put("id",  adminUser.id);
                    item.put("userName",  adminUser.username);
                    if(currentUser == adminUser) {
                        item.put("currentAadmin",  "true");
                    } else {
                        item.put("currentAadmin",  "false"); 
                    }
                    def apiUser = ApiUser.findWhere(user: adminUser, enabled:true) 
                    if(apiUser) {
                        item.put("apiAccess",  "true");
                    } else {
                        item.put("apiAccess",  "false");
                    }
                    adminUsers.add(item);
                }
                
            }
            return adminUsers
            
        } else {
            throw new Exception("access denied");
        }
    }
    
    def addAdminUser(requestBody) {
        try {  
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            Calendar calendar = Calendar.getInstance(); 
            
            def currentAcc = springSecurityService.currentUser 
            
            User user = new User();
            user.account = Account.get(10001);
            user.domain = user.account.domain
            user.token = "added by admin"
            user.username = requestData.userName;  
            user.failureCount = 0;
            user.firstName = "admin"
            user.lastName = "admin"
            user.password = requestData.password; 
            user.enabled = true;
            user.tokenExpiryDate = calendar.getTime() 
            user.accountExpired = false;
            user.accountLocked = false;
            user.passwordExpired = false;
            user.save(flush:true, failOnError:true); 

            def role = Role.findByAuthority("ROLE_ADMIN");
            UserRole userRole = new UserRole();
            userRole.user = user;
            userRole.role = role;
            userRole.save(flush:true, failOnError:true);   
            
            log.info("Admin user:${user.id} added by user ${currentAcc.id}")
            
        } catch (Exception ex) {
            throw ex;   
        } 
    }
    
    def getAPIkey(userId) {
        
        long id = Long.parseLong(userId)
        
        def user = User.get(id.toLong())
        
        def apikey = ApiUser.findWhere(user: user, enabled:true)
        
        ArrayList<ArrayList<String>> apiKeyArray = new ArrayList<ArrayList<String>>();       
        HashMap<String,String> item = new HashMap<String,String>();  
        item.put("id",  apikey.id);
        item.put("apiKey",  apikey.apiKey);
        item.put("secret",  apikey.secret);
        
        apiKeyArray.add(item);
        
        return apiKeyArray;
        
    }
    
    def revokeAPIKey(userId) {
        try {
            
            def currentAcc = springSecurityService.currentUser 
            
            long id = Long.parseLong(userId)

            def user = User.get(id.toLong())

            def apikey = ApiUser.findWhere(user: user, enabled:true)
            apikey.delete();
            log.info("Revoke api key access for user:${user.id} by user ${currentAcc.id}")
            
        } catch (Exception ex) {
            throw ex;   
        } 
    }
    
    def resetAPIKey(userId) {
        
        try {
            
            def currentAcc = springSecurityService.currentUser 
            
            long id = Long.parseLong(userId)

            def user = User.get(id.toLong())

            def apikey = ApiUser.findWhere(user: user, enabled:true)

            Date date = new Date();
            def time = new Timestamp(date.getTime())
          
            def apiKeyData = "Fog-API"+ user.username + date.toString()
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(apiKeyData.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String apiHashText = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (apiHashText.length() < 32) {
                apiHashText = "0" + apiHashText;
            }


            def secretKeyData = "Fog-secret" + user.username + date.toString() + apiHashText.toString()
            MessageDigest md2 = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest2 = md2.digest(secretKeyData.getBytes());
            BigInteger number2 = new BigInteger(1, messageDigest2);
            String secretHashText = number2.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (secretHashText.length() < 32) {
                secretHashText = "0" + secretHashText;
            }
            
            apikey.apiKey = apiHashText.toString();
            apikey.secret = secretHashText.toString();
            apikey.save()
            log.info("Reset api key access for user:${user.id} by user ${currentAcc.id}")
            
        } catch (Exception ex) { 
            throw ex;   
        } 
    } 
    
    def grantAPIKey(userId) {
        try {
            
            def currentAcc = springSecurityService.currentUser 
            
            long id = Long.parseLong(userId)

            def user = User.get(id.toLong())
            
            Date date = new Date();
            def time = new Timestamp(date.getTime())
            
            def apikey = new ApiUser()
            apikey.enabled = true;
            apikey.createdDate = time;
            apikey.user = user;

            def apiKeyData = "Fog-API"+ user.username + date.toString()
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(apiKeyData.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String apiHashText = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (apiHashText.length() < 32) {
                apiHashText = "0" + apiHashText;
            }


            def secretKeyData = "Fog-secret" + user.username + date.toString() + apiHashText.toString()
            MessageDigest md2 = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest2 = md2.digest(secretKeyData.getBytes());
            BigInteger number2 = new BigInteger(1, messageDigest2);
            String secretHashText = number2.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (secretHashText.length() < 32) {
                secretHashText = "0" + secretHashText;
            }

            apikey.apiKey = apiHashText.toString();
            apikey.secret = secretHashText.toString();
            apikey.save()
            log.info("Grant API access for user:${user.id} added by user ${currentAcc.id}")
            if (!apikey.save()) {
                apikey.errors.allErrors.each { println it }
            }
            
        } catch (Exception ex) {
            throw ex;   
        } 
    }
    
    def validateAdminUserName(userName) {
                       
        def result = User.findByUsername(userName);
        def validationStatus = userName.matches("[a-zA-Z0-9]*");
        
        if(result) {
            return [GeneralConstants.RESULT_FAILURE]
        } else if(validationStatus == false || validationStatus == "false" || userName.size() < 5) {
            return [GeneralConstants.RESULT_FALSE]
        } else if(validationStatus == true || validationStatus == "true" && userName.size() >= 5) {
            return [GeneralConstants.RESULT_TRUE]
        }
    }
    def deleteAdminUser(requestBody) {
        try {  
            
            def currentAcc = springSecurityService.currentUser 
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            def user = User.get(requestData.id)
            
            user.accountExpired = true
            user.save(flush:true, failOnError:true); 
            log.info("Delete Admin user:${user.id} by user ${currentAcc.id}")
                    
        } catch (Exception ex){
            throw ex;   
        } 
    }
     
    def creditLimitStatus() {
        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        ArrayList<ArrayList<String>> creditLimitAccount = new ArrayList<ArrayList<String>>();   
        def accountList = Account.findAll()
        for(def account: accountList) {

            if(account.totalPayable > account.creditLimit) {
                HashMap item = new HashMap();  
                item.put("accountId",  account.id);
                item.put("account",  account.firstName);
                item.put("amount",  Math.ceil((account.totalPayable - account.creditLimit) * 100d) / 100d);
                item.put("currency", currency);      
                creditLimitAccount.add(item)
                 
            }
        }
        return creditLimitAccount;
    }
    
    def resourceLimit() {
       
        def user = springSecurityService.currentUser 
                
        ArrayList<ArrayList<String>> account = new ArrayList<ArrayList<String>>();
        HashMap<String,String> accountItem = new HashMap<String,String>();
        accountItem.put("accountType", user.account.accountType.name());
        //        accountItem.put("vmLimitNo", user.account.vmLimit);
        //        accountItem.put("storageLimitNo", user.account.storageLimit);
        //        accountItem.put("snapLimitNo", user.account.snapshotLimit);
        
        //        def vmCount = VirtualMachine.findAllWhere(deleted: false, user: user); 
        //        def volumes = Volume.findAllWhere(deleted: false, owner: user.account); 
        //        def snap = Snapshot.findAllWhere(deleted: false, user: user);  
        //        
        //        accountItem.put("vmCount", vmCount.size());
        //        accountItem.put("volumeCount", volumes.size());
        //        accountItem.put("snapCount", snap.size());
        account.add(accountItem);
        return account
    }
    
    def pullAccountsFromOpenStackJobStart() {
        
        PullAccountJob.triggerNow([id:"account"])
            
        return "OK"
        
    }
    def pullAccountFromOpenStack(jobid) {
        
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            
            //default country
            Country country = Country.findByCountryCode(356);
            
            //default state
            State state = State.findByStateName("TAMIL NADU");
            
            
            //default language        
            def defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            
            ConfigLoader configLoader = ConfigLoader.getInstance();
           
            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();
            
            Config billingPeriodDays =  Config.findByName(Config.BILLING_PERIOD_DAYS);
            Config signupCardVerificationEnabled =  Config.findByName(Config.SIGNUP_CARD_VERIFICATION_ENABLED);
            
            Config creditCardProcessing =  Config.findByName(Config.CARD_PROCESSING_ENABLED);
    
            Config vmLimitNo =  Config.findByName(Config.INSTANCE_LIMIT);
            Config storageLimitNo =  Config.findByName(Config.STORAGE_LIMIT);
            Config snapLimitNo =  Config.findByName(Config.SNAPSHOT_LIMIT);
            Config bandLimitNo =  Config.findByName(Config.BANDWIDTH_LIMIT);
                
            List<? extends Project> projects = os.identity().projects().list();
            
            for (Project project : projects) {
                
                //                println("project.id"+project.id);
                
                Account account = Account.findByUuid(project.id);
                
                
                if(!account) {
                    
                    account = new Account();
                    account.uuid = project.id;
                    
                    // todo to be changed based on role
                    account.accountType = AccountType.valueOf("RETAIL");
                    
                    //account.cpuLimit = data.cpuLimit == "Unlimited" ? "-1" : data.cpuLimit;
                    account.domain = Domain.findByReferenceId(project.domainId);
                    //account.publicIPLimit = data.ipLimit == "Unlimited" ? "-1" : data.ipLimit;
                    //account.memoryLimit = data.memoryLimit == "Unlimited" ? "-1" : data.memoryLimit;
                    account.email = "XXXXX@dummy.com";
                    //account.networkLimit = data.networkLimit == "Unlimited" ? "-1" : data.networkLimit;
                    //account.primaryStorageLimit = data.primaryStorageLimit == "Unlimited" ? "-1" : data.primaryStorageLimit;
                    //account.secondaryStorageLimit = data.secondaryStorageLimit == "Unlimited" ? "-1" : data.secondaryStorageLimit;
                    //account.snapshotLimit = data.snapshotLimit == "Unlimited" ? "-1" : data.snapshotLimit;
                    account.status = project?.enabled ? Status.valueOf("ACTIVE") : Status.valueOf("DISABLED");
                  
                    //account.vmLimit = data.vmLimit == "Unlimited" ? "-1" : data.vmLimit;
                    //account.storageLimit = data.volumeLimit == "Unlimited" ? "-1" : data.volumeLimit;
                    //account.vpcLimit= data.vpcLimit == "Unlimited" ? "-1" : data.vpcLimit;
                    account.accountIdentifier=project.name                    
                    account.billingCity = "XXXXX"
                    account.billingCountry=country
                    account.billingPhoneNumber="0000000000"
                    account.billingState=state
                    account.billingStreetAddress="Administratoraddress"
                    account.billingZip="00000"
                    account.cardVerified=0
                    account.city="00000"
                    account.country=country
                    account.credit=0
                    account.creditLimit=0
                    account.firstName="Administrator"
                    account.lastName="Administrator"
                    account.phoneNumber="0000000000"
                    account.state=state
                    account.streetAddress="Administratoraddress"
                 
                    account.zip="00000"
                    account.streetAddress1="xxx"
                    account.billingStreetAddress1="xxx"
                   
                    account.preferredLanguage = defaultLanguage.value
                    
                    account.signUpDate=new Date();
                    account.lateFee=false;
                    account.totalAmount=0;
                    account.totalPayable=0;
                    account.totalPaid=0;
                    account.previousPaid=0;
                    account.penaltyFee=0;
                    
                    Calendar vmsnapdate = Calendar.getInstance();
                    vmsnapdate.add(Calendar.DATE, -1);
                    account.vmSnapUsageDate = vmsnapdate.getTime()

                    
                    // setting is_completed flag false since billing info not yet updated
                    //account.isCompleted = false
                    
                    if (!account.save(flush: true)) {
                        account.errors.allErrors.each { println it }
                    }
                    
                    
                } else {
                    
                                                       
                    account.status = project?.enabled ? Status.valueOf("ACTIVE") : Status.valueOf("DISABLED");
                    account.accountIdentifier=project.name 
                    account.domain = Domain.findByReferenceId(project.domainId);
                                        
                    if (!account.save(flush: true)) {
                        account.errors.allErrors.each { println it }
                    }
                    
                }

            }  
            
            job.status = JobStatus.valueOf("COMPLETED")
            job.completedAt = new Date()
            job.save(flush: true)
            
        } catch (Exception ex) {
            
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }  
    
    def getResourceLimit(String accountId) {
        
        
        if(accountId != null && !accountId.empty) {
            
            // getting Account
            Account account = Account.findById(Long.parseLong(accountId));
            
            // getting ResouceLimit
            ResourceLimit resourceLimit = ResourceLimit.findByAccount(account);
            
            // forming resourceInfo data
            ArrayList<ArrayList<String>> resourceInfo = new ArrayList<ArrayList<String>>();
            
            def volumes = Volume.findAllWhere(deleted: false, account:account);
            def volumeSnapshots = VolumeSnapshot.findAllWhere(deleted: false, account:account);
            def virtualMachines = VirtualMachine.findAllWhere(account: account, deleted:false);
            
            
            HashMap item = new HashMap();
            
            item.put("metadataItems", resourceLimit.metadataItems);      
            item.put("injectedFileContentBytes", resourceLimit.injectedFileContentBytes);                                     
            item.put("injectedFiles", resourceLimit.injectedFiles);      
            item.put("ram", resourceLimit.ram);      
            
            item.put("instances", resourceLimit.instances);      
            item.put("instancesUsed", virtualMachines.size());      
            
            
            item.put("cores", resourceLimit.cores);  
            
            def coresUsed = 0;
            def ramUsed = 0;
            // looping instances by account
            for(def vm : virtualMachines) {
                
                coresUsed += vm.flavor.vcpus;
                ramUsed += vm.flavor.memory;
            }
            item.put("coresUsed", coresUsed);
            item.put("ramUsed", ramUsed);
            
            item.put("securityGroups", resourceLimit.securityGroups);  
            item.put("securityGroupsUsed", SecurityGroup.findAllWhere(deleted: false, account:account).size());  
            
            item.put("securityGroupRules", resourceLimit.securityGroupRules);      
            item.put("subnet", resourceLimit.subnet);      
            item.put("router", resourceLimit.router);      
            item.put("port", resourceLimit.port);  
            
            item.put("network", resourceLimit.network); 
            item.put("networkUsed", Network.findAllWhere(deleted: false, account:account).size());
            
            
            item.put("publicIPLimit", resourceLimit.floatingIp);      
            item.put("publicIPUsed", FloatingIp.findAllWhere(deleted: false, account:account).size());     
            
            def gigabytesUsed = 0;
            item.put("gigabytes", resourceLimit.gigabytes);    
            
            // looping volumes and volume snapshot and get total GB used
            for(def volume : volumes) {
                gigabytesUsed += volume.size
            }
            for(def volumeSnapshot :volumeSnapshots ) {
                gigabytesUsed += volumeSnapshot.size
            }
            
            item.put("gigabytesUsed", gigabytesUsed);      
            
            item.put("volumes", resourceLimit.volumes);      
            item.put("volumesUsed", volumes.size());
            
            item.put("snapshots", resourceLimit.snapshots); 
            item.put("snapshotsUsed", volumeSnapshots.size());
            
            item.put("accountId", resourceLimit.account.id);      
            
            
            resourceInfo.add(item)
            
        
            return resourceInfo
            
        }
        
        
        
    }
    
    def updateResourceLimit(requestData) {
        
        // getting Account
        Account account = Account.findById(requestData.accountId);
        def tenantId = account.uuid;

        // getting Openstack server object
        OpenStackServer server = OpenStackServer.getInstance();
        
        // authenticate
        OSClient os = server.authenticate();
        
       
        // update the compute quotaset
        ComputeQuotaSet computeQuotaSet = os.compute().quotaSets()
        .put(Builders.quotaSet()
            .ram(requestData.retailMemoryLimit as int)
            .instances(requestData.retailInstanceLimit as int)
            .cores(requestData.retailCpuLimit as int)
            .metadataItems(requestData.retailMetaDataLimit as int)
            .injectedFiles(requestData.retailInfectedFileLimit as int)
            .injectedFileContentBytes(requestData.retailInfectedFileContentLimit as int)
            .securityGroups(requestData.retailSecurityGroupLimit as int)
            .securityGroupRules(requestData.retailSecurityGroupRuleLimit as int)
            .floatingIps(requestData.retailPublicIPLimit as int)
            .build(), tenantId);
        
        // update the nova quotaset
        NetQuota novaQuota = os.networking().quotas()
        .put(Builders.netQuota()
            .floatingIP(requestData.retailPublicIPLimit as int)
            .network(requestData.retailNetworkLimit as int)
            .port(requestData.retailPortLimit as int)
            .router(requestData.routerLimit as int)
            .subnet(requestData.subnetLimit as int).build(), tenantId);
        
        // update cinder quotaset
        CinderQuotaSet cinderQuotaSet = os.blockStorage().quotaSets()
        .put(Builders.volumeQuota()
            .gigabytes(requestData.retailTotalSizeOfVolumeAndSnapshot as int)
            .snapshots(requestData.retailSnapshotLimit as int)
            .volumes(requestData.retailStorageLimit as int)
            .build(), tenantId);
                
        // update resource limit table
        ResourceLimit resourceLimit = ResourceLimit.findByAccount(account);
        if(!resourceLimit) {
            
            resourceLimit = new ResourceLimit();
            resourceLimit.account = account;
        }
        
        // setting the updated value from openstack to fogpanel
        resourceLimit.ram = computeQuotaSet.ram
        resourceLimit.instances = computeQuotaSet.instances
        resourceLimit.cores = computeQuotaSet.cores
        resourceLimit.metadataItems = computeQuotaSet.metadataItems
        resourceLimit.injectedFiles = computeQuotaSet.injectedFiles
        resourceLimit.injectedFileContentBytes = computeQuotaSet.injectedFileContentBytes
        resourceLimit.securityGroups = computeQuotaSet.securityGroups
        resourceLimit.securityGroupRules = computeQuotaSet.securityGroupRules
        
        resourceLimit.floatingIp = novaQuota.floatingIP
        resourceLimit.network = novaQuota.network
        resourceLimit.port = novaQuota.port
        resourceLimit.router = novaQuota.router
        resourceLimit.subnet = novaQuota.subnet
        
        resourceLimit.gigabytes = cinderQuotaSet.gigabytes
        resourceLimit.snapshots = cinderQuotaSet.snapshots
        resourceLimit.volumes = cinderQuotaSet.volumes
        
        resourceLimit.save(flush:true);
        
        ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();    
        HashMap item = new HashMap();
        item.put("result", GeneralConstants.RESULT_SUCCESS);
        resultList.add(item)
        
        return resultList
        
    }
        
}
