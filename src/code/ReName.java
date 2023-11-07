package code;

import UI.FileItem;
import database.Database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReName {
    public static void checkReName(List<FileItem> FileList, String Rules) throws Exception {
        if (Rules.equals("[time]"))reNameFiles3(FileList);
        else if(FileList.size()==1)reNameFile(FileList.get(0).file,Rules);
        else if(Rules.charAt(0)=='.')reExname(FileList,Rules);
        else if(Rules.indexOf("[?]")!=-1)reNameFiles1(FileList,Rules);
        else if(Rules.indexOf("[!]")!=-1)reNameFiles2(FileList,Rules);
        return;
    }
    public static void reNameFile(File file,String Rules){
        if (!file.isFile())return;
        String suf = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.'));
        file.renameTo(new File(file.getParent()+"/"+Rules+suf));
    }
    public static void reExname(List<FileItem> FileList, String Rules) throws Exception {
        for (FileItem fileItem:FileList){
            File file = fileItem.getFile();
            String orName = file.getName();
            String name = file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf("."));
            file.renameTo(new File(name+Rules));
            Database.updateData(orName,name+Rules,"重命名");
        }
    }
    public static void reNameFiles1(List<FileItem> FileList, String Rules) throws Exception {
        int cnt=1;
        int ind=Rules.indexOf("[?]");
        if (ind==-1)return;
        String LName=Rules.substring(0,ind);
        String RName=Rules.substring(ind+3);
        for (int i=0;i<FileList.size();i++){
            File file = FileList.get(i).getFile();
            if(file.isFile()){
                String suf = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.'));
                file.renameTo(new File(file.getParent()+"/"+LName+(i+1)+RName+suf));
                System.out.println(file.getParent()+"/"+LName+(i+1)+RName+suf);
                Database.updateData(file.getName(),LName+(i+1)+RName+suf,"重命名");
            }
        }
        System.out.println(LName+" "+RName);

        //System.out.println(FileList.get(0).getFile().getParent()+"/"+LName+1+RName+suf));
    }
    public static void reNameFiles2(List<FileItem> FileList, String Rules) throws Exception {
        int cnt=1;
        int ind=Rules.indexOf("[!]");
        if (ind==-1)return;
        Map<String,Integer> MP = new HashMap<>();
        String LName=Rules.substring(0,ind);
        String RName=Rules.substring(ind+3);
        for (int i=0;i<FileList.size();i++){
            File file = FileList.get(i).getFile();
            if(file.isFile()){
                String suf = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.'));
                if (MP.get(suf)==null)MP.put(suf,1);
                else MP.put(suf,MP.get(suf)+1);
                file.renameTo(new File(file.getParent()+"/"+LName+MP.get(suf)+RName+suf));
                System.out.println(file.getParent()+"/"+LName+MP.get(suf)+RName+suf);
                Database.updateData(file.getName(),LName+MP.get(suf)+RName+suf,"重命名");
            }
        }
        System.out.println(LName+" "+RName);
        //System.out.println(FileList.get(0).getFile().getParent()+"/"+LName+1+RName+suf));
    }

    public static void reNameFiles3(List<FileItem> FileList) throws Exception {
        for (int i=0;i<FileList.size();i++){
            File file = FileList.get(i).getFile();
            if(file.isFile()){
                String suf = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.'));
                String TimeName = FileList.get(i).time.replace(":","");
                String TN = check(file,TimeName);
                file.renameTo(new File(file.getParent()+"/"+TN+suf));
                System.out.println(file.getParent()+"/"+TN+suf);
                Database.updateData(file.getName(),TN+suf,"重命名");
            }
        }

        //System.out.println(FileList.get(0).getFile().getParent()+"/"+LName+1+RName+suf));
    }

    public static String check(File file,String time){
        String suf = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.'));
        String ref = file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf('\\'));
        if(file.exists()){
            time+=0;
            return check(new File(ref+"\\"+time+suf),time);
        }else return time;
    }

    public static void main(String[] args) throws Exception {
        List<FileItem> LS = new ArrayList<>();
        LS.add(new FileItem(new File("E:\\测试\\测试2\\2022-10-29 231408.cpp")));
        LS.add(new FileItem(new File("E:\\测试\\测试2\\2022-10-29 231408.png")));
        LS.add(new FileItem(new File("E:\\测试\\测试2\\2022-10-29 2314080.cpp")));
        //reNameFiles2(LS,"第[!]个");
        //reNameFile(new File("E:\\测试\\测试2\\探索未知.cpp"),"abc");
        checkReName(LS,"第[!]不同类型文件");
        //String str =check(new File("E:\\测试\\测试2\\2022-10-29 231408.cpp"),"2022-10-29 231408");
        //System.out.println(str);
    }
}
