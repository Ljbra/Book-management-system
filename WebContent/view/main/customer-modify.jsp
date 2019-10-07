<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
    
    
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
        <script type="text/javascript" src="resources/index/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="resources/index/js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
            <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
            <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]--></head>
    
    <body>
    
	<c:set var="id" value="${customer != null ? customer.id : param.id }"></c:set>
    <c:set var="name" value="${customer != null ? customer.name : param.name }"></c:set>
    <c:set var="oldaccount" value="${customer != null ? customer.account : param.oldaccount }"></c:set>
    <c:set var="account" value="${customer != null ? customer.account : param.oldaccount }"></c:set>
    <c:set var="pw" value="${customer != null ? customer.pw : param.pw }"></c:set>
    <c:set var="phone" value="${customer != null ? customer.phone : param.phone }"></c:set>    
       
        <div class="layui-fluid">
            <div class="layui-row">
                <form class="layui-form" action="update.do" method = "post">
                <input type="hidden" name="id" value="${id }"/>
        		<input type="hidden" name="oldaccount" value="${oldaccount }"/>
                    <div class="layui-form-item">
                        <label for="L_username" class="layui-form-label">用户名</label>
                        <div class="layui-input-inline">
                            <input type="text" id="name" name="name" class="layui-input" value="${name}"></div>
                    </div>
                    <div class="layui-form-item">
                        <label for="L_repass" class="layui-form-label">
                            <span class="x-red">*</span>账号</label>
                        <div class="layui-input-inline">
                            <input type="text" id="account" name="account" class="layui-input" value="${account}"></div>
                            <div class="layui-form-mid layui-word-aux">
						    <c:if test="${requestScope.message != null }">
						        <font color="red">${requestScope.message }</font>	
						    </c:if>						                                                 
                            </div>
                    </div>
                    <div class="layui-form-item">
                        <label for="L_pass" class="layui-form-label">
                            <span class="x-red">*</span>密码</label>
                        <div class="layui-input-inline">
                            <input type="text" id="pw" name="pw" class="layui-input" value="${pw}"></div>
                     </div>
                    <div class="layui-form-item">
                        <label for="L_repass" class="layui-form-label">
                            <span class="x-red">*</span>电话</label>
                        <div class="layui-input-inline">
                            <input type="text" id="phone" name="phone" class="layui-input" value="${phone}"></div>
                    </div>
                    <div class="layui-form-item">
                        <label for="L_repass" class="layui-form-label"></label>
                        <input type="submit" class="layui-btn" value = "修改"></div>
                </form>
            </div>
        </div>
    </body>

</html>