package com.tc.edu.tc.MyProject.Base;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.tc.edu.tc.MyBase.CDownloadBitmap;

/**
 * Created by Administrator on 15-3-29.
 */
public class CPrjDownloadBitmap extends CDownloadBitmap {
    private ImageView imageView;

    public CPrjDownloadBitmap(ImageView imageView){
        this.imageView = imageView;
    }



    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap);
    }
}
