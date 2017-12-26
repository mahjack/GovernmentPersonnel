package com.sdzx.government.governmentpersonnel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.bean.RsdaInfo;

import java.util.List;

/**
 * Author：Mark
 * Date：2017/10/26 0026
 * Tell：15006330640
 */

public class PeoplesAdapter extends BaseAdapter{
    Context context;
    List<RsdaInfo> list;
    public PeoplesAdapter(Context context, List<RsdaInfo> list) {
        super();
        this.context = context;
        this.list = list;
    }
    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     * @param list
     */
    public void updateListView(List<RsdaInfo> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.listview_peoples, null);
            holder.txt1 = (TextView) view.findViewById(R.id.textView_name);
            holder.txt2 = (TextView) view.findViewById(R.id.textView_company);
            holder.txt3 = (TextView) view.findViewById(R.id.textView_groupname);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txt1.setText(list.get(position).getName());
        holder.txt2.setText(list.get(position).getOrigin());
        holder.txt3.setText(list.get(position).getXzjb());

        return view;
    }

    public class ViewHolder {
        private TextView txt1;
        private TextView txt2;
        private TextView txt3;
    }
}