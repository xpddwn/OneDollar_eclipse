package com.onedollar.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.onedollar.MerchandiseActivity;
import com.example.onedollar.PaymentActivity;
import com.example.onedollar.R;
import com.onedollar.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<HashMap<String,String>> data;
    private static Handler handler=null;
    ListView lv_cart;
    Button cart_check_bt;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CartFragment() {
        // Required empty public constructor
    }
    final int GOODS_DATAIL=0;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        // Inflate the layout for this fragment
        initView(view);
        handler = new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==GOODS_DATAIL){
                   Intent intent = new Intent();
                    intent.setClass(getActivity(), MerchandiseActivity.class);
                    startActivity(intent);
                }

            }
        };
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    private void initView(View view){
        lv_cart=(ListView)view.findViewById(R.id.cart_listview);
        MyAdapter adapter = new MyAdapter(getActivity());
        lv_cart.setAdapter(adapter);
        cart_check_bt=(Button)view.findViewById(R.id.cart_check_bt);
        cart_check_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), PaymentActivity.class);
                startActivity(intent);
            }
        });
//        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
//        for(int i=5;i<10;i++)
//        {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("goods_image", R.drawable.example);//图像资源的ID
//            map.put("goods_name", "Name "+i);
//            map.put("goods_left", i+" left ");
//            map.put("goods_total","Total "+i+" entitis");
//            map.put("good_want_buy","I want to buy");
//            listItem.add(map);
//        }
//
//        SimpleAdapter sa=new SimpleAdapter(MyApplication.newInstance(),listItem,R.layout.item_cart,
//                new String[] {"goods_image","goods_name", "goods_left","goods_total","good_want_buy"},
//                new int[] {R.id.goods_image,R.id.goods_name,R.id.goods_left,R.id.goods_total,R.id.good_want_buy});
//        lv_cart.setAdapter(sa);
    }

    static class ViewHolder
    {
        public ImageView goods_image;
        public TextView goods_name,goods_left,goods_total,good_want_buy;
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
                convertView = mInflater.inflate(R.layout.item_cart,null);
                //映射 eg：holder.img = (ImagView)convertView.findViewById(R.id.img);
                //holder.itemtablelayout = (TableLayout)convertView.findViewById(R.id.selcourse_tablelayout);
                holder.goods_image = (ImageView) convertView.findViewById(R.id.goods_image);
                holder.goods_name = (TextView)convertView.findViewById(R.id.goods_name);
                holder.goods_left = (TextView)convertView.findViewById(R.id.goods_left);
                holder.goods_total = (TextView)convertView.findViewById(R.id.goods_total);
                holder.good_want_buy = (TextView)convertView.findViewById(R.id.good_want_buy);

                holder.relativeLayout = (RelativeLayout)convertView.findViewById(R.id.whole_item_cart);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            //赋值
            //eg:holder.img.setText()
            //Typeface iconfont = Typeface.createFromAsset(getAssets(),"iconfont/iconfont.ttf");
            //holder.course_icon.setTypeface(iconfont);

            final int p = position;
            holder.goods_image.setOnClickListener(new View.OnClickListener() {
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
