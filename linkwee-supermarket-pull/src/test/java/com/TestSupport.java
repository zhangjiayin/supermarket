package com;

import java.io.FileNotFoundException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Log4jConfigurer;

/**
 * TestSupport : Spring测试支持,用于测试由Spring 管理的bean,编写测试类时,继承该类
 *
 * @author Mignet
 * @since 2014年5月18日 下午2:28:58
 */
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestSupport extends AbstractJUnit4SpringContextTests {
    protected long startTime;
    protected long endTime;
    
    static {  
        try {  
            Log4jConfigurer.initLogging("classpath:config/log4j.properties");  
        } catch (FileNotFoundException ex) {  
            System.err.println("Cannot Initialize log4j");  
        }  
    }

    /**
     * 记录 开始运行时间
     *
     * @return
     */
    protected long start() {
        this.startTime = System.currentTimeMillis();
        return startTime;
    }

    /**
     * 记录 结束运行时间
     *
     * @return
     */
    protected long end() {
        this.endTime = System.currentTimeMillis();
        this.log();
        return endTime;
    }

    /**
     * 输出记录
     */
    protected void log() {
        String text = "\nStartTime: " + this.startTime + "\nEndTime : " + this.endTime + "\nExecTime : " + (this.endTime - this.startTime)+"ms";
        logger.info(text);
    }
}