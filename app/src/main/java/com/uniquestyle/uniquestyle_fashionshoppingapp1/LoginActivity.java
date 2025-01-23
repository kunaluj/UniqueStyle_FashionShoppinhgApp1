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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

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

                    userLogin();


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

    private void userLogin() {
        AsyncHttpClient client= new AsyncHttpClient();
        RequestParams params =new RequestParams();

        params.put("username",etUsername.getText().toString());
        params.put("password",etPassword.getText().toString());

        client.post("http://172.20.10.2:80/UniqueStyle_FashionShoppingAppAPI/userLogin.php",params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        progressDialog.dismiss();
                        try {
                            String status = response.getString("success");
                            if (status.equals("1"))
                            {
                                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Invalid Username or Password",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this,"Server Error",
                                Toast.LENGTH_SHORT).show();


                    }
                }


        );

    }


}

