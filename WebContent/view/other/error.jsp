<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
 <%
	//获取绝对路径路径 
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
%> 

<!doctype html>
<html class="x-admin-sm">
    <head>
    <base href="<%=basePath %>" />
        <meta charset="UTF-8">
        <title>后台登录-X-admin2.2</title>
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />

        <link rel="stylesheet" href="resources/index/css/font.css">
        <link rel="stylesheet" href="resources/index/css/xadmin.css">
    </head>
    <body>
          <div class="layui-container">
           <div class="fly-panel"> 
            <div class="fly-none"> 
             <h2><i class="layui-icon layui-icon-404"></i></h2> 
             <p>页面或者数据被<a href=""> 纸飞机 </a>运到火星了，啥都看不到了…</p> 
            </div>
           </div>
          </div>
    </body>
</html>