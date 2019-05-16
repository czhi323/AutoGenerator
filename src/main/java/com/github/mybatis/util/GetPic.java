package com.github.mybatis.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPic {

    // 地址
    private static final String URL = "file:///D:/Users/Desktop/youku/cartoon/part2.mhtml";
    // 获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";


    public static void main(String[] args) {
        try {
//            //判断是否存在
//            if (!fileParent.exists()) {
//                创建父目录文件
//                fileParent.mkdirs();
//            }

            traverseFolder1( "D:/Users/Desktop/youku/cartoon/");
//            GetPic cm=new GetPic();
//            //获得html文本内容
//            String HTML = cm.getHtml(URL);
//            //获取图片标签
//            List<String> imgUrl = cm.getImageUrl(HTML);
//            //获取图片src地址
//            List<String> imgSrc = cm.getImageSrc(imgUrl);
//            //下载图片
//            cm.Download(imgSrc);

        }catch (Exception e){
            System.out.println("发生错误");
        }

    }


    public static void traverseFolder1(String path) {
        int fileNum = 0;
        int folderNum = 0;
        String  url = "file:///" + path;
        String  htmlUrl=null;
        String picUrl;

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
                    System.out.println("文件:" + file2.getAbsolutePath());
                    htmlUrl = "file:///" + file2.getAbsolutePath();
                    picUrl = file2.getAbsolutePath().substring(0,file2.getAbsolutePath().lastIndexOf("."));

                    fileNum++;
                    String strPath = picUrl +"\\aaa.txt";
                    File file1 = new File(strPath);
                    File fileParent = file1.getParentFile();
                    if(!fileParent.exists()){
                        fileParent.mkdirs();
                    }


                    try {
                        GetPic cm=new GetPic();
                        //获得html文本内容
                        String HTML = null;
                        HTML = cm.getHtml(htmlUrl);
                        //获取图片标签
                        List<String> imgUrl = cm.getImageUrl(HTML);
                        //获取图片src地址
                        List<String> imgSrc = cm.getImageSrc(imgUrl);
                        //下载图片
                        cm.Download(imgSrc,picUrl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
//            File temp_file;
//            while (!list.isEmpty()) {
//                temp_file = list.removeFirst();
//                files = temp_file.listFiles();
//                for (File file2 : files) {
//                    if (file2.isDirectory()) {
//                        System.out.println("文件夹:" + file2.getAbsolutePath());
//                        list.add(file2);
//                        folderNum++;
//                    } else {
//                        System.out.println("文件:" + file2.getAbsolutePath());
//                        fileNum++;
//                    }
//                }
//            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);

    }

    //获取HTML内容
    private String getHtml(String url)throws Exception{
        URL url1=new URL(url);
        URLConnection connection=url1.openConnection();
        InputStream in=connection.getInputStream();
        InputStreamReader isr=new InputStreamReader(in);
        BufferedReader br=new BufferedReader(isr);

        String line;
        StringBuffer sb=new StringBuffer();
        while((line=br.readLine())!=null){
            sb.append(line,0,line.length());
            sb.append('\n');
        }
        br.close();
        isr.close();
        in.close();
        return sb.toString();
    }

    //获取ImageUrl地址
    private List<String> getImageUrl(String html){
        Matcher matcher=Pattern.compile(IMGURL_REG).matcher(html);
        List<String>listimgurl=new ArrayList<String>();
        while (matcher.find()){
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }

    //获取ImageSrc地址
    private List<String> getImageSrc(List<String> listimageurl){
        List<String> listImageSrc=new ArrayList<String>();
        for (String image:listimageurl){
            image = image.replaceAll("=\n","");
            Matcher matcher=Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()){
                listImageSrc.add(matcher.group().substring(0, matcher.group().length()-1));
            }
        }
        return listImageSrc;
    }

    public static List<String> getImageSrc(String htmlCode) {
        List<String> imageSrcList = new ArrayList<String>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);

            // src=https://sms.reyo.cn:443/temp/screenshot/zY9Ur-KcyY6-2fVB1-1FSH4.png
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
            imageSrcList.add(src);

        }
        return imageSrcList;
    }

    //下载图片
    private void Download(List<String> listImgSrc,String picUrl) {
        try {
            //开始时间
            Date begindate = new Date();
            int i = 0;
            for (String url : listImgSrc) {

                //开始时间
                Date begindate2 = new Date();
                if (url.lastIndexOf("?") >=0)
                    url = url.substring(0,url.lastIndexOf("?"));
                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
                if (imageName.lastIndexOf("?") >=0)
                    imageName = imageName.substring(0,imageName.lastIndexOf("?"));
                imageName = i + "--" + imageName;
                i++;
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                String  pathName = picUrl + "\\"+imageName;
                FileOutputStream fo = new FileOutputStream(new File(pathName));
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("开始下载:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
                System.out.println(imageName + "下载完成");
                //结束时间
                Date overdate2 = new Date();
                double time = overdate2.getTime() - begindate2.getTime();
                System.out.println("耗时：" + time / 1000 + "s");
            }
            Date overdate = new Date();
            double time = overdate.getTime() - begindate.getTime();
            System.out.println("总耗时：" + time / 1000 + "s");
        } catch (Exception e) {
            System.out.println("下载失败");
        }
    }
}