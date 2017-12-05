package com.umsa.proyecto391.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umsa.proyecto391.R;
import com.umsa.proyecto391.model.DatoCNG;
import com.umsa.proyecto391.model.DatoSB;
import com.umsa.proyecto391.views.XTextViewBold;
import com.umsa.proyecto391.views.XTextViewLight;

import java.util.List;


public class AdapterTableSB extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DatoSB> itemList;
    /**
     * Constructor
     **/
    public AdapterTableSB(List<DatoSB> datos) {
        itemList = datos;
    }

    /**
     * METHODS OF ADAPTER
     **/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TableViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_sb, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder _holder, int position) {

        final DatoSB dato = itemList.get(position);
        final TableViewHolder holder = (TableViewHolder) _holder;
        if (dato!=null){
            holder.field_id.setText(dato.id);
            holder.field_x_n.setText(dato.x_n);
            holder.field_x_n1.setText(dato.x_n1);
            if (position ==0 || position == itemList.size()-1){
                holder.field_id.setTextColor(Color.RED);
                holder.field_x_n.setTextColor(Color.RED);
                holder.field_x_n1.setTextColor(Color.RED);
            }else{
                holder.field_id.setTextColor(Color.GRAY);
                holder.field_x_n.setTextColor(Color.GRAY);
                holder.field_x_n1.setTextColor(Color.GRAY);
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    /**
     * My Methods to add Items
     **/


    public void addItemMore(List<DatoSB> lst) {
        itemList.addAll(lst);
        notifyDataSetChanged();
    }

    /**
     * NOTICE VIEWHOLDER
     **/
    static class TableViewHolder extends RecyclerView.ViewHolder {
        private XTextViewLight field_id;
        private XTextViewLight field_x_n;
        private XTextViewBold field_x_n1;

        private Context context;

        public TableViewHolder(View v) {
            super(v);
            context = v.getContext();
            field_id = (XTextViewLight) v.findViewById(R.id.field_id);
            field_x_n = (XTextViewLight) v.findViewById(R.id.field_x_n);
            field_x_n1 = (XTextViewBold) v.findViewById(R.id.field_x_n1);
        }
    }

}
