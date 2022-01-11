package com.sine.sineagol.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sine.sineagol.DesignPatterns.PersonFactory;
import com.sine.sineagol.FirebaseDatabaseHelper;
import com.sine.sineagol.R;
import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Staff;
import com.sine.sineagol.models.Theatre;
import com.sine.sineagol.models.Ticket;

import java.util.List;

public class StaffAddActivity extends AppCompatActivity {

    EditText edtstaffname, edtsurname,edt_staff_phone,edtemail,edtposition,edtsalary,edtstartDate;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_add);


        edtstaffname = findViewById(R.id.edtstaffname);
        edtsurname = findViewById(R.id.edtsurname);
        edt_staff_phone = findViewById(R.id.edt_staff_phone);
        edtemail = findViewById(R.id.edtemail);
        edtposition = findViewById(R.id.edtposition);
        edtsalary = findViewById(R.id.edtsalary);
        edtstartDate = findViewById(R.id.edtstartDate);
        btnSave = findViewById(R.id.btnSave);




        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(edtstaffname.getText().toString().equals("") || edtsurname.getText().toString().equals("") ||
                        edt_staff_phone.getText().toString().equals("")|| edtemail.getText().toString().equals("")|| edtposition.getText().toString().equals("")
                        || edtsalary.getText().toString().equals("")|| edtstartDate.getText().toString().equals(""))){

                    String name = edtstaffname.getText().toString();
                    String surname = edtsurname.getText().toString();
                    String phone = edt_staff_phone.getText().toString();
                    String email = edtemail.getText().toString();
                    double salary =Double.parseDouble(edtsalary.getText().toString());
                    String position = edtposition.getText().toString();
                    String start_date = edtstartDate.getText().toString();
                    Staff stf = (Staff) PersonFactory.getPerson("00000",name,surname,email,phone,"Staff",salary,position,start_date);
                    new FirebaseDatabaseHelper("Staff").addStaff(stf, new FirebaseDatabaseHelper.DataStatus() {
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

                    Toast.makeText(getApplicationContext(),"succsessful",Toast.LENGTH_LONG).show();


                    edtstaffname.setText("");
                    edtsurname.setText("");
                    edt_staff_phone.setText("");
                    edtemail.setText("");
                    edtposition.setText("");
                    edtsalary.setText("");
                    edtstartDate.setText("");


                }
                else Toast.makeText(getApplicationContext(),"Empty Slots",Toast.LENGTH_LONG).show();
            }
        });





    }
}
