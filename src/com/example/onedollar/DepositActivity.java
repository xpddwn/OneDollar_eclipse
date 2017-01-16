package com.example.onedollar;

import com.onedollar.base.BaseActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DepositActivity extends BaseActivity{
    RadioGroup group1,group2;
    RadioButton button1,button2,button3,button4,button5,button6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        group1=(RadioGroup)findViewById(R.id.deposit_radiogroup1);
        group2=(RadioGroup)findViewById(R.id.deposit_radiogroup2);
        button1=(RadioButton)findViewById(R.id.deposit_buttona);
        button2=(RadioButton)findViewById(R.id.deposit_buttonb);
        button3=(RadioButton)findViewById(R.id.deposit_buttonc);
        button4=(RadioButton)findViewById(R.id.deposit_buttond);
        button5=(RadioButton)findViewById(R.id.deposit_buttone);
        button6=(RadioButton)findViewById(R.id.deposit_buttonf);
        button1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                group2.clearCheck();
            }
        });
        button2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                group2.clearCheck();
            }
        });
        button3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                group2.clearCheck();
            }
        });
        button4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                group1.clearCheck();
            }
        });
        button5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                group1.clearCheck();
            }
        });
        button6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                group1.clearCheck();
            }
        });
    }
}
