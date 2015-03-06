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
import com.kayako.api.rest.RawArrayElement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Custom field multi select.
 */
public class CustomFieldMultiSelect extends CustomField {

    /**
     * Separator of main and linked select values.
     *
     * @var string
     */
    public static final String VALUE_SEPARATOR = ", ";

    /**
     * The Options.
     */
    protected ArrayList<CustomFieldOption> options = new ArrayList<CustomFieldOption>();

    /**
     * Instantiates a new Custom field multi select.
     *
     * @param customFieldGroup the custom field group
     */
    public CustomFieldMultiSelect(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    /**
     * Gets options.
     *
     * @return the options
     */
    public ArrayList<CustomFieldOption> getOptions() {
        return options;
    }

    /**
     * Sets options.
     *
     * @param options the options
     * @return the options
     */
    public CustomFieldMultiSelect setOptions(ArrayList<CustomFieldOption> options) {
        this.options = options;
        String rawValue = "";
        for (CustomFieldOption customFieldOption : options) {
            rawValue += customFieldOption.getValue() + VALUE_SEPARATOR;
        }
        this.setRawValue(rawValue);
        return this;
    }

    /**
     * Sets options.
     *
     * @param option the option
     * @return the options
     */
    public CustomFieldMultiSelect setOptions(CustomFieldOption option) {
        ArrayList<CustomFieldOption> arrayList = new ArrayList<CustomFieldOption>();
        arrayList.add(option);
        return this.setOptions(arrayList);
    }

    /**
     * Add to options.
     *
     * @param customFieldOption the custom field option
     * @return the custom field multi select
     */
    public CustomFieldMultiSelect addToOptions(CustomFieldOption customFieldOption) {
        this.options.add(customFieldOption);
        return this;
    }

    /**
     * Gets values.
     *
     * @return the values
     */ //returns HashMap like (option.id => option.value)
    public HashMap<String, String> getValues() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (CustomFieldOption option : this.options) {
            hashMap.put(Integer.toString(option.getId()), option.getValue());
        }
        return hashMap;
    }

    //TODO setValue, getValue

    @Override
    public CustomFieldMultiSelect populate(RawArrayElement element) throws KayakoException {
        super.populate(element);
        String[] values = element.getContent().split(VALUE_SEPARATOR);
        for (String value : values) {
            this.addToOptions(this.getOption(value));
        }
        return this;
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomFieldMultiSelect) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        int i = 0;
        for (CustomFieldOption customFieldOption : this.getOptions()) {
            hashMap.put(this.getName() + "[" + Integer.toString(i++) + "]", Integer.toString(customFieldOption.getId()));
        }
        return hashMap;
    }
}
