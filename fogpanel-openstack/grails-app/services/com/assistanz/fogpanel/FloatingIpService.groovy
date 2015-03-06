package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.openstack.OpenStackServer;
import java.util.Arrays;
import java.util.List;
import com.assistanz.cloud.config.ConfigLoader
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.IP;
import org.openstack4j.model.network.NetFloatingIP;
import org.openstack4j.model.compute.ActionResponse;
import org.springframework.context.MessageSource

@Transactional
class FloatingIpService {
    
    def springSecurityService;
    InvoiceService invoiceService
    OpenStackAuthService openStackAuthService
    MessageSource messageSource

    def floatingIpList(referenceId) {
        
       try { 
           
            def user = springSecurityService.currentUser;  
        
            ArrayList<ArrayList<String>> floatingIps = new ArrayList<ArrayList<String>>();
            
            def account = user.account;

            def floatingIpList = FloatingIp.withCriteria { 
                eq('deleted', false)
                eq('account', account)
                if(referenceId) {
                    eq('referenceId', referenceId) 
                }
            }
            
            for (def floatingIp : floatingIpList) {
            
                HashMap item = new HashMap();
                item.put("networkId", floatingIp.externalNetwork?.referenceId);
                item.put("networkName", floatingIp.externalNetwork?.name);
                item.put("referenceId", floatingIp.referenceId);
                item.put("instanceName", floatingIp.virtualMachine?.name);
                item.put("instanceId", floatingIp.virtualMachine?.referenceId);
                item.put("portId", floatingIp.portId);
                item.put("routerId", floatingIp.routerId);
                item.put("fixedIpAddress", floatingIp.fixedIpAddress)
                item.put("floatingIpAddress", floatingIp.floatingIpAddress)
                                
                floatingIps.add(item)             
            }
            
            return floatingIps;
            
       } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    def floatingIpListForAssociate() {
        try {
            
            def user = springSecurityService.currentUser;  
        
            ArrayList<ArrayList<String>> floatingIps = new ArrayList<ArrayList<String>>();
            
            def account = user.account;
            
            def floatingIpList = FloatingIp.withCriteria { 
                eq('deleted', false)
                eq('account', account)
                isNull("fixedIpAddress")
            }
            
            for (def floatingIp : floatingIpList) {
            
                HashMap item = new HashMap();
                item.put("networkId", floatingIp.externalNetwork?.referenceId);
                item.put("networkName", floatingIp.externalNetwork?.name);
                item.put("referenceId", floatingIp.referenceId);
                item.put("instanceName", floatingIp.virtualMachine?.name);
                item.put("portId", floatingIp.portId);
                item.put("routerId", floatingIp.routerId);
                item.put("fixedIpAddress", floatingIp.fixedIpAddress)
                item.put("floatingIpAddress", floatingIp.floatingIpAddress)
                                
                floatingIps.add(item)             
            }
            
            return floatingIps;
            
        } catch (Exception ex) {
           
          
            throw ex;
        }
    }
    
    def getPortList(instanceId) {
        try {
            
            def user = springSecurityService.currentUser; 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate();
            
            List<? extends Port> ports = os.networking().port().list();
            ArrayList<ArrayList<String>> portList = new ArrayList<ArrayList<String>>();
            
            for(Port port : ports) {
                
                String owner = GeneralConstants.PORT_DEVICE_OWNER_COMPUTE;
                HashMap portItem = new HashMap();
                
                if(port.getDeviceOwner().equals(owner)) {
                    
                    def instance = VirtualMachine.findWhere(referenceId: port.getDeviceId(), deleted: false)
                    def floatingIp = FloatingIp.findWhere(portId: port.getId(), deleted: false)
                    //Get instance Port list
                    if(instance) {
                        //Get Port list for Particular instance
                        if(instanceId) {
                            if(instance.referenceId == instanceId) {
                                //Check if port was already associated 
                                if(floatingIp) {
                                 
                                } else {
                                    portItem = instancePortList(port, instance)
                                    portList.add(portItem)
                                } 
                                
                            }
                        } else { 
                            //Check if port was already associated 
                            if(floatingIp) {
                             
                            } else {
                                portItem = instancePortList(port, instance)
                                portList.add(portItem)  
                            }
                            
                            
                        }
                    }
                }
            }
            return portList;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def instancePortList(port, instance) {
        try {
                 
            ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
            
            HashMap item = new HashMap();

            for(IP ip : port.getFixedIps()) {

                item.put("referenceId", port.getId())
                item.put("mappedIpAddress", instance.name + ": " + ip.getIpAddress())
                item.put("subnetId", ip.getSubnetId())

            }
            return item;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def getInstanceIp(referenceId) {
        try {
            def virtualMachine = VirtualMachine.findByReferenceId(referenceId)
            ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
            
            def instanceIpList = FloatingIp.withCriteria {
                eq('deleted', false)
                if(referenceId) {
                    eq('virtualMachine', virtualMachine) 
                }
            }
            
            for(def instanceIp: instanceIpList) {
                HashMap item = new HashMap();
                if(instanceIp.fixedIpAddress) {
                    item.put("referenceId",instanceIp.referenceId)
                    item.put("fixedIpAddress",instanceIp.fixedIpAddress)
//                    item.put("virtualMachine",virtualMachine)
                    item.put("floatingIpAddress", instanceIp.floatingIpAddress)
                    item.put("portId",instanceIp.portId)
                    list.add(item);
                }
                
            }
            
            return list;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def create(requestData) {
        try {
            
            def user = springSecurityService.currentUser; 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate(); 
            
            NetFloatingIP netFloatingIP = os.networking().floatingip().create(Builders.netFloatingIP()
                     .floatingNetworkId(requestData.networkId)
                     .build());
                 
            def network = Network.findByReferenceId(netFloatingIP.getFloatingNetworkId());
            def account = Account.findByUuid(netFloatingIP.getTenantId());
                 
            def floatingIp = new FloatingIp();
            floatingIp.referenceId = netFloatingIP.getId();
            floatingIp.externalNetwork = network;
            floatingIp.floatingIpAddress = netFloatingIP.getFloatingIpAddress();
            floatingIp.account = account;
            floatingIp.createdAt = new Date();
            floatingIp.billingType = requestData.billingType
            floatingIp.save(flush: true);
                        
            def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
            def price = ""
            def cost = MiscellaneousOffer.get(6)?.cost
            if(floatingIp.billingType == "monthly") {
                price = cost * 720
            } else {
                price = cost
            }
            
            if(!invoice) {
                invoice = invoiceService.createDraftInvoice(user.account)             
            }            
            def invoiceItem = invoiceService.addInvoiceItem(invoice, BillableItem.get(14), floatingIp.billingType, floatingIp.floatingIpAddress, floatingIp.referenceId, null, price, null, null)    
            
            ArrayList<ArrayList<String>> createResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createResponse.add(item)
            
            return createResponse;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def associate(requestData) {
        try {
            
            def user = springSecurityService.currentUser; 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate();
            def id;
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
                        
            if(requestData.instanceId) {
               
                def selectedFloatingIp = FloatingIp.findWhere(floatingIpAddress: requestData.ipAddress, deleted: false);
                id = selectedFloatingIp.referenceId;
                
            } else {
                id = requestData.id;
            }

            NetFloatingIP netFloatingIP = os.networking().floatingip()
                                             .associateToPort(id, requestData.portId);
                                                         
            if(netFloatingIP) {
                
                Port port = os.networking().port().get(netFloatingIP.getPortId());
            
                def instance = VirtualMachine.findByReferenceId(port.getDeviceId());
                
                if(instance) {
                    def floatingIp = FloatingIp.findByReferenceId(id);
                    floatingIp.referenceId = netFloatingIP.getId();
                    floatingIp.fixedIpAddress = netFloatingIP.getFixedIpAddress();
                    floatingIp.virtualMachine = instance;
                    floatingIp.portId = netFloatingIP.getPortId();
                    floatingIp.routerId = netFloatingIP.getRouterId();
                    floatingIp.createdAt = new Date();
                    floatingIp.save(flush: true);
                }
 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                response.add(item)
                
            } else {
                item.put("result", GeneralConstants.RESULT_FAILURE);
                response.add(item)
            }
                                             
            
            
            return response;
            
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    
    def disAssociate(requestData) {
        try {
            
            def user = springSecurityService.currentUser; 
            
            def projectId = user.account.uuid;
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            OSClient os = openStackAuthService.authenticate();
            
            def id = requestData.id;
            
            def currentfloatingIp = FloatingIp.findWhere(referenceId: id, deleted: false)
            
            
            def monitoringDevice = MonitoringDevice.findWhere(virtualMachine: currentfloatingIp.virtualMachine, deleted: false) 

            //Device dependency check
            if(monitoringDevice) {
                
                if(currentfloatingIp.floatingIpAddress == monitoringDevice.deviceName) {
                   
                    Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                    Object[] floatingArgs = new Object[2];
                    floatingArgs[0] = monitoringDevice.deviceName
                    item.put("message", messageSource.getMessage('user.vm.device.cannotDisAssociate', floatingArgs, new Locale(defaultLanguage.value)));
                    item.put("result", GeneralConstants.DEVICE_ENABLED);
                    response.add(item);
                } else {
                    
                    disAssociateFromPort(os, id)
                    item.put("result", GeneralConstants.RESULT_SUCCESS);
                    response.add(item)
                }
   
            } else {
                
                disAssociateFromPort(os, id)
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                response.add(item)  
            }
  
            return response;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def disAssociateFromPort(os, id) {
        try {
            
            NetFloatingIP netFloatingIP = os.networking().floatingip()
                                             .disassociateFromPort(id);
            
            def floatingIp = FloatingIp.findByReferenceId(id);
            floatingIp.referenceId = netFloatingIP.getId();
            floatingIp.fixedIpAddress = null;
            floatingIp.virtualMachine = null;
            floatingIp.portId = null;
            floatingIp.routerId = null;
            floatingIp.save(flush: true);
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def delete(requestData) {
        try {
            
            def user = springSecurityService.currentUser; 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate();
                        
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def monitoringDeviceEnabled = false;
            def monitoringDevice;
            ActionResponse actionResponse;
                        
            def floatingIp = FloatingIp.findWhere(referenceId: requestData.referenceId, deleted: false);
                
                if(floatingIp.virtualMachine) {
                    monitoringDevice = MonitoringDevice.findWhere(virtualMachine: floatingIp.virtualMachine, deleted: false) 
                    
                    if(monitoringDevice) {
                        
                        if(floatingIp.floatingIpAddress == monitoringDevice.deviceName) {
                            
                            monitoringDeviceEnabled = true;                          
                        }
                    
                    }
                    
                } else {
                    monitoringDeviceEnabled = false;
                }
               
            if(monitoringDeviceEnabled) {
                                
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                Object[] floatingArgs = new Object[2];
                floatingArgs[0] = monitoringDevice.deviceName
                item.put("message", messageSource.getMessage('user.vm.device.cannotReleaseFloatingIp', floatingArgs, new Locale(defaultLanguage.value)));
                item.put("result", GeneralConstants.DEVICE_ENABLED);
                response.add(item);
                            
            } else {
                                
                actionResponse = os.networking().floatingip().delete(requestData.referenceId);
                        
                if(actionResponse.isSuccess() == true) {


                    //Disassociate floating ip
                    floatingIp.fixedIpAddress = null;
                    floatingIp.virtualMachine = null;
                    floatingIp.portId = null;
                    floatingIp.routerId = null;

                    //Delete floating ip
                    floatingIp.deleted = true;
                    floatingIp.deletedAt = new Date();
                    floatingIp.save(flush: true);
                    
                    item.put("result", GeneralConstants.RESULT_SUCCESS);
                    response.add(item)

                } else {
                    item.put("message", actionResponse.getFault());
                    item.put("result", GeneralConstants.RESULT_FAILURE);
                    response.add(item);
                } 
                
            }
                
            return response;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
}
