package UI.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class SwingUtil
{
	//加载图片
	public static BufferedImage loadImage ( File file )
	{
		try {
			return ImageIO.read( file );			
		}catch(Exception e) {
			throw new RuntimeException( e );
		}
	}

	public static BufferedImage loadImage ( String resourcePath )
	{
		try {
			InputStream res = SwingUtil.class.getResourceAsStream( resourcePath);
			return ImageIO.read( res );
		}catch(Exception e) {
			//e.printStackTrace();
			throw new RuntimeException( "资源路径有误!或者不能解析图片!" + resourcePath );
		}
	}
	
	// 显示在原窗口的中央
	public static void centerInOwner(Window win, Window owner)
	{
		// 获取原窗口的位置
		Rectangle ownerRect = owner.getBounds();
		int width = win.getWidth();
		int height = win.getHeight();		
		int x = ownerRect.x + (ownerRect.width - width)/2;
		int y = ownerRect.y + (ownerRect.height - height)/2;
		win.setBounds(x,y, width, height);
	}

	public static void centerInScreen(Window win)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 

		int x = ( screenSize.width - win.getWidth())/2;
		int y = ( screenSize.height - win.getHeight())/2;
		win.setLocation(x,  y);
	}
	
	// JList 列表框点击位置计算
	public static int locationToIndex(JList listbox, Point location)
	{
		int index = listbox.locationToIndex( location);
		if(index < 0) return -1;
		
		// 精确选择
		Rectangle bounds = listbox.getCellBounds(index, index);
		if(bounds.contains( location))
			return index;
		
		return -1;
	}
}
