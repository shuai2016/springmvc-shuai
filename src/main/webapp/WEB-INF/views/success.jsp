<%--
  Created by IntelliJ IDEA.
  User: shuai
  Date: 2019/1/14
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
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
</body>
</html>
