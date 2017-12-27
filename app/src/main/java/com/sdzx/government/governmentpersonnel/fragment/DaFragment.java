package com.sdzx.government.governmentpersonnel.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.activity.MyApp;
import com.sdzx.government.governmentpersonnel.activity.PeopleInfoActivity;
import com.sdzx.government.governmentpersonnel.activity.SlectManDialog;
import com.sdzx.government.governmentpersonnel.adapter.PeoplesAdapter;
import com.sdzx.government.governmentpersonnel.adapter.SpinnerAda1;
import com.sdzx.government.governmentpersonnel.bean.RsdaInfo;
import com.sdzx.government.governmentpersonnel.database.GetCS;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.sdzx.government.governmentpersonnel.activity.MainActivity.db;

/**
 * Created by Administrator on 2017/11/3 0003.
 */

public class DaFragment extends Fragment implements Runnable, SwipeRefreshLayout.OnRefreshListener {
    Context context;
    @Bind(R.id.text_bz)
    EditText textBz;
    @Bind(R.id.text_cx)
    Button textCx;
    private Handler handler;
    @Bind(R.id.expandableListView)
    ListView expandableListView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.spinner_bm)
    TextView spinner_bm;
    @Bind(R.id.spinner_xb)
    Spinner spinner_xb;
    @Bind(R.id.spinner_xl)
    Spinner spinner_xl;
    @Bind(R.id.spinner_jb)
    Spinner spinner_jb;
    @Bind(R.id.spinner_zzmm)
    Spinner spinner_zzmm;
    @Bind(R.id.message)
    TextView message;
    private List<String> GroupData=new ArrayList<>();//第一级数据
    private List<String> GroupDataId=new ArrayList<>();//第一级数据ID
    private List<String> ChildData=new ArrayList<>();//第一级数据
    private List<String> ChildDataId=new ArrayList<>();//第一级数据ID

    String ssbm = "0";
    String jb = "0";
    String zzmm = "0";
    String xb = "0";
    String xl = "0";
    String bz = "0";
    private SlectManDialog dialog;

    private PeoplesAdapter expandableAdapter;
    private List<RsdaInfo> infoList = new ArrayList<RsdaInfo>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 隐藏键盘
        View v = inflater.inflate(R.layout.fragment_da, container, false);
        context = v.getContext();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ButterKnife.bind(this, v);
        handler = new Handler();
        swipeRefreshLayout.setColorSchemeResources(R.color.deeppink, R.color.darkorange, R.color.mediumblue);
        swipeRefreshLayout.post(this);
        swipeRefreshLayout.setOnRefreshListener(this);//下拉刷新
        infoList = db.findAll();
        message.setText("当前查询到"+infoList.size()+"人");
        expandableAdapter = new PeoplesAdapter(context, infoList);
        expandableListView.setAdapter(expandableAdapter);
        textBz.setCursorVisible(false);
        swipeRefreshLayout.setRefreshing(false);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, PeopleInfoActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("info", infoList.get(i));
                intent.putExtra("state", 0);
                intent.putExtras(mBundle);
                startActivity(intent);

            }
        });

        List<String> list2 = new ArrayList<String>();
        for (int i = 0; i < MyApp.group_infoList.size(); i++) {
            list2.add(MyApp.group_infoList.get(i).getName());
        }

//        SpinnerAda bmAda = new SpinnerAda(list2, context);
//        spinner_bm.setAdapter(bmAda);
//        spinner_bm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                int ssbmnum = MyApp.group_infoList.get(position).getId();
//                ssbm = ssbmnum + "";
//                if (ssbmnum != 0) {
//                    infoList = db.findByStateAndBm(0, ssbmnum);
//                    expandableAdapter.updateListView(infoList);
//                    message.setText("当前查询到"+infoList.size()+"人");
//                } else {
//                    infoList = db.findByState(0);
//                    expandableAdapter.updateListView(infoList);
//                    message.setText("当前查询到"+infoList.size()+"人");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
        spinner_bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new SlectManDialog(context);
                dialog.builder()
                        .setTitle("请选择")
                        .setCancelable(true)
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                spinner_bm.setText(MyApp.bmname);
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });


        SpinnerAda1 xbAda = new SpinnerAda1(GetCS.xbMap, context);
        spinner_xb.setAdapter(xbAda);
        spinner_xb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                xb = position+"";
                Log.v("xingbie", xb + "");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        SpinnerAda1 xlAda = new SpinnerAda1(GetCS.xlMap, context);
        spinner_xl.setAdapter(xlAda);
        spinner_xl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                xl=position+"";
                Log.v("xingbie", xl + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        SpinnerAda1 jbAda = new SpinnerAda1(GetCS.xzjbMap, context);
        spinner_jb.setAdapter(jbAda);
        spinner_jb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                jb = position+"";
                Log.v("xingbie", jb + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        SpinnerAda1 zzmmAda = new SpinnerAda1(GetCS.zzmmMap, context);
        spinner_zzmm.setAdapter(zzmmAda);
        spinner_zzmm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                zzmm = position+"";
                Log.v("xingbie", zzmm + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        textCx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoList = db.findByCs(ssbm,jb,zzmm,xb,xl,textBz.getText().toString());
                expandableAdapter.updateListView(infoList);
                message.setText("当前查询到"+infoList.size()+"人");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
