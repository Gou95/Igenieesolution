package com.example.igenieesolution.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SetRefrigeratorResponse {

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
    public class Doorstatus {

        @SerializedName("doorState")
        @Expose
        private String doorState;
        @SerializedName("locationName")
        @Expose
        private String locationName;

        public String getDoorState() {
            return doorState;
        }

        public void setDoorState(String doorState) {
            this.doorState = doorState;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

    }
    public class Refrigeration {

        @SerializedName("expressMode")
        @Expose
        private Boolean expressMode;
        @SerializedName("expressModeName")
        @Expose
        private String expressModeName;
        @SerializedName("freshAirFilter")
        @Expose
        private String freshAirFilter;

        public Boolean getExpressMode() {
            return expressMode;
        }

        public void setExpressMode(Boolean expressMode) {
            this.expressMode = expressMode;
        }

        public String getExpressModeName() {
            return expressModeName;
        }

        public void setExpressModeName(String expressModeName) {
            this.expressModeName = expressModeName;
        }

        public String getFreshAirFilter() {
            return freshAirFilter;
        }

        public void setFreshAirFilter(String freshAirFilter) {
            this.freshAirFilter = freshAirFilter;
        }

    }
    public class Response {

        @SerializedName("temperature")
        @Expose

        private ArrayList<Temperature> temperature;
        @SerializedName("refrigeration")
        @Expose

        private Refrigeration refrigeration;
        @SerializedName("doorStatus")
        @Expose

        private List<Doorstatus> doorStatus;

        public List<Temperature> getTemperature() {
            return temperature;
        }

        public void setTemperature(ArrayList<Temperature> temperature) {
            this.temperature = temperature;
        }

        public Refrigeration getRefrigeration() {
            return refrigeration;
        }

        public void setRefrigeration(Refrigeration refrigeration) {
            this.refrigeration = refrigeration;
        }

        public List<Doorstatus> getDoorStatus() {
            return doorStatus;
        }

        public void setDoorStatus(List<Doorstatus> doorStatus) {
            this.doorStatus = doorStatus;
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

        @SerializedName("targetTemperature")
        @Expose
        private String targetTemperature;
        @SerializedName("unit")
        @Expose
        private String unit;
        @SerializedName("locationName")
        @Expose
        private String locationName;

        public String getTargetTemperature() {
            return targetTemperature;
        }

        public void setTargetTemperature(String targetTemperature) {
            this.targetTemperature = targetTemperature;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

    }
}
