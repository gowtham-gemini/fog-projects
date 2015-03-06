package com.assistanz.cloud.cloudstack.usages;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListUsageTypesResponse {
	
    /**
     * The list of usage Records
     */
    private List<UsageTypeRecordResponse> usageTypes;

    public List<UsageTypeRecordResponse> getUsageTypes() {
        return usageTypes;
    }

    public void setUsageTypes(List<UsageTypeRecordResponse> usageTypes) {
        this.usageTypes = usageTypes;
    }
}
