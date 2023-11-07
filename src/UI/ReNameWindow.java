/*
 * Created by JFormDesigner on Tue Nov 01 17:36:16 CST 2022
 */

package UI;

import code.ReName;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 26182619
 */
public class ReNameWindow extends JFrame {
    public ReNameWindow(List<FileItem> LS,DoubleWindow DW,int ind) {
        this.LS=LS;
        this.DW=DW;
        this.ind=ind;
        initComponents();
        this.getRootPane().setDefaultButton(OKButton);
    }

    private void OKButtonMouseClicked(MouseEvent e) throws Exception {
        ReName.checkReName(LS,inputText.getText());
        if (ind==1) DW.JF1.refresh();
        else DW.JF2.refresh();
        System.out.println(inputText.getText());
        this.dispose();
    }



    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        inputText = new JTextField();
        OKButton = new JButton();
        label1 = new JLabel();

        //======== this ========
        setTitle("\u6587\u4ef6\u91cd\u547d\u540d");
        setAlwaysOnTop(true);
        setType(Window.Type.UTILITY);
        setIconImage(new ImageIcon(getClass().getResource("/res/Rename.png")).getImage());
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
            label1.setText("<html> <pre> \n\u5355\u4e2a\u6587\u4ef6\u53ef\u4ee5\u4efb\u610f\u547d\u540d \u591a\u4e2a\u6587\u4ef6\u5fc5\u987b\u9075\u5b88\u4ee5\u4e0b\u89c4\u5219: \n\t1.\u4f7f\u4e0d\u540c\u6587\u4ef6\u5171\u540c\u8ba1\u6570\u4f7f\u7528 [?] \t\n\t2.\u4f7f\u76f8\u540c\u6587\u4ef6\u5171\u540c\u8ba1\u6570\u4f7f\u7528 [!] \n\t3.\u6309\u65f6\u95f4\u547d\u540d\u4f7f\u7528 [time] \u4e14\u4e0d\u542b\u591a\u4f59\u5b57\u7b26 \n\t4.\u6279\u91cf\u4fee\u6539\u62d3\u5c55\u540d\u4f7f\u7528.\u5f00\u5934\u5982(.exe)\n\u8be6\u7ec6\u8bf4\u660e\u8bf7\u67e5\u770b\u5e2e\u52a9\u6587\u6863 \n</pre> </html>");
            dialogPane.add(label1, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(350, 225);
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

    public static void main(String[] args) throws Exception {
        //new ReNameWindow(new ArrayList<>()).setVisible(true);
    }
}
