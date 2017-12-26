package com.sdzx.government.governmentpersonnel.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.activity.MyApp;
import com.sdzx.government.governmentpersonnel.activity.PeopleInfoActivity;
import com.sdzx.government.governmentpersonnel.adapter.PeoplesAdapter;
import com.sdzx.government.governmentpersonnel.adapter.SpinnerAda;
import com.sdzx.government.governmentpersonnel.bean.RsdaInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.sdzx.government.governmentpersonnel.activity.MainActivity.db;

/**
 * Created by Administrator on 2017/11/3 0003.
 */

public class LsFragment extends Fragment implements Runnable, SwipeRefreshLayout.OnRefreshListener  {
    Context context;
    private Handler handler;
    @Bind(R.id.expandableListView)
    ListView expandableListView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.spinner_bm)
    Spinner spinner_bm;
    private PeoplesAdapter expandableAdapter;
    private List<RsdaInfo> infoList=new ArrayList<RsdaInfo>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_da, container,false);
        context=v.getContext();
        ButterKnife.bind(this,v);

        handler = new Handler();
        swipeRefreshLayout.setColorSchemeResources(R.color.deeppink, R.color.darkorange, R.color.mediumblue);
        swipeRefreshLayout.post(this);
        swipeRefreshLayout.setOnRefreshListener(this);//下拉刷新
        infoList=db.findByState(1);
        expandableAdapter=new PeoplesAdapter(context, infoList);
        expandableListView.setAdapter(expandableAdapter);

        swipeRefreshLayout.setRefreshing(false);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(context,PeopleInfoActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("info",infoList.get(i));
                intent.putExtras(mBundle);
                intent.putExtra("state",1);
                startActivity(intent);
            }
        });

//        final List<String> list1 = new ArrayList<String>();
//        list1.add("档案查询");
//        list1.add("退休人员库");
//        list1.add("历史人员库");
//        SpinnerAda rwztAda=new SpinnerAda(list1,this);
//        spinner_zt.setAdapter(rwztAda);
//        spinner_zt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                String title = list1.get(position);
//                if (title.equals("档案查询")) {
//                    infoList=db.findByState(0);
//                    expandableAdapter.updateListView(infoList);
//                    spinner_bm.setVisibility(View.VISIBLE);
//                    tvbm.setVisibility(View.VISIBLE);
//                }else if(title.equals("退休人员库")){
//                    infoList=db.findByState(1);
//                    expandableAdapter.updateListView(infoList);
//                    spinner_bm.setVisibility(View.INVISIBLE);
//                    tvbm.setVisibility(View.INVISIBLE);
//                }else{
//                    infoList=db.findByState(2);
//                    expandableAdapter.updateListView(infoList);
//                    spinner_bm.setVisibility(View.INVISIBLE);
//                    tvbm.setVisibility(View.INVISIBLE);
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
        List<String> list2 = new ArrayList<String>();
        for (int i = 0; i < MyApp.group_infoList.size() ; i++) {
            list2.add(MyApp.group_infoList.get(i).getName());
        }

        SpinnerAda bmAda=new SpinnerAda(list2,context);
        spinner_bm.setAdapter(bmAda);
        spinner_bm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                int ssbm=MyApp.group_infoList.get(position).getId();
                if (ssbm!=0){
                    infoList=db.findByStateAndBm(1,ssbm);
                    expandableAdapter.updateListView(infoList);
                }else{
                    infoList=db.findByState(1);
                    expandableAdapter.updateListView(infoList);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return v;
    }
        @Override
    public void run() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);
    }
}
