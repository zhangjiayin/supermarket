package com.linkwee.web.util;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by lenli on 2016/6/24.
 *
 * @Author Libin
 * @Date 2016/6/24
 */
public class RandomAccessUtil {

    public static String readLine(RandomAccessFile reader) throws IOException {
        StringBuffer sb  = new StringBuffer();
        int ch;
        boolean seenCR = false;
        while((ch=reader.read()) != -1) {
            switch(ch) {
                case '\n':
                     sb.append("<br/>");
                case '\r':
                    seenCR = true;
                    break;
                default:
                    if (seenCR) {
                        sb.append("&nbsp;");
                        seenCR = false;
                    }
                    sb.append((char)ch); // add character, not its ascii value
            }
        }
        return sb.toString();
    }

}
