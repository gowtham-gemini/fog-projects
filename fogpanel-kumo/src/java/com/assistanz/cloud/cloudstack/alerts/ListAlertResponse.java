/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.cloud.cloudstack.alerts;

import java.util.List;

/**
 *
 * @author gowtham
 */
class ListAlertResponse {
    
    /**
     * the list of alerts
     */
    private List<AlertResponse> alerts;

    public List<AlertResponse> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertResponse> alerts) {
        this.alerts = alerts;
    }
    
    
}
