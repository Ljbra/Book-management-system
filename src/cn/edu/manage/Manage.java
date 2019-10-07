package cn.edu.manage;

public class Manage {

	private Integer id;
	private String account;
	private String pw;
	
	public Manage() {
		super();
	}

	public Manage(String account, String pw) {
		super();
		this.account = account;
		this.pw = pw;
	}

	public Manage(Integer id, String account, String pw) {
		super();
		this.id = id;
		this.account = account;
		this.pw = pw;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@Override
	public String toString() {
		return "Person [id=" + id + ", account=" + account + ", password=" + pw + "]";
	}
	
}
