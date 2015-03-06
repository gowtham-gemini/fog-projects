package com.kayako.api.rest;

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

import com.kayako.api.customfield.CustomField;
import com.kayako.api.customfield.CustomFieldGroup;
import com.kayako.api.exception.KayakoException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type K entity custom.
 */
public abstract class KEntityCustom extends KEntity {

    /**
     * Name of URL parameter for sending object identifier.
     *
     * @var string
     */
    static protected String objectIdField = null;

    /**
     * For fast lookup of custom fields based on their name.
     *
     * @var array
     */
    protected HashMap<String, CustomField> customFieldHashMap = new HashMap<String, CustomField>();

    /**
     * Object custom field groups.
     *
     * @var
     */
    protected ArrayList<CustomFieldGroup> customFieldGroups = new ArrayList<CustomFieldGroup>();

    /**
     * Gets custom field groups.
     *
     * @return the custom field groups
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public ArrayList<CustomFieldGroup> getCustomFieldGroups() throws KayakoException {
        return getCustomFieldGroups(false);
    }

    /**
     * Gets custom field groups.
     *
     * @param refresh the refresh
     * @return the custom field groups
     * @throws KayakoException the kayako exception
     */
    public ArrayList<CustomFieldGroup> getCustomFieldGroups(Boolean refresh) throws KayakoException {
        this.loadCustomFieldGroups(refresh);
        return this.customFieldGroups;
    }

    /**
     * Sets custom field groups.
     *
     * @param customFieldGroups the custom field groups
     */
    public void setCustomFieldGroups(ArrayList<CustomFieldGroup> customFieldGroups) {
        this.customFieldGroups = customFieldGroups;
    }

    /**
     * Gets custom field hash map.
     *
     * @return the custom field hash map
     * @throws KayakoException the kayako exception
     */
    public HashMap<String, CustomField> getCustomFieldHashMap() throws KayakoException {
        return this.getCustomFieldHashMap(false);
    }

    /**
     * Gets custom field hash map.
     *
     * @param refresh the refresh
     * @return the custom field hash map
     * @throws KayakoException the kayako exception
     */
    public HashMap<String, CustomField> getCustomFieldHashMap(Boolean refresh) throws KayakoException {
        this.loadCustomFieldGroups(refresh);
        return customFieldHashMap;
    }

    /**
     * Gets custom fields.
     *
     * @return the custom fields
     * @throws KayakoException the kayako exception
     */
    public ArrayList<CustomField> getCustomFields() throws KayakoException {
        return this.getCustomFields(false);
    }

    /**
     * Gets custom fields.
     *
     * @param refresh the refresh
     * @return the custom fields
     * @throws KayakoException the kayako exception
     */ //This function will return ArrayList of custom fields
    public ArrayList<CustomField> getCustomFields(Boolean refresh) throws KayakoException {
        return new ArrayList<CustomField>(this.getCustomFieldHashMap(refresh).values());
    }

    /**
     * Sets custom field hash map.
     *
     * @param customFieldHashMap the custom field hash map
     */
    public void setCustomFieldHashMap(HashMap<String, CustomField> customFieldHashMap) {
        this.customFieldHashMap = customFieldHashMap;
    }

    /**
     * Gets object id field.
     *
     * @return the object id field
     */
    public static String getObjectIdField() {
        return objectIdField;
    }

    /**
     * Sets object id field.
     *
     * @param objectIdField the object id field
     */
    public static void setObjectIdField(String objectIdField) {
        KEntityCustom.objectIdField = objectIdField;
    }

    public KEntityCustom update(String controller) throws KayakoException {
        super.update(controller);
        if (!this.isNew()) {
            this.updateCustomFields();
        }
        return this;
    }

    /**
     * Returns custom field based on its name.
     *
     * @param name Field name.
     * @return CustomField custom field
     * @throws KayakoException the kayako exception
     */
    public CustomField getCustomField(String name) throws KayakoException {
        this.loadCustomFieldGroups();
        return this.getCustomFieldHashMap().get(name);
    }

    /**
     * Load custom field groups.
     *
     * @return the array list
     * @throws KayakoException the kayako exception
     */
    protected ArrayList<CustomFieldGroup> loadCustomFieldGroups() throws KayakoException {
        return this.loadCustomFieldGroups(false);
    }

    /**
     * Load custom field groups.
     *
     * @param refresh the refresh
     * @return the array list
     * @throws KayakoException the kayako exception
     */
    protected abstract ArrayList<CustomFieldGroup> loadCustomFieldGroups(Boolean refresh) throws KayakoException;

    /**
     * Prepares local array for custom field fast lookup based on its name.
     *
     * @throws KayakoException the kayako exception
     */
    protected void cacheFields() throws KayakoException {
        for (CustomFieldGroup customFieldGroup : this.getCustomFieldGroups()) {
            for (CustomField customField : customFieldGroup.getFields()) {
                this.customFieldHashMap.put(customField.getName(), customField);
            }
        }
    }

    /**
     * Update custom fields.
     *
     * @return the k entity custom
     * @throws KayakoException the kayako exception
     */ //this method needs to be implemented in the derived class to basically call the overloaded method with correct arguments
    public abstract KEntityCustom updateCustomFields() throws KayakoException;

    /**
     * Updates all custom fields values on Kayako server.
     *
     * @param customGroupController - This is the customGroupController of CustomGroupClass
     * @return KEntityCustom k entity custom
     * @throws KayakoException the kayako exception
     */
    public KEntityCustom updateCustomFields(String customGroupController) throws KayakoException {
        if (this.customFieldGroups == null) {
            return this;
        }
        ArrayList<String> parameters = this.getIdArray();
        HashMap<String, HashMap<String, String>> files = new HashMap<String, HashMap<String, String>>();
        //prepare URL customGroupController and parameters
        //collect all field values into request data
        //foreach custom field groups- call build data and merge
        HashMap<String, String> data = new HashMap<String, String>();
        for (CustomFieldGroup customFieldGroup : this.getCustomFieldGroups()) {
            data.putAll(customFieldGroup.buildHashMap());
            //get files from data
            files.putAll(customFieldGroup.buildFilesHashMap());
        }

        //send request
        getRESTClient().post(customGroupController, parameters, data, files);

        //reload custom fields from server
        return this;

    }

}
