package com.example.administrator.testglide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button imageBtn;
    private Button staggeredBtn;
    private OkHttpClient client = new OkHttpClient();
    private final String url = "http://www.baidu.com";
    private static final String TAG = "Loggggggggggg";
    private static int imageId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageBtn = (Button) findViewById(R.id.image_btn);
        imageView = (ImageView) findViewById(R.id.image_view);
        staggeredBtn = (Button) findViewById(R.id.staggered_btn);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
//                sendRequestWithOkHttp();
            }
        });
        staggeredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StaggeredActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loadImage() {
//        String url = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1514531282&di=73f260dfdb4a2aec8d05f3de30b1c10d&src=http://img.tupianzj.com/uploads/allimg/160519/9-160519202315.jpg";
        if(imageId >= ImageUtil.imageUrls.length){
            imageId = 0;
        }
        String url = ImageUtil.imageUrls[imageId];
        imageId ++;
        Log.d(TAG, "loadImage: " + imageView.getScaleType());
        Glide.with(this)
                .load(url)
//                .placeholder(R.drawable.loading)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
//                .dontTransform()
//                .error(R.drawable.error)
//                .centerCrop()
                .into(imageView);
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url(url) //指定访问的服务器地址是电脑本机
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();

//                    parseJSONWithGSON(responseData);

                    Log.d(TAG, "run: " + responseData);
//                    List<Topics> list = getJSONWithGSON(responseData);
//                    for (int i = 0; i < list.size(); i++) {
//                        Log.d("ceshi",list.get(i).getPostsCount() + "--" + list.get(i).getId() + "--" + list.get(i).getName());
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
