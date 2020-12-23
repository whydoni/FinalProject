package com.example.mybtpns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybtpns.model.Nasabah;
import com.example.mybtpns.model.NasabahResponse;
import com.example.mybtpns.networking.NasabahApi;
import com.example.mybtpns.networking.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton bt_login;
    private TextView bt_register;
    private TextInputEditText et_username,et_password;
    private NasabahApi nasabahApi;
    private String Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Username = et_username.getText().toString();
                Password = et_password.getText().toString();
                letsLogin(Username, Password);
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initView(){
        bt_login = findViewById(R.id.bt_login);
        et_username =findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        bt_register=findViewById(R.id.bt_register);

    }

    private void letsLogin(String username, String password) {
        nasabahApi = RetrofitService.getNasabahApi();
        Call<NasabahResponse> nasabahResponseCall = nasabahApi.userLogin(username, password);
        nasabahResponseCall.enqueue(new Callback<NasabahResponse>() {
            @Override
            public void onResponse(Call<NasabahResponse> call, Response<NasabahResponse> response) {
                if(response.body() != null && response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, response.body().getNasabah().getUsername(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Gagalsss", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NasabahResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doLogin(){
        if (!et_username.getText().toString().equals("")&&!et_password.getText().toString().equals("")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            Toast.makeText(getApplicationContext(),"Yeay!" + et_username.getText() + " kamu berhasil Login.",Toast.LENGTH_LONG).show();
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Mohon isi Username dan Password",Toast.LENGTH_LONG).show();
        }
    }

//    public void login(){
//        Nasabah nasabah = new Nasabah();
//        nasabah.setUsername(et_username.getText().toString());
//        nasabah.setPassword(et_password.getText().toString());
//
//        Call<NasabahResponse> nasabahResponseCall = RetrofitService.getNasabahApi().userLogin(nasabah);
//        nasabahResponseCall.enqueue(new Callback<NasabahResponse>() {
//            @Override
//            public void onResponse(Call<NasabahResponse> call, Response<NasabahResponse> response) {
//                if(response.isSuccessful()){
//                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                    Toast.makeText(getApplicationContext(),"Login Sukses", Toast.LENGTH_LONG).show();
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(getApplicationContext(),"Login Failed", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NasabahResponse> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Throwable " + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
//            }
//        });
//    }


}