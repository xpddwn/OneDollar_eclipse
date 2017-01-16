package com.example.onedollar;

import com.onedollar.base.BaseActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PayFailActivity extends BaseActivity {
    TextView pay_fail_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_fail);
        initView();
    }

    protected void initView(){
        pay_fail_detail=(TextView)findViewById(R.id.pay_fail_detail);

    }
}
