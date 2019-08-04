package com.qapaper.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class signup extends AppCompatActivity {

    EditText userC, passC,conpassC,emailC,phoneC,addressC;
    String user,pass,conpass,email,phone,address;
    Member member;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userC=findViewById(R.id.user);
        passC=findViewById(R.id.pass);
        conpassC=findViewById(R.id.conpass);
        emailC=findViewById(R.id.email);
        phoneC=findViewById(R.id.phone);
        addressC=findViewById(R.id.address);
        myRef = FirebaseDatabase.getInstance().getReference().child("Member");
    }
    protected void validateSignup(View v)
    {

        user= String.valueOf(userC.getText()).trim();
        pass= String.valueOf(passC.getText()).trim();
        conpass= String.valueOf(conpassC.getText()).trim();
        email= String.valueOf(emailC.getText()).trim();
        phone= String.valueOf(phoneC.getText()).trim();
        address= String.valueOf(addressC.getText()).trim();
        member=new Member();



        if(user.equals("")||pass.equals("")||conpass.equals("")||email.equals("")||phone.equals("")||address.equals(""))
            Toast.makeText(this,"Please Fill All Fields",Toast.LENGTH_SHORT).show();
        else if(pass.length()<8)
            Toast.makeText(this,"Password Length Should be 8 or more",Toast.LENGTH_SHORT).show();
        else if(phone.length()<10||phone.length()>10)
            Toast.makeText(this,"Please Enter Correct Phone Number",Toast.LENGTH_SHORT).show();

        else if(!pass.equals(conpass))
            Toast.makeText(this,"Password didn't match!",Toast.LENGTH_SHORT).show();
        else
        {


            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     int error = 1;
                      if (!dataSnapshot.child(user).exists()) {
                          member.setUsername(user);
                          member.setPass(pass);
                          member.setEmail(email);
                          member.setPhone(phone);
                          member.setAddress(address);

                          myRef.child(user).setValue(member);
                          Toast.makeText(getApplicationContext(),"Done! Signup",Toast.LENGTH_LONG).show();
                          Intent ii=new Intent(getApplicationContext(),Login.class);
                          startActivity(ii);
                          finish();

                       }
                      else{
                          Toast.makeText(getApplicationContext(),"Username Already exist",Toast.LENGTH_SHORT).show();
                      }
                      }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                             });



        }


            }
}
