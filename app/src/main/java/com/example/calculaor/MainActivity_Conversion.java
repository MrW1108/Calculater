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

public class MainActivity_Conversion extends AppCompatActivity {

    int flag = 0; //标志几进制

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__conversion);

        //加载单位菜单
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner_base1);
        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.items_base,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        //加载单位菜单
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner_base2);
        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.items_base,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        //加载计算器控件
        final TextView txt = findViewById(R.id.txt);
        final TextView result = findViewById(R.id.result);
        GridLayout gridLayout = findViewById(R.id.gridLayout_base);
        for(int i=0;i<gridLayout.getChildCount()-4;i++){
            final Button bt = (Button)gridLayout.getChildAt(i+4);  //获取Button
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    txt.setText(txt.getText()+bt.getText().toString());
                }
            });
        }

        //下拉列表选择事件监听
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                    flag = 2;
                else if(i==1)
                    flag = 8;
                else if(i==2)
                    flag = 10;
                else
                    flag = 16;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int re = flag;
                String front = result.getText().toString();
                try {
                    if(i==0){  //二进制
                        switch (re){
                            case 2: result.setText(front);break;
                            case 8: result.setText(a8to2(front));break;
                            case 10: result.setText(a10to2(front));break;
                            case 16: result.setText(a16to2(front));break;
                        }
                    }else if(i==1){  //八进制
                        switch (re){
                            case 2: result.setText(a2to8(front));break;
                            case 8: result.setText(front);break;
                            case 10: result.setText(a10to8(front));break;
                            case 16: result.setText(a16to8(front));break;
                        }
                    }else if(i==2){ //十进制
                        switch (re){
                            case 2: result.setText(a2to10(front));break;
                            case 8: result.setText(a8to10(front));break;
                            case 10: result.setText(front);break;
                            case 16: result.setText(a16to10(front));break;
                        }
                    }else{     //十六进制
                        switch (re){
                            case 2: result.setText(a2to16(front));break;
                            case 8: result.setText(a8to16(front));break;
                            case 10: result.setText(a10to16(front));break;
                            case 16: result.setText(front);break;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public static String a2to8(String a){
        return Integer.toOctalString(Integer.parseInt(a, 2))+"";
    }
    public static String a2to10(String a){
        return Integer.parseInt(a, 2) +"";
    }
    public static String a2to16(String a){
        return Integer.toHexString(Integer.parseInt(a, 2))+"";
    }
    public static String a8to2(String a){
        return Integer.toBinaryString(Integer.parseInt(a, 8));
    }
    public static String a8to10(String a){
        return Integer.toString(Integer.parseInt(a, 8));
    }
    public static String a8to16(String a){
        return Integer.toHexString(Integer.parseInt(a, 8));
    }
    public static String a10to2(String a){
        return Integer.toBinaryString(Integer.parseInt(a));
    }
    public static String a10to8(String a){
        return Integer.toOctalString(Integer.parseInt(a));
    }
    public static String a10to16(String a){
        return Integer.toHexString(Integer.parseInt(a));
    }
    public static String a16to2(String a){
        return Integer.toBinaryString(Integer.parseInt(a,16));
    }
    public static String a16to8(String a){
        return Integer.toOctalString(Integer.parseInt(a,16));
    }
    public static String a16to10(String a){
        return Integer.toString(Integer.parseInt(a,16))+"";
    }
}