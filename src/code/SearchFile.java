package code;

import UI.FileItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

public class SearchFile {
    public static Vector<Vector> simpleSearch(List<FileItem> Files, String name){
        Vector<Vector> result=new Vector<>();
        name=".*"+name+".*";
        for (FileItem file : Files){
            if (Pattern.matches(name,file.getFile().getName())){
                Vector<String> fi = new Vector<>();
                fi.add(file.getFile().getName());
                fi.add(file.getFile().getParent());
                result.add(fi);
            }
        }
        return result;
    }
    public static Vector<Vector> zzSearch(List<FileItem> Files, String name){
        Vector<Vector> result=new Vector<>();
        //name=".*"+name+".*";
        for (FileItem file : Files){
            if (Pattern.matches(name,file.getFile().getName())){
                Vector<String> fi = new Vector<>();
                fi.add(file.getFile().getName());
                fi.add(file.getFile().getParent());
                result.add(fi);
            }
        }
        return result;
    }

//    public static void main(String[] args) {
//        List<FileItem> LS = new ArrayList<>();
//        LS.add(new FileItem(new File("E:\\测试\\测试2\\1.png")));
//        LS.add(new FileItem(new File("E:\\测试\\测试2\\2.txt")));
//        LS.add(new FileItem(new File("E:\\测试\\测试2\\2.cpp")));
//        LS.add(new FileItem(new File("E:\\测试\\测试2\\1.txt")));
//        search(LS,"1");
//    }
}
