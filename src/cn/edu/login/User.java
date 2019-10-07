package cn.edu.login;

public class User {

	private int id;
	private String account;
	private String pw;
	
	public User() {
		super();
	}
	public User(String account,String pw) {
		super();
		this.account = account;
		this.pw = pw;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
}
