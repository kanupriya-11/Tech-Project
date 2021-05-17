package com.example.konnect;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {

    private EditText phoneNumber, message;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);


        phoneNumber = findViewById(R.id.sms_phone_number);
        message = findViewById(R.id.sms_message);
        send = findViewById(R.id.send_sms);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                    {
                        sendSMS();
                    }
                    else
                    {
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1);
                    }
                }

            }
        });


    }

    private void sendSMS()
    {
        String phoneNo = phoneNumber.getText().toString();
        String SMS = message.getText().toString();
        try {
            SmsManager smsManager  = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, SMS, null, null);
            Toast.makeText(this, "Message Sent Successfully", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Failed to Send Message", Toast.LENGTH_SHORT).show();
        }

    }
}
