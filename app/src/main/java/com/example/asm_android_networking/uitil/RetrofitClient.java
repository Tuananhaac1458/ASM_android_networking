package com.example.asm_android_networking.uitil;

import com.example.asm_android_networking.uitil.API.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = SumString.URLserver;
    private  static RetrofitClient mInstance;


    private RetrofitClient(){
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApt(){
        return retrofit.create(Api.class);
    }
}
