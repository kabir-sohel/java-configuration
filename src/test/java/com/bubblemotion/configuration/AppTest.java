package com.bubblemotion.configuration;
import com.bubblemotion.configuration.utils.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Assert;
import junit.framework.*;

import org.apache.log4j.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	public static final Logger logger = Logger.getLogger(AppTest.class);
    public AppTest( String testName )
    {
        super( testName );
        BasicConfigurator.configure();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class.getName() );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    public void testIt() throws Exception
    {
    	
    	String fileName = "/Users/Kabir/Desktop/Projects/MavenProjects/configuration/src/resource/file1.txt";
    	//String ret = Utils.readFile(fileName);
    	logger.info("Testing readFile Method of Utils Class");
    	assertEquals("Hellooo testing", Utils.readFile(fileName));
    }
    
}
