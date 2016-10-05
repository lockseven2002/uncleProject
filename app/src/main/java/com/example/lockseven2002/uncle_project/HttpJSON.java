package com.example.lockseven2002.uncle_project;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user-pc on 2016/7/21.
 */
public class HttpJSON {
    public String endConn(String strUrl) {
        HttpURLConnection httpConn;
        StringBuffer strBuf = new StringBuffer();
        String strLine;
        URL url;
        try {
            url = new URL(strUrl);
            httpConn = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpConn.getInputStream();
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((strLine = bufReader.readLine()) != null) {
                strBuf.append(strLine);
            }
            return strBuf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return strUrl;
        }
    }

    public String[] endJSON(String strJSON) {
        JSONObject jsObjParent;
        JSONArray jsArrayParent;
        JSONObject jsObjChild;
        String[] strSplitEnd;
        String[] arrTestErr = {"Not exists!!!"};
        try {
            jsObjParent = new JSONObject(strJSON);
            jsArrayParent = jsObjParent.getJSONArray("products");
            jsObjParent = jsArrayParent.getJSONObject(0);
            strSplitEnd = new String[jsArrayParent.length()];
            if(jsObjParent.has("detailsname")){
                if(jsObjParent.has("details_key")){
                    //撈產品名稱,產品鍵值(DetailItems)
                    for(int i=0;i<jsArrayParent.length();i++){
                        jsObjChild = jsArrayParent.getJSONObject(i);
                        strSplitEnd[i] = jsObjChild.getString("detailsname")+"/"+
                                         jsObjChild.getString("details_key");
                    }
                }else{
                    //撈產品名稱,日期,數量(CommonUpdateDelete)
                    for (int i=0; i<jsArrayParent.length(); i++) {
                        jsObjChild = jsArrayParent.getJSONObject(i);
                        strSplitEnd[i] = jsObjChild.getString("detailsname") +"/"+
                                jsObjChild.getString("amount") +"/"+
                                jsObjChild.getString("_createdate");
                    }
                }
            }else{
                //撈日期(TitleItems)
                for (int i=0; i<jsArrayParent.length(); i++) {
                    jsObjChild = jsArrayParent.getJSONObject(i);
                    strSplitEnd[i] = jsObjChild.getString("_createdate");
                }

            }
            return strSplitEnd;
            }catch(Exception e){
                e.printStackTrace();
                return  arrTestErr;
            }
    }
}