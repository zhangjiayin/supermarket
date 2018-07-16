package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.xn.user.domain.LoginRlt;
import cn.xn.user.domain.StatisticRlt;

import com.alibaba.fastjson.JSONObject;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<LoginRlt> testList =  new ArrayList<LoginRlt>();
		LoginRlt loginRlt = new LoginRlt();
		Map<String, String> testMap = new HashMap<String, String>();
		for (int i=0;i<10;i++){
			loginRlt.setMemberNo(i+"no");
			loginRlt.setTokenId("test");
			testList.add(loginRlt);
			testMap.put(i+"no", "test");
		}
		String aa = JSONObject.toJSONString(loginRlt);
		System.out.println(aa);
		System.out.println(JSONObject.parseObject(aa, LoginRlt.class));
		String bb = JSONObject.toJSONString(testList);
		System.out.println(bb);
		System.out.println(JSONObject.parseArray(bb, LoginRlt.class));
		String cc = JSONObject.toJSONString(testMap);
		System.out.println(cc);
		System.out.println(JSONObject.parseObject(cc, Map.class));
		System.out.println(JSONObject.parseObject(cc, new HashMap<String, String>().getClass()));
		
		StatisticRlt dataObj =  new StatisticRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(null),StatisticRlt.class);
		System.out.println(dataObj);
	}

}
