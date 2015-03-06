package com.assistanz.cloud.cloudstack.swift;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListSwiftsResponse {

    /**
     * List Swift
     */
    private List<SwiftResponse> swifts;

    public List<SwiftResponse> getSwifts() {
        return swifts;
    }

    public void setSwifts(List<SwiftResponse> swifts) {
        this.swifts = swifts;
    }

}
