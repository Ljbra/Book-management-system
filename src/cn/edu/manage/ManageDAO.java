package cn.edu.manage;

import java.util.List;

public interface ManageDAO {

	//ģ����ѯ����
    public List<Manage> getForListWithCriteriaManage(CriteriaManage cc);
    
    //��ѯ�ķ����������ݿ���������ݷ���list�����У���ʵ������Ĳ�ѯ
    public List<Manage> getAll();
    
    //
    public void save(Manage manage);
    
    //ͨ��jsp���������棿�����id,��ȡ��id�µĸ����Ե�ֵ
    public Manage get(Integer id);
    
    //ͨ��jsp���������棿�����id,����ʵ��ɾ���Ĺ���
    public void delete(Integer id);
    
    //���غ͸�name��ȵĸ���
    public long getCountWithName(String name);

    void update(Manage manage);
	
}
