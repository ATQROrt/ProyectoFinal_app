package com.adrianiglesia.atqr.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adrianiglesia.atqr.MateriasActivity;
import com.adrianiglesia.atqr.R;
import com.adrianiglesia.atqr.ViewModel.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.bt_login_2)
    Button btnLogin;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.edt_user)
    EditText edtUser;


    private String username;
    private String pass;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        btnLogin.setOnClickListener(v -> {
            username = edtUser.getText().toString();
            pass = edtPass.getText().toString();
            if(validate(username, pass)){
                //llamo el viewmodel para obtener el usaurio
                loginViewModel.callLogin(username, pass, msg -> Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show());
            }else{
                Toast.makeText(LoginActivity.this, "Verifique los campos ingresados", Toast.LENGTH_SHORT).show();
            }
        });

        loginViewModel.getUserLiveData().observe(this, user -> {
            Log.d("SUCCES_LOGIN_NAME",user.getName());
            Log.d("SUCCES_LOGIN_LASTNAME", user.getLastName());
            Intent intent = new Intent(this, MateriasActivity.class);
            intent.putExtra("USER", user);
            startActivity(intent);
        });

    }

    private Boolean validate(String username, String pass){

        //Valido que los campos ingresados esten correctos

        return true;
    }


}
