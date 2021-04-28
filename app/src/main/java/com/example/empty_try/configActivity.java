package com.example.empty_try;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class configActivity extends AppCompatActivity {
    public final String TAG = "aaaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Intent in = getIntent();
        double usdRate = in.getDoubleExtra("usd_save", 1.0);
        double eurRate = in.getDoubleExtra("eur_save", 2.0);
        double janpRate = in.getDoubleExtra("janp_save", 3.0);
        double hkRate = in.getDoubleExtra("hk_save", 4.0);
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

        double musd = Double.valueOf(String.valueOf(us.getText()));
        double meur = Double.valueOf(String.valueOf(eur.getText()));
        double mjanp = Double.valueOf(String.valueOf(janp.getText()));
        double mhk = Double.valueOf(String.valueOf(hk.getText()));

        Intent re = getIntent();
        Bundle bd1 = new Bundle();
        bd1.putDouble("usd", musd);
        bd1.putDouble("eur", meur);
        bd1.putDouble("janp", mjanp);
        bd1.putDouble("hk", mhk);
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
 * double musd=Double.valueOf(String.valueOf(us.getText()));
 * double meur=Double.valueOf(String.valueOf(eur.getText()));
 * double mjanp=Double.valueOf(String.valueOf(janp.getText()));
 * double mhk=Double.valueOf(String.valueOf(hk.getText()));
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
