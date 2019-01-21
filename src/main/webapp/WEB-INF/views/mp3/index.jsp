<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	</head>
	<body>
		<table>
			<tr>
				<td><span>网易云音乐链接</span></td>
				<td><input type="text" id="163url" size="60"/></td>
			</tr>
			<tr>
				<td><span>网易云音乐外链</span></td>
				<td><input type="text" id="163link" size="60" onfocus="this.select()" onmouseover="this.focus()"/></td>
			</tr>
			<tr>
				<td><input type="button" value="转换" onclick="change163url()"/></td>
			</tr>
		</table>
		<form action="<%=request.getContextPath()%>/downloadMusic" method="post" id="form">
			<input type="text" name="fileName" id="fileName">
			<input type="button" value="下载" onclick="download163url()">
			<input type="hidden" name="musicUrl" id="musicUrl">
		</form>
	</body>
	<script>
		function get163link() {
            var url = $("#163url").val();
            var temp = url.split("id=");
            var need = temp[1].split("&");
            var link = "http://music.163.com/song/media/outer/url?id="+need[0]+".mp3";
            return link;
        }
		function change163url(){
		    var link = get163link();
            $("#163link").val(link);
		}
		function download163url() {
            var link = get163link();
            $("#musicUrl").attr("value",link);
            $("#form").submit();
        }
	</script>
</html>
