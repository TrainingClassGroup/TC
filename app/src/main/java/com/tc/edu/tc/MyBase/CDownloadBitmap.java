package com.tc.edu.tc.MyBase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 15-3-29.
 */
public class CDownloadBitmap  extends AsyncTask<String, Integer, Bitmap> {
    public CDownloadBitmap() {
        super();
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        return _download(url[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Bitmap bitmap) {
        super.onCancelled(bitmap);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    private Bitmap _download(URL url) {
        Bitmap bitmap = null;
        try {
            //获得连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private Bitmap _download(String url) {
        try {
            return _download(new URL(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
