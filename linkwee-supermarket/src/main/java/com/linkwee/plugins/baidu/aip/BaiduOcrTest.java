package com.linkwee.plugins.baidu.aip;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;

import com.linkwee.plugins.baidu.aip.ocr.AipOcr;

public class BaiduOcrTest {

	public static final String APP_ID = "10559455";
    public static final String API_KEY = "CqLzN07BG5C51CBM0DLFG55R";
    public static final String SECRET_KEY = "PwHSLKIR5DBhxQUWTbcEP7kQhbSasdgC";
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		AipOcr client = new AipOcr(APP_ID,API_KEY,SECRET_KEY);
		// 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
//        String filename = "aaaa";
//        File targetFile = new File("D:/linkweeworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/supermarket/WEB-INF", filename);
//        //判断文件夹是否已经存在，如果已经存在了重新建   
//        if (!targetFile.exists()) {   
//           targetFile.mkdirs();   
//        }

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
		
		idcardRecognition(client);
	}
	
	public static void idcardRecognition(AipOcr client) throws IOException {
	    // 设置识别身份证正面参数
//	    boolean isFront = true;
//	    HashMap<String, String> options = new HashMap<String, String>();
//
//	    // 参数为本地图片路径
//	    String imagePath = "idcard.jpg";
////	    JSONObject response = client.idcard(imagePath, isFront, options);
////	    System.out.println(response.toString());
//
//	    // 参数为本地图片文件二进制数组
//	    byte[] file = readImageFile(imagePath);
////	    JSONObject response = client.idcard(file, isFront, options);
//	    String imgPath = "http://image.toobei.com/8b340abe0dcf9f352f143487be0f6dfa";
////	    JSONObject response3 = client.idcard(imgPath, isFront, options);
//	    
//	    JSONObject response3 = client.general(imgPath, options);
//	    System.out.println(response3.toString());
		
		boolean isFront = true;
	    HashMap<String, String> options = new HashMap<String, String>();
		  // 参数为本地图片路径
//	    String imagePath = "bankcard.jpg";
	    org.springframework.core.io.Resource cures = new ClassPathResource("icons/123.jpg");
	    String culogoPath = cures.getFile().getPath();
//	    String imgPath = "http://image.toobei.com/8b340abe0dcf9f352f143487be0f6dfa";
//	    byte[] imgData = Util.readFileByBytes(imgPath);
//	    JSONObject response = client.general(culogoPath,options);
//	    System.out.println(response.toString());
//	    HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");

//        String idCardSide = "front";
	    JSONObject response = client.idcard(culogoPath, "front", options);
	    Object word = response.get("words_result");
	    Object idword = ((JSONObject) word).get("公民身份号码");
	    Object idno = ((JSONObject) idword).get("words");
	    Object nameword = ((JSONObject) word).get("姓名");
	    Object name = ((JSONObject) nameword).get("words");
	    System.out.println("==============================身份证号码"+idno);
	    System.out.println("==================================姓名"+name);
	    // 参数为本地图片文件二进制数组
//	    byte[] file = readImageFile(imagePath);
//	    JSONObject response = client.bankcard(file);
//	    System.out.println(response.toString());{"words_result":
//	    {"性别":{"words":"男","location":{"height":26,"width":17,"left":125,"top":96}},
//	    "姓名":{"words":"周胜","location":{"height":24,"width":60,"left":121,"top":55}},
//	    "住址":{"words":"武汉市江夏区纸坊街兴新街特1号","location":{"height":46,"width":234,"left":122,"top":187}},
//	    "公民身份号码":{"words":"420115198707270018","location":{"height":23,"width":309,"left":214,"top":293}},
//	    "出生":{"words":"19870727","location":{"height":19,"width":163,"left":123,"top":143}},
//	    "民族":{"words":"汉","location":{"height":31,"width":23,"left":235,"top":90}}},
//	"words_result_num":6,"image_status":"normal","log_id":7412604492042529808}
	    
//	    String url = "http://image.toobei.com/1f418e27b224a9d2463cdc009a0bd733";
//	    JSONObject response3 = client.basicGeneralUrl(imgPath, new HashMap<String, String>());
//	    System.out.println(response3.toString());
	    
	    
	    
	   /* JSONObject res = client.general(culogoPath,new HashMap<String, String>());
	    Object word = res.get("words_result");
    	String xmString = "";  
        String xmlUTF8="";
        xmString = new String(word.toString().getBytes()); 
        xmString.contains("公民身份号码");
//        xmlUTF8 = URLEncoder.encode(xmString, "ANSI"); 
        System.out.println("是否有公民身份证号码：" + xmString.contains("公民身份号码")) ; 
        System.out.println("公民身份证号码字符串位置：" + xmString.substring(398)) ;
        System.out.println("截取到身份证号码：" +xmString.substring(404,422)) ;
        System.out.println("长度：" +word.toString().length()) ;
        
        System.out.println("utf-8 编码：" + xmlUTF8) ;  
    	System.out.println("===============baseReco===================银行卡名:"+word.toString());*/

	}

}
