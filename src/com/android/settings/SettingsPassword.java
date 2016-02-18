package com.android.settings;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemProperties;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class SettingsPassword extends Activity {
    private EditText txtPassword;
    private Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_password);
        addListenerOnButton();
    }

    private static String getPassword() {
        String pw = SystemProperties.get("aeris.settings.password");
        if (pw == null) {
            return "1234567890";
        }
        return pw;
    }

    private void addListenerOnButton() {
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw = txtPassword.getText().toString();
                if (!pw.equals(getPassword())) {
                    Toast.makeText(SettingsPassword.this, "Access Denied", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent settings = new Intent(SettingsPassword.this, Settings.class);
                startActivity(settings);
            }
        });
    }
}