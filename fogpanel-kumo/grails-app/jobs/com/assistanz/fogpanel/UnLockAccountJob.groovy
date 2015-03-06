package com.assistanz.fogpanel

import com.assistanz.fogpanel.UserService

class UnLockAccountJob {
    
    UserService userService = new UserService()
    
    static triggers = {
      
    }

    def group = "unLock"
    
    def execute() {
        userService.unLock();
    }
}
