package com.example.onedollar;

import com.onedollar.base.BaseActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class PaymentActivity extends BaseActivity {
    int flag = 0;
    Button payment_confirmpay_bt;
    RadioGroup payment_radio_group;
    private static Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 0) {

                    if (flag == 1){
                        Intent intent = new Intent();
                        intent.setClass(PaymentActivity.this, PaySuccessActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent();
                        intent.setClass(PaymentActivity.this, PayFailActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        };
        initView();
    }

    protected void initView() {
        payment_radio_group = (RadioGroup) findViewById(R.id.payment_radio_group);
        payment_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.payment_radio_bt_1) {
                    flag = 1;
                } else
                    flag = 0;
            }

        });
        payment_confirmpay_bt = (Button) findViewById(R.id.payment_confirmpay_bt);
        payment_confirmpay_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
    }
}
