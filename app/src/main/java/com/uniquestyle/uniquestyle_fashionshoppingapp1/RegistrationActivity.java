package com.uniquestyle.uniquestyle_fashionshoppingapp1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.prefs.Preferences;

import cz.msebera.android.httpclient.Header;

public class RegistrationActivity extends AppCompatActivity {

    EditText etUserName,etFullName,etMobileNo,etEmailid,etNewPassWord,etOTP;
    Button btnRegister;



    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        setTitle("Registration New User");

        etUserName=findViewById(R.id.etRegisterUserName);
        etFullName=findViewById(R.id.etFullName);
        etMobileNo=findViewById(R.id.etRegisterMobileNo);
        etEmailid=findViewById(R.id.etRegisterEmail);
        etNewPassWord=findViewById(R.id.etRegisterNewPassword);
        etOTP =findViewById(R.id.etRegisterOTP);
        btnRegister=findViewById(R.id.btnRegisterUser);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUserName.getText().toString().isEmpty())
                {
                    etUserName.setError("Please Enter Name");

                } else if (etFullName.getText().toString().isEmpty()) {
                    etFullName.setError("Please Enter Username");
                } else if (etMobileNo.getText().toString().isEmpty()) {
                    etMobileNo.setError("Please Enter Your Mobile Number");
                } else if (etMobileNo.getText().toString().length()!=10) {
                    etMobileNo.setError("Please Enter correct 10 Digit Mobile Number ");
                } else if (etEmailid.getText().toString().isEmpty()) {
                    etEmailid.setError("Please Enter Email Address");
                } else if (!etEmailid.getText().toString().contains("@")|| (!etEmailid.getText().toString().contains(".com"))) {
                    etEmailid.setError("Please use correct Email id");
                } else if (etNewPassWord.getText().toString().isEmpty()) {
                    etNewPassWord.setError("please enter your password");

                } else if (etNewPassWord.getText().toString().length()<8) {
                    etNewPassWord.setError("please enter greater then 8 char");
                }
                else if (etOTP.getText().toString().isEmpty()) {
                    etNewPassWord.setError("please enter Otp");
                }
                else
                {
                    progressDialog = new ProgressDialog(RegistrationActivity.this);
                    progressDialog.setTitle("Please Wait...");
                    progressDialog.setMessage("Registration is in Progress");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();
                    userregisterdetails();

                }

            }
        });


    }

    private void userregisterdetails() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("username",etUserName.getText().toString());
        params.put("fullname",etFullName.getText().toString());
        params.put("mobileno",etMobileNo.getText().toString());
        params.put("emailid",etEmailid.getText().toString());
        params.put("password",etNewPassWord.getText().toString());
        params.put("otpverification",etOTP.getText().toString());


        client.post("http://172.20.10.2:80/UniqueStyle_FashionShoppingAppAPI/userregisterdetails.php",params,
                new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            String status = response.getString("success");
                            if (status.equals("1"))
                            {
                                progressDialog.dismiss();
                                Toast.makeText(RegistrationActivity.this,"Registration Done Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(RegistrationActivity.this,"Already Data Present",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                          JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this,"Server Error",
                                Toast.LENGTH_SHORT).show();
                    }
                }

        );
    }


}


