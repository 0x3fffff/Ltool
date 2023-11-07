/*
 * Created by JFormDesigner on Thu Nov 17 16:23:53 CST 2022
 */

package UI;

import code.FileSel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import javax.swing.*;

/**
 * @author 26182619
 */
public class QXWindow extends JFrame {
    public QXWindow() {
        initComponents();
    }
    public QXWindow(DoubleWindow DW, int ind) {
        this.DW=DW;
        this.ind=ind;
        initComponents();
    }
    private void OKButtonMouseClicked(MouseEvent e) {
        if (ind==1){
            FileListBox<FileItem> ListBox =  DW.JF1.getListbox();
            DefaultListModel<FileItem> FileModelList = (DefaultListModel<FileItem>) ListBox.getModel();
            int[] ck = FileSel.nameSel(FileModelList,textField1.getText());
            ListBox.setSelectedIndices(ck);
            for (int i:ck){
                System.out.println(i);
            }
        }else{
            FileListBox<FileItem> ListBox =  DW.JF2.getListbox();
            DefaultListModel<FileItem> FileModelList = (DefaultListModel<FileItem>) ListBox.getModel();
            int[] ck = FileSel.nameSel(FileModelList,textField1.getText());
            ListBox.setSelectedIndices(ck);
        }
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        OKButton = new JButton();
        label2 = new JLabel();

        //======== this ========
        setTitle("\u6587\u4ef6\u9009\u62e9");
        setIconImage(new ImageIcon(getClass().getResource("/res/\u5168\u90093.png")).getImage());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("<html><pre>\n\u8bf7\u8f93\u5165\u8981\u9009\u62e9\u7684\u6587\u4ef6\u540e\u7f00\u540d:                          \n</pre></html>");
        contentPane.add(label1, BorderLayout.NORTH);
        contentPane.add(textField1, BorderLayout.CENTER);

        //---- OKButton ----
        OKButton.setText("\u9009\u62e9");
        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OKButtonMouseClicked(e);
            }
        });
        contentPane.add(OKButton, BorderLayout.EAST);

        //---- label2 ----
        label2.setText("<html><pre>\n</pre></html>");
        contentPane.add(label2, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JButton OKButton;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private List<FileItem> LS;
    private DoubleWindow DW;
    private int ind;
}
