package com.umsa.proyecto391;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.umsa.proyecto391.adapters.AdapterTableCNG;
import com.umsa.proyecto391.model.DatoCNG;
import com.umsa.proyecto391.model.Divisor;
import com.umsa.proyecto391.views.XTextView;
import com.umsa.proyecto391.views.XTextViewBold;
import com.umsa.proyecto391.views.XTextViewLight;

import java.util.ArrayList;
import java.util.List;

public class TableActivityCNG extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterTableCNG adapter;
    private String semilla;
    private String ctte;
    private String module;
    private String multiply;

    private List<DatoCNG> datos = new ArrayList<>();
    private String mostOcurred="0.0";
    private String desarrollo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_cng);
        setupToolbar();
        try{
            Bundle mochila = getIntent().getExtras();
            semilla = mochila.getString("SEMILLA");
            multiply = mochila.getString("MULTIPLY");
            module = mochila.getString("MODULE");
            int d =0;
            int moduleFake = Integer.parseInt(module);
            //Getting d
            while(moduleFake>1){
                d++;
                moduleFake= moduleFake/10;
            }
            Log.e("D", d+"");
            desarrollo+="     d = "+d+"\n";

            //Validating d
            if (d>=5){
                startMethodMAX(Integer.parseInt(semilla),Integer.parseInt(multiply),Integer.parseInt(module),d);
            }else{
                startMethodMINOR(Integer.parseInt(semilla),Integer.parseInt(multiply),Integer.parseInt(module),d);
            }
            SetupRecyclerView();
        }catch (Exception e){}

    }

    private void startMethodMAX(int Xo, int a, int m,int d) {
        double Period = 5 * Math.pow(10, d-2);

        int Xn = Xo;
        for (int i = 0; i<=Period; i++) {
            //Getting X(n+1)
            int Xn_1 = (a*Xn)%m;

            //Adding to our DataStorage
            datos.add(new DatoCNG(String.valueOf(i),String.valueOf(Xn),String.valueOf(Xn_1)));
            Log.e("CONGRUENTES MULT.", i+" , "+Xn+" , "+Xn_1 );

            Xn =Xn_1;
        }
        XTextView table_period = (XTextView) findViewById(R.id.table_period);
        table_period.setText("PERIODO: "+(int)Period);
    }

    private void startMethodMINOR(int Xo, int a, int m, int d) {

        desarrollo+="     >>>>>> METODO LAMBDA"+"\n";
        int Xn = Xo;
        List<String> ocurrences = new ArrayList<>();
        //Getting the descomposicition
        int numero = m;
        int divisor =2;
        List<Divisor> divisores = new ArrayList<>();
        int seq =0;
        while(numero>1){
            for (int i = 0; i <= numero; i++){
                if (numero % divisor == 0) {
                    seq++;
                    numero = numero/divisor;
                }else{
                    if(seq!=0){
                        divisores.add(new Divisor(divisor,seq,false));
                        seq=0;
                    }
                    divisor++;
                }
            }
        }

        for (Divisor divisor1: divisores){

            Log.e("DIVISORES", divisor1.valor+" "+divisor1.seq);
        }

        //Getting Lambda of Divisors
        List<Integer> mcm = new ArrayList<>();

        for (Divisor di:divisores) {
            //CASE 1 (lambda(2))
            if ((int) Math.pow(di.valor,di.seq) ==2 && !di.state){
                mcm.add(1);
                desarrollo+="        ∆("+di.valor+") = "+1+"\n";
                di.state =true;
            }
            //CASE 1 (lambda(4))
            else if ((int)Math.pow(di.valor,di.seq) == 4 && !di.state) {
                mcm.add(2);
                desarrollo+="        ∆("+di.valor+"^"+di.seq+") =  ∆(4) = "+2+"\n";
                di.state =true;
            }
            //CASE 2 (lambda(2^d))
            else if (di.seq >=3 && !di.state && di.valor %2==0){
                mcm.add((int) Math.pow(2,di.seq-2));
                desarrollo+="        ∆("+di.valor+"^"+di.seq+") = "+"2^("+di.seq+"-2) = "+(int) Math.pow(2,di.seq-2)+"\n";
                di.state =true;
            }
            //CASE 3 (lambda(p^d-1))
            else{
                mcm.add((int) Math.pow(di.valor,di.seq-1) *(di.valor-1));
                desarrollo+="        ∆("+di.valor+"^"+di.seq+") = "+di.valor+"^("+di.seq+"-1) * ("+di.valor+"-1) = "+(int) Math.pow(di.valor,di.seq-1) *(di.valor-1)+"\n";
                di.state =true;
            }
        }

        //Getting the mcm
        desarrollo+="     >>>>>> MCM: "+mcm+"\n";
        Log.e("MCM", mcm+"");
        int Period = findMCM(mcm.get(0),mcm.get(1));

        //Settingg the Period
        XTextView table_period = (XTextView) findViewById(R.id.table_period);
        XTextViewLight dev = (XTextViewLight) findViewById(R.id.desarrollo);
        table_period.setText("PERIODO: "+Period);
        dev.setText(desarrollo);


        //Getting the Table
        for (int i = 0; i<=Period; i++) {
            //Getting X(n+1)
            int Xn_1 = (a*Xn)%m;

            //Adding to our DataStorage
            datos.add(new DatoCNG(String.valueOf(i),String.valueOf(Xn),String.valueOf(Xn_1)));
            Log.e("CONGRUENTES MULT.", i+" , "+Xn+" , "+Xn_1 );

            Xn =Xn_1;
        }


    }
    public int findMCD(int num1, int numb2){
        int mcd =0;
        int a = Math.max(num1,numb2);
        int b = Math.min(num1,numb2);
        do {
            mcd =b;
            b = a%b;
            a = mcd;
        }while (b!=0);
        return mcd;
    }
    public  int findMCM(int num1, int numb2){
        int mcm =0;
        int a = Math.max(num1,numb2);
        int b = Math.min(num1,numb2);
        mcm = (a/ findMCD(a,b)*b);
        return mcm;
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
        toolbarTitle.setText("CONGRUENCIAL MULTIPLICATIVO");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void SetupRecyclerView() {

        //Creamos la Lista en Columnas (Grids)
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Nuevo Adaptador
        adapter = new AdapterTableCNG(datos,mostOcurred);
        recyclerView.setAdapter(adapter);

    }
}
