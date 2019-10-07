package cn.edu.manage;

public class CriteriaManage {

	private String account;

	public CriteriaManage(String account) {
		super();
		this.account = account;
	}

	public CriteriaManage() {
		super();
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
	
}
