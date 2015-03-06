package com.assistanz.cloud.cloudstack.cluster;

import java.util.List;
import com.assistanz.cloud.cloudstack.CapacityResponse;

/**
 *
 * @author Gowtham
 *
 */
public class AddClusterResponse {

    /**
     * the cluster ID
     */
    private String id;

    /**
     * the allocation state of the cluster
     */
    private String allocationState;

    /**
     * the type of the cluster
     */
    private String clusterType;
    
    /**
     * the cpu overcommit ratio of the cluster
     */
    private String cpuOverCommitRatio;

    /**
     * the hypervisor type of the cluster
     */
    private String hypervisorType;

    /**
     * whether this cluster is managed by cloudstack
     */
    private String managedState;

    /**
     * the memory overcommit ratio of the cluster
     */
    private String memoryOverCommitRatio;

    /**
     * the cluster name
     */
    private String name;

    /**
     * the Pod ID of the cluster
     */
    private String podId;

    /**
     * the Pod name of the cluster
     */
    private String podName;

    /**
     * the Zone ID of the cluster
     */
    private String zoneId;

    /**
     * the Zone name of the cluster
     */
    private String zoneName;

    /**
     * the capacity of the Cluster
     */
    private List<CapacityResponse> clusterCapacitys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAllocationState() {
        return allocationState;
    }

    public void setAllocationState(String allocationState) {
        this.allocationState = allocationState;
    }

    public String getClusterType() {
        return clusterType;
    }

    public void setClusterType(String clusterType) {
        this.clusterType = clusterType;
    }

    public String getCpuOverCommitRatio() {
        return cpuOverCommitRatio;
    }

    public void setCpuOverCommitRatio(String cpuOverCommitRatio) {
        this.cpuOverCommitRatio = cpuOverCommitRatio;
    }

    public String getHypervisorType() {
        return hypervisorType;
    }

    public void setHypervisorType(String hypervisorType) {
        this.hypervisorType = hypervisorType;
    }

    public String getManagedState() {
        return managedState;
    }

    public void setManagedState(String managedState) {
        this.managedState = managedState;
    }

    public String getMemoryOverCommitRatio() {
        return memoryOverCommitRatio;
    }

    public void setMemoryOverCommitRatio(String memoryOverCommitRatio) {
        this.memoryOverCommitRatio = memoryOverCommitRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPodId() {
        return podId;
    }

    public void setPodId(String podId) {
        this.podId = podId;
    }

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public List<CapacityResponse> getClusterCapacitys() {
        return clusterCapacitys;
    }

    public void setClusterCapacitys(List<CapacityResponse> clusterCapacitys) {
        this.clusterCapacitys = clusterCapacitys;
    }
 
}
