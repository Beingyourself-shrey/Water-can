package com.qapaper.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class OrderConfirmActivity extends AppCompatActivity {
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    DatabaseReference dref;
    String msg;
    RelativeLayout rl;
    TextView location_tv,address_tv,type_tv,quantity_tv,price_tv,total_tv,phone_tv,email_tv;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirm);
        rl=findViewById(R.id.loader);
        spref=getApplicationContext().getSharedPreferences("session_user",0);
//        editor=spref.edit();
        user=spref.getString("username","");
        location_tv=findViewById(R.id.location_heading);
        address_tv=findViewById(R.id.address);
        type_tv=findViewById(R.id.type);
        quantity_tv=findViewById(R.id.quantity);
        price_tv=findViewById(R.id.price);
        total_tv=findViewById(R.id.total);
        phone_tv=findViewById(R.id.phone);
        email_tv=findViewById(R.id.email);
        String address=spref.getString("address","");
        String phone=spref.getString("phone","");
        String email=spref.getString("email","");
        String type=spref.getString("type","");
        String price=spref.getString("price","");
        String total_price=spref.getString("total_price","");
        String quantity=spref.getString("quantity","");
        String location=spref.getString("location","");
        location_tv.setText(String.valueOf(location));
        address_tv.setText(String.valueOf(address));
        type_tv.setText(String.valueOf(type));
        quantity_tv.setText(String.valueOf(quantity));
        price_tv.setText(String.valueOf(price));
        total_tv.setText(String.valueOf("Total Price : "+total_price));
        phone_tv.setText(String.valueOf(phone));
        email_tv.setText(String.valueOf(email));

        //database refs
        dref= FirebaseDatabase.getInstance().getReference().child("Member").child(user);

    }
    public void placeOrder(View view){
        rl.setVisibility(View.VISIBLE);
        String username=spref.getString("username","");
        String phone=spref.getString("phone","");
        String email=spref.getString("email","");
        String type=spref.getString("type","");
        String price=spref.getString("price","");
        String total_price=spref.getString("total_price","");
        String quantity=spref.getString("quantity","");
        String location=spref.getString("location","");
        String address=spref.getString("address","");
        Date date = new Date();
         msg="Username: "+username+"\n Phone: "+phone+"\nEmail: "+email+"\nType: "+type+"\nPrice: "+price+"\nTotal Price: "+total_price+"\nQuantity: "+quantity+"\nLocation: "+location+"\nAddress: "+address+"\nDate: "+date;


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
                rl.setVisibility(View.INVISIBLE);

                Toast.makeText(getApplicationContext(),"Ordered",Toast.LENGTH_SHORT).show();
                editor=spref.edit();
                editor.putString("quantity","1");
                editor.apply();

                //sms api



                try {
                    // Construct data
                    String apiKey = "apikey=" + "MRKFXNEiepA-t9BFAiMVRfXMkrmMXALXuhAH9LQzGF";
                    String message = "&message=" +msg;
                    String sender = "&sender=" + "TXTLCL";
                    String numbers = "&numbers=" + "918871009875";

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                        Log.d("messagesbyshrey", line);
                    }
                    rd.close();


                } catch (Exception e) {
                    Log.d("Error SMS ", e.toString());

                }



                //sms api end

                Intent i=new Intent(getApplicationContext(),Location.class);
                startActivity(i);
                finish();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        StrictMode.ThreadPolicy p= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(p);




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