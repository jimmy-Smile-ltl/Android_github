package com.example.empty_try;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GridViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private  final  String TAG="aaaaaaaaa";
    TextView nodata;
    ListAdapter adapter;
    List<String> listItems;
    GridView grid;
    HashMap<String,String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        grid = findViewById(R.id.gridview);
        nodata=findViewById(R.id.noDataTip);
        listItems = new ArrayList<String>();
        for(int i=0;i<50;i++){
            listItems.add("item"+String.valueOf(i))
        }
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        grid.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String,String> temo=(HashMap<String,String>)grid.getItemAtPosition(position);
        Log.i(TAG, "onItemClick:"+temo.get("left"));

        listItems.remove(position);
        adapter.notify();
    }
}