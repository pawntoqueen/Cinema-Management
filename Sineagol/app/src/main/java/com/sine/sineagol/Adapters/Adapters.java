package com.sine.sineagol.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sine.sineagol.R;
import com.sine.sineagol.models.Staff;

import java.util.ArrayList;

public class Adapters extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public static class StaffAdapter extends BaseAdapter {

        Context context ;
        ArrayList<Staff> kisilerim;

        public StaffAdapter(Context context, ArrayList<Staff> kisilerim) {
            this.context = context;
            this.kisilerim = kisilerim;
        }

        @Override
        public int getCount() {
            return kisilerim.size();
        }

        @Override
        public Object getItem(int position) {
            return kisilerim.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(context, R.layout.staff_list_row,null) ;
            TextView name_surname = v.findViewById(R.id.name_surname);
            name_surname.setText(kisilerim.get(position).getName());

            TextView phone_num = v.findViewById(R.id.phone_num);
            phone_num.setText(kisilerim.get(position).getPhone());

            TextView duty = v.findViewById(R.id.position);
            duty.setText(kisilerim.get(position).getPosition());

            TextView staff_id = v.findViewById(R.id.staff_id);
            staff_id.setText(kisilerim.get(position).getId());

            return v;
        }
    }
}