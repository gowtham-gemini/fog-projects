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
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.ticket.Ticket;
import com.kayako.api.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Staff.
 */
public class Staff extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller = "/Base/Staff";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "staff";
    /**
     * The Read only.
     */
    protected Boolean readOnly = false;

    /**
     * Instantiates a new Staff.
     */
    public Staff() {
    }

    /**
     * Instantiates a new Staff.
     *
     * @param element the raw array element
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public Staff(RawArrayElement element) throws KayakoException {
        this.populate(element);
    }

    /**
     * Instantiates a new Staff.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param userName the user name
     * @param email the email
     * @param staffGroup the staff group
     * @param password the password
     */
    public Staff(String firstName, String lastName, String userName, String email, StaffGroup staffGroup, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.setEmail(email);
        this.setStaffGroup(staffGroup);
        this.setPassword(password);
    }

    /**
     * Staff identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Staff group identifier.
     *
     * @apiField required_create =true
     * @var int
     */
    protected int staffGroupId = 0;
    /**
     * Staff firstName.
     *
     * @apiField
     * @var string
     */

    protected String firstName = "";

    /**
     * The Last name.
     */
    protected String lastName = "";

    /**
     * The Full name.
     */
    protected String fullName = "";

    /**
     * Staff username (login).
     *
     * @apiField required_create =true
     * @var string
     */
    protected String userName;

    /**
     * The Email.
     */
    protected String email;

    /**
     * The Designation.
     */
    protected String designation = "";
    /**
     * Staff livechat greeting message.
     *
     * @apiField
     * @var string
     */
    protected String greeting = "";

    /**
     * Staff signature appended to posts.
     *
     * @apiField
     * @var string
     */
    protected String signature = "";

    /**
     * The Mobile number.
     */
    protected String mobileNumber = "";

    /**
     * Is this staff enabled.
     *
     * @apiField
     * @var bool
     */
    protected Boolean enabled = true;

    /**
     * The Time zone.
     */
    protected String timeZone = "GMT";
    /**
     * Is Daylight Saving Time enabled.
     *
     * @apiField
     * @var bool
     */
    protected Boolean DST = false;

    /**
     * The Password.
     */
    protected String password = "";

    private StaffGroup staffGroup = null;

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {

        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param FirstName the first name
     * @return the first name
     */
    public Staff setFirstName(String FirstName) {
        this.firstName = FirstName;
        return this;
    }

    public int getId() {

        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public Staff setId(int id) {
        this.id = id;
        return this;
    }

    public Boolean getReadOnly() {

        return readOnly;
    }

    public Staff setReadOnly(Boolean readOnly) {
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
        Staff.objectXmlName = objectXmlName;
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
        Staff.controller = controller;

    }

    /**
     * Gets staff group id.
     *
     * @return the staff group id
     */
    public int getStaffGroupId() {
        return staffGroupId;
    }

    /**
     * Sets staff group id.
     *
     * @param staffGroupId the staff group id
     * @return the staff group id
     */
    public Staff setStaffGroupId(int staffGroupId) {
        this.staffGroupId = staffGroupId;
        this.staffGroup = null;
        return this;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     * @return the last name
     */
    public Staff setLastName(String lastName) {
        this.lastName = lastName;
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
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * Gets greeting.
     *
     * @return the greeting
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * Gets signature.
     *
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Sets signature.
     *
     * @param signature the signature
     * @return the signature
     */
    public Staff setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    /**
     * Sets greeting.
     *
     * @param greeting the greeting
     * @return the greeting
     */
    public Staff setGreeting(String greeting) {
        this.greeting = greeting;
        return this;
    }

    /**
     * Sets designation.
     *
     * @param designation the designation
     * @return the designation
     */
    public Staff setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    /**
     * Sets email.
     *
     * @param email the email
     * @return the email
     */
    public Staff setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     * @return the user name
     */
    public Staff setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Sets full name.
     *
     * @param fullName the full name
     * @return the full name
     */
    public Staff setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Gets mobile number.
     *
     * @return the mobile number
     */
    public String getMobileNumber() {
        return mobileNumber;
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
    public Staff setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
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
    public Staff setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Gets staff group.
     *
     * @return the staff group
     * @throws KayakoException the kayako exception
     */
    public StaffGroup getStaffGroup() throws KayakoException {
        return this.getStaffGroup(false);
    }

    /**
     * Gets staff group.
     *
     * @param refresh the refresh
     * @return the staff group
     * @throws KayakoException the kayako exception
     */
    public StaffGroup getStaffGroup(Boolean refresh) throws KayakoException {
        if ((this.staffGroup == null || refresh) && this.getStaffGroupId() > 0) {
            this.staffGroup = (StaffGroup) StaffGroup.get(this.getStaffGroupId());
        }
        return this.staffGroup;
    }

    /**
     * Sets staff group.
     *
     * @param staffGroup the staff group
     * @return the staff group
     */
    public Staff setStaffGroup(StaffGroup staffGroup) {
        this.setStaffGroupId(staffGroup.getId());
        this.staffGroup = staffGroup;
        return this;
    }

    /**
     * Sets dST.
     *
     * @param DST the dST
     * @return the dST
     */
    public Staff setDST(Boolean DST) {
        this.DST = DST;
        return this;
    }

    /**
     * Sets time zone.
     *
     * @param timeZone the time zone
     * @return the time zone
     */
    public Staff setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * Sets mobile number.
     *
     * @param mobileNumber the mobile number
     * @return the mobile number
     */
    public Staff setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    /**
     * Get staff.
     *
     * @param id the id
     * @return the staff
     * @throws KayakoException the kayako exception
     */
    public static Staff get(int id) throws KayakoException {
        return new Staff(get(controller, id));
    }

    /**
     * Save staff.
     *
     * @return the staff
     * @throws KayakoException the kayako exception
     */
    public Staff save() throws KayakoException {
        return (Staff) super.save(controller);
    }

    /**
     * Update staff.
     *
     * @return the staff
     * @throws KayakoException the kayako exception
     */
    public Staff update() throws KayakoException {
        return (Staff) super.update(controller);
    }

    /**
     * Create staff.
     *
     * @return the staff
     * @throws KayakoException the kayako exception
     */
    public Staff create() throws KayakoException {
        return (Staff) super.create(controller);
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
     * Refresh staff.
     *
     * @return the staff
     * @throws KayakoException the kayako exception
     */
    public Staff refresh() throws KayakoException {
        return (Staff) super.refresh(controller);
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement getAll() throws KayakoException {
        return getAll(controller);
    }

    //this function will populate the data of the staff instance when supplied with RawArrayElement derived from the xml
    @Override
    public Staff populate(RawArrayElement element) throws KayakoException {
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
            } else if (elementName.equals("staffgroupid")) {
                this.setStaffGroupId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("firstname")) {
                this.setFirstName(component.getContent());
            } else if (elementName.equals("lastName")) {
                this.setLastName(component.getContent());
            } else if (elementName.equals("fullname")) {
                this.setFullName(component.getContent());
            } else if (elementName.equals("username")) {
                this.setUserName(component.getContent());
            } else if (elementName.equals("email")) {
                this.setEmail(component.getContent());
            } else if (elementName.equals("designation")) {
                this.setDesignation(component.getContent());
            } else if (elementName.equals("greeting")) {
                this.setGreeting(component.getContent());
            } else if (elementName.equals("mobilenumber")) {
                this.setMobileNumber(component.getContent());
            } else if (elementName.equals("isenabled")) {
                this.setEnabled(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("enabledst")) {
                this.setDST(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("signature")) {
                this.setSignature(component.getContent());
            } else if (elementName.equals("timezone")) {
                this.setTimeZone(component.getContent());
            }
        }
        return this;
    }

    /**
     * Build hash map.
     *
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap(Boolean NewStaff) {
        HashMap<String, String> staffHashMap = new HashMap<String, String>();
        staffHashMap.put("firstname", this.getFirstName());
        staffHashMap.put("lastname", this.getLastName());
        staffHashMap.put("fullname", this.getFullName());
        staffHashMap.put("username", this.getUserName());
        if (this.getPassword().length() > 0) {
            staffHashMap.put("password", this.getPassword());
        }
        staffHashMap.put("email", this.getEmail());
        staffHashMap.put("designation", this.getDesignation());
        staffHashMap.put("mobilenumber", this.getMobileNumber());
        if (this.getSignature().length() > 0) {
            staffHashMap.put("staffsignature", this.getSignature());
        }
        staffHashMap.put("greeting", this.getGreeting());
        staffHashMap.put("staffgroupid", Integer.toString(this.getStaffGroupId()));
        staffHashMap.put("isenabled", this.isEnabled() ? "1" : "0");
        staffHashMap.put("enabledst", this.isDST() ? "1" : "0");
        return staffHashMap;
    }

    /**
     * Create ticket.
     *
     * @param department the department
     * @param content the content
     * @param subject the subject
     * @return the ticket
     */
    public Ticket createTicket(Department department, String content, String subject) {
        return new Ticket(department, this, content, subject);
    }

}
