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

    def list(referenceId) {
        
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
                item.put("network", floatingIp.externalNetwork);
                item.put("referenceId", floatingIp.referenceId);
                item.put("instance", floatingIp.virtualMachine);
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
            floatingIp.save(flush: true);
                        
            def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
            def cost = MiscellaneousOffer.get(6)?.cost
                        
            if(!invoice) {
                invoice = invoiceService.createDraftInvoice(user.account)             
            }            
            def invoiceItem = invoiceService.addInvoiceItem(invoice, BillableItem.get(14), "hourly", floatingIp.floatingIpAddress, floatingIp.referenceId, null, cost, null, null)    
            
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
            
//            if(requestData.instanceId) {
//                def instance =  VirtualMachine.findByReferenceId(requestData.id)
//                def selctedFloatingIp = FloatingIp.findByVirtualMachine(instance);
//                id = selctedFloatingIp.referenceId;
//            } else {
//                id = requestData.id;
//            }

            VirtualMachine virtualMachine = VirtualMachine.findWhere(referenceId: requestData.instanceId, deleted: false);
            
            def currentfloatingIp = FloatingIp.findWhere(referenceId: id, deleted: false)
            
            
            def monitoringDevice = MonitoringDevice.findWhere(virtualMachine: virtualMachine, deleted: false) 
            
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
    
    def delete(referenceId) {
        try {
            
            def user = springSecurityService.currentUser; 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate();
            
            ActionResponse actionResponse = os.networking().floatingip().delete(referenceId);
                        
            if(actionResponse.isSuccess() == true) {
                
                def floatingIp = FloatingIp.findByReferenceId(referenceId);
                floatingIp.deleted = true;
                floatingIp.deletedAt = new Date();
                floatingIp.save(flush: true);
                
            } else {
                             
            } 
            return actionResponse;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
