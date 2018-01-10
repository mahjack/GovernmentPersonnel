package com.sdzx.government.governmentpersonnel.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.adapter.FragmentAdapter;
import com.sdzx.government.governmentpersonnel.adapter.InfoAdapter;
import com.sdzx.government.governmentpersonnel.adapter.PxInfoAdapter;
import com.sdzx.government.governmentpersonnel.adapter.TxAdapter;
import com.sdzx.government.governmentpersonnel.adapter.ZzjyInfoAdapter;
import com.sdzx.government.governmentpersonnel.bean.RsdaInfo;
import com.sdzx.government.governmentpersonnel.database.GetCS;
import com.sdzx.government.governmentpersonnel.fragment.GrFragment;
import com.sdzx.government.governmentpersonnel.fragment.JlFragment;
import com.sdzx.government.governmentpersonnel.fragment.JtFragment;
import com.sdzx.government.governmentpersonnel.tools.DragImageView;

import org.json.JSONArray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Mark
 * Date：2017/10/26 0026
 * Tell：15006330640
 */

/**
 *
 */

public class PeopleInfoActivity extends FragmentActivity {
    @Bind(R.id.imageView)
    ImageView imageView;//头像
    @Bind(R.id.header_back)
    TextView header_back;//返回
    @Bind(R.id.tefd)
    TextView tefd;//放大
    @Bind(R.id.mylistview)
    ListView mylistview;//在职
    @Bind(R.id.mylistviewjt)
    ListView mylistviewjt;//家庭成员
    @Bind(R.id.mylistviewpx)
    ListView mylistviewpx;//培训
    @Bind(R.id.mylistviewtx)
    ListView mylistviewtx;//退休
    @Bind(R.id.mylistviewdl)
    ListView mylistviewdl;//调离

    //人员信息
    @Bind(R.id.tv_xm)
    TextView tv_name;
    @Bind(R.id.tv_jg)
    TextView tv_jg;
    @Bind(R.id.tv_mz)
    TextView tv_mz;
    @Bind(R.id.tv_xb)
    TextView tv_xb;
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
    TextView tv_beizhu;//备注
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
    @Bind(R.id.tv_bysj)
    TextView xw;
    @Bind(R.id.byyx)
    TextView byyx;
    @Bind(R.id.xjzy)
    TextView xjzy;
    //    @Bind(R.id.xlzjz)
//    TextView xlzjz;
//    @Bind(R.id.xwzjz)
//    TextView xwlzjz;
    @Bind(R.id.xrzfj)
    TextView xrzfj;
    @Bind(R.id.xrzjfj)
    TextView xrzjfj;
    @Bind(R.id.zjck)
    TextView zjck;
    //    @Bind(R.id.tv_bysj)
//    TextView bysj;
    @Bind(R.id.tv_txsj)
    TextView txsj;
    @Bind(R.id.tv_dlsj)
    TextView dlsj;
    //父类布局
    @Bind(R.id.linertx)
    LinearLayout linertx;
    @Bind(R.id.linerdl)
    LinearLayout linerdl;
    @Bind(R.id.linz)
    LinearLayout linz;

    @Bind(R.id.lint)
    LinearLayout lint;

    public static RsdaInfo info;
    public static int state;
    int size = 0;

    @Bind(R.id.gr_tv)
    TextView gr_tv;
    @Bind(R.id.jt_tv)
    TextView jt_tv;
    @Bind(R.id.jl_tv)
    TextView jl_tv;
    @Bind(R.id.tab_line)
    ImageView tab_line;
    @Bind(R.id.tab_gr)
    View tab_gr;
    @Bind(R.id.tab_jl)
    View tab_jl;
    @Bind(R.id.tab_jt)
    View tab_jt;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.img)
    DragImageView img;
    JlFragment jlFragment;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    GrFragment grFragment;
    JtFragment jtFragment;
    ZzjyInfoAdapter zzAda;
    InfoAdapter infoAda;
    PxInfoAdapter pxAda;
    TxAdapter dlAda;
    TxAdapter txAda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        info = (RsdaInfo) intent.getSerializableExtra("info");//人员详细信息
        state = intent.getIntExtra("state", 0);//人员分类
        getInfo();//加载人员信息
        tefd.setText("");
//        tefd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                initFragment();
//
//            }
//        });
        //返回按钮
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    /**
     * 加载人员信息
     */
    public void getInfo() {
        try {
            tv_name.setText(info.getName());//姓名
            tv_jg.setText(info.getOrigin());//籍贯
            tv_mz.setText(info.getNation());//民族
            tv_xb.setText(info.getSex());//性别
            String picaddr = info.getTxzp();
            BitmapUtils bitmapUtils = new BitmapUtils(PeopleInfoActivity.this);
            bitmapUtils.configDefaultLoadingImage(R.mipmap.unknow);//默认背景图片
            bitmapUtils.configDefaultLoadFailedImage(R.mipmap.unknow);//加载失败图片
            bitmapUtils.display(imageView, MyApp.PATH + "/" + picaddr);//加载人员头像
            String birth = info.getBirth();
            String birth_addr = info.getCsd();
            String zzmm = info.getZzmm();
            String xzjb = info.getXzjb();
            String ssbm = info.getSsbm();
            String jkqk = info.getHealthy();
            String sfz = info.getSfzh();
            String in_partime = info.getRdtime();
            String work_time = info.getWorktime();
            String gzdw = info.getGzdwzw();
            String zyjs = info.getZyjszw();
            String zyzc = info.getSxzyzc();
            String bz = info.getBianzhi();
            String drsj = info.getDrsj();
            String xzsj = info.getXrzsj();
            String xzjsj = info.getXrzjsj();
            String jianli = info.getJianli();
            String jiangcheng = info.getJlqk();
            String khjg = info.getNdkhjg();
            String beizhu = info.getBeizhu();
            //判断人员分类 退休显示退休时间，调离显示调离时间
            if (state == 1) {
                linertx.setVisibility(View.VISIBLE);
            } else if (state == 2) {
                linerdl.setVisibility(View.VISIBLE);
            }
            //退休材料集合
            try {
                String txcl = info.getRetire_attach();
                txcl = txcl.replaceAll("\\\\", "");
                JSONArray jsonArray = new JSONArray(txcl);
                txAda = new TxAdapter(PeopleInfoActivity.this, jsonArray);
                mylistviewtx.setAdapter(txAda);
            } catch (Exception e) {

            }
            //调离材料集合
            try {
                String dlcl = info.getLeave_attach();
                dlcl = dlcl.replaceAll("\\\\", "");
                JSONArray jsonArr = new JSONArray(dlcl);
                dlAda = new TxAdapter(PeopleInfoActivity.this, jsonArr);
                mylistviewdl.setAdapter(dlAda);
            } catch (Exception e) {
                e.toString();
            }
            try {
                Cursor cursor = MainActivity.db.findJtcyCursor("zzjy_info", info.getId());
                zzAda = new ZzjyInfoAdapter(this, cursor);
                mylistview.setAdapter(zzAda);
            } catch (Exception e) {
                e.toString();
            }
            try {
                Cursor cursor1 = MainActivity.db.findJtcyCursor("rypx_info", info.getId());
                pxAda = new PxInfoAdapter(this, cursor1);
                mylistviewpx.setAdapter(pxAda);
            } catch (Exception e) {
                e.toString();
            }
            try {
                Cursor cursor_jt = MainActivity.db.findJtcyCursor("family_info", info.getId());
                infoAda = new InfoAdapter(this, cursor_jt);
                mylistviewjt.setAdapter(infoAda);
            } catch (Exception e) {
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
            String xlsm = "";
            try {
                xlsm = GetCS.getXljbCs(Integer.valueOf(info.getQr_xl()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            xl.setText(xlsm);
            xw.setText(info.getQr_xw());
            byyx.setText(info.getQr_byyx());
            xjzy.setText(info.getQr_xjzy());
//            bysj.setText(info.getQr_bysj());
            txsj.setText(info.getRetire_time());
            dlsj.setText(info.getLeave_time());
            initFragment();
        } catch (Exception e) {
            Toast.makeText(PeopleInfoActivity.this, "数据获取错误", Toast.LENGTH_SHORT).show();
        }

    }
    private Fragment[] fragments;
    private TextView[] textViews;

    private int index;
    private int fragment_index = 0;
    private void initFragment() {
        grFragment = new GrFragment();
        jlFragment = new JlFragment();
        jtFragment = new JtFragment();

        fragments = new Fragment[]{grFragment, jlFragment, jtFragment};

        textViews = new TextView[]{gr_tv, jl_tv, jt_tv};
        textViews[0].setTextColor(getResources().getColor(R.color.main_color));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content, grFragment)
                .add(R.id.main_content, jlFragment)
                .add(R.id.main_content, jtFragment)
                .hide(jlFragment).hide(jtFragment)
                .show(grFragment).commit();
    }


    @OnClick({R.id.tab_gr, R.id.tab_jl, R.id.tab_jt}) void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_gr:
                index = 0;
                break;
            case R.id.tab_jl:
                index = 1;
                break;
            case R.id.tab_jt:
                index = 2;
                break;

        }

        if (fragment_index != index) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragments[fragment_index]);
            if (!fragments[index].isAdded()) {
                transaction.add(R.id.main_content, fragments[index]);
            }
            transaction.show(fragments[index]).commit();
        }
        textViews[fragment_index].setTextColor(getResources().getColor(R.color.text_color_bottom));
        textViews[index].setTextColor(getResources().getColor(R.color.main_color));
        fragment_index = index;
    }



    public void displayPic(String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PeopleInfoActivity.this);
        builder.setTitle("查看图片");
        LayoutInflater inflater = LayoutInflater.from(PeopleInfoActivity.this);
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

    public void setTSize(int Size2) {
        tv_name.setTextSize(Size2);
        tv_jg.setTextSize(Size2);
        tv_mz.setTextSize(Size2);
        tv_xb.setTextSize(Size2);
        tv_birth.setTextSize(Size2);
        tv_birth_addr.setTextSize(Size2);
        tv_zzmm.setTextSize(Size2);
        tv_xzjb.setTextSize(Size2);
        tv_sfz.setTextSize(Size2);
        tv_in_partime.setTextSize(Size2);
        tv_work_time.setTextSize(Size2);
        tv_ssbm.setTextSize(Size2);
        tv_zyjs_posi.setTextSize(Size2);
        tv_zyzc.setTextSize(Size2);
        tv_jkqk.setTextSize(Size2);
        tv_drsj.setTextSize(Size2);
        tv_bz.setTextSize(Size2);
        tv_beizhu.setTextSize(Size2);
        tv_khjg.setTextSize(Size2);
        tv_now_posi_time.setTextSize(Size2);
        tv_now_posij_time.setTextSize(Size2);
        tv_jianli.setTextSize(Size2);
        tv_jiangcheng.setTextSize(Size2);
        xl.setTextSize(Size2);
//        xw.setTextSize(Size2);
        byyx.setTextSize(Size2);
        xjzy.setTextSize(Size2);
//        xwlzjz.setTextSize(Size2);
        xrzjfj.setTextSize(Size2);
        zjck.setTextSize(Size2);
//        bysj.setTextSize(Size2);
        txsj.setTextSize(Size2);
        dlsj.setTextSize(Size2);
    }

    public void setSizeAll(LinearLayout ll, int size) {
        //遍历LinearLayout中的元素
        for (int i = 0; i < ll.getChildCount(); i++) {
            if (ll.getChildAt(i) instanceof TextView) {
                //如果是TextView，做你需要的处理
                TextView te = (TextView) ll.getChildAt(i);
                te.setTextSize(size);
            } else if (ll.getChildAt(i) instanceof ViewGroup) {
                ViewGroup vp = (ViewGroup) ll.getChildAt(i);
                for (int j = 0; j < vp.getChildCount(); j++) {
                    if (vp.getChildAt(j) instanceof TextView) {
                        TextView te = (TextView) vp.getChildAt(j);
                        te.setTextSize(size);
                    } else if (vp.getChildAt(j) instanceof ViewGroup) {
                        ViewGroup vp1 = (ViewGroup) vp.getChildAt(j);
                        for (int k = 0; k < vp1.getChildCount(); k++) {
                            if (vp1.getChildAt(k) instanceof TextView) {
                                TextView te = (TextView) vp1.getChildAt(k);
                                te.setTextSize(size);
                            } else if (vp1.getChildAt(k) instanceof ViewGroup) {
                                ViewGroup vp2 = (ViewGroup) vp1.getChildAt(k);
                                for (int l = 0; l < vp2.getChildCount(); l++) {
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
