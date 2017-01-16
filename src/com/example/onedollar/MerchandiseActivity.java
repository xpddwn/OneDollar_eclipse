package com.example.onedollar;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import com.onedollar.base.BaseActivity;

public class MerchandiseActivity extends BaseActivity {

    private static final String LOG_TAG = "MerchandiseActivity";
    private ImageHandler handler = new ImageHandler(new WeakReference<MerchandiseActivity>(this));
    private Handler myhandler;
    private ViewPager viewPager;
    private ImageView specifics,previous,reviews;
    private RelativeLayout bottom1;
    private LinearLayout bottom2,countdown,progressbar,currentrecord,open;
    private int mode;//商品状态,1未参与揭晓中,2未参与未开奖,3未参与已揭晓,4已参与揭晓中,5已参与未开奖,6已参与已揭晓
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchandise);
        mode=3;//预设mode为1
        specifics=(ImageView)findViewById(R.id.specifics);
        previous=(ImageView)findViewById(R.id.previous);
        reviews=(ImageView)findViewById(R.id.reviews);
        bottom1=(RelativeLayout)findViewById(R.id.merchandise_undo);
        bottom2=(LinearLayout)findViewById(R.id.merchandise_close);
        countdown=(LinearLayout)findViewById(R.id.countdown);
        progressbar=(LinearLayout)findViewById(R.id.progressbar);
        currentrecord=(LinearLayout)findViewById(R.id.currentrecord);
        open=(LinearLayout)findViewById(R.id.open);
        if(mode==1)
        {
            bottom1.setVisibility(View.VISIBLE);
            bottom2.setVisibility(View.GONE);
            countdown.setVisibility(View.VISIBLE);
            progressbar.setVisibility(View.GONE);
            currentrecord.setVisibility(View.GONE);
            open.setVisibility(View.GONE);
        }
        else if(mode==2)
        {
            bottom1.setVisibility(View.GONE);
            bottom2.setVisibility(View.VISIBLE);
            countdown.setVisibility(View.GONE);
            progressbar.setVisibility(View.VISIBLE);
            currentrecord.setVisibility(View.VISIBLE);
            open.setVisibility(View.GONE);
        }
        else if(mode==3)
        {
            bottom1.setVisibility(View.VISIBLE);
            bottom2.setVisibility(View.GONE);
            countdown.setVisibility(View.GONE);
            progressbar.setVisibility(View.GONE);
            currentrecord.setVisibility(View.GONE);
            open.setVisibility(View.VISIBLE);
        }
        else if(mode==4)
        {
            bottom1.setVisibility(View.GONE);
            bottom2.setVisibility(View.GONE);
            countdown.setVisibility(View.VISIBLE);
            progressbar.setVisibility(View.GONE);
            currentrecord.setVisibility(View.GONE);
            open.setVisibility(View.GONE);
        }
        else if(mode==5)
        {
            bottom1.setVisibility(View.GONE);
            bottom2.setVisibility(View.VISIBLE);
            countdown.setVisibility(View.GONE);
            progressbar.setVisibility(View.VISIBLE);
            currentrecord.setVisibility(View.VISIBLE);
            open.setVisibility(View.GONE);
        }
        else
        {
            bottom1.setVisibility(View.GONE);
            bottom2.setVisibility(View.GONE);
            countdown.setVisibility(View.GONE);
            progressbar.setVisibility(View.GONE);
            currentrecord.setVisibility(View.GONE);
            open.setVisibility(View.VISIBLE);
        }
        specifics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = 1;
                myhandler.sendMessage(msg);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = 2;
                myhandler.sendMessage(msg);
            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = 3;
                myhandler.sendMessage(msg);
            }
        });
        myhandler=new Handler(){
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Intent intent = new Intent();
                    ArrayList<String> infolist = new ArrayList<String>();
                    intent.setClass(MerchandiseActivity.this,SpecificsActivity.class );
                    intent.putStringArrayListExtra("infolist", infolist);
                    startActivity(intent);
                }
                else if(msg.what==2)
                {
                    Intent intent = new Intent();
                    ArrayList<String> infolist = new ArrayList<String>();
                    intent.setClass(MerchandiseActivity.this,PreviousActivity.class );
                    intent.putStringArrayListExtra("infolist", infolist);
                    startActivity(intent);
                }
                else if(msg.what==3)
                {
                    Intent intent = new Intent();
                    ArrayList<String> infolist = new ArrayList<String>();
                    intent.setClass(MerchandiseActivity.this,ReviewsActivity.class );
                    intent.putStringArrayListExtra("infolist", infolist);
                    startActivity(intent);
                }
            }
        };
        //设置viewpager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        LayoutInflater inflater = LayoutInflater.from(this);
        ImageView view1 = (ImageView) inflater.inflate(R.layout.viewpage1, null);
        ImageView view2 = (ImageView) inflater.inflate(R.layout.viewpage1, null);
        ImageView view3 = (ImageView) inflater.inflate(R.layout.viewpage1, null);
        //view1.setImageResource(R.drawable.ics);
        ArrayList<ImageView> views = new ArrayList<ImageView>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        viewPager.setAdapter(new ImageAdapter(views));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //配合Adapter的currentItem字段进行设置。
            @Override
            public void onPageSelected(int arg0) {
                handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, arg0, 0));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            //覆写该方法实现轮播效果的暂停和恢复
            @Override
            public void onPageScrollStateChanged(int arg0) {
                switch (arg0) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });
        viewPager.setCurrentItem(Integer.MAX_VALUE/2);//默认在中间，使用户看不到边界
        //开始轮播效果
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
    //end of onCreate
    }
    private class ImageAdapter extends PagerAdapter {

        private ArrayList<ImageView> viewlist;

        public ImageAdapter(ArrayList<ImageView> viewlist) {
            this.viewlist = viewlist;
        }

        @Override
        public int getCount() {
            //设置成最大，使用户看不到边界
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }
        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            //Warning：不要在这里调用removeView
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //对ViewPager页号求模取出View列表中要显示的项
            position %= viewlist.size();
            if (position<0){
                position = viewlist.size()+position;
            }
            ImageView view = viewlist.get(position);
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp =view.getParent();
            if (vp!=null){
                ViewGroup parent = (ViewGroup)vp;
                parent.removeView(view);
            }
            container.addView(view);
            //add listeners here if necessary
            return view;
        }
    }
    private static class ImageHandler extends Handler {

        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE  = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT   = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT  = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED  = 4;

        //轮播间隔时间
        protected static final long MSG_DELAY = 3000;

        //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
        private WeakReference<MerchandiseActivity> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<MerchandiseActivity> wk){
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(LOG_TAG, "receive message " + msg.what);
            MerchandiseActivity activity = weakReference.get();
            if (activity==null){
                //Activity已经回收，无需再处理UI了
                return ;
            }
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)){
                activity.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    activity.viewPager.setCurrentItem(currentItem);
                    //准备下次播放
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }
}
