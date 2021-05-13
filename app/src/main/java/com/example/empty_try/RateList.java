package com.example.empty_try;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * author: Jimmmy Smile
 * date: 2021-05-12 20:26::26
 * positioan: com.example.empty_try
 * e-mail:jimmysmileltl@163.com
 * 有待改进
 **/
public class RateList extends ListActivity implements Runnable {
    private static String TAG= "aaaaaaa";
    String data[];
    Handler handler;
    ListView show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        show = findViewById(R.id.list);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huilv_list);

        Thread t=new Thread(this);
        t.start();
        setContentView(R.layout.huilv_list);
        ListView listView=findViewById(R.id.list);

        handler=new Handler() {
            ListAdapter adapter = new ArrayAdapter<String>(RateList.this, android.R.layout.simple_list_item_1, data);
            //Log.i(TAG,"onCreate: ");
            // setListAdapter(adapter);
        }
    }}
    }




    public void run(){
        Log.i(TAG, "getData: getData方法开始调用");
        data = new String[30];
        Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj").get();
        Log.i(TAG, "run: " + doc.title()+"\n"+doc.text());
        Elements tables = doc.getElementsByTag("table");
        Element table = tables.get(1);
        Log.i(TAG, "\n\n\nrun: " + table.html()+"\n"+table.text());
        Log.i(TAG, "getData: " + table.text());
        Elements trs = table.getElementsByTag("tr");
        for (Element tr : trs) {
            Log.i(TAG, "\n\n\nrun: " + tr.html()+"\n"+tr.text());
            Elements tds = tr.getElementsByTag("td");
            for (int i = 0; i < tds.size(); i += 8) {
                Element td1 = tds.get(i);
                Element td2 = tds.get(i + 5);
                String str1 = td1.text();
                String str2 = td2.text();
                String info = str1 + "=====>>" + str2;
                int count = 0;
                data[count] = info;
                count++;
                Log.i(TAG, "run: " + str1 + "=====>>" + str2);
            }

        }
        Log.i(TAG, "getData: 爬取结束\n\n");

    }
    public String [] getData(){
        for(int i = 0; i<30; i+=1)
        {
            Log.i(TAG, "onCreate: " + data[i]);
            Log.i(TAG, "onCreate: " + i);
        }
        Log.i(TAG,"onCreate:循环成功 ");
        return data;
    }


}




//        Thread t = new Thread(this);
//        t.start();
//        handler = new Handler(Looper.myLooper()) {
//            public void handleMessage(Message msg) {
//                if (msg.what == 7) {
//                    List<String> list2 = (List<String>) msg.obj;
//                    ListAdapter adapter = new ArrayAdapter<String>(RateList.this,
//                            R.layout.huilv_list,
//                            list2
//                    );
//                    setListAdapter(adapter);
//                }
//                super.handleMessage(msg);
//            }
//        };
//    }
//    public void run () {
//        List<String> data = new ArrayList<String>();
//        try {
//
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
//        Message msg = new Message();
//        msg.obj=data;
//        msg.what=7;
//        handler.sendMessage(msg);
//
//    }
//}
