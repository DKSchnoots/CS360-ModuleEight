package com.example.cs360_appdevproposal_jamiejavis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_SEND_SMS = 1;

    private LinearLayout buttonsLayout;
    private TextView notificationTextView;
    private TextView notificationSettingsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        buttonsLayout = findViewById(R.id.buttonsLayout);
        notificationTextView = findViewById(R.id.notificationTextView);
        notificationSettingsTextView = findViewById(R.id.notificationSettingsTextView);

        Button denyButton = findViewById(R.id.denyButton);
        Button allowButton = findViewById(R.id.allowButton);

        denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // deny button click
                hideNotificationViews();
                Toast.makeText(MainActivity3.this, "SMS notifications denied", Toast.LENGTH_SHORT).show();
            }
        });

        allowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // allow button click
                requestSmsPermission();
            }
        });
    }

    private void requestSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // permission not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_SEND_SMS);
        } else {
            // permission granted
            Toast.makeText(this, "SMS notifications allowed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // granted
                Toast.makeText(this, "SMS notifications allowed", Toast.LENGTH_SHORT).show();
            } else {
                // denied
                hideNotificationViews();
                Toast.makeText(this, "SMS notifications denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void hideNotificationViews() {
        notificationTextView.setVisibility(View.GONE);
        buttonsLayout.setVisibility(View.GONE);
        notificationSettingsTextView.setVisibility(View.VISIBLE);
    }
}
