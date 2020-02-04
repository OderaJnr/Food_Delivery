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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLoginActivity extends AppCompatActivity {

    TextView RegisterLink;
    private Button Customerloginbtn;
    private EditText Emailcustomer,Passwordcustomer;
    private ProgressDialog loadingbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        RegisterLink = findViewById(R.id.registerlink);
        Emailcustomer=findViewById(R.id.emailcustomerlogin);
        Passwordcustomer=findViewById(R.id.passwordcustomerlogin);
        Customerloginbtn = findViewById(R.id.loginbutton);
        loadingbar=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();





        RegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        Customerloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email=Emailcustomer.getText().toString();
                String password=Passwordcustomer.getText().toString();

                SignInCustomer(email,password);

            }
        });





    }

    private void SignInCustomer(String email, String password) {


        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(UserLoginActivity.this,"Please enter your email",Toast.LENGTH_SHORT).show();
        }

       else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(UserLoginActivity.this,"Please enter your password",Toast.LENGTH_SHORT).show();
        }else
        {
            loadingbar.setTitle("SIGNING IN USER");
            loadingbar.setMessage("please wait..");
            loadingbar.show();


            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {

                        Intent customerIntent=new Intent(UserLoginActivity.this,HomeActivity.class);
                        startActivity(customerIntent);
                        Toast.makeText(UserLoginActivity.this,"signed in successfully ",Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();


                    }

                    else
                    {
                        Toast.makeText(UserLoginActivity.this,"Sign in Unsuccessful..please check your details and  try again",Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();

                    }

                }
            });
        }



    }

}
