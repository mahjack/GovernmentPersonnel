package com.sdzx.government.governmentpersonnel.tools;

/**
 * Author: Eron
 * Date: 2016/3/2 0002
 * Time: 9:55
 */
public class ConstantURL {
    private static final String BASIC_URL = "http://gzwrlzy.sdzxkj.cn/json/";
    // 登录
    public static final String URL_TOKEN = "module=user&action=list&token="+ Ppp.inpsw();
    public static final String URL_LOGIN = BASIC_URL+"login.php?"+URL_TOKEN;
    public static final String URL_LIST = BASIC_URL+"list.php?"+"module=user&action=list&token="+Ppp.inpsw();
    //检测更新
    public static final String VERSION = BASIC_URL+"version.php?"+URL_TOKEN; // 检查更新
//http://gzwrlzy.sdzxkj.cn/json/list.php?module=user&action=list&token=&act=ulist&uid=1&gid=1&tag=1
//    http://gzwrlzy.sdzxkj.cn/json/list.php?module=user&action=list&act=glist&uid=1&token=



}
