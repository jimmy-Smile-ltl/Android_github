package com.example.empty_try;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * author: Jimmmy Smile
 * date: 2021-04-15 19:20::20
 * positioan: com.example.empty_try
 * e-mail:jimmysmileltl@163.com
 **/
public class two_scorers extends Activity {
    int A=0;
    int B=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_scorers);
    }
    private  void show(){
        TextView a=findViewById(R.id.textViewA);
        a.setText(String.valueOf(A));
    }
    private  void showb(){
        TextView b=findViewById(R.id.textViewB);
        b.setText(String.valueOf(B));
    }
    public  void btn3(View v){
        A+=3;
        show();
    }
    public   void btn2(View v){
        A+=2;
        show();
    }
    public   void btn1(View v){
        A+=1;
        show();
    }

    public   void btnb3(View v){
        B+=3;
        showb();
    }
    public  void btnb2(View v){
        B+=2;
        showb();
    }
    public   void btnb1(View v){
        B+=1;
        showb();
    }
    public   void reset(View v){
        B=0;
        showb();
        A=0;
        show();
    }

}
