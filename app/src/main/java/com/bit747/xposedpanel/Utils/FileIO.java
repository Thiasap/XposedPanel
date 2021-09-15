package com.bit747.xposedpanel.Utils;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    public static int saveData(String path, String fileName, String rules) {
        byte[] buffer = rules.getBytes(StandardCharsets.UTF_8);
        FileOutputStream fos = null;
        try {
            /* 判断sd的外部设置状态是否可以读写 */
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(path, fileName);
                // 先清空内容再写入
                fos = new FileOutputStream(file);
                fos.write(buffer);
                fos.close();
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    public static String readData(String path, String fileName){
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File file = new File(path, fileName);
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
                byte[] b = new byte[1024];
                int n;
                while ((n = fis.read(b)) != -1) {
                    bos.write(b, 0, n);
                }
                fis.close();
                bos.close();
                return bos.toString();
            }else return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<String> readLine(String path, String fileName){
        List<String> rules = new ArrayList<>();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            InputStream instream = null;
            try {

                instream = new FileInputStream(path+fileName);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while (( line = buffreader.readLine()) != null) {
                        rules.add(line);
                    }
                    instream.close();
                    return rules;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return null;
        }
        return null;
    }

}
