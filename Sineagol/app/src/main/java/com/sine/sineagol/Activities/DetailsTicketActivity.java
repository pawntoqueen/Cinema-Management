package com.sine.sineagol.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sine.sineagol.FirebaseDatabaseHelper;
import com.sine.sineagol.R;
import com.sine.sineagol.models.Customer;
import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Seat;
import com.sine.sineagol.models.Theatre;
import com.sine.sineagol.models.Ticket;

import java.util.List;

public class DetailsTicketActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button btnupdate,btndelete;
    private EditText edtname,edtsrname,edtphone,edtmail;
    private String key,name,srname,phone,mail,seatIntent,theatre;
    private Spinner spinner;
    private String spintext="A-1";
    private int a =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_ticket);


        key=getIntent().getStringExtra("key");
        name=getIntent().getStringExtra("name");
        srname=getIntent().getStringExtra("surname");
        phone=getIntent().getStringExtra("phone");
        mail=getIntent().getStringExtra("email");
        seatIntent=getIntent().getStringExtra("seatno");
        theatre=getIntent().getStringExtra("theatre");


        edtname=findViewById(R.id.edtdetname);
        edtsrname=findViewById(R.id.edtdetsrname);
        edtphone=findViewById(R.id.edtdetph);
        edtmail=findViewById(R.id.edtdetmail);
        spinner=findViewById(R.id.edtdetspinner);
        btnupdate=findViewById(R.id.btnupTicket);
        btndelete=findViewById(R.id.btndeleteTicket);


        edtname.setText(name);
        edtsrname.setText(srname);
        edtphone.setText(phone);
        edtmail.setText(mail);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.seats,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        if(seatIntent!=null){
            int spposition = adapter.getPosition(seatIntent);
            spinner.setSelection(spposition);
        }

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FirebaseDatabaseHelper("Theatre").readTheatre(new FirebaseDatabaseHelper.DataStatus() {
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
                        Theatre t;
                        if(theatre.contains("1"))t=theatres.get(0);
                        else if(theatre.contains("2"))t=theatres.get(1);
                        else if(theatre.contains("3"))t=theatres.get(2);
                        else if(theatre.contains("4"))t=theatres.get(3);
                        else return;

                        if(!seatIntent.equals(spintext)){
                            t.getSoloSeatByCode(seatIntent).setStatus(false);
                            t.getSoloSeatByCode(spintext).setStatus(true);
                            Log.e("slm",key);
                            seatIntent=spintext;
                            new FirebaseDatabaseHelper("Theatre").updateTheatreSeat(t.getId(),t.getSeats());
                        }

                        Ticket ticket = new Ticket();
                        Customer customer=new Customer();
                        customer.setName(edtname.getText().toString());
                        customer.setEmail(edtmail.getText().toString());
                        customer.setSurname(edtsrname.getText().toString());
                        customer.setPhone(edtphone.getText().toString());

                        Seat seat =new Seat();
                        seat.setStatus(true);
                        seat.setCode(seatIntent);
                        seat.setTheatreName(theatre);
                        ticket.setCustomer(customer);
                        ticket.setPrice(50);
                        ticket.setSeat(seat);

                        new FirebaseDatabaseHelper("ticket").updateTicket(key, ticket, new FirebaseDatabaseHelper.DataStatus() {
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
                                Toast.makeText(DetailsTicketActivity.this,"Ticket has been updated",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void dataDeleted() {
                            }
                            @Override
                            public void theatreIsLoaded(List<Theatre> theatres) {
                            }

                        });
                    }
                });



            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    new FirebaseDatabaseHelper("ticket").deleteTicket(key, new FirebaseDatabaseHelper.DataStatus() {
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
                            Toast.makeText(DetailsTicketActivity.this,"silindi",Toast.LENGTH_SHORT).show();

                            new FirebaseDatabaseHelper("Theatre").readTheatre(new FirebaseDatabaseHelper.DataStatus() {
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
                                    Theatre t;
                                    if(theatre.contains("1"))t=theatres.get(0);
                                    else if(theatre.contains("2"))t=theatres.get(1);
                                    else if(theatre.contains("3"))t=theatres.get(2);
                                    else if(theatre.contains("4"))t=theatres.get(3);
                                    else return;

                                    t.getSoloSeatByCode(spintext).setStatus(false);

                                    new FirebaseDatabaseHelper("Theatre").updateTheatreSeat(t.getId(),t.getSeats());
                                    finish();return;
                                }
                            });

                            finish();return;
                        }
                        @Override
                        public void theatreIsLoaded(List<Theatre> theatres) {
                        }

                    });
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           spintext =parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
