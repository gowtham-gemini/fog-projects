package com.assistanz.cloud.cloudstack.autoscale;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListConditionsResponse {

    /**
     * List Conditions for the specific user
     */
    private List<ConditionResponse> conditions;

    public List<ConditionResponse> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionResponse> conditions) {
        this.conditions = conditions;
    }

}
