package cn.xn.user.enums;

public enum SystemType {
	/**
	 * 钱罐子
	 */
	QGZ("QGZ"),

	/**
	 * 众筹
	 */
	ZC("ZC"),
	/**
	 * 理财师
	 */
	CHANNEL("channel"),
	/**
	 * 理财师投资者端
	 */
	CHANNEL2("investor"),
	/**
	 * 牛贷投资
	 */
	ND("10005"),
	
	/**
	 * 牛贷借贷
	 */
	ND2("10006"),
	
	/**
	 * 集团官网
	 */
	JITUAN("21001")
	;

	private String text;

	private SystemType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public static SystemType get(String systemType){
		SystemType[] arr = SystemType.values();
		for (SystemType item : arr) {
			if(item.getText().equals(systemType)){
				return item;
			}
		}
		return null;
	}
}
