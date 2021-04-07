package com.example.empty_try;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class huilv extends AppCompatActivity {
    EditText et;
    String rmb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huilv);
    }
    public void jisuan(View v){
        et=findViewById(R.id.rmb);
        rmb=String.valueOf(et.getText());
        double a=Double.valueOf(rmb);
        double us=a*6.5;
        et.setText(String.valueOf(us));
    }


}