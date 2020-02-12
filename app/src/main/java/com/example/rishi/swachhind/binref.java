package com.example.rishi.swachhind;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class binref extends AppCompatActivity {
    Button binloc,home1;
    DatabaseReference ref1,ref2;
    String value1,value2;
    TextView binvalue,binleft;
    FirebaseDatabase database=FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binref);
        binloc=findViewById(R.id.binloc);
        home1=findViewById(R.id.home1);
        binvalue=findViewById(R.id.binvalue);
        binleft=findViewById(R.id.binleft);
        home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home1=new Intent(binref.this,mainref.class);
                startActivity(home1);
            }
        });
        binloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent binlo=new Intent(Intent.ACTION_VIEW);
                binlo.setData(Uri.parse("https://goo.gl/maps/dk4sKzxzjg46LEvBA"));
                startActivity(binlo);
            }
        });
        ref1=database.getReference("bin");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value1=dataSnapshot.getValue(String.class);
                binvalue.setText(value1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref2=database.getReference("binleft");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value2=dataSnapshot.getValue(String.class);
                binleft.setText("About "+value2+" left to fill");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
