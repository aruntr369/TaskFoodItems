package com.arun.taskfooditems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);

        TextView tv=(TextView) findViewById(R.id.tvd);
        ImageView iv =(ImageView) findViewById(R.id.imgVD);
        int img =getIntent().getIntExtra("img",0);
        iv.setImageResource(img);
        String fud =getIntent().getStringExtra("food");
        tv.setText(fud);

    }
}