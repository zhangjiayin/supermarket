package cn.xn.user.enums;

public enum ResultType {

	SUCCESS("SUCCESS"),
	
	FAILURE("FAILURE");
	
	private String text;

	private ResultType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
