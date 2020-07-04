package com.qapaper.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditAddress extends AppCompatActivity {
 TextView heading_tv;
 EditText address_et;
 SharedPreferences spref;
 SharedPreferences.Editor editor;
    DatabaseReference dref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);


        heading_tv=findViewById(R.id.heading);
        address_et=findViewById(R.id.address);
        //Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");


        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        address_et.setTypeface(tf);
        heading_tv.setTypeface(tf);

        //sharePref getting address
        spref=getApplicationContext().getSharedPreferences("session_user",0);
        editor=spref.edit();

    }

    public void Save(View view) {
        if(address_et.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(),"Fill the address",Toast.LENGTH_SHORT).show();
        }
        else{

            editor.putString("address",address_et.getText().toString().trim());
            editor.apply();
            String user=spref.getString("username","");
            dref= FirebaseDatabase.getInstance().getReference().child("Member");
            dref.child(user).child("address").setValue(address_et.getText().toString().trim());
            Intent i=new Intent(this,Generation.class);
            startActivity(i);
            this.finish();

        }

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
