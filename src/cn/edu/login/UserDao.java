package cn.edu.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.PreparedStatement;

public class UserDao {

	public User login(Connection con,User user) throws SQLException{
		User resultUser=null;
		String sql="select * from manage where account=? and pw=?";
		PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, user.getAccount());
		ps.setString(2, user.getPw());
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			resultUser = new User();
			resultUser.setAccount(rs.getString("account"));
			resultUser.setPw(rs.getString("pw"));
		}
		return resultUser;
	}
}
