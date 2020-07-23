package com.qapaper.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class User extends AppCompatActivity {
SharedPreferences sharedPreferencesobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        sharedPreferencesobj=getApplicationContext().getSharedPreferences("session_user",0);

        TextView nametv=findViewById(R.id.name);
        TextView emailtv=findViewById(R.id.email);
        TextView phonetv=findViewById(R.id.Phone);
        nametv.setText(sharedPreferencesobj.getString("username",""));
        phonetv.setText(sharedPreferencesobj.getString("phone",""));
        emailtv.setText(sharedPreferencesobj.getString("email",""));



    }
    public void logout(View view)
    {
        SharedPreferences.Editor editor=sharedPreferencesobj.edit();
        editor.clear();
        editor.apply();

        Intent i=new Intent(this,Login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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


    }
}
