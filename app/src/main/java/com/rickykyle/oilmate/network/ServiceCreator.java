package com.rickykyle.oilmate.network;

import com.rickykyle.oilmate.utilities.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * This class generates an instance of the Retrofit service.
 */
public class ServiceCreator {

    // Required information to build the connection - BaseURL and the plug in to turn the JSON response
    // to a JAVA object using GSON.
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    // Create an instance of Retrofit.
    private static Retrofit retrofit = builder.build();

    // Generic, publicly available method to create a connection using the interface class passed
    // to it.
    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }

}
