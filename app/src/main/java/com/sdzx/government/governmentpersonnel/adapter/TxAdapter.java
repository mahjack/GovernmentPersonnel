package com.sdzx.government.governmentpersonnel.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.activity.MyApp;
import com.sdzx.government.governmentpersonnel.tools.OpenFiles;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Author：Mark
 * Date：2017/10/26 0026
 * Tell：15006330640
 */

public class TxAdapter extends BaseAdapter{
    Context context;
    JSONArray jsonArray;
//    int size=18;

    public TxAdapter(Context context, JSONArray jsonArray) {
        this.context = context;
        this.jsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return jsonArray.get(i);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
//    public void updateListView(int size){
//        this.size = size;
//        notifyDataSetChanged();
//    }

    @Override
    public View getView(final  int position, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.listview_tx, null);
            holder.txt1 = (Button) view.findViewById(R.id.texttx);
            holder.txt2 = (TextView) view.findViewById(R.id.texttxaddr);
            holder.txt3 = (TextView) view.findViewById(R.id.fj);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            holder.txt1.setText(jsonArray.getJSONObject(position).getString("origin"));
            holder.txt2.setText(jsonArray.getJSONObject(position).getString("uri"));
//            holder.txt3.setTextSize(size);
            holder.txt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= null;

                    try {
                        String uri=jsonArray.getJSONObject(position).getString("uri");
                        if (uri.contains("jpg")||uri.contains("jpeg")||uri.contains("png")){
                            displayPic(MyApp.PATH+"/"+uri);
                        }else {

                            intent = OpenFiles.getWordFileIntent(MyApp.PATH+"/"+jsonArray.getJSONObject(position).getString("uri"));
                            context.startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    public class ViewHolder {
        private Button txt1;
        private TextView txt2;
        private TextView txt3;
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
}
