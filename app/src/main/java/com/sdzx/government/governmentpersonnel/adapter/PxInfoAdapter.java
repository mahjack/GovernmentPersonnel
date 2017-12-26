package com.sdzx.government.governmentpersonnel.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdzx.government.governmentpersonnel.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Author：Mark
 * Date：2017/10/26 0026
 * Tell：15006330640
 */

public class PxInfoAdapter extends BaseAdapter{
    Context context;
    Cursor cursor;
    int size=18;

    public PxInfoAdapter(Context context, Cursor cursor) {
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
            view = View.inflate(context, R.layout.listview_px, null);
            holder.txt1 = (TextView) view.findViewById(R.id.pxxm);
            holder.txt2 = (TextView) view.findViewById(R.id.pxrq);
            holder.txt3 = (TextView) view.findViewById(R.id.pxxs);
            holder.txt4 = (TextView) view.findViewById(R.id.pxdx);
            holder.txt5 = (TextView) view.findViewById(R.id.pxd);
            holder.txt6 = (TextView) view.findViewById(R.id.pxxings);
            holder.txt7 = (TextView) view.findViewById(R.id.pxjf);
            holder.txt8 = (TextView) view.findViewById(R.id.cjpc);
            holder.txt9 = (TextView) view.findViewById(R.id.pxcj);
            holder.txt10 = (TextView) view.findViewById(R.id.pxdw);
            holder.lin = (LinearLayout) view.findViewById(R.id.linpx);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
//            String cc=cursor.getString(cursor.getColumnIndex("cw"));
            cursor.moveToPosition(position);
            holder.txt1.setText(cursor.getString(cursor.getColumnIndex("pxxm")));
            holder.txt2.setText(cursor.getString(cursor.getColumnIndex("pxrq")));
            holder.txt3.setText(cursor.getString(cursor.getColumnIndex("hour")));
            holder.txt4.setText(cursor.getString(cursor.getColumnIndex("pxdx")));
            holder.txt5.setText(cursor.getString(cursor.getColumnIndex("pxdd")));
            holder.txt6.setText(cursor.getString(cursor.getColumnIndex("pxxs")));
            holder.txt7.setText(cursor.getString(cursor.getColumnIndex("jf")));
            holder.txt8.setText(cursor.getString(cursor.getColumnIndex("cjpc")));
            holder.txt9.setText(cursor.getString(cursor.getColumnIndex("cj")));
            holder.txt10.setText(cursor.getString(cursor.getColumnIndex("pxdw")));
            setSizeAll(holder.lin,size);


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
        private TextView txt6;
        private TextView txt7;
        private TextView txt8;
        private TextView txt9;
        private TextView txt10;
        private LinearLayout lin;
    }
    public void displayPic(String url){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("查看图片");
        LayoutInflater inflater = LayoutInflater.from(context);
        View diaView = inflater.inflate(R.layout.dlg_view, null);
        ((ImageView) diaView.findViewById(R.id.bigPic))
                .setImageBitmap(getLoacalBitmap(url));
        builder.setView(diaView);
        builder.setNegativeButton("返回", null);
        builder.show();
    }
    /**
     * 加载本地图片
     * @param url
     * @return
     */
    public Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Resources res=context.getResources();
            Bitmap bmp=BitmapFactory.decodeResource(res, R.mipmap.unpic);
            return bmp;
        }
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
