package com.kayako.api.department;

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

import com.kayako.api.enums.AccessTypeEnum;
import com.kayako.api.enums.AppEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.ticket.Ticket;
import com.kayako.api.user.UserGroup;
import com.kayako.api.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Department.
 */
public class Department extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller = "/Base/Department";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "department";
    /**
     * The Read only.
     */
    protected Boolean readOnly = false;

    /**
     * Instantiates a new Department.
     */
    public Department() {
        this.setType(AccessTypeEnum.PUBLIC);
    }

    /**
     * Instantiates a new Department.
     *
     * @param title the title
     */
    public Department(String title) {
        this(title, AccessTypeEnum.PUBLIC);
    }

    /**
     * Instantiates a new Department.
     *
     * @param title the title
     * @param type  the type
     */
    public Department(String title, AccessTypeEnum type) {
        this(title, type, AppEnum.TICKETS);
    }

    /**
     * Instantiates a new Department.
     *
     * @param title the title
     * @param type  the type
     * @param app   the app
     */
    public Department(String title, AccessTypeEnum type, AppEnum app) {
        this.title = title;
        this.type = type;
        this.app = app;
    }

    /**
     * Department identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Department title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Department display order.
     *
     * @apiField
     * @var int
     */
    protected int displayOrder;

    /**
     * Linked department identifier.
     * <p/>
     * If a department is linked to a department, it will be visible only under the linked department.
     *
     * @apiField
     * @var int
     */
    protected int parentDepartmentId = 0;

    /**
     * Path to icon displayed in GUI for this department.
     *
     * @apiField
     * @var string
     */
    protected String displayIcon;

    /**
     * If this department is visible to specific user groups only.
     *
     * @apiField
     * @var bool
     */
    protected Boolean userVisibilityCustom = false;

    /**
     * Identifiers of user groups which can change department to this status.
     *
     * @apiField name = usergroupid
     * @var int[]
     */
    protected ArrayList<Integer> userGroupIds = new ArrayList<Integer>();

    /**
     * User groups which can change department to this status.
     *
     * @var UserGroup[]
     */
    private HashMap<Integer, UserGroup> userGroups = new HashMap<Integer, UserGroup>();

    protected AccessTypeEnum type;

    /**
     * Department app.
     *
     * @apiField required_create =true
     * @var int
     */

    protected AppEnum app;

    /**
     * Parent department.
     *
     * @var Department
     */

    protected Department parentDepartment = null;

    /**
     * Gets parent department.
     *
     * @return the parent department
     * @throws com.kayako.api.exception.KayakoException
     *          the kayako exception
     */
    public Department getParentDepartment() throws KayakoException {
        return this.getParentDepartment(false);
    }

    /**
     * Gets parent department.
     *
     * @param refresh the refresh
     * @return the parent department
     * @throws KayakoException the kayako exception
     */
    public Department getParentDepartment(Boolean refresh) throws KayakoException {
        if (parentDepartment != null && !refresh) {
            return parentDepartment;
        }
        if (this.getParentDepartmentId() <= 0) {
            return null;
        }
        this.parentDepartment = (Department) Department.get(this.getParentDepartmentId());
        return this.parentDepartment;

    }

    /**
     * Get department.
     *
     * @param id the id
     * @return the department
     * @throws KayakoException the kayako exception
     */
    public static Department get(int id) throws KayakoException {
        return new Department().populate(KEntity.get(controller, id));
    }

    /**
     * Sets parent department.
     *
     * @param parentDepartment the parent department
     * @return the parent department
     */
    public Department setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
        if (parentDepartment != null && parentDepartment.getId() > 0) {
            this.setParentDepartmentId(parentDepartment.getId());
        }
        return this;
    }

    /**
     * Is user visibility custom.
     *
     * @return the boolean
     */
    public Boolean isUserVisibilityCustom() {

        return userVisibilityCustom;
    }

    /**
     * Sets user visibility custom.
     *
     * @param userVisibilityCustom the user visibility custom
     * @return the user visibility custom
     */
    public Department setUserVisibilityCustom(Boolean userVisibilityCustom) {
        this.userVisibilityCustom = userVisibilityCustom;
        return this;
    }

    /**
     * Gets display icon.
     *
     * @return the display icon
     */
    public String getDisplayIcon() {

        return displayIcon;
    }

    /**
     * Sets display icon.
     *
     * @param displayIcon the display icon
     * @return the display icon
     */
    public Department setDisplayIcon(String displayIcon) {
        this.displayIcon = displayIcon;
        return this;
    }

    /**
     * Gets parent department id.
     *
     * @return the parent department id
     */
    public int getParentDepartmentId() {

        return parentDepartmentId;
    }

    /**
     * Sets parent department id.
     *
     * @param parentDepartmentId the parent department id
     * @return the parent department id
     */
    public Department setParentDepartmentId(int parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
        return this;
    }

    /**
     * Gets display order.
     *
     * @return the display order
     */
    public int getDisplayOrder() {

        return displayOrder;
    }

    /**
     * Sets display order.
     *
     * @param displayOrder the display order
     * @return the display order
     */
    public Department setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {

        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     * @return the title
     */
    public Department setTitle(String title) {
        this.title = title;
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
    public Department setId(int id) {
        this.id = id;
        return this;
    }

    public Boolean getReadOnly() {

        return readOnly;
    }

    public Department setReadOnly(Boolean readOnly) {
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
        Department.objectXmlName = objectXmlName;
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
        Department.controller = controller;

    }

    /**
     * Gets user groups.
     *
     * @return the user groups
     * @throws KayakoException the kayako exception
     */
    public HashMap<Integer, UserGroup> getUserGroups() throws KayakoException {
        return getUserGroups(false);
    }

    /**
     * Gets user groups.
     *
     * @param refresh the refresh
     * @return the user groups
     * @throws KayakoException the kayako exception
     */
    public HashMap<Integer, UserGroup> getUserGroups(Boolean refresh) throws KayakoException {
        for (int userGroupId : this.getUserGroupIds()) {
            if (!userGroups.containsKey(userGroupId) || refresh) {
                userGroups.put(userGroupId, UserGroup.get(userGroupId));
            }
        }
        return userGroups;
    }

    /**
     * Sets user groups.
     *
     * @param userGroups the user groups
     * @return the user groups
     */
    public Department setUserGroups(HashMap<Integer, UserGroup> userGroups) {
        this.userGroups = userGroups;
        return this;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public AccessTypeEnum getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     * @return the type
     */
    public Department setType(AccessTypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * Gets user group ids.
     *
     * @return the user group ids
     */
    public ArrayList<Integer> getUserGroupIds() {
        return userGroupIds;
    }

    /**
     * Sets user group ids.
     *
     * @param userGroupIds the user group ids
     * @return the user group ids
     */
    public Department setUserGroupIds(ArrayList<Integer> userGroupIds) {
        this.userGroupIds = userGroupIds;
        return this;
    }

    /**
     * Add user group.
     *
     * @param userGroup the user group
     * @return the department
     */
    public Department addUserGroup(UserGroup userGroup) {
        if (userGroupIds.contains(userGroup.getId())) {
            return this;
        }
        this.userGroups.put(userGroup.getId(), userGroup);
        this.userGroupIds.add(userGroup.getId());
        this.setUserVisibilityCustom(true);
        return this;
    }

    /**
     * Is visible to user group.
     *
     * @param userGroupId the user group id
     * @return the boolean
     */
    public Boolean isVisibleToUserGroup(int userGroupId) {
        if (!isUserVisibilityCustom()) {
            return true;
        }
        return this.userGroupIds.contains(userGroupId);
    }

    /**
     * Is visible to user group.
     *
     * @param userGroup the user group
     * @return the boolean
     */
    public Boolean isVisibleToUserGroup(UserGroup userGroup) {
        return this.isVisibleToUserGroup(userGroup.getId());
    }

    /**
     * Gets app.
     *
     * @return the app
     */
    public AppEnum getApp() {
        return app;
    }

    /**
     * Sets app.
     *
     * @param app the app
     * @return the app
     */
    public Department setApp(AppEnum app) {
        this.app = app;
        return this;
    }

    /**
     * Gets all as RawArrayElement.
     *
     * @return the all
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement getAll() throws KayakoException {
        return KEntity.getAll(controller);
    }

    private static ArrayList<Department> refineToArray(RawArrayElement element) throws KayakoException {
        ArrayList<Department> departments = new ArrayList<Department>();
        for (RawArrayElement rawArrayElementDepartment : element.getComponents()) {
            departments.add(new Department().populate(rawArrayElementDepartment));
        }
        return departments;
    }

    public String toString() {
        return "Department : " + this.getTitle();
    }

    /**
     * Gets all departments.
     *
     * @return the all departments
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<Department> getAllDepartments() throws KayakoException {
        return refineToArray(getAll());
    }

    /**
     * Create auto ticket.
     *
     * @param creatorFullName the creator full name
     * @param email           the email
     * @param contents        the contents
     * @param subject         the subject
     * @return the ticket
     * @throws KayakoException the kayako exception
     */
    public Ticket createAutoTicket(String creatorFullName, String email, String contents, String subject) throws KayakoException {
        return new Ticket(this, creatorFullName, email, contents, subject);
    }

    /**
     * Creates new subDepartment in this department. Module of new department will be the same as parent department's module.
     * WARNING: Data is not sent to Kayako unless you explicitly call create() on this method's result.
     *
     * @param title Title of new department.
     * @return Department department
     */
    public Department createSubDepartment(String title) {
        return this.createSubDepartment(title, AccessTypeEnum.PUBLIC);
    }

    /**
     * Save k entity.
     *
     * @return the k entity
     * @throws KayakoException the kayako exception
     */
    public Department save() throws KayakoException {
        return (Department) super.save(controller);
    }

    /**
     * Create k entity.
     *
     * @return the k entity
     * @throws KayakoException the kayako exception
     */
    public Department create() throws KayakoException {
        return (Department) super.create(controller);
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
     * Refresh k entity.
     *
     * @return the k entity
     * @throws KayakoException the kayako exception
     */
    public Department refresh() throws KayakoException {
        return (Department) super.refresh(controller);
    }

    /**
     * Create sub department.
     *
     * @param title the title
     * @param type  the type
     * @return the department
     */
    public Department createSubDepartment(String title, AccessTypeEnum type) {
        return new Department(title, type, this.getApp()).setParentDepartment(this);
    }

    //this function will populate the data of the department instance when supplied with RawArrayElement derived from the xml
    @Override
    public Department populate(RawArrayElement element) throws KayakoException {
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
            } else if (elementName.equals("title")) {
                this.setTitle(component.getContent());
            } else if (elementName.equals("displayorder")) {
                this.setDisplayOrder(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("departmentid")) {
                this.setParentDepartmentId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("displayicon")) {
                this.setDisplayIcon(component.getContent());
            } else if (elementName.equals("type")) {
                this.setType(AccessTypeEnum.getEnum(component.getContent()));
            } else if (elementName.equals("app")) {
                this.setApp(AppEnum.getEnum(component.getContent()));
            } else if (elementName.equals("uservisibilitycustom")) {
                if (Helper.parseInt(component.getContent()) == 1) {
                    this.setUserVisibilityCustom(true);
                } else {
                    this.setUserVisibilityCustom(false);
                }
            } else if (elementName.equals("usergroups")) {
                ArrayList<RawArrayElement> userGroupElements = component.getComponents();
                for (RawArrayElement innerComponent : userGroupElements) {
                    if (innerComponent.getElementName().equals("id")) {
                        this.userGroupIds.add(new Integer(innerComponent.getContent()));
                    }

                }

            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap(Boolean newDepartment) {
        HashMap<String, String> departmentHashMap = new HashMap<String, String>();
        departmentHashMap.put("title", this.getTitle());
        departmentHashMap.put("type", this.getType().getString());
        departmentHashMap.put("app", this.getApp().getString());
        departmentHashMap.put("displayorder", Integer.toString(this.getDisplayOrder()));
        departmentHashMap.put("parentdepartmentid", Integer.toString(this.getParentDepartmentId()));
        departmentHashMap.put("uservisibilitycustom", this.isUserVisibilityCustom() ? "1" : "0");
        if (this.isUserVisibilityCustom()) {
            for (Integer id : this.getUserGroupIds()) {
                departmentHashMap.put("usergroupid[]", id.toString());
            }
        }
        return departmentHashMap;
    }
}
