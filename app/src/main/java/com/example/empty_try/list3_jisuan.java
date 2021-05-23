package com.example.empty_try;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class list3_jisuan extends AppCompatActivity {
    private static final String TAG = "jisuanaaaaaa";
    String Ratename;
    String value;
    Float RateValue;
    TextView title;
    TextView show;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list3_jisuan);
        Ratename=getIntent().getStringExtra("RateName");
        value=getIntent().getStringExtra("RateValue");
        Log.i("list3_jisuan", "onCreate: "+Ratename+":   "+value);
        RateValue=Float.parseFloat(value);
        title=findViewById(R.id.list3_jisuan_title);
        show=findViewById(R.id.list3_jisuan_show);
        input=findViewById(R.id.list3_jisuan_input);
        title.setText(Ratename+"汇率换算为人民币");
        input.setHint("请输入   "+Ratename+"  数量");
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String foreign=input.getText().toString();
                if(foreign.length()>0){
                float temp=Float.parseFloat(foreign.trim());
                float result=temp*RateValue/100;
                show.setText(String.valueOf(result));
                Log.i(TAG, "afterTextChanged: 监听器执行完毕");
                }
                else{
                    show.setHint("请在上方输入输入"+Ratename+"数量");
                }

            }
        });
    }
    public   void list3_jisuan(View view){
        String foreign=input.getText().toString();
        float temp=Float.parseFloat(foreign.trim());
        float result=temp*RateValue/100;
        show.setText(String.valueOf(result));
        
        Log.i("list3_jisuan", "list3_jisuan:执行完毕 ");
    }
}