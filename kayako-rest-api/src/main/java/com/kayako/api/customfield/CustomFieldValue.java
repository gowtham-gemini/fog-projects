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

/**
 * The type Custom field value.
 */
public class CustomFieldValue {

    //This file is not being used - think on this
    String rawValue;
    Boolean isFile = false;
    String fileName = "";

    /**
     * Instantiates a new Custom field value.
     *
     * @param rawValue the raw value
     */
    public CustomFieldValue(String rawValue) {
        this.rawValue = rawValue;
    }

    /**
     * Instantiates a new Custom field value.
     *
     * @param rawValue the raw value
     * @param fileName the file name
     */
    public CustomFieldValue(String rawValue, String fileName) {
        this.isFile = true;
        this.fileName = fileName;
        this.rawValue = rawValue;
    }

    public String toString() {
        if (this.isFile) {
            return fileName + " : " + rawValue;
        }
        return rawValue;
    }
}
