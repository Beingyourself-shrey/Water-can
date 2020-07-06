package com.qapaper.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Generation extends AppCompatActivity {
    SharedPreferences sharedPreferencesobj;
    SharedPreferences.Editor shareeditor;
    TextView quantity_tv;
    TextView can_price_tv;
    TextView total_price_tv;

    Double price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generation);
        TextView heading=findViewById(R.id.type_heading);
        can_price_tv=findViewById(R.id.price);
        quantity_tv=findViewById(R.id.quantity);

        //Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        heading.setTypeface(typeface);

        //SharePreference
        sharedPreferencesobj=getApplicationContext().getSharedPreferences("session_user",0);
        shareeditor=sharedPreferencesobj.edit();
        String qty=sharedPreferencesobj.getString("quantity",null);
        heading.setText(sharedPreferencesobj.getString("type","").toUpperCase());
        price=Double.parseDouble(sharedPreferencesobj.getString("price",null));
        can_price_tv.setText(String.valueOf(price));
        if(qty!=null)
        quantity_tv.setText(qty);




        //total_price_tv txt_view
        total_price_tv=findViewById(R.id.total);
        total_price_tv.setText(String.valueOf(price));


    }

    public void reduce(View v)
    {
        if(Integer.parseInt(String.valueOf(quantity_tv.getText()))!=1)
        {
            quantity_tv.setText(String.valueOf(Integer.parseInt(String.valueOf(quantity_tv.getText()))-1));
            double calc=price*((Integer.parseInt(String.valueOf(quantity_tv.getText()))));
            total_price_tv.setText(String.valueOf(calc));
            
        }

    }
    public void increase(View v)
    {
        if(Integer.parseInt(String.valueOf(quantity_tv.getText()))!=9)
        {
            quantity_tv.setText(String.valueOf(Integer.parseInt(String.valueOf(quantity_tv.getText()))+1));
            double calc=price*Integer.parseInt(String.valueOf(quantity_tv.getText()));
            total_price_tv.setText(String.valueOf(calc));
        }
    }

    public void order_address(View view) {
        shareeditor.putString("total_price",String.valueOf(total_price_tv.getText()));
        shareeditor.putString("quantity",String.valueOf(quantity_tv.getText()));
        shareeditor.apply();
        Intent intent=new Intent(this,address_activity.class);
        startActivity(intent);

    }

    public void home(View view) {
        Intent i=new Intent(this,Location.class);
        startActivity(i);
    }
    public void myorders(View view) {

        Intent i =new Intent(getApplicationContext(),order_container.class);
        startActivity(i);
    }
    public void myinfo(View view) {
        Intent i=new Intent(this,User.class);
        startActivity(i);

    }


}
