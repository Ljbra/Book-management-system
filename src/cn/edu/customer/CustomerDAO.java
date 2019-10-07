package cn.edu.customer;

import java.util.List;

public interface CustomerDAO {

	//ģ����ѯ����
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc);
    
    //��ѯ�ķ����������ݿ���������ݷ���list�����У���ʵ������Ĳ�ѯ
    public List<Customer> getAll();
    
    //
    public void save(Customer customer);
    
    //ͨ��jsp���������棿�����id,��ȡ��id�µĸ����Ե�ֵ
    public Customer get(Integer id);
    
    //ͨ��jsp���������棿�����id,����ʵ��ɾ���Ĺ���
    public void delete(Integer id);
    
    //���غ͸�name��ȵĸ���
    public long getCountWithName(String name);

    void update(Customer customer);
}
