package com.example.p.doctroseadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RateActivity extends AppCompatActivity {
    RatingBar ratingBar;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    Button buttonsubmit;
    String A;
    public double previousrate=0.0,rating=0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        buttonsubmit = (Button) findViewById(R.id.submit);
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rating = ratingBar.getRating() ;
                Toast.makeText(RateActivity.this,""+rating,Toast.LENGTH_LONG).show();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        Log.d("dataSnapshot", dataSnapshot.toString());
                        for (DataSnapshot k : dataSnapshot.child("Doctor").getChildren()) {
                            try {
                                A = k.child("rate").getValue().toString();
                                previousrate = Double.parseDouble(A);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(RateActivity.this, databaseError.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                saverate();
            }
        });

    }

    private void saverate() {
        int rateingstars=(int)((rating+previousrate)/2.0);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference.child("Doctor").child(firebaseUser.getUid()).child("rate").setValue(rateingstars);
        Toast.makeText(RateActivity.this,"RATING SAVED!!!!",Toast.LENGTH_LONG).show();
        finish();

    }
}
