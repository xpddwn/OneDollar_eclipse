package com.example.onedollar;

import com.onedollar.base.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SettingActivity extends BaseActivity {
    ImageView aboutus,protection,agreement,cluase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        aboutus=(ImageView)findViewById(R.id.aboutus);
        protection=(ImageView)findViewById(R.id.protection);
        agreement=(ImageView)findViewById(R.id.agreement);
        cluase=(ImageView)findViewById(R.id.liability);
        aboutus.setOnClickListener(myListener);
        protection.setOnClickListener(myListener);
        agreement.setOnClickListener(myListener);
        cluase.setOnClickListener(myListener);
    }
    View.OnClickListener myListener= new View.OnClickListener() {
    @Override
    public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(SettingActivity.this, ClauseActivity.class);
            startActivity(intent);
            }
    };
}