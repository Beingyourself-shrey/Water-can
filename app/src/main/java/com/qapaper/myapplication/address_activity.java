package com.qapaper.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class address_activity extends AppCompatActivity {
    TextView heading_tv,address_tv;
    String user,address;
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_activity);

        //sharePref getting address
        spref=getApplicationContext().getSharedPreferences("session_user",0);
//        editor=spref.edit();
        user=spref.getString("username","");


        heading_tv=findViewById(R.id.heading);
        address_tv=findViewById(R.id.address);
        //Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        heading_tv.setTypeface(typeface);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        address_tv.setTypeface(tf);

        //Database References

        dref=FirebaseDatabase.getInstance().getReference().child("Member").child(user);

        //SharePreference getting address
        address=spref.getString("address","");
        address=address.toUpperCase();
        address_tv.setText(address);
    }


    public void editActivity(View view) {
        Intent i=new Intent(this,EditAddress.class);
        startActivity(i);
    }

    public void order(View view) {
        String username=spref.getString("username","");
        String phone=spref.getString("phone","");
        String email=spref.getString("email","");
        String type=spref.getString("type","");
        String price=spref.getString("price","");
        String total_price=spref.getString("total_price","");
        String quantity=spref.getString("quantity","");
        String location=spref.getString("location","");

        Date date = new Date();


        //setting all sharepref values to shareprefDetailsClass
        final SharePrefDetails sharePrefDetails=new SharePrefDetails(location,address,type,quantity,total_price,username,phone,email,price,date.toString());




             dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=0;
                if(dataSnapshot.child("orders").exists())
                {
                    count=(int)dataSnapshot.child("orders").getChildrenCount();

                            dref.child("orders").child(String.valueOf(count)).setValue(sharePrefDetails);
                }
                else
                {
                    dref.child("orders").child(String.valueOf(count)).setValue(sharePrefDetails);

                }

                Toast.makeText(getApplicationContext(),"Ordered",Toast.LENGTH_SHORT).show();

                Intent i=new Intent(getApplicationContext(),Location.class);
                startActivity(i);
                finish();


            }

             @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void home(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void myinfo(View view) {
        Intent i=new Intent(this,User.class);
        startActivity(i);

    }
}
