package com.example.lockseven2002.uncle_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
/*
*
*
* */
public class Welcome extends AppCompatActivity {
    Button btnQuery;
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnQuery = (Button)findViewById(R.id.btnQueryAll);
        btnCreate = (Button)findViewById(R.id.btnNew);
        btnQuery.setOnClickListener(new ClickEvent());
        btnCreate.setOnClickListener(new ClickEvent());

    }
    class ClickEvent implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnQueryAll:
                    Intent itn = new Intent(getApplicationContext(),TitleItems.class);
                    startActivity(itn);
                    finish();
                    break;
                default:
                    Intent itnCreate = new Intent(getApplicationContext(),CreateItems.class);
                    startActivity(itnCreate);
                    finish();
                    break;
            }
        }
    }

}
