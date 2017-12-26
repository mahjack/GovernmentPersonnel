package com.sdzx.government.governmentpersonnel.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.activity.MainActivity;
import com.sdzx.government.governmentpersonnel.activity.MyApp;
import com.sdzx.government.governmentpersonnel.activity.PeopleInfoActivity;
import com.sdzx.government.governmentpersonnel.adapter.PxInfoAdapter;
import com.sdzx.government.governmentpersonnel.adapter.TxAdapter;
import com.sdzx.government.governmentpersonnel.adapter.ZzjyInfoAdapter;
import com.sdzx.government.governmentpersonnel.bean.RsdaInfo;
import com.sdzx.government.governmentpersonnel.tools.OpenFiles;

import org.json.JSONArray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class GrFragment extends Fragment{
    Context context;
    @Bind(R.id.tv_birth)
    TextView tv_birth;
    @Bind(R.id.tv_birth_addr)
    TextView tv_birth_addr;
    @Bind(R.id.tv_zzmm)
    TextView tv_zzmm;
    @Bind(R.id.tv_xzjb)
    TextView tv_xzjb;
    @Bind(R.id.tv_sfz)
    TextView tv_sfz;
    @Bind(R.id.in_partime)
    TextView tv_in_partime;
    @Bind(R.id.tv_work_time)
    TextView tv_work_time;
    @Bind(R.id.tv_ssbm)
    TextView tv_ssbm;
    @Bind(R.id.tv_zyjs_posi)
    TextView tv_zyjs_posi;
    @Bind(R.id.tv_zyzc)
    TextView tv_zyzc;
    @Bind(R.id.tv_jkqk)
    TextView tv_jkqk;
    @Bind(R.id.tv_drsj)
    TextView tv_drsj;
    @Bind(R.id.tv_bz)
    TextView tv_bz;//编制
    @Bind(R.id.tv_beizhu)
    TextView tv_beizhu;
    @Bind(R.id.tv_khjg)
    TextView tv_khjg;
    @Bind(R.id.tv_now_posi_time)
    TextView tv_now_posi_time;
    @Bind(R.id.tv_now_posij_time)
    TextView tv_now_posij_time;
    @Bind(R.id.tv_jianli)
    TextView tv_jianli;
    @Bind(R.id.tv_jiangcheng)
    TextView tv_jiangcheng;
    @Bind(R.id.tv_gzdw)
    TextView tv_gzdw;
    @Bind(R.id.xl)
    TextView xl;
    @Bind(R.id.xw)
    TextView xw;
    @Bind(R.id.byyx)
    TextView byyx;
    @Bind(R.id.xjzy)
    TextView xjzy;
    @Bind(R.id.xlzjz)
    TextView xlzjz;
    @Bind(R.id.xwzjz)
    TextView xwlzjz;
    @Bind(R.id.mylistview)
    ListView mylistview;
    @Bind(R.id.mylistviewpx)
    ListView mylistviewpx;
    @Bind(R.id.mylistviewtx)
    ListView mylistviewtx;
    @Bind(R.id.mylistviewdl)
    ListView mylistviewdl;
    @Bind(R.id.xrzfj)
    TextView xrzfj;
    @Bind(R.id.xrzjfj)
    TextView xrzjfj;
    @Bind(R.id.zjck)
    TextView zjck;
    @Bind(R.id.tv_bysj)
    TextView bysj;
    @Bind(R.id.tv_txsj)
    TextView txsj;
    @Bind(R.id.tv_dlsj)
    TextView dlsj;
    @Bind(R.id.linertx)
    LinearLayout linertx;
    @Bind(R.id.linerdl)
    LinearLayout linerdl;
    private RsdaInfo rsdaInfo;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gr, container,false);
        context=v.getContext();
        ButterKnife.bind(this,v);
        init();
        Cursor cursor= MainActivity.db.findJtcyCursor("zzjy_info", rsdaInfo.getId());
        ZzjyInfoAdapter zzAda=new ZzjyInfoAdapter(context,cursor);
        mylistview.setAdapter(zzAda);

        Cursor cursor1= MainActivity.db.findJtcyCursor("rypx_info", rsdaInfo.getId());
        PxInfoAdapter pxAda=new PxInfoAdapter(context,cursor1);
        mylistviewpx.setAdapter(pxAda);


        final String xlzp=rsdaInfo.getQr_xlzp();
        final String xwzp=rsdaInfo.getQr_xwzp();
        final String zjzp=rsdaInfo.getZjzp();

        if (xlzp==null||xlzp.equals("")){
            xlzjz.setTextColor(0xFF626262);
        }
        if (xwzp==null||xwzp.equals("")){
            xwlzjz.setTextColor(0xFF626262);
        }
        if (zjzp==null||zjzp.equals("")){
            zjck.setTextColor(0xFF626262);
        }
        zjck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xlzp==null||"".equals(zjzp)){
                }else{
                    displayPic("/mnt/sdcard/zxkj/"+zjzp);
                }
            }
        });
        xlzjz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xlzp==null||"".equals(xlzp)){
                }else{
                    displayPic("/mnt/sdcard/zxkj/"+xlzp);
                }
            }
        });
        xwlzjz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xwzp==null||"".equals(xwzp)){
                }else{
                    displayPic("/mnt/sdcard/zxkj/"+xwzp);
                }
            }
        });
        xrzfj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=OpenFiles.getWordFileIntent(MyApp.PATH+"/rsda/"+"test.docx");
                startActivity(intent);
            }
        });
        xrzjfj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=OpenFiles.getWordFileIntent(MyApp.PATH+"/rsda/"+"test.docx");
                startActivity(intent);
            }
        });
        return v;

    }
    public void init(){
        try{
            rsdaInfo= PeopleInfoActivity.info;
            String birth=rsdaInfo.getBirth();
            String birth_addr=rsdaInfo.getCsd();
            String zzmm=rsdaInfo.getZzmm();
            String xzjb=rsdaInfo.getXzjb();
            String ssbm=rsdaInfo.getSsbm();
            String jkqk=rsdaInfo.getHealthy();
            String sfz=rsdaInfo.getSfzh();
            String in_partime=rsdaInfo.getRdtime();
            String work_time=rsdaInfo.getWorktime();
            String gzdw=rsdaInfo.getGzdwzw();
            String zyjs=rsdaInfo.getZyjszw();
            String zyzc=rsdaInfo.getSxzyzc();
            String bz=rsdaInfo.getBianzhi();
            String drsj=rsdaInfo.getDrsj();
            String xzsj=rsdaInfo.getXrzsj();
            String xzjsj=rsdaInfo.getXrzjsj();
            String jianli=rsdaInfo.getJianli();
            String jiangcheng=rsdaInfo.getJlqk();
            String khjg=rsdaInfo.getNdkhjg();
            String beizhu=rsdaInfo.getBeizhu();
            if(PeopleInfoActivity.state==1){
                linertx.setVisibility(View.VISIBLE);
            }else if(PeopleInfoActivity.state==2){
                linerdl.setVisibility(View.VISIBLE);
            }
            try {

                String txcl=rsdaInfo.getRetire_attach();
                txcl=txcl.replaceAll("\\\\","");
                JSONArray jsonArray=new JSONArray(txcl);
                TxAdapter txAda=new TxAdapter(context,jsonArray);
                mylistviewtx.setAdapter(txAda);
            }catch (Exception e){

            }
            try{
                String dlcl=rsdaInfo.getLeave_attach();
                dlcl=dlcl.replaceAll("\\\\","");
                JSONArray jsonArr=new JSONArray(dlcl);
                TxAdapter dlAda=new TxAdapter(context,jsonArr);
                mylistviewdl.setAdapter(dlAda);
            }catch (Exception e){
                e.toString();
            }



            tv_birth.setText(birth);
            tv_birth_addr.setText(birth_addr);
            tv_zzmm.setText(zzmm);
            tv_xzjb.setText(xzjb);
            tv_sfz.setText(sfz);
            tv_in_partime.setText(in_partime);
            tv_work_time.setText(work_time);
            tv_jkqk.setText(jkqk);
            tv_zyjs_posi.setText(zyjs);
            tv_zyzc.setText(zyzc);
            tv_ssbm.setText(ssbm);
            tv_drsj.setText(drsj);
            tv_now_posi_time.setText(xzsj);
            tv_now_posij_time.setText(xzjsj);
            tv_bz.setText(bz);
            tv_jianli.setText(jianli);
            tv_jiangcheng.setText(jiangcheng);
            tv_khjg.setText(khjg);
            tv_gzdw.setText(gzdw);
            tv_beizhu.setText(beizhu);
            xl.setText(rsdaInfo.getQr_xl());
            xw.setText(rsdaInfo.getQr_xw());
            byyx.setText(rsdaInfo.getQr_byyx());
            xjzy.setText(rsdaInfo.getQr_xjzy());
            bysj.setText(rsdaInfo.getQr_bysj());
            txsj.setText(rsdaInfo.getRetire_time());
            dlsj.setText(rsdaInfo.getLeave_time());

        }catch(Exception e){

        }
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
    public  Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Resources res=getResources();
            Bitmap bmp=BitmapFactory.decodeResource(res, R.mipmap.unpic);
            return bmp;
        }
    }
}
