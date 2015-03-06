package com.kayako.api.configuration;

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

import com.kayako.api.util.library.RESTClient;
import com.kayako.api.util.library.RESTInterface;

/**
 * The type Configuration.
 */
public class Configuration {
    private String baseUrl;
    private String apiKey;
    private String secretKey;
    private Boolean debug;
    private Boolean standardUrlType = true;
    private static Configuration configuration = null;

    private String dateFormat = "yyyy-MM-dd";
    private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * REST client.
     *
     * @var RESTInterface
     */
    private RESTInterface restClient = null;

    private Configuration(String baseUrl, String apiKey, String secretKey, Boolean testMode) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.debug = testMode;
    }

    public static Configuration init(String baseUrl, String apiKey, String secretKey) {
        return init(baseUrl, apiKey, secretKey, false);
    }

    public static Configuration init(String baseUrl, String apiKey, String secretKey, Boolean testMode) {
        if (configuration == null) {
            configuration = new Configuration(baseUrl, apiKey, secretKey, testMode);
        } else {
            configuration.setApiKey(apiKey);
            configuration.setBaseUrl(baseUrl);
            configuration.setSecretKey(secretKey);
            configuration.setDebug(testMode);
        }
        
        return configuration;
    }

    /**
     * Gets configuration.
     *
     * @return the configuration
     */
    public static Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Sets configuration.
     *
     * @param configuration the configuration
     */
    public static void setConfiguration(Configuration configuration) {
        Configuration.configuration = configuration;
    }

    /**
     * Gets base url.
     *
     * @return the base url
     */
    public String getBaseUrl() {
        return this.baseUrl;
    }

    /**
     * Sets base url.
     *
     * @param baseUrl the base url
     * @return the base url
     */
    public Configuration setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * Gets secret key.
     *
     * @return the secret key
     */
    public String getSecretKey() {
        return this.secretKey;
    }

    /**
     * Sets secret key.
     *
     * @param secretKey the secret key
     * @return the secret key
     */
    public Configuration setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    /**
     * Gets api key.
     *
     * @return the api key
     */
    public String getApiKey() {
        return this.apiKey;
    }

    /**
     * Sets api key.
     *
     * @param apiKey the api key
     * @return the api key
     */
    public Configuration setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    /**
     * Sets debug.
     *
     * @param debug the debug
     * @return the debug
     */
    public Configuration setDebug(Boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * Is debug.
     *
     * @return the boolean
     */
    public Boolean isDebug() {
        return this.debug;
    }

    /**
     * Is standard url type.
     *
     * @return the boolean
     */
    public Boolean isStandardUrlType() {
        return standardUrlType;
    }

    /**
     * Sets standard url type.
     *
     * @param standardUrlType the standard url type
     */
    public void setStandardUrlType(Boolean standardUrlType) {
        this.standardUrlType = standardUrlType;
    }

    /**
     * Gets date format.
     *
     * @return the date format
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * Sets date format.
     *
     * @param dateFormat the date format
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Gets date time format.
     *
     * @return the date time format
     */
    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    /**
     * Sets date time format.
     *
     * @param dateTimeFormat the date time format
     * @return the date time format
     */
    public Configuration setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
        return this;
    }

    /**
     * Gets rest client.
     *
     * @return the rest client
     */
    public RESTInterface getRestClient() {
        if (this.restClient == null) {
            this.restClient = new RESTClient().setConfig(this);
        }
        return this.restClient;
    }

    /**
     * Sets rest client.
     *
     * @param restClient the rest client
     * @return the rest client
     */
    public Configuration setRestClient(RESTInterface restClient) {
        this.restClient = restClient;
        return this;
    }
}
