package com.example.administrator.testglide;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

public class ShowActivity extends AppCompatActivity {

    private static final String TAG = "ShowActivity";
    private ImageView showImage = null;
    private PopupWindow mPopWindow;

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
        showImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(ShowActivity.this,"LongClick",Toast.LENGTH_SHORT).show();
                showPopupWindow();
                return true;
            }
        });
    }
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(ShowActivity.this).inflate(R.layout.popuplayout, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = (TextView)contentView.findViewById(R.id.save_img);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(ShowActivity.this).inflate(R.layout.activity_show, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }
}
