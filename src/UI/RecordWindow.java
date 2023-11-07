/*
 * Created by JFormDesigner on Sat Oct 29 14:18:20 CST 2022
 */

package UI;

import database.DataItem;
import database.Database;

import java.awt.*;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

/**
 * @author 26182619
 */
public class RecordWindow extends JFrame {
    public RecordWindow() throws Exception {
        initComponents();
    }

    private void initComponents() throws Exception {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        Init();
        RcTable = new JTable(VC,title);

        //======== this ========
        setTitle("\u64cd\u4f5c\u8bb0\u5f55");
        setIconImage(new ImageIcon(getClass().getResource("/res/database1.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(RcTable);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable RcTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private String[] columnNames = {"时间", "源数据", "目标数据", "操作"};
    private List<DataItem> LD = Database.readData();
    private Vector<String> title = new Vector<>();
    private Vector<Vector> VC = new Vector<>();
    public void Init() throws Exception {
        //TableModel tb = new DefaultTableModel();
        System.out.println("-----");
        //System.out.println(LD);
        for (String i:columnNames){
            title.addElement(i);
        }
        for (int i=LD.size()-1;i>=0;i--){
            VC.addElement(LD.get(i).getVec());
        }
//        for (DataItem DI : LD){
//            VC.addElement(DI.getVec());
//        }

    }

    public static void main(String[] args) throws Exception {

        RecordWindow JF=new RecordWindow();
        //JF.Init();

        JF.setVisible(true);
        System.out.println("---");
        System.out.println(JF.VC);
        System.out.println(JF.title);
    }
}
