package com.sdzx.government.governmentpersonnel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sdzx.government.governmentpersonnel.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class SpinnerAda1 extends BaseAdapter {
    private Map<Integer, String> map=new HashMap<>();
    private Context context;

    public SpinnerAda1(Map<Integer, String> map, Context context) {
        this.map = map;
        this.context = context;
    }

    @Override
    public int getCount() {
        return map.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.spinner_item, null);
            holder.txt1 = (TextView) view.findViewById(R.id.textView_item);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            holder.txt1.setText(map.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    public class ViewHolder {
        private TextView txt1;
    }
}
