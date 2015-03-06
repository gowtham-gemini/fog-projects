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
 * The type User organization.
 */
public class UserOrganization extends KEntity {

    public static final String TYPE_RESTRICTED = "restricted";
    public static final String TYPE_SHARED = "shared";

    /**
     * The Controller.
     */
    static protected String controller = "/Base/UserOrganization";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "userorganization";

    /**
     * User Organization identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * User Organization name.
     *
     * @apiField
     * @var string
     */
    protected String name;

    /**
     * Type of user Organization.
     *
     * @apiField
     * @var string
     * @see  ::TYPE constants.
     */
    protected String type = TYPE_RESTRICTED;

    /**
     * User organization address.
     *
     * @apiField
     * @var string
     */
    protected String address;

    /**
     * User organization city.
     *
     * @apiField
     * @var string
     */
    protected String city;

    /**
     * User organization state.
     *
     * @apiField
     * @var string
     */
    protected String state;

    /**
     * User organization postal code.
     *
     * @apiField
     * @var string
     */
    protected String postalCode;

    /**
     * User organization country.
     *
     * @apiField
     * @var string
     */
    protected String country;

    /**
     * User organization phone number.
     *
     * @apiField
     * @var string
     */
    protected String phone;

    /**
     * User organization FAX number.
     *
     * @apiField
     * @var string
     */
    protected String fax;

    /**
     * User organization website URL.
     *
     * @apiField
     * @var string
     */
    protected String website;

    /**
     * Timestamp of when the user organization was created.
     *
     * @apiField
     * @var int
     */
    protected int dateline;

    /**
     * Timestamp of when the user organization was last updated.
     *
     * @apiField
     * @var int
     */
    protected int lastUpdate;

    /**
     * Identifier of Service Level Agreement plan associated to this user organization.
     *
     * @apiField
     * @var int
     */
    protected int SLAPlanID;

    /**
     * Timestamp of when the Service Level Agreement plan associated to this user organization will expire.
     *
     * @apiField
     * @var int
     */
    protected int SLAPlanExpiry;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public UserOrganization setName(String name) {
        this.name = name;
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
    public UserOrganization setId(int id) {
        this.id = id;
        return this;
    }

    public Boolean getReadOnly() {

        return readOnly;
    }

    public UserOrganization setReadOnly(Boolean readOnly) {
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
        UserOrganization.objectXmlName = objectXmlName;
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
        UserOrganization.controller = controller;
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
     * @return the type
     */
    public UserOrganization setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     * @return the address
     */
    public UserOrganization setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     * @return the city
     */
    public UserOrganization setCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     * @return the state
     */
    public UserOrganization setState(String state) {
        this.state = state;
        return this;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets postal code.
     *
     * @param postalCode the postal code
     * @return the postal code
     */
    public UserOrganization setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     * @return the country
     */
    public UserOrganization setCountry(String country) {
        this.country = country;
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
     * Sets phone.
     *
     * @param phone the phone
     * @return the phone
     */
    public UserOrganization setPhone(String phone) {
        this.phone = phone;
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
    public UserOrganization setSLAPlanExpiry(int SLAPlanExpiry) {
        this.SLAPlanExpiry = SLAPlanExpiry;
        return this;
    }

    /**
     * Gets sLA plan iD.
     *
     * @return the sLA plan iD
     */
    public int getSLAPlanID() {

        return SLAPlanID;
    }

    /**
     * Sets sLA plan iD.
     *
     * @param SLAPlanID the sLA plan iD
     * @return the sLA plan iD
     */
    public UserOrganization setSLAPlanID(int SLAPlanID) {
        this.SLAPlanID = SLAPlanID;
        return this;
    }

    /**
     * Gets last update.
     *
     * @return the last update
     */
    public int getLastUpdate() {

        return lastUpdate;
    }

    /**
     * Sets last update.
     *
     * @param lastUpdate the last update
     * @return the last update
     */
    public UserOrganization setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    /**
     * Gets dateline.
     *
     * @return the dateline
     */
    public int getDateline() {

        return dateline;
    }

    /**
     * Sets dateline.
     *
     * @param dateline the dateline
     * @return the dateline
     */
    public UserOrganization setDateline(int dateline) {
        this.dateline = dateline;
        return this;
    }

    /**
     * Gets website.
     *
     * @return the website
     */
    public String getWebsite() {

        return website;
    }

    /**
     * Sets website.
     *
     * @param website the website
     * @return the website
     */
    public UserOrganization setWebsite(String website) {
        this.website = website;
        return this;
    }

    /**
     * Gets fax.
     *
     * @return the fax
     */
    public String getFax() {

        return fax;
    }

    /**
     * Sets fax.
     *
     * @param fax the fax
     * @return the fax
     */
    public UserOrganization setFax(String fax) {
        this.fax = fax;
        return this;
    }

    /**
     * Get user organization.
     *
     * @param id the id
     * @return the user organization
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public static UserOrganization get(int id) throws KayakoException {
        return new UserOrganization().populate(get(controller, id));
    }

    //this function will populate the data of the user Organization instance when supplied with RawArrayElement derived from the xml
    @Override
    public UserOrganization populate(RawArrayElement element) throws KayakoException {
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
            } else if (elementName.equals("name")) {
                this.setName(component.getContent());
            } else if (elementName.equals("organizationtype")) {
                this.setType(component.getContent());
            } else if (elementName.equals("address")) {
                this.setAddress(component.getContent());
            } else if (elementName.equals("city")) {
                this.setCity(component.getContent());
            } else if (elementName.equals("state")) {
                this.setState(component.getContent());
            } else if (elementName.equals("postalcode")) {
                this.setPostalCode(component.getContent());
            } else if (elementName.equals("country")) {
                this.setCountry(component.getContent());
            } else if (elementName.equals("phone")) {
                this.setPhone(component.getContent());
            } else if (elementName.equals("fax")) {
                this.setFax(component.getContent());
            } else if (elementName.equals("website")) {
                this.setWebsite(component.getContent());
            } else if (elementName.equals("dateline")) {
                this.setDateline(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("lastupdate")) {
                this.setLastUpdate(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("slaplanid")) {
                this.setSLAPlanID(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("slaplanexpiry")) {
                this.setSLAPlanExpiry(Helper.parseInt(component.getContent()));
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> userOrganizationHashMap = new HashMap<String, String>();
        userOrganizationHashMap.put("name", this.getName());
        userOrganizationHashMap.put("organizationtype", this.getType());
        userOrganizationHashMap.put("address", this.getAddress());
        userOrganizationHashMap.put("city", this.getCity());
        userOrganizationHashMap.put("state", this.getState());
        userOrganizationHashMap.put("postalcode", this.getPostalCode());
        userOrganizationHashMap.put("country", this.getCountry());
        userOrganizationHashMap.put("phone", this.getPhone());
        userOrganizationHashMap.put("fax", this.getFax());
        userOrganizationHashMap.put("website", this.getWebsite());
        userOrganizationHashMap.put("slaplanid", Integer.toString(this.getSLAPlanID()));
        userOrganizationHashMap.put("slaplanexpiry", Integer.toString(this.getSLAPlanExpiry()));

        return userOrganizationHashMap;
    }

}
