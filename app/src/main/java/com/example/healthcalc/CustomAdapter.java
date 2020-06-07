package com.example.healthcalc;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context context;
    int layoutId;
    int flags[];
    ArrayList<Danhsach> danhsachdata = new ArrayList<>();


    public CustomAdapter(@NonNull Context context, int layoutId, ArrayList<Danhsach> danhsachdata,int flags[]) {
        super(context, layoutId);
        this.context=context;
        this.layoutId = layoutId;
        this.danhsachdata = danhsachdata;
        this.flags=flags;

    }
    @Override
    public int getCount() {
        return danhsachdata.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent ) {
        LayoutInflater inflater = (LayoutInflater.from(context));
        convertView = inflater.inflate(layoutId, null);

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvDestription = convertView.findViewById(R.id.tvDestription);
        tvTitle.setText(danhsachdata.get(position).getTitle());
        tvDestription.setText(danhsachdata.get(position).getDestription());
        ImageView icon = convertView.findViewById(R.id.tvicon);

        if (danhsachdata.get(position).getTitle().equals("Chỉ số khối cơ thể (BMI)")){

            icon.setImageResource(flags[0]);
        }
        if (danhsachdata.get(position).getTitle().equals("Chỉ số Calo cần thiết")){

            icon.setImageResource(flags[1]);
        }
        if (danhsachdata.get(position).getTitle().equals("Thể tích máu")){

            icon.setImageResource(flags[2]);
        }
        if (danhsachdata.get(position).getTitle().equals("Uống nước")){

            icon.setImageResource(flags[3]);
        }
        if (danhsachdata.get(position).getTitle().equals("Trọng lượng lý tưởng")){

            icon.setImageResource(flags[4]);
        }

        if (danhsachdata.get(position).getTitle().equals("Thông báo !")){

            icon.setImageResource(flags[5]);
        }




        return  convertView;
    }
}

