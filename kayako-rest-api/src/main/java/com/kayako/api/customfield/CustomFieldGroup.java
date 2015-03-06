package com.kayako.api.customfield;

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

import com.kayako.api.enums.CustomFieldGroupTypeEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Custom field group.
 */
public abstract class CustomFieldGroup extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller;
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "group";
    /**
     * The Read only.
     */
    protected Boolean readOnly = true;

    /**
     * Custom field group identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Custom field group title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * List of custom fields in this group.
     *
     * @var CustomField[]
     */
    protected ArrayList<CustomField> fields = new ArrayList<CustomField>();

    /**
     * Type of custom field group.
     */
    protected CustomFieldGroupTypeEnum type;

    /**
     * Gets fields.
     *
     * @return the fields
     */
    public ArrayList<CustomField> getFields() {
        return fields;
    }

    /**
     * Sets fields.
     *
     * @param fields the fields
     * @return the fields
     */
    public CustomFieldGroup setFields(ArrayList<CustomField> fields) {
        this.fields = fields;
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
    public CustomFieldGroup setId(int id) {
        this.id = id;
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
        CustomFieldGroup.objectXmlName = objectXmlName;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public CustomFieldGroup setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
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
        CustomFieldGroup.controller = controller;
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
    public CustomFieldGroup setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public CustomFieldGroupTypeEnum getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     * @return the type
     */
    public CustomFieldGroup setType(CustomFieldGroupTypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * Get custom field group.
     *
     * @param id the id
     * @return the custom field group
     * @throws com.kayako.api.exception.KayakoException
     *          the kayako exception
     */
    public static CustomFieldGroup get(int id) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public CustomFieldGroup refresh(String controller) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public CustomFieldGroup populate(RawArrayElement element) throws KayakoException {
        if (!element.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        //attribute =  title, id
        this.setTitle(element.getAttribute("title")).setId(Helper.parseInt(element.getAttribute("id")));

        for (RawArrayElement component : element.getComponents()) {
            String elementName = component.getElementName();
            if (elementName.equalsIgnoreCase("field")) {
                this.fields.add(CustomFieldFactory.createCustomField(this, component));
            }
        }
        return this;

    }

    public HashMap<String, String> buildHashMap() {
        return this.buildHashMap(false);
    }

    /**
     * Build hash map.
     *
     * @param newCustomFieldGroup the new custom field group
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap(Boolean newCustomFieldGroup) {
        HashMap<String, String> customFieldGroupHashMap = new HashMap<String, String>();
        for (CustomField customField : this.getFields()) {
            customFieldGroupHashMap.putAll(customField.buildHashMap());
        }

        return customFieldGroupHashMap;
    }

    public HashMap<String, HashMap<String, String>> buildFilesHashMap() {
        return this.buildFilesHashMap(false);
    }

    /**
     * Build files hash map.
     *
     * @param newCustomFieldFile the new custom field file
     * @return the hash map
     */
    public HashMap<String, HashMap<String, String>> buildFilesHashMap(Boolean newCustomFieldFile) {
        HashMap<String, HashMap<String, String>> filesHashMap = new HashMap<String, HashMap<String, String>>();
        for (CustomField customField : this.getFields()) {
            filesHashMap.putAll(customField.buildFilesHashMap());
        }
        return filesHashMap;
    }

}
