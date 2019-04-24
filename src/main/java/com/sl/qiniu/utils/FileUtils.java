/**
 * 
 */
package com.sl.qiniu.utils;

/**
 * @author 李旭日
 *
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * @author gaoxuyang
 *
 */
public class FileUtils {

    /**
     * 
     * @param file 文件
     * @param path   文件存放路径
     * @param fileName 原文件名
     * @return
     */
     public static boolean upload(MultipartFile file, String path){

            File dest = new File(path);

            try {
                //保存文件
                file.transferTo(dest);
                return true;
            } catch (IllegalStateException e) {             
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }
     
     
     public static void inputStreamToFile(InputStream ins, File file) {
 	    try {
 	        OutputStream os = new FileOutputStream(file);
 	        int bytesRead = 0;
 	        byte[] buffer = new byte[8192];
 	        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
 	            os.write(buffer, 0, bytesRead);
 	        }
 	        os.close();
 	        ins.close();
 	    } catch (Exception e) {
 	        e.printStackTrace();
 	    }
 	}
}
