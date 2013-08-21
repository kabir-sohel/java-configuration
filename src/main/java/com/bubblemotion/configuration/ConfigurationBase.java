package com.bubblemotion.configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;
import com.bubblemotion.configuration.file.*;
import com.bubblemotion.configuration.data.*;
import com.bubblemotion.configuration.utils.*;

public abstract class ConfigurationBase implements Configuration{
	
	//Map<String, ConfigurationData> data = new HashMap<String, ConfigurationData>();
	Map<String, String> data = new HashMap<String, String>();

	private static final Logger logger = Logger.getLogger(Configuration.class.getCanonicalName());
	
	FileManager fileManager = new FileManager();
	public ConfigurationBase()
	{
			
	}
	public void configure() {
		// TODO Auto-generated method stub
		String rootFile = Constants.ROOT_FILE_DIR;
		if(rootFile.substring(rootFile.length() - 1).equals(Constants.DIRECTORY_SEPERATOR) != true)
		{
			rootFile += Constants.DIRECTORY_SEPERATOR;
		}
		rootFile += Constants.ROOT_FILE_NAME;
		logger.info(String.format("Root File's full url is = %s",rootFile));
		List<String> fileLists = fileManager.loadRootConfig(rootFile);
		for(String file : fileLists)
		{
			addFile(file);
		}
		/*
		Iterator iterator = data.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
			logger.info(String.format("New Config key value is added. Key = %s , Value = %s", entry.getKey(), entry.getValue()));
		}*/
		//logger.debug(String.format("Test Log, key = %s , value = %s", "file3.key1", data.get("file3.key1")));
	}

	public void add(String key, String value) {
		// TODO Auto-generated method stub
		data.put(key, value);
	}
	public void addFile(String fileName)
	{
		Map<String, String> tempMap = Utils.parseFile(fileName);
		data = Utils.mergeMap(data, tempMap);
		fileManager.updateKeyFileMap(tempMap, fileName);
		fileManager.addTextFile(fileName);
	}
	public void removeFile(String file)
	{
		
	}
	public void remove(String key) {
		// TODO Auto-generated method stub
		if(data.containsKey(key))
		{
			data.remove(key);
		}
		fileManager.removeKey(key);
	}

	public String get(String key) {
		
		
		if(data.containsKey(key))
		{
		
			if(true == fileManager.updateNeeded(key))
			{
				logger.info("Update is needed for the key %s" + key);
				addFile(fileManager.getFileName(key));
			}
			return replaceVariable(data.get(key));
			//return data.get(key);
		}
		else
		{
			logger.info("Data doesn't contain key");
			return null;
		}
		
	}

	public boolean exist(String key) {
		// TODO Auto-generated method stub
		return data.containsKey(key);
	}

	public int countKeys() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public List<String> getKeys() {
		// TODO Auto-generated method stub
		List<String> keys = new ArrayList<String>();
		Iterator iterator = data.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
			keys.add(entry.getKey());
		}
		return keys;
	}
	public List<String> getValues() {
		// TODO Auto-generated method stub
		List<String> values = new ArrayList<String>();
		Iterator iterator = data.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
			values.add(entry.getValue());
		}
		return values;
	}
	public int getInt(String key, int defaultValue)
	{
		String value = get(key);
		if(value == null)return defaultValue;
		int ret;
		try
		{
			ret = Integer.parseInt(value); 
		}
		catch(Exception e)
		{
			ret = defaultValue;
		}
		return ret;
	}
	public int getInt(String key) throws ConfigurationException
	{
		String value = get(key);
		int ret;
		if(null == value)
		{
			throw new ConfigurationException(Constants.CONFIGURATION_EXCEPTION_VALUE_NOT_FOUND, key);
		}
		try
		{
			ret = Integer.parseInt(value);
		}
		catch(Exception e)
		{
			throw new ConfigurationException(Constants.CONFIGURATION_EXCEPTION_CAN_NOT_CONVERT_TO_INT + e.getMessage(), key, value);
		}
		return ret;	
	}
	
	public long getLong(String key, long defaultValue)
	{
		String value = get(key);
		if(value == null)return defaultValue;
		long ret;
		try
		{
			ret = Integer.parseInt(value); 
		}
		catch(Exception e)
		{
			ret = defaultValue;
		}
		return ret;
	}
	public long getLong(String key) throws ConfigurationException
	{
		String value = get(key);
		long ret;
		if(null == value)
		{
			throw new ConfigurationException(Constants.CONFIGURATION_EXCEPTION_VALUE_NOT_FOUND, key);
		}
		try
		{
			ret = Long.parseLong(value);
		}
		catch(Exception e)
		{
			throw new ConfigurationException(Constants.CONFIGURATION_EXCEPTION_CAN_NOT_CONVERT_TO_LONG + e.getMessage(), key, value);
		}
		return ret;	
	}
	public float getFloat(String key, float defaultValue)
	{
		String value = get(key);
		if(value == null)return defaultValue;
		float ret;
		try
		{
			ret = Float.parseFloat(value);
		}
		catch(Exception e)
		{
			ret = defaultValue;
		}
		return ret;
	}
	public float getFloat(String key) throws ConfigurationException
	{
		String value = get(key);
		float ret;
		if(null == value)
		{
			throw new ConfigurationException(Constants.CONFIGURATION_EXCEPTION_VALUE_NOT_FOUND, key);
		}
		try
		{
			ret = Float.parseFloat(value);
		}
		catch(Exception e)
		{
			throw new ConfigurationException(Constants.CONFIGURATION_EXCEPTION_CAN_NOT_CONVERT_TO_FLOAT + e.getMessage(), key, value);
		}
		return ret;	
	}
	
	public double getDouble(String key, double defaultValue)
	{
		String value = get(key);
		if(value == null)return defaultValue;
		double ret;
		try
		{
			ret = Double.parseDouble(value); 
		}
		catch(Exception e)
		{
			ret = defaultValue;
		}
		return ret;
	}
	public double getDouble(String key) throws ConfigurationException
	{
		String value = get(key);
		double ret;
		if(null == value)
		{
			throw new ConfigurationException(Constants.CONFIGURATION_EXCEPTION_VALUE_NOT_FOUND, key);
		}
		try
		{
			ret = Double.parseDouble(value);
		}
		catch(Exception e)
		{
			throw new ConfigurationException(Constants.CONFIGURATION_EXCEPTION_CAN_NOT_CONVERT_TO_DOUBLE + e.getMessage(), key, value);
		}
		return ret;	
	}
		
	public String replaceVariable(String key)
	{
		return replaceVariable(key, 0, Constants.REPLACE_VARIABLE_DEAPTH_LIMIT);
	}
	public String replaceVariable(String key, int deapth, int limit)
	{
		if(deapth >= limit)return key;
		
		StringBuilder builder = new StringBuilder();
		int fromIndex = 0;
		while(fromIndex < key.length())
		{
			int searchPositionStart = -1;
			int searchPositionEnd = -1;
			searchPositionStart = key.indexOf("${", fromIndex);
			if(searchPositionStart > -1)
			{
				searchPositionEnd = key.indexOf("}", searchPositionStart + 1);
			}
			if(-1 == searchPositionStart || -1 == searchPositionEnd)
			{
				builder.append(key.substring(fromIndex));
				break;
			}
			
			String candidateKey = key.substring(searchPositionStart + 2, searchPositionEnd);
			String candidateValue = data.get(candidateKey);
			if(null != candidateValue)
			{
				builder.append(key.substring(fromIndex, searchPositionStart));
				builder.append(candidateValue);
			}
			else
			{
				logger.info(String.format("Variable %s can't be replaced. No corresponding value is found.", candidateKey));
				builder.append(key.substring(fromIndex, searchPositionEnd + 1));
			}
			
			fromIndex = searchPositionEnd + 1;
		}
		return replaceVariable(builder.toString(), deapth + 1, limit);
	}
	

}
