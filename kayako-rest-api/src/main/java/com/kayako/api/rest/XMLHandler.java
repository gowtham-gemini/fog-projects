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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The type XML handler.
 */
public class XMLHandler extends DefaultHandler {

    Boolean currentElement = false;
    String currentValue = null;
    private RawArrayElement element = null;
    //private RawArrayElement openElement = null;
    private Stack<RawArrayElement> objectStack = new Stack<RawArrayElement>();

    /**
     * Called when tag starts ( ex:- <name>KayakoPeople</name>
     * -- <name> )
     */
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        Map<String, String> attributeMap = new HashMap<String, String>();

        /** Start */

        for (int i = 0; i < attributes.getLength(); i++) {
            attributeMap.put(attributes.getQName(i), attributes.getValue(i));
        }
        RawArrayElement temp = new RawArrayElement(qName, attributeMap);
        if (element == null) {
            element = temp;
        }
        if (!objectStack.empty()) {
            RawArrayElement parentTemp = objectStack.pop().put(temp);
            objectStack.push(parentTemp);
        }
        objectStack.push(temp);

        currentElement = true;
    }

    /**
     * Called when tag closing ( ex:- <name>kayakoPeople</name>
     * -- </name> )
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        objectStack.pop();
        currentElement = false;

        /** set value */
    }

    /**
     * Called to get tag characters ( ex:- <name>kayakoPeople</name>
     * -- to get kayakoPeople Character )
     */
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        if (currentElement && !objectStack.isEmpty()) {
            currentValue = new String(ch, start, length);
            objectStack.push(objectStack.pop().put(currentValue));
            currentElement = false;

        }

    }

    /**
     * Gets raw array element.
     *
     * @return the raw array element
     */
    public RawArrayElement getRawArrayElement() {
        return element;
    }

    private RawArrayElement getCurrentElement() {
        return this.element;
    }

}
