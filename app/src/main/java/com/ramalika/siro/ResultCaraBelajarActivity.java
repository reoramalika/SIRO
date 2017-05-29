package com.ramalika.siro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultCaraBelajarActivity extends AppCompatActivity {

    private String idKecerdasan;
    public static final String keyIDKecerdasan="keyID";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> rs;
    private Controller contoller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_cara_belajar);

        contoller=new Controller(this);
        idKecerdasan=getIntent().getExtras().getString(keyIDKecerdasan);
        listView=(ListView) findViewById(R.id.listCaraBelajar);

        rs=contoller.getListResultCaraBelajar(contoller.getIDKecerdasan(idKecerdasan));

        adapter=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,rs);
        listView.setAdapter(adapter);
    }
}
