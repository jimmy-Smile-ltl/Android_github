package com.example.empty_try;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * author: Jimmmy Smile
 * date: 2021-05-19 19:59::59
 * positioan: com.example.empty_try
 * e-mail:jimmysmileltl@163.com
 **/
public class MyAdapter extends ArrayAdapter{
    public  MyAdapter(Context context, int resourse, ArrayList<HashMap<String, String>> map){
        super(context,resourse, map);

    }
    public View getView(int position , View convertView, ViewGroup parent){
        View itemView=convertView;
        if(itemView==null){
            itemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Map<String,String> map =(Map<String,String>)getItem(position);
        TextView title =(TextView)itemView.findViewById(R.id.itemTitle);
        TextView detail =(TextView)itemView.findViewById(R.id.itemDetail);

        title.setText("myTitle"+map.get("ItemTitle"));
        detail.setText("mydetail"+map.get("ItemDetail"));
        return  itemView;
    }
}
