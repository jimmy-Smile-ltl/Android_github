package com.example.empty_try;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * author: Jimmmy Smile
 * date: 2021-04-15 0:01::01
 * positioan: com.example.empty_try
 * e-mail:jimmysmileltl@163.com
 **/
public class two_scorer1 extends Activity {
    int scoreA=0;
    int scoreB=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_scorer1);
        //two_scorer1与two_scorer2皆可
    }
    private  void show(){
        TextView a=findViewById(R.id.show);
        a.setText(String.valueOf(scoreA));
    }
    private  void showb(){
        TextView b=findViewById(R.id.showb);
       b.setText(String.valueOf(scoreB));
    }
    public  void add3(View v){
        scoreA+=3;
        show();
    }
    public   void add2(View v){
        scoreA+=2;
        show();
    }
    public   void add1(View v){
        scoreA+=1;
        show();
    }

    public   void addb3(View v){
        scoreB+=3;
    }
    public  void addb2(View v){
        scoreB+=2;
        showb();
    }
    public   void addb1(View v){
        scoreB+=1;
        showb();
    }
    public   void reset(View v){
        scoreB=0;
        showb();
        scoreA=0;
        show();
    }

}
