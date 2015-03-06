package com.assistanz.fogpanel

import grails.transaction.Transactional
import grails.converters.deep.JSON


class PaymentGatewaysService {

    def updatePaymentGateways(String requestBody) {
        
        int defaultGateway = 0;
        boolean enabledGateway = true;
        def requestData = JSON.parse(requestBody)
                
        System.out.println("Method Called")
        for(int i=0;i<requestData.gridData.length();i++){
            if(requestData.gridData[i].gatewayDefault == true){
                defaultGateway = defaultGateway + 1
            }
            
            if(requestData.gridData[i].gatewayDefault == true && requestData.gridData[i].gatewayStatus == "DISABLE"){
                System.out.println("Select Enabled Gateway as a default one")
                enabledGateway = false                
            }else if(requestData.gridData[i].gatewayDefault == true && requestData.gridData[i].gatewayStatus == "ENABLE"){
                enabledGateway = true
            }
        }
        
        if(enabledGateway == false){
            System.out.println("*** Select Enabled Gateway as a default one")
            ArrayList<ArrayList<String>> enableStaticResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", "Select Enabled Gateway as a default one");
            enableStaticResponse.add(item)
            return enableStaticResponse   
        }else if(defaultGateway == 0){
            System.out.println("No gateways are default")
            ArrayList<ArrayList<String>> enableStaticResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", "No gateways are default");
            enableStaticResponse.add(item)
            return enableStaticResponse    
        }else{
            try{
                for(int i=0;i<requestData.gridData.length();i++){
                    PaymentGateways gateway = PaymentGateways.findByGatewayName(requestData.gridData[i].gatewayName);
                    gateway.status = requestData.gridData[i].gatewayStatus;
                    gateway.includeExclude = requestData.gridData[i].includeExclude;
                    gateway.isDefault = requestData.gridData[i].gatewayDefault;
                    
                    System.out.println(requestData.gridData[i].gatewayName+"-"+gateway.status+"-"+gateway.includeExclude+"-"+gateway.isDefault);
                }
            }catch(Exception e){
                [ex] as JSON
            }
            ArrayList<ArrayList<String>> enableStaticResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("message", "Gateway Updated Successfully");
            enableStaticResponse.add(item)
            return enableStaticResponse 
        }
    }
    def getGateways(){   
        
        System.out.println("Service Called")
        def gatewayList = PaymentGateways.findAll();
        ArrayList<ArrayList<String>> paymentGatewayResponse = new ArrayList<ArrayList<String>>(); 
  
        
        HashMap itemResponse = new HashMap();
        itemResponse.put("isPaymentsAvailable", Payments.count())
                
        def defaultGateway;
                
        if(PaymentGateways.findByIsDefault(true) != null){
            defaultGateway = PaymentGateways.findByIsDefault(true);
            itemResponse.put("defaultGWName",defaultGateway.gatewayName)
            itemResponse.put("defaultGWType",defaultGateway.gatewayType)
        }else{
            itemResponse.put("defaultGWName","none")
            itemResponse.put("defaultGWType","none")
        }    
                
        ArrayList<ArrayList<String>> paymentGatewayList = new ArrayList<ArrayList<String>>(); 
        
 
        if(gatewayList){
                    
            for(def gateway:gatewayList){
                System.out.println("**"+gateway.gatewayURL)
                HashMap item = new HashMap();
                item.put("gatewayName", gateway.gatewayName)
                item.put("gatewayType", gateway.gatewayType)
                item.put("status", gateway.status)
                item.put("isDefault", gateway.isDefault)
                item.put("includeExclude", gateway.includeExclude)
                item.put("processingFeePercent", gateway.processingFeePercent)
                item.put("processingFeeAmount", gateway.processingFeeAmount)
                item.put("gatewayUrl",gateway.gatewayURL);
                paymentGatewayList.add(item)
            }
        }
        itemResponse.put("paymentsGateways", paymentGatewayList)
        paymentGatewayResponse.add(itemResponse)
        Console.print(paymentGatewayResponse)   
     
       
        return paymentGatewayResponse;
    }
}
