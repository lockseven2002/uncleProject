package com.example.lockseven2002.uncle_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user-pc on 2016/7/25.
 */
public class BaseAdp extends BaseAdapter {
    Context context;
    String strSelectNameAll[];
    String strId[];
    LayoutInflater layoutInflater;
    ListViewHolder listViewHolder;
    private int intCount[];
    //JSONObject jsObjCount = new JSONObject();

    public BaseAdp(){

    }
    public BaseAdp(Context applicationContext, String[] strSelectNameAll, String[] strId) {
        this.context = applicationContext;
        this.strSelectNameAll = strSelectNameAll;
        this.strId = strId;
        intCount = new int[strSelectNameAll.length];
        for(int i=0;i<strSelectNameAll.length;i++){
            intCount[i] = 0;
        }
    }

    public int[] getIntCount(){

        return  intCount;
    }


    @Override
    public int getCount() {
        return strSelectNameAll.length;
    }

    @Override
    public Object getItem(int i) {return null;}

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position,View view, ViewGroup viewGroup) {
        final View row;
        if(view == null){
            layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.base_adp,null);
            listViewHolder = new ListViewHolder();
            listViewHolder.textName = (TextView)row.findViewById(R.id.tvName);
            listViewHolder.tvCount = (TextView)row.findViewById(R.id.tvCount);
            listViewHolder.btnAdd = (Button)row.findViewById(R.id.btnUp);
            listViewHolder.btnLess = (Button)row.findViewById(R.id.btnDown);
            listViewHolder.id = strId;
            row.setTag(listViewHolder);
        }else{
            row = view;
            listViewHolder = (ListViewHolder)view.getTag();
        }
        listViewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewHolder = (ListViewHolder) row.getTag();
                //將原始陣列內容更換至position所指定位置的tvCount內容,由上面程式取得
                intCount[position] = Integer.parseInt(listViewHolder.tvCount.getText().toString())+1;
                listViewHolder.tvCount.setText(intCount[position]+"");
                //Toast.makeText(context,strId[position],Toast.LENGTH_LONG).show();
            }
        });
        listViewHolder.btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewHolder = (ListViewHolder)row.getTag();
                if(Integer.parseInt(listViewHolder.tvCount.getText().toString())!=0){
                    intCount[position] = Integer.parseInt(listViewHolder.tvCount.getText().toString())-1;
                    listViewHolder.tvCount.setText(intCount[position]+"");
                   //Toast.makeText(context,strId[position],Toast.LENGTH_LONG).show();
                }else{
                    intCount[position] = 0;
                    listViewHolder.tvCount.setText(intCount[position]+"");
                    //Toast.makeText(context,strId[position],Toast.LENGTH_LONG).show();
                }
            }
        });

        listViewHolder.textName.setText(strSelectNameAll[position]);
        listViewHolder.tvCount.setText(intCount[position]+"");
        return row;
    }
    class ListViewHolder{
        TextView textName;
        TextView tvCount;
        Button btnAdd;
        Button btnLess;
        String[] id;
    }

}

