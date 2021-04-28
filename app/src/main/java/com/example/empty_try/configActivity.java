package com.example.empty_try;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class configActivity extends AppCompatActivity {
    public final String TAG = "bbbbbbbbb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Intent in = getIntent();
        float usdRate = in.getFloatExtra("usd_save", 1.0f);
        float eurRate = in.getFloatExtra("eur_save", 2.0f);
        float janpRate = in.getFloatExtra("janp_save", 3.0f);
        float hkRate = in.getFloatExtra("hk_save", 4.0f);
        Log.i(TAG, "onCreate: " + usdRate);

        EditText us = findViewById(R.id.us_save);
        EditText eur = findViewById(R.id.eur_save);
        EditText hk = findViewById(R.id.hk_save);
        EditText janp = findViewById(R.id.janp_save);


        us.setText(String.valueOf(usdRate));
        janp.setText(String.valueOf(janpRate));
        eur.setText(String.valueOf(eurRate));
        hk.setText(String.valueOf(hkRate));
        String a = String.valueOf(janpRate);
    }


    public void save(View v) {
        EditText us = findViewById(R.id.us_save);
        EditText eur = findViewById(R.id.eur_save);
        EditText hk = findViewById(R.id.hk_save);
        EditText janp = findViewById(R.id.janp_save);

        float musd = Float.parseFloat(String.valueOf(us.getText()));
        float meur = Float.parseFloat(String.valueOf(eur.getText()));
        float mjanp = Float.parseFloat(String.valueOf(janp.getText()));
        float mhk = Float.parseFloat(String.valueOf(hk.getText()));


        SharedPreferences share = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor_save = share.edit();
        editor_save.putFloat("usRate", musd);
        editor_save.putFloat("janpRate", mjanp);
        editor_save.putFloat("eurRate", meur);
        editor_save.putFloat("hkRate", mhk);
        editor_save.apply();

        Log.i(TAG, "hkrate=" + mhk);
        Log.i(TAG, "janprate=" + mjanp);
        Log.i(TAG, "usdrate=" + musd);
        Log.i(TAG, "eurrate=" + meur);

        Intent re = getIntent();
        Bundle bd1 = new Bundle();
        bd1.putFloat("usd", musd);
        bd1.putFloat("eur", meur);
        bd1.putFloat("janp", mjanp);
        bd1.putFloat("hk", mhk);
        re.putExtras(bd1);
        setResult(2, re);
        finish();


    }
}
/**
 * EditText us=findViewById(R.id.us_save);
 * EditText eur=findViewById(R.id.eur_save);
 * EditText hk=findViewById(R.id.hk_save);
 * EditText janp=findViewById(R.id.janp_save);
 * float musd=float.valueOf(String.valueOf(us.getText()));
 * float meur=float.valueOf(String.valueOf(eur.getText()));
 * float mjanp=float.valueOf(String.valueOf(janp.getText()));
 * float mhk=float.valueOf(String.valueOf(hk.getText()));
 * config.putExtra("usd_save",musd);
 * config.putExtra("eur_save",meur);
 * config.putExtra("janp_save",mjanp);
 * config.putExtra("hk_save",mhk);
 * <p>
 * <p>
 * //        Intent config = new Intent(this, huilv.class);
 * //        startActivity(config);
 * //        config.putExtra("usd_save",musd);
 * //        config.putExtra("eur_save",meur);
 * //        config.putExtra("janp_save",mjanp);
 * //        config.putExtra("hk_save",mhk);
 * <p>
 * }
 **/
