package com.tc.edu.tc.MyProject.Data;

import android.util.Base64;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tc.edu.tc.MyProject.Base.CPrjDataRequest;
import com.tc.edu.tc.MyProject.Config.CPrjConfig;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 15-3-28.
 */
public class CPrjDataTcImage {

    public static interface OnLoadListener{
        public void onload(byte[] b);
    };

    private OnLoadListener onLoadListener = null;

   // private CTcItemView mCTcItemView;

    public CPrjDataTcImage() {
    }

    public CPrjDataTcImage setOnLoadListener(OnLoadListener onLoadListener){
        this.onLoadListener = onLoadListener;
        return this;
    }
    public void execute(final String id, final boolean isThumbnail){
        CPrjConfig.checkDir(CPrjConfig.CATCH_IMG);
        String fileName = isThumbnail?(id+".thumbnail"):(id+".img");
        final File bitmapFile = new File( CPrjConfig.CATCH_IMG+File.separator+fileName);
        if(bitmapFile.exists()){
            long fileSize = bitmapFile.length();
            if (fileSize > Integer.MAX_VALUE) {
                System.out.println("file too big...");
                return;
            }

            try {
                FileInputStream fi = new FileInputStream(bitmapFile);
                byte[] buffer = new byte[(int) fileSize];
                int offset = 0;
                int numRead = 0;

                while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                    offset += numRead;
                }

                if(onLoadListener!=null) {
                    onLoadListener.onload(Base64.decode(buffer, Base64.DEFAULT));
                }
            }
            catch(Exception e){}

            return;
        }

        CPrjDataRequest dataRequest = new CPrjDataRequest("CData_Image");

        dataRequest.getParams().put("{paras:{id:\""+id+"\",isthumbnail:\""+(isThumbnail?1:0)+"\",type:json}}");

        dataRequest.post(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final byte[] bytes) {
                String jsonData = new String(bytes);
                try {
                    JSONObject jsonObj = new JSONObject(jsonData);
                    JSONObject imgJsonObj = jsonObj.getJSONObject(id);
                    byte[] datas = imgJsonObj.getString("imagedata").getBytes();

                    try {
                        FileOutputStream fos = new FileOutputStream(bitmapFile);
                        fos.write(datas);
                        fos.close();
                    }
                    catch(Exception e){}

                    if(onLoadListener!=null) {
                        onLoadListener.onload(Base64.decode(datas, Base64.DEFAULT));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable error) {
                error.printStackTrace();
            }
        });
    }
}
