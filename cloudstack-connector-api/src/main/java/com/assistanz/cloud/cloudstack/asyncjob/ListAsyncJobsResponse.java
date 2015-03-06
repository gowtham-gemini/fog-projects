package com.assistanz.cloud.cloudstack.asyncjob;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListAsyncJobsResponse {

    /**
     * Lists all pending asynchronous jobs for the account.
     */
    private List<AsyncJobResponse> asyncJobs;

    public List<AsyncJobResponse> getAsyncJobs() {
        return asyncJobs;
    }

    public void setAsyncJobs(List<AsyncJobResponse> asyncJobs) {
        this.asyncJobs = asyncJobs;
    }
}
