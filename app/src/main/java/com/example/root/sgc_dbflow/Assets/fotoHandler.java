package com.example.root.sgc_dbflow.Assets;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.root.sgc_dbflow.conector.ConectorEventListener;
import com.example.root.sgc_dbflow.conector.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;



public class fotoHandler extends AsyncTask<Void, Void, Void> {

    private Uri uri;
    private ContentResolver resolver;
    private int orientacion;
    private File newFile;
    private ConectorEventListener conectorEventListener;
    private Exception exception;

    public fotoHandler(Uri uri, ContentResolver resolver, int orientacion, File newFile, ConectorEventListener conector){
        this.uri = uri;
        this.resolver = resolver;
        this.orientacion = orientacion;
        this.newFile = newFile;
        this.conectorEventListener = conector;

    }



    @Override
    protected Void doInBackground(Void... voids) {

        WeakReference<Bitmap> image = null;

        try {
            image = new WeakReference<>(MediaStore.Images.Media.getBitmap(resolver, uri));
        } catch (IOException e) {
            e.printStackTrace();
            exception = e;
        }
        if (image != null)
            TimeStamp(rotateBitmap(orientacion, image), newFile);

        return null;
    }


    private static Bitmap rotateBitmap (int orientacion, WeakReference<Bitmap> src){

        Matrix matrix  = new Matrix();
        matrix.postRotate(orientacion);

        src =  new WeakReference<>(Bitmap.createBitmap(src.get(), 0,0,src.get().getWidth() , src.get().getHeight(), matrix, true));

        return src.get();

    }

    private static void TimeStamp (Bitmap src, File file){

        src = convertToMutable(src);

        String timeStampFoto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        Canvas cs = new Canvas(src);
        Paint tPaint = new Paint();


        tPaint.setTextSize(105);
        tPaint.setColor(Color.rgb(235,63,23));
        tPaint.setStyle(Paint.Style.FILL);
        cs.drawBitmap(src, 0,0, null);
        float height = tPaint.measureText("yY");
        cs.drawText(timeStampFoto, 20f, height+15f, tPaint);

        try {
            src.compress(Bitmap.CompressFormat.JPEG, 100,
                    new FileOutputStream(
                            file, false));
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        //src.recycle();
        src = null;

    }

    private static Bitmap convertToMutable(Bitmap imgIn) {
        try {
            //this is the newFile going to use temporally to save the bytes.
            // This newFile will not be a image, it will store the raw image data.
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "temp.tmp");

            //Open an RandomAccessFile
            //Make sure you have added uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
            //into AndroidManifest.xml newFile
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

            // get the width and height of the source bitmap.
            int width = imgIn.getWidth();
            int height = imgIn.getHeight();
            Bitmap.Config type = imgIn.getConfig();

            //Copy the byte to the newFile
            //Assume source bitmap loaded using options.inPreferredConfig = Config.ARGB_8888;
            FileChannel channel = randomAccessFile.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, imgIn.getRowBytes()*height);
            imgIn.copyPixelsToBuffer(map);
            //recycle the source bitmap, this will be no longer used.
            imgIn.recycle();
            System.gc();// try to force the bytes from the imgIn to be released

            //Create a new bitmap to load the bitmap again. Probably the memory will be available.
            imgIn = Bitmap.createBitmap(width, height, type);
            map.position(0);
            //load it back from temporary
            imgIn.copyPixelsFromBuffer(map);
            //close the temporary newFile and channel , then delete that also
            channel.close();
            randomAccessFile.close();

            // delete the temp newFile
            file.delete();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imgIn;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        if (conectorEventListener != null){
            if (exception == null)
                conectorEventListener.OnSuccess(new Response("exito"));
            else
                conectorEventListener.OnFailure(new Response("fallo"));

        }
    }


}
