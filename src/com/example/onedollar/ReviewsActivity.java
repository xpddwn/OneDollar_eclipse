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

public class ReviewsActivity extends BaseActivity {

    private static Handler handler=null;
    ListView lv_winner;
    private ArrayList<HashMap<String,String>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
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
        lv_winner = (ListView)findViewById(R.id.reviews_listview);

        MyAdapter adapter = new MyAdapter(this);
        lv_winner.setAdapter(adapter);
    }
    static class ViewHolder
    {
        public ImageView winner_avatar;
        public TextView winner_date,winner_goods_name,winner_comment,winner_name,winner_goods_authenticity;
        public LinearLayout relativeLayout,mSampleLayout;

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
            return 3;
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
                convertView = mInflater.inflate(R.layout.item_winner,null);
                //映射 eg：holder.img = (ImagView)convertView.findViewById(R.id.img);
                //holder.itemtablelayout = (TableLayout)convertView.findViewById(R.id.selcourse_tablelayout);
                holder.winner_avatar = (ImageView) convertView.findViewById(R.id.winner_avatar);
                holder.winner_date = (TextView)convertView.findViewById(R.id.winner_date);
                holder.winner_goods_name = (TextView)convertView.findViewById(R.id.winner_goods_name);
                holder.winner_comment = (TextView)convertView.findViewById(R.id.winner_comment);
                holder.winner_name = (TextView)convertView.findViewById(R.id.winner_name);
                holder.winner_goods_authenticity=(TextView)convertView.findViewById(R.id.winner_simple_talk);
                holder.relativeLayout = (LinearLayout)convertView.findViewById(R.id.whole_item_winner);
                holder.mSampleLayout = (LinearLayout)convertView.findViewById(R.id.sample_layout);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            //赋值
            //eg:holder.img.setText()
            //Typeface iconfont = Typeface.createFromAsset(getAssets(),"iconfont/iconfont.ttf");
            //holder.course_icon.setTypeface(iconfont);

            final int p = position;
           
            if(p%2==0){
            	holder.mSampleLayout.setVisibility(View.VISIBLE);
            }
            
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 0;
                    Bundle bundle = new Bundle();
//                    bundle.putString("courseid", data.get(p).get("kc_id"));  //往Bundle中存放数据
//                    bundle.putInt("position",position);
                    msg.setData(bundle);//mes利用Bundle传递数据
                    handler.sendMessage(msg);
                }
            });
            return convertView;
        }
    }

}
