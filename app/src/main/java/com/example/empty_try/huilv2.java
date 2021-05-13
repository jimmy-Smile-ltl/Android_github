package com.example.empty_try;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Date;

public class huilv2 extends AppCompatActivity implements Runnable {
    private static final String TAG = "aaaaaaa";
    TextView out;
    EditText et;
    float mjanp = 0.059f;
    float meur = 7.786f;
    float musd = 6.5f;
    float mhk = 0.8373f;
    URL url = null;
    String updateTime;
    Handler handler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huilv2);

        SharedPreferences read = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        //PreferenceManager.getDefaultSharedPreferences(this);
        mhk = read.getFloat("hkRate", 0.0f);
        mjanp = read.getFloat("janpRate", 0.0f);
        meur = read.getFloat("eurRate", 0.0f);
        musd = read.getFloat("usRate", 0.0f);

        Log.i(TAG, "hkrate=" + mhk);
        Log.i(TAG, "janprate=" + mjanp);
        Log.i(TAG, "usdrate=" + musd);
        Log.i(TAG, "eurrate=" + meur);

        Thread t = new Thread(this);
        t.start();
        handler = new Handler(Looper.myLooper()) {
            public void handMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    String str = (String) msg.obj;
                    Log.i(TAG, "handMessage: " + str);
                }
                super.handleMessage(msg);
            }
        };

    }

    public void run() {

        //try {
//            url = new URL("https://www.boc.cn/sourcedb/whpj/");
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            InputStream in = http.getInputStream();
//            String html = htmlString(in);
//            Log.i(TAG, "run: htm1 = " + html);
//            Message msg = handler.obtainMessage(1);
//            msg.obj = html;
//            handler.sendMessage(msg);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        Date now=new Date();
        if(now.toString().equals(updateTime)){
            try {
                //前提是程序不关闭
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj").get();
                Log.i(TAG, "run: " + doc.title());
                Elements tables = doc.getElementsByTag("table");
                Element table = tables.get(1);
                Elements trs = table.getElementsByTag("tr");
//            int count=0;
                for (Element tr : trs) {
//                count++;
//                Log.i(TAG, "run: "+count);
//                Elements tds = tr.getElementsByTag("td");
//                for (int i = 0; i < tds.size(); i+=8) {
//                    Element td1 = tds.get(i);
//                    Element td2 = tds.get(i+5);
//                    String str1 = td1.text();
//                    String str2 = td2.text();
//
//                    Log.i(TAG, "run: " + str1 +"=====>>" +str2);
//                }
                    Element trUS = trs.get(26);
                    Element trJANP = trs.get(12);
                    Element trHK = trs.get(9);
                    Element trEUR = trs.get(8);

                    Elements tdsUS = trUS.getElementsByTag("td");
                    Elements tdsJANP = trJANP.getElementsByTag("td");
                    Elements tdsHK = trHK.getElementsByTag("td");
                    Elements tdsEUR = trEUR.getElementsByTag("td");

                    String rateUS = tdsUS.get(5).text();
                    String rateJANP = tdsJANP.get(5).text();
                    String rateHK = tdsHK.get(5).text();
                    String rateEUR = tdsEUR.get(5).text();

                    musd = Float.parseFloat(rateUS) / 100;
                    mjanp = Float.parseFloat(rateJANP) / 100;
                    mhk = Float.parseFloat(rateHK) / 100;
                    meur = Float.parseFloat(rateEUR) / 100;

                    Log.i(TAG, "run: " + "美元汇率：" + rateUS + "\n 欧元汇率 ： " + rateEUR + "\n 港币汇率 ：" + rateHK + "\n 日元汇率" + rateJANP);
                    //需要全部除以100；

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            Log.i(TAG, "run: 今日日期："+now.toString()+"已经更新");

        }

    }

    public void open(View v) {
        Intent config = new Intent(this, configActivity.class);
        config.putExtra("usd_save", musd);
        config.putExtra("eur_save", meur);
        config.putExtra("janp_save", mjanp);
        config.putExtra("hk_save", mhk);
        Date today=new Date();
        updateTime=today.toString();
        config.putExtra("update",updateTime);
        Log.i(TAG, "open: update"+updateTime);
        Log.i(TAG, "run: float" + musd);
        Log.i(TAG, "run: float" + meur);
        Log.i(TAG, "run: float" + mjanp);
        Log.i(TAG, "run: float" + mhk);
        startActivityForResult(config, 1);
    }

    private String htmlString(InputStream inputStream) throws IOException {
        final int buffersize = 1024;
        final char[] buffer = new char[buffersize];
        final StringBuffer out = new StringBuffer();
        Reader in = new InputStreamReader(inputStream, "utf-8");
        while (true) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }


    public void jisuan(View btn) {
        et = findViewById(R.id.rmb);
        out = findViewById(R.id.output);
        String foreignB = et.getText().toString();
        float money = 0.0f;
        float temp = 0.0f;
        if (foreignB.length() > 0) {
            money = Float.parseFloat(foreignB);
        }
        if (btn.getId() == R.id.usd) {
            temp = money / musd;
            out.setText(String.valueOf(temp));
        } else if (btn.getId() == R.id.hk) {
            temp = money / mhk;
            out.setText(String.valueOf(temp));
        } else if (btn.getId() == R.id.japanse) {
            temp = money / mjanp;
            out.setText(String.valueOf(temp));
        } else if (btn.getId() == R.id.eur) {
            temp = money / meur;
            out.setText(String.valueOf(temp));
        } else
            out.setHint("请输入具体数据");
    }


//    public void open(View v) {
//        Intent config = new Intent(this, configActivity.class);
//        config.putExtra("usd_save", musd);
//        config.putExtra("eur_save", meur);
//        config.putExtra("janp_save", mjanp);
//        config.putExtra("hk_save", mhk);
//        startActivityForResult(config, 1);

//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == 2) {
            Bundle bd1 = data.getExtras();
            musd = bd1.getFloat("usd", 1.0f);
            meur = bd1.getFloat("eur", 2.0f);
            mhk = bd1.getFloat("hk", 3.0f);
            mjanp = bd1.getFloat("janp", 4.0f);


        }
        super.onActivityResult(requestCode, resultCode, data);

    }


}
