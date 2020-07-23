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
import android.widget.RelativeLayout;
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
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_activity);

        //sharePref getting address
        spref=getApplicationContext().getSharedPreferences("session_user",0);
//        editor=spref.edit();
        user=spref.getString("username","");
        rl=findViewById(R.id.loader);


        heading_tv=findViewById(R.id.heading);
        address_tv=findViewById(R.id.address);


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
       Intent i=new Intent(getApplicationContext(),OrderConfirmActivity.class);
       startActivity(i);

    }

    public void home(View view) {
        Intent i=new Intent(this,Location.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
