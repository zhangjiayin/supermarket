package cn.xn.user.enums;

public enum StatusType {
	YES("Y"), NO("N");

	private String text;

	private StatusType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public static StatusType get(String text){
		StatusType[] arr = StatusType.values();
		for(StatusType tmp : arr){
			if(tmp.getText().equals(text)){
				return tmp;
			}
		}
		return null;
	}
}
