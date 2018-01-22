package com.example.administrator.testglide;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;




public class MyOkhttp {

    public static OkHttpClient client = new OkHttpClient();

    public static String get(String url){
        try {
//         client.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);
            client.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
