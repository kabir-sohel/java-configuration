package com.bubblemotion.configuration.file;

import java.util.List;
import java.util.Date;
import com.bubblemotion.configuration.data.*;
public class TextFile extends FileBase implements File{
	
	/*public TextFile()
	{
		super();
		this.fileType = Constants.TEXT_FILE;
	}*/
	public TextFile(String fileName)
	{
		super(fileName);
	}
	public void setFileType(int fileType)
	{
		this.fileType = fileType;
	}

}
