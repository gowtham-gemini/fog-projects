jslint.options = "white, sloppy, browser, unparam, warnings, vars, eqeq"
jslint.directory = "web-app/js"
jslint.includes = "**/dojo-1.8/CustomButton/*.js,**/app/*.js,**/dojo-1.8/FogPanel/*.js,**/dojo-1.8/List/*.js,**/dojo-1.8/Zone/*.js,**/dojo-1.8/custom/*.js,**/dojo-1.8/widgets/*.js"
jslint.excludes = "**/*.min.js, **/i18n/**/*.js, **/prototype/*.js,**/*-min.js,**/*.pack.js,**/dojo-1.8/dijit/*.js,**/dojo-1.8/dojo/*.js,**/dojo-1.8/dojox/*.js"
jslint.haltOnFailure = true
jslint.preDef= "\$, declare, require, define, defined, action, core, dijit, dojo"
jslint.reports = {
     MyXmlReport('xml') {                    // The report name "MyXmlReport" is user-defined; Report type is 'xml'
        destfile = 'target/test-reports/jslint.xml'  // Set the 'outputFile' property of the (XML) Report
    }
    MyHtmlReport('report') {                  // Report type is 'html'
        destfile = 'target/test-reports/jslint.html'
    }
}    
