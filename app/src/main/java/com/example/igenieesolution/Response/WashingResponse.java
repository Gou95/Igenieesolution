package com.example.igenieesolution.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WashingResponse {


    @SerializedName("messageId")
    @Expose
    private String messageId;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("response")
    @Expose

    private List<Response> response;
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

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public class Location {

        @SerializedName("locationName")
        @Expose
        private String locationName;

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

    }

    public class RemoteControlEnable {

        @SerializedName("remoteControlEnabled")
        @Expose
        private Boolean remoteControlEnabled;

        public Boolean getRemoteControlEnabled() {
            return remoteControlEnabled;
        }

        public void setRemoteControlEnabled(Boolean remoteControlEnabled) {
            this.remoteControlEnabled = remoteControlEnabled;
        }

    }
    public class Response {

        @SerializedName("runState")
        @Expose

        private RunState runState;
        @SerializedName("remoteControlEnable")
        @Expose

        private RemoteControlEnable remoteControlEnable;
        @SerializedName("timer")
        @Expose

        private Timer timer;
        @SerializedName("location")
        @Expose

        private Location location;

        public RunState getRunState() {
            return runState;
        }

        public void setRunState(RunState runState) {
            this.runState = runState;
        }

        public RemoteControlEnable getRemoteControlEnable() {
            return remoteControlEnable;
        }

        public void setRemoteControlEnable(RemoteControlEnable remoteControlEnable) {
            this.remoteControlEnable = remoteControlEnable;
        }

        public Timer getTimer() {
            return timer;
        }

        public void setTimer(Timer timer) {
            this.timer = timer;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

    }
    public class RunState {

        @SerializedName("currentState")
        @Expose
        private String currentState;

        public String getCurrentState() {
            return currentState;
        }

        public void setCurrentState(String currentState) {
            this.currentState = currentState;
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

        @SerializedName("remainHour")
        @Expose
        private Integer remainHour;
        @SerializedName("remainMinute")
        @Expose
        private Integer remainMinute;
        @SerializedName("relativeHourToStart")
        @Expose
        private Integer relativeHourToStart;
        @SerializedName("relativeMinuteToStart")
        @Expose
        private Integer relativeMinuteToStart;
        @SerializedName("totalHour")
        @Expose
        private Integer totalHour;
        @SerializedName("totalMinute")
        @Expose
        private Integer totalMinute;

        public Integer getRemainHour() {
            return remainHour;
        }

        public void setRemainHour(Integer remainHour) {
            this.remainHour = remainHour;
        }

        public Integer getRemainMinute() {
            return remainMinute;
        }

        public void setRemainMinute(Integer remainMinute) {
            this.remainMinute = remainMinute;
        }

        public Integer getRelativeHourToStart() {
            return relativeHourToStart;
        }

        public void setRelativeHourToStart(Integer relativeHourToStart) {
            this.relativeHourToStart = relativeHourToStart;
        }

        public Integer getRelativeMinuteToStart() {
            return relativeMinuteToStart;
        }

        public void setRelativeMinuteToStart(Integer relativeMinuteToStart) {
            this.relativeMinuteToStart = relativeMinuteToStart;
        }

        public Integer getTotalHour() {
            return totalHour;
        }

        public void setTotalHour(Integer totalHour) {
            this.totalHour = totalHour;
        }

        public Integer getTotalMinute() {
            return totalMinute;
        }

        public void setTotalMinute(Integer totalMinute) {
            this.totalMinute = totalMinute;
        }

    }
}
