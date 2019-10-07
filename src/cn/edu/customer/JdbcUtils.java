package cn.edu.customer;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {


	//ʵ�����ݿ����ӳصķ�����
	       
	    //�þ�̬�����ִֻ��һ��
	    private static ComboPooledDataSource dataSource=null;
	    static{
	        dataSource=new ComboPooledDataSource("mvcapp");
	    }
	    public static Connection getConnection() throws SQLException{
	        return dataSource.getConnection();
	        
	    }
	    
	    @Test
	    //�������ݿ����ӳ��Ƿ����ӳɹ�
	    public void testT() throws SQLException{
	        System.out.println(getConnection());
	    }
	    
	    //�ͷ�connection����
	    public static void releaseConnection(Connection connection){
	        try {
	            if(connection!=null){
	                connection.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
