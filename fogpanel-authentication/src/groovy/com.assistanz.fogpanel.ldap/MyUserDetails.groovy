package com.assistanz.fogpanel.ldap

import org.springframework.security.core.userdetails.User

class MyUserDetails extends User {
    // extra instance variables final String fullname final String email final String title
    String firstName
    String email
    String lastName
    String uuid

    MyUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
        boolean credentialsNonExpired, boolean accountNonLocked, Collection authorities,
        String firstName, String email, String lastName, String uuid) {
                  
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities)              
        
        this.firstName = firstName
        this.email = email
        this.lastName = lastName
        this.uuid = uuid
        
    }
}
