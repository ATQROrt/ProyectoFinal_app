package com.adrianiglesia.atqr.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.adrianiglesia.atqr.Model.User;
import com.adrianiglesia.atqr.Services.MainRepository;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    private MainRepository repository = new MainRepository();

    public void callLogin(Long document, String pass, ErrorCallback callback){
        repository.userLogin(document, pass, new MainRepository.LoginInterface() {
            @Override
            public void onSuccess(User user) {
                userMutableLiveData.setValue(user);
            }

            @Override
            public void onError(String msg) {
                callback.onErrorLogin(msg);

            }
        });
    }


    public LiveData<User> getUserLiveData(){
        return userMutableLiveData;
    }

    public interface ErrorCallback{
        void onErrorLogin(String msg);
    }
}
