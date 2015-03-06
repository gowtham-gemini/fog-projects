/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.cloud.cloudstack;

/**
 *
 * @author Santhosh Bheeman
 */
public class AffinityGroupResponse {

    /**
     * the ID of the AffinityGroup
     */
    private String id;

    /**
     * the account name of the AffinityGroup
     */
    private String account;

    /**
     * the description of the AffinityGroup
     */
    private String description;

    /**
     * the domain name of the AffinityGroup
     */
    private String domain;

    /**
     * the domainid of the AffinityGroup
     */
    private String domainId;

    /**
     * the name of the AffinityGroup
     */
    private String name;

    /**
     * the type of the AffinityGroup
     */
    private String type;

    /**
     * the virtualMachineIds of the AffinityGroup
     */
    private String virtualMachineIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVirtualMachineIds() {
        return virtualMachineIds;
    }

    public void setVirtualMachineIds(String virtualMachineIds) {
        this.virtualMachineIds = virtualMachineIds;
    }

}
