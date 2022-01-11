package com.sine.sineagol.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sine.sineagol.DesignPatterns.MovieBuilder;
import com.sine.sineagol.FirebaseDatabaseHelper;
import com.sine.sineagol.R;
import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Theatre;
import com.sine.sineagol.models.Ticket;

import java.util.List;

public class DetailsMovieActivity extends AppCompatActivity {

    private EditText edtname,edtcateg,edtimdb,edtdescr,edtimageUrl;
    private Button btnupdate;


    private String key,name,description,category,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        edtname = findViewById(R.id.edtmoviename);
        edtcateg= findViewById(R.id.edtcstph);
        edtimdb = findViewById(R.id.edtimdbmov);
        edtdescr=findViewById(R.id.edtdescript);
        edtimageUrl=findViewById(R.id.edturlpic);


        key=getIntent().getStringExtra("key");
        name=getIntent().getStringExtra("name");
        description=getIntent().getStringExtra("description");
        category=getIntent().getStringExtra("category");
        btnupdate = findViewById(R.id.btnupTicket);
        url=getIntent().getStringExtra("url");
        edtname.setText(name);
        edtdescr.setText(description);
        edtcateg.setText(category);
        edtimageUrl.setText(url);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(edtname.getText().toString().equals("") || edtcateg.getText().toString().equals("") || edtdescr.getText().toString().equals("")||
                        edtimdb.getText().toString().equals("")|| edtimageUrl.getText().toString().equals(""))){


                    Movie movie = MovieBuilder.getMovieOnDetails(key,edtname.getText().toString(),edtdescr.getText().toString(),new Theatre(),Double.parseDouble(edtimdb.getText().toString()),edtcateg.getText().toString(),url);

                    new FirebaseDatabaseHelper("Movie").updateMovies(key, movie, new FirebaseDatabaseHelper.DataStatus() {
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
                            Toast.makeText(DetailsMovieActivity.this,"Succesfully Updated",Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void dataDeleted() {
                        }
                        @Override
                        public void theatreIsLoaded(List<Theatre> theatres) {
                        }
                    });
                }

                else Toast.makeText(getApplicationContext(),"Empty Slots",Toast.LENGTH_LONG).show();

            }
        });
    }



}
