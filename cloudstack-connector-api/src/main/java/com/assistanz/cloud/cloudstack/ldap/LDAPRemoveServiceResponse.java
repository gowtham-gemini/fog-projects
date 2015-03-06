package com.assistanz.cloud.cloudstack.ldap;

/**
 *
 * @author Santhosh
 *
 */
public class LDAPRemoveServiceResponse {

    /**
     * Specify the distinguished name of a user with the search permission on the directory
     */
    private String bindDn;

    /**
     * DN password
     */
    private String bindPass;

    /**
     * Hostname or ip address of the ldap server eg: my.ldap.com
     */
    private String hostName;

    /**
     * Specify the LDAP port if required, default is 389
     */
    private String port;

    /**
     * You specify a query filter here, which narrows down the users, who can be part of this domain
     */
    private String queryFilter;

    /**
     * The search base defines the starting point for the search in the directory tree Example: dc=cloud,dc=com
     */
    private String searchBase;

    /**
     * Check Use SSL if the external LDAP server is configured for LDAP over SSL
     */
    private String ssl;

    public String getBindDn() {
        return bindDn;
    }

    public void setBindDn(String bindDn) {
        this.bindDn = bindDn;
    }

    public String getBindPass() {
        return bindPass;
    }

    public void setBindPass(String bindPass) {
        this.bindPass = bindPass;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getQueryFilter() {
        return queryFilter;
    }

    public void setQueryFilter(String queryFilter) {
        this.queryFilter = queryFilter;
    }

    public String getSearchBase() {
        return searchBase;
    }

    public void setSearchBase(String searchBase) {
        this.searchBase = searchBase;
    }

    public String getSsl() {
        return ssl;
    }

    public void setSsl(String ssl) {
        this.ssl = ssl;
    }

}
