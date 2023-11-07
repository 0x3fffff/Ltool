package UI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class FileListBox<T> extends JList<T>
{
	public FileListBox()
	{
		// 删除原有的鼠标监听器
		if(true) {
			MouseListener[] aaa = this.getMouseListeners();
			for(MouseListener a : aaa)
				this.removeMouseListener( a );
			
			MouseMotionListener[] bbb = this.getMouseMotionListeners();
			for(MouseMotionListener b : bbb)
				this.removeMouseMotionListener( b );
		}
		
		// 添加新的鼠标点击处理
		this.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON1)
				{
					listboxClicked( e );
				}
			}
		});

	}
	//单击实现多选
	private void listboxClicked(MouseEvent e)
	{
		//int index = SwingUtil.locationToIndex(this, e.getPoint());
		int index = this.locationToIndex( e.getPoint());
		if(index < 0) return;
		
		if ( this.isSelectedIndex( index ))
			this.removeSelectionInterval(index,index);
		else
			this.addSelectionInterval(index, index);
	}
	
}
