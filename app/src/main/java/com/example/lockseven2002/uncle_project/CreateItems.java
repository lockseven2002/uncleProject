package com.example.lockseven2002.uncle_project;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user-pc on 2016/7/25.
 */
public class CreateItems extends AppCompatActivity {

    String[] strSelectNameAll;
    ListView lsAllProject;
    String[] strId;
    TextView test;
    Button create;
    BaseAdp baCount = null;
    int[] intCount;
    String strJson = "";


    //JSONObject jsObjIDCount = new JSONObject();
    //JSONArray jsonArray = new JSONArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_count);
        new AsynHttpConn().execute("http://10.0.2.2:8080/UncleSelectDetails.php");
        test = (TextView) findViewById(R.id.textView);
        lsAllProject = (ListView) findViewById(R.id.listProject);
        create = (Button) findViewById(R.id.btnNew);

        create.setOnClickListener(new View.OnClickListener() {
            int i;

            @Override
            public void onClick(View v) {
                try {
                    //自定義JSON格式
                    intCount = baCount.getIntCount();
                    for (i = 0; i < strSelectNameAll.length - 1; i++) {
                        strJson = strJson + "\"" + "countID" + i + "\":" +
                                  "[" + strId[i] + "," + intCount[i] + "],";
                    }
                    strJson = "{" + strJson + "\"" + "countID"+ i + "\":" +
                              "[" + strId[i] + "," + intCount[i] + "]}";




                /*此方法因jsonArray是物件,所以當jsonArray.remove(0);,
                //執行後下個迴圈的jsObjIDCount.put(String.valueOf(i),jsonArray)內的jsonArray會是空值
                for(i=0;i<strSelectNameAll.length;i++){
                    jsonArray.put(strId[i]);
                    jsonArray.put(intCount[i]);
                    jsObjIDCount.put(String.valueOf(i),jsonArray);
                //未執行jsonArray.remove(0)時;jsonArray==>[strID[i] ,intCount[i]]
                    jsonArray.remove(0);  //第一次執行完jsonArray.remove(0);jsonArray==>[intCount[i]]
                    jsonArray.remove(0);  //第二次執行完jsonArray.remove(0);jsonArray==>[]
                }
                */


                    //strJson[strSelectNameAll.length-1] = i + ":["+intCount[i]+","+strId[i]+"]";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new AsynCreateHttpConn().execute("http://10.0.2.2:8080/UncleCreateProduct.php", strJson);
                //try {
                //Toast.makeText(CreateItems.this,jsObjIDCount.getString("44")+"/"+strId[0]
                //,Toast.LENGTH_LONG).show();
                //} catch (JSONException e) {
                //e.printStackTrace();
                //}
                //Toast.makeText(CreateItems.this,count[0]+"/"+count[1]+"/"+strId[0]+"/"+strId[1]+"/"
                //,Toast.LENGTH_LONG).show();

            }
        });

    }



    class AsynHttpConn extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpJSON httpConn = new HttpJSON();
            return httpConn.endConn(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            HttpJSON httpJSON = new HttpJSON();
            int x = httpJSON.endJSON(s)[0].split("/").length;
            String[] strNameId;
            String[] strAll = new String[httpJSON.endJSON(s).length * x];
            String strTmp;
            int z = 0;
            for (int i = 0; i < httpJSON.endJSON(s).length; i++) {
                strNameId = httpJSON.endJSON(s)[i].split("/");
                for (int j = 0; j < strNameId.length; j++) {
                    strTmp = strNameId[j];
                    strAll[z] = strTmp;
                    z++;
                }
            }
            int y = 0;
            String strTemp1;
            strSelectNameAll = new String[strAll.length / 2];
            for (int i = 0; i < strAll.length; i = i + 2) {
                strTemp1 = strAll[i];
                strSelectNameAll[y] = strTemp1;
                y++;
            }
            strId = new String[strAll.length / 2];
            int r = 0;
            String strTemp2;
            for (int i = 1; i < strAll.length; i = i + 2) {
                strTemp2 = strAll[i];
                strId[r] = strTemp2;
                r++;
            }
            //strJson = new String[strSelectNameAll.length*2];
            BaseAdp ba = new BaseAdp(CreateItems.this, strSelectNameAll, strId);
            baCount = ba;
            lsAllProject.setAdapter(ba);
        }
    }

    class AsynCreateHttpConn extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {
            //BufferedReader reader = null;
            HttpURLConnection urlConnection;
            URL url;
            StringBuilder result = new StringBuilder();
            try {

                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                result.append(URLEncoder.encode("products","UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(params[1],"UTF-8"));
                writer.write(result.toString());
                writer.flush();
                writer.close();
                os.close();
                urlConnection.connect();
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
            try {
                int response_code = urlConnection.getResponseCode();
                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder resultResponse = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        resultResponse.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(resultResponse.toString());



                } else {

                    return "unsuccessful";
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                urlConnection.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            test.setText(s);
        }
    }


}

