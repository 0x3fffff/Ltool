package code;

import UI.FileItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileCopy {
    public static void CopyFile(List<FileItem> FileList,String MovePath) throws IOException {
        //Scanner sc = new Scanner(System.in);
        ArrayList<String> fileArray = new ArrayList<String>(), dirArray = new ArrayList<String>();//fileArray表示要转移的文件位置，dirArray表示要转移的文件夹位置
//        while(true) {
//            String Name;//从控制台输入文件或者文件夹路径
//            Name = sc.nextLine();
//            if(Objects.equals(Name, "0")) break;//当什么都没有输入的时候判断为结束输入
//            File file = new File(Name);
//            if(file.isDirectory()) {
//                dirArray.add(Name);
//            }
//            else if(file.isFile()) {
//                fileArray.add(Name);
//            }
//        }

        {
            for (FileItem fileItem:FileList){
               File file=fileItem.getFile();
                if(file.isDirectory()) {
                    dirArray.add(file.getAbsolutePath().replace('\\','/'));
                }
                else if(file.isFile()) {
                    fileArray.add(file.getAbsolutePath().replace('\\','/'));
                }
            }
        }

        //String MovePath = "";//转移路径
        //MovePath = sc.nextLine();
        MovePath.replace('\\','/');
        if(MovePath == "")
            return;
            //MovePath = sc.nextLine();
        for(String f : fileArray) { //遍历转移文件的列表
            File file = new File(MovePath);
            File file1 = new File(f);
            moveFIle(file1, file);
        }
        for(String f : dirArray) { //遍历转移文件夹的列表
            int idx = 0;
            for(int i = f.length() - 1; i >= 0; i--) {
                char c = f.charAt(i);
                if(c == '/') {
                    idx = i;
                    break;
                }
            }
            //System.out.println(idx);
            File file = new File(MovePath);
            File file1 = new File(f);
            copyMothod(file1, file, idx);
        }
    }
    public static void moveFIle(File oldDir, File newDir){
        String oldFilePath = oldDir.getAbsolutePath();//获得转移文件的路径
        String oldFileName = "";
        //获得转移文件的文件名
        for(int i = oldFilePath.length() - 1; oldFilePath.charAt(i) != '\\'; i--) oldFileName += oldFilePath.charAt(i);
        oldFileName = new StringBuffer(oldFileName).reverse().toString();
        //组合成新的路径
        String name = (newDir.getAbsolutePath().endsWith("\\") ? newDir.getAbsolutePath() : newDir.getAbsolutePath() + "\\")
                + oldFileName;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(oldDir);
            fileOutputStream = new FileOutputStream(name);
            byte[] date = new byte[1024];
            int len = 0;
            while((len = fileInputStream.read(date)) != -1) {
                fileOutputStream.write(date, 0, len);
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void copyMothod(File oldDir, File newDir, int cnt) {

        if(oldDir.isDirectory()) {
            String scrDir = oldDir.getAbsolutePath();
            String desDir = (newDir.getAbsolutePath().endsWith("\\") ? newDir.getAbsolutePath() : newDir.getAbsolutePath() + "\\")
                    + scrDir.substring(cnt + 1);
            //System.out.println(desDir);
            File file = new File(desDir);
            if(!file.exists()) {
                file.mkdirs();
            }
        }
        if(oldDir.isFile()) {
            String scrDir = oldDir.getAbsolutePath();
            String Name = (newDir.getAbsolutePath().endsWith("\\") ? newDir.getAbsolutePath() : newDir.getAbsolutePath() + "\\")
                    + oldDir.toString().substring(cnt + 1);
            //System.out.println(Name);
            FileInputStream fileInputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                fileInputStream = new FileInputStream(oldDir);
                fileOutputStream = new FileOutputStream(Name);
                byte[] b = new byte[1024 * 1024];
                int len = 0;
                while((len = fileInputStream.read(b)) != -1) {
                    fileOutputStream.write(b, 0, len);
                }
                fileOutputStream.flush();
            }  catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                if(fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return;
        }
        File[] files = oldDir.listFiles();
        for(File file : files) {
            copyMothod(file, newDir, cnt);
        }
    }
}