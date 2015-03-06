package com.assistanz.cloud.cloudstack.domain;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListDomainsResponse {
	
    
    /**
     * the list of Domains
     */
    private List<DomainResponse> domains;

    public List<DomainResponse> getDomains() {
        return domains;
    }

    public void setDomains(List<DomainResponse> domains) {
        this.domains = domains;
    }
    
}
