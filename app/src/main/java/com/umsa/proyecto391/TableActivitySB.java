package com.umsa.proyecto391;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.umsa.proyecto391.adapters.AdapterTableCNG;
import com.umsa.proyecto391.adapters.AdapterTableSB;
import com.umsa.proyecto391.model.DatoCNG;
import com.umsa.proyecto391.model.DatoSB;
import com.umsa.proyecto391.views.XTextView;
import com.umsa.proyecto391.views.XTextViewBold;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TableActivitySB extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterTableSB adapter;
    private String semilla;
    private String ctte;
    private String module;
    private String multiply;

    private List<DatoSB> datos = new ArrayList<>();
    private String mostOcurred="0.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_sb);
        setupToolbar();
        try{
            Bundle mochila = getIntent().getExtras();
            semilla = mochila.getString("SEMILLA");
            multiply = mochila.getString("MULTIPLY");
            module = mochila.getString("MODULE");
            int d =0;
            int moduleFake = Integer.parseInt(module);
            while(moduleFake>1){
                d++;
                moduleFake= moduleFake/2;
            }
            Log.e("SISTEMA BINARIO", d+"");
            startMethod(Integer.parseInt(semilla),Integer.parseInt(multiply),Integer.parseInt(module),d);
            SetupRecyclerView();
        }catch (Exception e){}

    }

    private void startMethod(int Xo, int a, int m, int d) {
        int Xn = Xo;

        double Period = Math.pow(2,d-2);
        for (int i = 0; i<=(int) Period; i++) {

            //Getting the U_i (Numero PseundoAleatorio)
            int Xn_1 = (a*Xn)%m;

            //Adding to our DataStorage
            datos.add(new DatoSB(String.valueOf(i),String.valueOf(Xn),String.valueOf(Xn_1)));
            Log.e("SISTEMA BINARIO",i+" , "+Xn+" , "+Xn_1);

            Xn =Xn_1;
        }

        //Setting the Period
        XTextView table_period = (XTextView) findViewById(R.id.table_period);
        table_period.setText("PERIODO: "+ (int)Period);

    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        XTextViewBold toolbarTitle = (XTextViewBold) findViewById(R.id.toolbar_name);
        toolbarTitle.setText("SISTEMA BINARIO");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void SetupRecyclerView() {
        //Creamos la Lista en Columnas (Grids)
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Nuevo Adaptador
        adapter = new AdapterTableSB(datos);
        recyclerView.setAdapter(adapter);
    }
}
