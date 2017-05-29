package com.ramalika.siro;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reo Ramalika_2 on 29/05/2017.
 */

public class CiriAdapter extends RecyclerView.Adapter<CiriAdapter.ViewHolder> {

    private ArrayList<CiriEntity> listCiri;
    private ArrayList<CiriEntity> listChecked;

    public CiriAdapter(ArrayList<CiriEntity> listCiri){
        this.listCiri=listCiri;
        this.listChecked=new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CiriEntity singleCiri;
        public CheckBox chkSelected;
        public ListView list;

        public ViewHolder(View itemView) {
            super(itemView);

            chkSelected=(CheckBox) itemView.findViewById(R.id.chkSelected);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rowciri,null);

        ViewHolder viewHolder=new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int pos=position;

        holder.chkSelected.setTag(listCiri.get(pos));
        holder.chkSelected.setText(listCiri.get(pos).getNamaCiri());
        holder.chkSelected.setChecked(listCiri.get(pos).isChecked());

        holder.chkSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb=(CheckBox) v;
                CiriEntity ciri=(CiriEntity) cb.getTag();

                ciri.setChecked(cb.isChecked());
                listCiri.get(pos).setChecked(cb.isChecked());

                Toast.makeText(v.getContext(), "Clicked on "+cb.getText()+" is "+cb.isChecked(), Toast.LENGTH_SHORT).show();

                if(ciri.isChecked()){
                    listChecked.add(listCiri.get(pos));
                }
                else {
                    for (int i=0;i<listChecked.size();i++){
                        if(listChecked.get(i).getId()==listCiri.get(pos).getId()) {
                            listChecked.remove(i);
                            break;
                        }
                    }
                }
                String text="";
                for(int i=0;i<listChecked.size();i++){
                    text=text+"\n"+listChecked.get(i).getNamaCiri()+listChecked.get(i).getKategori();
                }
                Log.i("CheckedList","size: "+listChecked.size()+text);


            }
        });
    }

    @Override
    public int getItemCount() {
        return listCiri.size();
    }

    public ArrayList<CiriEntity> getListCiri(){
        return listCiri;
    }

    public ArrayList<CiriEntity> getListChecked(){
        return listChecked;
    }
}
