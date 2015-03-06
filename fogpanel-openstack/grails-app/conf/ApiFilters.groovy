
import com.assistanz.fogpanel.ApiUser
import com.assistanz.fogpanel.UserRole
import com.assistanz.fogpanel.ValueMissingException
import grails.converters.deep.JSON
import grails.plugin.springsecurity.SpringSecurityService;
import java.security.MessageDigest
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.converters.deep.JSON

/**
 *
 * @author Sujai
 */
class ApiFilters {
    
    def springSecurityService;
    def grailsApplication = ApplicationHolder.application
     
    def filters = {
            
        apiURIs(uri:'/api/**') {
            before = {
                def publicData = request.forwardURI.toString().replace(request.getContextPath()+"/api/public/", "")
                if (publicData == "country" || publicData == "accountValidate" || 
                    publicData == "promotionValidate" || publicData == "state" || 
                    publicData == "captcha" || publicData == "accountSignup" || publicData == "termsAndCondition" ||
                    publicData == "forgotPassword/sendEmail" || publicData == "forgotPassword/resetPassword" || 
                    publicData == "resendconfirmMail")  {
                     
                } else {
                    
                    def user = springSecurityService.currentUser
                    if(!user) {
                        def apiData = request.forwardURI.toString().replace(request.getContextPath()+"/api/admin/", "")
                                                

                        if(apiData == "billing/listInvoice" || apiData == "billing/currentUsage" || 
                            apiData == "billing/listPayment" ||  apiData == "billing/addPayment" || 
                            apiData == "account/createAccount" || apiData == "serviceOffering/listComputingOffer" || 
                            apiData == "template/listTemplate" || apiData == "firewall/listFirewall" || 
                            apiData == "virtualMachine/createVM" || apiData == "staticData/listCountry" || apiData == "staticData/listState" || 
                            apiData == "virtualMachine/getVM" || apiData == "staticData/listBillableItem" ||apiData == "account/listAccount") {  

//                            if(request.getMethod()== "POST") {
//                                makeAPIPostRequest(request, owner, response, params)
//                            } else {
                               makeAPIRequest(request, owner, response, params) 
//                            } 
                                     
                        } else {
                            //return [[]] as JSON
                            doResponse(owner, request, response, params, 401,'{error: "security.authenticationFailed"}'); 
                        }
                        
                    } else {
                        def apiData = request.forwardURI.toString().replace(request.getContextPath()+"/api/admin/", "")                                                

                        if(apiData == "billing/listInvoice" || apiData == "billing/currentUsage" || 
                            apiData == "billing/listPayment" ||  apiData == "billing/addPayment" || 
                            apiData == "account/createAccount" || apiData == "serviceOffering/listComputingOffer" ||
                            apiData == "template/listTemplate" || apiData == "firewall/listFirewall" || apiData == "virtualMachine/createVM" ||
                            apiData == "public/listCountry" || apiData == "public/listState" || apiData == "virtualMachine/getVM" || 
                            apiData == "public/listBillableItem" || apiData == "account/listAccount") {  

//                            if(request.getMethod()== "POST") {
//                                makeAPIPostRequest(request, owner, response, params)
//                            } else {
                               makeAPIRequest(request, owner, response, params) 
//                            } 
                                                       
                                                        
                        } else {
                            if(request.getHeader("X-FogPayment") == "Payment") {
                                
                            } else {
                                if(user.account.status.name() == "DISABLED") {
                                    doResponse(owner, request, response, params, 402,'{error: "account.paymentRequired"}');
                                }
                            }
                        }
                       
                        //check account
                        // if account check status
                        // if status is not valid
                        // 
                    }
                 }
            }
            after = {
                // do some postprocessing
            }
            
        }   
    }	
    
    boolean doResponse(webFilter, request, response, params, statusCode, text) { 
        // Do Stuff
        webFilter.render(status: statusCode, contentType: 
        "application/json", encoding: "UTF-8", text: text)
        // Do post-render stuff
        return false
    }
    
    def makeAPIPostRequest(request, owner, response, params) {
        
      try {      
        def currentUser = springSecurityService.currentUser       
//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder();
//        
//        String line;
//                
//        br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        while ((line = br.readLine()) != null) {
//                sb.append(line);
//        }
//        
//        Console.print("string read" + sb.toString());
            
        List keys = new LinkedList(request.getParameterMap().keySet());
        Collections.sort(keys);
     
        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map sortedMap = new LinkedHashMap();
        for(def key: keys){
            sortedMap.put(key, request.getParameterMap().get(key));
        }
        
        def requestBody = sortedMap
       
        String dataString  = "";
        String apiKey;
        String signature;
        
        for (String key : requestBody.keySet()) {               
            
            if (key != "apiKey" && key != "signature") {
                if(requestBody.get(key)[0] == "" || requestBody.get(key)[0] == null || requestBody.get(key)[0] == "null") {                    
                    throw new ValueMissingException("{'errorCode':'2000' ,'message':'Field "+ key +" value missing'}");
                    } else {
                    dataString += key+"="+requestBody.get(key)[0]+"&" 
                }                
                
            } else if(key == "apiKey") {
                apiKey = requestBody.get(key)[0]
            } else if(key == "signature") {
                signature = requestBody.get(key)[0]
            }
        }
                
        def apiUser = ApiUser.findWhere(apiKey: apiKey, enabled:true) 
                
        if(apiUser) { 
            
            def user = apiUser.user
            def role = UserRole.findByUser(user).role

            if(role.authority == "ROLE_ADMIN") {
                def apiString = dataString                                                                                                                           
                def base64 = apiString.bytes.encodeBase64().toString()+apiUser.secret                                                                                                
                MessageDigest md = MessageDigest.getInstance("MD5"); 
                byte[] messageDigest = md.digest(base64.getBytes());
                BigInteger number = new BigInteger(1, messageDigest);
                String hashtext = number.toString(16);
                // Now we need to zero pad it if you actually want the full 32 chars.
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }

                def md5 = hashtext.toString();

                if(md5 == signature) {

                } else {
                    doResponse(owner, request, response, params, 400,'{errorCode: "3200", message: "Invalid Signature"}'); 
                } 
            } else {
                doResponse(owner, request, response, params, 401,'{errorCode: "3000", message: "security AuthenticationFailed"}'); 
            }
            
        } else {
            doResponse(owner, request, response, params, 401,'{errorCode: "3100", message: "The API Key used is not valid or it was disabled"}'); 
        }
            
        } catch (Exception ex) {
            doResponse(owner, request, response, params, 400, ex.message); 
        } catch (RuntimeException ex) {
            doResponse(owner, request, response, params, 400, ex.message); 
        } 
    }
    
    def makeAPIRequest(request, owner, response, params) {
        try {
       
        def queryParam = request.getQueryString(); 
        String dataString  = "";
        String apiKey;
        String signature;

        def paramList = queryParam.tokenize('&')
        ArrayList errorResponse = new ArrayList();
                    
        for(def param : paramList) {

            def paramVal = param.tokenize('=')
                                    
            if (paramVal[0] != "apiKey" && paramVal[0] != "signature") {
                if(paramVal[1] == "" || paramVal[1] == null || paramVal[1] == "null") {
                    for(def param2 : paramList) {
                        def paramVal2 =  param2.tokenize('=')
                        
                        if(paramVal2[1] == "" || paramVal2[1] == null || paramVal2[1] == "null") {
                             errorResponse.add("{'errorCode':'3200' ,'message':'Field "+ paramVal2[0] +" value missing'}")
                        }
                    }
                    def jasonErrorResult = errorResponse as JSON
                    throw new ValueMissingException(jasonErrorResult.toString());
                } else {
                    dataString += dataString.size() == 0 ? dataString : "&";
                    dataString += param
                }
            } else if(paramVal[0] == "apiKey") {
                apiKey = paramVal[1]
            } else if(paramVal[0] == "signature") {
                signature = paramVal[1]
            }
        }
        
        def apiUser = ApiUser.findWhere(apiKey: apiKey, enabled:true) 
        if(apiUser) {

            def user = apiUser.user
            def role = UserRole.findByUser(user).role

            if(role.authority == "ROLE_ADMIN") {
                def apiString = dataString                                                                                                                            
                def base64 = apiString.bytes.encodeBase64().toString()+apiUser.secret                                                                                                           
                MessageDigest md = MessageDigest.getInstance("MD5"); 
                byte[] messageDigest = md.digest(base64.getBytes());
                BigInteger number = new BigInteger(1, messageDigest);
                String hashtext = number.toString(16);
                // Now we need to zero pad it if you actually want the full 32 chars.
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
                def md5 = hashtext.toString();

                if(md5 == signature) {

                } else {
                    doResponse(owner, request, response, params, 400,'{errorCode: "3200", message: "Invalid Signature"}'); 
                } 
            } else {
                doResponse(owner, request, response, params, 401,'{errorCode: "3000", message: "Security AuthenticationFailed"}'); 
            }
            
            
        } else {
            doResponse(owner, request, response, params, 401,'{errorCode: "3100", message: "The API Key used is not valid or it was disabled"}'); 
        }
        
        } catch (Exception ex) {
            doResponse(owner, request, response, params, 400, ex.message); 
        } catch (RuntimeException ex) {
            doResponse(owner, request, response, params, 400, ex.message); 
        } 
        
    }
}

