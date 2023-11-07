/*
 * Created by JFormDesigner on Mon Nov 07 19:19:16 CST 2022
 */

package UI;

import code.ReName;
import code.Zip;
import database.Database;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 26182619
 */
public class ZipWindow extends JFrame {
    public ZipWindow(List<FileItem> LS,DoubleWindow DW,int ind) {
        this.LS = LS;
        this.DW = DW;
        this.ind=ind;
        initComponents();
        this.getRootPane().setDefaultButton(OKButton);
    }

    private void OKButtonMouseClicked(MouseEvent e) throws Exception {
        String oriname = inputText.getText();

        if(oriname.length()<=4 || oriname.substring(oriname.length()-4).compareToIgnoreCase(".zip")!=0)oriname+=".zip";
        File desFile = new File(LS.get(0).getFile().getParent()+"/"+oriname);
        Zip.zip(LS,desFile);
        if (ind==1) DW.JF1.refresh();
        else DW.JF2.refresh();
        Database.updateData(LS,oriname,"压缩");
        System.out.println(oriname);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        inputText = new JTextField();
        OKButton = new JButton();
        label1 = new JLabel();

        //======== this ========
        setTitle("\u538b\u7f29\u6587\u4ef6");
        setIconImage(new ImageIcon(getClass().getResource("/res/\u538b\u7f29.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());
            dialogPane.add(inputText, BorderLayout.CENTER);

            //---- OKButton ----
            OKButton.setText("\u786e\u5b9a");
            OKButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        OKButtonMouseClicked(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            dialogPane.add(OKButton, BorderLayout.EAST);

            //---- label1 ----
            label1.setText("<html> <pre>\n\t\u2022\u9ed8\u8ba4\u538b\u7f29\u683c\u5f0f\u4e3azip\n\t\u2022\u547d\u540d\u53ef\u4e0d\u5199\u62d3\u5c55\u540d\n</pre> </html>");
            dialogPane.add(label1, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(400, 135);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JTextField inputText;
    private JButton OKButton;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private List<FileItem> LS;
    private DoubleWindow DW;
    private int ind;
}
