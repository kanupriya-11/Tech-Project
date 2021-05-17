package com.example.konnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
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
import com.google.firebase.auth.FirebaseUser;
import com.scwang.wave.MultiWaveHeader;

public class login_activity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthstatelistner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int startColor = getWindow().getStatusBarColor();
            int endColor = ContextCompat.getColor(this,R.color.lightblue);
            ObjectAnimator.ofArgb(getWindow(), "statusBarColor", startColor, endColor).start();
        }

        TextView signup = findViewById(R.id.tv_signup);


        EditText login_email = findViewById(R.id.login_email);
        EditText login_password = findViewById(R.id.login_pass);
        Button login_button = findViewById(R.id.login_button);
        TextView dont_have_account = findViewById(R.id.tv_signup);
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();



        mAuthstatelistner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseuser = mFirebaseAuth.getCurrentUser();

                if(mFirebaseAuth != null)
                {
                    Toast.makeText(login_activity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login_activity.this, MainActivity.class));
                }

                else
                {
                    Toast.makeText(login_activity.this, "You are not registered. Please Sign Up!!", Toast.LENGTH_SHORT).show();
                }
            }
        };



        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();

                if(email.isEmpty())
                {
                    login_email.setError("Enter a valid Email");
                    login_email.requestFocus();
                }

                else if(password.isEmpty())
                {
                    login_password.setError("Enter a valid password");
                    login_password.requestFocus();
                }

                else if(email.isEmpty() && password.isEmpty())
                {
                    Toast.makeText(login_activity.this, "Please fill your details", Toast.LENGTH_SHORT).show();
                }

                else if(!(email.isEmpty()&& password.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login_activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(login_activity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login_activity.this, MainActivity.class));
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            }

                            else
                            {
                                Toast.makeText(login_activity.this, "Login Error. Try Again", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                }

                else
                {
                    Toast.makeText(login_activity.this, "An Error Occurred!!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_activity.this, SignupActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }

}