package com.bubblemotion.configuration.utils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Date;

import org.apache.log4j.Logger;
import com.bubblemotion.configuration.data.Constants;

public class Utils {
	private static final Logger logger = Logger.getLogger(Utils.class);
	public static List<String> readFile(String fileName)
	{
		logger.info("file Name is " + fileName);
		List<String> data = new ArrayList<String>();
		if(false == Utils.fileExists(fileName))
		{
			logger.error(String.format("File can't be found. File Name is = %s", fileName));
			return data;
		}
		
		String line;
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(fileName));
			while((line = reader.readLine()) != null)
			{
				data.add(line);
			}
		}
		catch(IOException e)
		{
			logger.error(String.format("File Reading Error. File name is %s", fileName));			
			e.printStackTrace();
		}
		finally{
			try{
				if(reader != null)
				{
					reader.close();
				}
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
				logger.error(String.format("File Reading Error. File name is %s", fileName));
				return data;
			}
		}
		return data;
	}
	public static Map<String, String> parseString(String line, String delimiter)
	{
		Map<String, String> dataMap = new HashMap<String, String>();
		if(null == delimiter || "" == delimiter)
		{
			delimiter = Constants.KEY_VALUE_SEPERATOR;
		}
		if(null == line || "" == line)
		{
			logger.error("Invalid Data to be parsed. String is empty or null");
			return dataMap;
		}
		int position = line.indexOf(delimiter, 0);
		if(-1 == position)
		{
			logger.error("Invalid Data to be parsed. No Delimiter is found");
			return dataMap;
		}
		String left = (line.substring(0, position)).trim();
		String right = (line.substring(position + 1)).trim();
		//logger.debug(String.format("Left String = %s , Right String = %s , delimiter = %s\n",left, right, delimiter));
		dataMap.put(left, right);
		return dataMap;
	}
	public static Map<String, String> mergeMap(Map<String, String> mergeWithMap, Map<String,String> mergeFromMap)
	{
		Iterator iterator = mergeFromMap.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry<String, String> entry = (Map.Entry) iterator.next();
			mergeWithMap.put(entry.getKey(), entry.getValue());
		}
		return mergeWithMap;
	}
	public static Map<String, String> parseFile(String fileName)
	{
		Map<String, String> dataMap = new HashMap<String, String>();
		if(null == fileName || "" == fileName)
		{
			logger.error("Invalid FileName. File name is Null or empty. returning null");
			return dataMap;
		}
		List<String> lines = readFile(fileName);
		for(int i = 0; i < lines.size(); ++i)
		{
			Map<String, String> singleEntryMap = parseString((String)lines.get(i), Constants.KEY_VALUE_SEPERATOR);
			Iterator iterator = singleEntryMap.entrySet().iterator();
			while(iterator.hasNext())
			{
				Map.Entry<String, String> pairs = (Map.Entry<String, String>) iterator.next();
				dataMap.put(pairs.getKey(), pairs.getValue());
				
			}
		}
		return dataMap;
	}
	public static long getLastModifiedTime(String fileName)
	{
		if(null == fileName)
		{
			logger.error("File Name is Null, returning 0 .....");
			return 0;
		}
		if(false == Utils.fileExists(fileName))
		{
			logger.error("File doesn't exist, returning 0. File Name is = " + fileName);
		}
		File file = new File(fileName);
		long lastModifiedTime = file.lastModified();
		//logger.info("Last modified Time is = " + lastModifiedTime + "  File Name is = " + fileName);
		
		return lastModifiedTime;
	}
	public static String replaceSpace(String name)
	{
		name.replaceAll("\\s+", "");
		return name;
	}
	public static boolean fileExists(String fileName)
	{
		File file = new File(fileName);
		if(file.exists())return true;
		return false;
	}
	public static long getCurrentTimeStamp()
	{
		Date date = new Date();
		return date.getTime();
		
	}
}
