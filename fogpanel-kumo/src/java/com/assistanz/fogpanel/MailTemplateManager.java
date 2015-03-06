package com.assistanz.fogpanel;

import freemarker.template.*;
import freemarker.template.Template;
import freemarker.template.Configuration;
import java.util.*;
import java.net.URL;
import java.io.*;

/**
 *
 * @author gowtham
 */
public class MailTemplateManager {
    
    public String getTemplate(Map root, String templateUrl, String templateName, String language) throws Exception {
        
        System.out.println("templateName" + templateName);
        /* Create and adjust the configuration */
        Configuration cfg = new Configuration();
              
        if(templateUrl == null || templateUrl.equals("")) {
            URL resourceURL = getClass().getResource("/com/assistanz/fogpanel/mailtemplate/" + language);
            File resourceDir = new File(resourceURL.toString());
            if(!resourceDir.exists()) {
                throw new Exception("Unable to detect directory:" + resourceDir.getPath());
            }
            cfg.setDirectoryForTemplateLoading(resourceDir);
        } else {
            cfg.setDirectoryForTemplateLoading(new File(templateUrl));
        }
        
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        
        /* Get the template */
        Template temp = cfg.getTemplate(templateName);

        /* Merge data-model with template */       
        StringWriter write = new StringWriter();
        
        temp.process(root, write);
        
        return write.toString();
    }
    
}
