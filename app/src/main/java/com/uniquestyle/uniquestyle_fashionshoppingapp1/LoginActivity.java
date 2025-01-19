package com.uniquestyle.uniquestyle_fashionshoppingapp1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    ImageView ivLogo;
    TextView tvTtle,tvNewUser;
    EditText etUsername,etPassword;
    CheckBox chkShowHide;
    Button btnLogin;

    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ivLogo=findViewById(R.id.ivLoginLogo);
        tvTtle=findViewById(R.id.tvLoginTitle);
        tvNewUser=findViewById(R.id.tvLoginNewUser);
        etUsername=findViewById(R.id.etLoginUsername);
        etPassword=findViewById(R.id.etLoginPassword);
        chkShowHide=findViewById(R.id.cbLoginShowHidePassword);
        btnLogin=findViewById(R.id.btnLoginLogin);


        chkShowHide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                else
                {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().isEmpty())
                {
                    etUsername.setError("Please Enter Valid UserName");

                } else if (etUsername.getText().toString().contains("@")&&etUsername.getText().toString().contains(".com")) {
                    etUsername.setError("Please Enter Valid UserName");
                } else if (etUsername.getText().toString().length()>12) {
                    etUsername.setError("Please Enter Username greter than 8");
                } else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Please Enter Coreect Password");
                } else if (etPassword.getText().toString().length()<8) {
                    etPassword.setError("Please Enter Less than 9");
                }
                else
                {
                    progressDialog=new ProgressDialog(LoginActivity.this);
                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("Login Under Progress");
                    progressDialog.show();


                }

            }
        });
        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }






    }

