package com.example.empty_try;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * author: Jimmmy Smile
 * date: 2021-04-07 19:15::15
 * positioan: com.example.empty_try
 * e-mail:jimmysmileltl@163.com
 **/

/**
 * ghadgaj
 */
public class score extends AppCompatActivity {
int score=0;
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorer);
    }
    private  void show(){
        TextView out=findViewById(R.id.show);
        out.setText(String.valueOf(score));
    }
    private  void add2(View v){
        score+=2;
        show();
    }
    private  void add1(View v){
        score+=1;
        show();
    }private  void reset(View v){
        score=0;
        show();
    }
}