package com.example.empty_try;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class huilv extends AppCompatActivity {
    private static final String TAG = "huilv";
    String rmb;
    double mjanp = 0.059;
    double meur = 7.786;
    double musd = 6.5;
    double mhk = 0.8373;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huilv);
    }

    public void reset(View v) {
        EditText et = findViewById(R.id.rmb);
        String foreignB = String.valueOf(et.getText());
        et.setHint("input RMB");
    }

    public void eur(View v) {
        EditText et = findViewById(R.id.rmb);
        String foreignB = String.valueOf(et.getText());
        if (foreignB == null || foreignB.length() == 0) {
            et.setHint("输入不合法");
        } else {
            double a = Double.valueOf(foreignB);
            double us = a / meur;
            et.setText(String.valueOf(us));
        }
    }

    public void janp(View v) {
        EditText et = findViewById(R.id.rmb);
        String foreignB = String.valueOf(et.getText());
        if (foreignB == null || foreignB.length() == 0) {
            et.setHint("input RMB");
        } else {
            double a = Double.valueOf(foreignB);
            double janp = a / mjanp;
            et.setText(String.valueOf(janp));
        }
    }

    public void hk(View v) {
        EditText et = findViewById(R.id.rmb);
        String foreignB = String.valueOf(et.getText());
        if (foreignB == null || foreignB.length() == 0) {
            et.setHint("输入不合法");
        } else {
            double a = Double.valueOf(foreignB);
            double hk = a / mhk;
            et.setText(String.valueOf(hk));
        }
    }

    public void usd(View v) {
        EditText et = findViewById(R.id.rmb);
        String foreignB = String.valueOf(et.getText());
        if (foreignB == null || foreignB.length() == 0) {
            et.setHint("输入不合法");
        } else {
            double a = Double.valueOf(foreignB);
            double usd = a / musd;
            et.setText(String.valueOf(usd));
        }
    }

    public void open(View v) {
        Intent config = new Intent(this, configActivity.class);
        config.putExtra("usd_save", musd);
        config.putExtra("eur_save", meur);
        config.putExtra("janp_save", mjanp);
        config.putExtra("hk_save", mhk);
        startActivityForResult(config, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == 2) {
            Bundle bd1 = data.getExtras();
            musd = bd1.getDouble("usd", 1.0);
            meur = bd1.getDouble("eur", 2.0);
            mhk = bd1.getDouble("hk", 3.0);
            mjanp = bd1.getDouble("janp", 4.0);

        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}