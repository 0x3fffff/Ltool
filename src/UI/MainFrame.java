package UI;



import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import af.swing.LayoutBox;
import af.swing.PictureView;
import af.swing.layout.HLayout;
import UI.util.SwingUtil;


public class MainFrame extends JFrame
{
	// 当前位置
	JTextField locationField = new JTextField();


	FileListBox<FileItem> listbox = new FileListBox<>();
	DefaultListModel<FileItem> model = new DefaultListModel<>();

	// 当前显示的目录
	private File currentDir = new File(".");

	public MainFrame(String title)
	{
		super(title);

		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		this.setContentPane(root);

		// 顶部
		root.add(initTop(), BorderLayout.NORTH);
		// 中央
		root.add( initCenter(), BorderLayout.CENTER);


		loadDir ( currentDir );
		listbox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}

	public String getLocationField() {
		return locationField.getText();
	}

	private JComponent initTop()
	{
		LayoutBox panel = new LayoutBox().layout(new HLayout(4));
		panel.padding(4).preferredHeight(34);


		// JButton openButton = new JButton("打开");
		PictureView openButton = new PictureView();
		panel.add(this.locationField, "1w");
		panel.add(openButton, "20px");

		openButton.setImage("/res/open2.png");
		openButton.setBackgroundColor(Color.WHITE);
		openButton.setBorderColor(new Color(0x666666));

		openButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				onOpenDir();
			}
		});

		return panel;
	}

	public FileListBox<FileItem> getListbox() {
		return listbox;
	}

	// 初始化列表数据
	private JComponent initCenter()
	{
		listbox.setModel(model);

		listbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		listbox.setCellRenderer( new FileListRenderer());


		listbox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// 左键，双击
				if(e.getButton()== MouseEvent.BUTTON1 && e.getClickCount()==2)
				{
					onLeftDbclicked( e );
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(listbox);
		return scrollPane;
	}


	public void loadDir(File dir)
	{
		// 清空原来的所有项
		model.removeAllElements();

		// 显示当前目录的位置
		try
		{
			// 把相对路径转成绝对路径
			String dirPath = dir.getCanonicalPath();
			this.currentDir = new File(dirPath);
			locationField.setText(dirPath);
		} catch (IOException e)
		{
			e.printStackTrace();
		}


		File[] files = dir.listFiles();


		if(files == null) return;


		List<FileItem> list = new ArrayList<>();
		for( File f : files )
		{
			FileItem item = new FileItem( f );
			list.add( item );
		}

		// 排序。先显示目录，后显示文件。
		list.sort( new Comparator<FileItem>() {
			@Override
			public int compare(FileItem a, FileItem b)
			{
				if(a.isDir && ! b.isDir)
					return -1;
				if(! a.isDir && b.isDir)
					return 1;
				else {
					return a.name.compareTo( b.name);
				}
			}
		});

		// 父级目录
		if(true) {
			File parentDir = currentDir.getParentFile();
			if(parentDir != null) // 当前目录可能是顶级目录
			{
				FileItem parentItem = new FileItem(parentDir);
				parentItem.name = "(回到上层目录)";
				parentItem.time = "";
				model.addElement(parentItem);
			}
		}

		for(FileItem item : list)
		{
			model.addElement( item);
		}
	}

	// ‘打开’按钮
	private void onOpenDir()
	{
		JFileChooser chooser = new JFileChooser();

		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory( this.currentDir);

		// 显示对话框
		int ret = chooser.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			File dir = chooser.getSelectedFile();
			loadDir ( dir );
		}
	}
	//刷新页面
	public void refresh(){
		loadDir ( new File(getLocationField()) );
	}
	// 左键，双击
	private void onLeftDbclicked( MouseEvent e)
	{
		System.out.println(" 双击 ");

		int index = SwingUtil.locationToIndex( listbox, e.getPoint());
		listbox.setSelectedIndex( index);

		if(index <0) return; // 没有选中项目

		FileItem item = model.getElementAt( index );
		if( item.isDir)
		{
			loadDir( item.file );
		}
	}
}
