package cn.edu.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {

	//�����ԣ��ķ���
	
	//������Ľӿ�
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;

	
	private String url = "jdbc:mysql://localhost:3306/jiao_book";
	private String user = "root";
	private String password = "990926";
	private String diriver = "com.mysql.jdbc.Driver";
	
	
	//method1:�������ݿ�
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
			System.out.println("�����������ݿ⣬���ӳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�����������ݿ⣬����ʧ��");
		}
	}
}
