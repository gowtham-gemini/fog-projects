package org.zenoss.client.common;


public class RestServiceFactory {


    public static HTTPRestServiceHandler getHttpClient(String zenosURL, String username, String password) 
            throws ApplicationException {
        return new HTTPRestServiceHandler(zenosURL, username, password);
    }
    }
