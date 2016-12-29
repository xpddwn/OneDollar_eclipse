package com.onedollar.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.onedollar.MainActivity;
import com.example.onedollar.R;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogRecord;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpcomingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpcomingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private  View view;
    private GridView gview;
    private List<Map<String, Object>> data;
    private SimpleAdapter sim_adapter;
    private Handler handler;
    // 图片封装为一个数组
    private int[] icon = { R.drawable.example, R.drawable.example,
            R.drawable.example, R.drawable.example, R.drawable.example,
            R.drawable.example, R.drawable.example, R.drawable.example,
            R.drawable.example, R.drawable.example, R.drawable.example,
            R.drawable.example };
    private String[] iconName = { "通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
            "设置", "语音", "天气", "浏览器", "视频" };

    public UpcomingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingFragment newInstance(String param1, String param2) {
        UpcomingFragment fragment = new UpcomingFragment();
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
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_upcoming, container, false);
        gview = (GridView) view.findViewById(R.id.upcominggrid);
        //新建List
        data = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String [] from ={"image","text"};
        int [] to = {R.id.upcoming_image,R.id.upcoming_introduce};
        //sim_adapter = new SimpleAdapter(getActivity(), data, R.layout.upcoming_item, from, to);
        //配置适配器
        MyAdapter adapter = new MyAdapter(getActivity());
        gview.setAdapter(adapter);
        //
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    //0号事件为绑定数据
                   // BindListData();
                    Log.d("debug", "binddata");
                } else if (msg.what == 1) {
                    Intent intent = new Intent();
                    ArrayList<String> infolist = new ArrayList<String>();
//                    infolist.add(userid);
//                    infolist.add(data.get(position).get("activity_id"));
//                    infolist.add(data.get(position).get("place_name"));
//                    infolist.add(data.get(position).get("begin_date"));
//                    infolist.add(data.get(position).get("activity_name"));
//                    infolist.add(data.get(position).get("status"));

                    //设置Intent的class属性，跳转到SecondActivity
//                    intent.setClass(getActivity(), MerchandiseActivity.class);
                    intent.putStringArrayListExtra("infolist", infolist);
                    startActivity(intent);

                }

            }
        };


        return view;
    }

    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data.add(map);
        }
        return data;
    }
    static class ViewHolder
    {
        public TextView upcoming_introduce,upcoming_winner,upcoming_luckynumber,upcoming_number,upcoming_time;
        public LinearLayout upcoming_linearLayout;

    }
    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return data.size();
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
                convertView = mInflater.inflate(R.layout.upcoming_item,null);
                //映射 eg：holder.img = (ImagView)convertView.findViewById(R.id.img);
                //holder.itemtablelayout = (TableLayout)convertView.findViewById(R.id.selcourse_tablelayout);
                holder.upcoming_introduce = (TextView)convertView.findViewById(R.id.upcoming_introduce);
                holder.upcoming_winner = (TextView)convertView.findViewById(R.id.upcoming_winner);
                holder.upcoming_luckynumber = (TextView)convertView.findViewById(R.id.upcoming_luckynumber);

                holder.upcoming_number = (TextView)convertView.findViewById(R.id.upcoming_number);
                holder.upcoming_time = (TextView)convertView.findViewById(R.id.upcoming_time);
                holder.upcoming_linearLayout = (LinearLayout)convertView.findViewById(R.id.upcoming_linearLayout);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            holder.upcoming_introduce.setText((String)data.get(position).get("text"));

            final int p = position;
            holder.upcoming_linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Message msg = new Message();
                    msg.what = 1;

                    handler.sendMessage(msg);
                }
            });

            return convertView;
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
