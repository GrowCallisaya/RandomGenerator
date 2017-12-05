package com.umsa.proyecto391;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.umsa.proyecto391.adapters.AdapterTableCM;
import com.umsa.proyecto391.model.Dato;
import com.umsa.proyecto391.model.DatoCM;
import com.umsa.proyecto391.views.XTextView;
import com.umsa.proyecto391.views.XTextViewBold;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TableActivityCM extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterTableCM adapter;
    private String semilla;
    private List<DatoCM> datos = new ArrayList<>();
    private String mostOcurred="0.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_cm);
        setupToolbar();
        try{
            Bundle mochila = getIntent().getExtras();
            semilla = mochila.getString("SEMILLA");
            startMethod(Integer.parseInt(semilla));
            SetupRecyclerView();
        }catch (Exception e){}

    }

    private void startMethod(int valor) {
        BigInteger Xn = new BigInteger(valor+"");
        List<String> ocurrences = new ArrayList<>();

        for (int i = 0; i<100; i++) {
            if (Xn.equals(0)) {
                break;
            }

            //Getting the Xn^2
            BigInteger Xn2 = Xn.pow(2);

            //Getting the Xn_(n+1)
            String Xn2_s = Xn2 + "";
            String Xn_1_s = "";
            if (Xn2_s.length() % 2 == 0) {
                Xn_1_s = Xn2_s.substring((Xn2_s.length() / 2) - 2, (Xn2_s.length() / 2) + 2);
            } else {
                Xn2_s = "0" + Xn2_s;
                Xn_1_s = Xn2_s.substring((Xn2_s.length() / 2) - 2, (Xn2_s.length() / 2) + 2);
            }

            //Getting the U_i (Numero PseundoAleatorio)
            int Xn_1 = Integer.parseInt(Xn_1_s);
            String U_i = "0,"+Xn_1;

            //Adding to our DataStorage
            datos.add(new DatoCM(i+"",Xn+"",Xn2+"",Xn_1+"",U_i+""));
            Log.e("CUADRADOS MEDIOS",i+" , "+Xn+" , "+Xn2+" , "+Xn_1+" , "+U_i);

            ocurrences.add(U_i);
            Xn =new BigInteger(Xn_1+"");
        }

        //Looking for the most Occurred
        mostOcurred = getPopularElement(ocurrences);
        Log.e("CUADRADOS MEDIOS M.O.", mostOcurred+"");


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
        toolbarTitle.setText("CUADRADOS MEDIOS");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void SetupRecyclerView() {

        //Creamos la Lista en Columnas (Grids)
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Nuevo Adaptador

        adapter = new AdapterTableCM(datos,mostOcurred);
        recyclerView.setAdapter(adapter);

    }
}
