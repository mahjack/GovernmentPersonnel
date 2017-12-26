package com.sdzx.government.governmentpersonnel.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.database.GetCS;


/**
 * Author：Mark
 * Date：2017/10/26 0026
 * Tell：15006330640
 */

public class InfoAdapter extends BaseAdapter{
    Context context;
    Cursor cursor;
    int size=18;

    public InfoAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int i) {
       return cursor.getColumnName(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public void updateListView(int size){
        this.size = size;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.gv_jt, null);
            holder.txt1 = (TextView) view.findViewById(R.id.gv_gx);
            holder.txt2 = (TextView) view.findViewById(R.id.gv_xm);
            holder.txt3 = (TextView) view.findViewById(R.id.gv_nl);
            holder.txt4 = (TextView) view.findViewById(R.id.gv_zzmm);
            holder.txt5 = (TextView) view.findViewById(R.id.gv_dw);
            holder.lin1 = (LinearLayout) view.findViewById(R.id.lingv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
//            String cc=cursor.getString(cursor.getColumnIndex("cw"));
//            cursor.moveToNext();
            cursor.moveToPosition(position);
            holder.txt1.setText(cursor.getString(cursor.getColumnIndex("cw")));
            holder.txt2.setText(cursor.getString(cursor.getColumnIndex("xm")));
            holder.txt3.setText(cursor.getString(cursor.getColumnIndex("csrq")));
            String zzmm=GetCS.getZzmmCs(cursor.getInt(cursor.getColumnIndex("zzmm")));
            holder.txt4.setText(zzmm);
            holder.txt5.setText(cursor.getString(cursor.getColumnIndex("gzdw")));
//            setSizeAll(holder.lin1,size);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    public class ViewHolder {
        private TextView txt1;
        private TextView txt2;
        private TextView txt3;
        private TextView txt4;
        private TextView txt5;
        private LinearLayout lin1;
    }
    public void setSizeAll(LinearLayout ll, int size){
        //遍历LinearLayout中的元素
        for (int i = 0; i < ll.getChildCount(); i++) {
            if (ll.getChildAt(i) instanceof TextView) {
                //如果是TextView，做你需要的处理
                TextView te=(TextView) ll.getChildAt(i);
                te.setTextSize(size);
            }else if (ll.getChildAt(i) instanceof ViewGroup){
                ViewGroup vp = (ViewGroup) ll.getChildAt(i);
                for (int j = 0; j <vp.getChildCount() ; j++) {
                    if (vp.getChildAt(j) instanceof TextView){
                        TextView te=(TextView) vp.getChildAt(j);
                        te.setTextSize(size);
                    }else if (vp.getChildAt(j) instanceof ViewGroup){
                        ViewGroup vp1 = (ViewGroup) vp.getChildAt(j);
                        for (int k = 0; k < vp1.getChildCount(); k++) {
                            if (vp1.getChildAt(k) instanceof TextView) {
                                TextView te = (TextView) vp1.getChildAt(k);
                                te.setTextSize(size);
                            }
                        }
                    }
                }
            }
        }
    }
}
