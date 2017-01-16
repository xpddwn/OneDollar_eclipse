package com.example.onedollar;

import com.onedollar.base.BaseActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Winner_detail extends BaseActivity {
    ImageView winner_detail_avatar,winner_detail_image1,winner_detail_image2,winner_detail_image3;
    TextView winner_detail_name,winner_date,winner_detail_product_name,winner_detail_comment,winner_detail_winnnernum,winner_detail_totalnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_detail);
        setView();
    }
    protected void setView(){
        winner_detail_avatar = (ImageView)findViewById(R.id.winner_detail_avatar);
        winner_detail_image1 = (ImageView)findViewById(R.id.winner_detail_image1);
        winner_detail_image2 = (ImageView)findViewById(R.id.winner_detail_image2);
        winner_detail_image3 = (ImageView)findViewById(R.id.winner_detail_image3);

        winner_detail_name=(TextView)findViewById(R.id.winner_detail_name);
        winner_date=(TextView)findViewById(R.id.winner_date);
        winner_detail_product_name=(TextView)findViewById(R.id.winner_detail_product_name);
        winner_detail_comment=(TextView)findViewById(R.id.winner_detail_comment);
        winner_detail_winnnernum=(TextView)findViewById(R.id.winner_detail_winnnernum);
        winner_detail_totalnum=(TextView)findViewById(R.id.winner_detail_totalnum);

        winner_detail_avatar.setImageResource(R.drawable.example);
        winner_detail_image1.setImageResource(R.drawable.example);
        winner_detail_image2.setImageResource(R.drawable.example);
        winner_detail_image3.setImageResource(R.drawable.example);

    }
}
