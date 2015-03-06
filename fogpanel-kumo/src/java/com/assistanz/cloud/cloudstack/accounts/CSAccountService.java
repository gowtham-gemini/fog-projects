package com.assistanz.cloud.cloudstack.accounts;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer; 
import com.assistanz.cloud.cloudstack.UserResponse;

/**
 *
 * @author Gowtham
 */
public class CSAccountService {
  
    private CloudStackServer server;
    
    public CSAccountService(CloudStackServer server) {
        this.server = server;
    }
    
   /**
    * Creates an account
    * 
    * @param accounttype Type of the account. Specify 0 for user, 1 for root admin, and 2 for domain admin
    * @param emailId email
    * @param firstname first name
    * @param lastname last name
    * @param username Unique username
    * @param password Hashed password (Default is MD5). If you wish to use any other hashing algorithm, you would need to write a custom authentication adapter See Docs section.Hashed password (Default is MD5). If you wish to use any other hashing algorithm, you would need to write a custom authentication adapter See Docs section.
    * @param optional
    * @return
    * @throws Exception 
    */
    public CreateAccountResponse createAccount(String accountType, String emailId, 
            String firstName, String lastName, String userName, String password,
            HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createAccount", optional);
        arguments.add(new NameValuePair("accounttype", accountType));
        arguments.add(new NameValuePair("email", emailId));
        arguments.add(new NameValuePair("firstname", firstName));
        arguments.add(new NameValuePair("lastname", lastName));
        arguments.add(new NameValuePair("username", userName));
        arguments.add(new NameValuePair("password", password));
        
        Document responseDocument = server.makeRequest(arguments);

        return getCreateAccountResponse(responseDocument);
    }
  
    /**
     * Converts XML document into CreateAccountResponse object
     * 
     * @param doc
     * @return 
     */
    private CreateAccountResponse getCreateAccountResponse(Document doc) {
        CreateAccountResponse response = new CreateAccountResponse();
        
        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserId(node.getTextContent());
        }
        
        // get username from XML and set as user Name
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserName(node.getTextContent());
        }
        
        // get account from XML and set as user account name
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }
        
        // get accountid from XML and set as user account ID
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }
        
         // get accounttype from XML and set as user account type
        list = doc.getElementsByTagName("accounttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getTextContent());
        }
        
         // get apikey from XML and set as user APIkey
        list = doc.getElementsByTagName("apikey");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setApiKey(node.getTextContent());
        }
        
        // get created from XML and set as user Created time and date
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }
        
        // get domain from XML and set as user DomainName
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }
        
         // get domainid from XML and set as user Domain ID
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }
        
        // get email from XML and set as user email ID
        list = doc.getElementsByTagName("email");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEmailId(node.getTextContent());
        }
        
        // get firstname from XML and set as user First Name
        list = doc.getElementsByTagName("firstname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirstName(node.getTextContent());
        }
        
        // get lastname from XML and set as user Last Name
        list = doc.getElementsByTagName("lastname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLastName(node.getTextContent());
        }
        
        // get secretkey from XML and set as user Secret key
        list = doc.getElementsByTagName("secretkey");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecretKey(node.getTextContent());
        }
        
        // get state from XML and set as user state
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }
        
        // get timezone from XML and set as user Time Zone
        list = doc.getElementsByTagName("timezone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTimeZone(node.getTextContent());
        }
        
       return response;
    }
    
    /**
     * Deletes a account, and all users associated with this account
     * 
     * @param accountId Account id
     * @return 
     */
    public DeleteAccountResponse deleteAccount(String accountId) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteAccount", null);
        arguments.add(new NameValuePair("id",accountId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getDeleteAccountResponse(responseDocument);
        
    }
    
    /**
      * Converts XML document into DeleteAccountResponse object
      * 
      * @param doc
      * @return 
      */
    private DeleteAccountResponse getDeleteAccountResponse(Document doc) {
        DeleteAccountResponse response = new DeleteAccountResponse();
        
        // get displaytext from XML and set Any text associated with the success or 
        // failure on Deleting a account
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }
        
        // get success from XML and set Return true if Delete Account operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }
        
         return response;
     }
               
    /**
     * Updates account information for the authenticated user
     * 
     * @param newName new name for the account
     * @param optional
     * @return
     * @throws Exception 
     */
    public UpdateAccountResponse updateAccount(String newName, 
            HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateAccount", optional);
        arguments.add(new NameValuePair("newname",newName));
        
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateAccountResponse(responseDocument);
        
    }
    
    /**
     * Converts XML document into UpdateAccountResponse object
     * 
     * @param doc
     * @return 
     */
    private UpdateAccountResponse getUpdateAccountResponse(Document doc) {
        UpdateAccountResponse response = new UpdateAccountResponse();
        
        NodeList list = doc.getElementsByTagName("user");
        if (list.getLength() > 0) {
            List<UserResponse> users = new LinkedList<UserResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node userNode = list.item(index);
                UserResponse user = new UserResponse();
                
                
                NodeList userProperties = userNode.getChildNodes();
                for (int childIndex = 0; childIndex < userProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                        user.setUserId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        user.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        user.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        user.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("apikey")) {
                        user.setApiKey(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        user.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        user.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        user.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        user.setEmailId(property.getTextContent());
                    } else if (property.getNodeName().equals("firstname")) {
                        user.setFirstName(property.getTextContent());
                    } else if (property.getNodeName().equals("lastname")) {
                        user.setLastName(property.getTextContent());
                    } else if (property.getNodeName().equals("secretkey")) {
                        user.setSecretKey(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        user.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("timezone")) {
                        user.setTimeZone(property.getTextContent());
                    } else if (property.getNodeName().equals("username")) {
                        user.setUserName(property.getTextContent());
                    }                                         
                    
                }
                
                users.add(user);
                response.setUsers(users);
            }
        }        
        
        // get Id from XML and set Id for the account      
        list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }
        
        // get accountdetails from XML and set the details for the account
        list = doc.getElementsByTagName("accountdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountDetails(node.getTextContent());
        }
        
        // get accounttype from XML and set the type for the account
        list = doc.getElementsByTagName("accounttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getTextContent());
        }
        
        // // get accountdomainname from XML and set the domain name for the account 
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }
        
        // get accounttype from XML and set the domain Id for the account
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }
        
        //get accounttype from XML and set the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }
        
        // get accounttype from XML and set true if the account requires cleanup
        list = doc.getElementsByTagName("iscleanuprequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCleanupRequired(node.getTextContent());
        }
        
        // get accounttype from XML and set the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }
        
        // get accounttype from XML and set the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of network traffic bytes received
        list = doc.getElementsByTagName("receivedbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReceivedBytes(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of network traffic bytes sent
        list = doc.getElementsByTagName("sentbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSentBytes(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }
        
        //get accounttype from XML and set the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }
        
        // get accounttype from XML and set the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountState(node.getTextContent());
        }
        
        
        // get accounttype from XML and set the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of virtual machines available for this account 
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }
        
        // get vmrunning from XML and set the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }
        
        // get vmstopped from XML and set the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }
        
        // get vmtotal from XML and set the total number virtual machines  for this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }
        
        // get volumeavailable from XML and set the total number volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }
        
        // get volumelimit from XML and set the volume limit for this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }
        
        // get volumetotal from XML and set the total volume for this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }
        
        return response;
    }
    
    /**
     * Disables an account
     * 
     * @param lock If true, only lock the account; else disable the account
     * @param optional
     * @return
     * @throws Exception 
     */
    public DisableAccountResponse disableAccount(String lock,  
            HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("disableAccount", optional);
        arguments.add(new NameValuePair("lock",lock));
        
        Document responseDocument = server.makeRequest(arguments);

        return getDisableAccountResponse(responseDocument);
    }
     
    /**
     * Converts XML document into DisableAccountResponse object
     * 
     * @param doc
     * @return 
     */
    private DisableAccountResponse getDisableAccountResponse(Document doc) {
        DisableAccountResponse response = new DisableAccountResponse();
        
        NodeList list = doc.getElementsByTagName("user");
        if (list.getLength() > 0) {
            List<UserResponse> users = new LinkedList<UserResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node userNode = list.item(index);
                UserResponse user = new UserResponse();
                
                
                NodeList userProperties = userNode.getChildNodes();
                for (int childIndex = 0; childIndex < userProperties.getLength(); childIndex++) {
                    Node property = userProperties.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                        user.setUserId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        user.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        user.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        user.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("apikey")) {
                        user.setApiKey(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        user.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        user.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        user.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        user.setEmailId(property.getTextContent());
                    } else if (property.getNodeName().equals("firstname")) {
                        user.setFirstName(property.getTextContent());
                    } else if (property.getNodeName().equals("lastname")) {
                        user.setLastName(property.getTextContent());
                    } else if (property.getNodeName().equals("secretkey")) {
                        user.setSecretKey(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        user.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("timezone")) {
                        user.setTimeZone(property.getTextContent());
                    } else if (property.getNodeName().equals("username")) {
                        user.setUserName(property.getTextContent());
                    }                                         
                    
                }
                
                users.add(user);
                response.setUsers(users);
            }
        }        
        
        // get accountId from XML and set Id for the account      
        list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }
        
        // get accountdetails from XML and set the details for the account
        list = doc.getElementsByTagName("accountdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountDetails(node.getTextContent());
        }
        
        // get accounttype from XML and set the type for the account
        list = doc.getElementsByTagName("accounttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getTextContent());
        }
        
        // // get accountdomainname from XML and set the domain name for the account 
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }
        
        // get accounttype from XML and set the domain Id for the account
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }
        
        //get accounttype from XML and set the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }
        
        // get accounttype from XML and set true if the account requires cleanup
        list = doc.getElementsByTagName("iscleanuprequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCleanupRequired(node.getTextContent());
        }
        
        // get accounttype from XML and set the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }
        
        // get accounttype from XML and set the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of network traffic bytes received
        list = doc.getElementsByTagName("receivedbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReceivedBytes(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of network traffic bytes sent
        list = doc.getElementsByTagName("sentbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSentBytes(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }
        
        //get accounttype from XML and set the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }
        
        // get accounttype from XML and set the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountState(node.getTextContent());
        }
        
        
        // get accounttype from XML and set the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of virtual machines available for this account 
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }
        
        // get vmrunning from XML and set the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }
        
        // get vmstopped from XML and set the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }
        
        // get vmtotal from XML and set the total number virtual machines  for this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }
        
        // get volumeavailable from XML and set the total number volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }
        
        // get volumelimit from XML and set the volume limit for this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }
        
        // get volumetotal from XML and set the total volume for this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }
        
        return response;
    }
      /**
       * Enables an account
       * 
       * @param optional
       * @return
       * @throws Exception 
       */
      public EnableAccountResponse enableAccount( 
            HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("enableAccount", optional);
        
        Document responseDocument = server.makeRequest(arguments);

        return getEnableAccountResponse(responseDocument);
    }
      
    /**
     * Converts XML document into EnableAccountResponse object
     *      
     * @param doc
     * @return 
     */
    private EnableAccountResponse getEnableAccountResponse(Document doc) {
        EnableAccountResponse response = new EnableAccountResponse();
        
        NodeList list = doc.getElementsByTagName("user");
        if (list.getLength() > 0) {
            List<UserResponse> users = new LinkedList<UserResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node userNode = list.item(index);
                UserResponse user = new UserResponse();
                
                
                NodeList userProperties = userNode.getChildNodes();
                for (int childIndex = 0; childIndex < userProperties.getLength(); childIndex++) {
                    Node property = userProperties.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                        user.setUserId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        user.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        user.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        user.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("apikey")) {
                        user.setApiKey(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        user.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        user.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        user.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        user.setEmailId(property.getTextContent());
                    } else if (property.getNodeName().equals("firstname")) {
                        user.setFirstName(property.getTextContent());
                    } else if (property.getNodeName().equals("lastname")) {
                        user.setLastName(property.getTextContent());
                    } else if (property.getNodeName().equals("secretkey")) {
                        user.setSecretKey(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        user.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("timezone")) {
                        user.setTimeZone(property.getTextContent());
                    } else if (property.getNodeName().equals("username")) {
                        user.setUserName(property.getTextContent());
                    }                                         
                    
                }
                
                users.add(user);
                response.setUsers(users);
            }
        }        
        
        // get accountId from XML and set Id for the account      
        list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }
        
        // get accountdetails from XML and set the details for the account
        list = doc.getElementsByTagName("accountdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountDetails(node.getTextContent());
        }
        
        // get accounttype from XML and set the type for the account
        list = doc.getElementsByTagName("accounttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getTextContent());
        }
        
        // // get accountdomainname from XML and set the domain name for the account 
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }
        
        // get accounttype from XML and set the domain Id for the account
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }
        
        //get accounttype from XML and set the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }
        
        // get accounttype from XML and set true if the account requires cleanup
        list = doc.getElementsByTagName("iscleanuprequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCleanupRequired(node.getTextContent());
        }
        
        // get accounttype from XML and set the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }
        
        // get accounttype from XML and set the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of network traffic bytes received
        list = doc.getElementsByTagName("receivedbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReceivedBytes(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of network traffic bytes sent
        list = doc.getElementsByTagName("sentbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSentBytes(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }
        
        //get accounttype from XML and set the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }
        
        // get accounttype from XML and set the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountState(node.getTextContent());
        }
        
        
        // get accounttype from XML and set the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of virtual machines available for this account 
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }
        
        // get accounttype from XML and set the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }
        
        // get vmrunning from XML and set the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }
        
        // get vmstopped from XML and set the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }
        
        // get vmtotal from XML and set the total number virtual machines  for this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }
        
        // get volumeavailable from XML and set the total number volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }
        
        // get volumelimit from XML and set the volume limit for this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }
        
        // get volumetotal from XML and set the total volume for this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }
        
        return response;
    }
    /**
     * Lists accounts and provides detailed account information for listed accounts
     * 
     * @param optional
     * @return
     * @throws Exception 
     */
    public ListAccountsResponse listAccounts( 
            HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listAccounts", optional);
        
        Document responseDocument = server.makeRequest(arguments);

        return getListAccountsResponse(responseDocument);
    }
    
    /**
     * Converts XML document into ListAccountsResponse object
     * 
     * @param doc
     * @return 
     */
    private ListAccountsResponse getListAccountsResponse(Document doc) {
        ListAccountsResponse response = new ListAccountsResponse();
        
        NodeList list = doc.getElementsByTagName("account");
        List<AccountResponse> accounts = new LinkedList<AccountResponse>(); 
        if (list.getLength() > 0) {                       
            for (int index = 0; index < list.getLength(); index++) {
                Node accountNode = list.item(index);
                AccountResponse account = new AccountResponse();                
                List<UserResponse> users = new LinkedList<UserResponse>();  
                NodeList accountProperties = accountNode.getChildNodes();
                for (int childIndex = 0; childIndex < accountProperties.getLength(); childIndex++) {
                    Node property = accountProperties.item(childIndex);
                    
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                    if (property.getNodeName().equals("id")) {
                        account.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        account.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("accountdetails")) {
                        account.setAccountDetails(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        account.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("ipavailable")) {
                        account.setIpAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("iplimit")) {
                        account.setIpLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        account.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        account.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("iptotal")) {
                        account.setIpTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("iscleanuprequired")) {
                        account.setIsCleanUpRequired(property.getTextContent());
                    } else if (property.getNodeName().equals("networkdomain")) {
                        account.setNetworkDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("receivedbytes")) {
                        account.setReceivedBytes(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        account.setAccountState(property.getTextContent());
                    } else if (property.getNodeName().equals("sentbytes")) {
                        account.setSentBytes(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshotavailable")) {
                        account.setSnapshotAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshotlimit")) {
                        account.setSnapshotLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshottotal")) {
                        account.setSnapshotTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("templateavailable")) {
                        account.setTemplateAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("templatelimit")) {
                        account.setTemplateLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("templatetotal")) {
                        account.setTemplateTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("vmavailable")) {
                        account.setVmAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("vmlimit")) {
                        account.setVmLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("vmrunning")) {
                        account.setVmRunning(property.getTextContent());
                    } else if (property.getNodeName().equals("vmstopped")) {
                        account.setVmStopped(property.getTextContent());
                    } else if (property.getNodeName().equals("vmtotal")) {
                        account.setVmTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("volumeavailable")) {
                        account.setVolumeAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("volumelimit")) {
                        account.setVolumeLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("volumetotal")) {
                        account.setVolumeTotal(property.getTextContent());
                    }  else if (property.getNodeName().equals("user")) {
                        NodeList userProperties = property.getChildNodes();
                        if (userProperties.getLength() > 0) {
                            UserResponse user = new UserResponse();
                            for (int userIndex = 0; userIndex < userProperties.getLength(); userIndex++) {
                                Node userProperty = userProperties.item(userIndex);
                                if (userProperty.getNodeName().equals("id")) {
                                    user.setUserId(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("account")) {
                                    user.setAccountName(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("accountid")) {
                                    user.setAccountId(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("accounttype")) {
                                    user.setAccountType(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("apikey")) {
                                    user.setApiKey(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("created")) {
                                    user.setCreated(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("domain")) {
                                    user.setDomainName(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("domainid")) {
                                    user.setDomainId(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("email")) {
                                    user.setEmailId(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("firstname")) {
                                    user.setFirstName(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("lastname")) {
                                    user.setLastName(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("secretkey")) {
                                    user.setSecretKey(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("state")) {
                                    user.setState(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("timezone")) {
                                    user.setTimeZone(userProperty.getTextContent());
                                } else if (userProperty.getNodeName().equals("username")) {
                                    user.setUserName(userProperty.getTextContent());
                                }
                            }
                            users.add(user);
                            account.setUsers(users);
                        }
                    }
                }                
                accounts.add(account);               
            }           
        }        
        response.setAccounts(accounts);
        return response;
    }
     
    /**
     * Adds account to a project
     * 
     * @param projectId id of the project to add the account to
     * @param optional
     * @return
     * @throws Exception 
     */
    public AddAccountToProjectResponse addAccountToProject(String projectId,
             HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("addAccountToProject", optional);
        arguments.add(new NameValuePair("projectid",projectId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getAddAccountToProjectResponse(responseDocument);
        
    }
    
    /**
     * Converts XML document into AddAccountToProjectResponse object
     * 
     * @param doc
     * @return 
     */
    private AddAccountToProjectResponse getAddAccountToProjectResponse(Document doc) {
        AddAccountToProjectResponse response = new AddAccountToProjectResponse();
        
        // get displaytext from XML and set Any text associated with the success or 
        // failure on Adding Account to Project
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }
        
        // get success from XML and set Return true if on Adding Account to Project operation 
        //        is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }
        
         return response;
    }
    
    /**
     * Deletes account from the project
     * 
     * @param accountName name of the account to be removed from the project
     * @param projectId id of the project to remove the account from
     * @return
     * @throws Exception 
     */
    public DeleteAccountFromProjectResponse deleteAccountFromProject(String accountName, 
            String projectId) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteAccountFromProject", null);
        arguments.add(new NameValuePair("account",accountName));
        arguments.add(new NameValuePair("projectid",projectId));
         
        Document responseDocument = server.makeRequest(arguments);

        return getDeleteAccountFromProjectResponse(responseDocument);
        
    }
    
    /**
     * Converts XML document into DeleteAccountFromProjectResponse object
     * 
     * @param doc
     * @return 
     */
    private DeleteAccountFromProjectResponse getDeleteAccountFromProjectResponse(Document doc) {
        DeleteAccountFromProjectResponse response = new DeleteAccountFromProjectResponse();
        
        // get displaytext from XML and set Any text associated with the success or 
        // failure on Deleting Account from Project
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }
        
        // get success from XML and set Return true if on Deleting Account from Project operation 
        //        is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }
        
         return response;
    }
    
    /**
     * Lists project's accounts
     * 
     * @param projectId id of the project
     * @param optional
     * @return
     * @throws Exception 
     */
    public ListProjectAccountsResponse listProjectAccounts(String projectId, 
              HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteAccountFromProject", optional);
        arguments.add(new NameValuePair("projectid",projectId));
         
        Document responseDocument = server.makeRequest(arguments);

        return getListProjectAccountsResponse(responseDocument);
        
    }
     
    /**
     * Converts XML document into ListProjectAccountsResponse object
     * 
     * @param doc
     * @return 
     */
    private ListProjectAccountsResponse getListProjectAccountsResponse(Document doc) {
        ListProjectAccountsResponse response = new ListProjectAccountsResponse();
        
        // get projectid from XML and set the projrct Id
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }
        
        // get accountname from XML and set the account name of the project's owner
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }
        
        // get displaytext from XML and set the displaytext of the project
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        
        // get domain from XML and set the domain name where the project belongs to
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }
        
        // get domainid from XML and set the domain id the project belongs to
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainid(node.getTextContent());
        }
        
        // get projectname from XML and set the name of the project
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectName(node.getTextContent());
        }
        
        // get projectstate from XML and set the state of the project
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectState(node.getTextContent());
        }
        
         return response;
    } 
     
}   
