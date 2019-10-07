package cn.edu.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {

	//三属性，四方法
	
	//三大核心接口
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;

	
	private String url = "jdbc:mysql://localhost:3306/jiao_book";
	private String user = "root";
	private String password = "990926";
	private String diriver = "com.mysql.jdbc.Driver";
	
	
	//method1:创建数据库
	public Connection getCon() throws Exception {
		Class.forName(diriver);
		Connection con = DriverManager.getConnection(url,user,password);
		return con;
	}
	
	public static void getClose(Connection con) throws SQLException {
		if(con!=null) {
			con.close();
		}
	}
	public static void main(String[] args) {
		DbUtil db = new DbUtil();
		try {
			db.getCon();
			System.out.println("测试连接数据库，连接成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("测试连接数据库，连接失败");
		}
	}
}
