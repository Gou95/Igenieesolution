package com.example.igenieesolution.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcResponse {

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

    public class AirConJobMode {

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
        @SerializedName("totalPollution")
        @Expose
        private Integer totalPollution;
        @SerializedName("totalPollutionLevel")
        @Expose
        private String totalPollutionLevel;

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

    }
    public class Operation {

        @SerializedName("airConOperationMode")
        @Expose
        private String airConOperationMode;

        public String getAirConOperationMode() {
            return airConOperationMode;
        }

        public void setAirConOperationMode(String airConOperationMode) {
            this.airConOperationMode = airConOperationMode;
        }

    }
    public class Response {

        @SerializedName("airConJobMode")
        @Expose

        private AirConJobMode airConJobMode;
        @SerializedName("airQualitySensor")
        @Expose

        private AirQualitySensor airQualitySensor;
        @SerializedName("temperature")
        @Expose

        private Temperature temperature;
        @SerializedName("airFlow")
        @Expose

        private AirFlow airFlow;
        @SerializedName("operation")
        @Expose

        private Operation operation;
        @SerializedName("timer")
        @Expose

        private Timer timer;
        @SerializedName("sleepTimer")
        @Expose

        private SleepTimer sleepTimer;

        public AirConJobMode getAirConJobMode() {
            return airConJobMode;
        }

        public void setAirConJobMode(AirConJobMode airConJobMode) {
            this.airConJobMode = airConJobMode;
        }

        public AirQualitySensor getAirQualitySensor() {
            return airQualitySensor;
        }

        public void setAirQualitySensor(AirQualitySensor airQualitySensor) {
            this.airQualitySensor = airQualitySensor;
        }

        public Temperature getTemperature() {
            return temperature;
        }

        public void setTemperature(Temperature temperature) {
            this.temperature = temperature;
        }

        public AirFlow getAirFlow() {
            return airFlow;
        }

        public void setAirFlow(AirFlow airFlow) {
            this.airFlow = airFlow;
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
    public class Temperature {

        @SerializedName("currentTemperature")
        @Expose
        private Float currentTemperature;
        @SerializedName("targetTemperature")
        @Expose
        private Float targetTemperature;
        @SerializedName("unit")
        @Expose
        private String unit;

        public Float getCurrentTemperature() {
            return currentTemperature;
        }

        public void setCurrentTemperature(Float currentTemperature) {
            this.currentTemperature = currentTemperature;
        }

        public Float getTargetTemperature() {
            return targetTemperature;
        }

        public void setTargetTemperature(Float targetTemperature) {
            this.targetTemperature = targetTemperature;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
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
