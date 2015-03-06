package com.kayako.api.ticket;

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
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.user.UserGroup;
import com.kayako.api.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Ticket priority.
 */
public class TicketPriority extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller = "/Tickets/TicketPriority";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "ticketpriority";
    /**
     * The Read only.
     */
    protected Boolean readOnly = true;

    /**
     * Ticket priority identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Ticket priority title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Ticket priority display order.
     *
     * @apiField
     * @var int
     */
    protected int displayOrder;

    /**
     * Path to icon displayed in GUI for this ticket priority.
     *
     * @apiField
     * @var string
     */
    protected String displayIcon;

    /**
     * Type of this ticket type.
     *
     * @apiField
     * @var string
     * @see ::TYPE constants.
     */
    protected AccessTypeEnum type;

    /**
     * If this ticket type is visible to specific user groups only.
     *
     * @apiField
     * @var bool
     */
    protected Boolean userVisibilityCustom;

    /**
     * Identifier of user group this ticket type is visible to.
     *
     * @apiField name =usergroupid
     * @var int[]
     */
    protected ArrayList<Integer> userGroupIds = new ArrayList<Integer>();

    /**
     * Linked department.
     *
     * @var Department
     */

    /**
     * Background color associated with this ticket priority in GUI.
     * <p/>
     * This color is used for the "General Tab Bar" in Kayako GUI when viewing the ticket.
     *
     * @apiField
     * @var string
     */
    private String backgroundColor;

    /**
     * Font color associated with this ticket priority in GUI.
     *
     * @apiField
     * @var string
     */
    protected String color;
    private HashMap<Integer, UserGroup> userGroups = new HashMap<Integer, UserGroup>();

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
    public TicketPriority setUserVisibilityCustom(Boolean userVisibilityCustom) {
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
    public TicketPriority setDisplayIcon(String displayIcon) {
        this.displayIcon = displayIcon;
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
    public TicketPriority setDisplayOrder(int displayOrder) {
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
    public TicketPriority setTitle(String title) {
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
    public TicketPriority setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Boolean getReadOnly() {

        return readOnly;
    }

    @Override
    public TicketPriority setReadOnly(Boolean readOnly) {
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
        TicketPriority.objectXmlName = objectXmlName;
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
        TicketPriority.controller = controller;
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
    public TicketPriority setType(AccessTypeEnum type) {
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
    public TicketPriority setUserGroupIds(ArrayList<Integer> userGroupIds) {
        this.userGroupIds = userGroupIds;
        return this;
    }

    /**
     * Gets user groups.
     *
     * @return the user groups
     * @throws com.kayako.api.exception.KayakoException
     *          the kayako exception
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
    public TicketPriority setUserGroups(HashMap<Integer, UserGroup> userGroups) {
        this.userGroups = userGroups;
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
     * Gets background color.
     *
     * @return the background color
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets background color.
     *
     * @param backgroundColor the background color
     * @return the background color
     */
    public TicketPriority setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * Sets color.
     *
     * @param color the color
     * @return the color
     */
    public TicketPriority setColor(String color) {
        this.color = color;
        return this;
    }

    /**
     * Get ticket priority.
     *
     * @param id the id
     * @return the ticket priority
     * @throws KayakoException the kayako exception
     */
    public static TicketPriority get(int id) throws KayakoException {
        return new TicketPriority().populate(get(controller, id));
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

    //this function will populate the data of the ticket priority instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketPriority populate(RawArrayElement element) throws KayakoException {
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
            } else if (elementName.equals("displayicon")) {
                this.setDisplayIcon(component.getContent());
            } else if (elementName.equals("type")) {
                this.setType(AccessTypeEnum.getEnum(component.getContent()));
            } else if (elementName.equals("uservisibilitycustom")) {
                if (Helper.parseInt(component.getContent()) == 1) {
                    this.setUserVisibilityCustom(true);
                } else {
                    this.setUserVisibilityCustom(false);
                }
            } else if (elementName.equals("usergroupid")) {
                this.userGroupIds.add(new Integer(component.getContent()));
            } else if (elementName.equals("frcolorcode")) {
                this.setColor(component.getContent());
            } else if (elementName.equals("bgcolorcode")) {
                this.setBackgroundColor(component.getContent());
            }

        }

        return this;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
