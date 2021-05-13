package com.example.empty_try;

/**
 * author: Jimmmy Smile
 * date: 2021-05-12 21:36::36
 * positioan: com.example.empty_try
 * e-mail:jimmysmileltl@163.com
 **/

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RateListActivity extends AppCompatActivity implements Runnable{
    private static final String TAG = "FirstListActivity";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread t=new Thread(this);
        t.start();
        setContentView(R.layout.huilv_list);
        ListView listView=findViewById(R.id.list);
        handler=new Handler(Looper.myLooper()){
            //Looper 循环器
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage:接收到" + msg.what);
                if (msg.what == 5) {
                    ArrayList<String>res=(ArrayList<String>) msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(
                            RateListActivity.this,
                            android.R.layout.simple_list_item_1,res
                    );
                    listView.setAdapter(adapter);
                    Toast.makeText(RateListActivity.this,"resize="+res.size(),Toast.LENGTH_SHORT);
                }
                super.handleMessage(msg);
            }
        };
        List<String>list1=new ArrayList();

    }
    public void run() {
        List<String> retList=new ArrayList<String>();
        Document doc=null;
        final ArrayList<String>ret=new ArrayList<>();
        try {
            doc = Jsoup.connect("https://www.usd-cny.com/bankofChina.htm").get();
            Log.i(TAG,"run:"+doc.title());
            //Element publictiime =doc.getElementsByClass("time").first();
            Elements tables=doc.getElementsByTag("table");
            Element table1=tables.get(0);
            Log.i(TAG,"run: table1"+ table1);
            //获取TD中的数据,按行取
            Elements trs=table1.getElementsByTag("tr");
            Elements tds=table1.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=6) {
                Element td1 = tds.get(i);
                Element td2 = tds.get(i + 5);
                Log.i(TAG, "run:" + td1.text() + "==>" + td2.text());
                ret.add(td1.text()+"==>"+td2.text());
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(5,ret);
        //msg.what=5
        handler.sendMessage(msg);
    }
}

