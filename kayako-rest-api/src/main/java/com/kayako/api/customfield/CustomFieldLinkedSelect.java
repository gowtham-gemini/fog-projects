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

import java.util.HashMap;

/**
 * The type Custom field linked select.
 */
public class CustomFieldLinkedSelect extends CustomFieldSelect {

    /**
     * Separator of main and linked select values.
     *
     * @var string
     */
    public static final String PARENT_CHILD_SEPARATOR = " &gt; ";

    /**
     * Instantiates a new Custom field linked select.
     *
     * @param customFieldGroup the custom field group
     */
    public CustomFieldLinkedSelect(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    @Override
    public CustomFieldLinkedSelect populate(RawArrayElement element) throws KayakoException {
        super.populate(element);
        String[] values = element.getContent().split(PARENT_CHILD_SEPARATOR);
        if (values.length > 1) {
            this.setSelectedOption(this.getOption(values[1]));
        }
        return this;
    }

    @Override
    public CustomFieldLinkedSelect setSelectedOption(CustomFieldOption customFieldOption) throws KayakoException {
        if (customFieldOption != null) {
        	this.selectedOption = customFieldOption;
            this.setRawValue(this.getOption(customFieldOption.getParentOptionId()).getValue() + PARENT_CHILD_SEPARATOR + customFieldOption.getValue());
        } else {
            this.setRawValue(null);
        }

        return this;
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomFieldLinkedSelect) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (this.getSelectedOption().getParentOptionId() > 0) {
            hashMap.put(this.getName() + "[0]", Integer.toString(this.getSelectedOption().getParentOptionId()));
            hashMap.put(this.getName() + "[1][" + Integer.toString(this.getSelectedOption().getParentOptionId()) + "]", Integer.toString(this.getSelectedOption().getId()));
        } else {
            hashMap.put(this.getName() + "[0]", Integer.toString(this.getSelectedOption().getId()));
        }
        return hashMap;
    }
}
