/*
 * Created by JFormDesigner on Tue Nov 08 15:58:16 CST 2022
 */

package UI;

import code.Getallfiles;
import code.SearchFile;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 26182619
 */
public class SearchWindow extends JFrame {
    public SearchWindow() {
        initComponents();
    }
    public SearchWindow(File pathFile,DoubleWindow DW,int ind) {
        this.pathFile=pathFile;
        this.DW=DW;
        this.ind=ind;
        initComponents();
    }

    private void OKbuttonMouseClicked(MouseEvent e) {
        dm.getDataVector().clear();
        if (LcheckBox.isSelected() && RcheckBox.isSelected()){
            List<FileItem> File = Getallfiles.getAllFile(pathFile);
            Vector<Vector> resFiles = SearchFile.zzSearch(File,inputField.getText());
            for (Vector v: resFiles){
                dm.addRow(v);
            }
        }else  if (LcheckBox.isSelected()){
            List<FileItem> File = Getallfiles.getAllFile(pathFile);
            Vector<Vector> resFiles = SearchFile.simpleSearch(File,inputField.getText());
            for (Vector v: resFiles){
                dm.addRow(v);
            }
        }else if (RcheckBox.isSelected()){
            List<FileItem> File = Getallfiles.getFile(pathFile);
            Vector<Vector> resFiles = SearchFile.zzSearch(File,inputField.getText());
            for (Vector v: resFiles){
                dm.addRow(v);
            }
        }else{
            List<FileItem> File = Getallfiles.getFile(pathFile);
            Vector<Vector> resFiles = SearchFile.simpleSearch(File,inputField.getText());
            for (Vector v: resFiles){
                dm.addRow(v);
            }
        }
    }

    private void OpenbuttonMouseClicked(MouseEvent e) {
        String fName = (String) dm.getValueAt(table1.getSelectedRow(),0);
        String pPathName = (String) dm.getValueAt(table1.getSelectedRow(),1);
        File file = new File(pPathName);
        FileListBox<FileItem> ListBox;
        if (ind==1){
            DW.JF1.loadDir(file);
            ListBox =  DW.JF1.getListbox();
        }else{
            DW.JF2.loadDir(file);
            ListBox =  DW.JF1.getListbox();
        }

        int index=-1;

        DefaultListModel<FileItem> FileModelList1 = (DefaultListModel<FileItem>) ListBox.getModel();
        for (int i=0;i<FileModelList1.size();i++){
            if (FileModelList1.get(i).getFile().getName().equals(fName)){
                index=i;
                //System.out.println(fName);
                break;
            }
        }
        if (ind==1){
            DW.JF1.getListbox().setSelectedIndex(index);
        }else{
            DW.JF2.getListbox().setSelectedIndex(index);
        }
        System.out.println(file);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        init();
        table1 = new JTable(dm);
        Npanel = new JPanel();
        inputField = new JTextField();
        OKbutton = new JButton();
        LcheckBox = new JCheckBox();
        RcheckBox = new JCheckBox();
        Openbutton = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("\u6587\u4ef6\u67e5\u627e");
        setIconImage(new ImageIcon(getClass().getResource("/res/Search.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, BorderLayout.CENTER);

            //======== Npanel ========
            {
                Npanel.setLayout(null);
                Npanel.add(inputField);
                inputField.setBounds(15, 5, 340, inputField.getPreferredSize().height);

                //---- OKbutton ----
                OKbutton.setText("\u786e\u5b9a");
                OKbutton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        OKbuttonMouseClicked(e);
                    }
                });
                Npanel.add(OKbutton);
                OKbutton.setBounds(new Rectangle(new Point(365, 5), OKbutton.getPreferredSize()));

                //---- LcheckBox ----
                LcheckBox.setText("\u5305\u542b\u5b50\u6587\u4ef6\u5939");
                LcheckBox.setSelected(true);
                Npanel.add(LcheckBox);
                LcheckBox.setBounds(new Rectangle(new Point(55, 35), LcheckBox.getPreferredSize()));

                //---- RcheckBox ----
                RcheckBox.setText("\u4f7f\u7528\u6b63\u5219\u8868\u8fbe\u5f0f");
                Npanel.add(RcheckBox);
                RcheckBox.setBounds(new Rectangle(new Point(200, 35), RcheckBox.getPreferredSize()));

                //---- Openbutton ----
                Openbutton.setText("\u6253\u5f00");
                Openbutton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        OpenbuttonMouseClicked(e);
                    }
                });
                Npanel.add(Openbutton);
                Openbutton.setBounds(new Rectangle(new Point(365, 35), Openbutton.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < Npanel.getComponentCount(); i++) {
                        Rectangle bounds = Npanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = Npanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    Npanel.setMinimumSize(preferredSize);
                    Npanel.setPreferredSize(preferredSize);
                }
            }
            panel1.add(Npanel, BorderLayout.NORTH);
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel Npanel;
    private JTextField inputField;
    private JButton OKbutton;
    private JCheckBox LcheckBox;
    private JCheckBox RcheckBox;
    private JButton Openbutton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private Vector<Vector> con = new Vector<>();
    private Vector<String> title = new Vector<>();
    private DefaultTableModel dm;
    private File pathFile;
    private DoubleWindow DW;
    private int ind;
    private void init(){
        title.add("文件名");
        title.add("路径");
        Vector<String> c=new Vector<>();
        c.add("不明白正则表达式不要点！");
        c.add("！");
        con.add(c);
        dm = new DefaultTableModel(con,title);
    }

    public static void main(String[] args) {
        new SearchWindow().setVisible(true);
    }
}
