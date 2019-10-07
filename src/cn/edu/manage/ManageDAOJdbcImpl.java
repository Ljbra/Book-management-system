package cn.edu.manage;

import java.util.List;

import cn.edu.manage.CriteriaManage;
import cn.edu.manage.ManageDAO;
import cn.edu.manage.Manage;

public class ManageDAOJdbcImpl extends DAO<Manage> implements ManageDAO{

	@Override
    //模糊查询的方法
    public List<Manage> getForListWithCriteriaManage(CriteriaManage cc) {
        String sql="select id,account,pw from manage "
                + "where account like ?";
        //修改了CriteriaManage的get方法，使其返回的字符串中含有%%
        return getForList(sql,cc.getAccount());
    }
	
	 @Override
	    //获取整个数据库中的所有数据
	    public List<Manage> getAll() {
	        String sql1="select id,account,pw from manage";
	        return getForList(sql1);
	    }
	 
	 @Override
	    //插入数据到数据库的方法
	    public void save(Manage manage) {
	        String sql2="insert into manage(account,pw) values(?,?)";
	        update(sql2, manage.getAccount(),manage.getPw());
	    }
	 
	 @Override
	    //获取某个数据
	    public Manage get(Integer id) {
	        String sql3="select id,account,pw from manage where id=?";
	        return get(sql3,id);
	    }
	 
	 @Override
	    //删除id=?的数据
	    public void delete(Integer id) {
	        String sql4="delete from manage where id=?";
	        update(sql4, id);
	    }
	 
	 @Override
	    //用名字获取count(name)，即获取用户的数量
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
