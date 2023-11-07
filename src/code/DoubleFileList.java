package code;

import UI.FileItem;

import java.util.List;

public class DoubleFileList {
    private List<FileItem> FileList1;
    private List<FileItem> FileList2;
    private int[] L1Index;
    private int[] L2Index;
    private int[] L1NO;
    private int[] L2NO;
    public DoubleFileList(){}
    public DoubleFileList(List<FileItem> fileList1, List<FileItem> fileList2) {
        FileList1 = fileList1;
        FileList2 = fileList2;
    }

    public DoubleFileList(List<FileItem> fileList1, List<FileItem> fileList2, int[] l1Index, int[] l2Index) {
        FileList1 = fileList1;
        FileList2 = fileList2;
        L1Index = l1Index;
        L2Index = l2Index;
    }

    public DoubleFileList(List<FileItem> fileList1, List<FileItem> fileList2, int[] l1Index, int[] l2Index, int[] l1NO, int[] l2NO) {
        FileList1 = fileList1;
        FileList2 = fileList2;
        L1Index = l1Index;
        L2Index = l2Index;
        L1NO = l1NO;
        L2NO = l2NO;
    }

    public List<FileItem> getFileList1() {
        return FileList1;
    }

    public List<FileItem> getFileList2() {
        return FileList2;
    }

    public void setFileList1(List<FileItem> fileList1) {
        FileList1 = fileList1;
    }

    public void setFileList2(List<FileItem> fileList2) {
        FileList2 = fileList2;
    }

    public void setL1Index(int[] l1Index) {
        L1Index = l1Index;
    }

    public void setL2Index(int[] l2Index) {
        L2Index = l2Index;
    }

    public void setL1NO(int[] l1NO) {
        L1NO = l1NO;
    }

    public void setL2NO(int[] l2NO) {
        L2NO = l2NO;
    }

    public int[] getL1NO() {
        return L1NO;
    }

    public int[] getL2NO() {
        return L2NO;
    }

    public int[] getL1Index() {
        return L1Index;
    }

    public int[] getL2Index() {
        return L2Index;
    }
}
