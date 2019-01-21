<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<body>
<a href="helloworld">
    <h2>Hello World!</h2>
</a>
<br>
<a href="testParamsAndHeaders">testParamsAndHeaders</a>
<br>
<a href="testPathVariable/1">testParamsAndHeaders</a>
<br>
<form action="testRest/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="TestRest DELETE">
</form>
<br>
<form action="testRest/1" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="TestRest PUT">
</form>
<br>
<form action="testRest" method="post">
    <input type="submit" value="TestRest POST">
</form>
<br>
<a href="testRest/1">TestRest GET</a>
<br>
<a href="testRequestParam?username=qwe&age=12">testRequestParam</a>
<br>
<a href="testRequestHeader">testRequestHeader</a>
<br>
<a href="testCookieValue">testCookieValue</a>
<br>
<form action="testPojo" method="post">
    <table style="text-align:center ">
        <tr>
            <td>username</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>age</td>
            <td><input type="text" name="age"></td>
        </tr>
        <tr>
            <td>email</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>province</td>
            <td><input type="text" name="address.province"></td>
        </tr>
        <tr>
            <td>city</td>
            <td><input type="text" name="address.city"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit"></td>
        </tr>
    </table>
</form>
<br>
<a href="testServletAPI">testServletAPI</a>
<br>
<a href="testModelAndView">testModelAndView</a>
<br>
<a href="testMap">testMap</a>
<br>
<a href="testSessionAttributes">testSessionAttributes</a>
<br>
<form action="testModelAttribute" method="post">
    <table style="text-align:center ">
        <tr>
            <input type="hidden" name="id" value="1">
            <td>username</td>
            <td><input type="text" name="username" value="Tom"></td>
        </tr>
        <tr>
            <td>age</td>
            <td><input type="text" name="age" value="18"></td>
        </tr>
        <tr>
            <td>email</td>
            <td><input type="text" name="email" value="qwe@qq.com"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit"></td>
        </tr>
    </table>
</form>
<br>
<a href="testView">testView</a>
<br>
<a href="testRedirect">testRedirect</a>
<br>
<a href="testForward">testForward</a>
<br>
<a href="user">user</a>
<br>
<a href="testJson">testJson</a>
<br>
<a href="i18n">i18n</a>
<br>
<a href="testResponseEntity">testResponseEntity</a>
<br>
<form action="<%= application.getContextPath() %>/testFileUpload" method="post" enctype="multipart/form-data">
    File : <input type="file" name="file">
    <br>
    Desc : <input type="text" name="desc">
    <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
