package com.rickykyle.oilmate.network;

import com.rickykyle.oilmate.entities.Reading;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface OilmateApi {


    @Headers("x-access-token: oilmateaccesspass")
    @GET("readings")
    Call<List<Reading>> getOilReadings();
}
