package com.sine.sineagol.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sine.sineagol.FirebaseDatabaseHelper;
import com.sine.sineagol.R;
import com.sine.sineagol.Adapters.RecyclerView_Ticket;
import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Theatre;
import com.sine.sineagol.models.Ticket;

import java.util.List;

public class TicketActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_o_p);
        recyclerView=findViewById(R.id.ticketlstrecycler);

         new FirebaseDatabaseHelper("ticket").readTicket(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void movieIsLoaded(List<Movie> movies, List<String> keys) {

            }

            @Override
            public void ticketIsLoaded(List<Ticket> tickets, List<String> keys) {
                new RecyclerView_Ticket().setConfig(recyclerView, TicketActivity.this,tickets,keys);
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
    }

}
