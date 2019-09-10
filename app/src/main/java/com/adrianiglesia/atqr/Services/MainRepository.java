package com.adrianiglesia.atqr.Services;

import com.adrianiglesia.atqr.Model.User;

import retrofit2.Retrofit;

public class MainRepository {

    private ApiServices services;
    private Retrofit retrofit;
    

    public MainRepository(){
//        retrofit = MyRetrofit.getRetrofit();
       // services = retrofit.create(ApiServices.class);
    }

    public void userLogin(String username, String pass, LoginInterface callback){

        //Por el momento utilizo Gson para leer desde un Json local hasta que tengamos la APi lista.
        //Aca se supone que llamaria utilizando retrofit

        if(username.equals("adrian") && pass.equals("123456")){
            User user = User.getInstance();
            user.setName("aldana");
            user.setLastName("vazquez");
            callback.onSuccess(user);

        }else{
           callback.onError("Error al querer ingresar, verifique los datos ingresados");
        }

    }


    public interface LoginInterface{
        void onSuccess(User user);
        void onError(String msg);
    }
}
