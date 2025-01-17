package com.uniquestyle.uniquestyle_fashionshoppingapp1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    ImageView ivLogo;
    TextView tvTitle,tvNewUser;
    EditText etUsername,etPassword;
    CheckBox chkShowHide;
    Button btnLogoin;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ivLogo =findViewById(R.id.ivLoginLogo);
        tvTitle =findViewById(R.id.tvLoginTitle);
        tvNewUser=findViewById(R.id.tvLoginNewUser);
        etUsername=findViewById(R.id.etLoginUsername);
        etPassword =findViewById(R.id.etLoginPassword);
        chkShowHide=findViewById(R.id.cbLoginShowHidePassword);
        btnLogoin =findViewById(R.id.btnLoginLogin);

        btnLogoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUsername.getText().toString().toString().isEmpty())
                {
                    etUsername.setError("Please Enter  Your Username");
                } else if (!etUsername.getText().toString().contains("@") && !etUsername.getText().toString().contains(".com") ) {
                    
                }
            }
        });

    }
}