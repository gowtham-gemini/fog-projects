package com.assistanz.cloud.cloudstack.vpc;

import java.util.List;

/**
 *
 * @author Santhosh
 */
public class ListVpcOfferingsResponse {

    /**
     * The list of VPCOffering
     */
    private List<VpcOfferingResponse> vpcOfferings;

    public List<VpcOfferingResponse> getVpcOfferings() {
        return vpcOfferings;
    }

    public void setVpcOfferings(List<VpcOfferingResponse> vpcOfferings) {
        this.vpcOfferings = vpcOfferings;
    }

}
