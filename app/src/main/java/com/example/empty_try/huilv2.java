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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class huilv2 extends AppCompatActivity implements Runnable {
    private static final String TAG = "aaaaaaa";
    TextView out;
    EditText et;
    float mjanp = 0.059f;
    float meur = 7.786f;
    float musd = 6.5f;
    float mhk = 0.8373f;
    URL url = null;

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
        try {
            url = new URL("https://www.boc.cn/sourcedb/whpj/");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();
            String html = htmlString(in);
            Log.i(TAG, "run: htm1 = " + html);
            Message msg = handler.obtainMessage(1);
            msg.obj = html;
            handler.sendMessage(msg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        String foreignB = String.valueOf(et.getText());
        float money = Float.parseFloat(foreignB);
        float temp = 0.0f;
        if (foreignB.length() > 0) {
            if (btn.getId() == R.id.usd)
                temp = money / musd;
            out.setText(String.valueOf(temp));
            if (btn.getId() == R.id.hk)
                temp = money / mhk;
            out.setText(String.valueOf(temp));
            if (btn.getId() == R.id.japanse)
                temp = money / mjanp;
            out.setText(String.valueOf(temp));
            if (btn.getId() == R.id.eur)
                temp = money / meur;
            out.setText(String.valueOf(temp));
        } else
            out.setHint("请输入具体数据");

    }


    public void open(View v) {
        Intent config = new Intent(this, configActivity.class);
        config.putExtra("usd_save", musd);
        config.putExtra("eur_save", meur);
        config.putExtra("janp_save", mjanp);
        config.putExtra("hk_save", mhk);
        startActivityForResult(config, 1);

    }

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
