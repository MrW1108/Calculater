package com.example.calculaor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity_unitConversion extends AppCompatActivity {

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
    }
}