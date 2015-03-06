package com.assistanz.cloud.cloudstack.diskoffering;

import java.util.List;

public class ListDiskOfferingResponse {

    /**
     * the list of Disk Offerings
     */
    private List<DiskOfferingResponse> diskOfferings;

    public List<DiskOfferingResponse> getDiskOfferings() {
        return diskOfferings;
    }

    public void setDiskOfferings(List<DiskOfferingResponse> diskOfferings) {
        this.diskOfferings = diskOfferings;
    }
        
}
