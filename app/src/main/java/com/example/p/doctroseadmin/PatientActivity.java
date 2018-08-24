package com.example.p.doctroseadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class PatientActivity extends AppCompatActivity {

    EditText doctorsname;
    private FirebaseAuth firebaseAuth;
    Button buttonsubmit,signout,buttonrate;
    String doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Intent intent1=getIntent();
        final Bundle bundle1=intent1.getExtras();
        buttonsubmit = (Button) findViewById(R.id.button);
        firebaseAuth  = FirebaseAuth.getInstance();
        buttonrate = (Button) findViewById(R.id.rate);
       // buttonupload = (Button) findViewById(R.id.uploadmedicine);
        doctorsname = (EditText) findViewById(R.id.doctorsname);
        signout = (Button) findViewById(R.id.signout);
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            Intent intent = new Intent(PatientActivity.this,MainActivity.class);
            startActivity(intent);
        }
        // doctor = doctorsname.getText().toString();
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctor = doctorsname.getText().toString();
                Toast.makeText(getApplicationContext(),doctor,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PatientActivity.this,ListOfPatients.class);
                Bundle bundle = new Bundle();
                bundle.putString("doctorsname",doctor);
                bundle.putString("emailid",bundle1.getString("emailid"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        buttonrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this,RateActivity.class);
                startActivity(intent);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent intent = new Intent(PatientActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
