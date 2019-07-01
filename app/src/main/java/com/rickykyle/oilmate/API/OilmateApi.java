package com.rickykyle.oilmate.API;

import com.rickykyle.oilmate.Reading;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface OilmateApi {


    @Headers("x-access-token: oilmateaccesspass")
    @GET("readings")
    Call<List<Reading>> loadReadings();
}
