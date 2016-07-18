package com.example.lockseven2002.uncle_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lockseven2002 on 2016/6/12.
 * 12345678915146546
 */
public class TitleItems extends AppCompatActivity {
    Button btnQuery;
    Button btnPreviousPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_items);

        btnQuery = (Button)findViewById(R.id.btnQueryDetail);
        btnPreviousPage = (Button)findViewById(R.id.btnPrevious);
        btnPreviousPage.setOnClickListener(new SelectBtn());
        btnQuery.setOnClickListener(new SelectBtn());
    }
    class SelectBtn implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnQueryDetail:
                    Intent inQuery = new Intent(TitleItems.this,DetailItems.class);
                    inQuery.putExtra("FishQuery","2");
                    startActivity(inQuery);
                    finish();
                    break;
                case R.id.btnPrevious:
                    Intent inWelcome = new Intent(TitleItems.this,Welcome.class);
                    startActivity(inWelcome);
                    finish();
                    break;
            }
        }
    }
}
