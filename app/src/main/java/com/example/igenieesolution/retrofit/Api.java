package com.example.igenieesolution.retrofit;





import com.example.igenieesolution.Response.AcResponse;
import com.example.igenieesolution.Response.AirPurifierResponse;
import com.example.igenieesolution.Response.GetLightResponse;
import com.example.igenieesolution.Response.LightResponse;
import com.example.igenieesolution.Response.LockResponse;
import com.example.igenieesolution.Response.RefrigeratorResponse;
import com.example.igenieesolution.Response.SetACResponse;
import com.example.igenieesolution.Response.SetRefrigeratorResponse;
import com.example.igenieesolution.Response.WashingResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("postData.php")
    Call<AcResponse> getACResponse(@Field("id") String id);

    @FormUrlEncoded
    @POST("setData.php")
    Call<SetACResponse> getSetResponse(@Field("id") String id, @Field("id1") String id1);


    @FormUrlEncoded
    @POST("postData.php")
    Call<RefrigeratorResponse> getRefrigeratorResponse(@Field("id") String id);


    @FormUrlEncoded
    @POST("setData.php")
    Call<SetRefrigeratorResponse> getExpressMode(@Field("id") String id, @Field("id1") String id1);

    @FormUrlEncoded
    @POST("ctrlLightControl.php")
    Call<LightResponse> getLightRes(@Field("id") String id);

    @FormUrlEncoded
    @POST("ctrlLightControl.php")
    Call<LightResponse> getLightOneTwo(@Field("id1") String id1,@Field("id2") String id2);

    @FormUrlEncoded
    @POST("ctrlLightControl.php")
    Call<LightResponse> getLightDimming(@Field("id1") String id1,@Field("id2") String id2,@Field("id3") String id3,@Field("id4") String id4);


    @GET("GetLightStatus.php")
    Call<GetLightResponse> getLiveResponse();

    @GET("bas_api.php")
    Call<LockResponse> getLockResponse();

    @FormUrlEncoded
    @POST("postData.php")
    Call<WashingResponse> getWashingResponse(@Field("id") String id);
    @FormUrlEncoded
    @POST("setData.php")
    Call<WashingResponse> getSetWashingResponse(@Field("id") String id,@Field("id") String id1);

    @FormUrlEncoded
    @POST("postData.php")
    Call<AirPurifierResponse> getAirPurifierRes(@Field("id")String id);

    @FormUrlEncoded
    @POST("setData.php")
    Call<AirPurifierResponse> setAirPurifierRes(@Field("id")String id,@Field("id1") String id1);


}