package com.example.konnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class phone_call extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private EditText mEditTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);


        mEditTextNumber = findViewById(R.id.edit_text_number);
        Button imageCall = findViewById(R.id.image_call);

        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }

    private void makePhoneCall()
    {
        String number = mEditTextNumber.getText().toString();
        if(number.trim().length() > 0)
        {
            if(ContextCompat.checkSelfPermission(phone_call.this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(phone_call.this, new String[]
                        {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }

            else
            {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
        else
        {
            Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }



}