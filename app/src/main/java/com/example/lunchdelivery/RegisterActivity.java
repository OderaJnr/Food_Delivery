package com.example.lunchdelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button Registerbtn;
    private TextView customerloginlink;
    private EditText NameCustomer,Emailcustomer,Phonenumbercustomer,Passwordcustomer;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        progressDialog=new ProgressDialog(this);
        mAuth= FirebaseAuth.getInstance();

         currentUser = mAuth.getCurrentUser();





        //User Inputs/edittexts
                NameCustomer = findViewById(R.id.username);
                Emailcustomer= findViewById(R.id.email);
                Phonenumbercustomer= findViewById(R.id.phonenumber);
                Passwordcustomer= findViewById(R.id.password);


        Registerbtn = findViewById(R.id.registerbuttton);
        customerloginlink = findViewById(R.id.loginlink);


        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=Emailcustomer.getText().toString();
                String password=Passwordcustomer.getText().toString();
                String phonenumber=Phonenumbercustomer.getText().toString();
                String name=NameCustomer.getText().toString();


                RegisterCustomer(email,name,password,phonenumber);


            }
        });


        customerloginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,UserLoginActivity.class);
                startActivity(intent);

            }
        });



    }

    private void RegisterCustomer(final String email, final String name, String password, final String phonenumber)
    {

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(RegisterActivity.this,"Please enter your email",Toast.LENGTH_SHORT).show();
        }

         else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(RegisterActivity.this,"Please enter your password",Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(name))
        {
            Toast.makeText(RegisterActivity.this,"Please enter your full names",Toast.LENGTH_SHORT).show();
        }


        else if (TextUtils.isEmpty(phonenumber))
        {
            Toast.makeText(RegisterActivity.this,"Please enter your phone number",Toast.LENGTH_SHORT).show();
        }


         else
        {
            progressDialog.setTitle("REGISTERING NEW USER");
            progressDialog.setMessage("please wait..");
            progressDialog.show();


            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {


                    if (task.isSuccessful())

                    {
                        DatabaseReference ref =  FirebaseDatabase.getInstance().getReference().child("Clients");

                        HashMap<String,Object> userMap = new HashMap<>();
                        userMap.put("name",name);
                        userMap.put("phone",phonenumber);
                        userMap.put("email",email);
                        ref.child(currentUser.getUid()).child("Details").updateChildren(userMap);




                        Toast.makeText(RegisterActivity.this,"Registration Successful ",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
                        startActivity(intent);

                    }

                    else
                    {
                        Toast.makeText(RegisterActivity.this,"Registration unsuccessful please check your details and try again ",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
            });
        }

    }


}
