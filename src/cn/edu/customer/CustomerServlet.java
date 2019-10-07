package cn.edu.customer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.customer.CriteriaCustomer;
import cn.edu.customer.Customer;

import cn.edu.customer.CustomerDAO;
import cn.edu.customer.CustomerDAOJdbcImpl;


public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDAO customerDAO=new CustomerDAOJdbcImpl();
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        doPost(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        //���÷��䣬���ܶ����.do��β������
	        
	        //1.���� servletPath��ȡ���к�ߴ�.do�ķ�������.do ,�������/pass.do
	        String servletPath=request.getServletPath();
	        
	        
	        //2.���ַ��������ȥ.do����ַ�
	        String methodName=servletPath.substring(1);
	        methodName=methodName.substring(0,methodName.length()-3);
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
        String name=request.getParameter("name");
        String account=request.getParameter("account");
        String phone=request.getParameter("phone");
        
        //�����������װΪһ��CriteriaCustomer����
        CriteriaCustomer cc=new CriteriaCustomer(account,name,phone);
        
        //1.����CustomerDAO��getForListWithCriteriaCustomer(cc)�����õ�lists�ļ���
        List<Customer> lists=customerDAO.getForListWithCriteriaCustomer(cc);
        
        //2.��list���Ϸŵ�request��
        request.setAttribute("list", lists);
        
        //3.ת��ҳ�浽index.jsp(����ʹ���ض���) /������Ǹ�Ŀ¼�µ�jsp�ļ���
        request.getRequestDispatcher("view/main/customer-list.jsp").forward(request, response);
    }
	    
    //�������ݵķ�����
    private void addCustomer(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        //1.��ȡ��������name,address,phone
        String name=request.getParameter("name");
        String account=request.getParameter("account");
        String pw = request.getParameter("pw");
        String phone=request.getParameter("phone");
        
        //���������Ƿ��Ѿ���ռ����:����CustomerDAO��getCountWithName��������ȡname�����Ƿ����0���������....
        //������Ϣ���Ի���:value="<%=request.getParameter("name")==null ? "" :%>"
        long count=customerDAO.getCountWithName(account);
        
        if (count>0) {
            request.setAttribute("message","�˺�"+account+"�Ѿ���ռ���ˣ�������ѡ�񣡣���");
            
            //�����ظ��ˣ������ת����/newcustomer.jsp
            request.getRequestDispatcher("view/main/customer-add.jsp").forward(request, response);
            //��������
            return ;
        }
        
        //2.����֤ͨ���Ļ����ѱ�������װΪһ��customer�Ķ���
        Customer customer=new Customer(account,pw,name,phone); 
        
        //3.����CustomerDAO��save����ִ�б���
        customerDAO.save(customer);
        
        //���ݲ���ɹ��������ת����newcustomer.jsp
        //request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
        
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
            //2.����CustomerDAO��getId()����ִ��ɾ��
            customerDAO.delete(id);
        } catch (Exception e) {
            response.sendRedirect("view/other/error.jsp");
        }
        //�ض����ҳ��jsp,��ǰ�治�ü�"/"
        response.sendRedirect("query.do");
    }
    
    //Ԥ�޸����ݷ���
    private void edit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //����������ѯ�����ݲ����ڵĴ�����ʾ��error.jspҳ��
        String forwardPath="view/other/error.jsp";
        
        //1.��ȡ�����id
        String idStr=request.getParameter("id");
        
        //2. ���� CustomerDAO �� customerDAO.get(id) ��ȡ�� id ��Ӧ�� Customer ���� customer
        try {
            
            Customer customer=customerDAO.get(Integer.parseInt(idStr));
            //������ݴ��ڵ���ת��updatecustomer.jspҳ�棬�����޸�����
            if(customer!=null){
                forwardPath="view/main/customer-modify.jsp";
                //�����ݷŵ�request�����ת��������
                request.setAttribute("customer", customer);
            }
        } catch (Exception e) {
            System.out.println("fsdlkf");
        }
        
        //4. ��Ӧ updatecustomer.jsp ҳ��: ת��.
        request.getRequestDispatcher(forwardPath).forward(request, response);
    }
    
    //�޸����ݷ���
    private void update(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //1. ��ȡ������: id, name, address, phone, oldName
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String pw=request.getParameter("pw");
        String phone=request.getParameter("phone");
        String oldaccount=request.getParameter("oldaccount");
        String account=request.getParameter("account");
        
        //2. ���� name �Ƿ��Ѿ���ռ��:
        
        //2.1 �Ƚ� name �� oldName �Ƿ���ͬ, ����ͬ˵�� name ����. 
        //2.1 ������ͬ, ����� CustomerDAO �� getCountWithName(String name) ��ȡ name �����ݿ����Ƿ����
        if(!oldaccount.equalsIgnoreCase(account)){
            long count=customerDAO.getCountWithName(account);
            
            //2.2 ������ֵ���� 0, ����Ӧ updatecustomer.jsp ҳ��: ͨ��ת���ķ�ʽ����Ӧ newcustomer.jsp
            if (count>0) {
                //2.2.1 �� updatecustomer.jsp ҳ����ʾһ��������Ϣ: �û��� name �Ѿ���ռ��, ������ѡ��!
                //�� request �з���һ������ message: �û��� name �Ѿ���ռ��, ������ѡ��!, 
                //��ҳ����ͨ�� request.getAttribute("message") �ķ�ʽ����ʾ
                request.setAttribute("message", "�˺�"+account+"�Ѿ���ռ�ã�������ѡ�񣡣���");
                
                //2.2.2 newcustomer.jsp �ı�ֵ���Ի���. 
                //address, phone ��ʾ�ύ�����µ�ֵ, �� name ��ʾ oldName, ���������ύ�� name
                
                //2.2.3 ��������: return 
                request.getRequestDispatcher("view/main/customer-modify.jsp").forward(request, response);
                return ;
            }
        }
        //3. ����֤ͨ��, ��ѱ�������װΪһ�� Customer ���� customer
        Customer customer=new Customer(account,pw,name,phone);
        customer.setId(Integer.parseInt(id));
        
        //4. ���� CustomerDAO ��  update(Customer customer) ִ�и��²���
        customerDAO.update(customer);
        
        //5. �ض��� query.do
        response.sendRedirect("view/other/success.jsp");
    }
}
