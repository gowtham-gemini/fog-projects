package com.assistanz.cloud.cloudstack.users;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSUserService {

    private CloudStackServer server;

    public CSUserService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates a user for an account that already exists
     *
     * @param accountName Creates the user under the specified account. If no account is specified, the username will be
     * used as the account name.
     * @param emailId email ID
     * @param firstName first name
     * @param lastName last name
     * @param userName Unique user name.
     * @param password Hashed password (Default is MD5). If you wish to use any other hashing algorithm, you would need
     * to write a custom authentication adapter See Docs section.
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateUserResponse createUser(String accountName, String emailId,
            String firstName, String lastName, String userName, String password,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createUser", optional);
        arguments.add(new NameValuePair("account", accountName));
        arguments.add(new NameValuePair("email", emailId));
        arguments.add(new NameValuePair("firstname", firstName));
        arguments.add(new NameValuePair("lastname", lastName));
        arguments.add(new NameValuePair("username", userName));
        arguments.add(new NameValuePair("password", password));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateUserResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateUserResponse object
     *
     * @param doc
     * @return
     */
    private CreateUserResponse getCreateUserResponse(Document doc) {
        CreateUserResponse response = new CreateUserResponse();

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account name of the user
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as user Domain ID
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get created from XML and set as user email ID
        list = doc.getElementsByTagName("email");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEmail(node.getTextContent());
        }

        // get firstname from XML and set as user First Name
        list = doc.getElementsByTagName("firstname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirstName(node.getTextContent());
        }

        // the boolean value representing if the updating target is in caller's child domain
        list = doc.getElementsByTagName("iscallerchilddomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCallerChildDomain(node.getTextContent());
        }

        // true if user is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
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

        // get username from XML and set as user Name
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserName(node.getTextContent());
        }

        return response;
    }

    /**
     * delete user from the account
     *
     * @param accountId account id of the user
     * @return
     * @throws Exception
     */
    public DeleteUserResponse deleteUser(String userId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteUser", null);
        arguments.add(new NameValuePair("id", userId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteUserResponse(responseDocument);

    }

    /**
     * Converts XML document into DeleteUserResponse object
     *
     * @param doc
     * @return
     */
    private DeleteUserResponse getDeleteUserResponse(Document doc) {
        DeleteUserResponse response = new DeleteUserResponse();

        // any text associated with the success or failure
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // true if operation is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }
        return response;
    }

    /**
     * Updates a user account
     *
     * @param userId the User id
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateUserResponse updateUser(String userId,
            HashMap<String, String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateUser", optional);
        arguments.add(new NameValuePair("id", userId));
       
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateUserResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateUserResponse object
     *
     * @param doc
     * @return
     */
    private UpdateUserResponse getUpdateUserResponse(Document doc) {
        UpdateUserResponse response = new UpdateUserResponse();

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account name of the user
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as user Domain ID
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get created from XML and set as user email ID
        list = doc.getElementsByTagName("email");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEmail(node.getTextContent());
        }

        // get firstname from XML and set as user First Name
        list = doc.getElementsByTagName("firstname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirstName(node.getTextContent());
        }

        // the boolean value representing if the updating target is in caller's child domain
        list = doc.getElementsByTagName("iscallerchilddomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCallerChildDomain(node.getTextContent());
        }

        // true if user is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
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

        // get username from XML and set as user Name
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserName(node.getTextContent());
        }
        return response;
    }

    /**
     * Lists user accounts
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListUsersResponse listUsers(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listUsers", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListUsersResponse(responseDocument);
    }

    /**
     * Converts XML document into ListUsersResponse object
     *
     * @param doc
     * @return
     */
    private ListUsersResponse getListUsersResponse(Document doc) {
        ListUsersResponse response = new ListUsersResponse();

        NodeList list = doc.getElementsByTagName("user");

        List<UserResponse> listUsers = new LinkedList<UserResponse>();
        if (list.getLength() > 0) {
            for (int listUserIndex = 0; listUserIndex < list.getLength(); listUserIndex++) {
                Node listUserNode = list.item(listUserIndex);

                UserResponse listUser = new UserResponse();
                NodeList listUserProperties = listUserNode.getChildNodes();
                for (int childIndex = 0; childIndex < listUserProperties.getLength(); childIndex++) {
                    Node property = listUserProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        listUser.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("accountname")) {
                        listUser.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        listUser.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("accounttype")) {
                        listUser.setAccountType(property.getTextContent());
                    } else if (property.getNodeName().equals("apikey")) {
                        listUser.setApiKey(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        listUser.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domainname")) {
                        listUser.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        listUser.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        listUser.setEmail(property.getTextContent());
                    } else if (property.getNodeName().equals("firstname")) {
                        listUser.setFirstName(property.getTextContent());
                    } else if (property.getNodeName().equals("iscallerchilddomain")) {
                        listUser.setIsCallerChildDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        listUser.setIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("lastname")) {
                        listUser.setLastName(property.getTextContent());
                    } else if (property.getNodeName().equals("secretkey")) {
                        listUser.setSecretKey(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        listUser.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("timezone")) {
                        listUser.setTimeZone(property.getTextContent());
                    } else if (property.getNodeName().equals("username")) {
                        listUser.setUserName(property.getTextContent());
                    }
                }
                listUsers.add(listUser);
            }
        }
        response.setListUsers(listUsers);
        return response;
    }

    /**
     * Locks user
     *
     * @param userId the user id
     * @return
     * @throws Exception
     */
    public LockUserResponse lockUser(String userId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("lockUser", null);
        arguments.add(new NameValuePair("id", userId));

        Document responseDocument = server.makeRequest(arguments);

        return getLockUserResponse(responseDocument);

    }

    /**
     * Converts XML document into DisableLockResponse object
     *
     * @param doc
     * @return
     */
    private LockUserResponse getLockUserResponse(Document doc) {
        LockUserResponse response = new LockUserResponse();

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account name of the user
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as user Domain ID
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get created from XML and set as user email ID
        list = doc.getElementsByTagName("email");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEmail(node.getTextContent());
        }

        // get firstname from XML and set as user First Name
        list = doc.getElementsByTagName("firstname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirstName(node.getTextContent());
        }

        // the boolean value representing if the updating target is in caller's child domain
        list = doc.getElementsByTagName("iscallerchilddomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCallerChildDomain(node.getTextContent());
        }

        // true if user is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
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

        // get username from XML and set as user Name
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserName(node.getTextContent());
        }

        return response;
    }

    /**
     * Disables user
     *
     * @param userId the user id
     * @return
     * @throws Exception
     */
    public DisableUserResponse disableUser(String userId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("disableUser", null);
        arguments.add(new NameValuePair("id", userId));

        Document responseDocument = server.makeRequest(arguments);

        return getDisableUserResponse(responseDocument);

    }

    /**
     * Converts XML document into DisableUserResponse object
     *
     * @param doc
     * @return
     */
    private DisableUserResponse getDisableUserResponse(Document doc) {
        DisableUserResponse response = new DisableUserResponse();

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account name of the user
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as user Domain ID
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get created from XML and set as user email ID
        list = doc.getElementsByTagName("email");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEmail(node.getTextContent());
        }

        // get firstname from XML and set as user First Name
        list = doc.getElementsByTagName("firstname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirstName(node.getTextContent());
        }

        // the boolean value representing if the updating target is in caller's child domain
        list = doc.getElementsByTagName("iscallerchilddomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCallerChildDomain(node.getTextContent());
        }

        // true if user is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
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

        // get username from XML and set as user Name
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserName(node.getTextContent());
        }

        return response;
    }

    /**
     * Enables User
     *
     * @param userId the user id
     * @return
     * @throws Exception
     */
    public EnableUserResponse enableUser(String userId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("enableUser", null);
        arguments.add(new NameValuePair("id", userId));

        Document responseDocument = server.makeRequest(arguments);

        return getEnableUserResponse(responseDocument);

    }

    /**
     * Converts XML document into DisableUserResponse object
     *
     * @param doc
     * @return
     */
    private EnableUserResponse getEnableUserResponse(Document doc) {
        EnableUserResponse response = new EnableUserResponse();

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account name of the user
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as user Domain ID
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get created from XML and set as user email ID
        list = doc.getElementsByTagName("email");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEmail(node.getTextContent());
        }

        // get firstname from XML and set as user First Name
        list = doc.getElementsByTagName("firstname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirstName(node.getTextContent());
        }

        // the boolean value representing if the updating target is in caller's child domain
        list = doc.getElementsByTagName("iscallerchilddomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCallerChildDomain(node.getTextContent());
        }

        // true if user is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
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

        // get username from XML and set as user Name
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserName(node.getTextContent());
        }

        return response;
    }

    /**
     * Finds user account by API key
     *
     * @param userApiKey the user api key
     * @return
     * @throws Exception
     */
    public GetUserResponse GetUser(String userApiKey) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("getUser", null);
        arguments.add(new NameValuePair("userapikey", userApiKey));

        Document responseDocument = server.makeRequest(arguments);

        return getGetUserResponse(responseDocument);

    }

    /**
     * Converts XML document into DisableUserResponse object
     *
     * @param doc
     * @return
     */
    private GetUserResponse getGetUserResponse(Document doc) {
        GetUserResponse response = new GetUserResponse();

        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account name of the user
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as user Domain ID
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get created from XML and set as user email ID
        list = doc.getElementsByTagName("email");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEmail(node.getTextContent());
        }

        // get firstname from XML and set as user First Name
        list = doc.getElementsByTagName("firstname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirstName(node.getTextContent());
        }

        // the boolean value representing if the updating target is in caller's child domain
        list = doc.getElementsByTagName("iscallerchilddomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsCallerChildDomain(node.getTextContent());
        }

        // true if user is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
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

        // get username from XML and set as user Name
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserName(node.getTextContent());
        }

        return response;
    }

    /**
     * This command allows a user to register for the developer API, returning a secret key and an API key. This request
     * is made through the integration API port, so it is a privileged command and must be made on behalf of a user. It
     * is up to the implementer just how the username and password are entered, and then how that translates to an
     * integration API request. Both secret key and API key should be returned to the user
     *
     * @param userId The user id
     * @return
     * @throws Exception
     */
    public RegisterUserKeysResponse registerUserKeys(String userId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("registerUserKeys", null);
        arguments.add(new NameValuePair("id", userId));

        Document responseDocument = server.makeRequest(arguments);

        return getRegisterUserKeysResponse(responseDocument);

    }

    /**
     * Converts XML document into RegisterUserKeysResponse object
     *
     * @param doc
     * @return
     */
    private RegisterUserKeysResponse getRegisterUserKeysResponse(Document doc) {
        RegisterUserKeysResponse response = new RegisterUserKeysResponse();

        // get apikey from XML and set as user APIkey
        NodeList list = doc.getElementsByTagName("apikey");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setApiKey(node.getTextContent());
        }

        // get secretkey from XML and set as user Secret key
        list = doc.getElementsByTagName("secretkey");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecretKey(node.getTextContent());
        }

        return response;

    }

    /**
     * Adds vpn users
     *
     * @param password password for the username
     * @param userName username for the vpn user
     * @param optional
     * @return
     * @throws Exception
     */
    public AddVpnUserResponse addVpnUser(String password, String userName,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addVpnUser", optional);
        arguments.add(new NameValuePair("password", password));
        arguments.add(new NameValuePair("username", userName));

        Document responseDocument = server.makeRequest(arguments);

        return getAddVpnUserResponse(responseDocument);

    }

    /**
     * Converts XML document into AddVpnUserResponse object
     *
     * @param doc
     * @return
     */
    private AddVpnUserResponse getAddVpnUserResponse(Document doc) {
        AddVpnUserResponse response = new AddVpnUserResponse();

        // get id from XML and set the vpn userID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnUserId(node.getTextContent());
        }

        // get account from XML and set as the account of the remote access vpn
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnAccount(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the account of the remote access vpn
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnDomainName(node.getTextContent());
        }

        // get domainid from XML and set as the account of the remote access vpn
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnDomainId(node.getTextContent());
        }

        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnProjectName(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnProjectId(node.getTextContent());
        }

        // get username from XML and set as the user name of the vpn user
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnUserName(node.getTextContent());
        }

        return response;

    }

    /**
     * Removes vpn user
     *
     * @param username username for the vpn user
     * @param optional
     * @return
     * @throws Exception
     */
    public RemoveVpnUserResponse removeVpnUser(String username,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("removeVpnUser", optional);
        arguments.add(new NameValuePair("username", username));

        Document responseDocument = server.makeRequest(arguments);

        return getRemoveVpnUserResponse(responseDocument);

    }

    /**
     * Converts XML document into RemoveVpnUserResponse object
     *
     * @param doc
     * @return
     */
    private RemoveVpnUserResponse getRemoveVpnUserResponse(Document doc) {
        RemoveVpnUserResponse response = new RemoveVpnUserResponse();

        // get displaytext from XML and set Any text associated with the success or 
        // failure on Removing Vpn 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get success from XML and set Return true if Remove Vpn  operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists vpn users
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListVpnUsersResponse listVpnUsers(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVpnUsers", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListVpnUsersResponse(responseDocument);

    }

    /**
     * Converts XML document into ListVpnUsersResponse object
     *
     * @param doc
     * @return
     */
    private ListVpnUsersResponse getListVpnUsersResponse(Document doc) {
        ListVpnUsersResponse response = new ListVpnUsersResponse();

        // get id from XML and set the vpn userID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnUserId(node.getTextContent());
        }

        // get account from XML and set as the account of the remote access vpn
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnAccount(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the account of the remote access vpn
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnDomainName(node.getTextContent());
        }

        // get domainid from XML and set as the account of the remote access vpn
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnDomainId(node.getTextContent());
        }

        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnProjectName(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnProjectId(node.getTextContent());
        }

        // get username from XML and set as the user name of the vpn user
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnUserName(node.getTextContent());
        }

        return response;

    }
}
