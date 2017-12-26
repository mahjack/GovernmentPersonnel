package com.sdzx.government.governmentpersonnel.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;


import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.activity.MainActivity;
import com.sdzx.government.governmentpersonnel.activity.MyApp;
import com.sdzx.government.governmentpersonnel.activity.PeopleInfoActivity;
import com.sdzx.government.governmentpersonnel.adapter.InfoAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class JtFragment extends Fragment{
    Context context;
    @Bind(R.id.gridView)
    GridView gridView;
    @Bind(R.id.tv_no)
    TextView tv_no;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_jt, container,false);
        context=v.getContext();
        ButterKnife.bind(this,v);
        init();
        return v;
    }

    private void init() {
        try {
            Cursor cursor=MainActivity.db.findJtcyCursor("family_info", PeopleInfoActivity.info.getId());

            if(cursor.getCount()<1){
                tv_no.setVisibility(View.VISIBLE);
            }else{
                tv_no.setVisibility(View.GONE);
            }
            InfoAdapter infoAda=new InfoAdapter(context,cursor);
            gridView.setAdapter(infoAda);
        } catch (Exception e) {
            tv_no.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }
    }
}
