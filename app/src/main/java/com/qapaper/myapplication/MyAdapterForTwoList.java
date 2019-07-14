package com.qapaper.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterForTwoList extends ArrayAdapter{
    Activity context;
    List<String> can_type;
    List<Double> price;

    public MyAdapterForTwoList(Activity context,List<String> can_type,List<Double> price)
    {
        super(context, R.layout.my_list_can, can_type);
        this.context=context;
        this.can_type=can_type;
        this.price=price;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View rowView=inflater.inflate(R.layout.my_list_can,null,true);
        TextView can_type_textView=rowView.findViewById(R.id.name);
        TextView can_price_textView=rowView.findViewById(R.id.price);
        can_type_textView.setText(can_type.get(position));
        can_price_textView.setText(String.valueOf(price.get(position)));


        return rowView;
    }


}
