package com.assistanz.cloud.cloudstack.autoscale;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListCountersResponse {

    /**
     * List the counters
     */
    private List<CounterResponse> counters;

    public List<CounterResponse> getCounters() {
        return counters;
    }

    public void setCounters(List<CounterResponse> counters) {
        this.counters = counters;
    }

}
