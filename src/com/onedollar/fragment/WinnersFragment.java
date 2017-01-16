package com.onedollar.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
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

import com.example.onedollar.R;
import com.example.onedollar.Winner_detail;
import com.onedollar.base.BaseFragment;
import com.onedollar.data.Constants.msg;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WinnersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WinnersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WinnersFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static Handler handler=null;
    private ArrayList<HashMap<String,String>> data;
    ListView lv_winner;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WinnersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WinnersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WinnersFragment newInstance(String param1, String param2) {
        WinnersFragment fragment = new WinnersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_winners, container, false);
        // Inflate the layout for this fragment
        initView(view);
        handler = new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==0){
                    Intent intent = new Intent();

                    //设置Intent的class属性，跳转到SecondActivity
                    intent.setClass(getActivity(), Winner_detail.class);
                    startActivity(intent);
                }

            }
        };
        return view;
    }

    private void initView(View view){
        lv_winner=(ListView)view.findViewById(R.id.winner_listview);

        MyAdapter adapter = new MyAdapter(getActivity());
        lv_winner.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void onAttach(Context context) {
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    static class ViewHolder
    {
        public ImageView winner_avatar;
        public TextView winner_date,winner_goods_name,winner_comment,winner_name;
        public LinearLayout mLinearLayout,mSampleLayout;

    }
    public class MyAdapter extends BaseAdapter{
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
                convertView = mInflater.inflate(R.layout.item_winner,null);
                //映射 eg：holder.img = (ImagView)convertView.findViewById(R.id.img);
                //holder.itemtablelayout = (TableLayout)convertView.findViewById(R.id.selcourse_tablelayout);
                holder.winner_avatar = (ImageView) convertView.findViewById(R.id.winner_avatar);
                holder.winner_date = (TextView)convertView.findViewById(R.id.winner_date);
                holder.winner_goods_name = (TextView)convertView.findViewById(R.id.winner_goods_name);
                holder.winner_comment = (TextView)convertView.findViewById(R.id.winner_comment);
                holder.winner_name = (TextView)convertView.findViewById(R.id.winner_name);

                holder.mLinearLayout = (LinearLayout)convertView.findViewById(R.id.whole_item_winner);
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
            
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
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
