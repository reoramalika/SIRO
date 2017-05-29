package com.ramalika.siro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Reo Ramalika_2 on 29/05/2017.
 */

public class ResultActivity extends AppCompatActivity{
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<KecerdasanEntity> listresult;
    private Controller controller=new Controller(ResultActivity.this);
    public static final String keyResult="keyResult";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle b;
        Intent in=getIntent();
        b=in.getExtras();
        ResultDataHelper r= (ResultDataHelper) b.getSerializable(keyResult);

        listresult=r.getRs();
        ArrayList<String> rs=new ArrayList<>();
        for (int i=0;i<listresult.size();i++){
            rs.add("("+listresult.get(i).getPersentase()+")"+listresult.get(i).getNamaKecerdasan());
        }
        adapter=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs);

        listView=(ListView) findViewById(R.id.listResult);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

            }
        });
    }
}
