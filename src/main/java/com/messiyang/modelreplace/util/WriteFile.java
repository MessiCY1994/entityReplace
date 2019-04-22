package com.messiyang.modelreplace.util;

import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 输出文件
 *
 * @author messiyang
 */
@RestController
public class WriteFile {



    /**
     * 输出json字符串到文件
     * @param filePathName
     * @param json
     */
    public static void writeJson(String filePathName, String json) {
        try {
            wtriteData(filePathName, json.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出数据到文件
     * @param filePathName
     * @param data
     */
    public static void wtriteData(String filePathName, byte[] data) {
        File dest = new File(filePathName);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        FileOutputStream fileOutputStream = null;
        FileChannel channel = null;
        BufferedOutputStream outputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePathName);
            //进行加锁，以便上传部分的代码能够判断文件是否已经输出完毕。可能有更好的解决方案
            channel = fileOutputStream.getChannel();
            outputStream = new BufferedOutputStream(fileOutputStream);
            outputStream.write(data);
            outputStream.flush();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                channel.close();
                fileOutputStream.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }



}
