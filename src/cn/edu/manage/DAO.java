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
 * ��װ�˻�������ɾ�Ĳ鷽�����Թ�����̳�ʹ�ã�
 * ��ǰdaoû������ֱ���ڷ����л�ȡ���ݿ������
 * */

public class DAO <T>{

	//������̰߳�ȫ��
    private QueryRunner queryRunner=new QueryRunner();
    
     private Class<T> clazz;
     
     //��Ĺ��췽��
     public DAO() {
         //�õ���������͵�����
         //type���͵���Ϊimport java.lang.reflect.Type;�������������
         //���䡣����������Type���г�����ӿڣ�ParameterizedType��ʾ����������,�����������ڷ��䷽���״���Ҫʱ�������ڴ˰���ָ������
         //���������������� p ʱ��p ʵ������һ�����������ᱻ���������Ұ��ݹ鷽ʽ���� p ���������Ͳ�����
         
        Type superClass=getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            ParameterizedType parameterizedType=(ParameterizedType) superClass;
            
            // getActualTypeArguments():���ر�ʾ������ʵ�����Ͳ����� Type ��������顣
            Type[] typeArgs=parameterizedType.getActualTypeArguments();
            if (typeArgs!=null && typeArgs.length>0) {
                if (typeArgs[0] instanceof Class) {
                    clazz=(Class<T>) typeArgs[0];
                }
            }
        }
    }

    //����ĳһ�ֶε�ֵ�����緵��ĳһ����¼��customerName,�򷵻��ж�������¼
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
     
     //��������Ӧ��list����,��õ���һ���
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
     
    //���ض�Ӧ��T��һ��ʵ����Ķ���
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
    
    
    //�÷�����װ����ɾ�Ĳ���
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

