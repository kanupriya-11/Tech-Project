package com.example.konnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        EditText signup_email = findViewById(R.id.signup_email);
        EditText signup_password = findViewById(R.id.signnup_pass);
        Button signup_button = findViewById(R.id.signup_button);
        TextView have_account = findViewById(R.id.tv_login);
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signup_email.getText().toString();
                String password = signup_password.getText().toString();

                if(email.isEmpty())
                {
                    signup_email.setError("Enter a valid Email");
                    signup_email.requestFocus();
                }

                else if(password.isEmpty())
                {
                    signup_password.setError("Enter a valid password");
                    signup_password.requestFocus();
                }

                else if(email.isEmpty() && password.isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "Please fill your details", Toast.LENGTH_SHORT).show();
                }

                else if(!(email.isEmpty()&& password.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignupActivity.this, "Sign Up Successful!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            }

                            else
                            {
                                Toast.makeText(SignupActivity.this, "Failed to SignUp, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

                else
                {
                    Toast.makeText(SignupActivity.this, "An Error Occurred!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, login_activity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}