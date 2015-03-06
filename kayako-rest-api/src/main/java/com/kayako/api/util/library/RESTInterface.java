package com.kayako.api.util.library;

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
import com.kayako.api.rest.RawArrayElement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The interface REST interface.
 */
public interface RESTInterface {

    /**
     * The constant METHOD_GET.
     */
    public final static String METHOD_GET = "GET";
    /**
     * The constant METHOD_POST.
     */
    public final static String METHOD_POST = "POST";
    /**
     * The constant METHOD_PUT.
     */
    public final static String METHOD_PUT = "PUT";
    /**
     * The constant METHOD_DELETE.
     */
    public final static String METHOD_DELETE = "DELETE";

    /**
     * Initialize rEST interface.
     *
     * @param config the config
     * @return the rEST interface
     */
    public RESTInterface initialize(Configuration config);

    /**
     * Get raw array element.
     *
     * @param controller the controller
     * @return the raw array element
     */
    public RawArrayElement get(String controller);

    /**
     * Post raw array element.
     *
     * @param controller the controller
     * @return the raw array element
     */
    public RawArrayElement post(String controller);

    /**
     * Put raw array element.
     *
     * @param controller the controller
     * @return the raw array element
     */
    public RawArrayElement put(String controller);

    /**
     * Delete raw array element.
     *
     * @param controller the controller
     * @return the raw array element
     */
    public RawArrayElement delete(String controller);

    /**
     * Get raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @return the raw array element
     */
    public RawArrayElement get(String controller, ArrayList<String> parameters);

    /**
     * Post raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @return the raw array element
     */
    public RawArrayElement post(String controller, ArrayList<String> parameters);

    /**
     * Put raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @return the raw array element
     */
    public RawArrayElement put(String controller, ArrayList<String> parameters);

    /**
     * Delete raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @return the raw array element
     */
    public RawArrayElement delete(String controller, ArrayList<String> parameters);

    /**
     * Post raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @param data the data
     * @return the raw array element
     */
    public RawArrayElement post(String controller, ArrayList<String> parameters, HashMap<String, String> data);

    /**
     * Put raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @param data the data
     * @return the raw array element
     */
    public RawArrayElement put(String controller, ArrayList<String> parameters, HashMap<String, String> data);

    /**
     * Post raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @param data the data
     * @param files the files
     * @return the raw array element
     */
    public RawArrayElement post(String controller, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, HashMap<String, String>> files);

    /**
     * Put raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @param data the data
     * @param files the files
     * @return the raw array element
     */
    public RawArrayElement put(String controller, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, HashMap<String, String>> files);
}