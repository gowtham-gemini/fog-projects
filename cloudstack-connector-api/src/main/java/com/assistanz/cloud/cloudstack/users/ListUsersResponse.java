package com.assistanz.cloud.cloudstack.users;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListUsersResponse {

    /*
     * list of accounts
     */
    private List<UserResponse> listUsers;

    public List<UserResponse> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<UserResponse> listUsers) {
        this.listUsers = listUsers;
    }

}
