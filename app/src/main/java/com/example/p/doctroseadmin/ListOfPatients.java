package com.example.p.doctroseadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfPatients extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    private String doctor,details,emailpatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final Bundle bundle1 = intent.getExtras();
        doctor = bundle1.getString("doctorsname");
        setContentView(R.layout.activity_list_of_patients);
        listView = (ListView) findViewById(R.id.listview);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList arrayList = new ArrayList();
                Log.d("dataSnapshot", dataSnapshot.toString());
                for(DataSnapshot k: dataSnapshot.child("Patient").child(doctor).getChildren()){
                    arrayList.add(k.child("name").getValue()+"\n"+k.child("address").getValue()+"\n"+k.child("email").getValue());
                    ArrayAdapter adapter  = new ArrayAdapter(ListOfPatients.this,android.R.layout.simple_list_item_1,arrayList);
                    listView.setAdapter(adapter);
                }
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        details = parent.getItemAtPosition(position).toString();
                        String k[] = details.split("\n");
                        emailpatient = k[2];
                        Toast.makeText(getApplicationContext(),emailpatient,Toast.LENGTH_LONG).show();
                        Bundle bundle=new Bundle();
                        bundle.putString("emailid",bundle1.getString("emailid"));
                        bundle.putString("emailidpatient",emailpatient);
                        bundle.putString("doctorsname",doctor);
                        Intent intent = new Intent(ListOfPatients.this,Decision.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ListOfPatients.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
