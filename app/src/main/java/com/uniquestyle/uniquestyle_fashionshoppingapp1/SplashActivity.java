package com.uniquestyle.uniquestyle_fashionshoppingapp1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    ImageView ivMainLogo;
    TextView tvTitle;
    Animation fadeInAnim;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ivMainLogo = findViewById(R.id.ivSplashLogo);
        tvTitle = findViewById(R.id.tvSplashTitle);

        fadeInAnim = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fadein);
        ivMainLogo.startAnimation(fadeInAnim);
        tvTitle.startAnimation(fadeInAnim);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(i);
            }
        },4000);



    }
}