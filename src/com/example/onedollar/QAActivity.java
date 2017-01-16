package com.example.onedollar;

import com.onedollar.base.BaseActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class QAActivity extends BaseActivity {
    ImageView q1,q2,q3,q4;
    TextView a1,a2,a3,a4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);
        q1=(ImageView)findViewById(R.id.q1);
        q2=(ImageView)findViewById(R.id.q2);
        q3=(ImageView)findViewById(R.id.q3);
        q4=(ImageView)findViewById(R.id.q4);
        a1=(TextView) findViewById(R.id.a1);
        a2=(TextView) findViewById(R.id.a2);
        a3=(TextView) findViewById(R.id.a3);
        a4=(TextView) findViewById(R.id.a4);
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a1.getVisibility()==View.GONE)
                {
                    a1.setVisibility(View.VISIBLE);
                    q1.setImageResource(R.drawable.arrow1);
                }
                else
                {
                    a1.setVisibility(View.GONE);
                    q1.setImageResource(R.drawable.arrow);
                }
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a2.getVisibility()==View.GONE)
                {
                    a2.setVisibility(View.VISIBLE);
                    q2.setImageResource(R.drawable.arrow1);
                }
                else
                {
                    a2.setVisibility(View.GONE);
                    q2.setImageResource(R.drawable.arrow);
                }
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a3.getVisibility()==View.GONE)
                {
                    a3.setVisibility(View.VISIBLE);
                    q3.setImageResource(R.drawable.arrow1);
                }
                else
                {
                    a3.setVisibility(View.GONE);
                    q3.setImageResource(R.drawable.arrow);
                }
            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a4.getVisibility()==View.GONE)
                {
                    a4.setVisibility(View.VISIBLE);
                    q4.setImageResource(R.drawable.arrow1);
                }
                else
                {
                    a4.setVisibility(View.GONE);
                    q4.setImageResource(R.drawable.arrow);
                }
            }
        });
    }
}
