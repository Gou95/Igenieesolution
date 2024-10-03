package com.example.igenieesolution.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirPurifierResponse {

    @SerializedName("messageId")
    @Expose
    private String messageId;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("response")
    @Expose

    private Response response;
    @SerializedName("status")
    @Expose

    private Status status;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public class AirFlow {

        @SerializedName("windStrength")
        @Expose
        private String windStrength;

        public String getWindStrength() {
            return windStrength;
        }

        public void setWindStrength(String windStrength) {
            this.windStrength = windStrength;
        }

    }

    public class AirPurifierJobMode {

        @SerializedName("currentJobMode")
        @Expose
        private String currentJobMode;

        public String getCurrentJobMode() {
            return currentJobMode;
        }

        public void setCurrentJobMode(String currentJobMode) {
            this.currentJobMode = currentJobMode;
        }

    }

    public class AirQualitySensor {

        @SerializedName("PM1")
        @Expose
        private Integer pm1;
        @SerializedName("PM2")
        @Expose
        private Integer pm2;
        @SerializedName("PM10")
        @Expose
        private Integer pm10;
        @SerializedName("oder")
        @Expose
        private Integer oder;
        @SerializedName("odor")
        @Expose
        private Integer odor;
        @SerializedName("odorLevel")
        @Expose
        private String odorLevel;
        @SerializedName("totalPollution")
        @Expose
        private Integer totalPollution;
        @SerializedName("totalPollutionLevel")
        @Expose
        private String totalPollutionLevel;
        @SerializedName("monitoringEnabled")
        @Expose
        private String monitoringEnabled;

        public Integer getPm1() {
            return pm1;
        }

        public void setPm1(Integer pm1) {
            this.pm1 = pm1;
        }

        public Integer getPm2() {
            return pm2;
        }

        public void setPm2(Integer pm2) {
            this.pm2 = pm2;
        }

        public Integer getPm10() {
            return pm10;
        }

        public void setPm10(Integer pm10) {
            this.pm10 = pm10;
        }

        public Integer getOder() {
            return oder;
        }

        public void setOder(Integer oder) {
            this.oder = oder;
        }

        public Integer getOdor() {
            return odor;
        }

        public void setOdor(Integer odor) {
            this.odor = odor;
        }

        public String getOdorLevel() {
            return odorLevel;
        }

        public void setOdorLevel(String odorLevel) {
            this.odorLevel = odorLevel;
        }

        public Integer getTotalPollution() {
            return totalPollution;
        }

        public void setTotalPollution(Integer totalPollution) {
            this.totalPollution = totalPollution;
        }

        public String getTotalPollutionLevel() {
            return totalPollutionLevel;
        }

        public void setTotalPollutionLevel(String totalPollutionLevel) {
            this.totalPollutionLevel = totalPollutionLevel;
        }

        public String getMonitoringEnabled() {
            return monitoringEnabled;
        }

        public void setMonitoringEnabled(String monitoringEnabled) {
            this.monitoringEnabled = monitoringEnabled;
        }

    }

    public class Operation {

        @SerializedName("airPurifierOperationMode")
        @Expose
        private String airPurifierOperationMode;

        public String getAirPurifierOperationMode() {
            return airPurifierOperationMode;
        }

        public void setAirPurifierOperationMode(String airPurifierOperationMode) {
            this.airPurifierOperationMode = airPurifierOperationMode;
        }

    }

    public class Response {

        @SerializedName("airPurifierJobMode")
        @Expose

        private AirPurifierJobMode airPurifierJobMode;
        @SerializedName("operation")
        @Expose

        private Operation operation;
        @SerializedName("timer")
        @Expose

        private Timer timer;
        @SerializedName("sleepTimer")
        @Expose

        private SleepTimer sleepTimer;
        @SerializedName("airFlow")
        @Expose

        private AirFlow airFlow;
        @SerializedName("airQualitySensor")
        @Expose

        private AirQualitySensor airQualitySensor;

        public AirPurifierJobMode getAirPurifierJobMode() {
            return airPurifierJobMode;
        }

        public void setAirPurifierJobMode(AirPurifierJobMode airPurifierJobMode) {
            this.airPurifierJobMode = airPurifierJobMode;
        }

        public Operation getOperation() {
            return operation;
        }

        public void setOperation(Operation operation) {
            this.operation = operation;
        }

        public Timer getTimer() {
            return timer;
        }

        public void setTimer(Timer timer) {
            this.timer = timer;
        }

        public SleepTimer getSleepTimer() {
            return sleepTimer;
        }

        public void setSleepTimer(SleepTimer sleepTimer) {
            this.sleepTimer = sleepTimer;
        }

        public AirFlow getAirFlow() {
            return airFlow;
        }

        public void setAirFlow(AirFlow airFlow) {
            this.airFlow = airFlow;
        }

        public AirQualitySensor getAirQualitySensor() {
            return airQualitySensor;
        }

        public void setAirQualitySensor(AirQualitySensor airQualitySensor) {
            this.airQualitySensor = airQualitySensor;
        }

    }
    public class SleepTimer {

        @SerializedName("relativeStopTimer")
        @Expose
        private String relativeStopTimer;

        public String getRelativeStopTimer() {
            return relativeStopTimer;
        }

        public void setRelativeStopTimer(String relativeStopTimer) {
            this.relativeStopTimer = relativeStopTimer;
        }

    }
    public class Status {

        @SerializedName("flagMessage")
        @Expose
        private String flagMessage;
        @SerializedName("DeviceType")
        @Expose
        private String deviceType;

        public String getFlagMessage() {
            return flagMessage;
        }

        public void setFlagMessage(String flagMessage) {
            this.flagMessage = flagMessage;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

    }

    public class Timer {

        @SerializedName("absoluteStartTimer")
        @Expose
        private String absoluteStartTimer;
        @SerializedName("absoluteStopTimer")
        @Expose
        private String absoluteStopTimer;

        public String getAbsoluteStartTimer() {
            return absoluteStartTimer;
        }

        public void setAbsoluteStartTimer(String absoluteStartTimer) {
            this.absoluteStartTimer = absoluteStartTimer;
        }

        public String getAbsoluteStopTimer() {
            return absoluteStopTimer;
        }

        public void setAbsoluteStopTimer(String absoluteStopTimer) {
            this.absoluteStopTimer = absoluteStopTimer;
        }

    }

}
