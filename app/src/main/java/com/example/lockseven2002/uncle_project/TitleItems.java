package com.example.lockseven2002.uncle_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lockseven2002 on 2016/6/12.
 */
public class TitleItems extends AppCompatActivity {
    Button btnFish;
    Button btnPreviousPage;
    Button btnSushi;
    Spinner spDate;
    String strSpChance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_items);

        btnFish = (Button) findViewById(R.id.btnQueryFish);
        btnSushi = (Button)findViewById(R.id.btnQuerySushi);
        btnPreviousPage = (Button) findViewById(R.id.btnPrevious);
        spDate = (Spinner) findViewById(R.id.spDateShow);
        btnPreviousPage.setOnClickListener(new SelectBtn());
        btnFish.setOnClickListener(new SelectBtn());
        btnSushi.setOnClickListener(new SelectBtn());

        String strSelectIdAndUrl = "http://10.0.2.2:8080/UncleReadDate.php";
        new AsynHttpConn().execute(strSelectIdAndUrl);
    }

    class SelectBtn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnQueryFish:
                    Intent inFish = new Intent(TitleItems.this, DetailItems.class);
                    //生魚片species_key = 2
                    inFish.putExtra("allDetail", "2");
                    inFish.putExtra("Date", strSpChance);
                    startActivity(inFish);
                    finish();
                    break;
                case R.id.btnQuerySushi:
                    //生魚片握壽司 species_key = 3
                    Intent inSushi = new Intent(TitleItems.this, DetailItems.class);
                    inSushi.putExtra("allDetail", "3");
                    inSushi.putExtra("Date", strSpChance);
                    startActivity(inSushi);
                    finish();
                    break;
                case R.id.btnPrevious:
                    Intent inWelcome = new Intent(TitleItems.this, Welcome.class);
                    startActivity(inWelcome);
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
            ArrayAdapter<String> arrayAdp = new ArrayAdapter<String>(TitleItems.this,
                    android.R.layout.simple_spinner_item,
                    httpJSON.endJSON(s));
            spDate.setAdapter(arrayAdp);
            spDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    strSpChance = parent.getItemAtPosition(position).toString();
                    Toast.makeText(TitleItems.this,strSpChance,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Toast.makeText(TitleItems.this,"Item no selected!!!",1000).show();
                }
            });
        }
    }

}
