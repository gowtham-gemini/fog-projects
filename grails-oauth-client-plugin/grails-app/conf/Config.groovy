// configuration for plugin testing - will not be included in the plugin zip

log4j = {
    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

// just for test, to avoid oauthService BeanCreationException "No oauth configuration found"
environments {
  test {
    oauth {
      providers {
        fogpanel {
            api = org.scribe.builder.api.FogPanelApi
            key = 'oauth_fogpanel_key'
            secret = 'oauth_fogpanel_secret'
            successUri = '/oauth/fogpanel/success'
            failureUri = '/oauth/fogpanel/failure'
            callback = "/oauth/fogpanel/callback"
        }
      }
    }
  }
}
