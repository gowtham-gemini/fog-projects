

package org.zenoss.client.modal.entities.device;

import org.json.simple.JSONObject;

/**
 * Entity to get all graph definitions 
 * 
 * @author Nandhini
 */
public class GraphDef {    
    private GraphDefType type;
    private String url;

    public GraphDef(GraphDefType type, String url) {
        this.type = type;
        this.url = url;
    }    
    
    public GraphDefType getType() {
        return type;
    }

    public void setType(GraphDefType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }   
    
    public static GraphDef convert(JSONObject obj) {
        GraphDefType type = null;
        
        if(obj.get("title").equals("Load Average")) {
            type = GraphDefType.LOAD_AVERAGE;
        }
        else if(obj.get("title").equals("CPU Utilization")) {
            type = GraphDefType.CPU_UTILIZATION;
        }
        else if(obj.get("title").equals("Memory Utilization")) {
            type = GraphDefType.MEMORY_UTILIZATION;
        }
        else if(obj.get("title").equals("IO")) {
            type = GraphDefType.IO;
        }
        else if(obj.get("title").equals("Availability")) {
            type = GraphDefType.SYSTEM_AVAILABLITY;
        }
        else if(obj.get("title").equals("Throughput")) {
            type = GraphDefType.THROUGHPUT;
        }
        else if(obj.get("title").equals("Packets")) {
            type = GraphDefType.PACKETS;
        }
        else if(obj.get("title").equals("Errors")) {
            type = GraphDefType.ERRORS;
        }
        
        if(type == null) {
            return null;
        }
        else {
            return new GraphDef(type, (String)obj.get("url"));
        }    
    }
}
