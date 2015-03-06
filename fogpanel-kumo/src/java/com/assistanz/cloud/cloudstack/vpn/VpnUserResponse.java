/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.vpn;

/**
 *
 * @author gowtham
 */
class VpnUserResponse {
    
    
     /**
     * the id of the remote access vpn
     */
    String vpnUserId;	
    
    /**
     * the account of the remote access vpn
     */
    String account;	

    /**
     * the domain name of the account of the remote access vpn
     */
    String domain;	

    /**
     * the domain id of the account of the remote access vpn
     */
    String domainId;	

    /**
     * the project name of the vpn
     */
    String projectName;	

    /**
     * the project id of the vpn
     */
    String projectId;

    /**
     * the state of the vpn
     */
    String state;

    /**
     * the username of the vpn
     */
    String username;

    public String getVpnUserId() {
        return vpnUserId;
    }

    public void setVpnUserId(String vpnUserId) {
        this.vpnUserId = vpnUserId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
