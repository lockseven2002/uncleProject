package com.example.lockseven2002.uncle_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lockseven2002 on 2016/6/27.
 * 123456879
 */
public class DetailItems extends AppCompatActivity {
    ListView lsItems;
    Button btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_items);

        lsItems = (ListView)findViewById(R.id.listViewDetail);
        btnPrevious = (Button)findViewById(R.id.btnPrevious);

        Intent inQueryId = this.getIntent();
        String strSelectIdAndUrl = "http://10.0.3.2:8080/UncleReadAll.php?id="+
                                   inQueryId.getStringExtra("FishQuery");
        new AsynHttpConn().execute(strSelectIdAndUrl);
    }

    class AsynHttpConn extends AsyncTask<String, String, String> {
        HttpURLConnection httpConn;
        StringBuffer strBuf = new StringBuffer();
        String strLine;
        JSONObject jsObjParent;
        JSONArray jsArrayParent;
        JSONObject jsObjChild;

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                httpConn = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpConn.getInputStream();
                BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
                while ((strLine = bufReader.readLine())!=null) {
                    strBuf.append(strLine);
                }
                return strBuf.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return params[0];
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                jsObjParent = new JSONObject(s);
                jsArrayParent = jsObjParent.getJSONArray("products");
                jsObjChild = new JSONObject();
                String[] strSplitEnd = new String[jsArrayParent.length()];
                for (int i = 0; i < jsArrayParent.length(); i++) {
                    jsObjChild = jsArrayParent.getJSONObject(i);
                    strSplitEnd[i] = jsObjChild.getString("detailsname")+
                            jsObjChild.getString("amount")+
                            jsObjChild.getString("_date");
                }
                ArrayAdapter<String> arrayAdp = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        strSplitEnd);
                lsItems.setAdapter(arrayAdp);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
