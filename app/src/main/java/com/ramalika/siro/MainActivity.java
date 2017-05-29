package com.ramalika.siro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String myPref="mySharedPreferences";
    public static final String keyResultNama="keyResultNama";
    public static final String keyResultPersen="keyResultPersen";
    JSONArray jsonArray;

    private DAO dao;
    private Controller controller;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CiriEntity> ciriList;
    private Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences(myPref, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();


        dao=new DAO(this);
        controller=new Controller(this);

        controller.init();

        //initComponentLayout
        btnShow=(Button) findViewById(R.id.btn);
        recyclerView=(RecyclerView) findViewById(R.id.myRecylerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ciriList=dao.getAllCiri();
        Log.i("cekKat","kat"+ciriList.get(0).getKategori()+"-"+dao.getAllCiri().get(0).getKategori());

        setAdapter(new CiriAdapter(ciriList));

        recyclerView.setAdapter(getAdapter());
        recyclerView.setHasFixedSize(true);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data="Checked List:";

                List<CiriEntity> list=((CiriAdapter) getAdapter()).getListChecked();

                for (int i = 0; i < list.size(); i++) {
                    CiriEntity ciri = list.get(i);
                    if (ciri.isChecked() == true) {
                        data = data + "\n" + ciri.getNamaCiri().toString();
                    }
                }
                //Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();

                ArrayList<KecerdasanEntity> rs1=controller.getListResultKecerdasan(((CiriAdapter) getAdapter()).getListChecked());
                /*ArrayList<String> rs2=controller.getListResultCaraBelajar(rs1.get(0).);

                String p="";
                for (int i=0;i<rs2.size();i++){
                    p=p+"\n"+rs2.get(i);
                }*/
                //Toast.makeText(MainActivity.this, p, Toast.LENGTH_SHORT).show();
                ArrayList<String> rsnama=new ArrayList<>();
                ArrayList<String> rspersen=new ArrayList<>();

                Bundle b=new Bundle();
                for (int i=0;i<rs1.size();i++){
                    rsnama.add(rs1.get(i).getNamaKecerdasan());
                }
                for (int i=0;i<rs1.size();i++){
                    rspersen.add(rs1.get(i).getPersentase()+"");
                }
                b.putStringArrayList(keyResultNama,rsnama);
                b.putStringArrayList(keyResultPersen,rspersen);
                Intent i=new Intent(getApplicationContext(),ResultActivity.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }
}