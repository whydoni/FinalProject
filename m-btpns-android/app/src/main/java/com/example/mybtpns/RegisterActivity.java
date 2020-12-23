package com.example.mybtpns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private MaterialButton bt_register;
    private TextView bt_login;
    private TextInputEditText et_regist_nama, et_regist_norek, et_regist_alamat, et_regist_notelp, et_regist_username, et_regist_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });
    }


    private void initView() {
        bt_login    = findViewById(R.id.bt_login);
        bt_register = findViewById(R.id.bt_register);
        et_regist_nama     = findViewById(R.id.et_regist_nama);
        et_regist_norek    = findViewById(R.id.et_regist_norek);
        et_regist_alamat   = findViewById(R.id.et_regist_alamat);
        et_regist_notelp   = findViewById(R.id.et_regist_notelp);
        et_regist_username = findViewById(R.id.et_regist_username);
        et_regist_password = findViewById(R.id.et_regist_password);
    }

    private void doRegister() {
        if (!et_regist_username.getText().toString().equals("")&&!et_regist_password.getText().toString().equals("")){
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            Toast.makeText(getApplicationContext(),"Yeay!" + et_regist_username.getText() + " kamu berhasil Login.",Toast.LENGTH_LONG).show();
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Mohon isi Username dan Password",Toast.LENGTH_LONG).show();
        }
    }

}