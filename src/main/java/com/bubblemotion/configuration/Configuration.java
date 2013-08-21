package com.bubblemotion.configuration;
import java.util.List;
public interface Configuration {
	public void configure();
	public void add(String key, String value);
	public void remove(String key);
	public String get(String key);
	public boolean exist(String key);
	public int countKeys();
	public List <String> getKeys();
	
}
