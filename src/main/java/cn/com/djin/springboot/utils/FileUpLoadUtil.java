package cn.com.djin.springboot.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * author:快乐风男
 * time:12:26
 */
public class FileUpLoadUtil {

    public static Map<String, Object> upload(MultipartFile myFile, String path) {
        Map<String, Object> map = new HashMap<>();
        try {
            InputStream is = myFile.getInputStream();
            //1.得到目标文件名uuid+'.'+'jpg'
            String originalFilename = myFile.getOriginalFilename();
            String newFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalFilename);//uuid.jpg   后缀：.jpg
            //2.得到目标文件的文件路径
            //若目标文件夹不存在，则创建
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            //创建空的目标文件
            File newFile = new File(path,newFileName);
            //得到目标文件的输出流对象（空的）
            OutputStream os = new FileOutputStream(newFile);
            //完成复制
            IOUtils.copy(is,os);
            //关闭资源
            os.close();
            is.close();
            //上传成功将新文件名字传入到页面中
            map.put("newFileName",newFileName);
            map.put("code",0);  //成功
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",200);  //失败
        }
        return map;
    }

}
