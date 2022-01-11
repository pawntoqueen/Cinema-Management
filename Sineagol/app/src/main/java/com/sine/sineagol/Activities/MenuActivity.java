package com.sine.sineagol.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sine.sineagol.R;

public class MenuActivity extends AppCompatActivity {


    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mainGrid = findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
        final CardView cardView = (CardView) mainGrid.getChildAt(0);
        registerForContextMenu(cardView);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MenuActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MenuActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {

        final FirebaseAuth mAuth=FirebaseAuth.getInstance();

        FirebaseUser user =mAuth.getCurrentUser();
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI==0){
                        Toast.makeText(MenuActivity.this,"Long click for add new staff",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MenuActivity.this, StaffListActivity.class));                    }
                    else if(finalI==1){
                        Intent intent=new Intent(MenuActivity.this, MovieActivity.class);
                        intent.putExtra("flag",true);
                        startActivity(intent);
                    }
                    else if(finalI==2){
                        Intent i = new Intent(MenuActivity.this, TicketActivity.class);
                        startActivity(i);
                    }
                    else if(finalI==3){
                        Toast.makeText(MenuActivity.this,"Logged Out",Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        Intent i = new Intent(MenuActivity.this, MovieActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(MenuActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.add_staff,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.addStaffButton){
            startActivity(new Intent(MenuActivity.this, StaffAddActivity.class));
            return true;
        }
        else
            return super.onContextItemSelected(item);
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