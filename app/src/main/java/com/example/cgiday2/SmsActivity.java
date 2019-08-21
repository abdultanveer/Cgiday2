package com.example.cgiday2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;

public class SmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage("5554",null,
                "happy birthday",null,null);

    }
}
