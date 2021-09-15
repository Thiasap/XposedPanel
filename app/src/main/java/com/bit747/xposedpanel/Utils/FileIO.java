package com.bit747.xposedpanel.Utils;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class FileIO {
    public static void saveData(String path, String fileName, String rules) {
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

}
