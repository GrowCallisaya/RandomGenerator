package com.umsa.proyecto391;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import com.github.javiersantos.bottomdialogs.BottomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    
    CardView cardMethod1;
    CardView cardMethod2;
    CardView cardMethod3;
    CardView cardMethod4;
    private BottomDialog bar;
    private View formView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        setupForm();

        cardMethod1 = (CardView) findViewById(R.id.card_method1); 
        cardMethod2 = (CardView) findViewById(R.id.card_method2); 
        cardMethod3 = (CardView) findViewById(R.id.card_method3); 
        cardMethod4 = (CardView) findViewById(R.id.card_method4); 
        cardMethod1.setOnClickListener(this);
        cardMethod2.setOnClickListener(this);
        cardMethod3.setOnClickListener(this);
        cardMethod4.setOnClickListener(this);

    }

    private void setupForm() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        formView = inflater.inflate(R.layout.form_fragment,null);
        FancyButton btn_calculate = (FancyButton) formView.findViewById(R.id.btn_calculate);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TableActivity.class));
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_method1:
                bar = new BottomDialog.Builder(this)
                        .setCustomView(formView)
                        .show();
                break;
            case R.id.card_method2:
                bar = new BottomDialog.Builder(this)
                        .setCustomView(formView)
                        .show();
                break;
            case R.id.card_method3:
                bar = new BottomDialog.Builder(this)
                        .setCustomView(formView)
                        .show();
                break;
            case R.id.card_method4:
                bar = new BottomDialog.Builder(this)
                        .setCustomView(formView)
                        .show();
                break;
            default:
                break;
        }
    }
}
