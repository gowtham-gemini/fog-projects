package com.assistanz.cloud.cloudstack.accounts;

import java.util.List;

/**
 *
 * @author Santhosh Bheeman
 */
public class ListAccountsResponse {

    /*
     * list of accounts
     */
    private List<AccountResponse> listAccounts;

    public List<AccountResponse> getListAccounts() {
        return listAccounts;
    }

    public void setListAccounts(List<AccountResponse> listAccounts) {
        this.listAccounts = listAccounts;
    }
}
