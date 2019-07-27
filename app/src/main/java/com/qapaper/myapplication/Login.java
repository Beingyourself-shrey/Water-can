package com.qapaper.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    DatabaseReference myRef;
    EditText userC;
    EditText passC;
    String user,pass;
    SharedPreferences sharedPreferencesobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferencesobj=getApplicationContext().getSharedPreferences("session_user",0);
        if(sharedPreferencesobj.contains("username"))
        {
            Intent i = new Intent(getApplicationContext(), Location.class);
            startActivity(i);
            finish();
        }
    }
    public void loginValidate(View v)
    {

        userC=findViewById(R.id.user);
        passC=findViewById(R.id.pass);
        user=userC.getText().toString().trim();
        pass=passC.getText().toString().trim();
        if(user.equals("")||pass.equals(""))
            Toast.makeText(getApplicationContext(), "Please Fill All Field!", Toast.LENGTH_SHORT).show();
        else {

            myRef = FirebaseDatabase.getInstance().getReference().child("Member");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int error = 0;
                    if (!dataSnapshot.child(user).exists()) {
                        error = 1;
                    } else {


                        String userF = dataSnapshot.child(user).child("username").getValue().toString();
                        String passF = dataSnapshot.child(user).child("pass").getValue().toString();
                        String emailF = dataSnapshot.child(user).child("email").getValue().toString();
                        String phoneF = dataSnapshot.child(user).child("phone").getValue().toString();
                        String addressF = dataSnapshot.child(user).child("address").getValue().toString();

                        if (user.equals(userF) && pass.equals(passF)) {
                            Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_SHORT).show();

                            //Storing values in SharedPreferences
                            SharedPreferences.Editor user_editor=sharedPreferencesobj.edit();
                            user_editor.putString("username",userF);
                            user_editor.putString("password",passF);
                            user_editor.putString("email",emailF);
                            user_editor.putString("phone",phoneF);
                            user_editor.putString("address",addressF);

                            user_editor.apply();

                            Intent i = new Intent(getApplicationContext(), Location.class);
                            startActivity(i);
                            finish();
                        } else {
                            error = 1;

                        }
                    }
                    if (error == 1)
                        Toast.makeText(getApplicationContext(), "Wrong Credentials!", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
    public void signUp(View v){
        Intent i=new Intent(getApplicationContext(),signup.class);
        startActivity(i);
    }
}
