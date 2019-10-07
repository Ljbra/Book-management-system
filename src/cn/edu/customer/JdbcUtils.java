package cn.edu.customer;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {


	//实现数据库连接池的方法类
	       
	    //该静态代码块只执行一次
	    private static ComboPooledDataSource dataSource=null;
	    static{
	        dataSource=new ComboPooledDataSource("mvcapp");
	    }
	    public static Connection getConnection() throws SQLException{
	        return dataSource.getConnection();
	        
	    }
	    
	    @Test
	    //测试数据库连接池是否连接成功
	    public void testT() throws SQLException{
	        System.out.println(getConnection());
	    }
	    
	    //释放connection连接
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
