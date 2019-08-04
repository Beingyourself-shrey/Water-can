package com.qapaper.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    DatabaseReference myRef;
    SharedPreferences sharedPreferencesobj;
    SharedPreferences.Editor editor;
    TextView normal_tv;
    TextView chilled_tv;
    String price_of_location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView heading_tv =  findViewById(R.id.heading);
        normal_tv=findViewById(R.id.normal_price);
        chilled_tv=findViewById(R.id.chilled_price);

        //font added
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        heading_tv.setTypeface(typeface);
        //font end

       //Shared Preferences name session_user
        sharedPreferencesobj=getApplicationContext().getSharedPreferences("session_user",0);
         price_of_location=sharedPreferencesobj.getString("price","");
        normal_tv.setText(price_of_location);
        chilled_tv.setText(String.valueOf(Double.parseDouble(price_of_location)+15));
         editor=sharedPreferencesobj.edit();


    }
    public void chilled(View view)
    {
       editor.putString("type","chilled");
        editor.putString("price",String.valueOf(Double.parseDouble(price_of_location)+15));
       editor.apply();
       Intent i = new Intent(this,Generation.class);
       startActivity(i);
    }
    public void normal(View v)
    {
        editor.putString("type","normal");
        editor.putString("price",price_of_location);
        editor.apply();
        Intent i = new Intent(this,Generation.class);
        startActivity(i);
    }

    public void myinfo(View view) {
        Intent i=new Intent(this,User.class);
        startActivity(i);

    }
    public void myorders(View view) {

        Intent i =new Intent(getApplicationContext(),order_container.class);
        startActivity(i);
    }
    public void home(View view) {
        Intent i=new Intent(this,Location.class);
        startActivity(i);
    }
}
