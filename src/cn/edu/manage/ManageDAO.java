package cn.edu.manage;

import java.util.List;

public interface ManageDAO {

	//模糊查询方法
    public List<Manage> getForListWithCriteriaManage(CriteriaManage cc);
    
    //查询的方法，将数据库的所有数据放在list集合中，并实现输出的查询
    public List<Manage> getAll();
    
    //
    public void save(Manage manage);
    
    //通过jsp超链接里面？后面的id,获取该id下的各属性的值
    public Manage get(Integer id);
    
    //通过jsp超链接里面？后面的id,并且实现删除的功能
    public void delete(Integer id);
    
    //返回和该name相等的个数
    public long getCountWithName(String name);

    void update(Manage manage);
	
}
