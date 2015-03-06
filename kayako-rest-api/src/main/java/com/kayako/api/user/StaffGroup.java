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

import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Staff group.
 */
public class StaffGroup extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller = "/Base/StaffGroup";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "staffgroup";

    /**
     * Instantiates a new Staff group.
     *
     * @param element the raw array element
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public StaffGroup(RawArrayElement element) throws KayakoException {
        this.populate(element);
    }

    /**
     * Instantiates a new Staff group.
     *
     * @param title the title
     */
    public StaffGroup(String title) {
        this(title, false);
    }

    /**
     * Instantiates a new Staff group.
     *
     * @param title the title
     * @param admin the admin
     */
    public StaffGroup(String title, Boolean admin) {
        this.setTitle(title);
        this.setAdmin(admin);
    }

    /**
     * Staff Group identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Staff Group title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Whether this staff group is admin group (built-in).
     *
     * @apiField
     * @var bool
     */
    protected Boolean admin;

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
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {

        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    public Boolean getReadOnly() {

        return readOnly;
    }

    public StaffGroup setReadOnly(Boolean readOnly) {
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
        StaffGroup.objectXmlName = objectXmlName;
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
        StaffGroup.controller = controller;
    }

    /**
     * Is admin.
     *
     * @return the boolean
     */
    public Boolean isAdmin() {
        return admin;
    }

    /**
     * Sets admin.
     *
     * @param admin the admin
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     * Get staff group.
     *
     * @param id the id
     * @return the staff group
     * @throws KayakoException the kayako exception
     */
    public static StaffGroup get(int id) throws KayakoException {
        return new StaffGroup(get(controller, id));
    }


    /**
     * Save k entity.
     *
     * @return the k entity
     * @throws KayakoException the kayako exception
     */
    public StaffGroup save() throws KayakoException {
        return (StaffGroup) super.save(controller);
    }

    /**
     * Create k entity.
     *
     * @return the k entity
     * @throws KayakoException the kayako exception
     */
    public StaffGroup create() throws KayakoException {
        return (StaffGroup) super.create(controller);
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
    public StaffGroup refresh() throws KayakoException {
        return (StaffGroup) super.refresh(controller);
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

    //this function will populate the data of the staff Group instance when supplied with RawArrayElement derived from the xml
    @Override
    public StaffGroup populate(RawArrayElement element) throws KayakoException {
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
            } else if (elementName.equals("isadmin")) {
                this.setAdmin(Helper.parseInt(component.getContent()) == 1);
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> staffGroupHashMap = new HashMap<String, String>();
        staffGroupHashMap.put("title", this.getTitle());
        staffGroupHashMap.put("isadmin", this.isAdmin() ? "1" : "0");
        return staffGroupHashMap;
    }

    /**
     * Create staff.
     *
     * @param firstname the firstname
     * @param lastName the last name
     * @param userName the user name
     * @param email the email
     * @param password the password
     * @return the staff
     */
    public Staff createStaff(String firstname, String lastName, String userName, String email, String password) {
        return new Staff(firstname, lastName, userName, email, this, password);
    }
}
