package UI;

import java.io.File;
import java.text.SimpleDateFormat;


public class FileItem
{
	public File file ;
	
	public String name = "";
	
	public boolean isDir = false;
	
	public String time = "";

	public long fileSize;
	
	public FileItem(File file)
	{
		this.file = file;
		
		this.isDir = file.isDirectory();
		
		this.name = file.getName();			

		this.fileSize=file.length();
		// if(! this.isDir)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.time = sdf.format( file.lastModified() );
		}
	}

	public File getFile() {
		return file;
	}

	@Override
	public String toString()
	{
		return this.name;
	}
	
}
