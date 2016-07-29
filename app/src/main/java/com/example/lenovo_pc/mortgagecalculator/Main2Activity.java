package com.example.lenovo_pc.mortgagecalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String,String>> dataList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView=(ListView)findViewById(R.id.listView);
        dataList=MyDataSource.getMaps();
        adapter=new SimpleAdapter(Main2Activity.this,dataList,R.layout.activity_main2,new String[]{"序号","交易日期","合计","本金","利息","本金余额"},new int[]{R.id.serial_number,R.id.trade_date,R.id.total,R.id.principal,R.id.interests,R.id.principal_saved_money});
        listView.setAdapter(adapter);
    }
}
