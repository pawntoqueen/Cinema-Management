package com.sine.sineagol.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sine.sineagol.Activities.BookingActivity;
import com.sine.sineagol.Activities.DetailsMovieActivity;
import com.sine.sineagol.R;
import com.sine.sineagol.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerView_Movie {
    private Context context;
    private movieAdapter adapter;
    FirebaseAuth mAuth;
    private static FirebaseUser user;
    public void setConfig(RecyclerView recyclerView,Context context,List<Movie> movies,List<String> keys){

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        this.context=context;
        adapter=new movieAdapter(movies,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }
    class MovieItemView extends RecyclerView.ViewHolder{
        private TextView mname;
        private TextView mdescrip;
        private TextView mcategory;
        private TextView mpoint;
        private ImageView imageView;
        private String key;
        private String url="";


        public MovieItemView(ViewGroup parent){
            super(LayoutInflater.from(context).inflate(R.layout.movie_list_item,parent,false));
            mname= itemView.findViewById(R.id.txtmoviename);
            mcategory= itemView.findViewById(R.id.txtcategory);
            mpoint= itemView.findViewById(R.id.txtpoint);
            mdescrip= itemView.findViewById(R.id.txtdescrip);
            imageView= itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        if(user!=null){
                            Intent i = new Intent(context, DetailsMovieActivity.class);
                            i.putExtra("key",key);
                            i.putExtra("name",mname.getText().toString());
                            i.putExtra("category",mcategory.getText().toString());
                            i.putExtra("point",mpoint.getText().toString());
                            i.putExtra("description",mdescrip.getText().toString());
                            i.putExtra("url",url);
                            context.startActivity(i);
                        }
                        else{
                            Intent i = new Intent(context, BookingActivity.class);
                            i.putExtra("key",key);
                            context.startActivity(i);
                        }

                }
            });
        }
        public void bind(Movie movie,String key,String url){
            mname.setText(movie.getName());
            mcategory.setText(movie.getCategory());
            mpoint.setText(String.valueOf(movie.getScore()));
            mdescrip.setText(movie.getDescription());
            if(!movie.getURLPic().equals("")){
                Picasso.get().load(movie.getURLPic()).into(imageView);
                url=movie.getURLPic();
            }
            this.key = key;
            this.url=url;
        }

    }
    class movieAdapter extends RecyclerView.Adapter<MovieItemView>{
        private List<Movie> mMovieList;
        private List<String> keys;

        public movieAdapter(List<Movie> mMovieList, List<String> keys) {
            this.mMovieList = mMovieList;
            this.keys = keys;
        }

        @NonNull
        @Override
        public MovieItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MovieItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieItemView holder, int position) {
            String a="";
            holder.bind(mMovieList.get(position),keys.get(position),a);
        }

        @Override
        public int getItemCount() {
            return mMovieList.size();
        }
    }
}
