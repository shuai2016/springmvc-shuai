<%--
  Created by IntelliJ IDEA.
  User: shuai
  Date: 2019/1/14
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hello World !
<br>
request time : ${requestScope.time}
<br>
request user : ${requestScope.user}
<br>
session time : ${sessionScope.time}
<br>
session user : ${sessionScope.user}
<br>
<fmt:message key="i18n.username"></fmt:message>
<br>
<fmt:message key="i18n.password"></fmt:message>
<br>
<a href="<%=request.getContextPath()%>/i18n?locale=zh_CN">中文</a>
<a href="<%=request.getContextPath()%>/i18n?locale=en_US">英文</a>
</body>
</html>
