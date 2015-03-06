// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

environments {
    development {
         grails.config.locations = [ "classpath:${appName}-config.properties",
                "classpath:${appName}-config.groovy",
                "file:${userHome}/.grails/${appName}-config.properties",
                "file:${userHome}/.grails/${appName}-config.groovy",
                 "file:E:/etc/fogpanel/openstack-config2.groovy"
            ]
    }
    production {
         grails.config.locations = [ "classpath:${appName}-config.properties",
                "classpath:${appName}-config.groovy",
                "file:${userHome}/.grails/${appName}-config.properties",
                "file:${userHome}/.grails/${appName}-config.groovy",
                "file:/etc/fogpanel/openstack-config.groovy"
                ]
    }
}
// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
//grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
        preprocessor.urlThemes = 'themes' 
        preprocessor.fileSystemPath = 'web-app/themes'
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    
    environments {
        development {
            appenders {
                file name: 'infoLog', file: '/var/log/fog-info.log', layout:pattern(conversionPattern: "[%d{HH:mm:ss:SSS}] %-5p %c{2}: %m%n")
                file name: 'cloudstackLog', file: '/var/log/fog-cloudstack.log', layout:pattern(conversionPattern: "[%d{HH:mm:ss:SSS}] %-5p %c{2}: %m%n")
            }
    
//    root {
//        info 'infoLog'
//        additivity = false
//    }
    
            error  'org.codehaus.groovy.grails.web.servlet',        // controllers
                   'org.codehaus.groovy.grails.web.pages',          // GSP
                   'org.codehaus.groovy.grails.web.sitemesh',       // layouts
                   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
                   'org.codehaus.groovy.grails.web.mapping',        // URL mapping
                   'org.codehaus.groovy.grails.commons',            // core / classloading
                   'org.codehaus.groovy.grails.plugins',            // plugins
                   'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
                   'org.springframework',
                   'org.hibernate',
                   'net.sf.ehcache.hibernate'
            
            info additivity: false ,infoLog: ['com.assistanz.fogpanel']
            debug additivity: false ,cloudstackLog: ['com.assistanz.fogpanel']
        }
        production {
            appenders {
                file name: 'infoLog', file: '/var/log/fog-info.log', layout:pattern(conversionPattern: "[%d{HH:mm:ss:SSS}] %-5p %c{2}: %m%n")
                file name: 'cloudstackLog', file: 'var/log/fog-cloudstack.log', layout:pattern(conversionPattern: "[%d{HH:mm:ss:SSS}] %-5p %c{2}: %m%n")
            }
    
//    root {
//        info 'infoLog'
//        additivity = false
//    }
    
            error  'org.codehaus.groovy.grails.web.servlet',        // controllers
                   'org.codehaus.groovy.grails.web.pages',          // GSP
                   'org.codehaus.groovy.grails.web.sitemesh',       // layouts
                   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
                   'org.codehaus.groovy.grails.web.mapping',        // URL mapping
                   'org.codehaus.groovy.grails.commons',            // core / classloading
                   'org.codehaus.groovy.grails.plugins',            // plugins
                   'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
                   'org.springframework',
                   'org.hibernate',
                   'net.sf.ehcache.hibernate'

            info additivity: false ,infoLog: ['com.assistanz.fogpanel']
            debug additivity: false ,cloudstackLog: ['com.assistanz.fogpanel']
        }
    }
} 

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.assistanz.fogpanel.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.assistanz.fogpanel.UserRole'
grails.plugin.springsecurity.authority.className = 'com.assistanz.fogpanel.Role'
grails.plugin.springsecurity.useSwitchUserFilter = true
grails.plugin.springsecurity.switchUser.targetUrl = "/"
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap = [
   '/j_spring_security_switch_user': ['ROLE_ADMIN'],
   '/j_spring_security_check': ['permitAll'],
    
    '/api/**':       ['permitAll'],
    
    '/account/**':       ['permitAll'],
    '/notification/**':       ['permitAll'],
    '/Info/**':       ['permitAll'],
    
    '/simpleCaptcha/captcha':       ['permitAll'],
    
    '/login/**' :    ['IS_AUTHENTICATED_ANONYMOUSLY'],
    
    '/config/**':    ['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'], 
    
    '/**/js/**':     ['permitAll'],
    '/**/css/**':    ['permitAll'],
    
    '/**'       :    ['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER']   
]
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
   '/j_spring_security_switch_user': ['ROLE_ADMIN']
]
grails.plugin.springsecurity.password.algorithm = 'SHA-256'
grails.plugin.springsecurity.password.hash.iterations = 1

grails.plugin.databasemigration.updateOnStart = true
grails.plugin.springsecurity.useSecurityEventListener = true
grails.plugin.databasemigration.updateOnStartFileNames = ["changelog.groovy"]


simpleCaptcha {
    // font size used in CAPTCHA images
    fontSize = 30
    height = 200
    width = 200

// number of characters in CAPTCHA text 
length = 6

// amount of space between the bottom of the CAPTCHA text and the bottom of the CAPTCHA image 
bottomPadding = 16

// distance between the diagonal lines used to obfuscate the text
lineSpacing = 10

// the charcters shown in the CAPTCHA text must be one of the following 
chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
grails.doc.images = new File("src/docs/img/images")
grails.doc.style = new File("src/docs/css")
// this param will be passed as the first argument to this java.awt.Font constructor  

// // http://docs.oracle.com/javase/6/docs/api/java/awt/Font.html#Font(java.lang.String,%20int,%20int) 
font = "Times New Roman" 
}

// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

/* remove this line 
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
remove this line */
