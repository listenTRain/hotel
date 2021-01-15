<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>数据分析</title>
    <!--引入echarts.min的js文件-->
    <script src="lib/echarts/echarts.min.js"></script>
    <!--引入jquery的js文件-->
    <script src="lib/echarts/jquery.min.js"></script>
</head>
<body>
    <div align="center">
        <h1>数据分析页面</h1>
        <div id="main" style="width: 1200px;height:550px;"></div>
    </div>
</body>
<script src="js/idd/showIdd.js"></script>

</html>
