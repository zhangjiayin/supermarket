package com.linkwee.xoss.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 描述：汉字转换为汉语全拼工具类
 * @author yalin
 * @date 2017年2月22日 下午2:41:59 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class Pinyin4jUtil {
    /** 
     * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（长沙市长:cssc,zssz,zssc,cssz） 
     *  
     * @param chinese 
     *            汉字 
     * @return 拼音 
     */  
    public static String converterToFirstSpell(String chinese) {  
        StringBuffer pinyinName = new StringBuffer();  
        char[] nameChar = chinese.toCharArray();  
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        for (int i = 0; i < nameChar.length; i++) {  
            if (nameChar[i] > 128) {  
                try {  
                    // 取得当前汉字的所有全拼  
                    String[] strs = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);  
                    if (strs != null) {  
                        for (int j = 0; j < strs.length; j++) {  
                            // 取首字母  
                            pinyinName.append(strs[j].charAt(0));  
                            if (j != strs.length - 1) {  
                                pinyinName.append(",");  
                            }  
                        }  
                    }  
                    // else {  
                    // pinyinName.append(nameChar[i]);  
                    // }  
                } catch (BadHanyuPinyinOutputFormatCombination e) {  
                    e.printStackTrace();  
                }  
            } else {  
                pinyinName.append(nameChar[i]);  
            }  
            pinyinName.append(" ");  
        }  
        // return pinyinName.toString();  
        // System.out.println("converterToFirstSpell: "+pinyinName.toString());
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));  
    }  

    /** 
     * 汉字转换位汉语全拼，英文字符不变，特殊字符丢失 
     * 支持多音字，生成方式如
     * (重当参:zhongdangcen,zhongdangcan,chongdangcen,chongdangshen,zhongdangshen,chongdangcan)
     *  
     * @param chinese 
     *            汉字 
     * @return 拼音 
     */  
    public static String converterToSpell(String chinese) {  
        StringBuffer pinyinName = new StringBuffer();  
        char[] nameChar = chinese.toCharArray(); 
        //HanyuPinyinOutputFormat   可以设置拼音大小写、是否后面加读音数字、特殊读音的显示方式
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  //转大,小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  //是否后面加读音数字 例： "重" 汉语拼音：[zhong4, chong2]  说明重字有两个读音，拼音后面的1,2,3,4 代表的是读音
        for (int i = 0; i < nameChar.length; i++) {
        	//判断是否是汉字、中文符号
            if (nameChar[i] > 128) {  
                try {  
                    // 取得当前汉字的所有全拼  
                    String[] strs = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);  
                    if (strs != null) {  
                        for (int j = 0; j < strs.length; j++) {  
                            pinyinName.append(strs[j]);  
                            if (j != strs.length - 1) {  
                                pinyinName.append(",");  
                            }  
                        }  
                    }  
                } catch (BadHanyuPinyinOutputFormatCombination e) {  
                    e.printStackTrace();  
                }  
            }else if(String.valueOf(nameChar[i]).matches("^[A-Za-z]+$")){ //判断是否是英文字母
            	//pinyinName.append(String.valueOf(nameChar[i]).toUpperCase().charAt(0));
        		pinyinName.append(Character.toUpperCase(nameChar[i])); //转换字符类型为大写
            }else {  //其他符号(英文)
                pinyinName.append(nameChar[i]);  
            }  
            pinyinName.append(" ");  
        }  
        // return pinyinName.toString();  
        // System.out.println("converterToSpell: " +pinyinName.toString());
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));  
    }  

    /** 
     * 去除多音字重复数据 
     *  
     * @param theStr 
     * @return 
     */  
    private static List<Map<String, Integer>> discountTheChinese(String theStr) {  
        // 去除重复拼音后的拼音列表  
        List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();  
        // 用于处理每个字的多音字，去掉重复  
        Map<String, Integer> onlyOne = null;  
        String[] firsts = theStr.split(" ");  
        // 读出每个汉字的拼音  
        for (String str : firsts) {  
            onlyOne = new Hashtable<String, Integer>();  
            String[] china = str.split(",");  
            // 多音字处理  
            for (String s : china) {  
                Integer count = onlyOne.get(s);  
                if (count == null) {  
                    onlyOne.put(s, new Integer(1));  
                } else {  
                    onlyOne.remove(s);  
                    count++;  
                    onlyOne.put(s, count);  
                }  
            }  
            mapList.add(onlyOne);  
        }  
        return mapList;  
    }  

    /** 
     * 解析并组合拼音，对象合并方案
     *  
     * @return 
     */  
    private static String parseTheChineseByObject(List<Map<String, Integer>> list) {
    	
        Map<String, Integer> first = null; // 用于统计每一次,集合组合数据  
        // 遍历每一组集合  
        for (int i = 0; i < list.size(); i++) {  
            // 每一组集合与上一次组合的Map  
            Map<String, Integer> temp = new Hashtable<String, Integer>();  
            // 第一次循环，first为空  
            if (first != null) {  
                // 取出上次组合与此次集合的字符，并保存  
                for (String s : first.keySet()) {  
                    for (String s1 : list.get(i).keySet()) {  
                        String str = s + s1;  
                        temp.put(str, 1);  
                    }  
                }  
                // 清理上一次组合数据  
                if (temp != null && temp.size() > 0) {  
                    first.clear();  
                }  
            } else {  
                for (String s : list.get(i).keySet()) {  
                    String str = s;  
                    temp.put(str, 1);  
                }  
            }  
            // 保存组合数据以便下次循环使用  
            if (temp != null && temp.size() > 0) {  
                first = temp;  
            }  
        }  
        String returnStr = "";  
        if (first != null) {  
            // 遍历取出组合字符串  
            for (String str : first.keySet()) {  
                returnStr += (str + ",");  
            }  
        }  
        if (returnStr.length() > 0) {  
            returnStr = returnStr.substring(0, returnStr.length() - 1);  
        }  
        return returnStr;  
    }


    public static void main(String[] args) {
        String str = "长沙市长";  
        String pinyin = Pinyin4jUtil.converterToSpell(str);  
        System.out.println(str+" pin yin ："+pinyin);
        pinyin = Pinyin4jUtil.converterToFirstSpell(str);
        System.out.println(str+" short pin yin ："+pinyin);
    }

}
