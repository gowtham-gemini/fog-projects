/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.cloud.cloudstack.usages;

/**
 *
 * @author gowtham
 */
class UsageTypeRecordResponse {
    
    
       /**
        * description of usage type
        */
       private String description;

       /**
        * usage type ID
        */
       private String usageTypeId;

       public String getDescription() {
               return description;
       }

       public void setDescription(String description) {
               this.description = description;
       }

       public String getUsageTypeId() {
               return usageTypeId;
       }

       public void setUsageTypeId(String usageTypeId) {
               this.usageTypeId = usageTypeId;
       }
}
