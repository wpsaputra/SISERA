package com.virtualbox.torchick.rog.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Torchick on 27/03/2017.
 */

public class ApiClient {
//    public static final String BASE_URL = "https://kendarikota.bps.go.id";
//    public static final String BASE_URL = "http://sultra.bps.go.id";
    public static final String BASE_URL = "https://webapi.bps.go.id";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
