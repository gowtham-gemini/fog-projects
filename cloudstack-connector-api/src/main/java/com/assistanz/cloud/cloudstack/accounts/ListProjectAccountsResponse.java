package com.assistanz.cloud.cloudstack.accounts;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Gowtham
 */
public class ListProjectAccountsResponse {

    /*
     * Lists project's accounts
     */
    private List<ProjectAccountResponse> projectAccounts;
    
    /**
     * Lists resource tags associated with vm
     */
    private List<TagsResponse> tagss;

    public List<ProjectAccountResponse> getProjectAccounts() {
        return projectAccounts;
    }

    public void setProjectAccounts(List<ProjectAccountResponse> projectAccounts) {
        this.projectAccounts = projectAccounts;
    }
    
    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }
}
