package com.sdzx.government.governmentpersonnel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.adapter.PeoplesAdapter;
import com.sdzx.government.governmentpersonnel.bean.RsdaInfo;
import com.sdzx.government.governmentpersonnel.search.CharacterParser;
import com.sdzx.government.governmentpersonnel.search.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class SearchActivity extends Activity{
    @Bind(R.id.filter_edit)
    ClearEditText editText;
    @Bind(R.id.peolv)
    ListView peolistview;
    @Bind(R.id.header_back)
    TextView header_back;
//    List<Contacts.People> peoples=new ArrayList<>();
    PeoplesAdapter peoplesAdapter;
    private List<RsdaInfo> infoList=new ArrayList<RsdaInfo>();

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        infoList=MainActivity.db.findAll();
        peolistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(SearchActivity.this,PeopleInfoActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("info",infoList.get(i));
                intent.putExtra("state",0);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        //根据输入框输入值的改变来过滤搜索
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<RsdaInfo> filterDateList = new ArrayList<RsdaInfo>();

        if(TextUtils.isEmpty(filterStr)){
//            filterDateList ;
        }else{
            filterDateList.clear();
            for(RsdaInfo People : infoList){
                String name = People.getName();
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(People);
                }
            }
        }

        peoplesAdapter=new PeoplesAdapter(SearchActivity.this,filterDateList);
        peolistview.setAdapter(peoplesAdapter);
    }
}
