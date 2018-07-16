package test;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

public class BaseTest {
	protected final static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private static AbstractApplicationContext ctx;
	
    static {  
        try {
            Log4jConfigurer.initLogging("classpath:config/log4j.properties");  
        } catch (FileNotFoundException ex) {  
            System.err.println("Cannot Initialize log4j");  
        }  
    }
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public  <T>T getService(String name,Class<T> clazz){
		return (T)ctx.getBeanFactory().getBean(name, clazz);
	}
	
}
