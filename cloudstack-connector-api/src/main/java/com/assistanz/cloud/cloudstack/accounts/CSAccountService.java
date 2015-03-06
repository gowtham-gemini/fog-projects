package com.assistanz.cloud.cloudstack.accounts;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.TagsResponse;
import com.assistanz.cloud.cloudstack.UserResponse;
import org.apache.commons.httpclient.NameValuePair;

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
     * @param accountType Type of the account. Specify 0 for user, 1 for root
     * admin, and 2 for domain admin
     * @param emailId email
     * @param firstName first name
     * @param lastName last name
     * @param userName Unique username
     * @param password Hashed password (Default is MD5). If you wish to use any
     * other hashing algorithm, you would need to write a custom authentication
     * adapter See Docs section.Hashed password (Default is MD5). If you wish to
     * use any other hashing algorithm, you would need to write a custom
     * authentication adapter See Docs section.
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateAccountResponse createAccount(String accountType, String emailId,
            String firstName, String lastName, String userName, String password,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createAccount", optional);
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
            Node node = list.item(1);
            response.setId(node.getTextContent());
        }

        // get details for the account
        list = doc.getElementsByTagName("accountdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountDetails(node.getTextContent());
        }

        // return account type whether (admin, domain-admin, user)
        list = doc.getElementsByTagName("accounttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getTextContent());
        }

        // the total number of cpu cores available to be created for this account
        list = doc.getElementsByTagName("cpuavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAvailable(node.getTextContent());
        }

        // the total number of cpu cores the account can own
        list = doc.getElementsByTagName("cpulimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuLimit(node.getTextContent());
        }

        // the total number of cpu cores owned account 
        list = doc.getElementsByTagName("cputotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuTotal(node.getTextContent());
        }

        // the default zone id of the account 
        list = doc.getElementsByTagName("defaultzoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDefaultZoneId(node.getTextContent());
        }

        // name of the Domain the account belongs too
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // id of the Domain the account belongs too
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }

        // the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }

        // the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }

        // true if the account requires cleanup
        list = doc.getElementsByTagName("iscleanuprequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCleanupRequired(node.getTextContent());
        }

        // true if account is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // the total memory (in MB) available to be created for this account
        list = doc.getElementsByTagName("memoryavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAvailable(node.getTextContent());
        }

        // the total memory (in MB) the account can own
        list = doc.getElementsByTagName("memorylimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryLimit(node.getTextContent());
        }

        // the total memory (in MB) owned by account
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // the total number of networks available to be created for this account
        list = doc.getElementsByTagName("networkavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAvailable(node.getTextContent());
        }

        // the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // the total number of networks the account can own
        list = doc.getElementsByTagName("networklimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkLimit(node.getTextContent());
        }

        // the total number of networks owned by account
        list = doc.getElementsByTagName("networktotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTotal(node.getTextContent());
        }

        // the total primary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("primarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageAvailable(node.getTextContent());
        }

        // the total primary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("primarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageLimit(node.getTextContent());
        }

        // the total primary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("primarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageTotal(node.getTextContent());
        }

        // the total number of projects available for administration by this account
        list = doc.getElementsByTagName("projectavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectAvailable(node.getTextContent());
        }

        // the total number of projects the account can own
        list = doc.getElementsByTagName("projectlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectLimit(node.getTextContent());
        }

        // the total number of projects being administrated by this account
        list = doc.getElementsByTagName("projecttotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectTotal(node.getTextContent());
        }

        // the total number of network traffic bytes received
        list = doc.getElementsByTagName("receivedbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReceivedBytes(node.getTextContent());
        }

        // the total secondary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("secondarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageAvailable(node.getTextContent());
        }

        // the total secondary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("secondarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageLimit(node.getTextContent());
        }

        // the total secondary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("secondarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageTotal(node.getTextContent());
        }

        // the total number of network traffic bytes sent
        list = doc.getElementsByTagName("sentbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSentBytes(node.getTextContent());
        }

        // the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }

        // the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }

        // the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }

        // the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }

        // the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }

        // the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }

        // the total number of virtual machines available for this account to acquire
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }

        // the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }

        // the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }

        // the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }

        // the total number of virtual machines deployed by this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmTotal(node.getTextContent());
        }

        // the total volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeAvailable(node.getTextContent());
        }

        // the total volume which can be used by this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }

        // the total volume being used by this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }

        // the total number of vpcs available to be created for this account
        list = doc.getElementsByTagName("vpcavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcAvailable(node.getTextContent());
        }

        // the total number of vpcs the account can own
        list = doc.getElementsByTagName("vpclimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcLimit(node.getTextContent());
        }

        // the total number of vpcs owned by account
        list = doc.getElementsByTagName("vpctotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcTotal(node.getTextContent());
        }
        // gets associated user values from the account
        list = doc.getElementsByTagName("user");
        if (list.getLength() > 0) {
            List<UserResponse> users = new LinkedList<UserResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node userNode = list.item(index);
                UserResponse user = new UserResponse();

                NodeList userProperties = userNode.getChildNodes();
                for (int childIndex = 0; childIndex < userProperties.getLength(); childIndex++) {
                    Node property = userProperties.item(childIndex);
                    if (property.getNodeName().equals("id")) {
                        user.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        user.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        user.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        user.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("apikey")) {
                        user.setApiKey(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        user.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        user.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        user.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        user.setEmail(property.getTextContent());
                    } else if (property.getNodeName().equals("firstname")) {
                        user.setFirstName(property.getTextContent());
                    } else if (property.getNodeName().equals("iscallerchilddomain")) {
                        user.setIsCallerChildDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        user.setIsDefault(property.getTextContent());
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
        return response;
    }

    /**
     * Deletes a account, and all users associated with this account
     *
     * @param accountId Account id
     * @return
     */
    public DeleteAccountResponse deleteAccount(String accountId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteAccount", null);
        arguments.add(new NameValuePair("id", accountId));

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
    public UpdateAccountResponse updateAccount(String newName, String accountName,
            String domainId, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateAccount", optional);
        arguments.add(new NameValuePair("newname", newName));
        arguments.add(new NameValuePair("account", accountName));
        arguments.add(new NameValuePair("doaminid", domainId));

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

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get details for the account
        list = doc.getElementsByTagName("accountdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountDetails(node.getTextContent());
        }

        // return account type whether (admin, domain-admin, user)
        list = doc.getElementsByTagName("accounttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getTextContent());
        }

        // the total number of cpu cores available to be created for this account
        list = doc.getElementsByTagName("cpuavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAvailable(node.getTextContent());
        }

        // the total number of cpu cores the account can own
        list = doc.getElementsByTagName("cpulimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuLimit(node.getTextContent());
        }

        // the total number of cpu cores owned account 
        list = doc.getElementsByTagName("cputotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuTotal(node.getTextContent());
        }

        // the default zone id of the account 
        list = doc.getElementsByTagName("defaultzoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDefaultZoneId(node.getTextContent());
        }

        // name of the Domain the account belongs too
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // id of the Domain the account belongs too
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }

        // the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }

        // the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }

        // true if the account requires cleanup
        list = doc.getElementsByTagName("iscleanuprequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCleanupRequired(node.getTextContent());
        }

        // true if account is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // the total memory (in MB) available to be created for this account
        list = doc.getElementsByTagName("memoryavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAvailable(node.getTextContent());
        }

        // the total memory (in MB) the account can own
        list = doc.getElementsByTagName("memorylimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryLimit(node.getTextContent());
        }

        // the total memory (in MB) owned by account
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // the total number of networks available to be created for this account
        list = doc.getElementsByTagName("networkavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAvailable(node.getTextContent());
        }

        // the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // the total number of networks the account can own
        list = doc.getElementsByTagName("networklimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkLimit(node.getTextContent());
        }

        // the total number of networks owned by account
        list = doc.getElementsByTagName("networktotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTotal(node.getTextContent());
        }

        // the total primary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("primarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageAvailable(node.getTextContent());
        }

        // the total primary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("primarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageLimit(node.getTextContent());
        }

        // the total primary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("primarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageTotal(node.getTextContent());
        }

        // the total number of projects available for administration by this account
        list = doc.getElementsByTagName("projectavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectAvailable(node.getTextContent());
        }

        // the total number of projects the account can own
        list = doc.getElementsByTagName("projectlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectLimit(node.getTextContent());
        }

        // the total number of projects being administrated by this account
        list = doc.getElementsByTagName("projecttotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectTotal(node.getTextContent());
        }

        // the total number of network traffic bytes received
        list = doc.getElementsByTagName("receivedbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReceivedBytes(node.getTextContent());
        }

        // the total secondary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("secondarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageAvailable(node.getTextContent());
        }

        // the total secondary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("secondarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageLimit(node.getTextContent());
        }

        // the total secondary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("secondarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageTotal(node.getTextContent());
        }

        // the total number of network traffic bytes sent
        list = doc.getElementsByTagName("sentbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSentBytes(node.getTextContent());
        }

        // the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }

        // the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }

        // the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }

        // the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }

        // the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }

        // the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }

        // the total number of virtual machines available for this account to acquire
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }

        // the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }

        // the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }

        // the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }

        // the total number of virtual machines deployed by this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmTotal(node.getTextContent());
        }

        // the total volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeAvailable(node.getTextContent());
        }

        // the total volume which can be used by this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }

        // the total volume being used by this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }

        // the total number of vpcs available to be created for this account
        list = doc.getElementsByTagName("vpcavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcAvailable(node.getTextContent());
        }

        // the total number of vpcs the account can own
        list = doc.getElementsByTagName("vpclimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcLimit(node.getTextContent());
        }

        // the total number of vpcs owned by account
        list = doc.getElementsByTagName("vpctotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcTotal(node.getTextContent());
        }

        // gets associated user values from the account
        list = doc.getElementsByTagName("user");
        if (list.getLength() > 0) {
            List<UserResponse> users = new LinkedList<UserResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node userNode = list.item(index);
                UserResponse user = new UserResponse();

                NodeList userProperties = userNode.getChildNodes();
                for (int childIndex = 0; childIndex < userProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        user.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        user.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        user.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        user.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("apikey")) {
                        user.setApiKey(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        user.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        user.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        user.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        user.setEmail(property.getTextContent());
                    } else if (property.getNodeName().equals("firstname")) {
                        user.setFirstName(property.getTextContent());
                    } else if (property.getNodeName().equals("iscallerchilddomain")) {
                        user.setIsCallerChildDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        user.setIsDefault(property.getTextContent());
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
    public DisableAccountResponse disableAccount(String lock, String accountName, String domainId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("disableAccount", optional);
        arguments.add(new NameValuePair("lock", lock));
        arguments.add(new NameValuePair("account", accountName));
        arguments.add(new NameValuePair("doaminid", domainId));
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

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get details for the account
        list = doc.getElementsByTagName("accountdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountDetails(node.getTextContent());
        }

        // return account type whether (admin, domain-admin, user)
        list = doc.getElementsByTagName("accounttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getTextContent());
        }

        // the total number of cpu cores available to be created for this account
        list = doc.getElementsByTagName("cpuavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAvailable(node.getTextContent());
        }

        // the total number of cpu cores the account can own
        list = doc.getElementsByTagName("cpulimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuLimit(node.getTextContent());
        }

        // the total number of cpu cores owned account 
        list = doc.getElementsByTagName("cputotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuTotal(node.getTextContent());
        }

        // the default zone id of the account 
        list = doc.getElementsByTagName("defaultzoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDefaultZoneId(node.getTextContent());
        }

        // name of the Domain the account belongs too
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // id of the Domain the account belongs too
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }

        // the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }

        // the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }

        // true if the account requires cleanup
        list = doc.getElementsByTagName("iscleanuprequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCleanupRequired(node.getTextContent());
        }

        // true if account is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // the total memory (in MB) available to be created for this account
        list = doc.getElementsByTagName("memoryavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAvailable(node.getTextContent());
        }

        // the total memory (in MB) the account can own
        list = doc.getElementsByTagName("memorylimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryLimit(node.getTextContent());
        }

        // the total memory (in MB) owned by account
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // the total number of networks available to be created for this account
        list = doc.getElementsByTagName("networkavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAvailable(node.getTextContent());
        }

        // the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // the total number of networks the account can own
        list = doc.getElementsByTagName("networklimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkLimit(node.getTextContent());
        }

        // the total number of networks owned by account
        list = doc.getElementsByTagName("networktotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTotal(node.getTextContent());
        }

        // the total primary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("primarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageAvailable(node.getTextContent());
        }

        // the total primary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("primarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageLimit(node.getTextContent());
        }

        // the total primary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("primarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageTotal(node.getTextContent());
        }

        // the total number of projects available for administration by this account
        list = doc.getElementsByTagName("projectavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectAvailable(node.getTextContent());
        }

        // the total number of projects the account can own
        list = doc.getElementsByTagName("projectlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectLimit(node.getTextContent());
        }

        // the total number of projects being administrated by this account
        list = doc.getElementsByTagName("projecttotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectTotal(node.getTextContent());
        }

        // the total number of network traffic bytes received
        list = doc.getElementsByTagName("receivedbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReceivedBytes(node.getTextContent());
        }

        // the total secondary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("secondarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageAvailable(node.getTextContent());
        }

        // the total secondary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("secondarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageLimit(node.getTextContent());
        }

        // the total secondary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("secondarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageTotal(node.getTextContent());
        }

        // the total number of network traffic bytes sent
        list = doc.getElementsByTagName("sentbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSentBytes(node.getTextContent());
        }

        // the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }

        // the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }

        // the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }

        // the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }

        // the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }

        // the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }

        // the total number of virtual machines available for this account to acquire
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }

        // the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }

        // the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }

        // the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }

        // the total number of virtual machines deployed by this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmTotal(node.getTextContent());
        }

        // the total volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeAvailable(node.getTextContent());
        }

        // the total volume which can be used by this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }

        // the total volume being used by this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }

        // the total number of vpcs available to be created for this account
        list = doc.getElementsByTagName("vpcavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcAvailable(node.getTextContent());
        }

        // the total number of vpcs the account can own
        list = doc.getElementsByTagName("vpclimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcLimit(node.getTextContent());
        }

        // the total number of vpcs owned by account
        list = doc.getElementsByTagName("vpctotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcTotal(node.getTextContent());
        }

        // gets associated user values from the account
        list = doc.getElementsByTagName("user");
        if (list.getLength() > 0) {
            List<UserResponse> users = new LinkedList<UserResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node userNode = list.item(index);
                UserResponse user = new UserResponse();

                NodeList userProperties = userNode.getChildNodes();
                for (int childIndex = 0; childIndex < userProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        user.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        user.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        user.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        user.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("apikey")) {
                        user.setApiKey(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        user.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        user.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        user.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        user.setEmail(property.getTextContent());
                    } else if (property.getNodeName().equals("firstname")) {
                        user.setFirstName(property.getTextContent());
                    } else if (property.getNodeName().equals("iscallerchilddomain")) {
                        user.setIsCallerChildDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        user.setIsDefault(property.getTextContent());
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
        return response;
    }

    /**
     * Enables an account
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public EnableAccountResponse enableAccount(String accountName, String domainId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("enableAccount", optional);
        arguments.add(new NameValuePair("account", accountName));
        arguments.add(new NameValuePair("doaminid", domainId));

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

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get details for the account
        list = doc.getElementsByTagName("accountdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountDetails(node.getTextContent());
        }

        // return account type whether (admin, domain-admin, user)
        list = doc.getElementsByTagName("accounttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getTextContent());
        }

        // the total number of cpu cores available to be created for this account
        list = doc.getElementsByTagName("cpuavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAvailable(node.getTextContent());
        }

        // the total number of cpu cores the account can own
        list = doc.getElementsByTagName("cpulimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuLimit(node.getTextContent());
        }

        // the total number of cpu cores owned account 
        list = doc.getElementsByTagName("cputotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuTotal(node.getTextContent());
        }

        // the default zone id of the account 
        list = doc.getElementsByTagName("defaultzoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDefaultZoneId(node.getTextContent());
        }

        // name of the Domain the account belongs too
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // id of the Domain the account belongs too
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }

        // the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }

        // the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }

        // true if the account requires cleanup
        list = doc.getElementsByTagName("iscleanuprequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCleanupRequired(node.getTextContent());
        }

        // true if account is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // the total memory (in MB) available to be created for this account
        list = doc.getElementsByTagName("memoryavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAvailable(node.getTextContent());
        }

        // the total memory (in MB) the account can own
        list = doc.getElementsByTagName("memorylimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryLimit(node.getTextContent());
        }

        // the total memory (in MB) owned by account
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // the total number of networks available to be created for this account
        list = doc.getElementsByTagName("networkavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAvailable(node.getTextContent());
        }

        // the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // the total number of networks the account can own
        list = doc.getElementsByTagName("networklimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkLimit(node.getTextContent());
        }

        // the total number of networks owned by account
        list = doc.getElementsByTagName("networktotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTotal(node.getTextContent());
        }

        // the total primary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("primarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageAvailable(node.getTextContent());
        }

        // the total primary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("primarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageLimit(node.getTextContent());
        }

        // the total primary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("primarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageTotal(node.getTextContent());
        }

        // the total number of projects available for administration by this account
        list = doc.getElementsByTagName("projectavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectAvailable(node.getTextContent());
        }

        // the total number of projects the account can own
        list = doc.getElementsByTagName("projectlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectLimit(node.getTextContent());
        }

        // the total number of projects being administrated by this account
        list = doc.getElementsByTagName("projecttotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectTotal(node.getTextContent());
        }

        // the total number of network traffic bytes received
        list = doc.getElementsByTagName("receivedbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReceivedBytes(node.getTextContent());
        }

        // the total secondary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("secondarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageAvailable(node.getTextContent());
        }

        // the total secondary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("secondarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageLimit(node.getTextContent());
        }

        // the total secondary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("secondarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageTotal(node.getTextContent());
        }

        // the total number of network traffic bytes sent
        list = doc.getElementsByTagName("sentbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSentBytes(node.getTextContent());
        }

        // the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }

        // the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }

        // the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }

        // the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }

        // the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }

        // the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }

        // the total number of virtual machines available for this account to acquire
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }

        // the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }

        // the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }

        // the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }

        // the total number of virtual machines deployed by this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmTotal(node.getTextContent());
        }

        // the total volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeAvailable(node.getTextContent());
        }

        // the total volume which can be used by this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }

        // the total volume being used by this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }

        // the total number of vpcs available to be created for this account
        list = doc.getElementsByTagName("vpcavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcAvailable(node.getTextContent());
        }

        // the total number of vpcs the account can own
        list = doc.getElementsByTagName("vpclimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcLimit(node.getTextContent());
        }

        // the total number of vpcs owned by account
        list = doc.getElementsByTagName("vpctotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcTotal(node.getTextContent());
        }

        // gets associated user values from the account
        list = doc.getElementsByTagName("user");
        if (list.getLength() > 0) {
            List<UserResponse> users = new LinkedList<UserResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node userNode = list.item(index);
                UserResponse user = new UserResponse();

                NodeList userProperties = userNode.getChildNodes();
                for (int childIndex = 0; childIndex < userProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        user.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        user.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        user.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        user.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("apikey")) {
                        user.setApiKey(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        user.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        user.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        user.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        user.setEmail(property.getTextContent());
                    } else if (property.getNodeName().equals("firstname")) {
                        user.setFirstName(property.getTextContent());
                    } else if (property.getNodeName().equals("iscallerchilddomain")) {
                        user.setIsCallerChildDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        user.setIsDefault(property.getTextContent());
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
        return response;
    }

    /**
     * Lock accounts locks the specific account
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public LockAccountResponse lockAccount(String accountName, String domainId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("lockAccount", optional);
        arguments.add(new NameValuePair("account", accountName));
        arguments.add(new NameValuePair("domainid", domainId));

        Document responseDocument = server.makeRequest(arguments);

        return getLockAccountResponse(responseDocument);

    }

    /**
     * Converts XML document into LockAccountResponse object
     *
     * @param doc
     * @return
     */
    private LockAccountResponse getLockAccountResponse(Document doc) {
        LockAccountResponse response = new LockAccountResponse();

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get details for the account
        list = doc.getElementsByTagName("accountdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountDetails(node.getTextContent());
        }

        // return account type whether (admin, domain-admin, user)
        list = doc.getElementsByTagName("accounttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getTextContent());
        }

        // the total number of cpu cores available to be created for this account
        list = doc.getElementsByTagName("cpuavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAvailable(node.getTextContent());
        }

        // the total number of cpu cores the account can own
        list = doc.getElementsByTagName("cpulimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuLimit(node.getTextContent());
        }

        // the total number of cpu cores owned account 
        list = doc.getElementsByTagName("cputotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuTotal(node.getTextContent());
        }

        // the default zone id of the account 
        list = doc.getElementsByTagName("defaultzoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDefaultZoneId(node.getTextContent());
        }

        // name of the Domain the account belongs too
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // id of the Domain the account belongs too
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }

        // the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }

        // the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }

        // true if the account requires cleanup
        list = doc.getElementsByTagName("iscleanuprequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCleanupRequired(node.getTextContent());
        }

        // true if account is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // the total memory (in MB) available to be created for this account
        list = doc.getElementsByTagName("memoryavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAvailable(node.getTextContent());
        }

        // the total memory (in MB) the account can own
        list = doc.getElementsByTagName("memorylimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryLimit(node.getTextContent());
        }

        // the total memory (in MB) owned by account
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // the total number of networks available to be created for this account
        list = doc.getElementsByTagName("networkavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAvailable(node.getTextContent());
        }

        // the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // the total number of networks the account can own
        list = doc.getElementsByTagName("networklimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkLimit(node.getTextContent());
        }

        // the total number of networks owned by account
        list = doc.getElementsByTagName("networktotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTotal(node.getTextContent());
        }

        // the total primary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("primarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageAvailable(node.getTextContent());
        }

        // the total primary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("primarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageLimit(node.getTextContent());
        }

        // the total primary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("primarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageTotal(node.getTextContent());
        }

        // the total number of projects available for administration by this account
        list = doc.getElementsByTagName("projectavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectAvailable(node.getTextContent());
        }

        // the total number of projects the account can own
        list = doc.getElementsByTagName("projectlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectLimit(node.getTextContent());
        }

        // the total number of projects being administrated by this account
        list = doc.getElementsByTagName("projecttotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectTotal(node.getTextContent());
        }

        // the total number of network traffic bytes received
        list = doc.getElementsByTagName("receivedbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReceivedBytes(node.getTextContent());
        }

        // the total secondary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("secondarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageAvailable(node.getTextContent());
        }

        // the total secondary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("secondarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageLimit(node.getTextContent());
        }

        // the total secondary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("secondarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageTotal(node.getTextContent());
        }

        // the total number of network traffic bytes sent
        list = doc.getElementsByTagName("sentbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSentBytes(node.getTextContent());
        }

        // the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }

        // the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }

        // the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }

        // the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }

        // the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }

        // the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }

        // the total number of virtual machines available for this account to acquire
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }

        // the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }

        // the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }

        // the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }

        // the total number of virtual machines deployed by this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmTotal(node.getTextContent());
        }

        // the total volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeAvailable(node.getTextContent());
        }

        // the total volume which can be used by this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }

        // the total volume being used by this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }

        // the total number of vpcs available to be created for this account
        list = doc.getElementsByTagName("vpcavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcAvailable(node.getTextContent());
        }

        // the total number of vpcs the account can own
        list = doc.getElementsByTagName("vpclimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcLimit(node.getTextContent());
        }

        // the total number of vpcs owned by account
        list = doc.getElementsByTagName("vpctotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcTotal(node.getTextContent());
        }

        // gets associated user values from the account
        list = doc.getElementsByTagName("user");
        if (list.getLength() > 0) {
            List<UserResponse> users = new LinkedList<UserResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node userNode = list.item(index);
                UserResponse user = new UserResponse();

                NodeList userProperties = userNode.getChildNodes();
                for (int childIndex = 0; childIndex < userProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        user.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        user.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        user.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        user.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("apikey")) {
                        user.setApiKey(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        user.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        user.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        user.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        user.setEmail(property.getTextContent());
                    } else if (property.getNodeName().equals("firstname")) {
                        user.setFirstName(property.getTextContent());
                    } else if (property.getNodeName().equals("iscallerchilddomain")) {
                        user.setIsCallerChildDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        user.setIsDefault(property.getTextContent());
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
        return response;
    }

    /**
     * Lists accounts and provides detailed account information for listed
     * accounts
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListAccountsResponse listAccounts(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listAccounts", optional);

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

        List<AccountResponse> listAccounts = new LinkedList<AccountResponse>();
        if (list.getLength() > 0) {
            for (int listAccountIndex = 0; listAccountIndex < list.getLength(); listAccountIndex++) {
                Node listAccountNode = list.item(listAccountIndex);
                // to avoid additional account present under user
                if (listAccountNode.getChildNodes().getLength() > 2) {

                    AccountResponse listAccount = new AccountResponse();
                    List<UserResponse> users = new LinkedList<UserResponse>();
                    NodeList listAccountProperties = listAccountNode.getChildNodes();
                    for (int childIndex = 0; childIndex < listAccountProperties.getLength(); childIndex++) {
                        Node property = listAccountProperties.item(childIndex);

                        if (property.getNodeName().equals("id")) {
                            listAccount.setId(property.getTextContent());
                        } else if (property.getNodeName().equals("accountdetails")) {
                            listAccount.setAccountDetails(property.getTextContent());
                        } else if (property.getNodeName().equals("accounttype")) {
                            listAccount.setAccountType(property.getTextContent());
                        } else if (property.getNodeName().equals("cpuavailable")) {
                            listAccount.setCpuAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("cpulimit")) {
                            listAccount.setCpuLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("cputotal")) {
                            listAccount.setCpuTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("defaultzoneid")) {
                            listAccount.setDefaultZoneId(property.getTextContent());
                        } else if (property.getNodeName().equals("domain")) {
                            listAccount.setDomain(property.getTextContent());
                        } else if (property.getNodeName().equals("domainid")) {
                            listAccount.setDomainId(property.getTextContent());
                        } else if (property.getNodeName().equals("ipavailable")) {
                            listAccount.setIpAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("iplimit")) {
                            listAccount.setIpLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("iptotal")) {
                            listAccount.setIpTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("iscleanuprequired")) {
                            listAccount.setIsCleanupRequired(property.getTextContent());
                        } else if (property.getNodeName().equals("isdefault")) {
                            listAccount.setIsDefault(property.getTextContent());
                        } else if (property.getNodeName().equals("memoryavailable")) {
                            listAccount.setMemoryAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("memorylimit")) {
                            listAccount.setMemoryLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("memorytotal")) {
                            listAccount.setMemoryTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("name")) {
                            listAccount.setName(property.getTextContent());
                        } else if (property.getNodeName().equals("networkavailable")) {
                            listAccount.setNetworkAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("networkdomain")) {
                            listAccount.setNetworkDomain(property.getTextContent());
                        } else if (property.getNodeName().equals("networklimit")) {
                            listAccount.setNetworkLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("networktotal")) {
                            listAccount.setNetworkTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("primarystorageavailable")) {
                            listAccount.setPrimaryStorageAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("primarystoragelimit")) {
                            listAccount.setPrimaryStorageLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("primarystoragetotal")) {
                            listAccount.setPrimaryStorageTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("projectavailable")) {
                            listAccount.setProjectAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("projectlimit")) {
                            listAccount.setProjectLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("projecttotal")) {
                            listAccount.setProjectTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("receivedbytes")) {
                            listAccount.setReceivedBytes(property.getTextContent());
                        } else if (property.getNodeName().equals("secondarystorageavailable")) {
                            listAccount.setSecondaryStorageAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("secondarystoragelimit")) {
                            listAccount.setSecondaryStorageLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("secondarystoragetotal")) {
                            listAccount.setSecondaryStorageTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("sentbytes")) {
                            listAccount.setSentBytes(property.getTextContent());
                        } else if (property.getNodeName().equals("snapshotavailable")) {
                            listAccount.setSnapshotAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("snapshotlimit")) {
                            listAccount.setSnapshotLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("snapshottotal")) {
                            listAccount.setSnapshotTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("state")) {
                            listAccount.setState(property.getTextContent());
                        } else if (property.getNodeName().equals("templateavailable")) {
                            listAccount.setTemplateAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("templatelimit")) {
                            listAccount.setTemplateLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("templatetotal")) {
                            listAccount.setTemplateTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("vmavailable")) {
                            listAccount.setVmAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("vmlimit")) {
                            listAccount.setVmLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("vmrunning")) {
                            listAccount.setVmRunning(property.getTextContent());
                        } else if (property.getNodeName().equals("vmstopped")) {
                            listAccount.setVmStopped(property.getTextContent());
                        } else if (property.getNodeName().equals("vmtotal")) {
                            listAccount.setVmTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("volumeavailable")) {
                            listAccount.setVolumeAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("volumelimit")) {
                            listAccount.setVolumeLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("volumetotal")) {
                            listAccount.setVolumeTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("vpcavailable")) {
                            listAccount.setVpcAvailable(property.getTextContent());
                        } else if (property.getNodeName().equals("vpclimit")) {
                            listAccount.setVpcLimit(property.getTextContent());
                        } else if (property.getNodeName().equals("vpctotal")) {
                            listAccount.setVpcTotal(property.getTextContent());
                        } else if (property.getNodeName().equals("user")) {
                            NodeList userProperties = property.getChildNodes();
                            if (userProperties.getLength() > 0) {
                                UserResponse user = new UserResponse();
                                for (int userIndex = 0; userIndex < userProperties.getLength(); userIndex++) {
                                    Node userProperty = userProperties.item(userIndex);
                                    if (userProperty.getNodeName().equals("id")) {
                                        user.setId(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("account")) {
                                        user.setAccount(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("accountid")) {
                                        user.setAccountId(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("accounttype")) {
                                        user.setAccountType(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("apikey")) {
                                        user.setApiKey(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("created")) {
                                        user.setCreated(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("domainname")) {
                                        user.setDomain(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("domainid")) {
                                        user.setDomainId(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("emailid")) {
                                        user.setEmail(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("firstname")) {
                                        user.setFirstName(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("iscallerchilddomain")) {
                                        user.setIsCallerChildDomain(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("isdefault")) {
                                        user.setIsDefault(userProperty.getTextContent());
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
                            }

                        }

                    }
                    listAccount.setUsers(users);
                    listAccounts.add(listAccount);
                }
            }
        }
        response.setListAccounts(listAccounts);
        return response;
    }

    /**
     * Marks a default zone
     *
     * @param accountName Name of the account that is to be marked
     * @param domainId Marks the account that belongs to the specified domain
     * @param zoneId The Zone ID with which the account is to be marked
     * @param optional
     * @return
     * @throws Exception
     */
    public MarkDefaultZoneForAccountResponse markDefaultZoneForAccount(String accountName, String domainId,
            String zoneId, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("markDefaultZoneForAccount", optional);
        arguments.add(new NameValuePair("account", accountName));
        arguments.add(new NameValuePair("domainid", domainId));
        arguments.add(new NameValuePair("zoneid", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getMarkDefaultZoneForAccountResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateAccountResponse object
     *
     * @param doc
     * @return
     */
    private MarkDefaultZoneForAccountResponse getMarkDefaultZoneForAccountResponse(Document doc) {
        MarkDefaultZoneForAccountResponse response = new MarkDefaultZoneForAccountResponse();

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get details for the account
        list = doc.getElementsByTagName("accountdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountDetails(node.getTextContent());
        }

        // return account type whether (admin, domain-admin, user)
        list = doc.getElementsByTagName("accounttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getTextContent());
        }

        // the total number of cpu cores available to be created for this account
        list = doc.getElementsByTagName("cpuavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAvailable(node.getTextContent());
        }

        // the total number of cpu cores the account can own
        list = doc.getElementsByTagName("cpulimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuLimit(node.getTextContent());
        }

        // the total number of cpu cores owned account 
        list = doc.getElementsByTagName("cputotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuTotal(node.getTextContent());
        }

        // the default zone id of the account 
        list = doc.getElementsByTagName("defaultzoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDefaultZoneId(node.getTextContent());
        }

        // name of the Domain the account belongs too
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // id of the Domain the account belongs too
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }

        // the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }

        // the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }

        // true if the account requires cleanup
        list = doc.getElementsByTagName("iscleanuprequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCleanupRequired(node.getTextContent());
        }

        // true if account is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // the total memory (in MB) available to be created for this account
        list = doc.getElementsByTagName("memoryavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAvailable(node.getTextContent());
        }

        // the total memory (in MB) the account can own
        list = doc.getElementsByTagName("memorylimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryLimit(node.getTextContent());
        }

        // the total memory (in MB) owned by account
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // the total number of networks available to be created for this account
        list = doc.getElementsByTagName("networkavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAvailable(node.getTextContent());
        }

        // the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // the total number of networks the account can own
        list = doc.getElementsByTagName("networklimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkLimit(node.getTextContent());
        }

        // the total number of networks owned by account
        list = doc.getElementsByTagName("networktotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTotal(node.getTextContent());
        }

        // the total primary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("primarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageAvailable(node.getTextContent());
        }

        // the total primary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("primarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageLimit(node.getTextContent());
        }

        // the total primary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("primarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageTotal(node.getTextContent());
        }

        // the total number of projects available for administration by this account
        list = doc.getElementsByTagName("projectavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectAvailable(node.getTextContent());
        }

        // the total number of projects the account can own
        list = doc.getElementsByTagName("projectlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectLimit(node.getTextContent());
        }

        // the total number of projects being administrated by this account
        list = doc.getElementsByTagName("projecttotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectTotal(node.getTextContent());
        }

        // the total number of network traffic bytes received
        list = doc.getElementsByTagName("receivedbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReceivedBytes(node.getTextContent());
        }

        // the total secondary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("secondarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageAvailable(node.getTextContent());
        }

        // the total secondary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("secondarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageLimit(node.getTextContent());
        }

        // the total secondary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("secondarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageTotal(node.getTextContent());
        }

        // the total number of network traffic bytes sent
        list = doc.getElementsByTagName("sentbytes");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSentBytes(node.getTextContent());
        }

        // the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }

        // the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }

        // the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }

        // the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }

        // the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }

        // the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }

        // the total number of virtual machines available for this account to acquire
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }

        // the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }

        // the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }

        // the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }

        // the total number of virtual machines deployed by this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmTotal(node.getTextContent());
        }

        // the total volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeAvailable(node.getTextContent());
        }

        // the total volume which can be used by this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }

        // the total volume being used by this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }

        // the total number of vpcs available to be created for this account
        list = doc.getElementsByTagName("vpcavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcAvailable(node.getTextContent());
        }

        // the total number of vpcs the account can own
        list = doc.getElementsByTagName("vpclimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcLimit(node.getTextContent());
        }

        // the total number of vpcs owned by account
        list = doc.getElementsByTagName("vpctotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcTotal(node.getTextContent());
        }

        // gets associated user values from the account
        list = doc.getElementsByTagName("user");
        if (list.getLength() > 0) {
            List<UserResponse> users = new LinkedList<UserResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node userNode = list.item(index);
                UserResponse user = new UserResponse();

                NodeList userProperties = userNode.getChildNodes();
                for (int childIndex = 0; childIndex < userProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        user.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        user.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        user.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        user.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("apikey")) {
                        user.setApiKey(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        user.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        user.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        user.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        user.setEmail(property.getTextContent());
                    } else if (property.getNodeName().equals("firstname")) {
                        user.setFirstName(property.getTextContent());
                    } else if (property.getNodeName().equals("iscallerchilddomain")) {
                        user.setIsCallerChildDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        user.setIsDefault(property.getTextContent());
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
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addAccountToProject", optional);
        arguments.add(new NameValuePair("projectid", projectId));

        Document responseDocument = server.makeRequest(arguments);

        return getAddAccountToProjectResponse(responseDocument);

    }

    /**
     * Converts XML document into AddAccountToProjectResponse object
     *
     * @param doc
     * @return
     */
    public AddAccountToProjectResponse getAddAccountToProjectResponse(Document doc) {
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
     * Retrieves the current status of asynchronous job for account.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public AccountJobResultResponse accountJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getAccountJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into AccountJobResultResponse object
     *
     * @param doc
     * @return
     */
    private AccountJobResultResponse getAccountJobResultResponse(Document doc) {
        AccountJobResultResponse response = new AccountJobResultResponse();

        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousCmd(node.getTextContent());
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobProgressStatus(node.getTextContent());
        }

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            NodeList node = list.item(0).getChildNodes();
            for (int index = 0; index < node.getLength(); index++) {
                Node nodeProperty = node.item(index);
                if (nodeProperty.getNodeName().equals("account")) {
                    List<UserResponse> users = new LinkedList<UserResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("accountdetails")) {
                            response.setAccountDetails(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("accounttype")) {
                            response.setAccountType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("cpuavailable")) {
                            response.setCpuAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("cpulimit")) {
                            response.setCpuLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("cputotal")) {
                            response.setCpuTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("defaultzoneid")) {
                            response.setDefaultZoneId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ipavailable")) {
                            response.setIpAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("iplimit")) {
                            response.setIpLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("iptotal")) {
                            response.setIpTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("iscleanuprequired")) {
                            response.setIsCleanupRequired(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("isdefault")) {
                            response.setIsDefault(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("memoryavailable")) {
                            response.setMemoryAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("memorylimit")) {
                            response.setMemoryLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("memorytotal")) {
                            response.setMemoryTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkavailable")) {
                            response.setNetworkAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkdomain")) {
                            response.setNetworkDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networklimit")) {
                            response.setNetworkLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networktotal")) {
                            response.setNetworkTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("primarystorageavailable")) {
                            response.setPrimaryStorageAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("primarystoragelimit")) {
                            response.setPrimaryStorageLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("primarystoragetotal")) {
                            response.setPrimaryStorageTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectavailable")) {
                            response.setProjectAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectlimit")) {
                            response.setProjectLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projecttotal")) {
                            response.setProjectTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("receivedbytes")) {
                            response.setReceivedBytes(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("secondarystorageavailable")) {
                            response.setSecondaryStorageAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("secondarystoragelimit")) {
                            response.setSecondaryStorageLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("secondarystoragetotal")) {
                            response.setSecondaryStorageTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("sentbytes")) {
                            response.setSentBytes(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("snapshotavailable")) {
                            response.setSnapshotAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("snapshotlimit")) {
                            response.setSnapshotLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("snapshottotal")) {
                            response.setSecondaryStorageTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("templateavailable")) {
                            response.setTemplateAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("templatelimit")) {
                            response.setTemplateLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("templatetotal")) {
                            response.setTemplateTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmavailable")) {
                            response.setVmAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmlimit")) {
                            response.setVmLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmrunning")) {
                            response.setVmRunning(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmstopped")) {
                            response.setVmStopped(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmtotal")) {
                            response.setVmTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("volumeavailable")) {
                            response.setVolumeAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("volumelimit")) {
                            response.setVolumeLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("volumetotal")) {
                            response.setVolumeTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vpcavailable")) {
                            response.setVpcAvailable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vpclimit")) {
                            response.setVpcLimit(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vpctotal")) {
                            response.setVpcTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("user")) {
                            NodeList userProperties = childNodeProperty.getChildNodes();
                            if (userProperties.getLength() > 0) {
                                UserResponse user = new UserResponse();
                                for (int userIndex = 0; userIndex < userProperties.getLength(); userIndex++) {
                                    Node userProperty = userProperties.item(userIndex);

                                    if (userProperty.getNodeName().equals("id")) {
                                        user.setAccount(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("account")) {
                                        user.setAccount(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("accountid")) {
                                        user.setAccountId(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("accounttype")) {
                                        user.setAccountType(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("apikey")) {
                                        user.setApiKey(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("created")) {
                                        user.setCreated(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("domain")) {
                                        user.setDomain(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("domainid")) {
                                        user.setDomainId(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("email")) {
                                        user.setEmail(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("firstname")) {
                                        user.setFirstName(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("iscallerchilddomain")) {
                                        user.setIsCallerChildDomain(userProperty.getTextContent());
                                    } else if (userProperty.getNodeName().equals("isdefault")) {
                                        user.setIsDefault(userProperty.getTextContent());
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
                            }
                            response.setUsers(users);
                        }
                    }
                } else if (nodeProperty.getNodeName().equals("errorcode")) {
                    response.setErrorCode(nodeProperty.getTextContent());
                } else if (nodeProperty.getNodeName().equals("errortext")) {
                    response.setErrorText(nodeProperty.getTextContent());
                }
            }
        }

        // get jobresultcode from XML and set as the result code for the job
        list = doc.getElementsByTagName("jobresultcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobId(node.getTextContent());
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

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteAccountFromProject", null);
        arguments.add(new NameValuePair("account", accountName));
        arguments.add(new NameValuePair("projectid", projectId));

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
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listProjectAccounts", optional);
        arguments.add(new NameValuePair("projectid", projectId));

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

        NodeList list = doc.getElementsByTagName("projectaccount");

        List<ProjectAccountResponse> projectAccounts = new LinkedList<ProjectAccountResponse>();
        if (list.getLength() > 0) {
            for (int projectAccountIndex = 0; projectAccountIndex < list.getLength(); projectAccountIndex++) {
                Node projectAccountNode = list.item(projectAccountIndex);

                ProjectAccountResponse projectAccount = new ProjectAccountResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList projectAccountProperties = projectAccountNode.getChildNodes();
                for (int childIndex = 0; childIndex < projectAccountProperties.getLength(); childIndex++) {
                    Node property = projectAccountProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        projectAccount.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        projectAccount.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuavailable")) {
                        projectAccount.setCpuAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("cpulimit")) {
                        projectAccount.setCpuLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("cputotal")) {
                        projectAccount.setCpuTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                        projectAccount.setDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        projectAccount.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        projectAccount.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("ipavailable")) {
                        projectAccount.setIpAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("iplimit")) {
                        projectAccount.setIpLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("iptotal")) {
                        projectAccount.setIpTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("memoryavailable")) {
                        projectAccount.setMemoryAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("memorylimit")) {
                        projectAccount.setMemoryLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("memorytotal")) {
                        projectAccount.setMemoryTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        projectAccount.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkavailable")) {
                        projectAccount.setNetworkAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("networklimit")) {
                        projectAccount.setNetworkLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("networktotal")) {
                        projectAccount.setNetworkTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("primarystorageavailable")) {
                        projectAccount.setPrimaryStorageAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("primarystoragelimit")) {
                        projectAccount.setPrimaryStorageLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("primarystoragetotal")) {
                        projectAccount.setPrimaryStorageTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("secondarystorageavailable")) {
                        projectAccount.setSecondaryStorageAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("secondarystoragelimit")) {
                        projectAccount.setSecondaryStorageLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("secondarystoragetotal")) {
                        projectAccount.setSecondaryStorageTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshotavailable")) {
                        projectAccount.setSnapshotAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshotlimit")) {
                        projectAccount.setSnapshotLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshottotal")) {
                        projectAccount.setSnapshotTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        projectAccount.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("templateavailable")) {
                        projectAccount.setTemplateAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("templatelimit")) {
                        projectAccount.setTemplateLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("templatetotal")) {
                        projectAccount.setTemplateTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("vmavailable")) {
                        projectAccount.setVmAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("vmlimit")) {
                        projectAccount.setVmLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("vmrunning")) {
                        projectAccount.setVmRunning(property.getTextContent());
                    } else if (property.getNodeName().equals("vmstopped")) {
                        projectAccount.setVmStopped(property.getTextContent());
                    } else if (property.getNodeName().equals("vmtotal")) {
                        projectAccount.setVmTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("volumeavailable")) {
                        projectAccount.setVolumeAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("volumelimit")) {
                        projectAccount.setVolumeLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("volumetotal")) {
                        projectAccount.setVolumeTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcavailable")) {
                        projectAccount.setVpcAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("vpclimit")) {
                        projectAccount.setVpcLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("vpctotal")) {
                        projectAccount.setVpcTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        NodeList tagsProperties = property.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);
                                if (tagsProperty.getNodeName().equals("account")) {
                                    tags.setAccount(tagsProperty.getTextContent());
                                } else if (tagsProperty.getNodeName().equals("customer")) {
                                    tags.setCustomer(tagsProperty.getTextContent());
                                } else if (tagsProperty.getNodeName().equals("domain")) {
                                    tags.setDomain(tagsProperty.getTextContent());
                                } else if (tagsProperty.getNodeName().equals("domainid")) {
                                    tags.setDomainId(tagsProperty.getTextContent());
                                } else if (tagsProperty.getNodeName().equals("key")) {
                                    tags.setKey(tagsProperty.getTextContent());
                                } else if (tagsProperty.getNodeName().equals("project")) {
                                    tags.setProject(tagsProperty.getTextContent());
                                } else if (tagsProperty.getNodeName().equals("projectid")) {
                                    tags.setProjectId(tagsProperty.getTextContent());
                                } else if (tagsProperty.getNodeName().equals("resourceid")) {
                                    tags.setResourceId(tagsProperty.getTextContent());
                                } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                                    tags.setResourceType(tagsProperty.getTextContent());
                                } else if (tagsProperty.getNodeName().equals("value")) {
                                    tags.setValue(tagsProperty.getTextContent());
                                }
                            }
                            tagss.add(tags);
                        }

                    }

                    projectAccount.setTagss(tagss);
                    projectAccounts.add(projectAccount);
                }
            }
            response.setProjectAccounts(projectAccounts);
        }
        return response;
    }
}
