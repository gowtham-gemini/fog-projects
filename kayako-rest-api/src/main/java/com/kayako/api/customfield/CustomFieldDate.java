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

import com.kayako.api.configuration.Configuration;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.util.Helper;

import java.sql.Timestamp;
import java.text.ParseException;

/**
 * The type Custom field date.
 */
public class CustomFieldDate extends CustomField {
    /**
     * Timestamp representation of date.
     *
     * @var Timestamp
     */
    private Timestamp timestamp;
    protected static String objectXmlName = "field";

    /**
     * Instantiates a new Custom field date.
     *
     * @param customFieldGroup the custom field group
     */
    public CustomFieldDate(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    /**
     * Returns field value as timestamp.
     *
     * @return Timestamp timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the date using timestamp.
     *
     * @param timestamp Timestamp.
     * @return CustomFieldDate timestamp
     */
    public CustomFieldDate setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        this.setRawValue(Helper.getDateString(timestamp.getTime()));
        return this;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return getDate(Configuration.getConfiguration().getDateFormat());
    }

    /**
     * Gets date.
     *
     * @param dateFormat the date format String
     * @return the date String
     */
    public String getDate(String dateFormat) {
        return Helper.getDateString(this.getTimestamp().getTime(), dateFormat);
    }

    /**
     * Sets date.
     *
     * @param dateStr the date str
     * @return the date
     * @throws ParseException the parse exception
     */
    public CustomFieldDate setDate(String dateStr) throws ParseException {
        this.setTimestamp(new Timestamp(Helper.getTimeStampFromDateString(dateStr)));
        return this;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return this.getDate();
    }

    /**
     * Sets value.
     *
     * @param value the value
     * @return the value
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public CustomFieldDate setValue(String value) throws KayakoException {
        try {
            return this.setDate(value);
        } catch (ParseException e) {
            throw new KayakoException();
        }
    }

    @Override
    public CustomFieldDate populate(RawArrayElement element) throws KayakoException {
        if (!element.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        super.populate(element);
        try {
			this.setTimestamp(new Timestamp(Helper.getTimeStampFromDateString(element.getContent())));
		} catch (ParseException e) {
		}
        
        return this;
    }
}
