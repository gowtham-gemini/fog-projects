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
import net.iharder.Base64;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The type Custom field file.
 */
public class CustomFieldFile extends CustomField {

    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "field";

    /**
     * File name.
     *
     * @apiField
     * @var string
     */
    protected String fileName;

    /**
     * File contents.
     *
     * @var string
     */
    protected byte[] contents;

    /**
     * Was the file changed after fetching or creating.
     *
     * @var bool
     */
    protected Boolean isChanged = false;

    /**
     * Get contents.
     *
     * @return the byte [ ]
     */
    public byte[] getContents() {
        return contents;
    }

    /**
     * Sets contents.
     *
     * @param contents the contents
     */
    public void setContents(byte[] contents) {
        if (!Arrays.equals(contents, this.contents)) {
            this.setChanged(true);
        }
        this.contents = contents;
    }

    /**
     * Sets content from file.
     *
     * @param file the file
     * @return the content from file
     */
    public CustomFieldFile setContentFromFile(File file) {
        try {
            this.contents = Helper.readBytesFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.getFileName().equals(null)) {
            this.setFileName(file.getName());
        }
        this.setRawValue(Base64.encodeBytes(this.contents));
        return this;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        if (fileName != this.fileName) {
            this.setChanged(true);
        }
        this.fileName = fileName;
    }

    /**
     * Gets changed.
     *
     * @return the changed
     */
    public Boolean getChanged() {
        return isChanged;
    }

    /**
     * Sets changed.
     *
     * @param changed the changed
     */
    public void setChanged(Boolean changed) {
        isChanged = changed;
    }

    /**
     * Instantiates a new Custom field file.
     *
     * @param customFieldGroup the custom field group
     */
    public CustomFieldFile(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    @Override
    public CustomFieldFile populate(RawArrayElement element) throws KayakoException {
        if (!element.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        super.populate(element);
        this.setFileName(element.getAttribute("filename"));

        try {
            this.setContents(Base64.decode(element.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomFieldFile) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        //this function returns empty HashMap as functional method is buildFileHashMap here
        return hashMap;
    }

    public HashMap<String, HashMap<String, String>> buildFilesHashMap() {
        return this.buildFilesHashMap(false);
    }

    public HashMap<String, HashMap<String, String>> buildFilesHashMap(Boolean newCustomFieldFile) {
        HashMap<String, String> file = new HashMap<String, String>();
        file.put(this.getFileName(), new String(this.getContents()));
        HashMap<String, HashMap<String, String>> fileHashMap = new HashMap<String, HashMap<String, String>>();
        fileHashMap.put(this.getName(), file);
        return fileHashMap;
    }

}
