package cn.edu.customer;

public class Customer {

	private Integer id;
    private String account;
    private String pw;
    private String name;
    private String phone;
    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }
    

    
    public Customer(String account, String pw, String name, String phone) {
		super();
		this.account = account;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
	}


    
    public Customer(Integer id, String account, String pw, String name, String phone) {
		super();
		this.id = id;
		this.account = account;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
    public String toString() {
        return "Person [id=" + id + ", account=" + account + ", password=" + pw + ", name" + name + ", phone=" + phone + "]";
    }
}
