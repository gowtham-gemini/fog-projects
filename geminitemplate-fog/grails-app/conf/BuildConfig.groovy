grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.server.port.http=9090
grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]
grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()
        mavenRepo "http://maven.restlet.org/"
        mavenRepo "http://192.168.1.140:8081/artifactory/libs-release/"

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
//         mavenRepo "http://repo1.maven.org/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

		runtime 'mysql:mysql-connector-java:5.1.32'
//                runtime 'org.mariadb.jdbc:mariadb-java-client:1.1.7'
		compile "commons-httpclient:commons-httpclient:3.1"
		compile "xml-apis:xml-apis:1.4.01"
                compile "javax.mail:mail:1.4.3"
                compile "com.paypal.sdk:rest-api-sdk:0.7.0"
                runtime "org.bouncycastle:bcprov-jdk16:1.45"
                compile "org.tmatesoft:svn:1.1.0"
                compile "org.tmatesoft.svnkit:svnkit:1.3.1"
                compile "com.assistanz:licensor:0.1.0"
                compile 'c3p0:c3p0:0.9.1.2'
                compile 'org.freemarker:freemarker:2.3.14'
                compile 'com.fogpanel:OpenstackUserAPI:1.0'
                compile 'zenoss-client:zenoss-client:1.0'
                compile 'commons-codec:commons-codec:1.9'
                
//                compile("org.pacesys:openstack4j:1.0.2") {
//                    excludes "jersey-client", "jersey-media-json-jackson"
//                } 
                compile 'org.pacesys:openstack4j-core:2.0.1-SNAPSHOT'
                compile 'org.pacesys.openstack4j.connectors:openstack4j-httpclient:2.0.1-SNAPSHOT'
//                compile 'org.apache.httpcomponents:httpclient:4.3.2'
//                compile 'org.apache.httpcomponents:httpclient:4.3.2'
//                compile 'com.fogpanel.connectors:cloudstack-api:1.0-SNAPSHOT'
//                compile "net.authorize:anet-java-sdk:1.4.6"
    }

    plugins {
        runtime ":hibernate4:4.3.5.1"
        runtime ":jquery:1.11.0.2"
        runtime ":resources:1.2.8"
        compile ":spring-security-core:2.0-RC4"
        compile ":quartz2:2.1.6.2"
        compile ":pdf:0.6"
//        compile ":audit-trail:2.0.0"
        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:7.0.52.1"

        runtime ":database-migration:1.4.0"
        compile ":less-resources:1.3.3.2"
        compile ":jslint:0.6"
        
        compile ":jaxrs:0.10"
        compile ':cache:1.1.2'
        compile ":simple-captcha:0.9.9"
//        compile ':dynamic-themes:0.1'
        compile ":mail:1.0.5"
        compile ":codenarc:0.21"
//        compile ":jslint:0.6"
        compile ":rabbitmq:1.0.0"
                 
    }
}

codenarc.ruleSetFiles="file:grails-app/conf/MyRuleSet.groovy"
