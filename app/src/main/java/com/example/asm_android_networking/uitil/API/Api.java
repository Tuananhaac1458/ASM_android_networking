package com.example.asm_android_networking.uitil.API;

import com.example.asm_android_networking.Repository.User;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Api  {
    @GET("/user")
    Call<User> getusers(
            @Query("email") String email,
            @Query("pass") String pass
    );


    @POST("/user")
    Call loginuser(
            @Body RequestBody body);
}
