package model;

public class User {
	private String name;
	private String userId;
	private String password;

	public User(String userName, String password, String name) {
		this.userId = userName;
		this.password = password;
		;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "{name:" + this.name + "-userId:" + this.userId + "-password:" + this.password + "}";

	}

}
