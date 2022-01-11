package com.sine.sineagol;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Seat;
import com.sine.sineagol.models.Staff;
import com.sine.sineagol.models.Theatre;
import com.sine.sineagol.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper{


    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Movie> movieList = new ArrayList<>();
    private List<Theatre> theatreList = new ArrayList<>();
    private List<Ticket> ticketList =new ArrayList<>();

    public interface DataStatus{
        void movieIsLoaded(List<Movie> movies,List<String> keys);
        void ticketIsLoaded(List<Ticket> tickets,List<String> keys);
        void dataIsInserted();
        void dataUpdated();
        void dataDeleted();
        void theatreIsLoaded(List<Theatre> theatres);
    }

    public FirebaseDatabaseHelper(String tableName){
        mDatabase=FirebaseDatabase.getInstance();
        mReference=mDatabase.getReference(tableName);

    }
    public void readMovies(final DataStatus dataStatus){
             mReference.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     movieList.clear();
                     List<String> keys = new ArrayList<String>();
                     for(DataSnapshot keyNode:dataSnapshot.getChildren()){
                         keys.add(keyNode.getKey());
                         Movie movie = keyNode.getValue(Movie.class);
                         movieList.add(movie);
                     }
                     dataStatus.movieIsLoaded(movieList,keys);
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             });
    }
    public void updateMovies(String key,Movie movie,final DataStatus dataStatus){
        movie.setId(key);
        mReference.child(key).setValue(movie).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataUpdated();
            }
        });
    }
    public void addTicket(Ticket ticket, final DataStatus dataStatus){
        String key = mReference.push().getKey();
        ticket.setId(key);
        mReference.child(key).setValue(ticket).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsInserted();
            }
        });
    }
    public void updateTicket(String key,Ticket ticket,final DataStatus dataStatus){

        mReference.child(key).setValue(ticket).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsInserted();
            }
        });

    }
    public void addStaff(Staff staff, final DataStatus dataStatus){
        String key = mReference.push().getKey();
        staff.setId(key);
        mReference.child(key).setValue(staff).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsInserted();
            }
        });

    }
    public void readTicket(final DataStatus dataStatus){

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ticketList.clear();
                List<String> keys = new ArrayList<String>();
                for(DataSnapshot keyNode:dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Ticket ticket = keyNode.getValue(Ticket.class);
                    ticketList.add(ticket);
                }
                dataStatus.ticketIsLoaded(ticketList,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteTicket(String key,final DataStatus dataStatus){
           if(key!=null){
               mReference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       dataStatus.dataDeleted();
                   }
               });
           }
           else return;
    }

    public void addTheatre(Theatre theatre,final DataStatus dataStatus){
        String key = mReference.push().getKey();
        theatre.setId(key);
        mReference.child(key).setValue(theatre).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsInserted();
            }
        });

    }

    public void updateTheatreSeat(String key, List<Seat> seats){
        mReference.child(key).child("seats").setValue(seats);
    }


    public void readTheatre(final DataStatus dataStatus){

        mReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                theatreList.clear();
                for(DataSnapshot keyNode:dataSnapshot.getChildren()){

                    Theatre theatre = keyNode.getValue(Theatre.class);
                    theatre.setId(keyNode.getKey());
                    theatreList.add(theatre);
                }
                dataStatus.theatreIsLoaded(theatreList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}
