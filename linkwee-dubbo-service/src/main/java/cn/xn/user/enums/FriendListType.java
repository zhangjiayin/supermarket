package cn.xn.user.enums;

public enum FriendListType {

	ALL(0, "获取全部好友"), REGISTER(1, "获取注册好友"), UNREGISTER(2, "获取非注册好友");
	
	private Integer type;
	private String desc;

	private FriendListType(Integer type,String desc) {
		this.type = type;
		this.desc = desc;
	}

	public Integer getType() {
		return type;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public static FriendListType get(Integer type){
		FriendListType[] all = FriendListType.values();
		for(FriendListType tmp : all){
			if(tmp.getType().equals(type)){
				return tmp;
			}
		}
		return null;
	}
}
