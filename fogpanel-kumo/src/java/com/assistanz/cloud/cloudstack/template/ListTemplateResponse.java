package com.assistanz.cloud.cloudstack.template;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListTemplateResponse {
    
    /**
     * The list of Templates
     */
    private List<TemplateResponse> templates;

    public List<TemplateResponse> getTemplates() {
        return templates;
    }

    public void setTemplates(List<TemplateResponse> templates) {
        this.templates = templates;
    }    
}
