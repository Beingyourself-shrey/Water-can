package com.qapaper.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;
public class Location extends AppCompatActivity  {
    Spinner loc_spinner;
    List <String> loc_list;
    List <Double> loc_price;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        TextView textView =  findViewById(R.id.heading_loc);
        //font added
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        textView.setTypeface(typeface);

        pref=getApplicationContext().getSharedPreferences("session_user",0);
        editor=pref.edit();
        loc_spinner=findViewById(R.id.loc_spinner);
        loc_list=new ArrayList<String>();
        loc_price=new ArrayList<Double>();
        //Adding Default value for location name and price
        loc_list.add("Select One!!");
        loc_price.add(0.00);
        DatabaseReference myref= FirebaseDatabase.getInstance().getReference().child("location");
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(int i=0;i<dataSnapshot.getChildrenCount();i++)
                {
                    LocationDetails locationDetails=dataSnapshot.child(String.valueOf(i)).getValue(LocationDetails.class);

                     String name=locationDetails.getName();
                     Double price=locationDetails.getPrice();
                    loc_list.add(name);
                    loc_price.add(price);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Adapter adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,loc_list);
        loc_spinner.setPrompt("Select your favorite Planet!");
        loc_spinner.setAdapter((SpinnerAdapter) adapter);


    }

    public void loc_submit(View view) {
            String location=loc_spinner.getItemAtPosition(loc_spinner.getSelectedItemPosition()).toString();
            if(loc_spinner.getSelectedItemPosition()!=0) {
                Double price = loc_price.get(loc_spinner.getSelectedItemPosition());

                //Log.d("shs",location+""+String.valueOf(price));
                editor.putString("location", location);
                editor.putString("price", String.valueOf(price));
                editor.apply();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }


    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this,"kv" , Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
