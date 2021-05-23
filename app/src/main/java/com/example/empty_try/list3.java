package com.example.empty_try;

/**
 * author: Jimmmy Smile
 * date: 2021-05-12 21:36::36
 * positioan: com.example.empty_try
 * e-mail:jimmysmileltl@163.com
 **/

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//implements AdapterView.OnItemClickListener AppCompatActivity
public class list3 extends ListActivity implements Runnable,AdapterView.OnItemClickListener {
    private static final String TAG = "list3log";
    private SimpleAdapter listItemAdapter;
    private HashMap<String, String> map;
    private ArrayList<HashMap<String, String>> listItems;
    private List<HashMap<String, String>> retlist;
    ListView lv3;
    Handler handler;
    MyAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initListView();
//        this.setListAdapter(listItemAdapter);
//        this.setListAdapter(adapter);
        Thread t = new Thread(this);
        t.start();
        handler = new Handler(Looper.myLooper()) {
            //Looper 循环器
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage:接收到" + msg.what);
                if (msg.what == 5) {
                    List<HashMap<String, String>> list = (List<HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(
                            list3.this,
                            list,
                            R.layout.list_item,
                            new String[]{"itemTitle", "itemDetail"},
                            new int[]{R.id.itemTitle, R.id.itemDetail});

                    setListAdapter(listItemAdapter);
                    Toast.makeText(list3.this, "resize=" + list.size(), Toast.LENGTH_SHORT);
                }
                super.handleMessage(msg);
            }
        };
        getListView().setOnItemClickListener(this);
    }
//    private  void initListView(){
//        listItems=new   ArrayList<HashMap<String, String>>();
//        for(int i=0;i<20;i++){
//            map=new HashMap<String, String>();
//            map.put("Itenmtitle","Rate="+String.valueOf(i));
//            map.put("itemDetail","detail="+String.valueOf(i));
//            listItems.add(map);
//        }
//        listItemAdapter=new SimpleAdapter(this,listItems,
//                                            R.layout.list_item,
//                                            new String[] {"ItenmTitle","itemDetail"},
//                                            new int[] {R.id.itemTitle,R.id.itemDetail});
//        adapter=new MyAdapter(this,R.layout.list_item,listItems);
//}
        public void run () {
            retlist = new ArrayList<HashMap<String, String>>();
            Document doc = null;
            final ArrayList<String> ret = new ArrayList<>();
            try {
                Thread.sleep(3000);
                doc = Jsoup.connect("https://www.usd-cny.com/bankofChina.htm").get();
                Log.i(TAG, "run:" + doc.title());
                //Element publictiime =doc.getElementsByClass("time").first();
                Elements tables = doc.getElementsByTag("table");
                Element table1 = tables.get(0);
                Log.i(TAG, "run: table1" + table1);
                //获取TD中的数据,按行取
                Elements trs = table1.getElementsByTag("tr");
                Elements tds = table1.getElementsByTag("td");
                for (int i = 0; i < tds.size(); i += 6) {
                    Element td1 = tds.get(i);
                    Element td2 = tds.get(i + 5);
                    Log.i(TAG, "run:" + td1.text() + "==>" + td2.text());
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("itemTitle", td1.text());
                    map.put("itemDetail", td2.text());
                    retlist.add(map);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = handler.obtainMessage(5, retlist);
            msg.what = 5;
            msg.obj = retlist;
            handler.sendMessage(msg);
        }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick: ccccccccccc");
        Object itemAtPosition =getListView().getItemAtPosition(position);
        HashMap<String, String> map = (HashMap<String, String>) itemAtPosition;
        String titlestr = map.get("itemTitle");
        String detailstr = map.get("itemDetail");
        Log.i(TAG, "onItemClick: titlestr=" + titlestr);
        Log.i(TAG, "onItemClick: detailstr=" + detailstr);
        Intent intent =new Intent(this,list3_jisuan.class);
        intent.putExtra("RateName",titlestr);
        intent.putExtra("RateValue",detailstr);
        startActivity(intent);

    }
}





//        Thread t = new Thread((this);
//        t.start();
//        setContentView(R.layout.huilv_list);
//        ListView listView = findViewById(R.id.list);
//        handler = new Handler(Looper.myLooper()) {
//            //Looper 循环器
//            public void handleMessage(@NonNull Message msg) {
//                Log.i(TAG, "handleMessage:接收到" + msg.what);
//                if (msg.what == 5) {
//                    ArrayList<String> res = (ArrayList<String>) msg.obj;
//                    ListAdapter adapter = new ArrayAdapter<String>(
//                            list3.this,
//                            android.R.layout.simple_list_item_1, res
//                    );
//                    listView.setAdapter(adapter);
//                    Toast.makeText(list3.this, "resize=" + res.size(), Toast.LENGTH_SHORT);
//                }
//                super.handleMessage(msg);
//            }
//        };
//        List<String> list1 = new ArrayList();
//
//    }
//    public void run() {
//        List<String> retList=new ArrayList<String>();
//        Document doc=null;
//        final ArrayList<String>ret=new ArrayList<>();
//        try {
//            doc = Jsoup.connect("https://www.usd-cny.com/bankofChina.htm").get();
//            Log.i(TAG,"run:"+doc.title());
//            //Element publictiime =doc.getElementsByClass("time").first();
//            Elements tables=doc.getElementsByTag("table");
//            Element table1=tables.get(0);
//            Log.i(TAG,"run: table1"+ table1);
//            //获取TD中的数据,按行取
//            Elements trs=table1.getElementsByTag("tr");
//            Elements tds=table1.getElementsByTag("td");
//            for(int i=0;i<tds.size();i+=6) {
//                Element td1 = tds.get(i);
//                Element td2 = tds.get(i + 5);
//                Log.i(TAG, "run:" + td1.text() + "==>" + td2.text());
//                ret.add(td1.text()+"==>"+td2.text());
//            }
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
//        Message msg=handler.obtainMessage(5,ret);
//        //msg.what=5
//        handler.sendMessage(msg);
//    }

//        setContentView(R.layout.list3);
//        lv3=findViewById(R.id.list);
////        listItems =new ArrayList<HashMap<String,String>>();
//        map=new HashMap<String,String>();
//
//
//        }
//       adapter =new MyAdapter(list3.this, R.layout.list_item, map);
//        lv3.setAdapter(adapter);
//
//    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.i(TAG, "onItemClick: ccccccccccc");
//        Object itemAtPosition=lv3.getItemAtPosition(position);
//        HashMap<String,String> map =(HashMap<String, String>)itemAtPosition;
//        String titlestr=map.get("ItemTitle");
//        String detailstr=map.get("ItemDetail");
//        Log.i(TAG, "onItemClick: titlestr="+titlestr);
//        Log.i(TAG, "onItemClick: detailstr="+detailstr);
//    }

