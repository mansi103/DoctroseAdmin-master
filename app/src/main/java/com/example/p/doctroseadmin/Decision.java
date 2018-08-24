package com.example.p.doctroseadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Decision extends AppCompatActivity {
    Button accept,reject,send;
    EditText editTextmsg;
    String s="";
    DatabaseReference databaseReference;
    String doctor="";
    String emailpatient = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);
        accept=(Button)findViewById(R.id.buttonaccept);
        reject = (Button) findViewById(R.id.buttonreject);
        send = (Button) findViewById(R.id.buttonsend);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextmsg = (EditText) findViewById(R.id.editmessage);
        Intent intent1=getIntent();
        Bundle bundle=intent1.getExtras();
        doctor = bundle.getString("doctorsname");
        s=bundle.getString("emailid");
        emailpatient = bundle.getString("emailidpatient");
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailpatient});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Appointment");
                intent.putExtra(Intent.EXTRA_TEXT, "NAME:jdgnkfjagwdkfreng");
                try{
                    startActivity(Intent.createChooser(intent, "Send mail"));
                } catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(), "There are no email clients intsalled", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(),emailpatient,Toast.LENGTH_LONG).show();
                //        databaseReference = FirebaseDatabase.getInstance().getReference().child("Patient").child(doctor).child(emailpatient);
                //      databaseReference.removeValue();
             //   databaseReference.child("Patient").child(doctor).child(emailpatient).removeValue();
               // Toast.makeText(Decision.this,"Deleted",Toast.LENGTH_LONG).show();
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailpatient});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Appointment");
                intent.putExtra(Intent.EXTRA_TEXT, "NAME:jdgnkfjagwdkfreng");
                try{
                    startActivity(Intent.createChooser(intent, "Send mail"));
                } catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(), "There are no email clients intsalled", Toast.LENGTH_LONG).show();
                }
                //  Toast.makeText(getApplicationContext(),emailpatient,Toast.LENGTH_LONG).show();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editTextmsg.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL,new String [] {emailpatient});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Appointment");
                intent.putExtra(Intent.EXTRA_TEXT,msg);
                try {
                    startActivity(Intent.createChooser(intent, "Send mail"));
                }catch (android.content.ActivityNotFoundException e){
                    Toast.makeText(getApplicationContext(),"There is no email client installed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
