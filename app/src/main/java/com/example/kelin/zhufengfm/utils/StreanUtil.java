package com.example.kelin.zhufengfm.utils;

/**
 * Created by kelin on 2016/4/6.
 */

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * IO流的工具类
 */

public final class StreanUtil {
    private StreanUtil() {
    }

    /**
     * 关闭数据流
     * @param stream  java中，对于所用的io流，都让他实现该接口
     *                这个接口中只有一个方法，就是close（）用于关流的
     */
    public static void close(Closeable stream){
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] readStream(InputStream in) throws IOException {

        byte[] ret = null;
        if (in != null) {

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            readStream(in,bout);
            ret = bout.toByteArray();
            close(bout);
        }


        return ret;

    }

    /**
     *
     * @param in
     * @param out
     * @return
     */
    public static long readStream(InputStream in, OutputStream out){

        long ret = 0;
        if (in != null && out != null) {
            byte[] buf = new byte[128];
            int len;

            try {
                while (true) {
                    len = in.read(buf);
                    if (len == -1) {
                        break;
                    }
                    out.write(buf, 0, len);

                    ret += len;
                }
            }catch (IOException ioe){
                ret = -1;
            }
            // 当请求量大的时候，请求次数非常多的时候，即使是128的数组
            // 频繁创建，也会导致内存溢出
            buf = null;

        }

        return ret;
    }
}
