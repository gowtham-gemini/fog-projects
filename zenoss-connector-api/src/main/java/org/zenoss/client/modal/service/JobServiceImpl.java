package org.zenoss.client.modal.service;

import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.zenoss.client.api.JobService;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceHandler;
import org.zenoss.client.common.Router;

public class JobServiceImpl implements JobService {

    private RestServiceHandler handler;

    public JobServiceImpl(RestServiceHandler restServiceHandler) {
        this.handler = restServiceHandler;
    }

    @Override
    public String userjobs(String uuid) throws ApplicationException {

        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getJobs(), "userjobs", new HashMap());

        JSONObject jobObj = (JSONObject) jsonObject.get("jobs");

        JSONArray startedJSONArray = (JSONArray) jobObj.get("STARTED");
        JSONArray pendingJSONArray = (JSONArray) jobObj.get("PENDING");
        JSONArray successJSONArray = (JSONArray) jobObj.get("SUCCESS");

        if (successJSONArray != null && !successJSONArray.isEmpty()) {

            for (Object object : successJSONArray) {

                JSONObject jSONObject = (JSONObject) object;

                if (jSONObject != null && jSONObject.get("uuid") != null) {

                    if (jSONObject.get("uuid").equals(uuid)) {

                        return "" + jSONObject.get("status");

                    }

                }
            }

        }

        if (startedJSONArray != null && !startedJSONArray.isEmpty()) {

            for (Object object : startedJSONArray) {

                JSONObject jSONObject = (JSONObject) object;

                if (jSONObject != null && jSONObject.get("uuid") != null) {

                    if (jSONObject.get("uuid").equals(uuid)) {

                        return "" + jSONObject.get("status");

                    }

                }
            }
        }

        if (pendingJSONArray != null && !pendingJSONArray.isEmpty()) {

            for (Object object : pendingJSONArray) {

                JSONObject jSONObject = (JSONObject) object;

                if (jSONObject != null && jSONObject.get("uuid") != null) {

                    if (jSONObject.get("uuid").equals(uuid)) {

                        return "" + jSONObject.get("status");

                    }

                }
            }
        }

        return "";

    }

}
