package com.bubblemotion.configuration;


import com.bubblemotion.configuration.utils.*;
import org.apache.log4j.*;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import com.bubblemotion.configuration.*;
/**
 * Hello world!
 *
 */
public class App 
{
	public static Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args )
    {
    	BasicConfigurator.configure();
    	logger.info("Application is running\n");
    	
    	
    	ConfigurationBase config = new TextConfiguration();
    	try{
    		config.configure();
    		int total = 0;
    		while(total <= 3)
    		{
    			logger.info("key = key2 , value = " + config.get("key2"));
    			logger.info("key = key1 , value = " + config.get("key1"));
    			logger.info("key = key4 , value = " + config.get("key4"));
    			
    			logger.info("key = file2.key3 , value = " + config.get("file2.key2"));
    			logger.info("key = file3.key1 , value = " + config.get("file3.key1"));

    			Thread.sleep(10000);
    			total ++;
    		}
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getStackTrace());
    	}
    	logger.info("Application is ending");
    }
}
