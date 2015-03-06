includeTargets << grailsScript("_GrailsArgParsing")

target(setInfo: "Updates the application properties file") {
    // TODO: Implement script here
    def props = new Properties()
    File propsFile = new File("application.properties")
    propsFile.withInputStream { 
      stream -> props.load(stream) 
    }
   
    def buildNumber = argsMap.buildNumber ? argsMap.buildNumber : System.properties.getProperty('buildNumber')
    props.setProperty("app.build.number", buildNumber ? buildNumber : "");
        
    def buildChecksum = argsMap.buildChecksum ? argsMap.buildChecksum : System.properties.getProperty('buildChecksum')
    props.setProperty("app.build.checksum", buildChecksum ? buildChecksum : "");
    
    def commitChecksum = argsMap.commitChecksum ? argsMap.commitChecksum : System.properties.getProperty('commitChecksum')
    props.setProperty("app.commit.checksum", commitChecksum ? commitChecksum : "");
    
    def buildBranch = argsMap.buildBranch ? argsMap.buildBranch : System.properties.getProperty('buildBranch')
    props.setProperty("app.build.branch", buildBranch ? buildBranch : "");
    
    def buildTag = argsMap.buildTag ? argsMap.buildTag : System.properties.getProperty('buildTag')
    props.setProperty("app.build.tag", buildTag ? buildTag : "");
    
    props.setProperty("app.build.date", (new Date()).toString());
    
    props.store(new FileWriter(propsFile), "Build Update")
}

setDefaultTarget(setInfo)
