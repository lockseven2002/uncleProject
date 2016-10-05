package com.example.lockseven2002.uncle_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by lockseven2002 on 2016/6/27.
 *
 */
public class DetailItems extends AppCompatActivity {
    ListView lsItems;
    Button btnPrevious;
    Button btnUpdate;
    String strDetail;
    String strDate;
    String[] strTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_items);

        lsItems = (ListView)findViewById(R.id.listViewDetail);
        btnPrevious = (Button)findViewById(R.id.btnPrevious);
        btnUpdate = (Button)findViewById(R.id.btnModify);

        btnPrevious.setOnClickListener(new ClickEvent());
        btnUpdate.setOnClickListener(new ClickEvent());

        Intent inQueryId = this.getIntent();
        strDetail = inQueryId.getStringExtra("allDetail");
        strDate = inQueryId.getStringExtra("Date");
        String strSelectIdAndUrl = "http://10.0.2.2:8080/UncleReadAll.php?id="+
                                    strDetail+"&_createdate="+strDate;

        new AsynHttpConn().execute(strSelectIdAndUrl);
    }

    class ClickEvent implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent itn;
            switch (v.getId()){
                case R.id.btnPrevious:
                    itn = new Intent(DetailItems.this,TitleItems.class);
                    startActivity(itn);
                    finish();
                    break;
                default:
                    itn = new Intent(DetailItems.this,CommonUpdateDelete.class);
                    itn.putExtra("item",strTemp[0]);
                    itn.putExtra("count",strTemp[1]);
                    itn.putExtra("date",strTemp[2]);
                    itn.putExtra("Id",strDetail);
                    startActivity(itn);
                    finish();
                    break;
            }
        }
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

            /*======================Delete pass _Key parameter======================
            String[] strNoKeyArray = new String[httpJSON.endJSON(s).length];
            String[] strNoKeyArrayEnd = new String[strNoKeyArray.length];
            String strTmp = "";
            for(int i=0;i<httpJSON.endJSON(s).length;i++){
                strNoKeyArray = httpJSON.endJSON(s)[i].split("%");
                for(int j=0;j<strNoKeyArray.length-1;j++){
                    strTmp = strTmp+strNoKeyArray[j];
                }
                strNoKeyArrayEnd[i] = strTmp;
                strTmp = "";
            }
            ========================================================================*/

            ArrayAdapter<String> arrayAdp = new ArrayAdapter<String>(DetailItems.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        httpJSON.endJSON(s));
            lsItems.setAdapter(arrayAdp);

            lsItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String strSelectDetail = parent.getItemAtPosition(position).toString();
                    strTemp = strSelectDetail.split("/");
                    Toast.makeText(getApplicationContext(),strTemp[0]+"@"+
                            strTemp[1]+"@"+strTemp[2],1000).show();
                }
            });

        }
    }

}
