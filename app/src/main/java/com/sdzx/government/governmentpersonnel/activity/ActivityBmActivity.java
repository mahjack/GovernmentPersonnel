//package com.sdzx.government.governmentpersonnel.activity;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.ExpandableListView;
//
//import com.sdzx.government.governmentpersonnel.R;
//import com.sdzx.government.governmentpersonnel.adapter.ExpandableAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//public class ActivityBmActivity extends Activity  {
//
//    @Bind(R.id.expandableListView) ExpandableListView expandableListView;
//    private List<String> GroupData=new ArrayList<>();//第一级数据
//    private List<String> GroupDataId=new ArrayList<>();//第一级数据ID
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bm);
//        ButterKnife.bind(this);
//        for (int i = 0; i < MyApp.group_infoList.size(); i++) {
//            if ("0".equals(MyApp.group_infoList.get(i).getPid())) {
//                GroupData.add(MyApp.group_infoList.get(i).getName());
//                GroupDataId.add(MyApp.group_infoList.get(i).getId()+"");
//            }
//        }
//        ExpandableAdapter expandableAdapter=new ExpandableAdapter();
//        expandableListView.setAdapter(expandableAdapter );
//
//
//    }
//
//}
