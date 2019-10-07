package cn.edu.login;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	DbUtil db = new DbUtil();
	UserDao userDao = new UserDao();
	
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 做get请求的处理
		this.doPost(req, resp);
	}
	//Post和Get实现一个即可 如提交给servlet时使用get，则实现doGet方法
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 做post请求的处理  
		String account=req.getParameter("account");
		String pw=req.getParameter("pw");
		Connection con=null;
		try {
			User user=new User(account,pw);
			con=db.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null) {
				req.setAttribute("error", "账号或密码错误");
				req.setAttribute("account", account);
				req.setAttribute("pw", pw);
				req.getRequestDispatcher("view/login/login.jsp").forward(req, resp);  //这是服务器跳转 地址栏网址不变
			}else {
				HttpSession session=req.getSession();
				session.setAttribute("currentUser", currentUser);
				resp.sendRedirect("view/main/index.jsp");								//这是客户端跳转 地址栏网址改变
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
