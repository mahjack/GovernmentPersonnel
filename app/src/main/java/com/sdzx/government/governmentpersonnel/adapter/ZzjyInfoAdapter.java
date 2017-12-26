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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.database.GetCS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Author：Mark
 * Date：2017/10/26 0026
 * Tell：15006330640
 */

public class ZzjyInfoAdapter extends BaseAdapter{
    Context context;
    Cursor cursor;
    int size=18;

    public ZzjyInfoAdapter(Context context, Cursor cursor) {
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
            view = View.inflate(context, R.layout.listview_zzjy, null);
            holder.txt1 = (TextView) view.findViewById(R.id.xl);
            holder.txt2 = (TextView) view.findViewById(R.id.xw);
            holder.txt3 = (TextView) view.findViewById(R.id.byyx);
            holder.txt4 = (TextView) view.findViewById(R.id.xjzy);
            holder.txt5 = (Button) view.findViewById(R.id.xlzjz);
            holder.txt6 = (Button) view.findViewById(R.id.xwzjz);
            holder.txt7 = (TextView) view.findViewById(R.id.te_bysj);
            holder.txt8 = (LinearLayout) view.findViewById(R.id.linzz);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
//            String cc=cursor.getString(cursor.getColumnIndex("cw"));
//            holder = (ViewHolder) view.getTag();
//            cursor.moveToNext();
            cursor.moveToPosition(position);
            String slsm="";
            try {
                slsm= GetCS.getXljbCs(cursor.getInt(cursor.getColumnIndex("xl")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.txt1.setText(slsm);
//            holder.txt2.setText(cursor.getString(cursor.getColumnIndex("xw")));
            holder.txt3.setText(cursor.getString(cursor.getColumnIndex("byyx"))+"");
            holder.txt4.setText(cursor.getString(cursor.getColumnIndex("xjzy"))+"");
            holder.txt7.setText(cursor.getString(cursor.getColumnIndex("bysj"))+"");

            final String xlzj=cursor.getString(cursor.getColumnIndex("xlzp"));
            final String xwzj=cursor.getString(cursor.getColumnIndex("xwzp"));
            if (xlzj==null||xlzj.equals("")){
                holder.txt5.setTextColor(0xFF626262);
            }else {
                holder.txt5.setTextColor(0xFF1487df);
            }
            if (xwzj==null||xwzj.equals("")){
                holder.txt6.setTextColor(0xFF626262);
            }else {
                holder.txt5.setTextColor(0xFF1487df);
            }
//            setSizeAll(holder.txt8,size);

            holder.txt5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (xlzj!=null&&!xlzj.equals("")){

                        displayPic("/mnt/sdcard/zxkj/"+xlzj);
                    }
                }
            });
            holder.txt6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (xwzj!=null&&!xwzj.equals(""))
                    displayPic("/mnt/sdcard/zxkj/"+xwzj);
                }
            });
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
        private Button txt5;
        private Button txt6;
        private TextView txt7;
        private LinearLayout txt8;
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
//        Log.e("字体放大","size");

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
                            }else if(vp1.getChildAt(k) instanceof ViewGroup){
                                ViewGroup vp2=(ViewGroup) vp1.getChildAt(k);
                                for (int l = 0; l <vp2.getChildCount() ; l++) {
                                    if (vp2.getChildAt(l) instanceof TextView) {
                                        TextView te = (TextView) vp2.getChildAt(l);
                                        te.setTextSize(size);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
