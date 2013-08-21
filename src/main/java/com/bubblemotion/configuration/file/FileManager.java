package com.bubblemotion.configuration.file;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.bubblemotion.configuration.utils.Utils;
import com.bubblemotion.configuration.data.*;

public class FileManager {
	//private List <File> files;
	Map<String, FileBase> fileMap = new HashMap<String, FileBase>();
	Map<String, String> keyFileMap = new HashMap<String, String>();
	
	private static final Logger logger = Logger.getLogger(FileManager.class.getCanonicalName());
	
	public void addTextFile(String fileName)
	{
		FileBase file = new TextFile(fileName);
		fileMap.put(fileName, file);
		//files.add(file);
	}
	public String getFileName(String key)
	{
		return keyFileMap.get(key);
	}
	public boolean updateNeeded(String key)
	{
		String fileName = keyFileMap.get(key);
		logger.info("File name is checking for update. File is = " + fileName);
		if(null != fileName)
		{
			return isFileUpdated(fileName);
		}
		else
		{
			logger.info("File Not found With the associated Key = " + key);
			return false;
		}
	}
	public void updateKeyFileMap(Map<String, String> map, String fileName)
	{
		Iterator iterator = map.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
			keyFileMap.put(entry.getKey(), fileName);
		}
	}
	public boolean isFileUpdated(String fileName)
	{
		FileBase file = (FileBase)fileMap.get(fileName);
		if(null == file)
		{
			logger.info("FileBase object is not found. File Name is = " + fileName);
		}
		if(Utils.getCurrentTimeStamp() > file.getLastModifiedTime() + Constants.FILE_LOADING_DEFAULT_INTERVAL_MILLISECONDS)
		{
			
			if(file.isUpdated())
			{
				logger.info("File needs to be updated. File Name is = " + fileName);
				return true;
			}
		}
		return false;
	}
	public void removeKey(String key)
	{
		if(keyFileMap.containsKey(key))
		{
			keyFileMap.remove(key);
		}
	}
	public String getFileFromKey(String key)
	{
		String fileName = null;
		fileName = keyFileMap.get(key);
		return fileName;
	}
	public List<String> loadRootConfig(String fileName)
	{
		List<String> fileNames = new ArrayList<String>();
		if(Utils.fileExists(fileName) == false)
		{
			logger.error(String.format("Root File is not Found. file Name is %s. Returning empty",fileName));
			return fileNames;
		}
		List<String> lines = Utils.readFile(fileName);
		boolean dirFlag = false;
		boolean fileFlag = false;
		String lastDirectoryName = "";
		for(String line : lines)
		{
			
			if(true == fileFlag && line.indexOf("/") > -1)
			{
				String file = line.trim();
				if(Utils.fileExists(file) == false)
				{
					logger.error(String.format("File not Found. file Name is %s",file));
					continue;
				}
				fileNames.add(file);
			}
			else if(line.equals("@DIR") || line.equals("@dir"))
			{
				dirFlag = true;
				fileFlag = false;
			}
			else if(line.equals("@FILE") || line.equals("@file"))
			{
				fileFlag = true;
				dirFlag = false;
			}
			else if(true == dirFlag)
			{
				lastDirectoryName = line.trim();
				if(lastDirectoryName.length() > 0 && lastDirectoryName.substring(lastDirectoryName.length() - 1).equals("/") != true)
				{
					lastDirectoryName += Constants.DIRECTORY_SEPERATOR;
				}
			}
			else if(true == fileFlag)
			{
				String fullFileName = lastDirectoryName + line.trim();
				if(Utils.fileExists(fullFileName) == false)
				{
					logger.error(String.format("File not Found. file Name is %s",fullFileName));
					continue;
				}
				fileNames.add(fullFileName);
			}
		
		}
		return fileNames;
	}
	
}
