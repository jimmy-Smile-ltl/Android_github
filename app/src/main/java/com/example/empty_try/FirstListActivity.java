package com.example.empty_try;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirstListActivity extends ListActivity  implements Runnable {
    public final String TAG = "aaaaaaa";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_first_list);
        //String [] data_list={"one","two","three","four"};

//        List<String> list1 =getData();
//        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
//        setListAdapter(adapter);


        Thread t = new Thread(this);
        t.start();
        handler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    Log.i(TAG, "handleMessage: 数据传输成功");
                    List<String> list2 = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(FirstListActivity.this,
                            android.R.layout.simple_list_item_1,
                            list2
                    );
                    setListAdapter(adapter);
                    Log.i(TAG, "handleMessage: 任务成功，查看手机吧");
                }
                super.handleMessage(msg);
            }
        };
    }
    public void run () {
        Log.i(TAG, "run: run方法成功运行");
        List<String> data = new ArrayList<String>();
        try {
           Thread.sleep(10000);
            Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj").get();
            Log.i(TAG, "run: " + doc.title());
            Elements tables = doc.getElementsByTag("table");
            Element table = tables.get(1);
            Elements trs = table.getElementsByTag("tr");
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                for (int i = 0; i < tds.size(); i += 8) {
                    Element td1 = tds.get(i);
                    Element td2 = tds.get(i + 5);
                    String str1 = td1.text();
                    String str2 = td2.text();
                    String info = str1 + "==>>" + str2;
                    data.add(info);
                    Log.i(TAG, "run: " + str1 + "=====>>" + str2);
                }
            }
            Log.i(TAG, "run: 数据爬取成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NetworkOnMainThreadException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message msg = new Message();
        msg.obj=data;
        msg.what=7;
        handler.sendMessage(msg);

    }
}
//    public List<String> getdata(){
//        List<String> data = new ArrayList<String>();
//        try {
//            Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj").get();
//            Log.i(TAG, "run: " + doc.title());
//            Elements tables = doc.getElementsByTag("table");
//            Element table = tables.get(1);
//            Elements trs = table.getElementsByTag("tr");
//            for (Element tr : trs) {
//                Elements tds = tr.getElementsByTag("td");
//                for (int i = 0; i < tds.size(); i += 8) {
//                    Element td1 = tds.get(i);
//                    Element td2 = tds.get(i + 5);
//                    String str1 = td1.text();
//                    String str2 = td2.text();
//                    String info = str1 + "=====>>" + str2;
//                    data.add(info);
//                    Log.i(TAG, "run: " + str1 + "=====>>" + str2);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NetworkOnMainThreadException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
