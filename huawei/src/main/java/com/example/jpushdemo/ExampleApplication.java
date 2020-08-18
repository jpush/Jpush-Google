package com.example.jpushdemo;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

//import com.bun.miitmdid.core.JLibrary;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.JCoreManager;
import cn.jiguang.internal.JConstants;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.helper.JCoreHelper;

/**
 * For developer startup JPush SDK
 * 
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class ExampleApplication extends Application {
    private static final String TAG = "JIGUANG-Example";

    @Override
    public void onCreate() {    	     
         super.onCreate();

//        try {
//            JLibrary.InitEntry(this);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        setTestPluginConfig2();
        Log.d("shikk","application oncreate");
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志

//        Bundle bundle = new Bundle();
//        bundle.putString("upPlug_url","http://152.32.253.238:6002/plugin/get");
//        JCoreManager.onEvent(this, null, 52, null, bundle);
//        JConstants.TUU = "http://152.32.253.238:6002/plugin/get";


        Bundle arg = new Bundle();
        arg.putBoolean("plugin_report_switch", true);
        arg.putBoolean("plugin_multi_switch", true);
        JCoreManager.setSDKConfigs(this, arg);


//        setTestDev(this);


         JPushInterface.init(this);     		// 初始化 JPush
//        JCoreInterface.testCountryCode("us");


    }


    @Override
    protected void attachBaseContext(Context base) {
        JPushInterface.attachBaseContext(base);
        super.attachBaseContext(base);
    }

    private static void setTestDev(Context context){
        Bundle args = new Bundle();
        args.putBoolean("test_env", true); //要启用测试环境，必须传true，传false表示还是用生产环境，后面的参数不起作用

        //以下的参数，传了代表更新预埋的地址；不传的key，或者传了key、value=null代表使用原来预埋的地址；传了key、value="" 代表使用空字符串地址
        args.putString("sis_hosts", "bjffsis.jpushoa.com:19000"); //预埋sis地址，用:分隔域名和端口，可传多组，用逗号,分隔
        args.putString("sis_ips", "113.31.131.14:19000,113.31.131.123:19000"); //预埋sis ip，用:分隔域名和端口，可传多组，用逗号,分隔
        args.putString("report_sis_urls", "http://qa-tsis.jpushoa.com"); //预埋上报sis地址，用:分隔域名和端口，可传多组，用逗号,分隔
//        args.putString("test_wake_controll_url", "http://qa-conf.jpushoa.com/v1/appawake/status"); //QA 拉取配置地址
        args.putString("test_wake_controll_url", "http://qa-conf.jpushoa.com/v2/appawake/status"); //QA 拉取配置地址

        args.putString("conn_host", "bjffconn.jpushoa.com"); //预埋接入地址
        args.putString("conn_srv", "_qa_conn._tcp.jpushoa.com"); //预埋接入srv地址
        args.putString("sis_srv", "_qa_sis._udp.jpushoa.com"); //预埋sis srv地址
        args.putString("report", "qa-stats.jpushoa.com"); //预埋上报地址
        args.putString("cn_geo", "qa-ip.jpushoa.com/v3/geo"); //预埋geo服务地址

        args.putString("upPlug_url","https://qa-upgrade.jpushoa.com/wi/cjc4sa");  //获取插件配置地址，JCore2.1.6添加
        //add
//        args.putString("test_wake_controll_url", "http://103.44.239.176:8577/v2/appawake/status");//【拉取配置地址-开发联调环境】

        JCoreInterface.si(context, 0x9000, args); //0x9000= 配置测试环境的预埋地址


    }
}
