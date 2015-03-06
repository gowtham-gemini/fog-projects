package com.assistanz.fogpanel

import org.codehaus.groovy.grails.plugins.web.taglib.RenderTagLib
/**
 *
 * @author Gowtham
 */
class ThemeTagLib {
    static namespace = "az"    
    
    def templateLayout = { attrs, body ->
        def theme = attrs.theme
        def layout = attrs.layout
        def module = attrs.module
        String content = body()
        String templatePath = grailsAttributes.applicationContext.getResource("/themes/" + theme + "/layouts/" + "/" + module + "/" + layout + ".gsp").getFile()
        String fileContent = new File(templatePath).text
        def templateContent = fileContent.replace("{content}", content)
        out << templateContent
    }
}

