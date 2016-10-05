package com.example.lockseven2002.uncle_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user-pc on 2016/7/21.
 */
public class CommonUpdateDelete extends AppCompatActivity {
    Button btnConfirm;
    Button btnRe;
    TextView tvPj;
    EditText edCount;
    TextView tvDa;
    String strSelectId;
    String strItem;
    String strCount;
    String strDate;
    String strURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_update_delete);

        btnConfirm = (Button)findViewById(R.id.btnOk);
        btnRe = (Button)findViewById(R.id.btnReSet);
        tvPj = (TextView)findViewById(R.id.tvProject);
        edCount = (EditText) findViewById(R.id.edCountShow);
        tvDa = (TextView)findViewById(R.id.tvDate);

        Intent itnData = this.getIntent();

        tvPj.setText(itnData.getStringExtra("item").toString());
        edCount.setText(itnData.getStringExtra("count").toString());
        tvDa.setText(itnData.getStringExtra("date").toString());

        strItem = itnData.getStringExtra("item").toString();
        strCount = itnData.getStringExtra("count").toString();
        strSelectId = itnData.getStringExtra("Id").toString();
        strDate = itnData.getStringExtra("date").toString();

        btnRe.setOnClickListener(new ClickEvent());
        btnConfirm.setOnClickListener(new ClickEvent());
    }

    class ClickEvent implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnOk:
                    strURL = "http://10.0.2.2:8080/UncleUpdate.php?"+
                            "updateamount="+edCount.getText()+"&id="+strSelectId+
                            "&_createdate="+strDate+"&item="+strItem+"&count="+strCount;
                    new AsynHttpConn().execute(strURL);
                    Intent itn = new Intent(getApplicationContext(),DetailItems.class);
                    itn.putExtra("allDetail",strSelectId);
                    itn.putExtra("Date",strDate);
                    startActivity(itn);
                    finish();
                    //Toast.makeText(CommonUpdateDelete.this,strURL,Toast.LENGTH_LONG).show();
                    break;
                default:
                    Intent itnBreak = new Intent(getApplicationContext(),DetailItems.class);
                    itnBreak.putExtra("allDetail",strSelectId);
                    itnBreak.putExtra("Date",strDate);
                    startActivity(itnBreak);
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
        }
    }
}
