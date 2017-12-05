package com.umsa.proyecto391;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.umsa.proyecto391.adapters.AdapterTableCNG;
import com.umsa.proyecto391.adapters.AdapterTableMIX;
import com.umsa.proyecto391.model.DatoCNG;
import com.umsa.proyecto391.model.DatoMIX;
import com.umsa.proyecto391.views.XTextView;
import com.umsa.proyecto391.views.XTextViewBold;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TableActivityMIX extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterTableMIX adapter;
    private String semilla;
    private String ctte;
    private String module;
    private String multiply;

    private List<DatoMIX> datos = new ArrayList<>();
    private String mostOcurred="0.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_mix);
        setupToolbar();
        try{
            Bundle mochila = getIntent().getExtras();
            semilla = mochila.getString("SEMILLA");
            ctte = mochila.getString("CTTE");
            multiply = mochila.getString("MULTIPLY");
            module = mochila.getString("MODULE");
            startMethod(Integer.parseInt(semilla),Integer.parseInt(ctte),Integer.parseInt(multiply),Integer.parseInt(module));
            SetupRecyclerView();
        }catch (Exception e){}

    }

    private void startMethod(int Xo, int c, int a, int m) {
        int Xn = Xo;
        List<String> ocurrences = new ArrayList<>();

        for (int i = 0; i<100; i++) {

            //Getting the U_i (Numero PseundoAleatorio)
            int Xn_1 = ((a*Xn)+c)%m;
            BigDecimal U_i =new BigDecimal(Xn_1/(double)m);

            if (i==0) {
                mostOcurred = String.valueOf(U_i);
            }

            //Adding to our DataStorage
            datos.add(new DatoMIX(String.valueOf(i),String.valueOf(Xn),String.valueOf(Xn_1),String.valueOf(U_i)));
            Log.e("CONGRUENTES MIXTOS",i+" , "+Xn+" , "+Xn_1+" , "+U_i);

            ocurrences.add(String.valueOf(U_i));
            Xn =Xn_1;
        }

        //Setting the Period
        String Period = getDistanceofItems(ocurrences, mostOcurred);
        XTextView table_period = (XTextView) findViewById(R.id.table_period);
        table_period.setText("PERIODO: "+Period);

    }

    private String getDistanceofItems(List<String> ocurrences, String mostOcurred) {
        int count =0;
        List<String> distances = new ArrayList<>();
        for (int i = 0 ;i<ocurrences.size();i++){
            if (ocurrences.get(i).equals(mostOcurred)){
                count++;
                distances.add(count+"");
                count=0;
            }else{
                count++;
            }
        }

        return getPopularElement(distances);
    }

    public String getPopularElement(List<String> a)
    {
        int count = 1, tempCount;
        String popular = a.get(0);
        String temp = "0";
        for (int i = 0; i < (a.size() - 1); i++)
        {
            temp = a.get(i);
            tempCount = 0;
            for (int j = 1; j < a.size(); j++)
            {
                if (temp.equals(a.get(j)))
                    tempCount++;
            }
            if (tempCount > count)
            {
                popular = temp;
                count = tempCount;
            }
        }
        return popular;
    }


    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        XTextViewBold toolbarTitle = (XTextViewBold) findViewById(R.id.toolbar_name);
        toolbarTitle.setText("CONGRUENCIALES MIXTOS");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void SetupRecyclerView() {
        //Creamos la Lista en Columnas (Grids)
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Nuevo Adaptador
        adapter = new AdapterTableMIX(datos,mostOcurred);
        recyclerView.setAdapter(adapter);
    }
}
