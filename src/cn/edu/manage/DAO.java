package cn.edu.manage;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;



/*
 * 封装了基本的增删改查方法，以供子类继承使用；
 * 当前dao没有事务，直接在方法中获取数据库的链接
 * */

public class DAO <T>{

	//这个是线程安全的
    private QueryRunner queryRunner=new QueryRunner();
    
     private Class<T> clazz;
     
     //类的构造方法
     public DAO() {
         //得到父类带泛型的类型
         //type类型导包为import java.lang.reflect.Type;反射类型里面的
         //反射。。。。。。Type所有超级类接口，ParameterizedType表示参数化类型,参数化类型在反射方法首次需要时创建（在此包中指定）。
         //当创建参数化类型 p 时，p 实例化的一般类型声明会被解析，并且按递归方式创建 p 的所有类型参数。
         
        Type superClass=getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            ParameterizedType parameterizedType=(ParameterizedType) superClass;
            
            // getActualTypeArguments():返回表示此类型实际类型参数的 Type 对象的数组。
            Type[] typeArgs=parameterizedType.getActualTypeArguments();
            if (typeArgs!=null && typeArgs.length>0) {
                if (typeArgs[0] instanceof Class) {
                    clazz=(Class<T>) typeArgs[0];
                }
            }
        }
    }

    //返回某一字段的值：比如返回某一条记录的customerName,或返回有多少条记录
     public <E> E getForValue(String sql,Object...args){
         Connection connection=null;
        try {
            connection=JdbcUtils.getConnection();
            return (E) queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.releaseConnection(connection);
        }
        return null;     
     }
     
     //返回所对应的list集合,获得的是一组的
     public List<T> getForList(String sql,Object...args){
         Connection connection=null;
        try {
            connection=JdbcUtils.getConnection();
            return queryRunner.query(connection,sql,new BeanListHandler<>(clazz),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.releaseConnection(connection);
        } 
        return null; 
     }
     
    //返回对应的T的一个实体类的对象
    public T get(String sql,Object...args){
        
        Connection connection=null;
        try {
            connection=JdbcUtils.getConnection();
            return queryRunner.query(connection,sql,new BeanHandler<>(clazz),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.releaseConnection(connection);
        }
        return null;
    }
    
    
    //该方法封装了增删改操作
    public void update(String sql,Object...args){
        Connection connection=null;
        
        try {
            connection=JdbcUtils.getConnection();
            queryRunner.update(connection, sql, args);
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.releaseConnection(connection);
        }
    }
}

