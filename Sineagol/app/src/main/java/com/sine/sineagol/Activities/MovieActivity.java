package com.sine.sineagol.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sine.sineagol.FirebaseDatabaseHelper;
import com.sine.sineagol.R;
import com.sine.sineagol.Adapters.RecyclerView_Movie;
import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Theatre;
import com.sine.sineagol.models.Ticket;

import java.util.List;

public class MovieActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private static FirebaseUser user;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

        recyclerView = findViewById(R.id.myrecyler);
        LinearLayout lndlogin = findViewById(R.id.lnidlogin);
        if(user!=null)lndlogin.setVisibility(View.INVISIBLE);
        else lndlogin.setVisibility(View.VISIBLE);


        /// Adding theatres just 1 time. So this code is not neccessary if the theatres already exist.

       /*for (int i = 0; i <=3; i++) {
            Theatre theatre = new Theatre("Theatre-"+(i+1));
            new FirebaseDatabaseHelper("Theatre").addTheatre(theatre, new FirebaseDatabaseHelper.DataStatus() {
                @Override
                public void movieIsLoaded(List<Movie> movies, List<String> keys) {
                }
                @Override
                public void ticketIsLoaded(List<Ticket> tickets, List<String> keys) {
                }
                @Override
                public void dataIsInserted() {
                }

                @Override
                public void dataUpdated() {
                }
                @Override
                public void dataDeleted() {

                }
                @Override
                public void theatreIsLoaded(List<Theatre> theatres) {
                }
            });
        }*/


        new FirebaseDatabaseHelper("Movie").readMovies(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void movieIsLoaded(List<Movie> movies, List<String> keys) {
                new RecyclerView_Movie().setConfig(recyclerView,MovieActivity.this,movies,keys);
            }
            @Override
            public void ticketIsLoaded(List<Ticket> tickets, List<String> keys) {
            }
            @Override
            public void dataIsInserted() {
            }
            @Override
            public void dataUpdated() {
            }
            @Override
            public void dataDeleted() {
            }
            @Override
            public void theatreIsLoaded(List<Theatre> theatres){
            }
        });
        lndlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MovieActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
