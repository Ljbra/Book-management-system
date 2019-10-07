package cn.edu.manage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.manage.CriteriaManage;
import cn.edu.manage.Manage;

import cn.edu.manage.ManageDAO;
import cn.edu.manage.ManageDAOJdbcImpl;


public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ManageDAO manageDAO = new ManageDAOJdbcImpl();
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        doPost(request, response);
	    }


	 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        //利用反射，接受多个以.does结尾的请求
	        
	        //1.变量 servletPath获取所有后边带.does的方法名和.does ,例如这个/pass.does
	        String servletPath=request.getServletPath();
	        
	        
	        //2.在字符串后面除去.does这个字符
	        String methodName=servletPath.substring(1);
	        methodName=methodName.substring(0,methodName.length()-5);
	        System.out.println(methodName);
	        try {
	            //反射获取该类的该方法
	            Method method=getClass().getDeclaredMethod
	                    (methodName, HttpServletRequest.class,HttpServletResponse.class);
	            //这里面的this指通过这个方法的到方法名字，并且输出，及this指methodName的到的方法名字
	            method.invoke(this,request,response);
	            
	            
	        } catch (Exception e) {
	            //如果出现错误，去error页面
	            response.sendRedirect("view/other/error.jsp");
	        } 
	        
	    }

//查询方法
 private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //获取模糊查询的请求参数
     String account=request.getParameter("account");
     
     //把请求参数封装为一个CriteriaManage对象
     CriteriaManage cc=new CriteriaManage(account);
     
     //1.调用ManageDAO的getForListWithCriteriaManage(cc)方法得到lists的集合
     List<Manage> lists=manageDAO.getForListWithCriteriaManage(cc);
     
     //2.把list集合放到request中
     request.setAttribute("list", lists);
     
     //3.转发页面到index.jsp(不能使用重定向) /代表的是根目录下的jsp文件；
     request.getRequestDispatcher("view/main/manage-list.jsp").forward(request, response);
 }
	    
 //插入数据的方法：
 private void addManage(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException{
     
     //1.获取表单参数，name,address,phone
     String account=request.getParameter("account");
     String pw = request.getParameter("pw");
     
     //检验名字是否已经被占用了:调用ManageDAO的getCountWithName方法，获取name参数是否大于0，如果大于....
     //并且消息可以回显:value="<%=request.getParameter("name")==null ? "" :%>"
     long count=manageDAO.getCountWithName(account);
     
     if (count>0) {
         request.setAttribute("message","账号"+account+"已经被占用了，请重新选择！！！");
         
         //名字重复了，请求的转发到/newmanage.jsp
         request.getRequestDispatcher("view/main/manage-add.jsp").forward(request, response);
         //结束方法
         return ;
     }
     
     //2.若验证通过的话，把表单参数封装为一个manage的对象
     Manage manage=new Manage(account,pw); 
     
     //3.调用ManageDAO的save方法执行保存
     manageDAO.save(manage);
     
     //数据插入成功后，请求的转发到newmanage.jsp
     //request.getRequestDispatcher("/newmanage.jsp").forward(request, response);
     
     //4.数据插入成功后，重定向到success.jsp页面：使用重定向可以避免出现表单的重复提交问题. 
     response.sendRedirect("view/other/success.jsp");
     
 }
 
 //删除数据的方法
 private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
     //1.获取请求的id
     String idStr=request.getParameter("id");
     int id=0;
     try {
         id=Integer.parseInt(idStr);
         //2.调用ManageDAO的getId()方法执行删除
         manageDAO.delete(id);
     } catch (Exception e) {
         response.sendRedirect("view/other/error.jsp");
     }
     //重定向的页面jsp,其前面不用加"/"
     response.sendRedirect("query.does");
 }
 
 //预修改数据方法
 private void edit(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     //出现了所查询的数据不存在的错误显示到error.jsp页面
     String forwardPath="view/other/error.jsp";
     
     //1.获取请求的id
     String idStr=request.getParameter("id");
     
     //2. 调用 ManageDAO 的 manageDAO.get(id) 获取和 id 对应的 Manage 对象 manage
     try {
         
         Manage manage=manageDAO.get(Integer.parseInt(idStr));
         //如果数据存在的跳转到updatemanage.jsp页面，进行修改数据
         if(manage!=null){
             forwardPath="view/main/manage-modify.jsp";
             //将数据放到request请求的转发的里面
             request.setAttribute("manage", manage);
         }
     } catch (Exception e) {
         System.out.println("fsdlkf");
     }
     
     //4. 响应 updatemanage.jsp 页面: 转发.
     request.getRequestDispatcher(forwardPath).forward(request, response);
 }
 
 //修改数据方法
 private void update(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     //1. 获取表单参数: id, name, address, phone, oldName
     String id=request.getParameter("id");
     String pw=request.getParameter("pw");
     String oldaccount=request.getParameter("oldaccount");
     String account=request.getParameter("account");
     
     //2. 检验 name 是否已经被占用:
     
     //2.1 比较 name 和 oldName 是否相同, 若相同说明 name 可用. 
     //2.1 若不相同, 则调用 ManageDAO 的 getCountWithName(String name) 获取 name 在数据库中是否存在
     if(!oldaccount.equalsIgnoreCase(account)){
         long count=manageDAO.getCountWithName(account);
         
         //2.2 若返回值大于 0, 则响应 updatemanage.jsp 页面: 通过转发的方式来响应 newmanage.jsp
         if (count>0) {
             //2.2.1 在 updatemanage.jsp 页面显示一个错误消息: 用户名 name 已经被占用, 请重新选择!
             //在 request 中放入一个属性 message: 用户名 name 已经被占用, 请重新选择!, 
             //在页面上通过 request.getAttribute("message") 的方式来显示
             request.setAttribute("message", "账号"+account+"已经被占用，请重新选择！！！");
             
             //2.2.2 newmanage.jsp 的表单值可以回显. 
             //address, phone 显示提交表单的新的值, 而 name 显示 oldName, 而不是新提交的 name
             
             //2.2.3 结束方法: return 
             request.getRequestDispatcher("view/main/manage-modify.jsp").forward(request, response);
             return ;
         }
     }
     //3. 若验证通过, 则把表单参数封装为一个 Manage 对象 manage
     Manage manage=new Manage(account,pw);
     manage.setId(Integer.parseInt(id));
     
     //4. 调用 ManageDAO 的  update(Manage manage) 执行更新操作
     manageDAO.update(manage);
     
     //5. 重定向到 query.do
     response.sendRedirect("view/other/success.jsp");
 }

}
