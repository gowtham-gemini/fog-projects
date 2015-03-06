package com.assistanz.cloud.cloudstack.securitygroup;

/**
 *
 * @author Gowtham
 *
 */
public class AuthorizeSecurityGroupIngressResponse {

    /**
     * account owning the security group rule
     */
    private String account;

    /**
     * the CIDR notation for the base IP address of the security group rule
     */
    private String cidr;

    /**
     * the ending IP of the security group rule
     */
    private String endport;

    /**
     * the code for the ICMP message response
     */
    private String icmpCode;

    /**
     * the type of the ICMP message response
     */
    private String icmpType;

    /**
     * the protocol of the security group rule
     */
    private String protocol;

    /**
     * the id of the security group rule
     */
    private String ruleId;

    /**
     * security group name
     */
    private String securityGroupName;

    /**
     * the starting IP of the security group rule
     */
    private String startPort;

    /**
    * the jobId
    */
   String jobId;

   public String getJobId() {
       return jobId;
   }

   public void setJobId(String jobId) {
       this.jobId = jobId;
   }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getEndport() {
        return endport;
    }

    public void setEndport(String endport) {
        this.endport = endport;
    }

    public String getIcmpCode() {
        return icmpCode;
    }

    public void setIcmpCode(String icmpCode) {
        this.icmpCode = icmpCode;
    }

    public String getIcmpType() {
        return icmpType;
    }

    public void setIcmpType(String icmpType) {
        this.icmpType = icmpType;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getSecurityGroupName() {
        return securityGroupName;
    }

    public void setSecurityGroupName(String securityGroupName) {
        this.securityGroupName = securityGroupName;
    }

    public String getStartPort() {
        return startPort;
    }

    public void setStartPort(String startPort) {
        this.startPort = startPort;
    }

}
