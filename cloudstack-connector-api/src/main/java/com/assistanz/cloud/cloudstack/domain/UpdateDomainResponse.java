package com.assistanz.cloud.cloudstack.domain;

/**
 *
 * @author Gowtham
 *
 */
public class UpdateDomainResponse {

    /**
     * the ID of the domain
     */
    private String id;

    /**
     * whether the domain has one or more sub-domains
     */
    private String hasChild;

    /**
     * the level of the domain
     */
    private String level;

    /**
     * the name of the domain
     */
    private String name;

    /**
     * the network domain
     */
    private String networkDomain;

    /**
     * the domain ID of the parent domain
     */
    private String parentDomainId;

    /**
     * the domain name of the parent domain
     */
    private String parentDomainName;

    /**
     * the path of the domain
     */
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkDomain() {
        return networkDomain;
    }

    public void setNetworkDomain(String networkDomain) {
        this.networkDomain = networkDomain;
    }

    public String getParentDomainId() {
        return parentDomainId;
    }

    public void setParentDomainId(String parentDomainId) {
        this.parentDomainId = parentDomainId;
    }

    public String getParentDomainName() {
        return parentDomainName;
    }

    public void setParentDomainName(String parentDomainName) {
        this.parentDomainName = parentDomainName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
