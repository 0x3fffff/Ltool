package code;

import UI.FileItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class FileSel {
    public static int[] nameSel(DefaultListModel<FileItem> FileModeList1,String name){
        List<FileItem> List1 = new ArrayList<>();
        int len1=FileModeList1.size();
        int[] check1 = new int[len1+10];
        int[] NO1 = new int[len1+10];
        name=".*"+name;
        int m=0,n=0;
        Arrays.fill(NO1, 26182619);
        for (int i=1;i<FileModeList1.size();i++){
            if (Pattern.matches(name,FileModeList1.get(i).getFile().getName())){
                NO1[m++]=i;
            }
        }
        return NO1;
    }
}
