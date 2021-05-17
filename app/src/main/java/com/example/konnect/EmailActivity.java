package com.example.konnect;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.konnect.R;

public class EmailActivity extends AppCompatActivity {

    private EditText sender_email, subject, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);


        sender_email = findViewById(R.id.sender_email);
        subject = findViewById(R.id.email_subject);
        message = findViewById(R.id.email_body);

        Button send_email = findViewById(R.id.btn_email_send);
        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

    }

    private void sendMail()
    {
        String recipientList = sender_email.getText().toString();
        String[] recipients = recipientList.split(",");

        String email_subject = subject.getText().toString();
        String body = message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, email_subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}