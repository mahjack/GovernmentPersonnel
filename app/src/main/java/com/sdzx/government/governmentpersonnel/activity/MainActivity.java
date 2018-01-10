package com.sdzx.government.governmentpersonnel.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.adapter.FragmentAdapter;
import com.sdzx.government.governmentpersonnel.database.DBHelper;
import com.sdzx.government.governmentpersonnel.fragment.DaFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    @Bind(R.id.tv_yh)
    TextView tv_yh;
    @Bind(R.id.glc_tv)
    TextView glc_tv;
    @Bind(R.id.zczz_tv)
    TextView zczz_tv;
    @Bind(R.id.zcfz_tv)
    TextView zcfz_tv;
    @Bind(R.id.id_page_vp)
    ViewPager mPageVp;
    @Bind(R.id.id_tab_line_iv)
    ImageView mTabLineIv;
    @Bind(R.id.id_tab_chat_ll)
    View id_tab_chayan;
    @Bind(R.id.id_tab_friend_ll)
    View id_tab_waiguan;
    @Bind(R.id.id_tab_contacts_ll)
    View id_tab_ziliao;
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
    DaFragment daFragment;//档案查阅
//    TxFragment txFragment;//退休人员
//    LsFragment lsFragment;//历史人员
    public static DBHelper db;//数据库管理类
    private static final String DB_NAME = "sqlite.db";//本地数据库
    public static final int DB_VERSION = 1;//数据库版本
    private long exitTime = 0; // 两次点击相隔时间


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (!readDataBase(MainActivity.this,getPackageName())){
            ShowDialog(this,"本地数据加载失败,请检查设备内存中zxkj文件夹下sqlite.db文件是否存在");

            return;
        }
        try {
            db = new DBHelper(MainActivity.this,DB_NAME, DB_VERSION);
            MyApp.group_infoList=db.findAllCursor("kjj_group_info");
//        MyApp.rsdaInfoList=db.findByState(0);
        }catch (Exception e){
            ShowDialog(this,"查询本地数据失败,请检查设备内存中zxkj文件夹下sqlite.db文件是否正确");
            return;
        }
        mPageVp.setOffscreenPageLimit(2);
        init();
//        initTabLineWidth();
//        listenerClick();

        tv_yh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });

    }

    /**
     * 读取数据库文件
     * @param context
     * @param name
     */
    public Boolean readDataBase(Context context,String name){
        try {
            String DB_PATH = "/data/data/"+name+"/databases/";
            // 检查 SQLite 数据库文件是否存在
            File fileSql=new File(DB_PATH + DB_NAME);
            if (fileSql.exists()){
                fileSql.delete();
            }
            // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
            File f = new File(DB_PATH);
            // 如 database 目录不存在，新建该目录
            if (!f.exists()) {
                f.mkdir();
            }
            File file=new File(MyApp.PATH,DB_NAME);
            FileInputStream is = new FileInputStream(file);
            // 输出流,在指定路径下生成db文件
            OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
            // 文件写入
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            // 关闭文件流
            os.flush();
            os.close();
            is.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private void init() {
        daFragment = new DaFragment();
//        lsFragment = new LsFragment();
//        txFragment=new TxFragment();
        mFragmentList.add(daFragment);
//        mFragmentList.add(lsFragment);
//        mFragmentList.add(txFragment);
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

//        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            /**
//             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
//             */
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//
//            /**
//             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
//             * offsetPixels:当前页面偏移的像素位置
//             */
//            @Override
//            public void onPageScrolled(int position, float offset,
//                                       int offsetPixels) {
//                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
//                        .getLayoutParams();
//
////				Log.e("offset:", offset + "");
//                /**
//                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
//                 * 设置mTabLineIv的左边距 滑动场景：
//                 * 记3个页面,
//                 * 从左到右分别为0,1,2
//                 * 0->1; 1->2; 2->1; 1->0
//                 */
//
//                if (currentIndex == 0 && position == 0)// 0->1
//                {
//                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
//                            * (screenWidth / 3));
//
//                } else if (currentIndex == 1 && position == 0) // 1->0
//                {
//                    lp.leftMargin = (int) (-(1 - offset)
//                            * (screenWidth * 1.0 / 3) + currentIndex
//                            * (screenWidth / 3));
//
//                } else if (currentIndex == 1 && position == 1) // 1->2
//                {
//                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
//                            * (screenWidth / 3));
//                } else if (currentIndex == 2 && position == 1) // 2->1
//                {
//                    lp.leftMargin = (int) (-(1 - offset)
//                            * (screenWidth * 1.0 / 3) + currentIndex
//                            * (screenWidth / 3));
//                }
//                mTabLineIv.setLayoutParams(lp);
//            }
//            @Override
//            public void onPageSelected(int position) {
//                resetTextView();
//                switch (position) {
//                    case 0:
//                        glc_tv.setTextColor(0xFF33B5E5);
//                        break;
//                    case 1:
//                        zczz_tv.setTextColor(0xFF33B5E5);
//                        break;
//                    case 2:
//                        zcfz_tv.setTextColor(0xFF33B5E5);
//                        break;
//                }
//                currentIndex = position;
//            }
//        });

    }
    void listenerClick(){
        id_tab_chayan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                resetTextView();
                glc_tv.setTextColor(0xFF33B5E5);
                mPageVp.setCurrentItem(0);
            }
        });
        id_tab_waiguan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                resetTextView();
                zczz_tv.setTextColor(0xFF33B5E5);
                mPageVp.setCurrentItem(1);
            }
        });
        id_tab_ziliao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                resetTextView();
                zcfz_tv.setTextColor(0xFF33B5E5);
                mPageVp.setCurrentItem(2);
            }
        });
    }
    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(lp);
    }
    /**
     * 重置颜色
     */
    private void resetTextView() {
        glc_tv.setTextColor(Color.BLACK);
        zczz_tv.setTextColor(Color.BLACK);
        zcfz_tv.setTextColor(Color.BLACK);
    }
    /**
     * ShowDialog显示提示对话框
     * @param context
     * @param msg
     */
    public boolean ShowDialog(Context context,String msg ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("系统通知");
        builder.setMessage(msg).setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                moveTaskToBack(false);
                finish();
//                DemoHelper.getInstance().logout(false, null);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}

