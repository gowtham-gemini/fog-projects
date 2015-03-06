package com.assistanz.cloud.cloudstack.pool;

/**
 *
 * @author Gowtham
 *
 */
public class ListPoolsResponse {

    /**
     * pool id
     */
    private String poolId;

    /**
     * pool algorithm
     */
    private String poolAlgorithm;

    /**
     * pool name
     */
    private String poolName;

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    public String getPoolAlgorithm() {
        return poolAlgorithm;
    }

    public void setPoolAlgorithm(String poolAlgorithm) {
        this.poolAlgorithm = poolAlgorithm;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

}
