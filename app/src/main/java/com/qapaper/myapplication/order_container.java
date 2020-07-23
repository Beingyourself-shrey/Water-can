package com.qapaper.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.graphics.Color.BLACK;

public class order_container extends AppCompatActivity {
DatabaseReference dref;
SharedPreferences spref;
RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_container);
        //Main Container
        final LinearLayout parent=findViewById(R.id.parent);

        TextView heading=findViewById(R.id.type_heading);
        rl=findViewById(R.id.loader);
        rl.setVisibility(View.VISIBLE);
        //Font


        final Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/nunito.ttf");



        //database Reference
        dref= FirebaseDatabase.getInstance().getReference().child("Member");
        //sharePreference
        spref=getApplicationContext().getSharedPreferences("session_user",0);
        String user=spref.getString("username","");


        dref.child(user).child("orders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(int i = (int) (dataSnapshot.getChildrenCount()-1); i>=0; i--){
                    SharePrefDetails sharePrefDetails= dataSnapshot.child(String.valueOf(i)).getValue(SharePrefDetails.class);


                    //child LinearLayout in main Container
                    LinearLayout child = new LinearLayout(getApplicationContext());


                    child.setOrientation(LinearLayout.VERTICAL);
                    //creating textViews
                    TextView name_tv = new TextView(getApplicationContext());
                    TextView quantity_tv = new TextView(getApplicationContext());
                    TextView cantype_tv = new TextView(getApplicationContext());
                    TextView price_tv = new TextView(getApplicationContext());
                    TextView total_tv = new TextView(getApplicationContext());
                    TextView phone_tv = new TextView(getApplicationContext());
                    TextView address_tv = new TextView(getApplicationContext());
                    TextView location_tv = new TextView(getApplicationContext());
                    TextView date_tv = new TextView(getApplicationContext());
                    child.setPaddingRelative(10, 30, 10, 30);
                    //setting background
                    child.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.outline));
                    //setting textsize
                    name_tv.setTextSize(24);
                    quantity_tv.setTextSize(24);
                    price_tv.setTextSize(24);
                    total_tv.setTextSize(24);
                    phone_tv.setTextSize(24);
                    address_tv.setTextSize(24);
                    location_tv.setTextSize(24);
                    cantype_tv.setTextSize(24);
                    date_tv.setTextSize(24);

                    //setting Text
                    name_tv.setText("Username: "+sharePrefDetails.getUsrname());
                    quantity_tv.setText("Total Quantity: "+sharePrefDetails.getQuantity());
                    price_tv.setText("Price: "+sharePrefDetails.getPrice());
                    total_tv.setText("Total Price: "+sharePrefDetails.getTotalPrice());
                    phone_tv.setText("Phone: "+sharePrefDetails.getPhn());
                    address_tv.setText("Address: "+sharePrefDetails.getAddress());
                    location_tv.setText("Location: "+sharePrefDetails.getLocation());
                    cantype_tv.setText("Can Typre: "+sharePrefDetails.getCantype());
                    date_tv.setText("Date: "+sharePrefDetails.getDate());
                    //textcoloring of tv
                    name_tv.setTextColor(BLACK);
                    quantity_tv.setTextColor(BLACK);
                    price_tv.setTextColor(BLACK);
                    total_tv.setTextColor(BLACK);
                    phone_tv.setTextColor(BLACK);
                    address_tv.setTextColor(BLACK);
                    location_tv.setTextColor(BLACK);
                    cantype_tv.setTextColor(BLACK);
                    date_tv.setTextColor(BLACK);

                    //setting font family
                    name_tv.setTypeface(typeface1);
                    quantity_tv.setTypeface(typeface1);
                    price_tv.setTypeface(typeface1);
                    total_tv.setTypeface(typeface1);
                    phone_tv.setTypeface(typeface1);
                    address_tv.setTypeface(typeface1);
                    location_tv.setTypeface(typeface1);
                    cantype_tv.setTypeface(typeface1);
                    date_tv.setTypeface(typeface1);

                    //adding all textView in child
//                    child.addView(name_tv);
                    child.addView(quantity_tv);
                    child.addView(price_tv);
                    child.addView(total_tv);
                    child.addView(phone_tv);
                    child.addView(address_tv);
                    child.addView(location_tv);
                    child.addView(cantype_tv);
                    child.addView(date_tv);
                    //setting Margin1



                    //finally adding child LinearLayout in parent LinearLayout
                    parent.addView(child);


                }
                rl.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void home(View view) {
        Intent i=new Intent(this,Location.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
    public void myorders(View view) {


    }
    public void myinfo(View view) {
        Intent i=new Intent(this,User.class);
        startActivity(i);

    }
}
