package UI;

import af.swing.LayoutBox;
import af.swing.PictureView;
import af.swing.layout.HLayout;
import UI.util.SwingUtil;

import javax.swing.*;
import java.awt.*;

public class FileListRenderer implements ListCellRenderer
{
	LayoutBox root = new LayoutBox();
	PictureView picture = new PictureView();
	JLabel label = new JLabel();
	JLabel timeLabel = new JLabel();
	

	private Image icon_file, icon_folder;
	
	public FileListRenderer()
	{
		System.out.println("** Create FileListRenderer().. ");
		
		// 列表项的布局
		root.layout(new HLayout(4)).padding(4).preferredHeight(28);
		
		root.add(picture, "20px");
		root.add(label, "1w");
		root.add(timeLabel, "150px");
		

		this.icon_file = SwingUtil.loadImage("/res/file.png");
		this.icon_folder = SwingUtil.loadImage("/res/folder.png");
		
		// 左侧要显示的图标。
		picture.setBackgroundColor(Color.WHITE);
		picture.setImage(icon_file);
	}

	@Override
	public Component getListCellRendererComponent(JList list
			, Object value
			, int index
			, boolean isSelected
			, boolean cellHasFocus)
	{

		FileItem item = (FileItem) value;
		label.setText( item.name);
		timeLabel.setText( item.time);
		

		if (item.isDir)
			picture.setImage( this.icon_folder);
		else
			picture.setImage( this.icon_file);
		

		label.setOpaque( true ); 
	    if (isSelected) {
	    	label.setBackground(new Color(0xE5F3FF));
	    	label.setForeground(list.getSelectionForeground());
        } else {
        	label.setBackground(new Color(0xFFFFFF));
        	label.setForeground(list.getForeground());
        }
	    
	    // 返回一个 LayoutBox 对象
		return root;
	}
}
