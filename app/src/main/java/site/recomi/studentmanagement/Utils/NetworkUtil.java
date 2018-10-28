package site.recomi.studentmanagement.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtil {
    /**
     * get byte[] from InputStream
     * */
    public static byte[] read(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] cache = new byte[1024];
        int len = 0;
        while ((len = in.read(cache)) != -1){
            out.write(cache,0,len);
        }
        in.close();
        return out.toByteArray();
    }

    /**
     * 从网络获取图片，转Bitmap
     * */
    public static Bitmap getImage(String url_path) throws IOException {
        URL url = new URL(url_path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000); // Timeout
        connection.setRequestMethod("GET"); // RequestMethod
        InputStream inputStream = connection.getInputStream();
        byte[] data = read(inputStream);
        inputStream.close();
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }
}
