package com.onedollar.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.onedollar.MyApplication;
import com.example.onedollar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FantasyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FantasyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FantasyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View view;
    private GridView gview;
    private ListView lview;
    private LinearLayout livew1,toplinear;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    // 图片封装为一个数组

    private int[] icon = { R.drawable.example, R.drawable.example,
            R.drawable.example, R.drawable.example };
    private String[] iconName = {  "日历", "照相机", "时钟", "游戏", };

    public FantasyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FantasyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FantasyFragment newInstance(String param1, String param2) {
        FantasyFragment fragment = new FantasyFragment();
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
        view= inflater.inflate(R.layout.fragment_fantasy, container, false);
        lview=(ListView)view.findViewById(R.id.recentWinners);
        livew1=(LinearLayout)view.findViewById(R.id.recentUpcoming);
        gview = (GridView) view.findViewById(R.id.fantasygrid);
        toplinear=(LinearLayout)view.findViewById(R.id.toplinear);
        //配置ListView
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for(int i=1;i<4;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("messagetext", "Jack win an Iphone6S");//图像资源的ID
            listItem.add(map);
        }
        SimpleAdapter sa=new SimpleAdapter(getActivity(),listItem,R.layout.item_message,
                new String[] {"messagetext"},
                new int[] {R.id.messagetext});
        lview.setAdapter(sa);
        //配置HorizontalListView
        View horview= inflater.inflate(R.layout.horizontal_list_item,container,false);

        livew1.addView(horview);

        //配置GridView
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String [] from ={"image","text"};
        int [] to = {R.id.image,R.id.text};
        sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.fantasygriditem, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);
       // livew1.setAdapter(sim_adapter);
        return  view;
    }
    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
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
