package cn.edu.customer;

import java.util.List;

import cn.edu.customer.CriteriaCustomer;
import cn.edu.customer.CustomerDAO;
import cn.edu.customer.DAO;

//ʵ�ָ������ܵ���
public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO{

	@Override
    //ģ����ѯ�ķ���
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
        String sql="select id,account,pw,name,phone from customer "
                + "where account like ? and phone like ? and name like ?";
        //�޸���CriteriaCustomer��get������ʹ�䷵�ص��ַ����к���%%
        return getForList(sql,cc.getAccount(),cc.getPhone(),cc.getName());
    }
	
	 @Override
	    //��ȡ�������ݿ��е���������
	    public List<Customer> getAll() {
	        String sql1="select id,account,pw,name,phone from customer";
	        return getForList(sql1);
	    }
	 
	 @Override
	    //�������ݵ����ݿ�ķ���
	    public void save(Customer customer) {
	        String sql2="insert into customer(account,pw,name,phone) values(?,?,?,?)";
	        update(sql2, customer.getAccount(),customer.getPw(),customer.getName(),customer.getPhone());
	    }
	 
	 @Override
	    //��ȡĳ������
	    public Customer get(Integer id) {
	        String sql3="select id,account,pw,name,phone from customer where id=?";
	        return get(sql3,id);
	    }
	 
	 @Override
	    //ɾ��id=?������
	    public void delete(Integer id) {
	        String sql4="delete from customer where id=?";
	        update(sql4, id);
	    }
	 
	 @Override
	    //�����ֻ�ȡcount(name)������ȡ�û�������
	    public long getCountWithName(String account) {
	        String sql5="select count(account) from customer where account=?";
	        return getForValue(sql5, account);
	    }
	 
	 @Override
	    public void update(Customer customer) {
	        String sql = "UPDATE customer SET account = ?, pw = ?, name = ? ,phone = ?" +
	                "WHERE id = ?";
	        update(sql, customer.getAccount(), customer.getPw(), 
	                customer.getName(), customer.getPhone(),customer.getId());
	    }
}
