package com.example.mybtpns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mutasi:
                        startActivity(new Intent(getApplicationContext(), MutasiActivity.class));
                        overridePendingTransition(1,1);
                        return true;
                    case R.id.pulsa:
                        startActivity(new Intent(getApplicationContext(), PulsaActivity.class));
                        overridePendingTransition(1,1);
                        return true;
                    case R.id.home:
                        return true;
                }
                return false;
            }
        });
    }

    private void initView(){
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }
}