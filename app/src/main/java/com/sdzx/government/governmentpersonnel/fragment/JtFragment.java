package com.sdzx.government.governmentpersonnel.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.activity.MainActivity;
import com.sdzx.government.governmentpersonnel.activity.PeopleInfoActivity;
import com.sdzx.government.governmentpersonnel.adapter.InfoAdapter;
import com.sdzx.government.governmentpersonnel.bean.RsdaInfo;
import com.sdzx.government.governmentpersonnel.tools.MyListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class JtFragment extends Fragment {
    Context context;
    @Bind(R.id.mylistviewjt)
    MyListView mylistviewjt;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.tv_beizhu)
    TextView tvBeizhu;
    private RsdaInfo rsdaInfo;
    InfoAdapter infoAda;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_jt, container, false);
        context = v.getContext();
        ButterKnife.bind(this, v);
        init();
        return v;
    }

    private void init() {
        rsdaInfo = PeopleInfoActivity.info;
        tvBeizhu.setText(rsdaInfo.getBeizhu());
        try {
            Cursor cursor_jt = MainActivity.db.findJtcyCursor("family_info", rsdaInfo.getId());
            infoAda = new InfoAdapter(context, cursor_jt);
            mylistviewjt.setAdapter(infoAda);
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
