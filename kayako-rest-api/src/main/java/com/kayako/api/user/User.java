package com.kayako.api.user;

/*
Copyright (c) 2013 Kayako

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

import com.kayako.api.department.Department;
import com.kayako.api.enums.SalutationEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.ticket.Ticket;
import com.kayako.api.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type User.
 */
public class User extends KEntity {
    public static final String ROLE_USER = "user";
    public static final int DEFAULT_MAX_USERS = 1000;
    /**
     * The constant ROLE_MANAGER.
     */
    public static final String ROLE_MANAGER = "manager";

    /**
     * The Controller.
     */
    static protected String controller = "/Base/User";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "user";

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param name      the name
     * @param email     the email
     * @param userGroup the user group
     * @param password  the password
     */
    public User(String name, String email, UserGroup userGroup, String password) {
        this.setFullName(name);
        this.setEmail(email);
        this.setUserGroup(userGroup);
        this.setPassword(password);
    }

    /**
     * User identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * User group identifier.
     *
     * @apiField required_create =true
     * @var int
     */
    protected int userGroupId = 0;

    /**
     * User role.
     *
     * @apiField
     * @var string
     * @see ::ROLE constants.
     */
    protected String userRole = ROLE_USER;
    /**
     * The User organization id.
     */
    protected int userOrganizationId = 0;

    /**
     * User salutation.
     *
     * @apiField
     * @var string
     * @see ::SALUTATION constants.
     */
    protected SalutationEnum salutation = null;

    /**
     * The User expiry.
     */
    protected int userExpiry = 0;

    /**
     * The Designation.
     */
    protected String designation = "";

    /**
     * The Full name.
     */
    protected String fullName = "";

    /**
     * The Emails.
     */
    protected ArrayList<String> emails = new ArrayList<String>();

    /**
     * The Phone.
     */
    protected String phone = "";

    /**
     * The Date line.
     */
    protected int dateLine;

    /**
     * The Last visit.
     */
    protected int lastVisit;

    /**
     * The Enabled.
     */
    protected Boolean enabled = true;

    /**
     * The SLA plan id.
     */
    protected int SLAPlanId;

    /**
     * The SLA plan expiry.
     */
    protected int SLAPlanExpiry;

    /**
     * The Send welcome email.
     */
    protected Boolean sendWelcomeEmail = true;

    /**
     * The Password.
     */
    protected String password;

    /**
     * Timezone of the user.
     *
     * @apiField
     * @var string
     */
    protected String timeZone = "GMT";
    /**
     * Is Daylight Saving Time enabled.
     *
     * @apiField
     * @var bool
     */
    protected Boolean DST = false;

    private UserGroup userGroup = null;

    private UserOrganization userOrganization = null;

    public int getId() {

        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public User setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Is read only.
     *
     * @return the boolean
     */
    public Boolean isReadOnly() {

        return readOnly;
    }

    public User setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    /**
     * Gets object xml name.
     *
     * @return the object xml name
     */
    public static String getObjectXmlName() {

        return objectXmlName;
    }

    /**
     * Sets object xml name.
     *
     * @param objectXmlName the object xml name
     */
    public static void setObjectXmlName(String objectXmlName) {
        User.objectXmlName = objectXmlName;
    }

    /**
     * Gets controller.
     *
     * @return the controller
     */
    public static String getController() {

        return controller;
    }

    /**
     * Sets controller.
     *
     * @param controller the controller
     */
    public static void setController(String controller) {
        User.controller = controller;

    }

    /**
     * Gets user group id.
     *
     * @return the user group id
     */
    public int getUserGroupId() {
        return userGroupId;
    }

    /**
     * Sets user group id.
     *
     * @param userGroupId the user group id
     * @return the user group id
     */
    public User setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
        this.userGroup = null;
        return this;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        if (emails.size() > 0) {
            return emails.get(0);
        } else {
            return null;
        }

    }

    /**
     * Sets email.
     *
     * @param email the email
     * @return the email
     */
    public User setEmail(String email) {
        this.emails.add(email);
        return this;
    }

    /**
     * Sets full name.
     *
     * @param fullName the full name
     * @return the full name
     */
    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets time zone.
     *
     * @return the time zone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Is dST.
     *
     * @return the boolean
     */
    public Boolean isDST() {
        return DST;
    }

    /**
     * Gets user group.
     *
     * @return the user group
     * @throws com.kayako.api.exception.KayakoException
     *          the kayako exception
     */
    public UserGroup getUserGroup() throws KayakoException {
        return this.getUserGroup(false);
    }

    /**
     * Gets user group.
     *
     * @param refresh the refresh
     * @return the user group
     * @throws KayakoException the kayako exception
     */
    public UserGroup getUserGroup(Boolean refresh) throws KayakoException {
        if ((this.userGroup == null || refresh) && this.getUserGroupId() > 0) {
            this.userGroup = (UserGroup) UserGroup.get(this.getUserGroupId());
        }
        return this.userGroup;
    }

    /**
     * Sets user group.
     *
     * @param userGroup the user group
     * @return the user group
     */
    public User setUserGroup(UserGroup userGroup) {
        this.setUserGroupId(userGroup.getId());
        this.userGroup = userGroup;
        return this;
    }

    /**
     * Sets dST.
     *
     * @param DST the dST
     * @return the dST
     */
    public User setDST(Boolean DST) {
        this.DST = DST;
        return this;
    }

    /**
     * Sets time zone.
     *
     * @param timeZone the time zone
     * @return the time zone
     */
    public User setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     * @return the phone
     */
    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     * @return the password
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     * @return the user role
     */
    public User setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    /**
     * Gets salutation.
     *
     * @return the salutation
     */
    public SalutationEnum getSalutation() {
        return salutation;
    }

    /**
     * Sets salutation.
     *
     * @param salutation the salutation
     * @return the salutation
     */
    public User setSalutation(SalutationEnum salutation) {
        this.salutation = salutation;
        return this;
    }

    /**
     * Gets emails.
     *
     * @return the emails
     */
    public ArrayList<String> getEmails() {
        return emails;
    }

    /**
     * Sets emails.
     *
     * @param emails the emails
     * @return the emails
     */
    public User setEmails(ArrayList<String> emails) {
        this.emails = emails;
        return this;
    }

    /**
     * Add email.
     *
     * @param email the email
     * @return the user
     */
    public User addEmail(String email) {
        this.getEmails().add(email);
        return this;
    }

    /**
     * Gets user expiry.
     *
     * @return the user expiry
     */
    public int getUserExpiry() {
        return userExpiry;
    }

    /**
     * Sets user expiry.
     *
     * @param userExpiry the user expiry
     * @return the user expiry
     */
    public User setUserExpiry(int userExpiry) {
        this.userExpiry = userExpiry;
        return this;
    }

    /**
     * Gets user organization id.
     *
     * @return the user organization id
     */
    public int getUserOrganizationId() {
        return userOrganizationId;
    }

    /**
     * Sets user organization id.
     *
     * @param userOrganizationId the user organization id
     * @return the user organization id
     */
    public User setUserOrganizationId(int userOrganizationId) {
        this.userOrganizationId = userOrganizationId;
        this.userOrganization = null;
        return this;
    }

    /**
     * Gets designation.
     *
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets designation.
     *
     * @param designation the designation
     * @return the designation
     */
    public User setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    /**
     * Gets date line.
     *
     * @return the date line
     */
    public int getDateLine() {
        return dateLine;
    }

    /**
     * Sets date line.
     *
     * @param dateLine the date line
     * @return the date line
     */
    public User setDateLine(int dateLine) {
        this.dateLine = dateLine;
        return this;
    }

    /**
     * Gets last visit.
     *
     * @return the last visit
     */
    public int getLastVisit() {
        return lastVisit;
    }

    /**
     * Sets last visit.
     *
     * @param lastVisit the last visit
     * @return the last visit
     */
    public User setLastVisit(int lastVisit) {
        this.lastVisit = lastVisit;
        return this;
    }

    /**
     * Is enabled.
     *
     * @return the boolean
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enabled.
     *
     * @param enabled the enabled
     * @return the enabled
     */
    public User setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Gets sLA plan id.
     *
     * @return the sLA plan id
     */
    public int getSLAPlanId() {
        return SLAPlanId;
    }

    /**
     * Sets sLA plan id.
     *
     * @param SLAPlanId the sLA plan id
     * @return the sLA plan id
     */
    public User setSLAPlanId(int SLAPlanId) {
        this.SLAPlanId = SLAPlanId;
        return this;
    }

    /**
     * Gets sLA plan expiry.
     *
     * @return the sLA plan expiry
     */
    public int getSLAPlanExpiry() {
        return SLAPlanExpiry;
    }

    /**
     * Sets sLA plan expiry.
     *
     * @param SLAPlanExpiry the sLA plan expiry
     * @return the sLA plan expiry
     */
    public User setSLAPlanExpiry(int SLAPlanExpiry) {
        this.SLAPlanExpiry = SLAPlanExpiry;
        return this;
    }

    /**
     * Is send welcome email.
     *
     * @return the boolean
     */
    public Boolean isSendWelcomeEmail() {
        return sendWelcomeEmail;
    }

    /**
     * Sets send welcome email.
     *
     * @param sendWelcomeEmail the send welcome email
     * @return the send welcome email
     */
    public User setSendWelcomeEmail(Boolean sendWelcomeEmail) {
        this.sendWelcomeEmail = sendWelcomeEmail;
        return this;
    }

    /**
     * Gets user organization.
     *
     * @return the user organization
     * @throws KayakoException the kayako exception
     */
    public UserOrganization getUserOrganization() throws KayakoException {
        return this.getUserOrganization(false);
    }

    /**
     * Gets user organization.
     *
     * @param refresh the refresh
     * @return the user organization
     * @throws KayakoException the kayako exception
     */
    public UserOrganization getUserOrganization(Boolean refresh) throws KayakoException {
        if ((this.userOrganization == null || refresh) && this.getUserOrganizationId() > 0) {
            this.userOrganization = (UserOrganization) UserOrganization.get(this.getUserOrganizationId());
        }
        return this.userOrganization;
    }

    /**
     * Sets user organization.
     *
     * @param userOrganization the user organization
     * @return the user organization
     */
    public User setUserOrganization(UserOrganization userOrganization) {
        this.setUserOrganizationId(userOrganization.getId());
        this.userOrganization = userOrganization;
        return this;
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement getAll() throws KayakoException {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("Filter");
        return getAll(controller, searchParams);
    }

    /**
     * Gets all.
     *
     * @param startingUserId the starting user id
     * @return the all
     */
    public static RawArrayElement getAll(int startingUserId) {
        return User.getAll(startingUserId, DEFAULT_MAX_USERS);
    }

    /**
     * Gets all.
     *
     * @param startingUserId the starting user id
     * @param maxItems       the max items
     * @return the all
     */
    public static RawArrayElement getAll(int startingUserId, int maxItems) {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("Filter");
        searchParams.add(Integer.toString(startingUserId));
        searchParams.add(Integer.toString(maxItems));
        return getAll(controller, searchParams);
    }

    /**
     * Gets all users.
     *
     * @return the all users
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<User> getAllUsers() throws KayakoException {
        return User.refineToArray(User.getAll());
    }

    /**
     * Gets all users.
     *
     * @param startingUserId the starting user id
     * @return the all users
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<User> getAllUsers(int startingUserId) throws KayakoException {
        return User.getAllUsers(startingUserId, DEFAULT_MAX_USERS);
    }

    private static ArrayList<User> refineToArray(RawArrayElement element) throws KayakoException {
        ArrayList<User> users = new ArrayList<User>();
        for (RawArrayElement rawArrayElementUser : element.getComponents()) {
            users.add(new User().populate(rawArrayElementUser));
        }
        return users;
    }

    /**
     * Gets all users.
     *
     * @param startingUserId the starting user id
     * @param maxItems       the max items
     * @return the all users
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<User> getAllUsers(int startingUserId, int maxItems) throws KayakoException {
        return User.refineToArray(User.getAll(startingUserId, maxItems));

    }

    /**
     * Search array list.
     *
     * @param query the query
     * @return the array list
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<User> search(String query) throws KayakoException {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("query", query);
        return refineToArray(getRESTClient().post("/Base/UserSearch", new ArrayList<String>(), data).filterByComponentName(objectXmlName));
    }

    /**
     * Save user.
     *
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public User save() throws KayakoException {
        return (User) super.save(controller);
    }

    /**
     * Create user.
     *
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public User create() throws KayakoException {
        return (User) super.create(controller);
    }

    /**
     * Delete boolean.
     *
     * @return the boolean
     * @throws KayakoException the kayako exception
     */
    public Boolean delete() throws KayakoException {
        return super.delete(controller);
    }

    /**
     * Refresh user.
     *
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public User refresh() throws KayakoException {
        return (User) super.refresh(controller);
    }

    //this function will populate the data of the user instance when supplied with RawArrayElement derived from the xml
    @Override
    public User populate(RawArrayElement element) throws KayakoException {
        if (!element.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        ArrayList<RawArrayElement> components = element.getComponents();
        for (RawArrayElement component : components) {
            String elementName = component.getElementName();
            if (!component.isComposite() && component.getContent() == null) {
                break;
            }
            if (elementName.equals("id")) {
                this.setId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("usergroupid")) {
                this.setUserGroupId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("userrole")) {
                this.setUserRole(component.getContent());
            } else if (elementName.equals("userorganizationid")) {
                this.setUserOrganizationId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("salutation")) {
                this.setSalutation(SalutationEnum.getEnum(component.getContent()));
            } else if (elementName.equals("userexpiry")) {
                this.setUserExpiry(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("fullname")) {
                this.setFullName(component.getContent());
            } else if (elementName.equals("email")) {
                this.setEmail(component.getContent());
            } else if (elementName.equals("designation")) {
                this.setDesignation(component.getContent());
            } else if (elementName.equals("phone")) {
                this.setPhone(component.getContent());
            } else if (elementName.equals("dateline")) {
                this.setDateLine(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("lastvisit")) {
                this.setLastVisit(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("isenabled")) {
                this.setEnabled(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("timezone")) {
                this.setTimeZone(component.getContent());
            } else if (elementName.equals("enabledst")) {
                this.setDST(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("slaplanid")) {
                this.setSLAPlanId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("slaplanexpiry")) {
                this.setSLAPlanExpiry(Helper.parseInt(component.getContent()));
            }
        }
        return this;
    }

    /**
     * Get user.
     *
     * @param id the id
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public static User get(int id) throws KayakoException {
        return new User().populate(get(controller, id));
    }

    /**
     * Update user.
     *
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public User update() throws KayakoException {
        return (User) super.update(controller);
    }

    /**
     * Build hash map.
     *
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    /**
     * Build hash map.
     *
     * @param newUser the new user
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap(Boolean newUser) {
        HashMap<String, String> userHashMap = new HashMap<String, String>();
        userHashMap.put("fullname", this.getFullName());
        userHashMap.put("usergroupid", Integer.toString(this.getUserGroupId()));
        if (newUser) {
            userHashMap.put("password", this.getPassword());
            userHashMap.put("sendwelcomeemail", this.isSendWelcomeEmail() ? "1" : "0");
        }
        userHashMap.put("email", this.getEmail());
        userHashMap.put("userorganizationid", Integer.toString(this.getUserOrganizationId()));
        userHashMap.put("salutation", this.getSalutation() == null ? "" : this.getSalutation().getString());
        userHashMap.put("designation", this.getDesignation());
        userHashMap.put("phone", this.getPhone());
        userHashMap.put("isenabled", this.isEnabled() ? "1" : "0");
        userHashMap.put("userrole", this.getUserRole());
        userHashMap.put("timezone", this.getTimeZone());
        userHashMap.put("enabledst", this.isDST() ? "1" : "0");
        userHashMap.put("slaplanid", Integer.toString(this.getSLAPlanId()));
        userHashMap.put("slaplanexpiry", Integer.toString(this.getSLAPlanExpiry()));
        userHashMap.put("userexpiry", Integer.toString(this.getUserExpiry()));

        return userHashMap;
    }

    /**
     * Create ticket.
     *
     * @param department the department
     * @param content    the content
     * @param subject    the subject
     * @return the ticket
     * @throws KayakoException the kayako exception
     */
    public Ticket createTicket(Department department, String content, String subject) throws KayakoException {
        return new Ticket(department, this, content, subject);
    }

}
