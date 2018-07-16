package com.linkwee.xoss.util;

public class AcAccountUtils {

	public static String matchbankName(String bankName){
		if(bankName==null) return "";
		if(bankName.contains("中国银行")){
			return "中国银行";
		}else if(bankName.contains("建")&&bankName.contains("设")){
			return "中国建设银行";
		}else if(bankName.contains("农")&&bankName.contains("业")){
			return "中国农业银行";
		}else if(bankName.contains("工")&&bankName.contains("商")){
			return "中国工商银行";
		}else if(bankName.contains("平")&&bankName.contains("安")){
			return "平安银行";
		}else if(bankName.contains("招")&&bankName.contains("商")){
			return "招商银行";
		}else if(bankName.contains("邮")&&bankName.contains("储")){
			return "中国邮政储蓄银行";
		}else if(bankName.contains("交")&&bankName.contains("通")){
			return "中国交通银行";
		}else if(bankName.contains("浦")&&bankName.contains("发")){
			return "浦发银行";
		}else if(bankName.contains("广")&&bankName.contains("发")){
			return "广东发展银行";
		}else if(bankName.contains("民")&&bankName.contains("生")){
			return "中国民生银行";
		}else if(bankName.contains("中")&&bankName.contains("信")){
			return "中信银行";
		}else if(bankName.contains("兴")&&bankName.contains("业")){
			return "兴业银行";
		}else if(bankName.contains("光")&&bankName.contains("大")){
			return "中国光大银行";
		}else if(bankName.contains("华")&&bankName.contains("夏")){
			return "华夏银行";
		}
		return bankName;
	}
}
