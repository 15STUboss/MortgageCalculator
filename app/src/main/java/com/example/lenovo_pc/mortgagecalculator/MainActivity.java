package com.example.lenovo_pc.mortgagecalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private  double edited_sum;//贷款金额
    private  double edited_rate;//年利率
    private  double edited_monrate;//月利率
    private  int edited_time;//贷款期限
    private int pay_month;//总的还款月数
    private  int textview_date[]=new int[3];//起贷日期
    private String sum,rate,time,date;
    private double month_payments;//每月还款
    private double sum_interests;//利息总计
    private double sum_money;//本息总计
    private EditText edittext1;
    private EditText edittext2;
    private EditText edittext3;
    private EditText edittext4;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView_begin_date;//起贷日期
    private Button button_calculate;//计算按钮
    private Button button_empty;//清空按钮
    private Button button_checkdetail;//查看详细账单按钮
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext1=(EditText)findViewById(R.id.edit1);
        edittext2=(EditText)findViewById(R.id.edit2);
        edittext3=(EditText)findViewById(R.id.edit3);
        edittext4=(EditText)findViewById(R.id.edit4);
        textView1=(TextView) findViewById(R.id.textView6);
        textView2=(TextView)findViewById(R.id.editText2);
        textView3=(TextView)findViewById(R.id.textView7);
        textView4=(TextView)findViewById(R.id.ben_interests);
        textView_begin_date=(EditText)findViewById(R.id.edit4);
        button_checkdetail=(Button)findViewById(R.id.button3);
        //时间先不管
        //数据处理
        button_calculate=(Button)findViewById(R.id.button2);
        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sum=edittext1.getText().toString();
                rate=edittext2.getText().toString();
                time=edittext3.getText().toString();
                date=edittext4.getText().toString();
                edited_sum=Integer.parseInt(sum);
                edited_rate=Double.parseDouble(rate);
                edited_time=Integer.parseInt(time);
                pay_month=edited_time*12;
                edited_monrate=0.01*edited_rate/12;
                month_payments=1.0*edited_sum*edited_monrate*Math.pow(1+edited_monrate,1.0*pay_month)/(Math.pow(1+edited_monrate,1.0*pay_month)-1);
                BigDecimal big1=new BigDecimal(month_payments);
                month_payments=(double) big1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                textView1.setText(String.valueOf(month_payments));
                textView2.setText(String.valueOf(pay_month));
                sum_interests=edited_sum*edited_monrate*Math.pow(1+edited_monrate,1.0*pay_month)*pay_month/(Math.pow(1+edited_monrate,1.0*pay_month)-1)-edited_sum;
                BigDecimal big2=new BigDecimal(sum_interests);
                sum_interests=(double) big2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                textView3.setText(String.valueOf(sum_interests));
                sum_money=sum_interests+edited_sum;
                BigDecimal big3=new BigDecimal(sum_money);
                sum_money=(double) big3.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                textView4.setText(String.valueOf(sum_money));
            }
        });

        button_empty=(Button)findViewById(R.id.button);
        button_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext1.setText("");
                edittext2.setText("");
                edittext3.setText("");
                edittext4.setText("");
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
            }
        });

        button_checkdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataSource dataSource=new MyDataSource(textView_begin_date.getText().toString(),month_payments,edited_sum,pay_month,edited_monrate);
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(MainActivity.this,"You cliked Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(MainActivity.this,"You cliked Remove",Toast.LENGTH_SHORT).show();
                break;
        }
        return  true;
    }
    publci boolean onO
}
