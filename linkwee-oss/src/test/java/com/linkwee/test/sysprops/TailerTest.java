package com.linkwee.test.sysprops;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;

import java.io.File;
/**
 * Created by lenli on 2016/6/24.
 *
 * @Author Libin
 * @Date 2016/6/24
 */



/**
 * Created by yezi on 2014/6/2.
 */
public class TailerTest {

    public static void main(String []args) throws Exception{
        TailerTest tailerTest = new TailerTest();
        tailerTest.test();
    }

    public void test() throws Exception{
        File file = new File("D:\\programfiles\\apache-tomcat-7.0.42\\bin\\logs\\linkwee-oss.log");
        FileUtils.touch(file);

        Tailer tailer = new Tailer(file,new TailerListenerAdapter(){

            @Override
            public void fileNotFound() {  //文件没有找到
                System.out.println("文件没有找到");
                super.fileNotFound();
            }

            @Override
            public void fileRotated() {  //文件被外部的输入流改变
                System.out.println("文件rotated");
                super.fileRotated();
            }

            @Override
            public void handle(String line) { //增加的文件的内容
                System.out.println("文件line:"+line);
                super.handle(line);
            }

            @Override
            public void handle(Exception ex) {
                ex.printStackTrace();
                super.handle(ex);
            }

        },4000,true);
        new Thread(tailer).start();
    }
}

