package com.ramalika.siro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private DAO dao;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("dao","start DAO");
        dao=new DAO(this);
        Log.d("controller","start Controller");
        controller=new Controller(this);

        Log.d("init","start init");
        controller.init();

        ListView list=(ListView) findViewById(R.id.listView);
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,dao.getAllCiri());
        list.setAdapter(adapter);
    }
}