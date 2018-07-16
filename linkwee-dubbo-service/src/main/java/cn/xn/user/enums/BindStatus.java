package cn.xn.user.enums;

public enum BindStatus {

	ENABLE("ENABLE"),
	
	DISABLE("DISABLE");
	
	private String text;

	private BindStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
