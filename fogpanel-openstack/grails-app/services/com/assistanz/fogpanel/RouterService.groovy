package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.openstack.OpenStackServer;
import java.util.Arrays;
import java.util.List;
import com.assistanz.cloud.config.ConfigLoader
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.Router as OpenstackRouter;
import org.openstack4j.model.network.RouterInterface;
import org.openstack4j.model.network.AttachInterfaceType;
import org.openstack4j.model.network.Port as OpenstackPort;
import org.openstack4j.model.network.IP;
import org.openstack4j.model.compute.ActionResponse;
import com.fogpanel.openstackuserapi.HttpConnector;
import org.openstack4j.openstack.identity.domain.v3.KeystoneTokenV3;
import org.openstack4j.model.identity.v3.EndpointV3;
import org.openstack4j.model.identity.v3.Catalog;
import java.net.URL;

@Transactional
class RouterService {
    
    def springSecurityService;
    OpenStackAuthService openStackAuthService
    
    def listRouter(referenceId) {
        
       try { 
           
            def user = springSecurityService.currentUser;  
        
            ArrayList<ArrayList<String>> routers = new ArrayList<ArrayList<String>>();

            def routerList = Router.withCriteria { 
                eq('deleted', false)
                eq('user', user)
                if(referenceId) {
                    eq('referenceId', referenceId) 
                }
            }
            
            for (def router : routerList) {
            
                HashMap item = new HashMap();
                item.put("name", router.name);
                item.put("referenceId", router.referenceId);
                item.put("status", router.status);
                item.put("isAdminState", router.isAdminState);
                item.put("externalNetworkId", router.externalNetworkId)
                
                if(router.externalNetworkId) {
                    def network = Network.findByReferenceId(router.externalNetworkId)
                    item.put("externalNetworkName", network == null ? "-" : network.name)
                } else {
                    item.put("externalNetworkName", "-")
                }
                
                routers.add(item)             
            }
            
            return routers;
            
       } catch (Exception ex) {
            throw ex;
        }
        
    }

    def createRouter(requestData) {
        
        try { 
            
            def user = springSecurityService.currentUser; 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate(); 
            
            OpenstackRouter openstackRouter = os.networking().router().create(Builders.router()
                                                             .name(requestData.name)
                                                             .adminStateUp(true)
                                                             .tenantId(projectId)
                                                             .build());
                                                         
            def router = new Router();
            router.referenceId = openstackRouter.getId()
            router.name = requestData.name
            router.status = openstackRouter.getStatus()
            router.isAdminState = true;
            router.user = user;
            router.account = user.account;
            router.createdAt = new Date();
            router.save(flush: true);
            
            ArrayList<ArrayList<String>> createRouterResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createRouterResponse.add(item)
            
            return createRouterResponse;
         
            
        } catch (Exception ex) {
            throw ex;
        }
            
    }
    
    def deleteRouter(referenceId) {
        try {
            
            def user = springSecurityService.currentUser; 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate();
            
            ActionResponse actionResponse = os.networking().router().delete(referenceId);
            
            if(actionResponse.isSuccess() == true) {
                
                def router = Router.findByReferenceId(referenceId)
                router.deleted =true;
                router.deletedAt = new Date();
                router.save(flush: true);
            } else {

            }
            return actionResponse;
            
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def interfacesList(routerId) {
        
        try {
            
            def user = springSecurityService.currentUser; 
            ArrayList<ArrayList<String>> interfaceList = new ArrayList<ArrayList<String>>(); 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate();
            
            List<? extends OpenstackPort> ports = os.networking().port().list();
            
            for(OpenstackPort port : ports) {
                
                if(port.getDeviceId().equals(routerId)) {
                    
                    HashMap<String,String> item = new HashMap<String,String>(); 
                    item.put("referenceId",port.getId());
                    item.put("status",port.getState());
                    item.put("adminState",port.isAdminStateUp());
                    item.put("fixedIps",port.getFixedIps().iterator().next().getIpAddress());
                    item.put("subnetId",port.getFixedIps().iterator().next().getSubnetId());
                    item.put("name",port.getId());
                    item.put("deviceId",port.getDeviceId());
                    item.put("type",port.getDeviceOwner());
                    interfaceList.add(item);
                }
            }
            
            return interfaceList;
            
        } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    def attachInterface(requestData) {
        try {
            println("requestData : "+requestData)
            def user = springSecurityService.currentUser; 
            ArrayList<ArrayList<String>> attachInterfaceResponse = new ArrayList<ArrayList<String>>(); 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate(); 
            def ipAddress  = requestData.ipaddress;
            
            def subnet = NetworkSubnet.findByReferenceId(requestData.subnetId);
            
            if(!ipAddress.isEmpty()) {
                
                OpenstackPort interfacePort = os.networking().port().create(Builders.port()
                    .deviceId(requestData.routerId)
                    .networkId(subnet.network.referenceId)
                    .fixedIp(requestData.ipaddress,requestData.subnetId)
                    .build());
                
                
            }
            
            RouterInterface routerInterface = os.networking().router()
                                              .attachInterface(requestData.routerId, AttachInterfaceType.SUBNET,requestData.subnetId);
                                              
           println("routerInterface"+routerInterface)
            OpenstackPort port = os.networking().port().get(routerInterface.getPortId()); 
            def ips = "";
            def i=1;
            for(IP ip : port.getFixedIps()) {

                    def fixedIps = port.getFixedIps().size();

                    ips = ips + ip.getIpAddress();

                    if(fixedIps != i++) {
                        ips+= ",";
                    }

                }

            def existingPort = Port.findByReferenceId(port.getId());
            if(!existingPort) {

                def newPort = new Port();
                newPort.referenceId = port.getId();
                newPort.name = port.getName();
                newPort.status = port.getState();
                newPort.fixedIps = ips;
                newPort.macAddress = port.getMacAddress();
                newPort.user = user;
                newPort.account = user.account;
                newPort.isAdminState = port.isAdminStateUp();
                newPort.deviceId = port.getDeviceId();
                newPort.deviceOwner = port.getDeviceOwner();
                newPort.createdAt = new Date();
                newPort.save(flush: true);

            } else {
                
                if(existingPort.deviceId == port.getDeviceId()) {
                    
                } else {
                    existingPort.deviceId = port.getDeviceId();
                    existingPort.deviceOwner = port.getDeviceOwner();
                    existingPort.save(flush: true);
                }
            }    
            

            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            attachInterfaceResponse.add(item)
                           
            return attachInterfaceResponse;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def deleteInterface(requestData) {
        
        try{
            
            def user = springSecurityService.currentUser; 
            ArrayList<ArrayList<String>> deleteInterfaceResponse = new ArrayList<ArrayList<String>>(); 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate(); 
            
            RouterInterface routerInterface = os.networking().router()
            .detachInterface(requestData.routerId, requestData.subnetId, requestData.portId);
            
//            def detachedPort = Port.findByReferenceId(port.getId());
//            detachedPort.deleted = true;
//            detachedPort.deletedAt = new Date();
//            detachedPort.save(flush: true);
            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            deleteInterfaceResponse.add(item)
            
            return deleteInterfaceResponse
            
        } catch (Exception ex) {
            throw ex;
        }
        
    } 
    
    def updateRouter(requestData) {
        try {
            
            def user = springSecurityService.currentUser; 
            ArrayList<ArrayList<String>> deleteInterfaceResponse = new ArrayList<ArrayList<String>>(); 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate();
            
            OpenstackRouter router = os.networking().router().get(requestData.id);
                       
            OpenstackRouter routerGateway = os.networking().router().update(router.toBuilder().name(requestData.name).build());
            
            def updateRouter = Router.findByReferenceId(requestData.id)
            updateRouter.name = requestData.name
            updateRouter.save(flush: true);
            
            ArrayList<ArrayList<String>> updateRouterResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            updateRouterResponse.add(item)
            
            return updateRouterResponse;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def setGateWay(requestData) {
        try {
            
            def user = springSecurityService.currentUser; 
            ArrayList<ArrayList<String>> deleteInterfaceResponse = new ArrayList<ArrayList<String>>(); 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate();
            
            OpenstackRouter router = os.networking().router().get(requestData.routerId);
            
            OpenstackRouter routerGateway = os.networking().router().update(router.toBuilder().externalGateway(requestData.externalNetwork).build());
            
            println("routerGateway :" + routerGateway)
            def updateRouter = Router.findWhere(referenceId: requestData.routerId, deleted: false);
            updateRouter.externalNetworkId = requestData.externalNetwork
            updateRouter.save(flush: true);
            
            ArrayList<ArrayList<String>> gatewayRouterResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            gatewayRouterResponse.add(item)
            
            return gatewayRouterResponse;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def clearGateWay(requestData) {
        try {
            
            HttpConnector userAPIConnector = new HttpConnector()
            
            def user = springSecurityService.currentUser; 
            ArrayList<ArrayList<String>> clearGatewayResponse = new ArrayList<ArrayList<String>>(); 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate();
            
            //Get network endpoint URl
            KeystoneTokenV3 token = (KeystoneTokenV3) os.getAccess().getToken();
            URL networkEndPointUrl;
            
            for (Catalog catalog : token.getCatalog()) {
                
                if(catalog.getType().equals("network")) {
                   
                    System.out.println("Type: " + catalog.getEndpoints().iterator().next().getURL());
                    networkEndPointUrl = catalog.getEndpoints().iterator().next().getURL();
                    
                }
            }
            
            String id = requestData.routerId;
            
            //custom implementation
            def data = '{ "router": {"external_gateway_info": null}}' ;
            
            def url = networkEndPointUrl.toString() + "v2.0/routers/"+id

            def response = userAPIConnector.simplePut(new URL(url), data, os.getToken().getId())

            if(response == "Success") {
                
                def updateRouter = Router.findWhere(referenceId: id, deleted: false);
                updateRouter.externalNetworkId = null
                updateRouter.save(flush: true);
                
                HashMap<String,String> item = new HashMap<String,String>();
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                clearGatewayResponse.add(item);
            }

            return clearGatewayResponse;

        } catch (Exception ex) {
            throw ex;
        }
    }
}
