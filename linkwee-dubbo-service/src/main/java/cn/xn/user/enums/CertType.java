package cn.xn.user.enums;

public enum CertType {

	/**
	 * 身份证
	 */
	SFZ("SFZ");

	private String text;

	private CertType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
