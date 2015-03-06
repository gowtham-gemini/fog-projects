package com.assistanz.fogpanel

class CurrentUserService {

    def getCurrentUser() {
        def user = springSecurityService.currentUser
        
        return user.username;
    }
}
