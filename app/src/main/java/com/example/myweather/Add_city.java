package com.example.myweather;
import android.content.res.Resources;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Add_city extends AppCompatActivity implements View.OnClickListener{
    private Button btn_add;
    private Button btn_back;
    private AutoCompleteTextView autoCompleteTextView;//自动填充
    private CitySQL citySQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_city);
        initView();
    }

    private void initView(){
        citySQL = new CitySQL(this);
        this.autoCompleteTextView = this.findViewById(R.id.autoCompleteTextView);
        Resources resources = this.getResources();//获取array中的资源信息
        String[] country = resources.getStringArray(R.array.city_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1,
                country
        );
        this.autoCompleteTextView.setAdapter(adapter);
        this.autoCompleteTextView.setOnClickListener(this);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

}
