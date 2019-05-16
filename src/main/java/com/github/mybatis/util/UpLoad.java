package com.github.mybatis.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class UpLoad {

    public static void main(String[] args) {

        traverseFolder1("D:\\Users\\Desktop\\youku\\cartoon");
    }


    public static void traverseFolder1(String path) {

        Configuration cfg = new Configuration(Zone.zone2());                //zong1() 代表华北地区
        UploadManager uploadManager = new UploadManager(cfg);

        String accessKey = "QmTuKX9p1NLEX9O1KNX-EJIDHXJu62bTGW--agWX";      //AccessKey的值
        String secretKey = "pIKdQuYMcIE-e-jZ78oiOTye-KIvJkJ9rg8cbE-4";      //SecretKey的值
        String bucket = "wlcm1";
        String domain = "http://pps3p536g.bkt.clouddn.com/";
        //存储空间名
        //String localFilePath = "E:\\Wanglai\\图片\\11.jpg";     //上传图片路径

        //String key = "456.png";                                               //在七牛云中图片的命名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);


        int fileNum = 0;
        int folderNum = 0;
        String  htmlUrl=null;
        String picUrl;

        StringBuilder stringBuilder = new StringBuilder();

        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                } else {

                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                stringBuilder.append("\r\n");
                stringBuilder.append(temp_file.getName());
                stringBuilder.append(":");
                for (File file2 : files) {

                    if (file2.isDirectory()) {
                        //System.out.println("文件夹:" + file2.getAbsolutePath());
                        //list.add(file2);
                        //folderNum++;
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        fileNum++;

                        try {
                            Response response = uploadManager.put(file2.getAbsolutePath(), "a" + file2.getName(), upToken);
                            //解析上传成功的结果
                            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                            System.out.println(domain + putRet.key);
                            System.out.println(putRet.hash);

                            stringBuilder.append(domain + putRet.key);
                            stringBuilder.append(",");
                        } catch (QiniuException ex) {
                            Response r = ex.response;
                            System.err.println(r.toString());
                            try {
                                System.err.println(r.bodyString());
                            } catch (QiniuException ex2) {
                                //ignore
                            }
                        }

                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);

        writeFile(path,stringBuilder.toString());
    }


    /**
     * 写入TXT文件
     */
    public static void writeFile(String path,String contents) {
        try {
            File writeName = new File(path +"\\output.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(contents); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
