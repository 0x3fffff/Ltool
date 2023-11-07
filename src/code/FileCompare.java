package code;

import UI.FileItem;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileCompare {
    //对比两个List<FileItem>名字 返回不相同的文件名
    public static DoubleFileList nameCmp(List<FileItem> FileList1,List<FileItem> FileList2){
        List<FileItem> List1 = new ArrayList<>();
        List<FileItem> List2 = new ArrayList<>();

        int len=FileList2.size();
        int[] check = new int[len+10];
        for (int i=0;i<FileList1.size();i++){
            boolean ck=false;
            //System.out.println(FileList1.get(i).name);
            for (int j=0;i<FileList2.size();j++){
                if (FileList1.get(i).name.equals(FileList2.get(i).name)){
                    ck=true;
                    check[j]++;
                    break;
                }
            }
            if(!ck){
                List1.add(FileList1.get(i));
                //System.out.println(FileList1.get(i).name);
                ck=false;
            }
        }

        for (int i=0;i<len;i++){
            if(check[i]!=0){
                List2.add(FileList2.get(i));
            }
        }
        return new DoubleFileList(List1,List2);
    }

    public static DoubleFileList nameCmp(DefaultListModel<FileItem> FileModeList1, DefaultListModel<FileItem> FileModeList2){
        List<FileItem> List1 = new ArrayList<>();
        List<FileItem> List2 = new ArrayList<>();

        int len1=FileModeList1.size();
        int len2=FileModeList2.size();
        int[] check1 = new int[len1+10];
        int[] check2 = new int[len2+10];
        int[] NO1 = new int[len1+10];
        int[] NO2 = new int[len2+10];
        int m=0,n=0;
        Arrays.fill(NO1, 26182619);
        Arrays.fill(NO2, 26182619);
        for (int i=1;i<FileModeList1.size();i++){
            boolean ck=false;
            //System.out.println(FileList1.get(i).name);
            for (int j=1;j<FileModeList2.size();j++){
                if (FileModeList1.get(i).name.equals(FileModeList2.get(j).name)){
                    ck=true;
                    check1[i]++;
                    check2[j]++;
                    break;
                }
            }
            if(!ck){
                List1.add(FileModeList1.get(i));
                //System.out.println(FileList1.get(i).name);
                NO1[n++]=i;
                ck=false;
            }
        }

        for (int i=1;i<len2;i++){
            if(check2[i]==0){
                NO2[m++]=i;
                List2.add(FileModeList2.get(i));
            }
        }

        return new DoubleFileList(List1,List2,check1,check2,NO1,NO2);
    }
}
