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

import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.util.Helper;

import java.util.ArrayList;

/**
 * The type Custom field option.
 */
public class CustomFieldOption extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller = "/Base/CustomField/ListOptions";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "option";
    /**
     * The Read only.
     */
    protected Boolean readOnly = true;

    /**
     * Field option identifier.
     *
     * @apiField name =customfieldoptionid
     * @var int
     */
    protected int id;

    /**
     * Custom field identifier.
     *
     * @apiField name =customfieldid
     * @var int
     */
    protected int fieldId;

    /**
     * Field option value.
     *
     * @apiField name =optionvalue
     * @var string
     */
    protected String value;

    /**
     * Display order.
     *
     * @apiField
     * @var int
     */
    protected int displayOrder;

    /**
     * Is this option selected by default.
     *
     * @apiField
     * @var bool
     */
    protected Boolean isSelected;

    /**
     * Parent field option identifier (for linked selects).
     *
     * @apiField name =parentcustomfieldoptionid
     * @var int
     */
    protected int parentOptionId;

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
        CustomFieldOption.controller = controller;
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
    public CustomFieldOption setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    /**
     * Gets field id.
     *
     * @return the field id
     */
    public int getFieldId() {
        return fieldId;
    }

    /**
     * Sets field id.
     *
     * @param fieldId the field id
     * @return the field id
     */
    public CustomFieldOption setFieldId(int fieldId) {
        this.fieldId = fieldId;
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
    public CustomFieldOption setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets selected.
     *
     * @return the selected
     */
    public Boolean getSelected() {
        return isSelected;
    }

    /**
     * Sets selected.
     *
     * @param selected the selected
     * @return the selected
     */
    public CustomFieldOption setSelected(Boolean selected) {
        isSelected = selected;
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
        CustomFieldOption.objectXmlName = objectXmlName;
    }

    /**
     * Gets parent option id.
     *
     * @return the parent option id
     */
    public int getParentOptionId() {
        return parentOptionId;
    }

    /**
     * Sets parent option id.
     *
     * @param parentOptionId the parent option id
     * @return the parent option id
     */
    public CustomFieldOption setParentOptionId(int parentOptionId) {
        this.parentOptionId = parentOptionId;
        return this;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public CustomFieldOption setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     * @return the value
     */
    public CustomFieldOption setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public static RawArrayElement getAll(int fieldId) throws KayakoException {
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add(Integer.toString(fieldId));
        return KEntity.getAll(controller, parameters);
    }

    /**
     * Get custom field option.
     *
     * @param id the id
     * @return the custom field option
     * @throws KayakoException the kayako exception
     */
    public static CustomFieldOption get(int id) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    /**
     * Refresh custom field option.
     *
     * @return the custom field option
     * @throws KayakoException the kayako exception
     */
    public CustomFieldOption refresh() throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public CustomFieldOption populate(RawArrayElement element) throws KayakoException {
        if (!element.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }
        //content = timestamp
        this.setId(Helper.parseInt(element.getAttribute("customfieldoptionid")));
        this.setFieldId(Helper.parseInt(element.getAttribute("customfieldid")));
        this.setValue(element.getAttribute("optionvalue"));
        this.setDisplayOrder(Helper.parseInt(element.getAttribute("displayorder")));
        this.setSelected(Helper.parseInt(element.getAttribute("isselected")) > 0);
        this.setParentOptionId(Helper.parseInt(element.getAttribute("parentcustomfieldoptionid")));
        return this;
    }

}
