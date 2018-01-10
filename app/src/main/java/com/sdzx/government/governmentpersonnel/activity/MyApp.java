package com.sdzx.government.governmentpersonnel.activity;

import android.content.Context;
import android.content.res.Configuration;

import com.sdzx.government.governmentpersonnel.bean.Group_info;
import com.sdzx.government.governmentpersonnel.bean.RsdaInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class MyApp {

    public MyApp() {
    }

    /**用户ID*/
    public static String UID="1";
    /**用户名*/
    public static String UNAME="";
    /**人员信息*/
    public static List<RsdaInfo> rsdaInfoList=new ArrayList<RsdaInfo>();
    /**部门信息*/
    public static List<Group_info> group_infoList=new ArrayList<Group_info>();
    /**文件路径*/
    public static String PATH="/mnt/sdcard/zxkj";
    public static String bmname="选择部门";
    public static String bmid="0";
    public static String xlname="学历不限";
    public static String xlid="0";
    public static String jbname="级别不限";
    public static String jbid="0";
    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
