package com.umsa.proyecto391;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.umsa.proyecto391.adapters.AdapterTable;
import com.umsa.proyecto391.model.Dato;
import com.umsa.proyecto391.views.XTextViewBold;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterTable adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        setupToolbar();
        SetupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        XTextViewBold toolbarTitle = (XTextViewBold) findViewById(R.id.toolbar_name);
        toolbarTitle.setText("Sistema Binario");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void SetupRecyclerView() {

        //Creamos la Lista en Columnas (Grids)
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Nuevo Adaptador
        List<Dato> datos = new ArrayList<>();
        datos.add(new Dato("1","7","12,9456","0,129456"));
        datos.add(new Dato("2","7","12,9456","0,129456"));
        datos.add(new Dato("3","7","12,9456","0,129456"));
        datos.add(new Dato("4","7","12,9456","0,129456"));
        datos.add(new Dato("5","7","12,9456","0,129456"));
        datos.add(new Dato("6","7","12,9456","0,129456"));
        datos.add(new Dato("7","7","12,9456","0,129456"));
        datos.add(new Dato("8","7","12,9456","0,129456"));
        adapter = new AdapterTable(datos);
        recyclerView.setAdapter(adapter);

    }
}
