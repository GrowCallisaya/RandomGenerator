package com.umsa.proyecto391.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umsa.proyecto391.R;
import com.umsa.proyecto391.model.DatoCNG;
import com.umsa.proyecto391.model.DatoMIX;
import com.umsa.proyecto391.views.XTextViewBold;
import com.umsa.proyecto391.views.XTextViewLight;

import java.util.List;


public class AdapterTableMIX extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DatoMIX> itemList;
    private String mOcurred;
    /**
     * Constructor
     **/
    public AdapterTableMIX(List<DatoMIX> datos, String mOcurred) {
        itemList = datos;
        this.mOcurred = mOcurred;
    }

    /**
     * METHODS OF ADAPTER
     **/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TableViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_mix, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder _holder, int position) {

        final DatoMIX dato = itemList.get(position);
        final TableViewHolder holder = (TableViewHolder) _holder;
        if (dato!=null){
            holder.field_id.setText(dato.id);
            holder.field_x_n.setText(dato.x_n);
            holder.field_x_n1.setText(dato.x_n1);
            holder.field_x_u_i.setText(dato.x_u_i);
            if (dato.x_u_i.equals(mOcurred)){
                holder.field_x_u_i.setTextColor(Color.RED);
            }else{
                holder.field_x_u_i.setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
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
