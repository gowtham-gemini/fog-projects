package com.assistanz.cloud.cloudstack.networkacl;

/**
 *
 * @author Santhosh
 *
 */
public class NetworkAclListResponse {

    /**
     * the ID of the ACL
     */
    private String aclId;

    /**
     * Description of the ACL
     */
    private String aclDescription;

    /**
     * the Name of the ACL
     */
    private String aclName;

    /**
     * Id of the VPC this ACL is associated with
     */
    private String vpcId;

    public String getAclId() {
        return aclId;
    }

    public void setAclId(String aclId) {
        this.aclId = aclId;
    }

    public String getAclDescription() {
        return aclDescription;
    }

    public void setAclDescription(String aclDescription) {
        this.aclDescription = aclDescription;
    }

    public String getAclName() {
        return aclName;
    }

    public void setAclName(String aclName) {
        this.aclName = aclName;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

}
