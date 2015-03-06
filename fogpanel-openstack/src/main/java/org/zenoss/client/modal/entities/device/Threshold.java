package org.zenoss.client.modal.entities.device;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Setting Threshold limit
 *
 * @author developer
 */
public class Threshold {

    private String uid;
    private String thresholdType;
    private String thresholdId;
    private List<String> dataPoints;
    private String explanation;
    private List<String> dsnames;
    private String maxval;
    private String minval;
    private String resolution;
    private Long serverity;
    private String alarmType;
    private String partitionName;

    public Threshold(Builder builder) {

        this.uid = builder.uid;
        this.thresholdType = builder.thresholdType;
        this.thresholdId = builder.thresholdId;
        this.dataPoints = builder.dataPoints;
        this.explanation = builder.explanation;
        this.dsnames = builder.dsnames;
        this.maxval = builder.maxval;
        this.minval = builder.minval;
        this.resolution = builder.resolution;
        this.serverity = builder.serverity;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getThresholdType() {
        return thresholdType;
    }

    public void setThresholdType(String thresholdType) {
        this.thresholdType = thresholdType;
    }

    public String getThresholdId() {
        return thresholdId;
    }

    public void setThresholdId(String thresholdId) {
        this.thresholdId = thresholdId;
    }

    public List<String> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<String> dataPoints) {
        this.dataPoints = dataPoints;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<String> getDsnames() {
        return dsnames;
    }

    public void setDsnames(List<String> dsnames) {
        this.dsnames = dsnames;
    }

    public String getMaxval() {
        return maxval;
    }

    public void setMaxval(String maxval) {
        this.maxval = maxval;
    }

    public String getMinval() {
        return minval;
    }

    public void setMinval(String minval) {
        this.minval = minval;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Long getServerity() {
        return serverity;
    }

    public void setServerity(Long serverity) {
        this.serverity = serverity;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }
    

    public static class Builder {

        private String uid;
        private String thresholdType;
        private String thresholdId;
        private List<String> dataPoints;
        private String explanation;
        private List<String> dsnames;
        private String maxval;
        private String minval;
        private String resolution;
        private Long serverity;

        public Builder(String thresholdId, String thresholdType) {
            this.thresholdId = thresholdId;
            this.thresholdType = thresholdType;
        }

        public Builder(String uid) {
            this.uid = uid;
        }

        public Builder dataPoints(List<String> dataPoints) {
            this.dataPoints = dataPoints;
            return this;
        }

        public Builder explanation(String explanation) {
            this.explanation = explanation;
            return this;
        }

        public Builder dsnames(List<String> dsnames) {
            this.dsnames = dsnames;
            return this;
        }

        public Builder maxval(String maxval) {
            this.maxval = maxval;
            return this;
        }

        public Builder minval(String minval) {
            this.minval = minval;
            return this;
        }

        public Builder resolution(String resolution) {
            this.resolution = resolution;
            return this;
        }

        public Builder serverity(Long serverity) {
            this.serverity = serverity;
            return this;
        }
        
        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public Threshold build() {
            return new Threshold(this);
        }

    }

    public Map toHashMap() {

        Map map = new HashMap();

        map.put("thresholdId", this.thresholdId);
        map.put("thresholdType", this.thresholdType);
        map.put("uid", this.uid);
        map.put("dataPoints", (this.dataPoints != null && !this.dataPoints.isEmpty()) ? this.dataPoints : "");

        return map;
    }
}
