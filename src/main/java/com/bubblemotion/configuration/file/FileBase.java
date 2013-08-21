package com.bubblemotion.configuration.file;


import com.bubblemotion.configuration.utils.*;
import com.bubblemotion.configuration.data.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.bubblemotion.configuration.utils.Utils;
public abstract class FileBase implements File{
	protected String fileName;
	protected String encoding;
	protected long lastModifiedTime;
	protected int fileType;
	private static Logger logger = Logger.getLogger(FileBase.class.getCanonicalName());
	
	/*public FileBase()
	{
		fileName = null;
		encoding = null;
		lastModifiedTime = 0;
	}*/
	public void setFileType(int fileType)
	{
		//to be implemented by Child Classes
	}
	public FileBase(String fileName)
	{
		
		this.fileName = fileName;
		encoding = null;
		lastModifiedTime = Utils.getLastModifiedTime(fileName);
	}
	public void setFile(String fileName)
	{
		this.fileName = fileName;
	}
	public String getFile()
	{
		return fileName;
	}
	public String getContent()
	{
		String content = null;
		return content; 
	}
	public List<String> getContentbyLine()
	{
		List<String> content = new ArrayList<String>();
		return content;
	}
	public long getLastModifiedTime()
	{
		 return lastModifiedTime;
	}
	public void updateLastModifiedTime()
	{
		lastModifiedTime = Utils.getLastModifiedTime(fileName);
	}
	public boolean isUpdated()
	{
		//logger.info("isUpdated is called. File Name is = " + fileName);
		long updatedTime = 0;
		try{
			updatedTime = Utils.getLastModifiedTime(fileName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Erorr occurs while getting the last updated time of the file. File name is = " + this.fileName);
			return false;
		}
		if(lastModifiedTime != updatedTime)
		{
			lastModifiedTime = updatedTime;
			return true;
		}
		return false;
	}
}