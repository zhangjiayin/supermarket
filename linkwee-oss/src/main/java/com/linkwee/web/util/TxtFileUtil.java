package com.linkwee.web.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class TxtFileUtil {

	 /**   
     * 追加文件：使用FileWriter   
     *    
     * @param fileName   
     * @param content   
     */    
    public static void appendContent(String fileName, String content) {   
        FileWriter fw = null;  
        PrintWriter pw= null;
        try {     
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件     
        	fw = new FileWriter(fileName, true);     
            pw=new PrintWriter(fw);   
            pw.println(content);  
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally {     
            try { 
            	if(pw != null){  
                	pw.close();     
                }
                if(fw != null){  
                	fw.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }   
    } 
    
    public static String getFilePath() {
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
        //path=path.replace('/', '\\'); // 将/换成\  
        path=path.replace("file:", ""); //去掉file:  
       // path=path.replace("classes\\", ""); //去掉class\  
       // path=path.replace("target\\", ""); //去掉class\  
        //path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb... 
       // System.out.println("===========os.name:"+System.getProperties().getProperty("os.name")); 
        path= path+"investorAccInfo_"+DateUtils.format(new Date(),DateUtils.FORMAT_SHORT)+".txt";
		return path;
	}
	public static void main(String[] args) {
		String path = getFilePath();
		appendContent(path,"测试给文本追加内容");
	}

	
}
