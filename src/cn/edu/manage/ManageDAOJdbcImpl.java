package cn.edu.manage;

import java.util.List;

import cn.edu.manage.CriteriaManage;
import cn.edu.manage.ManageDAO;
import cn.edu.manage.Manage;

public class ManageDAOJdbcImpl extends DAO<Manage> implements ManageDAO{

	@Override
    //ģ����ѯ�ķ���
    public List<Manage> getForListWithCriteriaManage(CriteriaManage cc) {
        String sql="select id,account,pw from manage "
                + "where account like ?";
        //�޸���CriteriaManage��get������ʹ�䷵�ص��ַ����к���%%
        return getForList(sql,cc.getAccount());
    }
	
	 @Override
	    //��ȡ�������ݿ��е���������
	    public List<Manage> getAll() {
	        String sql1="select id,account,pw from manage";
	        return getForList(sql1);
	    }
	 
	 @Override
	    //�������ݵ����ݿ�ķ���
	    public void save(Manage manage) {
	        String sql2="insert into manage(account,pw) values(?,?)";
	        update(sql2, manage.getAccount(),manage.getPw());
	    }
	 
	 @Override
	    //��ȡĳ������
	    public Manage get(Integer id) {
	        String sql3="select id,account,pw from manage where id=?";
	        return get(sql3,id);
	    }
	 
	 @Override
	    //ɾ��id=?������
	    public void delete(Integer id) {
	        String sql4="delete from manage where id=?";
	        update(sql4, id);
	    }
	 
	 @Override
	    //�����ֻ�ȡcount(name)������ȡ�û�������
	    public long getCountWithName(String account) {
	        String sql5="select count(account) from manage where account=?";
	        return getForValue(sql5, account);
	    }
	 
	 @Override
	    public void update(Manage manage) {
	        String sql = "UPDATE manage SET account = ?, pw = ?" +
	                "WHERE id = ?";
	        update(sql, manage.getAccount(), manage.getPw(), 
	                manage.getId());
	    }
}
