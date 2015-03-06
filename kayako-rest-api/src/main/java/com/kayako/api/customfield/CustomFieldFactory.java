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

import com.kayako.api.enums.CustomFieldDefinitionTypeEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;

/**
 * The type Custom field factory.
 */
public class CustomFieldFactory {

    /**
     * Create custom field.
     *
     * @param customFieldGroup the custom field group
     * @param element          the raw array element
     * @return the custom field
     * @throws com.kayako.api.exception.KayakoException
     *          the kayako exception
     */
    public static CustomField createCustomField(CustomFieldGroup customFieldGroup, RawArrayElement element) throws KayakoException {
        if (element == null || !element.getElementName().equals(CustomField.getObjectXmlName())) {
            throw new KayakoException("Invalid XML Element Supplied");
        }
        CustomFieldDefinitionTypeEnum type = CustomFieldDefinitionTypeEnum.getEnum(element.getAttribute("type"));

        switch (type) {
            case FILE:
                return new CustomFieldFile(customFieldGroup).populate(element);
            case LINKED_SELECT:
                return new CustomFieldLinkedSelect(customFieldGroup).populate(element);
            case CHECKBOX:
            case MULTI_SELECT:
                return new CustomFieldMultiSelect(customFieldGroup).populate(element);
            case RADIO:
            case SELECT:
                return new CustomFieldSelect(customFieldGroup).populate(element);
            case DATE:
                return new CustomFieldDate(customFieldGroup).populate(element);
            case CUSTOM:
            case PASSWORD:
            case TEXT:
            case TEXTAREA:
                return new CustomField(customFieldGroup).populate(element);

        }
        return null;
    }

}
