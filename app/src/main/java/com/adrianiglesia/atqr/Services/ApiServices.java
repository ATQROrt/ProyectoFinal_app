package com.adrianiglesia.atqr.Services;

import com.adrianiglesia.atqr.Model.LoginBody;
import com.adrianiglesia.atqr.Model.ResponseService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("student/testlogin")
    Call<ResponseService> userLogin(@Body LoginBody body);
}
