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
	String clusterId;	
	
	/**
	 * the allocation state of the cluster
	 */
	String clusterAllocationState;	
	
	/**
	 * the type of the cluster
	 */
	String clusterType;	
	
	/**
	 * the hypervisor type of the cluster
	 */
	String hypervisorType;
	
	/**
	 * whether this cluster is managed by cloudstack
	 */
	String clusterManagedState;
	
	/**
	 * the cluster name
	 */
	String clusterName;
	
	/**
	 * the Pod ID of the cluster
	 */
	String clusterPodId;
	
	/**
	 * the Pod name of the cluster
	 */
	String clusterPodName;
	
	/**
	 * the Zone ID of the cluster
	 */
	String clusterZoneId;

	/**
	 * the Zone name of the cluster
	 */
	String clusterZoneName;
	
	/**
	 * 	the capacity of the Cluster
	 */
	private List<CapacityResponse> clusterCapacitys;

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getClusterAllocationState() {
		return clusterAllocationState;
	}

	public void setClusterAllocationState(String clusterAllocationState) {
		this.clusterAllocationState = clusterAllocationState;
	}

	public String getClusterType() {
		return clusterType;
	}

	public void setClusterType(String clusterType) {
		this.clusterType = clusterType;
	}

	public String getHypervisorType() {
		return hypervisorType;
	}

	public void setHypervisorType(String hypervisorType) {
		this.hypervisorType = hypervisorType;
	}

	public String getClusterManagedState() {
		return clusterManagedState;
	}

	public void setClusterManagedState(String clusterManagedState) {
		this.clusterManagedState = clusterManagedState;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getClusterPodId() {
		return clusterPodId;
	}

	public void setClusterPodId(String clusterPodId) {
		this.clusterPodId = clusterPodId;
	}

	public String getClusterPodName() {
		return clusterPodName;
	}

	public void setClusterPodName(String clusterPodName) {
		this.clusterPodName = clusterPodName;
	}

	public String getClusterZoneId() {
		return clusterZoneId;
	}

	public void setClusterZoneId(String clusterZoneId) {
		this.clusterZoneId = clusterZoneId;
	}

	public String getClusterZoneName() {
		return clusterZoneName;
	}

	public void setClusterZoneName(String clusterZoneName) {
		this.clusterZoneName = clusterZoneName;
	}

	public List<CapacityResponse> getClusterCapacitys() {
		return clusterCapacitys;
	}

	public void setClusterCapacitys(List<CapacityResponse> clusterCapacitys) {
		this.clusterCapacitys = clusterCapacitys;
	}	
	
	
	
}
