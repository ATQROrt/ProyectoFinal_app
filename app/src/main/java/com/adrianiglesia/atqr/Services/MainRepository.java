package com.adrianiglesia.atqr.Services;

import android.util.Log;

import com.adrianiglesia.atqr.Model.LoginBody;
import com.adrianiglesia.atqr.Model.ResponseService;
import com.adrianiglesia.atqr.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainRepository {

    private ApiServices services;
    private Retrofit retrofit;
    

    public MainRepository(){
        retrofit = MyRetrofit.getRetrofit();
        services = retrofit.create(ApiServices.class);
    }

    public void userLogin(Long username, String pass, LoginInterface callback){

        LoginBody body = new LoginBody(username, pass);
        Call<ResponseService> login = services.userLogin(body);
        login.enqueue(new Callback<ResponseService>() {
            @Override
            public void onResponse(Call<ResponseService> call, retrofit2.Response<ResponseService> response) {

                //Log.d("USER",userResponse.toString());

                if(response.isSuccessful() && response.body().getObject() != null){
                    User userResponse = (User) response.body().getObject();
                    callback.onSuccess(userResponse);
                }else{
                    callback.onError("Ups! Algo malio sal");
                }
            }

            @Override
            public void onFailure(Call<ResponseService> call, Throwable t) {
                //FALLA LA LLAMADA AL SERVICIO
            }
        });

    }


    public interface LoginInterface{
        void onSuccess(User user);
        void onError(String msg);
    }
}
