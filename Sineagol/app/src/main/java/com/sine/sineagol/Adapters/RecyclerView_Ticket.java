package com.sine.sineagol.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sine.sineagol.Activities.DetailsTicketActivity;
import com.sine.sineagol.R;
import com.sine.sineagol.models.Ticket;

import java.util.List;

public class RecyclerView_Ticket{
    
    private Context context;
    private ticketAdapter adapter;

    
    public void setConfig(RecyclerView recyclerView, Context context, List<Ticket> tickets, List<String> keys){
        
        this.context=context;
        adapter=new ticketAdapter(tickets,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }
    class TicketItemView extends RecyclerView.ViewHolder{
        private TextView tctstname,tcstsrname,tctstemail,tcstphone,tseatno,ttheatreno;
        private String key;

        public TicketItemView(ViewGroup parent){
            super(LayoutInflater.from(context).inflate(R.layout.ticket_list_item,parent,false));
            tctstname= itemView.findViewById(R.id.Ttxtcstname);
            tcstsrname= itemView.findViewById(R.id.Ttxtcstsrname);
            tctstemail= itemView.findViewById(R.id.Ttxtcstemail);
            tcstphone= itemView.findViewById(R.id.Ttxtcstphone);
            tseatno=itemView.findViewById(R.id.Ttxtseatno);
            ttheatreno=itemView.findViewById(R.id.Ttxtheatreno);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent i = new Intent(context, DetailsTicketActivity.class);
                        i.putExtra("key",key);
                        i.putExtra("name", tctstname.getText().toString());
                        i.putExtra("surname",tcstsrname.getText().toString());
                        i.putExtra("email",tctstemail.getText().toString());
                        i.putExtra("phone",tcstphone.getText().toString());
                        i.putExtra("theatre",ttheatreno.getText().toString());
                        i.putExtra("seatno",tseatno.getText().toString());
                        context.startActivity(i);

                }
            });
        }
        public void bind(Ticket ticket,String key){
            tctstname.setText(ticket.getCustomer().getName());
            tcstsrname.setText(ticket.getCustomer().getSurname());
            tctstemail.setText(ticket.getCustomer().getEmail());
            tcstphone.setText(ticket.getCustomer().getPhone());
            tseatno.setText(ticket.getSeat().getCode());
            ttheatreno.setText(ticket.getSeat().getTheatreName());
            this.key = key;
        }

    }
    class ticketAdapter extends RecyclerView.Adapter<TicketItemView>{
        private List<Ticket> mticketList;
        private List<String> keys;

        public ticketAdapter(List<Ticket> mticketList, List<String> keys) {
            this.mticketList = mticketList;
            this.keys = keys;
        }

        @NonNull
        @Override
        public TicketItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TicketItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TicketItemView holder, int position) {
            holder.bind(mticketList.get(position),keys.get(position));
        }

        @Override
        public int getItemCount() {
            return mticketList.size();
        }
    }
}