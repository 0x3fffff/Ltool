/*
 * Created by JFormDesigner on Mon Oct 24 15:03:01 CST 2022
 */

package UI;

import code.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import database.Database;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import javax.swing.*;

/**
 * @author 26182619
 */
public class DoubleWindow extends JFrame {
    public DoubleWindow() {
        initComponents();
        CmpButton.setVisible(false);
        rbutton.setVisible(false);
        lbutton.setVisible(false);
        LZipButton.setVisible(false);
        RZipButton.setVisible(false);
    }

    private void rbuttonMouseClicked(MouseEvent e) throws Exception {
        FileListBox<FileItem> mylist =  JF1.getListbox();
        int[] indexArray =  mylist.getSelectedIndices();
        List<FileItem> FileList = mylist.getSelectedValuesList();
        if (FileList.size()==0)return;
        if ("(回到上层目录)".equals(FileList.get(0).toString()))return;
        System.out.println(JF2.getLocationField().replace('\\','/'));
        FileCopy.CopyFile(FileList,JF2.getLocationField());
        for(int i:indexArray){
            System.out.println(i);
        }
        for(FileItem i:FileList){
            System.out.println(i);
        }
        JF2.refresh();
        Database.updateData(FileList,JF2.getLocationField(),"复制");
        //init();
    }

    private void lbuttonMouseClicked(MouseEvent e) throws Exception {
        FileListBox<FileItem> mylist =  JF2.getListbox();
        int[] indexArray =  mylist.getSelectedIndices();
        List<FileItem> FileList = mylist.getSelectedValuesList();
        //System.out.println(FileList.get(0).file+"             "+JF2.getLocationField().substring(0, JF2.getLocationField().lastIndexOf("\\")));
        //System.out.println(FileList.get(0));
        if (FileList.size()==0)return;
        if ("(回到上层目录)".equals(FileList.get(0).toString()))return;
        System.out.println(JF1.getLocationField().replace('\\','/'));
        FileCopy.CopyFile(FileList,JF1.getLocationField());
        for(int i:indexArray){
            System.out.println(i);
        }
        for(FileItem i:FileList){
            System.out.println(i);
        }
        JF1.refresh();
        Database.updateData(FileList,JF1.getLocationField(),"复制");
        //init();
    }

    private void CmpButtonMouseClicked(MouseEvent e) {
        //文件名比较
        System.out.println("比较");
        FileListBox<FileItem> LListBox =  JF1.getListbox();
        FileListBox<FileItem> RListBox =  JF2.getListbox();
        DefaultListModel<FileItem> FileModelList1 = (DefaultListModel<FileItem>) LListBox.getModel();
        DefaultListModel<FileItem> FileModelList2 = (DefaultListModel<FileItem>) RListBox.getModel();
        //System.out.println(FileModelList1.getElementAt(1));
        DoubleFileList DFileList = FileCompare.nameCmp(FileModelList1,FileModelList2);
        int[] ck1 = DFileList.getL1Index();
        int[] ck2 = DFileList.getL2Index();
        for (int i : DFileList.getL1NO()){
            System.out.println(i);
        }
        List<FileItem> FList1 = DFileList.getFileList1();
        List<FileItem> FList2 = DFileList.getFileList2();
        //LListBox.setSelectedIndices(new int[]{1,2});
        LListBox.setSelectedIndices(DFileList.getL1NO());
        RListBox.setSelectedIndices(DFileList.getL2NO());
        for (FileItem i : FList1){
            System.out.println(i);
        }
    }

    private void LQXButtonMouseClicked(MouseEvent e) {
        int c = e.getButton();
        if (c==MouseEvent.BUTTON1){
            FileListBox<FileItem> LListBox =  JF1.getListbox();
            int Len = LListBox.getModel().getSize();
            int[] Arr = new int[Len];
            for (int i=1,j=0;i<=Len;i++,j++){
                Arr[j]=i;
            }
            LListBox.setSelectedIndices(Arr);
        }else{
            new QXWindow(this,1).setVisible(true);
        }

    }

    private void RQXButtonMouseClicked(MouseEvent e) {
        int c = e.getButton();
        if (c==MouseEvent.BUTTON1){
            FileListBox<FileItem> RListBox =  JF2.getListbox();
            int Len = RListBox.getModel().getSize();
            int[] Arr = new int[Len];
            for (int i=1,j=0;i<=Len;i++,j++){
                Arr[j]=i;
            }
            RListBox.setSelectedIndices(Arr);
        }else{
            new QXWindow(this,2).setVisible(true);
        }

    }

    private void LQBXButtonMouseClicked(MouseEvent e) {
        FileListBox<FileItem> LListBox =  JF1.getListbox();
        LListBox.removeSelectionInterval(0, LListBox.getModel().getSize());
        JF1.refresh();
    }

    private void RQBXButtonMouseClicked(MouseEvent e) {
        FileListBox<FileItem> RListBox =  JF2.getListbox();
        RListBox.removeSelectionInterval(0, RListBox.getModel().getSize());
        JF2.refresh();
        //RListBox.setSelectedIndex(1);
    }

    private void OrButtonMouseClicked(MouseEvent e) throws Exception {
        RecordWindow RW = new RecordWindow();
        RW.setBounds(100,100,800,500);
        RW.setVisible(true);
    }

    private void LDelButtonMouseClicked(MouseEvent e) throws Exception {
        FileListBox<FileItem> mylist =  JF1.getListbox();
        int[] indexArray =  mylist.getSelectedIndices();
        List<FileItem> FileList = mylist.getSelectedValuesList();
        if (FileList.size()==0)return;
        if ("(回到上层目录)".equals(FileList.get(0).toString()))return;
        DeleteFile.DeleteFolder(FileList);
        System.out.println(FileList);
        JF1.refresh();
        Database.updateData(FileList,"无","删除");
    }

    private void RDelButton2MouseClicked(MouseEvent e) throws Exception {
        FileListBox<FileItem> mylist =  JF2.getListbox();
        int[] indexArray =  mylist.getSelectedIndices();
        List<FileItem> FileList = mylist.getSelectedValuesList();
        if (FileList.size()==0)return;
        if ("(回到上层目录)".equals(FileList.get(0).toString()))return;
        DeleteFile.DeleteFolder(FileList);
        System.out.println(FileList);
        JF2.refresh();
        Database.updateData(FileList,"无","删除");
    }

    private void DelDaButtonMouseClicked(MouseEvent e) throws Exception {
        Database.deleteData();
    }

    private void LRenameButtonMouseClicked(MouseEvent e) {
        FileListBox<FileItem> mylist =  JF1.getListbox();
        List<FileItem> FileList = mylist.getSelectedValuesList();
        if (FileList.size()==0)return;
        if ("(回到上层目录)".equals(FileList.get(0).toString()))return;
        new ReNameWindow(FileList,this,1).setVisible(true);
    }

    private void RRenameButtonMouseClicked(MouseEvent e) throws Exception {
        int c = e.getButton();
        FileListBox<FileItem> mylist =  JF2.getListbox();
        List<FileItem> FileList = mylist.getSelectedValuesList();
        if (FileList.size()==0)return;
        if ("(回到上层目录)".equals(FileList.get(0).toString()))return;
        new ReNameWindow(FileList,this,2).setVisible(true);
        //Database.updateData(FileList,"无","删除");

    }

    private void LZipButtonMouseClicked(MouseEvent e) throws Exception {
        int c = e.getButton();
        if (c == MouseEvent.BUTTON1){
            FileListBox<FileItem> mylist =  JF1.getListbox();
            List<FileItem> FileList = mylist.getSelectedValuesList();
            if (FileList.size()==0)return;
            if ("(回到上层目录)".equals(FileList.get(0).toString()))return;
            new ZipWindow(FileList,this,1).setVisible(true);
        }
        if (c == MouseEvent.BUTTON3){
            FileListBox<FileItem> mylist =  JF1.getListbox();
            List<FileItem> FileList = mylist.getSelectedValuesList();
            Zip.unzips(FileList,FileList.get(0).getFile().getParentFile());
            Database.updateData(FileList,FileList.get(0).getFile().getParent(),"解压");
            JF1.refresh();
        }
    }

    private void RZipButtonMouseClicked(MouseEvent e) throws Exception {
        int c = e.getButton();
        if (c == MouseEvent.BUTTON1){
            FileListBox<FileItem> mylist =  JF2.getListbox();
            List<FileItem> FileList = mylist.getSelectedValuesList();
            if (FileList.size()==0)return;
            if ("(回到上层目录)".equals(FileList.get(0).toString()))return;
            new ZipWindow(FileList,this,1).setVisible(true);
        }
        if (c == MouseEvent.BUTTON3){
            FileListBox<FileItem> mylist =  JF2.getListbox();
            List<FileItem> FileList = mylist.getSelectedValuesList();
            Zip.unzips(FileList,FileList.get(0).getFile().getParentFile());
            Database.updateData(FileList,FileList.get(0).getFile().getParent(),"解压");
            JF2.refresh();
        }
    }

    private void SearButtonMouseClicked(MouseEvent e) {
        //JF2.getLocationField()
        new SearchWindow(new File(JF1.getLocationField()),this,1).setVisible(true);
    }
    private void SearButton2MouseClicked(MouseEvent e) {
        new SearchWindow(new File(JF2.getLocationField()),this,2).setVisible(true);
    }

    private void CPanelMouseClicked(MouseEvent e) {
        boolean p1=true;
        boolean p2=false;
        int c = e.getButton();
        if(c==MouseEvent.BUTTON1){
           p1=true;
           p2=false;
        }else if (c==MouseEvent.BUTTON3){
            p1=false;
            p2=true;
        }
        CmpButton.setVisible(p2);
        rbutton.setVisible(p2);
        lbutton.setVisible(p2);
        LZipButton.setVisible(p2);
        RZipButton.setVisible(p2);
        OrButton.setVisible(p1);
        DelDaButton.setVisible(p1);
        LQXButton.setVisible(p1);
        RQXButton.setVisible(p1);
        RQBXButton.setVisible(p1);
        LQBXButton.setVisible(p1);
        LRenameButton.setVisible(p1);
        RRenameButton.setVisible(p1);
        //LDelButton.setVisible(p1);
        //RDelButton2.setVisible(p1);
        SearButton.setVisible(p1);
        SearButton2.setVisible(p1);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        LPanel = new JPanel();
        RPanel = new JPanel();
        CPanel = new JPanel();
        rbutton = new JButton();
        lbutton = new JButton();
        CmpButton = new JButton();
        LQXButton = new JButton();
        LQBXButton = new JButton();
        RQXButton = new JButton();
        RQBXButton = new JButton();
        OrButton = new JButton();
        LDelButton = new JButton();
        RDelButton2 = new JButton();
        DelDaButton = new JButton();
        LRenameButton = new JButton();
        RRenameButton = new JButton();
        LZipButton = new JButton();
        RZipButton = new JButton();
        SearButton = new JButton();
        SearButton2 = new JButton();

        //======== this ========
        setTitle("\u7b80\u6613\u6587\u4ef6\u6279\u7ba1\u7406\u5de5\u5177");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/res/mainicon.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== LPanel ========
        {
            LPanel.setLayout(null);
            init();
            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < LPanel.getComponentCount(); i++) {
                    Rectangle bounds = LPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = LPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                LPanel.setMinimumSize(preferredSize);
                LPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(LPanel, BorderLayout.WEST);

        //======== RPanel ========
        {
            RPanel.setLayout(null);
            init();
            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < RPanel.getComponentCount(); i++) {
                    Rectangle bounds = RPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = RPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                RPanel.setMinimumSize(preferredSize);
                RPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(RPanel, BorderLayout.EAST);

        //======== CPanel ========
        {
            CPanel.setPreferredSize(new Dimension(200, 220));
            CPanel.setMaximumSize(new Dimension(200, 32767));
            CPanel.setMinimumSize(new Dimension(200, 323));
            CPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CPanelMouseClicked(e);
                }
            });
            CPanel.setLayout(null);

            //---- rbutton ----
            rbutton.setIcon(new ImageIcon(getClass().getResource("/res/\u53f3.png")));
            rbutton.setBackground(new Color(0x00000000, true));
            rbutton.setForeground(new Color(0x00000000, true));
            rbutton.setBorderPainted(false);
            rbutton.setContentAreaFilled(false);
            rbutton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        rbuttonMouseClicked(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            CPanel.add(rbutton);
            rbutton.setBounds(new Rectangle(new Point(10, 300), rbutton.getPreferredSize()));

            //---- lbutton ----
            lbutton.setIcon(new ImageIcon(getClass().getResource("/res/\u5de6.png")));
            lbutton.setContentAreaFilled(false);
            lbutton.setBorderPainted(false);
            lbutton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        lbuttonMouseClicked(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            CPanel.add(lbutton);
            lbutton.setBounds(new Rectangle(new Point(5, 395), lbutton.getPreferredSize()));

            //---- CmpButton ----
            CmpButton.setIcon(new ImageIcon(getClass().getResource("/res/\u53cc\u54112.png")));
            CmpButton.setBorderPainted(false);
            CmpButton.setContentAreaFilled(false);
            CmpButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CmpButtonMouseClicked(e);
                }
            });
            CPanel.add(CmpButton);
            CmpButton.setBounds(5, 205, 135, 85);

            //---- LQXButton ----
            LQXButton.setBorderPainted(false);
            LQXButton.setContentAreaFilled(false);
            LQXButton.setIcon(new ImageIcon(getClass().getResource("/res/\u5168\u90093.png")));
            LQXButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    LQXButtonMouseClicked(e);
                }
            });
            CPanel.add(LQXButton);
            LQXButton.setBounds(0, 180, 50, 50);

            //---- LQBXButton ----
            LQBXButton.setContentAreaFilled(false);
            LQBXButton.setBorderPainted(false);
            LQBXButton.setIcon(new ImageIcon(getClass().getResource("/res/\u5168\u4e0d\u90090.png")));
            LQBXButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    LQBXButtonMouseClicked(e);
                }
            });
            CPanel.add(LQBXButton);
            LQBXButton.setBounds(0, 240, 50, 50);

            //---- RQXButton ----
            RQXButton.setIcon(new ImageIcon(getClass().getResource("/res/\u5168\u90093.png")));
            RQXButton.setContentAreaFilled(false);
            RQXButton.setBorderPainted(false);
            RQXButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    RQXButtonMouseClicked(e);
                }
            });
            CPanel.add(RQXButton);
            RQXButton.setBounds(95, 180, 50, 50);

            //---- RQBXButton ----
            RQBXButton.setIcon(new ImageIcon(getClass().getResource("/res/\u5168\u4e0d\u90090.png")));
            RQBXButton.setContentAreaFilled(false);
            RQBXButton.setBorderPainted(false);
            RQBXButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    RQBXButtonMouseClicked(e);
                }
            });
            CPanel.add(RQBXButton);
            RQBXButton.setBounds(95, 240, 50, 50);

            //---- OrButton ----
            OrButton.setIcon(new ImageIcon(getClass().getResource("/res/\u65f6\u95f4.png")));
            OrButton.setContentAreaFilled(false);
            OrButton.setBorderPainted(false);
            OrButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        OrButtonMouseClicked(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            CPanel.add(OrButton);
            OrButton.setBounds(-5, 100, 60, 65);

            //---- LDelButton ----
            LDelButton.setContentAreaFilled(false);
            LDelButton.setBorderPainted(false);
            LDelButton.setIcon(new ImageIcon(getClass().getResource("/res/del.png")));
            LDelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        LDelButtonMouseClicked(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            CPanel.add(LDelButton);
            LDelButton.setBounds(0, 505, 50, 50);

            //---- RDelButton2 ----
            RDelButton2.setContentAreaFilled(false);
            RDelButton2.setBorderPainted(false);
            RDelButton2.setIcon(new ImageIcon(getClass().getResource("/res/del.png")));
            RDelButton2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        RDelButton2MouseClicked(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            CPanel.add(RDelButton2);
            RDelButton2.setBounds(95, 505, 50, 50);

            //---- DelDaButton ----
            DelDaButton.setIcon(new ImageIcon(getClass().getResource("/res/delbase1.png")));
            DelDaButton.setContentAreaFilled(false);
            DelDaButton.setBorderPainted(false);
            DelDaButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        DelDaButtonMouseClicked(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            CPanel.add(DelDaButton);
            DelDaButton.setBounds(90, 100, 60, 65);

            //---- LRenameButton ----
            LRenameButton.setContentAreaFilled(false);
            LRenameButton.setBorderPainted(false);
            LRenameButton.setIcon(new ImageIcon(getClass().getResource("/res/\u7f16\u8f91.png")));
            LRenameButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    LRenameButtonMouseClicked(e);
                }
            });
            CPanel.add(LRenameButton);
            LRenameButton.setBounds(0, 305, 50, 50);

            //---- RRenameButton ----
            RRenameButton.setContentAreaFilled(false);
            RRenameButton.setBorderPainted(false);
            RRenameButton.setIcon(new ImageIcon(getClass().getResource("/res/\u7f16\u8f91.png")));
            RRenameButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        RRenameButtonMouseClicked(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            CPanel.add(RRenameButton);
            RRenameButton.setBounds(95, 305, 50, 50);

            //---- LZipButton ----
            LZipButton.setContentAreaFilled(false);
            LZipButton.setBorderPainted(false);
            LZipButton.setIcon(new ImageIcon(getClass().getResource("/res/\u538b\u7f29.png")));
            LZipButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        LZipButtonMouseClicked(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            CPanel.add(LZipButton);
            LZipButton.setBounds(0, 140, 50, 50);

            //---- RZipButton ----
            RZipButton.setContentAreaFilled(false);
            RZipButton.setBorderPainted(false);
            RZipButton.setIcon(new ImageIcon(getClass().getResource("/res/\u538b\u7f29.png")));
            RZipButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        RZipButtonMouseClicked(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            CPanel.add(RZipButton);
            RZipButton.setBounds(95, 140, 50, 50);

            //---- SearButton ----
            SearButton.setIcon(new ImageIcon(getClass().getResource("/res/Search.png")));
            SearButton.setContentAreaFilled(false);
            SearButton.setBorderPainted(false);
            SearButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SearButtonMouseClicked(e);
                }
            });
            CPanel.add(SearButton);
            SearButton.setBounds(-5, 365, 60, 65);

            //---- SearButton2 ----
            SearButton2.setIcon(new ImageIcon(getClass().getResource("/res/Search.png")));
            SearButton2.setContentAreaFilled(false);
            SearButton2.setBorderPainted(false);
            SearButton2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SearButton2MouseClicked(e);
                }
            });
            CPanel.add(SearButton2);
            SearButton2.setBounds(90, 365, 60, 65);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < CPanel.getComponentCount(); i++) {
                    Rectangle bounds = CPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = CPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                CPanel.setMinimumSize(preferredSize);
                CPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(CPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel LPanel;
    private JPanel RPanel;
    private JPanel CPanel;
    private JButton rbutton;
    private JButton lbutton;
    private JButton CmpButton;
    private JButton LQXButton;
    private JButton LQBXButton;
    private JButton RQXButton;
    private JButton RQBXButton;
    private JButton OrButton;
    private JButton LDelButton;
    private JButton RDelButton2;
    private JButton DelDaButton;
    private JButton LRenameButton;
    private JButton RRenameButton;
    private JButton LZipButton;
    private JButton RZipButton;
    private JButton SearButton;
    private JButton SearButton2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public MainFrame JF1=new MainFrame("1");
    public MainFrame JF2=new MainFrame("2");
    private JRootPane JP1;
    private JRootPane JP2;
    private void init(){
        JP1 = JF1.getRootPane();
        JP2 = JF2.getRootPane();
        JP1.setBounds(0,0,600,700);
        JP2.setBounds(0,0,600,700);
        LPanel.add(JP1);
        RPanel.add(JP2);
    }
    public static void main(String[] args) {
        //Flat Light
//        try {
//            UIManager.setLookAndFeel( new FlatLightLaf() );
//        } catch( Exception ex ) {
//            System.err.println( "Failed to initialize LaF" );
//        }

        //Flat Dark
//        try {
//            UIManager.setLookAndFeel( new FlatDarkLaf() );
//        } catch( Exception ex ) {
//            System.err.println( "Failed to initialize LaF" );
//        }
//
//        //Flat IntelliJ
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
//
//        //Flat Darcula
//        try {
//            UIManager.setLookAndFeel( new FlatDarculaLaf() );
//        } catch( Exception ex ) {
//            System.err.println( "Failed to initialize LaF" );
//        }
        DoubleWindow dw = new DoubleWindow();
        dw.setVisible(true);
    }

}
