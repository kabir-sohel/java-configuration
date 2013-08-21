package com.bubblemotion.configuration.data;

public class ConfigurationException extends Throwable{
	private String message;
	private String key;
	private String value;
	public ConfigurationException()
	{
		message = key = value = null;
	}
	public ConfigurationException(String msg)
	{
		message = key = value = null;
		message = msg;
	}
	public ConfigurationException(String msg, String key)
	{
		this.message = msg;
		this.key = key;
		this.value = null;
	}
	public ConfigurationException(String msg, String key, String value)
	{
		this.message = msg;
		this.key = key;
		this.value = value;
	}
	public String getMessage()
	{
		return message;
	}
	public String getKey()
	{
		return key;
	}
	public String getValue()
	{
		return value;
	}
	@Override
	public String toString()
	{
		return message;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	public void setMessage(String msg)
	{
		this.message = msg;
	}
}
