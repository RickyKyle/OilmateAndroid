package com.rickykyle.oilmate.network;

import com.rickykyle.oilmate.network.requests.PostNewHiveAccountRequest;
import com.rickykyle.oilmate.network.requests.LoginRequestResponse;
import com.rickykyle.oilmate.network.requests.PutNewTankDimensionsRequest;
import com.rickykyle.oilmate.network.requests.PutNewTargetTemperatureRequest;
import com.rickykyle.oilmate.network.responses.GetCurrentHiveAccountResponse;
import com.rickykyle.oilmate.network.responses.GetCurrentOilLimitResponse;
import com.rickykyle.oilmate.network.responses.GetCurrentTankDimensionsResponse;
import com.rickykyle.oilmate.network.responses.GetCurrentTargetTemperatureResponse;
import com.rickykyle.oilmate.network.responses.Reading;
import com.rickykyle.oilmate.network.requests.PutNewOilLimitRequest;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OilmateApi {


    @GET("readings")
    Call<List<Reading>> getOilReadings(@Header("x-access-token") String token);

    @GET("tank/oillowerlimit/{userID}")
    Call<List<GetCurrentOilLimitResponse>> getOilLowerLimit(@Header("x-access-token") String token,
                                                            @Path("userID") int userID);

    @GET("tank/{userID}")
    Call<List<GetCurrentTankDimensionsResponse>> getCurrentTankDimensions(@Header("x-access-token") String token,
                                                                          @Path("userID") int userID);

    @GET("hive/user/{userID}")
    Call<List<GetCurrentTargetTemperatureResponse>> getTargetTemperature(@Header("x-access-token") String token,
                                                                         @Path("userID") int userID);

    @GET("hive/user/{userID}")
    Call<List<GetCurrentHiveAccountResponse>> getCurrentHiveAccount(@Header("x-access-token") String token,
                                                                    @Path("userID") int userID);

    @PUT("tank/update/oillowerlimit/{userID}")
    @FormUrlEncoded
    Call<PutNewOilLimitRequest> putNewOilLimit(@Header("x-access-token") String token,
                                               @Path("userID") int userID,
                                               @Field("oilLowerLimit") int oilLowerLimit);

    @PUT("tank/update/dimensions/{userID}")
    @FormUrlEncoded
    Call<PutNewTankDimensionsRequest> putNewTankDimensions(@Header("x-access-token") String token,
                                                           @Path("userID") int userID,
                                                           @Field("diameter") double diameter,
                                                           @Field("length") double length);

    @PUT("hive/update/settemperature/{userID}")
    @FormUrlEncoded
    Call<PutNewTargetTemperatureRequest> putNewTargetTemperature(@Header("x-access-token") String token,
                                                                 @Path("userID") int userID,
                                                                 @Field("setTemperature") double targetTemperature);

    @POST("hive/new/")
    @FormUrlEncoded
    Call<PostNewHiveAccountRequest> postNewHiveAccount(@Header("x-access-token") String token,
                                                       @Field("userID") int userID,
                                                       @Field("hiveUsername") String hiveUsername,
                                                       @Field("hivePassword") String hivePassword,
                                                       @Field("setTemperature") double targetTemperature);

    @POST("login")
    @FormUrlEncoded
    Call<LoginRequestResponse> postLogin(@Field("username") String username,
                                         @Field("password") String password);
}