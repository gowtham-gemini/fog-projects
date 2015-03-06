
package com.assistanz.cloud.cloudstack.accounts;

import java.util.List;


/**
 *
 * @author Gowtham
 */
public class ListAccountsResponse {
    
    /*
     * list of accounts
     */
    private List<AccountResponse> accounts;

    public List<AccountResponse> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResponse> accounts) {
        this.accounts = accounts;
    }
}
