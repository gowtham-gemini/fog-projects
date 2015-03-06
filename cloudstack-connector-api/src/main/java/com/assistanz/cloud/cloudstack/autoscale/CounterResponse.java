package com.assistanz.cloud.cloudstack.autoscale;

/**
 *
 * @author Santhosh
 *
 */
public class CounterResponse {

    /**
     * the id of the Counter
     */
    private String Id;

    /**
     * Name of the counter
     */
    private String Name;

    /**
     * Source of the counter
     */
    private String Source;

    /**
     * Value in case of SNMP or other specific counters
     */
    private String Value;

    /**
     * zone id of counter
     */
    private String ZoneId;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public String getZoneId() {
        return ZoneId;
    }

    public void setZoneId(String ZoneId) {
        this.ZoneId = ZoneId;
    }

}
