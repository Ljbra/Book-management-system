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
	        //���÷��䣬���ܶ����.does��β������
	        
	        //1.���� servletPath��ȡ���к�ߴ�.does�ķ�������.does ,�������/pass.does
	        String servletPath=request.getServletPath();
	        
	        
	        //2.���ַ��������ȥ.does����ַ�
	        String methodName=servletPath.substring(1);
	        methodName=methodName.substring(0,methodName.length()-5);
	        System.out.println(methodName);
	        try {
	            //�����ȡ����ĸ÷���
	            Method method=getClass().getDeclaredMethod
	                    (methodName, HttpServletRequest.class,HttpServletResponse.class);
	            //�������thisָͨ����������ĵ��������֣������������thisָmethodName�ĵ��ķ�������
	            method.invoke(this,request,response);
	            
	            
	        } catch (Exception e) {
	            //������ִ���ȥerrorҳ��
	            response.sendRedirect("view/other/error.jsp");
	        } 
	        
	    }

//��ѯ����
 private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //��ȡģ����ѯ���������
     String account=request.getParameter("account");
     
     //�����������װΪһ��CriteriaManage����
     CriteriaManage cc=new CriteriaManage(account);
     
     //1.����ManageDAO��getForListWithCriteriaManage(cc)�����õ�lists�ļ���
     List<Manage> lists=manageDAO.getForListWithCriteriaManage(cc);
     
     //2.��list���Ϸŵ�request��
     request.setAttribute("list", lists);
     
     //3.ת��ҳ�浽index.jsp(����ʹ���ض���) /������Ǹ�Ŀ¼�µ�jsp�ļ���
     request.getRequestDispatcher("view/main/manage-list.jsp").forward(request, response);
 }
	    
 //�������ݵķ�����
 private void addManage(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException{
     
     //1.��ȡ��������name,address,phone
     String account=request.getParameter("account");
     String pw = request.getParameter("pw");
     
     //���������Ƿ��Ѿ���ռ����:����ManageDAO��getCountWithName��������ȡname�����Ƿ����0���������....
     //������Ϣ���Ի���:value="<%=request.getParameter("name")==null ? "" :%>"
     long count=manageDAO.getCountWithName(account);
     
     if (count>0) {
         request.setAttribute("message","�˺�"+account+"�Ѿ���ռ���ˣ�������ѡ�񣡣���");
         
         //�����ظ��ˣ������ת����/newmanage.jsp
         request.getRequestDispatcher("view/main/manage-add.jsp").forward(request, response);
         //��������
         return ;
     }
     
     //2.����֤ͨ���Ļ����ѱ�������װΪһ��manage�Ķ���
     Manage manage=new Manage(account,pw); 
     
     //3.����ManageDAO��save����ִ�б���
     manageDAO.save(manage);
     
     //���ݲ���ɹ��������ת����newmanage.jsp
     //request.getRequestDispatcher("/newmanage.jsp").forward(request, response);
     
     //4.���ݲ���ɹ����ض���success.jspҳ�棺ʹ���ض�����Ա�����ֱ����ظ��ύ����. 
     response.sendRedirect("view/other/success.jsp");
     
 }
 
 //ɾ�����ݵķ���
 private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
     //1.��ȡ�����id
     String idStr=request.getParameter("id");
     int id=0;
     try {
         id=Integer.parseInt(idStr);
         //2.����ManageDAO��getId()����ִ��ɾ��
         manageDAO.delete(id);
     } catch (Exception e) {
         response.sendRedirect("view/other/error.jsp");
     }
     //�ض����ҳ��jsp,��ǰ�治�ü�"/"
     response.sendRedirect("query.does");
 }
 
 //Ԥ�޸����ݷ���
 private void edit(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     //����������ѯ�����ݲ����ڵĴ�����ʾ��error.jspҳ��
     String forwardPath="view/other/error.jsp";
     
     //1.��ȡ�����id
     String idStr=request.getParameter("id");
     
     //2. ���� ManageDAO �� manageDAO.get(id) ��ȡ�� id ��Ӧ�� Manage ���� manage
     try {
         
         Manage manage=manageDAO.get(Integer.parseInt(idStr));
         //������ݴ��ڵ���ת��updatemanage.jspҳ�棬�����޸�����
         if(manage!=null){
             forwardPath="view/main/manage-modify.jsp";
             //�����ݷŵ�request�����ת��������
             request.setAttribute("manage", manage);
         }
     } catch (Exception e) {
         System.out.println("fsdlkf");
     }
     
     //4. ��Ӧ updatemanage.jsp ҳ��: ת��.
     request.getRequestDispatcher(forwardPath).forward(request, response);
 }
 
 //�޸����ݷ���
 private void update(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     //1. ��ȡ������: id, name, address, phone, oldName
     String id=request.getParameter("id");
     String pw=request.getParameter("pw");
     String oldaccount=request.getParameter("oldaccount");
     String account=request.getParameter("account");
     
     //2. ���� name �Ƿ��Ѿ���ռ��:
     
     //2.1 �Ƚ� name �� oldName �Ƿ���ͬ, ����ͬ˵�� name ����. 
     //2.1 ������ͬ, ����� ManageDAO �� getCountWithName(String name) ��ȡ name �����ݿ����Ƿ����
     if(!oldaccount.equalsIgnoreCase(account)){
         long count=manageDAO.getCountWithName(account);
         
         //2.2 ������ֵ���� 0, ����Ӧ updatemanage.jsp ҳ��: ͨ��ת���ķ�ʽ����Ӧ newmanage.jsp
         if (count>0) {
             //2.2.1 �� updatemanage.jsp ҳ����ʾһ��������Ϣ: �û��� name �Ѿ���ռ��, ������ѡ��!
             //�� request �з���һ������ message: �û��� name �Ѿ���ռ��, ������ѡ��!, 
             //��ҳ����ͨ�� request.getAttribute("message") �ķ�ʽ����ʾ
             request.setAttribute("message", "�˺�"+account+"�Ѿ���ռ�ã�������ѡ�񣡣���");
             
             //2.2.2 newmanage.jsp �ı�ֵ���Ի���. 
             //address, phone ��ʾ�ύ�����µ�ֵ, �� name ��ʾ oldName, ���������ύ�� name
             
             //2.2.3 ��������: return 
             request.getRequestDispatcher("view/main/manage-modify.jsp").forward(request, response);
             return ;
         }
     }
     //3. ����֤ͨ��, ��ѱ�������װΪһ�� Manage ���� manage
     Manage manage=new Manage(account,pw);
     manage.setId(Integer.parseInt(id));
     
     //4. ���� ManageDAO ��  update(Manage manage) ִ�и��²���
     manageDAO.update(manage);
     
     //5. �ض��� query.do
     response.sendRedirect("view/other/success.jsp");
 }

}
