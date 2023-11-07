package code;

import UI.FileItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Getallfiles {
    static List<FileItem> fileList2;
    static List<FileItem> fileList;
    public static List<FileItem> getAllFile(File file){
        //fileList.clear();
        fileList=new ArrayList<>();
        getFiles(file);
        return fileList;
    }
    public static List<FileItem> getFile(File file){
        //fileList2.clear();
        fileList2=new ArrayList<>();
        File[] fileArr=file.listFiles();
        for (File f:fileArr){
            if (f.isFile()){
                fileList2.add(new FileItem(f));
            }
        }
        return fileList2;
    }
    public static void getFiles(File f) {
        File[] fileArr=f.listFiles();
        if(fileArr!=null){
            for (File files:fileArr){
                if(files.isDirectory()){
                    getFiles(files);
                }else{
                    fileList.add(new FileItem(files));
                    //System.out.println(files.getName() );
                }
            }
        }
    }
}
