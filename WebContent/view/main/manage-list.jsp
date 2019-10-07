<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>      
    
    
    
<%
	//获取绝对路径路径 
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
%>     
    
    
    
    
    
<!DOCTYPE html>
<html class="x-admin-sm">
    <head>
    <base href="<%=basePath %>" />
        <meta charset="UTF-8">
        <title>欢迎页面-X-admin2.2</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="resources/index/css/font.css">
        <link rel="stylesheet" href="resources/index/css/xadmin.css">
        <script src="resources/index/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="resources/index/js/xadmin.js"></script>
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a>
              <cite>导航元素</cite></a>
          </span>
          <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
            <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
        </div>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <form class="layui-form layui-col-space5" action="query.does" method = "post">                                  
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="text" name="account"  placeholder="请输入账号" class="layui-input"/>
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input class="layui-btn" type="submit" value="查询"><i class="layui-icon">&#xe615;</i>
                                </div>
                            </form>
                        </div>
                        <div class="layui-card-header">
                            <button class="layui-btn" onclick="xadmin.open('添加管理员','view/main/manage-add.jsp',600,400)"><i class="layui-icon"></i>添加</button>
                        </div>
                        <div class="layui-card-body layui-table-body layui-table-main">
                            <table class="layui-table layui-form">
                                <thead>
                                  <tr>
                                    <th>ID</th>
                                    <th>账号</th>
                                    <th>密码</th>
                                    <th>操作</th></tr>
                                </thead>
                                <c:forEach items="${requestScope.list }" var="cust">
                                <tbody>                                  
                                  <tr>                                                   
                                    <td>${cust.id}</td>
                                    <td>${cust.account}</td>
                                    <td>${cust.pw}</td>                                                                
                                    <td class="td-manage">
                                      <a title="测试"  onclick="xadmin.open('测试','view/other/success.jsp',400,400)" href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                      </a>
                                      <c:url value="edit.does" var="editurl">
                                		<c:param name="id" value="${cust.id }"></c:param>
                            		  </c:url>
                                      <a title="编辑" onclick="xadmin.open('编辑','${editurl }',600,400)" href="javascript:;">
                                        <i class="layui-icon">&#xe631;</i>
                                      </a>
                                      <c:url value="delete.does" var="deleteurl">
                                		<c:param name="id" value="${cust.id }"></c:param>
                            		  </c:url>
                                      <a title="删除" href="${deleteurl }">
                                        <i class="layui-icon">&#xe640;</i>
                                      </a>
                                    </td>
                                    </c:forEach> 
                                   </tr>                                 
                                </tbody>
                            </table>
                        </div>
                        <div class="layui-card-body ">
                            <div class="page">
                                <div>
                                  <a class="prev" href="">&lt;&lt;</a>                          
                                  <span class="current">1</span>
                                  <a class="num" href="">2</a>
                                  <a class="num" href="">3</a>
                                  <a class="num" href="">...</a>
                                  <a class="next" href="">&gt;&gt;</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
</html>