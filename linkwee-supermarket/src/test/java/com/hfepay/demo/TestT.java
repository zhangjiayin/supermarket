package com.hfepay.demo;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.xoss.util.HttpClientUtil;

public class TestT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String AppID = "wx4ddbb3a6f5a7cec0";
		String AppSecret = "f8cb8e2c1bc1a1427892f4df6f45927d";
		
//		String AppID = "wx1c044759204216d4";
//		String AppSecret = "8be7cf66d5d950828d82bb3cf2ece449";
		
//
		JSONObject jsonParam = new JSONObject();
		String accToken = "Aht2y9S0rwsoRaxrQMAnan1ks_WJxA1C9prM6Bfvv4LbTO4jVCEqEI_yJhMotopOfDydvnU-9EonWbJ64HhPLCLbYqQBqc4qHjb7LuqfJZpciRJmAx-_Mk2UVGmdxGrERTRgAEAWYW";
        String OPENID = "oSIQOw7H1Fywssi8Je865UMi8B6c";
        String templateId = "cc2bW_oAhOo0WChbSgeCWPb8H-8lsNuPULAgx-dNrdI";
        
		jsonParam.put("touser", OPENID);
//        jsonParam.put("template_id", "dTKCYp15N5UEToCunXh8Byxvs322xi7m0Ki-y47n7Ds");
        jsonParam.put("template_id", templateId);
     	
        jsonParam.put("url", "http://weixin.qq.com/download");
        jsonParam.put("topcolor", "#173177");
        String ret = null;
		try {
//			//获取token  token过期    微信返回数据：{"errcode":42001,"errmsg":"access_token expired hint: [RU1VLa0537rsz3!]"}
			ret = HttpClientUtil.httpsGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+AppID+"&secret="+AppSecret);
			ret = HttpClientUtil.httpPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ accToken, jsonParam);
//			System.out.println("微信返回数据："+ret);
		} catch (Exception e) {
			System.out.println("HTTP请求错误："+e);
		}
//		JSONObject json = JSONObject.parseObject(ret);
		
		
        
//        ret = HttpClientUtil.httpsGet("https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="+accToken);
        
//		ret = httpclientutil.httpsget("https://api.weixin.qq.com/cgi-bin/user/info?access_token="
//				+ acctoken+ "&openid="+ openid+"&lang=zh_cn");
		System.out.println("微信返回数据："+ret);
		//微信返回数据：{"subscribe":1,"openid":"oSIQOw7H1Fywssi8Je865UMi8B6c","nickname":"容天","sex":0,"language":"zh_CN",
		//"city":"","province":"","country":"","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/MUbneJliahpW0brsgPG7HC7YYxI6mMPm3GJRJDZbvqO0CzRrwpvvFonzBxJG2V7gP32ejqwXQxbJZ8TFSibS0lsMeaFs2Bx1Y3\/0",
		//"subscribe_time":1477389386,"remark":"","groupid":0,"tagid_list":[]}

	}

	
}

/*错误时的返回JSON数据，形式类似，错误码请见本页下方返回码说明。

返回码	说明
-1	系统繁忙
0	请求成功
40001	验证失败
40002	不合法的凭证类型
40003	不合法的OpenID
40004	不合法的媒体文件类型
40005	不合法的文件类型
40006	不合法的文件大小
40007	不合法的媒体文件id
40008	不合法的消息类型
40009	不合法的图片文件大小
40010	不合法的语音文件大小
40011	不合法的视频文件大小
40012	不合法的缩略图文件大小
40013	不合法的APPID
41001	缺少access_token参数
41002	缺少appid参数
41003	缺少refresh_token参数
41004	缺少secret参数
41005	缺少多媒体文件数据
41006	access_token超时
42001	需要GET请求
43002	需要POST请求
43003	需要HTTPS请求
44001	多媒体文件为空
44002	POST的数据包为空
44003	图文消息内容为空
45001	多媒体文件大小超过限制
45002	消息内容超过限制
45003	标题字段超过限制
45004	描述字段超过限制
45005	链接字段超过限制
45006	图片链接字段超过限制
45007	语音播放时间超过限制
45008	图文消息超过限制
45009	接口调用超过限制
46001	不存在媒体数据
47001	解析JSON/XML内容错误*/
