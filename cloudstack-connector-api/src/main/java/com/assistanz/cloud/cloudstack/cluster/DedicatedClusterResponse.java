package com.assistanz.cloud.cloudstack.cluster;

/**
 *
 * @author Santhosh
 *
 */
public class DedicatedClusterResponse {

    /**
     * the ID of the dedicated resource
     */
    private String id;

    /**
     * the Account Id to which the Pod is dedicated
     */
    private String accountId;

    /**
     * the Dedication Affinity Group ID of the pod
     */
    private String affinityGroupId;

    /**
     * the ID of the cluster
     */
    private String clusterId;

    /**
     * the name of the cluster
     */
    private String clusterName;

    /**
     * the domain ID to which the Pod is dedicated
     */
    private String domainId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAffinityGroupId() {
        return affinityGroupId;
    }

    public void setAffinityGroupId(String affinityGroupId) {
        this.affinityGroupId = affinityGroupId;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

}
