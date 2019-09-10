package com.adrianiglesia.atqr.Services;

import com.adrianiglesia.atqr.Model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("login/")
    Call<User> userLogin(@Field("username") String username, @Field("pass") String pass);
}
