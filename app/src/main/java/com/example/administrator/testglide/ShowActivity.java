package com.example.administrator.testglide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ShowActivity extends AppCompatActivity {
    private ImageView showImage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        showImage = (ImageView) findViewById(R.id.show_image);
        //提取上一个activity传进来的URL
        //取得从上一个Activity当中传递过来的Intent对象
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        String url = intent.getStringExtra(Utils.URL_STRING);
        Glide.with(ShowActivity.this).load(url).into(showImage);
    }

}
