package site.recomi.studentmanagement.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
}
