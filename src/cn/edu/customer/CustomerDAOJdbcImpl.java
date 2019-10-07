package cn.edu.customer;

import java.util.List;

import cn.edu.customer.CriteriaCustomer;
import cn.edu.customer.CustomerDAO;
import cn.edu.customer.DAO;

//实现各个功能的类
public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO{

	@Override
    //模糊查询的方法
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
        String sql="select id,account,pw,name,phone from customer "
                + "where account like ? and phone like ? and name like ?";
        //修改了CriteriaCustomer的get方法，使其返回的字符串中含有%%
        return getForList(sql,cc.getAccount(),cc.getPhone(),cc.getName());
    }
	
	 @Override
	    //获取整个数据库中的所有数据
	    public List<Customer> getAll() {
	        String sql1="select id,account,pw,name,phone from customer";
	        return getForList(sql1);
	    }
	 
	 @Override
	    //插入数据到数据库的方法
	    public void save(Customer customer) {
	        String sql2="insert into customer(account,pw,name,phone) values(?,?,?,?)";
	        update(sql2, customer.getAccount(),customer.getPw(),customer.getName(),customer.getPhone());
	    }
	 
	 @Override
	    //获取某个数据
	    public Customer get(Integer id) {
	        String sql3="select id,account,pw,name,phone from customer where id=?";
	        return get(sql3,id);
	    }
	 
	 @Override
	    //删除id=?的数据
	    public void delete(Integer id) {
	        String sql4="delete from customer where id=?";
	        update(sql4, id);
	    }
	 
	 @Override
	    //用名字获取count(name)，即获取用户的数量
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
