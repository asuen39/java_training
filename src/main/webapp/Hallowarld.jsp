<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HalloWarld</title>
</head>
<body>
	  <%= request.getAttribute("foo") %>
	  
	  <form method="post" action="./HelloServlet">
	  	何か入力して: <input type="text" name="hoge">
	  	<button type="submit">送信</button>
	  </form>
</body>
</html>