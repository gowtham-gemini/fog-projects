package com.assistanz.fogpanel

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.springframework.web.bind.annotation.RequestBody;
import javax.ws.rs.GET
import javax.ws.rs.POST;
import javax.ws.rs.Path
import javax.ws.rs.PUT
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.QueryParam
import grails.converters.deep.JSON
import com.assistanz.cloud.cloudstack.accounts.CSAccountService;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.accounts.CreateAccountResponse;
import com.assistanz.fogpanel.AccountService;
import com.assistanz.cloud.cloudstack.ssh.CSSSHService
import com.assistanz.cloud.cloudstack.virtualmachine.CSVirtualMachineService
import org.codehaus.groovy.grails.commons.ApplicationHolder

@Path('/api/network')
class NetworkResource {
    
    def networkService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getNetworkRepresentation(@QueryParam("zoneReferenceId") String zoneReferenceId) {
        try {
            networkService.listNetwork(zoneReferenceId) as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/tier/list")
    @Produces(MediaType.APPLICATION_JSON)
    def getVPCTier(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("vpcId") String vpcId) {
        try {            
            networkService.getVPCTier(zoneReferenceId, vpcId) as JSON   
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/overall/listIP")
    @Produces(MediaType.APPLICATION_JSON)
    def listAllIps(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("vpcId") String vpcId, @QueryParam("isStatic") String isStatic) {
        try {            
            networkService.listAllIps(zoneReferenceId, vpcId, isStatic) as JSON   
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/overall/listLB")
    @Produces(MediaType.APPLICATION_JSON)
    def listAllLBs(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("vpcId") String vpcId, @QueryParam("scheme") String scheme) {
        try {            
            networkService.listAllLBs(zoneReferenceId, vpcId, scheme) as JSON   
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/overall/listPublicLB")
    @Produces(MediaType.APPLICATION_JSON)
    def listAllLBs(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("vpcId") String vpcId) {
        try {            
            networkService.listPublicLB(zoneReferenceId, vpcId) as JSON   
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/overall/vmList")
    @Produces(MediaType.APPLICATION_JSON)
    def listAllVM(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("vpcId") String vpcId) {
        try {            
            networkService.listAllVM(zoneReferenceId, vpcId) as JSON   
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/count/")
    @Produces(MediaType.APPLICATION_JSON)
    def networkTypeCount(@QueryParam("zoneReferenceId") String zoneReferenceId) {
        try {
            networkService.networkTypeCount(zoneReferenceId) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    
    
    @GET
    @Path("/vm/list/")
    @Produces(MediaType.APPLICATION_JSON)
    def getNetworkVmList(@QueryParam("networkId") String networkId) {
        try {
            networkService.getNetworkVmList(networkId) as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/loadBalancing/vm/list/")
    @Produces(MediaType.APPLICATION_JSON)
    def getLoadBalancerVmList(@QueryParam("loadBalancingId") String loadBalancingId) {
        try {
            networkService.getLoadBalancerVmList(loadBalancingId) as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/nic/ip/list/")
    @Produces(MediaType.APPLICATION_JSON)
    def getNetworkNicIPList(@QueryParam("networkId") String networkId, @QueryParam("vmId") String vmId) {
        try {
            networkService.getNetworkNicIPList(networkId, vmId) as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@PathParam("id") String id) {
        networkService.getNetwork(id) as JSON
    }
    
    @GET
    @Path("/byVM")
    @Produces(MediaType.APPLICATION_JSON)
    def listNetworkByVM(@QueryParam("vmReferenceId") String vmReferenceId) {
        try {                                
            networkService.listNetworkByVM(vmReferenceId) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def updateNetwork(@RequestBody String requestBody) {
        networkService.updateNetwork(requestBody) as JSON
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {                                
            networkService.createNetwork(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @POST
    @Path("/loadBalancing/add")
    @Produces(MediaType.APPLICATION_JSON)
    def addLoadBalancingRuleForIp(@RequestBody String requestBody) {
        try {                                
            networkService.addLoadBalancingRuleForIp(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/internal/loadBalancing/get/")
    @Produces(MediaType.APPLICATION_JSON)
    def getInternalLoadBalancerAttachedVMList(@QueryParam("loadBalancingId") String loadBalancingId, 
        @QueryParam("networkId") String networkId) {
        networkService.getInternalLoadBalancerList(loadBalancingId, networkId) as JSON
    }
    
    @POST
    @Path("/internal/loadBalancing/add")
    @Produces(MediaType.APPLICATION_JSON)
    def addInternalLoadBalancer(@RequestBody String requestBody) {
        try {         
            def requestData = JSON.parse(requestBody)
            networkService.addInternalLoadBalancer(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/internal/loadBalancing/addVM")
    @Produces(MediaType.APPLICATION_JSON)
    def addVMToInternalLoadBalancerRule(@RequestBody String requestBody) {
        try {      
             def requestData = JSON.parse(requestBody)
            networkService.addVMToInternalLoadBalancerRule(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/internal/loadBalancing/remove/")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteInternalLoadBalancingRule(@RequestBody String requestBody) {
        try {
            networkService.deleteInternalLoadBalancingRule(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }           
    }
    
    @POST
    @Path("/internal/loadBalancing/delete/job")
    @Produces(MediaType.APPLICATION_JSON)
    def getInternalLoadBalancingDeleteJobStatus(@RequestBody String requestBody) {
        
        try {                                
            def requestData = JSON.parse(requestBody)
            networkService.getInternalLoadBalancingDeleteJobStatus(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/internal/loadBalancing/job")
    @Produces(MediaType.APPLICATION_JSON)
    def internalLoadBalancerJob(@RequestBody String requestBody) {
        try {                                
            def requestData = JSON.parse(requestBody)
            networkService.internalLoadBalancerJob(requestData.jobId) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/loadBalancing/getVM")
    @Produces(MediaType.APPLICATION_JSON)
    def getLoadBalancerAttachedVMList(@QueryParam("loadBalancingId") String loadBalancingId) {
        networkService.getLoadBalancerAttachedVMList(loadBalancingId) as JSON
    }
    
    @POST
    @Path("/loadBalancing/addVM")
    @Produces(MediaType.APPLICATION_JSON)
    def addVMToLoadBalancerRule(@RequestBody String requestBody) {
        try {                                
            networkService.addVMToLoadBalancerRule(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/loadBalancing/addVM/job")
    @Produces(MediaType.APPLICATION_JSON)
    def addVMToLoadBalancerRuleJob(@RequestBody String requestBody) {
        try {                                
            networkService.addVMToLoadBalancerRuleJob(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    @POST
    @Path("/loadBalancing/removeVM")
    @Produces(MediaType.APPLICATION_JSON)
    def removeVMFromLoadBalancerRule(@RequestBody String requestBody) {
        try {                                
            networkService.removeVMFromLoadBalancerRule(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/loadBalancing/removeVM/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def removeVMFromLoadBalancerJob(@RequestBody String requestBody) {
        try {                                
            networkService.removeVMFromLoadBalancerJob(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    
    @POST
    @Path("/portForwarding/add")
    @Produces(MediaType.APPLICATION_JSON)
    def addPortForwardingRuleForIp(@RequestBody String requestBody) {
        try {                                
            networkService.addPortForwardingRuleForIp(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/portForwarding/remove/")
    @Produces(MediaType.APPLICATION_JSON)
    def deletePortForwardingRule(@RequestBody String requestBody) {
        try {
            networkService.deletePortForwardingRule(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }           
    }
    
    @GET
    @Path("/portForwarding/job/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getPortForwardingJob(@PathParam("id") String id) {
        networkService.getPortForwardingJob(id) as JSON
    }
    
    @GET
    @Path("/portForwarding/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def listPortForwardingRules(@PathParam("id") String id) {
        networkService.listPortForwardingRules(id) as JSON
    }
    
    
    @PUT
    @Path("/loadBalancing/edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def editLoadBalancingRule(@RequestBody String requestBody) {
        try {
            networkService.editLoadBalancingRule(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }           
    }
    
    @POST
    @Path("/loadBalancing/remove/")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteLoadBalancingRule(@RequestBody String requestBody) {
        try {
            networkService.deleteLoadBalancingRule(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }           
    }
    
    
    @GET
    @Path("/loadBalancing/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def listLoadBalancingRules(@PathParam("id") String id) {
        networkService.listLoadBalancingRules(id) as JSON
    }
    
    @GET
    @Path("/loadBalancing/job/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getLoadBalancingJob(@PathParam("id") String id) {
        networkService.getLoadBalancingJob(id) as JSON
    }
    
    @POST
    @Path("/loadBalancing/delete/job")
    @Produces(MediaType.APPLICATION_JSON)
    def getLoadBalancingDeleteJobStatus(@RequestBody String requestBody) {
        networkService.getLoadBalancingDeleteJobStatus(requestBody) as JSON
    }
    
    
    @POST
    @Path("/portForwarding/delete/job") 
    @Produces(MediaType.APPLICATION_JSON)
    def getPortForwardingDeleteJobStatus(@RequestBody String requestBody) {
        networkService.getPortForwardingDeleteJobStatus(requestBody) as JSON
    }
    
    
    @POST
    @Path("/egress/add")
    @Produces(MediaType.APPLICATION_JSON)
    def createNetworkEgressRule(@RequestBody String requestBody) {
        try {                                
            networkService.addEgressRule(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    
    @POST
    @Path("/ip/enableStaticNat")
    @Produces(MediaType.APPLICATION_JSON)
    def enableStaticNat(@RequestBody String requestBody) {
        try {                                
            networkService.enableStaticNat(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    
    @POST
    @Path("/ip/enableVpn")
    @Produces(MediaType.APPLICATION_JSON)
    def enableVpn(@RequestBody String requestBody) {
        try {                                
            networkService.enableVpn(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @POST
    @Path("/ip/disableVpn")
    @Produces(MediaType.APPLICATION_JSON)
    def disableVpn(@RequestBody String requestBody) {
        try {                                
            networkService.disableVpn(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    
    
    @POST
    @Path("/ip/disableStaticNat")
    @Produces(MediaType.APPLICATION_JSON)
    def disableStaticNat(@RequestBody String requestBody) {
        try {                                
            networkService.disableStaticNat(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    
    @GET
    @Path("/ip/enableStaticNat/job/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getEnableStaticNatJob(@PathParam("id") String id) {
        networkService.getEnableStaticNatJob(id) as JSON
    }
    
    @GET
    @Path("/ip/disableStaticNat/job/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getDisableStaticNatJob(@PathParam("id") String id) {
        networkService.getDisableStaticNatJob(id) as JSON
    }
    
    
    @POST
    @Path("/ip/firewall/add")
    @Produces(MediaType.APPLICATION_JSON)
    def addFirewallForIp(@RequestBody String requestBody) {
        try {                                
            networkService.addFirewallForIp(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @GET
    @Path("/ip/firewall/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getFirewallRulesForIp(@PathParam("id") String id) {        
        networkService.getFirewallRulesForIp(id) as JSON
    }
    
    @GET
    @Path("/egress/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getNetworkEgressRules(@PathParam("id") String id) {        
        networkService.getNetworkEgressRules(id) as JSON
    }             
    
    @GET
    @Path("/ip/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getNetworkIpAddressesList(@PathParam("id") String id) {        
        networkService.getNetworkIpAddressesList(id) as JSON
    }
    
    @GET
    @Path("/ip/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getIpAddressesDetails(@PathParam("id") String id) {        
        networkService.getIpAddressesDetails(id) as JSON
    }
    
    
    @POST
    @Path("/ip/acquire")
    @Produces(MediaType.APPLICATION_JSON)
    def acquireIpForNetwork(@RequestBody String requestBody) {
        try {                                
            networkService.acquireIpForNetwork(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    
    @POST
    @Path("/ip/release")
    @Produces(MediaType.APPLICATION_JSON)
    def releaseIpForNetwork(@RequestBody String requestBody) {
        try {                                
            networkService.releaseIpForNetwork(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @GET
    @Path("/ip/release/job/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def releaseIpJobForNetwork(@PathParam("id") String id) {
        try {                                
            networkService.releaseIpJobForNetwork(id) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    
    @POST
    @Path("/ip/job")
    @Produces(MediaType.APPLICATION_JSON)
    def acquireIpJobForNetwork(@RequestBody String requestBody) {
        try {                                
            networkService.acquireIpJobForNetwork(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/egress/job/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getNetworkEgressJob(@PathParam("id") String id) {
        networkService.job(id) as JSON
    }
    
    @GET
    @Path("/firewall/get/job/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getFirewallJob(@PathParam("id") String id) {
        networkService.job(id) as JSON
    }
    
    @DELETE
    @Path("/firewall/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteIPFirewallRule(@PathParam("id") String id) {
        try {
            networkService.deleteIPFirewallRule(id) 
            "OK"
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }           
    }
    
    
    @POST
    @Path("/delete/")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteNetwork(@RequestBody String requestBody) {
        try {
            networkService.deleteNetwork(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }           
    }
    
    @POST
    @Path("/delete/get/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def getNetworkDeleteJob(@RequestBody String requestBody) {
        networkService.networkDeletejob(requestBody) as JSON
    }
    
    @DELETE
    @Path("/egress/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteEgressRule(@PathParam("id") String id) {
        try {
            networkService.deleteEgressRule(id) 
            "OK"
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }           
    }
    
}
