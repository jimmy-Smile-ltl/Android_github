package com.example.empty_try;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class huilv extends AppCompatActivity {
    EditText et;
    String rmb;
    String foreignB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huilv);
    }


    public void eur(View v){
        et=findViewById(R.id.rmb);
        foreignB=String.valueOf(et.getText());
        if(foreignB==null||foreignB.length()==0){
            et.setText("输入不合法");
        }
        else {
            double a = Double.valueOf(foreignB);
            double us = a /7.786;
            et.setText(String.valueOf(us));
        }
    }
    public void janp(View v){
        if(foreignB==null||foreignB.length()==0){
            et.setText("输入不合法");
        }
        else {
            et = findViewById(R.id.rmb);
            foreignB = String.valueOf(et.getText());
            double a = Double.valueOf(foreignB);
            double janp = a /0.059;
            et.setText(String.valueOf(janp));
        }
    }
    public void hk(View v){
        et=findViewById(R.id.rmb);
        foreignB=String.valueOf(et.getText());
        if(foreignB==null||foreignB.length()==0){
            et.setText("输入不合法");
        }
        else {
            double a = Double.valueOf(foreignB);
            double hk = a /0.8373;
            et.setText(String.valueOf(hk));
        }
    }

    public void usd(View v){
        et=findViewById(R.id.rmb);
        foreignB=String.valueOf(et.getText());
        if(foreignB==null||foreignB.length()==0){
            et.setText("输入不合法");
        }
        else {
            double a = Double.valueOf(foreignB);
            double usd = a / 6.5;
            et.setText(String.valueOf(usd));
        }
    }


}