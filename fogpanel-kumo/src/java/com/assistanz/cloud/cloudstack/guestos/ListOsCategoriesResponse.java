package com.assistanz.cloud.cloudstack.guestos;

import java.util.List;
/**
 * 
 * @author Gowtham
 *
 */
public class ListOsCategoriesResponse {
	
    /**
     * The list of OS types
     */
    private List<OsCategoryResponse> osCategories;

    public List<OsCategoryResponse> getOsCategories() {
        return osCategories;
    }

    public void setOsCategories(List<OsCategoryResponse> osCategories) {
        this.osCategories = osCategories;
    }
}
