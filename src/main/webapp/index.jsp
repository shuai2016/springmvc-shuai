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
</body>
</html>
