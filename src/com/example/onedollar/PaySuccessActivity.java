package com.example.onedollar;

import android.content.Context;
import android.net.Uri;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import com.onedollar.base.BaseActivity;

public class PaySuccessActivity extends BaseActivity {
    Button pay_success_continue,pay_success_view;
    ListView pay_success_item_detail;
    TextView pay_success_summary;
    private ArrayList<HashMap<String,String>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);

        initView();
    }

    protected void initView(){
        pay_success_continue=(Button)findViewById(R.id.pay_success_continue);
        pay_success_view=(Button)findViewById(R.id.pay_success_view);
        pay_success_summary=(TextView)findViewById(R.id.pay_success_summary);
        pay_success_item_detail=(ListView)findViewById(R.id.pay_success_item_detail);

        pay_success_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaySuccessActivity.this, "Continue", Toast.LENGTH_SHORT).show();
            }
        });
        pay_success_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaySuccessActivity.this, "View Detail", Toast.LENGTH_SHORT).show();

            }
        });
        MyAdapter adapter=new MyAdapter(PaySuccessActivity.this);
        pay_success_item_detail.setAdapter(adapter);

    }

    static class ViewHolder
    {
        public TextView pay_success_goods_name,pay_success_good_num;
        public RelativeLayout relativeLayout;
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
//        public int getCount() {
//            return data.size();
//        }
        public int getCount() {
            return 2;
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_paysuccess,null);
                //映射 eg：holder.img = (ImagView)convertView.findViewById(R.id.img);
                //holder.itemtablelayout = (TableLayout)convertView.findViewById(R.id.selcourse_tablelayout);

                holder.pay_success_goods_name = (TextView)convertView.findViewById(R.id.pay_success_goods_name);
                holder.pay_success_good_num = (TextView)convertView.findViewById(R.id.pay_success_good_num);

                holder.relativeLayout = (RelativeLayout)convertView.findViewById(R.id.whole_item_pay_success);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            //赋值
            //eg:holder.img.setText()
            //Typeface iconfont = Typeface.createFromAsset(getAssets(),"iconfont/iconfont.ttf");
            //holder.course_icon.setTypeface(iconfont);

            Log.d("debug", "getview");
            holder.pay_success_goods_name.setText("Coach Newest Edition,Plue you could check the adthenticty at counter");
            holder.pay_success_good_num.setText("233 entitis");
            final int p = position;

            return convertView;
        }
    }


}
