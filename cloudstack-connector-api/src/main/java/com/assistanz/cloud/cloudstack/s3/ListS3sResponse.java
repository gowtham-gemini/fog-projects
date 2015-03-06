package com.assistanz.cloud.cloudstack.s3;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListS3sResponse {

    /**
     * List S3s
     */
    private List<S3Response> s3s;

    public List<S3Response> getS3s() {
        return s3s;
    }

    public void setS3s(List<S3Response> s3s) {
        this.s3s = s3s;
    }

}
