package com.assistanz.cloud.cloudstack;

/**
 *
 * @author Gowtam
 *
 */
public class CapabilityResponse {

    /**
     * can this service capability value can be choosable while creatine network offerings
     */
    private String canChooseServiceCapability;

    /**
     * the capability name
     */
    private String name;

    /**
     * the capability value
     */
    private String value;

    public String getCanChooseServiceCapability() {
        return canChooseServiceCapability;
    }

    public void setCanChooseServiceCapability(String canChooseServiceCapability) {
        this.canChooseServiceCapability = canChooseServiceCapability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
