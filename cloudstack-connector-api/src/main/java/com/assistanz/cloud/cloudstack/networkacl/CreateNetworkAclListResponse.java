package com.assistanz.cloud.cloudstack.networkacl;

/**
 *
 * @author Santhosh
 *
 */
public class CreateNetworkAclListResponse {

    /**
     * the ID of the ACL
     */
    private String id;

    /**
     * Description of the ACL
     */
    private String description;

    /**
     * the Name of the ACL
     */
    private String name;

    /**
     * Id of the VPC this ACL is associated with
     */
    private String vpcId;
    
    /**
     * returns jobid  for this  VPC this ACL is associated with
     */
    private String jobId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
