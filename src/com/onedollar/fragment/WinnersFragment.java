package com.onedollar.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.onedollar.MainActivity;
import com.example.onedollar.MyApplication;
import com.example.onedollar.R;

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
public class WinnersFragment extends Fragment {
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
//                    intent.setClass(getActivity(), Winner_detail.class);
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
//        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
//        for(int i=0;i<6;i++)
//        {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("winner_avatar", R.drawable.example);//图像资源的ID
//            map.put("winner_name", "Uesr "+i);
//            map.put("winner_date"," 2016-12- "+i);
//            map.put("winner_goods_name","["+i+"期][Coach]克罗斯比真皮迷你手提包！");
//            map.put("winner_comment",i+" 支持一元夺宝！我真是太幸运了！支持一元夺宝！我真是太幸运了！");
//            listItem.add(map);
//        }
//        SimpleAdapter sa=new SimpleAdapter(MyApplication.newInstance(),listItem,R.layout.item_winner,
//                new String[] {"winner_avatar","winner_name", "winner_date","winner_goods_name","winner_comment"},
//                new int[] {R.id.winner_avatar,R.id.winner_name,R.id.winner_date,R.id.winner_goods_name,R.id.winner_comment});
//        lv_winner.setAdapter(sa);
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

    static class ViewHolder
    {
        public ImageView winner_avatar;
        public TextView winner_date,winner_goods_name,winner_comment,winner_name;
        public RelativeLayout relativeLayout;

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

                holder.relativeLayout = (RelativeLayout)convertView.findViewById(R.id.winner_item_whole);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            //赋值
            //eg:holder.img.setText()
            //Typeface iconfont = Typeface.createFromAsset(getAssets(),"iconfont/iconfont.ttf");
            //holder.course_icon.setTypeface(iconfont);


            Log.d("debug", "getview");
            holder.winner_avatar.setImageResource(R.drawable.example);
            holder.winner_date.setText("2016-12-12");
            holder.winner_goods_name.setText("[第x期][Coach]克罗斯比真皮迷你手提包！");
            holder.winner_comment.setText("支持一元夺宝！我真是太幸运了！支持一元夺宝！我真是太幸运了");
            holder.winner_name.setText("Uesr ");
            final int p = position;
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
