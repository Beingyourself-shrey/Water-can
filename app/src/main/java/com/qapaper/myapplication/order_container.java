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
import android.view.LayoutInflater;
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

import java.util.zip.Inflater;

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

                    LayoutInflater inflater = getLayoutInflater();
                    View child = inflater.inflate(R.layout.activity_order_inflator,
                            (ViewGroup) findViewById(R.id.order_model),false);


                    TextView quantity_tv =child.findViewById(R.id.quantity);
                    TextView cantype_tv = child.findViewById(R.id.cantype);
                    TextView price_tv = child.findViewById(R.id.price);
                    TextView total_tv = child.findViewById(R.id.total);
                    TextView phone_tv = child.findViewById(R.id.phone);
                    TextView address_tv = child.findViewById(R.id.address);
                    TextView location_tv = child.findViewById(R.id.location);
                    TextView date_tv = child.findViewById(R.id.date);
                    //setting text
                    quantity_tv.setText("Total Quantity: "+sharePrefDetails.getQuantity());
                    price_tv.setText("Price: "+sharePrefDetails.getPrice());
                    total_tv.setText("Total Price: "+sharePrefDetails.getTotalPrice());
                    phone_tv.setText("Phone: "+sharePrefDetails.getPhn());
                    address_tv.setText("Address: "+sharePrefDetails.getAddress());
                    location_tv.setText("Location: "+sharePrefDetails.getLocation());
                    cantype_tv.setText("Can Typre: "+sharePrefDetails.getCantype());
                    date_tv.setText("Date: "+sharePrefDetails.getDate());




                    //finally adding child LinearLayout in parent LinearLayout
//                            if(child.getParent() != null) {
//                                ((ViewGroup)child.getParent()).removeView(child); // <- fix
//                            }

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
