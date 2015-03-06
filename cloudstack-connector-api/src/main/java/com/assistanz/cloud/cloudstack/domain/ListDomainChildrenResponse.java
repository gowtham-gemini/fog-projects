package com.assistanz.cloud.cloudstack.domain;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListDomainChildrenResponse {

    /**
     * Lists all children domains belonging to a specified domain
     */
    private List<DomainChildrenResponse> domainChildrens;

    public List<DomainChildrenResponse> getDomainChildrens() {
        return domainChildrens;
    }

    public void setDomainChildrens(List<DomainChildrenResponse> domainChildrens) {
        this.domainChildrens = domainChildrens;
    }
}
