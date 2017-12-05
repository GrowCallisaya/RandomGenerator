package com.umsa.proyecto391;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.umsa.proyecto391.views.XEditText;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cardMethod1;
    CardView cardMethod2;
    CardView cardMethod3;
    CardView cardMethod4;
    FancyButton cngBinario;
    FancyButton cngDecimal;

    private BottomDialog bar;
    private View formViewCM;
    private View formViewCNG;
    private View formViewMIX;
    private View formViewSB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();

        cardMethod1 = (CardView) findViewById(R.id.card_method1); 
        cardMethod2 = (CardView) findViewById(R.id.card_method2); 
        cardMethod3 = (CardView) findViewById(R.id.card_method3); 
        cardMethod4 = (CardView) findViewById(R.id.card_method4);
        cardMethod1.setOnClickListener(this);
        cardMethod2.setOnClickListener(this);
        cardMethod3.setOnClickListener(this);
        cardMethod4.setOnClickListener(this);

    }

    private void setupFormCM() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        formViewCM = inflater.inflate(R.layout.form_fragment_cm,null);
        final XEditText ee = (XEditText) formViewCM.findViewById(R.id.ee);
        FancyButton btnCalculate = (FancyButton) formViewCM.findViewById(R.id.btn_calculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TableActivityCM.class);
                i.putExtra("SEMILLA",ee.getText().toString());
                startActivity(i);
            }
        });
    }



    private void setupFormMIX() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        formViewMIX = inflater.inflate(R.layout.form_fragment_mix,null);
        final XEditText txtSemilla = (XEditText) formViewMIX.findViewById(R.id.txt_semilla);
        final XEditText txtMultiply = (XEditText) formViewMIX.findViewById(R.id.txt_multiply);
        final XEditText txtCtte= (XEditText) formViewMIX.findViewById(R.id.txt_ctte);
        final XEditText txtModule= (XEditText) formViewMIX.findViewById(R.id.txt_module);
        FancyButton btn_calculate = (FancyButton) formViewMIX.findViewById(R.id.btn_calculate);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TableActivityMIX.class);
                i.putExtra("SEMILLA",txtSemilla.getText().toString());
                i.putExtra("MULTIPLY",txtMultiply.getText().toString());
                i.putExtra("CTTE",txtCtte.getText().toString());
                i.putExtra("MODULE",txtModule.getText().toString());
                startActivity(i);
            }
        });
    }

    private void setupFormCNG() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        formViewCNG = inflater.inflate(R.layout.form_fragment_cng,null);
        final XEditText txtSemilla = (XEditText) formViewCNG.findViewById(R.id.txt_semilla);
        final XEditText txtMultiply = (XEditText) formViewCNG.findViewById(R.id.txt_multiply);
        final XEditText txtModule= (XEditText) formViewCNG.findViewById(R.id.txt_module);
        FancyButton btn_calculate = (FancyButton) formViewCNG.findViewById(R.id.btn_calculate);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TableActivityCNG.class);
                i.putExtra("SEMILLA",txtSemilla.getText().toString());
                i.putExtra("MULTIPLY",txtMultiply.getText().toString());
                i.putExtra("MODULE",txtModule.getText().toString());
                startActivity(i);
            }
        });
    }

    private void setupFormSB() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        formViewSB = inflater.inflate(R.layout.form_fragment_sb,null);
        final XEditText txtSemilla = (XEditText) formViewSB.findViewById(R.id.txt_semilla);
        final XEditText txtMultiply = (XEditText) formViewSB.findViewById(R.id.txt_multiply);
        final XEditText txtModule= (XEditText) formViewSB.findViewById(R.id.txt_module);
        FancyButton btn_calculate = (FancyButton) formViewSB.findViewById(R.id.btn_calculate);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TableActivitySB.class);
                i.putExtra("SEMILLA",txtSemilla.getText().toString());
                i.putExtra("MULTIPLY",txtMultiply.getText().toString());
                i.putExtra("MODULE",txtModule.getText().toString());
                startActivity(i);
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
                setupFormCM();
                bar = new BottomDialog.Builder(this)
                        .setCustomView(formViewCM)
                        .show();
                break;
            case R.id.card_method2:
                setupFormMIX();
                bar = new BottomDialog.Builder(this)
                        .setCustomView(formViewMIX)
                        .show();
                break;
            case R.id.card_method3:
                setupFormCNG();
                bar = new BottomDialog.Builder(this)
                        .setCustomView(formViewCNG)
                        .show();
                break;
            case R.id.card_method4:
                setupFormSB();
                bar = new BottomDialog.Builder(this)
                        .setCustomView(formViewSB)
                        .show();
                break;
            default:
                break;
        }
    }
}
