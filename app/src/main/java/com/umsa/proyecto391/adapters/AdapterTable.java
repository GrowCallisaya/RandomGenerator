package com.umsa.proyecto391.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.umsa.proyecto391.R;
import com.umsa.proyecto391.model.Dato;
import com.umsa.proyecto391.views.XTextViewBold;
import com.umsa.proyecto391.views.XTextViewLight;

import java.util.ArrayList;
import java.util.List;


public class AdapterTable extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Dato> itemList;

    /**
     * Constructor
     **/
    public AdapterTable(List<Dato> datos) {
//        itemList = new ArrayList<>();
        itemList = datos;
    }

    /**
     * METHODS OF ADAPTER
     **/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TableViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder _holder, int position) {

        final Dato dato = itemList.get(position);
        final TableViewHolder holder = (TableViewHolder) _holder;
        if (dato!=null){
            holder.field_id.setText(dato.id);
            holder.field_x_n.setText(dato.x_n);
            holder.field_x_n1.setText(dato.x_n1);
            holder.field_x_u_i.setText(dato.x_u_i);
        }

//        if (team != null) {
//            //Team Title
//            holder.positionTeam.setText(team.name);
//
//            //Team Cover
//            if (team.logo.length() > 0) {
//                Glide.with(holder.context).load(team.logo).placeholder(R.drawable.logox)
//                        .into(holder.positionLogo);
//            } else {
//                Glide.with(holder.context).load(R.drawable.logox)
//                        .into(holder.positionLogo);
//            }
//
//            //Team Position
//            holder.positionPos.setText(team.pos);
//
//            //Team Points
//            holder.positionPoints.setText(String.valueOf(team.points));
//
//            //Team PJ
//            holder.positionPj.setText(String.valueOf(team.pj));
//
//            //Team DF
//            holder.positionDf.setText(String.valueOf(team.df));
//
//        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    /**
     * My Methods to add Items
     **/


    public void addItemMore(List<Dato> lst) {
        itemList.addAll(lst);
        notifyDataSetChanged();
    }

    /**
     * NOTICE VIEWHOLDER
     **/
    static class TableViewHolder extends RecyclerView.ViewHolder {
        private XTextViewLight field_id;
        private XTextViewBold field_x_n;
        private XTextViewBold field_x_n1;
        private XTextViewBold field_x_u_i;

        private Context context;

        public TableViewHolder(View v) {
            super(v);
            context = v.getContext();
            field_id = (XTextViewLight) v.findViewById(R.id.field_id);
            field_x_n = (XTextViewBold) v.findViewById(R.id.field_x_n);
            field_x_n1 = (XTextViewBold) v.findViewById(R.id.field_x_n1);
            field_x_u_i = (XTextViewBold) v.findViewById(R.id.field_u_i);
        }
    }

}
