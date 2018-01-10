package com.sdzx.government.governmentpersonnel.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.activity.MainActivity;
import com.sdzx.government.governmentpersonnel.activity.MyApp;
import com.sdzx.government.governmentpersonnel.activity.PeopleInfoActivity;
import com.sdzx.government.governmentpersonnel.adapter.ZzjyInfoAdapter;
import com.sdzx.government.governmentpersonnel.bean.RsdaInfo;
import com.sdzx.government.governmentpersonnel.database.GetCS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class GrFragment extends Fragment {
    Context context;
    @Bind(R.id.tv_xm)
    TextView tvXm;
    @Bind(R.id.tv_xb)
    TextView tvXb;
    @Bind(R.id.tv_birth)
    TextView tvBirth;
    @Bind(R.id.tv_mz)
    TextView tvMz;
    @Bind(R.id.tv_jg)
    TextView tvJg;
    @Bind(R.id.tv_birth_addr)
    TextView tvBirthAddr;
    @Bind(R.id.tv_work_time)
    TextView tvWorkTime;
    @Bind(R.id.in_partime)
    TextView inPartime;
    @Bind(R.id.tv_jkqk)
    TextView tvJkqk;
    @Bind(R.id.tv_zyjs_posi)
    TextView tvZyjsPosi;
    @Bind(R.id.tv_zyzc)
    TextView tvZyzc;
    @Bind(R.id.lint)
    LinearLayout lint;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.tv_gzdw)
    TextView tvGzdw;
    @Bind(R.id.tv_zzmm)
    TextView tvZzmm;
    @Bind(R.id.tv_sfz)
    TextView tvSfz;
    @Bind(R.id.tv_ssbm)
    TextView tvSsbm;
    @Bind(R.id.tv_xzjb)
    TextView tvXzjb;
    @Bind(R.id.zjck)
    TextView zjck;
    @Bind(R.id.xl)
    TextView xl;
    @Bind(R.id.byyx)
    TextView byyx;
//    @Bind(R.id.tv_xw)
//    TextView tvxw;
//    @Bind(R.id.xjzy)
//    TextView xjzy;
    @Bind(R.id.mylistview)
    ListView mylistview;

    private RsdaInfo rsdaInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gr, container, false);
        context = v.getContext();
        ButterKnife.bind(this, v);
        init();

        return v;

    }

    public void init() {
        rsdaInfo = PeopleInfoActivity.info;
        tvXm.setText(rsdaInfo.getName());
        tvXb.setText(rsdaInfo.getSex());
        tvBirth.setText(rsdaInfo.getBirth());
        tvMz.setText(rsdaInfo.getNation());
        tvJg.setText(rsdaInfo.getOrigin());
        tvBirthAddr.setText(rsdaInfo.getCsd());
        tvJkqk.setText(rsdaInfo.getHealthy());
        inPartime.setText(rsdaInfo.getRdtime());
        tvWorkTime.setText(rsdaInfo.getWorktime());
        tvGzdw.setText(rsdaInfo.getGzdwzw());
        tvZyjsPosi.setText(rsdaInfo.getZyjszw());
        tvZyzc.setText(rsdaInfo.getSxzyzc());
        String xlsm = "";
        try {
            xlsm = GetCS.getXljbCs(Integer.valueOf(rsdaInfo.getQr_xl()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        xl.setText(xlsm+"\n"+rsdaInfo.getQr_xw());
//        xl.setText(rsdaInfo.getQr_xl());
        byyx.setText(rsdaInfo.getQr_byyx()+rsdaInfo.getQr_xjzy());
//        try {
//            Cursor cursor = MainActivity.db.findJtcyCursor("zzjy_info", rsdaInfo.getId());
//            cursor.moveToLast();
//            tvxw.setText(GetCS.getXljbCs(cursor.getInt(cursor.getColumnIndex("xl"))));
//            xjzy.setText(cursor.getString(cursor.getColumnIndex("byyx"))+cursor.getString(cursor.getColumnIndex("xjzy")));
//        } catch (Exception e) {
//            e.toString();
//        }
        try {
            Cursor cursor = MainActivity.db.findJtcyCursor("zzjy_info", rsdaInfo.getId());
            ZzjyInfoAdapter zzAda = new ZzjyInfoAdapter(context, cursor);
            mylistview.setAdapter(zzAda);
        } catch (Exception e) {
            e.toString();
        }
//        tvxw.setText(rsdaInfo.getQr_xw());
//        xjzy.setText(rsdaInfo.getQr_xjzy());
        String picaddr = rsdaInfo.getTxzp();
        BitmapUtils bitmapUtils = new BitmapUtils(context);
        bitmapUtils.configDefaultLoadingImage(R.mipmap.pic);//默认背景图片
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.pic);//加载失败图片
        bitmapUtils.display(imageView, MyApp.PATH + "/" + picaddr);//加载人员头像
    }

    public void displayPic(String url) {
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
     *
     * @param url
     * @return
     */
    public Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Resources res = getResources();
            Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.unpic);
            return bmp;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
