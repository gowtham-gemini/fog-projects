package com.assistanz.cloud.cloudstack.usages;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListUsageRecordsResponse {
    
    /**
     * The list of usage Records
     */
    private List<UsageRecordResponse> usageRecords;

    public List<UsageRecordResponse> getUsageRecords() {
        return usageRecords;
    }

    public void setUsageRecords(List<UsageRecordResponse> usageRecords) {
        this.usageRecords = usageRecords;
    }
	
}
