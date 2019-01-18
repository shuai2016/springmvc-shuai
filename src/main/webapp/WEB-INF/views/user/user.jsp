<%--
  Created by IntelliJ IDEA.
  User: shuai
  Date: 2019/1/17
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>user</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
</head>
<body>
<form:form action="user" method="POST" modelAttribute="user">
    <table style="text-align:center ">
        <tr>
            <td>username</td>
            <td><form:input path="username"/></td>
        </tr>
        <tr>
            <td>password</td>
            <td><form:password path="password"/></td>
        </tr>
        <tr>
            <td>age</td>
            <td><form:input path="age"/></td>
        </tr>
        <tr>
            <td>birth</td>
            <td><form:input path="birth"/></td>
        </tr>
        <tr>
            <td>salary</td>
            <td><form:input path="salary"/></td>
        </tr>
        <tr>
            <td>email</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>province</td>
            <td><form:input path="address.province"/></td>
        </tr>
        <tr>
            <td>city</td>
            <td><form:input path="address.city"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit"></td>
        </tr>
    </table>
</form:form>
<form action="testConversionServiceConverter">
    <input type="text" name="user" placeholder="username-password-age-email" size="50">
    <input type="submit" value="submit">
</form>
<script>
    $(function(){
        alert("hello jQuery");
    })
</script>
</body>
</html>