package com.example.calculaor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity_unitConversion extends AppCompatActivity {

    private static int flag = 0; //默认厘米
    private static int re = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_unit_conversion);

        //加载单位菜单
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner_unit1);
        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.items_unit,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        //加载单位菜单
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner_unit2);
        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.items_unit,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        //加载计算器控件
        final TextView txt = findViewById(R.id.txt);
        final TextView result = findViewById(R.id.result);
        GridLayout gridLayout = findViewById(R.id.gridLayout_unit);
        for(int i=0;i<gridLayout.getChildCount()-4;i++){
            final Button bt = (Button)gridLayout.getChildAt(i+4);  //获取Button
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectButtonEvent(bt,txt,result);
                }
            });
        }

        //下拉列表选择事件监听
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0) {
                    flag = 0;
                }
                else if(i==1) {
                    flag = 1;
                }
                else if(i==2) {
                    flag = 2;
                }
                else if(i==3)
                    flag = 3;
                else
                    flag = 4;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0) {
                    re = 0;
                }
                else if(i==1) {
                    re = 1;
                }
                else if(i==2) {
                    re = 2;
                }
                else if(i==3)
                    re = 3;
                else
                    re = 4;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void selectButtonEvent(Button bt,TextView txt,TextView result){
        switch (bt.getText().toString()){
            case "C":
                txt.setText(null);
                result.setText(null);
                break;
            case "X":
                if(txt.getText().toString().length()!=0)
                    txt.setText(txt.getText().toString().substring(0,txt.getText().toString().length()-1));
                try{
                    conversion(txt.getText().toString(),result);
                }catch (Exception e){
                    break;
                }
                break;
            default:
                txt.setText(txt.getText()+bt.getText().toString());
                try{
                    conversion(txt.getText().toString(),result);
                }catch (Exception e){
                    break;
                }
        }
    }

    protected static void conversion(String front, TextView result){
        if(re == 0){
            switch (flag){
                case 0:result.setText(front);break;
                case 1:result.setText(a2to1(front));break;
                case 2:result.setText(a3to1(front));break;
                case 3:result.setText(a4to1(front));break;
                case 4:result.setText(a5to1(front));break;
            }
        } else if(re == 1){
            switch(flag){
                case 0:result.setText(a1to2(front));break;
                case 1:result.setText(front);break;
                case 2:result.setText(a3to2(front));break;
                case 3:result.setText(a4to2(front));break;
                case 4:result.setText(a5to2(front));break;

            }

        }
        else if(re == 2){
            switch(flag){
                case 0:result.setText(a1to3(front));break;
                case 1:result.setText(a2to3(front));break;
                case 2:result.setText(front);break;
                case 3:result.setText(a4to3(front));break;
                case 4:result.setText(a5to3(front));break;
            }
        }
        else if(re == 3){
            switch(flag){
                case 0:result.setText(a1to4(front));break;
                case 1:result.setText(a2to4(front));break;
                case 2:result.setText(a3to4(front));break;
                case 3:result.setText(front);break;
                case 4:result.setText(a5to4(front));break;

            }
        }else {
            switch (flag) {
                case 0:
                    result.setText(a1to5(front));
                    break;
                case 1:
                    result.setText(a2to5(front));
                    break;
                case 2:
                    result.setText(a3to5(front));
                    break;
                case 3:
                    result.setText(a4to5(front));
                    break;
                case 4:
                    result.setText(front);
                    break;
            }
        }
    }

    public static String a1to2(String a){
        double b = Double.parseDouble(a);
        return b/1000+"";
    }
    public static String a1to3(String a){
        double b = Double.parseDouble(a);
        return b/1000000000+"";
    }
    public static String a1to4(String a){
        double b = Double.parseDouble(a);
        return b*0.000000000001+"";
    }
    public static String a1to5(String a){
        double b = Double.parseDouble(a);
        return "两单位相差量度太大";
    }
    public static String a1to6(String a){
        double b = Double.parseDouble(a);
        return "两单位相差量度太大";
    }

    public static String a2to1(String a){
        double b = Double.parseDouble(a);
        return b*1000 + "";
    }
    public static String a2to3(String a){
        double b = Double.parseDouble(a);
        return b/1000000 + "";
    }
    public static String a2to4(String a){
        double b = Double.parseDouble(a);
        return b*0.000000001+"";
    }
    public static String a2to5(String a){
        double b = Double.parseDouble(a);
        return "两单位相差量度太大";
    }
    public static String a2to6(String a){
        double b = Double.parseDouble(a);
        return "两单位相差量度太大";
    }

    public static String a3to1(String a){
        double b = Double.parseDouble(a);
        return b*1000000000 + "";
    }
    public static String a3to2(String a){
        double b = Double.parseDouble(a);
        return b*1000000 + "";
    }
    public static String a3to4(String a){
        double b = Double.parseDouble(a);
        return b/1000 + "";
    }
    public static String a3to5(String a){
        double b = Double.parseDouble(a);
        return b*0.00062137 + "";
    }
    public static String a3to6(String a){
        double b = Double.parseDouble(a);
        return 3.2808398950 + "";
    }

    public static String a4to1(String a){
        double b = Double.parseDouble(a);
        return "两单位相差量度太大";
    }
    public static String a4to2(String a){
        double b = Double.parseDouble(a);
        return b/1000000 + "";
    }
    public static String a4to3(String a){
        double b = Double.parseDouble(a);
        return b/1000 + "";
    }
    public static String a4to5(String a){
        double b = Double.parseDouble(a);
        return b*0.621371192237 + "";
    }
    public static String a4to6(String a){
        double b = Double.parseDouble(a);
        return b * 3280 + "";
    }

    public static String a5to1(String a){
        double b = Double.parseDouble(a);
        return"两单位相差量度太大" ;
    }
    public static String a5to2(String a){
        double b = Double.parseDouble(a);
        return "两单位相差量度太大";
    }
    public static String a5to3(String a){
        double b = Double.parseDouble(a);
        return b*1609.344 + "";
    }
    public static String a5to4(String a){
        double b = Double.parseDouble(a);
        return b*1.609344 + "";
    }
    public static String a5to6(String a){
        double b = Double.parseDouble(a);
        return 5280 + "";
    }

    public static String a6to1(String a){
        double b = Double.parseDouble(a);
        return "两单位相差量度太大";
    }
    public static String a6to2(String a){
        double b = Double.parseDouble(a);
        return "两单位相差量度太大";
    }
    public static String a6to3(String a){
        double b = Double.parseDouble(a);
        return b*0.3048+"";
    }
    public static String a6to4(String a){
        double b = Double.parseDouble(a);
        return b*0.0003048 + "";
    }
    public static String a6to5(String a){
        double b = Double.parseDouble(a);
        return b * 0.00018939 + "";
    }
}