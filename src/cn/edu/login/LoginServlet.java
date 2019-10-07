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
		// ��get����Ĵ���
		this.doPost(req, resp);
	}
	//Post��Getʵ��һ������ ���ύ��servletʱʹ��get����ʵ��doGet����
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��post����Ĵ���  
		String account=req.getParameter("account");
		String pw=req.getParameter("pw");
		Connection con=null;
		try {
			User user=new User(account,pw);
			con=db.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null) {
				req.setAttribute("error", "�˺Ż��������");
				req.setAttribute("account", account);
				req.setAttribute("pw", pw);
				req.getRequestDispatcher("view/login/login.jsp").forward(req, resp);  //���Ƿ�������ת ��ַ����ַ����
			}else {
				HttpSession session=req.getSession();
				session.setAttribute("currentUser", currentUser);
				resp.sendRedirect("view/main/index.jsp");								//���ǿͻ�����ת ��ַ����ַ�ı�
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
