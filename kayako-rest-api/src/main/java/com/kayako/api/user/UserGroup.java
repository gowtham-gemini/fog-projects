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
 * The type User group.
 */
public class UserGroup extends KEntity {

    public final static String TYPE_REGISTERED = "registered";
    public final static String TYPE_GUEST = "guest";

    /**
     * The Controller.
     */
    static protected String controller = "/Base/UserGroup";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "usergroup";

    /**
     * Instantiates a new User group.
     */
    public UserGroup() throws KayakoException {
    }

    /**
     * Instantiates a new User group.
     *
     * @param title the title
     */
    public UserGroup(String title) {
        this(title, TYPE_REGISTERED);
    }

    /**
     * Instantiates a new User group.
     *
     * @param title the title
     * @param type  the type
     */
    public UserGroup(String title, String type) {
        this.setTitle(title);
        this.setType(type);
    }

    /**
     * User Group identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * User Group title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Type of user Group.
     *
     * @apiField
     * @var string
     * @see ::TYPE constants.
     */
    protected String type;

    /**
     * Whether this user group is master group (built-in).
     *
     * @apiField
     * @var bool
     */
    protected Boolean master;

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

    public UserGroup setReadOnly(Boolean readOnly) {
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
        UserGroup.objectXmlName = objectXmlName;
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
        UserGroup.controller = controller;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Is master.
     *
     * @return the boolean
     */
    public Boolean isMaster() {
        return master;
    }

    /**
     * Sets master.
     *
     * @param master the master
     */
    public void setMaster(Boolean master) {
        this.master = master;
    }

    /**
     * Get user group.
     *
     * @param id the id
     * @return the user group
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public static UserGroup get(int id) throws KayakoException {
        return new UserGroup().populate(get(controller, id));
    }

    /**
     * Save UserGroup.
     *
     * @return the UserGroup
     * @throws KayakoException the kayako exception
     */
    public UserGroup save() throws KayakoException {
        return (UserGroup) super.save(controller);
    }

    /**
     * Update UserGroup.
     *
     * @return the UserGroup
     * @throws KayakoException the kayako exception
     */
    public UserGroup update() throws KayakoException {
        return (UserGroup) super.update(controller);
    }

    /**
     * Create UserGroup.
     *
     * @return the UserGroup
     * @throws KayakoException the kayako exception
     */
    public UserGroup create() throws KayakoException {
        return (UserGroup) super.create(controller);
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
     * Refresh UserGroup.
     *
     * @return the UserGroup
     * @throws KayakoException the kayako exception
     */
    public UserGroup refresh() throws KayakoException {
        return (UserGroup) super.refresh(controller);
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


    public String toString() {
        return "User Group : " + this.getTitle();
    }

    //this function will populate the data of the user Group instance when supplied with RawArrayElement derived from the xml
    @Override
    public UserGroup populate(RawArrayElement element) throws KayakoException {
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
            } else if (elementName.equals("grouptype")) {
                this.setType(component.getContent());
            } else if (elementName.equals("ismaster")) {
                this.setMaster(Helper.parseInt(component.getContent()) == 1);
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> userGroupHashMap = new HashMap<String, String>();
        userGroupHashMap.put("title", this.getTitle());
        userGroupHashMap.put("grouptype", this.getType());
        return userGroupHashMap;
    }

    /**
     * Create user.
     *
     * @param fullName the full name
     * @param email    the email
     * @param password the password
     * @return the user
     */
    public User createUser(String fullName, String email, String password) {
        return new User(fullName, email, this, password);
    }
}
