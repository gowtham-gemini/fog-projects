/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.zenoss.client.modal.entities.device;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nandhini
 */
public class Graph {
    
    private String uid;
    
    private Graph(Builder builder) {       
        this.uid = builder.getUid();        
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public static class Builder {  
        
        private String uid;        

        public Builder(String uid) {           
            this.uid = uid;
        }                

        public Graph build() {
            return new Graph(this);
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
    
    public Map toHashMap() {     
        
        Map map = new HashMap();        
        map.put("uid", this.uid);        
        return map;
    }
}
