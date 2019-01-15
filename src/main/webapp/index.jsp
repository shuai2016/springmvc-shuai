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
</body>
</html>
