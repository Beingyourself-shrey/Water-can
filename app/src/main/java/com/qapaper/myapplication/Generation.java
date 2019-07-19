package com.qapaper.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Generation extends AppCompatActivity {
    SharedPreferences sharedPreferencesobj;
    SharedPreferences.Editor shareeditor;
    TextView quantity_tv;
    TextView can_price;
    Double price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generation);
        TextView heading=findViewById(R.id.type_heading);
        can_price=findViewById(R.id.price);
        quantity_tv=findViewById(R.id.quantity);
        //Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        heading.setTypeface(typeface);

        //SharePreference
        sharedPreferencesobj=getApplicationContext().getSharedPreferences("session_user",0);

        heading.setText(sharedPreferencesobj.getString("type","").toUpperCase());
        price=Double.parseDouble(sharedPreferencesobj.getString("price",null));
        can_price.setText(String.valueOf(price));

    }
    public void myinfo(View view) {
        Intent i=new Intent(this,User.class);
        startActivity(i);

    }
    public void reduce(View v)
    {
        if(Integer.parseInt(String.valueOf(quantity_tv.getText()))!=1)
        {
            quantity_tv.setText(String.valueOf(Integer.parseInt(String.valueOf(quantity_tv.getText()))-1));
            double i=price*((Integer.parseInt(String.valueOf(quantity_tv.getText()))));
            can_price.setText(String.valueOf(i));
        }

    }
    public void increase(View v)
    {
        if(Integer.parseInt(String.valueOf(quantity_tv.getText()))!=9)
        {
            quantity_tv.setText(String.valueOf(Integer.parseInt(String.valueOf(quantity_tv.getText()))+1));
            double i=price*Integer.parseInt(String.valueOf(quantity_tv.getText()));
            can_price.setText(String.valueOf(i));
        }
    }
}
