package cn.edu.customer;

public class CriteriaCustomer {

	private String account;
    private String name;
    private String phone;
	
    public CriteriaCustomer() {
        super();
    }

    public CriteriaCustomer(String account, String name, String phone) {
        super();
        this.name = name;
        this.account = account;
        this.phone = phone;
    }

    public String getName() {
        if (name==null|| name == "") {
            name="%%";
        }
        else{
            name="%"+name+"%";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        if (account==null || account == "") {
        	account="%%";
        }
        else{
        	System.out.println(account);
        	account="%"+account+"%";
        }
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        if (phone==null || phone == "") {
            phone="%%";
        }
        else{
            phone="%"+phone+"%";
        }
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
