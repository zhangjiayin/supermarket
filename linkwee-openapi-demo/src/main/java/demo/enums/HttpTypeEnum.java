package demo.enums;

public enum HttpTypeEnum {
	
	HTTP("HTTP"),
	HTTPS("HTTPS");

	private HttpTypeEnum(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
