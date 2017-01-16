package com.example.onedollar;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import com.onedollar.base.BaseActivity;

public class PreviousActivity extends BaseActivity {
    private static Handler handler=null;
    ListView lv_winner;
    private ArrayList<HashMap<String,String>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous);
        initView();
        handler = new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==0){
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), Winner_detail.class);
                    startActivity(intent);
                }

            }
        };
    }
    private void initView()
    {
        lv_winner = (ListView)findViewById(R.id.previous_listview);

        MyAdapter adapter = new MyAdapter(this);
        lv_winner.setAdapter(adapter);
    }
    static class ViewHolder
    {
        public ImageView previous_avatar;
        public TextView previous_name,previous_number,previous_participation,previous_time;
        public LinearLayout linearLayout;

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
            return 5;
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
                convertView = mInflater.inflate(R.layout.item_previous,null);
                //映射 eg：holder.img = (ImagView)convertView.findViewById(R.id.img);
                //holder.itemtablelayout = (TableLayout)convertView.findViewById(R.id.selcourse_tablelayout);
                holder.previous_avatar = (ImageView) convertView.findViewById(R.id.previous_avatar);
                holder.previous_name = (TextView)convertView.findViewById(R.id.previous_name);
                holder.previous_number = (TextView)convertView.findViewById(R.id.previous_number);
                holder.previous_time=(TextView)convertView.findViewById(R.id.previous_time);
                holder.previous_participation = (TextView)convertView.findViewById(R.id.previous_participation);
                holder.linearLayout=(LinearLayout)convertView.findViewById(R.id.whole_item_previous) ;
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            //赋值
            //eg:holder.img.setText()
            //Typeface iconfont = Typeface.createFromAsset(getAssets(),"iconfont/iconfont.ttf");
            //holder.course_icon.setTypeface(iconfont);


            Log.d("debug", "getview");
            holder.previous_avatar.setImageResource(R.drawable.example);
            holder.previous_name.setText("Jack");
            holder.previous_number.setText("114514");
            holder.previous_participation.setText("1188 people");
            holder.previous_time.setText("Open from 2016-12-05 17:37:44");
            final int p = position;

            return convertView;
        }
    }

}
