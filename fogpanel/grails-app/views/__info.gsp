<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Application Information</title>
    </head>
    <body>
        <h1>Build Info</h1>
        <ul>
            <li>App Version:<g:meta name="app.version"/></li>
            <li>Grails Version<g:meta name="app.grails.version"/></li>
            <li>Groovy version: ${GroovySystem.getVersion()}</li>
            <li>JVM version: ${System.getProperty('java.version')}</li>
            <li>Build Number: <g:meta name="app.build.number"/></li>
            <li>Commit Checksum: <g:meta name="app.build.checksum"/></li>
            <li>Build Tag: <g:meta name="app.build.tag"/></li>
            <li>Build Date: <g:meta name="app.build.date"/></li>
        </ul>
    </body>
</html>
