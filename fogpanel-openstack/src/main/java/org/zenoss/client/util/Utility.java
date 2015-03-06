package org.zenoss.client.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Utility {

    /**
     * Converts the JSONArray containing name, value attributes to HasMap
     * 
     * @param jSONArray
     * @param nameDef
     * @param valueDef
     * @return 
     */
    public static HashMap convertJSONArrayToMap(JSONArray jSONArray, String nameDef, String valueDef) {

        HashMap map = new HashMap();

        if (jSONArray != null) {

            for (int i = 0; i < jSONArray.size(); i++) {

                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                map.put(jSONObject.get(nameDef), jSONObject.get(valueDef));
            }
        }
        return map;
    }
    
     /**
     * Converts the JSONArray to List
     * 
     * @param jSONArray
     * @param nameDef
     * @return 
     */
    public static List<String> convertJSONArrayToList(JSONArray jSONArray, String nameDef) {
        
        
        List<String> list = new ArrayList<String>();
        for (Object jSONArray1 : jSONArray) {
            JSONObject jSONObject = (JSONObject) jSONArray1;
            list.add(jSONObject.get(nameDef).toString());
        }
        return list;
    }

}
