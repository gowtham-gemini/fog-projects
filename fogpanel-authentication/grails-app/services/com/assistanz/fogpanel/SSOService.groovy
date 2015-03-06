package com.assistanz.fogpanel

import grails.transaction.Transactional

@Transactional
class SSOService {

    def getCredentials(String token) {
        
      return PersistentSession.findById(token);
    }
}
