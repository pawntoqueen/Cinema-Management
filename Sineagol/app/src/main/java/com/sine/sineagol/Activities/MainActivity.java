package com.sine.sineagol.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sine.sineagol.R;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private static FirebaseUser user;
    public static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user != null) {
                    startActivity(new Intent(MainActivity.this, MenuActivity.class));
                }
                else startActivity(new Intent(MainActivity.this, MovieActivity.class));

                finish();
            }
        },SPLASH_TIME_OUT);

    }


}
