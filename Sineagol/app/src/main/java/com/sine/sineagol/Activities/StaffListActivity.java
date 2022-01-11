package com.sine.sineagol.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.sine.sineagol.R;
import com.sine.sineagol.Adapters.Adapters.StaffAdapter;
import com.sine.sineagol.models.Staff;

import java.util.ArrayList;

public class StaffListActivity extends AppCompatActivity {

    ListView listStaff;
    ArrayList<Staff> staffs;
    private DatabaseReference mDatabase;
    StaffAdapter adapter;
    Button btnDelete;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Staff");
        staffs = new ArrayList<>() ;
        listStaff = findViewById(R.id.listStaff);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String name = (String)(postSnapshot.child("name").getValue());
                    String surname = (String)(postSnapshot.child("surname").getValue());
                    String phone = (String)(postSnapshot.child("phone").getValue());
                    String email = (String)(postSnapshot.child("email").getValue());
                    double salary = Double.parseDouble(postSnapshot.child("salary").getValue().toString());
                    String position = (String)(postSnapshot.child("position").getValue());
                    String start_date = (String)(postSnapshot.child("startDate").getValue());
                    String id = (String)(postSnapshot.child("id").getValue());
                    Staff stf = new Staff(id,name,surname,phone,email,salary,position,start_date);
                    staffs.add(stf);
                }

                adapter = new StaffAdapter(StaffListActivity.this, staffs);
                listStaff.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listStaff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();
            }
        });


    }
}
