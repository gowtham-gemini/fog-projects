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
import com.kayako.api.util.Helper;

import java.util.HashMap;

/**
 * The type Custom field select.
 */
public class CustomFieldSelect extends CustomField {

    /**
     * Selected option.
     *
     * @var CustomFieldOption
     */
    protected CustomFieldOption selectedOption;

    /**
     * Instantiates a new Custom field select.
     *
     * @param customFieldGroup the custom field group
     */
    public CustomFieldSelect(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    /**
     * Gets selected option.
     *
     * @return the selected option
     */
    public CustomFieldOption getSelectedOption() {
        return selectedOption;
    }

    /**
     * Sets selected option.
     *
     * @param selectedOption the selected option
     * @return the selected option
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public CustomFieldSelect setSelectedOption(CustomFieldOption selectedOption) throws KayakoException {
        this.selectedOption = selectedOption;
        if (this.selectedOption != null && this.getRawValue() == null) {
            this.setRawValue(this.selectedOption.getValue());
        }
        return this;
    }

    /**
     * Sets the option for this field.
     *
     * @param value the value
     * @return CustomFieldSelect value
     * @throws KayakoException the kayako exception
     */
    public CustomFieldSelect setValue(String value) throws KayakoException {
        int id = Helper.parseInt(value);
        if (id == 0) {
            this.setSelectedOption(this.getOption(value));
        } else {
            this.setSelectedOption(this.getOption(id));
        }
        return this;
    }

    @Override
    public CustomFieldSelect populate(RawArrayElement element) throws KayakoException {
        super.populate(element);
        int id = Helper.parseInt(element.getContent());
        if (id == 0) {
            this.setSelectedOption(this.getOption(element.getContent()));
        } else {
            this.setSelectedOption(this.getOption(id));
        }

        return this;
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomFieldSelect) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (this.getSelectedOption() != null) {
            hashMap.put(this.getName(), Integer.toString(this.getSelectedOption().getId()));
        }
        return hashMap;
    }
}
