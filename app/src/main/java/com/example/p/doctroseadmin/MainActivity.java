package com.example.p.doctroseadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText editTextenteremail,editTextenterpassword;
    Button buttonsignin,buttonmedicine;
    TextView textViewsignup;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    String email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextenteremail = (EditText) findViewById(R.id.email);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            Intent intentregistered = new Intent(getApplicationContext(),PatientActivity.class);
            startActivity(intentregistered);
        }
        editTextenterpassword = (EditText) findViewById(R.id.password);
        buttonsignin = (Button) findViewById(R.id.loginnow);
        buttonmedicine = (Button) findViewById(R.id.uploadmedicine);
        progressDialog = new ProgressDialog(this);
        // textViewsignup = (TextView) findViewById(R.id.Signup);
        buttonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //email = editTextenteremail.getText().toString().trim();
                userLogin();
            }
        });
        buttonmedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UploadMedicine.class);
                startActivity(intent);
            }
        });
    }

    private void userLogin() {
        email = editTextenteremail.getText().toString().trim();
        String password = editTextenterpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Signing in.....");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            Bundle bundle=new Bundle();
                            bundle.putString("emailid",email);
                            Intent intentregistered = new Intent(getApplicationContext(),PatientActivity.class);
                            intentregistered.putExtras(bundle);
                            startActivity(intentregistered);
                        }
                    }
                });
    }
}
