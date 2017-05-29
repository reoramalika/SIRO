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
    private Bundle bundle=new Bundle();
    public static final String keyResultNama="keyResultNama";
    public static final String keyResultPersen="keyResultPersen";
    public static final String keyIDKecerdasan="keyID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle b;
        Intent in=getIntent();
        b=in.getExtras();
        //ResultDataHelper r= (ResultDataHelper) b.getSerializable(keyResult);

        //listresult=r.getRs();
        ArrayList<String> rs=new ArrayList<>();
        final ArrayList<String> rsNama=b.getStringArrayList(keyResultNama);
        ArrayList<String> rsPersen=b.getStringArrayList(keyResultPersen);
        for (int i=0;i<rsNama.size();i++){
            rs.add("("+rsPersen.get(i)+" %)  |  "+rsNama.get(i));
        }
        adapter=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,rs);

        listView=(ListView) findViewById(R.id.listResult);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Log.d("cekkk",rsNama.get(pos).toString()+"  "+pos);
                bundle.putString(keyIDKecerdasan,rsNama.get(pos).toString());
                Intent i=new Intent(getApplicationContext(),ResultCaraBelajarActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}
