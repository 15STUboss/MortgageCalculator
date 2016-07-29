package com.example.lenovo_pc.mortgagecalculator;

import android.support.annotation.NonNull;
import android.util.Log;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by lenovo-pc on 2016/7/23 0023.
 */
public class MyDataSource {
    public static double edited_sum;//贷款金额
    public static int pay_month;//总的还款月数
    public static String trade_date;//交易日期
    public static double serial_number;//交易总金额
    public static double interests;//利息
    public static double principal;//本金
    public static double principal_savedmoney;//本金余额
    public static double month_payments;//每月还款
    public  static double edited_monrate;//月利率

    public MyDataSource(String trade_date,double month_payments,double edited_sum,int pay_month,double edited_monrate){
        this.trade_date=trade_date;
        this.month_payments=month_payments;
        this.edited_sum=edited_sum;
        this.pay_month=pay_month;
        this.edited_monrate=edited_monrate;
    }

    public static List<Map<String,String>> getMaps(){
        List<Map<String,String>> listMaps=new ArrayList<Map<String, String>>();
        BigDecimal big1=new BigDecimal(month_payments);
        month_payments=(double) big1.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
        principal_savedmoney=edited_sum;
        for (int i=1;i<=pay_month;i++){
            Map<String,String> map1=new HashMap<String ,String>();
            interests=principal_savedmoney*edited_monrate;//每月的利息
            principal=month_payments-interests;//每月本金
            principal_savedmoney-=principal;//每月本金余额
            BigDecimal big2=new BigDecimal(interests);
            interests=(double) big2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            BigDecimal big3=new BigDecimal(principal);
            principal=(double) big3.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            BigDecimal big=new BigDecimal(principal_savedmoney);
            principal_savedmoney=(double) big.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            //获取交易日期的子串
            map1.put("序号",String.valueOf(i));
            map1.put("交易日期",getDate(trade_date));
            map1.put("合计",String.valueOf(month_payments));
            map1.put("利息",String.valueOf(interests));
            map1.put("本金",String.valueOf(principal));
            map1.put("本金余额",String.valueOf(principal_savedmoney));
            listMaps.add(map1);
        }
        return listMaps;
        }

    public static String getDate(String string_date){
        String []son_string=string_date.split("\\-");//获取日期子串
        String []new_date=new String[3];
        int []son_int=new int[3];
        for (int i=0;i<son_string.length;i++){
            son_int[i]=Integer.parseInt(son_string[i]);

        }
        if (son_int[1]==12){
            son_int[0]++;
            son_int[1]=1;
        }
        else son_int[1]++;
        for (int i=0;i<son_string.length;i++){
            new_date[i]=String.valueOf(son_int[i]);
        }
        trade_date=new_date[0]+"-"+new_date[1]+"-"+new_date[2];
        return trade_date;
    }
    }
