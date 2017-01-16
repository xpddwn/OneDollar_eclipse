package com.example.onedollar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.onedollar.base.BaseActivity;

public class IdentityActivity extends BaseActivity {
    TextView phonetext;
    ImageView phone,name,email,company,order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity);
        phone=(ImageView)findViewById(R.id.identity_phone);
        name=(ImageView)findViewById(R.id.identity_name);
        email=(ImageView)findViewById(R.id.identity_email);
        company=(ImageView)findViewById(R.id.identity_company);
        order=(ImageView)findViewById(R.id.identity_order);
        phonetext=(TextView)findViewById(R.id.identity_phonetext);
        phone.setOnClickListener(myListener);
        name.setOnClickListener(myListener);
        email.setOnClickListener(myListener);
        company.setOnClickListener(myListener);
        order.setOnClickListener(myListener);
    }
    View.OnClickListener myListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            ArrayList<String> infolist = new ArrayList<String>();
            //infolist.add(phonetext.getText().toString());
            intent.setClass(IdentityActivity.this,ModifyActivity.class);
            intent.putStringArrayListExtra("infolist", infolist);
            startActivity(intent);
        }
    };

}
