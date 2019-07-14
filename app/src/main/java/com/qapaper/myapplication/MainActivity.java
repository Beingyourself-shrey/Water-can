package com.qapaper.myapplication;

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
    ListView can_type;
    DatabaseReference myRef;
    SharedPreferences sharedPreferencesobj;
    List<Double> list_array_cantype_price;
    List<String> list_array_cantype;
    MyAdapterForTwoList list_array_cantype_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView =  findViewById(R.id.heading);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        textView.setTypeface(typeface);
      sharedPreferencesobj=getApplicationContext().getSharedPreferences("session_user",0);
      TextView textView1=findViewById(R.id.name);
      textView1.setText(sharedPreferencesobj.getString("username",""));

         can_type=findViewById(R.id.list_can_type);
        list_array_cantype=new ArrayList<String>();
        list_array_cantype_price=new ArrayList<Double>();
        myRef = FirebaseDatabase.getInstance().getReference();
        Log.d("zzz", myRef.child("Can").toString());
        myRef.child("Can").addListenerForSingleValueEvent(new ValueEventListener() {
            //addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(int i=0;i<dataSnapshot.getChildrenCount();i++)
                {
//                    Log.d("jdj", String.valueOf(dataSnapshot.child(String.valueOf(i)).child("cantype").getValue()));
                    Can c = dataSnapshot.child(String.valueOf(i)).getValue(Can.class);
//                    Log.d("jdj", c.cantype + " " + c.price);
                    list_array_cantype.add(c.cantype);
                    list_array_cantype_price.add(c.price);
                    Log.d("dsd",list_array_cantype.get(i)+" "+list_array_cantype_price.get(i)+"");

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        list_array_cantype_adapter= new MyAdapterForTwoList( this,list_array_cantype,list_array_cantype_price);
        can_type.setAdapter(list_array_cantype_adapter);



    }
}
