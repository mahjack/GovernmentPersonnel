package com.sdzx.government.governmentpersonnel.database;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数查询表
 */

public class GetCS {

    public static String getSexCs(int dm){
        String sex="男";
        switch (dm){
            case 1:
                sex="男";
                break;
            case 2:
                sex="女";
                break;
        }
        return sex;
    }
    public static String getZzmmCs(int dm){
        String zzmm="群众";
        switch (dm){
            case 1: zzmm="中共党员";break;
            case 2: zzmm="中共预备党员";break;
            case 3: zzmm="共青团员";break;
            case 4: zzmm="民革会员";break;
            case 5: zzmm="民盟盟员";break;
            case 6: zzmm="民建会员";break;
            case 7: zzmm="民进会员";break;
            case 8: zzmm="农工党党员";break;
            case 9: zzmm="致公党党员";break;
            case 10: zzmm="九三学社社员";break;
            case 11: zzmm="台盟盟员";break;
            case 12: zzmm="无党派民主人士";break;
            case 13: zzmm="群众";break;
        }
        return zzmm;
    }
    public static String getXljbCs(int dm){
        String xljb="专科以下";
        switch (dm){
            case 1: xljb="博士研究生";break;
            case 2: xljb="硕士研究生";break;
            case 3: xljb="本科";break;
            case 4: xljb="专科";break;
            case 5: xljb="中专";break;
            case 6: xljb="高中";break;
            case 7: xljb="初中";break;
            case 8: xljb="小学";break;
            case 9: xljb="";break;
        }
        return xljb;
    }
    public static String getXzjbCs(int dm){
        String xzjb="普通岗位";
        switch (dm){
            case 1: xzjb="国家级正职";break;
            case 2: xzjb="国家级副职";break;
            case 3: xzjb="省部级正职";break;
            case 4: xzjb="省部级副职";break;
            case 5: xzjb="厅局级正职";break;
            case 6: xzjb="厅局级副职";break;
            case 7: xzjb="县处级正职";break;
            case 8: xzjb="县处级副职";break;
            case 9: xzjb="乡科级正职";break;
            case 10: xzjb="乡科级副职";break;
            case 11: xzjb="巡视员";break;
            case 12: xzjb="副巡视员";break;
            case 13: xzjb="调研员";break;
            case 14: xzjb="副调研员";break;
            case 15: xzjb="主任科员";break;
            case 16: xzjb="副主任科员";break;
            case 17: xzjb="科员";break;
            case 18: xzjb="办事员";break;
            case 21: xzjb="专业一级（正高级）";break;
            case 22: xzjb="专业二级（正高级）";break;
            case 23: xzjb="专业三级（正高级）";break;
            case 24: xzjb="专业四级（正高级）";break;
            case 25: xzjb="专业五级（副高级）";break;
            case 26: xzjb="专业六级（副高级）";break;
            case 27: xzjb="专业七级（副高级）";break;
            case 28: xzjb="专业八级（中级）";break;
            case 29: xzjb="专业九级（中级）";break;
            case 30: xzjb="专业十级（中级）";break;
            case 31: xzjb="专业十一级（初级）";break;
            case 32: xzjb="专业十二级（初级）";break;
            case 33: xzjb="专业十三级（初级）";break;
            case 41: xzjb="技术一级（高级技师）";break;
            case 42: xzjb="技术二级（技师）";break;
            case 43: xzjb="技术三级（高级工）";break;
            case 44: xzjb="技术四级（中级工）";break;
            case 45: xzjb="技术五级（初级工）";break;
            case 46: xzjb="普通岗位";break;
        }
        return xzjb;
    }

    public static String getMzCs(int dm){
        String mz="汉族";
        switch (dm){
            case 1:mz="汉族";break;
            case 2: mz="蒙古族";break;
            case 3: mz="回族";break;
            case 4: mz="藏族";break;
            case 5: mz="维吾尔族";break;
            case 6: mz="苗族";break;
            case 7: mz="彝族";break;
            case 8: mz="壮族";break;
            case 9: mz="布依族";break;
            case 10: mz="朝鲜族";break;
            case 11: mz="满族";break;
            case 12: mz="侗族";break;
            case 13: mz="瑶族";break;
            case 14: mz="白族";break;
            case 15: mz="土家族";break;
            case 16: mz="哈尼族";break;
            case 17: mz="哈萨克族";break;
            case 18: mz="傣族";break;
            case 19: mz="黎族";break;
            case 20: mz="傈僳族";break;
            case 21: mz="佤族";break;
            case 22: mz="畲族";break;
            case 23: mz="高山族";break;
            case 24: mz="拉祜族";break;
            case 25: mz="水族";break;
            case 26: mz="东乡族";break;
            case 27: mz="纳西族";break;
            case 28: mz="景颇族";break;
            case 29: mz="柯尔克孜族";break;
            case 30: mz="土族";break;
            case 31: mz="达斡尔族";break;
            case 32: mz="仫佬族";break;
            case 33: mz="羌族";break;
            case 34: mz="布朗族";break;
            case 35: mz="撒拉族";break;
            case 36: mz="毛南族";break;
            case 37: mz="仡佬族";break;
            case 38: mz="锡伯族";break;
            case 39: mz="阿昌族";break;
            case 40: mz="普米族";break;
            case 41: mz="塔吉克族";break;
            case 42: mz="怒族";break;
            case 43: mz="乌孜别克族";break;
            case 44: mz="俄罗斯族";break;
            case 45: mz="鄂温克族";break;
            case 46: mz="德昂族";break;
            case 47: mz="保安族";break;
            case 48: mz="裕固族";break;
            case 49: mz="京族";break;
            case 50: mz="塔塔尔族";break;
            case 51: mz="独龙族";break;
            case 52: mz="鄂伦春族";break;
            case 53: mz="赫哲族";break;
            case 54: mz="门巴族";break;
            case 55: mz="珞巴族";break;
            case 56: mz="基诺族";break;
            case 97: mz="其它";break;
            case 98: mz="外国血统中国籍人士";break;
        }
        return mz;
    }
    public static final Map<Integer, String> zzmmMap;
    static
    {
        zzmmMap = new HashMap<Integer, String>();
        zzmmMap.put( 0,"政治面貌不限");
        zzmmMap.put( 1,"中共党员");
        zzmmMap.put( 2,"中共预备党员");
        zzmmMap.put( 3,"共青团员");
        zzmmMap.put( 4,"民革会员");
        zzmmMap.put( 5,"民盟盟员");
        zzmmMap.put( 6,"民建会员");
        zzmmMap.put( 7,"民进会员");
        zzmmMap.put( 8,"农工党党员");
        zzmmMap.put( 9,"致公党党员");
        zzmmMap.put( 10,"九三学社社员");
        zzmmMap.put( 11,"台盟盟员");
        zzmmMap.put( 12,"无党派民主人士");
        zzmmMap.put( 13,"群众");
    }
    public static final Map<Integer, String> xzjbMap;
    static
    {
        xzjbMap = new HashMap<Integer, String>();
        xzjbMap.put( 0,"级别不限");
        xzjbMap.put( 1,"国家级正职");
        xzjbMap.put( 2,"国家级副职");
        xzjbMap.put( 3,"省部级正职");
        xzjbMap.put( 4,"省部级副职");
        xzjbMap.put( 5,"厅局级正职");
        xzjbMap.put( 6,"厅局级副职");
        xzjbMap.put( 7,"县处级正职");
        xzjbMap.put( 8,"县处级副职");
        xzjbMap.put( 9,"乡科级正职");
        xzjbMap.put( 10,"乡科级副职");
        xzjbMap.put( 11,"巡视员");
        xzjbMap.put( 12,"副巡视员");
        xzjbMap.put( 13,"调研员");
        xzjbMap.put( 14,"副调研员");
        xzjbMap.put( 15,"主任科员");
        xzjbMap.put( 16,"副主任科员");
        xzjbMap.put( 17,"科员");
        xzjbMap.put( 18,"办事员");
        xzjbMap.put( 21,"专业一级（正高级）");
        xzjbMap.put( 22,"专业二级（正高级）");
        xzjbMap.put( 23,"专业三级（正高级）");
        xzjbMap.put( 24,"专业四级（正高级）");
        xzjbMap.put( 25,"专业五级（副高级）");
        xzjbMap.put( 26,"专业六级（副高级）");
        xzjbMap.put( 27,"专业七级（副高级）");
        xzjbMap.put( 28,"专业八级（中级）");
        xzjbMap.put( 29,"专业九级（中级）");
        xzjbMap.put( 30,"专业十级（中级）");
        xzjbMap.put( 31,"专业十一级（初级）");
        xzjbMap.put( 32,"专业十二级（初级）");
        xzjbMap.put( 33,"专业十三级（初级）");
        xzjbMap.put( 41,"技术一级（高级技师）");
        xzjbMap.put( 42,"技术二级（技师）");
        xzjbMap.put( 43,"技术三级（高级工）");
        xzjbMap.put( 44,"技术四级（中级工）");
        xzjbMap.put( 45,"技术五级（初级工）");
        xzjbMap.put( 46,"普通岗位");
    }
    public static final Map<Integer, String> xlMap;
    static {
        xlMap = new HashMap<Integer, String>();
        xlMap.put( 0,"学历不限");
        xlMap.put( 1,"博士研究生");
        xlMap.put( 2,"硕士研究生");
        xlMap.put( 3,"本科");
        xlMap.put( 4,"专科");
        xlMap.put( 5,"中专");
        xlMap.put( 6,"高中");
        xlMap.put( 7,"初中");
        xlMap.put( 8,"小学");
        xlMap.put( 9,"");
    }
    public static final Map<Integer, String> xbMap;
    static {
        xbMap = new HashMap<Integer, String>();
        xbMap.put( 0,"性别不限");
        xbMap.put( 1,"男");
        xbMap.put( 2,"女");

    }
    public static final Map<Integer, String> mzMap;
    static {
        mzMap = new HashMap<Integer, String>();
        mzMap.put( 1,"汉族");
        mzMap.put( 2,"蒙古族");
        mzMap.put( 3,"回族");
        mzMap.put( 4,"藏族");
        mzMap.put( 5,"维吾尔族");
        mzMap.put( 6,"苗族");
        mzMap.put( 7,"彝族");
        mzMap.put( 8,"壮族");
        mzMap.put( 9,"布依族");
        mzMap.put( 10,"朝鲜族");
        mzMap.put( 11,"满族");
        mzMap.put( 12,"侗族");
        mzMap.put( 13,"瑶族");
        mzMap.put( 14,"白族");
        mzMap.put( 15,"土家族");
        mzMap.put( 16,"哈尼族");
        mzMap.put( 17,"哈萨克族");
        mzMap.put( 18,"傣族");
        mzMap.put( 19,"黎族");
        mzMap.put( 20,"傈僳族");
        mzMap.put( 21,"佤族");
        mzMap.put( 22,"畲族");
        mzMap.put( 23,"高山族");
        mzMap.put( 24,"拉祜族");
        mzMap.put( 25,"水族");
        mzMap.put( 26,"东乡族");
        mzMap.put( 27,"纳西族");
        mzMap.put( 28,"景颇族");
        mzMap.put( 29,"柯尔克孜族");
        mzMap.put( 30,"土族");
        mzMap.put( 31,"达斡尔族");
        mzMap.put( 32,"仫佬族");
        mzMap.put( 33,"羌族");
        mzMap.put( 34,"布朗族");
        mzMap.put( 35,"撒拉族");
        mzMap.put( 36,"毛南族");
        mzMap.put( 37,"仡佬族");
        mzMap.put( 38,"锡伯族");
        mzMap.put( 39,"阿昌族");
        mzMap.put( 40,"普米族");
        mzMap.put( 41,"塔吉克族");
        mzMap.put( 42,"怒族");
        mzMap.put( 43,"乌孜别克族");
        mzMap.put( 44,"俄罗斯族");
        mzMap.put( 45,"鄂温克族");
        mzMap.put( 46,"德昂族");
        mzMap.put( 47,"保安族");
        mzMap.put( 48,"裕固族");
        mzMap.put( 49,"京族");
        mzMap.put( 50,"塔塔尔族");
        mzMap.put( 51,"独龙族");
        mzMap.put( 52,"鄂伦春族");
        mzMap.put( 53,"赫哲族");
        mzMap.put( 54,"门巴族");
        mzMap.put( 55,"珞巴族");
        mzMap.put( 56,"基诺族");
        mzMap.put( 97,"其它");
        mzMap.put( 98,"外国血统中国籍人士");

    }
}
