package com.sine.sineagol.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sine.sineagol.R;

public class LoginActivity extends AppCompatActivity {

    private EditText logemail,logpass;
    private Button btnlogin;
    private ProgressBar progbar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        logemail = findViewById(R.id.edtemailconf);
        logpass=findViewById(R.id.edtpassconf);
        btnlogin=findViewById(R.id.btnlogin);
        progbar =findViewById(R.id.progbarlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty())return;
                inProgress(true);
                mAuth.signInWithEmailAndPassword(logemail.getText().toString(),logpass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LoginActivity.this,"Login successfull",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        return;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(LoginActivity.this,"Check your email or password!"+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    private void inProgress(boolean flag){
        if(flag){
            progbar.setVisibility(View.VISIBLE);
            btnlogin.setEnabled(false);
        }
        else{
            progbar.setVisibility(View.GONE);
            btnlogin.setEnabled(true);
        }
    }
    public boolean isEmpty(){
        if(TextUtils.isEmpty(logemail.getText().toString())){
            logemail.setError("Required!");
            return true;
        }
        if(TextUtils.isEmpty(logpass.getText().toString())){
            logpass.setError("Required");
            return true;
        }
        return  false;
    }
}
