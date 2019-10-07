package cn.edu.customer;

import java.util.List;

public interface CustomerDAO {

	//模糊查询方法
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc);
    
    //查询的方法，将数据库的所有数据放在list集合中，并实现输出的查询
    public List<Customer> getAll();
    
    //
    public void save(Customer customer);
    
    //通过jsp超链接里面？后面的id,获取该id下的各属性的值
    public Customer get(Integer id);
    
    //通过jsp超链接里面？后面的id,并且实现删除的功能
    public void delete(Integer id);
    
    //返回和该name相等的个数
    public long getCountWithName(String name);

    void update(Customer customer);
}
