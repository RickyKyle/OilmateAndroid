package com.rickykyle.oilmate.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator {

    // Base URL for the API.
    private static final String BASE_URL = "http://159.65.93.37/api/";

    // Required information to build the connection - BaseURL and the plug in to turn the JSON response
    // to a JAVA object using GSON.
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    // Create an instance of Retrofit.
    private static Retrofit retrofit = builder.build();

    // Generic, publicly available method to create a connection using the interface class passed
    // to it.
    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }

}