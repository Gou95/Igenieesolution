package com.example.igenieesolution.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLightResponse {

    @SerializedName("status")
    @Expose

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public class Response {

        @SerializedName("Area")
        @Expose
        private Integer area;
        @SerializedName("channel_1")
        @Expose
        private String channel1;
        @SerializedName("channel_2")
        @Expose
        private String channel2;
        @SerializedName("channel_3")
        @Expose
        private String channel3;
        @SerializedName("channel_4")
        @Expose
        private String channel4;
        @SerializedName("channel_4_CT")
        @Expose
        private String channel4CT;
        @SerializedName("channel_1_CT")
        @Expose
        private String channel1CT;

        public Integer getArea() {
            return area;
        }

        public void setArea(Integer area) {
            this.area = area;
        }

        public String getChannel1() {
            return channel1;
        }

        public void setChannel1(String channel1) {
            this.channel1 = channel1;
        }

        public String getChannel2() {
            return channel2;
        }

        public void setChannel2(String channel2) {
            this.channel2 = channel2;
        }

        public String getChannel3() {
            return channel3;
        }

        public void setChannel3(String channel3) {
            this.channel3 = channel3;
        }

        public String getChannel4() {
            return channel4;
        }

        public void setChannel4(String channel4) {
            this.channel4 = channel4;
        }

        public String getChannel4CT() {
            return channel4CT;
        }

        public void setChannel4CT(String channel4CT) {
            this.channel4CT = channel4CT;
        }

        public String getChannel1CT() {
            return channel1CT;
        }

        public void setChannel1CT(String channel1CT) {
            this.channel1CT = channel1CT;
        }

    }

    public class Status {

        @SerializedName("flagMessage")
        @Expose
        private String flagMessage;
        @SerializedName("DeviceType")
        @Expose
        private String deviceType;
        @SerializedName("response")
        @Expose

        private Response response;

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

        public Response getResponse() {
            return response;
        }

        public void setResponse(Response response) {
            this.response = response;
        }

    }
}
