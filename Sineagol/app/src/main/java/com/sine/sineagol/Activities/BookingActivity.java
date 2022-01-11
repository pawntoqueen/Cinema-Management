package com.sine.sineagol.Activities;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sine.sineagol.DesignPatterns.PersonFactory;
import com.sine.sineagol.FirebaseDatabaseHelper;
import com.sine.sineagol.R;
import com.sine.sineagol.models.Customer;
import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Person;
import com.sine.sineagol.models.Theatre;
import com.sine.sineagol.models.Ticket;

import java.util.List;

public class BookingActivity extends AppCompatActivity {


    Button[] btnseats = new Button[28];
    int colorFull,colorEmpty,colorEmptyText,colorFullText;
    Dialog dialog;
    private String key;
    Customer customer;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ticket);

        btnseats[0]=findViewById(R.id.btnseat1);
        btnseats[1]=findViewById(R.id.btnseat2);
        btnseats[2]=findViewById(R.id.btnseat3);
        btnseats[3]=findViewById(R.id.btnseat4);
        btnseats[4]=findViewById(R.id.btnseat5);
        btnseats[5]=findViewById(R.id.btnseat6);
        btnseats[6]=findViewById(R.id.btnseat7);
        btnseats[7]=findViewById(R.id.btnseat8);
        btnseats[8]=findViewById(R.id.btnseat9);
        btnseats[9]=findViewById(R.id.btnseat10);
        btnseats[10]=findViewById(R.id.btnseat11);
        btnseats[11]=findViewById(R.id.btnseat12);
        btnseats[12]=findViewById(R.id.btnseat13);
        btnseats[13]=findViewById(R.id.btnseat14);
        btnseats[14]=findViewById(R.id.btnseat15);
        btnseats[15]=findViewById(R.id.btnseat16);
        btnseats[16]=findViewById(R.id.btnseat17);
        btnseats[17]=findViewById(R.id.btnseat18);
        btnseats[18]=findViewById(R.id.btnseat19);
        btnseats[19]=findViewById(R.id.btnseat20);
        btnseats[20]=findViewById(R.id.btnseat21);
        btnseats[21]=findViewById(R.id.btnseat22);
        btnseats[22]=findViewById(R.id.btnseat23);
        btnseats[23]=findViewById(R.id.btnseat24);
        btnseats[24]=findViewById(R.id.btnseat25);
        btnseats[25]=findViewById(R.id.btnseat26);
        btnseats[26]=findViewById(R.id.btnseat27);
        btnseats[27]=findViewById(R.id.btnseat28);



        colorFull =Color.rgb(	33, 33, 33);
        colorEmpty=Color.rgb(	158, 158, 158);
        colorEmptyText=Color.rgb(	239, 235, 233);
        colorFullText=Color.rgb(221, 44, 0);



        key=getIntent().getStringExtra("key");


        ////////////////////////////////////////////////////////////////////////
        for (int i = 0; i <btnseats.length ; i++) {


            final int finalI = i;
            ////////////////////////



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

                    if(theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI).isStatus()==true){
                        btnseats[finalI].setBackgroundColor(colorFull);
                        btnseats[finalI].setClickable(false);
                        btnseats[finalI].setText("Full");
                        btnseats[finalI].setTextColor(colorFullText);
                    }
                    else{
                        btnseats[finalI].setBackgroundColor(colorEmpty);
                        btnseats[finalI].setClickable(true);
                        btnseats[finalI].setText(theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI).getCode());
                        btnseats[finalI].setTextColor(colorEmptyText);
                    }

                }

            });

            ///////////////////////

            btnseats[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog =new Dialog(BookingActivity.this,R.style.Dialog);
                    dialog.setContentView(R.layout.custom_dialog);
                    dialog.setTitle("Book Seat");
                    TextView txtseatno=dialog.findViewById(R.id.txtseatNo);
                    txtseatno.setText(btnseats[finalI].getText().toString());
                    TextView txtprice =dialog.findViewById(R.id.txtprice);
                    txtprice.setText("50TL");
                    Button btncancel = dialog.findViewById(R.id.btncancel);
                    Button btnbook = dialog.findViewById(R.id.btnbook);
                    final EditText edtcstname = dialog.findViewById(R.id.edtcstname);
                    final EditText edtcstsurname = dialog.findViewById(R.id.edtcstsurname);
                    final EditText edtcstphone = dialog.findViewById(R.id.edtcstph);
                    final EditText edtcstmail = dialog.findViewById(R.id.edtcstmail);



                    btnbook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(!edtcstname.getText().toString().equals("") && !edtcstsurname.getText().toString().equals("") && !edtcstphone.getText().toString().equals("")
                                    && !edtcstmail.getText().toString().equals("") && edtcstphone.getText().toString().length()==10 && edtcstmail.getText().toString().contains("@")
                                    && edtcstmail.getText().toString().endsWith(".com")){

                                String name =edtcstname.getText().toString();
                                String surname =edtcstsurname.getText().toString();
                                String phone =edtcstphone.getText().toString();
                                String email =edtcstmail.getText().toString();

                                customer= (Customer) PersonFactory.getPerson("22",name,surname,email,phone,"Customer",0,"","");


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


                                        if(theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI).isStatus()==false){

                                            theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI).setStatus(true);
                                            theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI).setTheatreName( theatres.get(Integer.valueOf(key)-1).getName());
                                            btnseats[finalI].setBackgroundColor(colorFull);
                                            btnseats[finalI].setClickable(false);

                                            ///Initializing ticket
                                            Ticket ticket = new Ticket(customer,theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI),50);

                                            //// Adding to firebase
                                            new FirebaseDatabaseHelper("ticket").addTicket(ticket, new FirebaseDatabaseHelper.DataStatus() {
                                                @Override
                                                public void movieIsLoaded(List<Movie> movies, List<String> keys) {
                                                }
                                                @Override
                                                public void ticketIsLoaded(List<Ticket> tickets, List<String> keys) {
                                                }
                                                @Override
                                                public void dataIsInserted() {
                                                    Toast.makeText(BookingActivity.this,"Success ticket",Toast.LENGTH_LONG).show();
                                                    try {
                                                        Thread.sleep(1000);
                                                        System.exit(0);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
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
                                            ////Updating seats from theatre

                                            new FirebaseDatabaseHelper("Theatre").updateTheatreSeat(theatres.get(Integer.valueOf(key)-1).getId(),theatres.get(Integer.valueOf(key)-1).getSeats());

                                        }

                                    }

                                });

                                dialog.dismiss();

                            }
                            else if(edtcstphone.getText().toString().length()<10)
                                Toast.makeText(BookingActivity.this,"Check your phone",Toast.LENGTH_SHORT).show();
                            else if(!edtcstmail.getText().toString().contains("@") || !edtcstmail.getText().toString().endsWith(".com"))
                                Toast.makeText(BookingActivity.this,"Check your e-mail (exp.format: @blabla.com)",Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(BookingActivity.this,"Fill the blanks",Toast.LENGTH_SHORT).show();
                        }




                    });
                    btncancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }

            });





        }

    }
}
