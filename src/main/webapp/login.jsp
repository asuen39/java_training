<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
	<!-- ログインのメッセージ表示 -->
	<% String loginMsg = (String)request.getAttribute("loginMsg"); %>
	
	<!-- 画面が再読み込みされた時にログインメッセージがあるか判定 -->
	<% if (loginMsg != null) { %>
		<!-- ログインメッセージ表示 -->
		<%= loginMsg %>
	<% } %> 
	
	<!-- inputに入力された値はlogin.javaにpostで送られる。 idとpassが送られる -->
	<form action="./login" method="post">
		<div class="login_area">
			<div class="input_area"><label class="input_label">ID:</label><input type="text" name="login_id"></div>
			<div class="input_area"><label  class="input_label">pw:</label><input type="text" name="login_pw"></div>
			<button type="submit" class="submit">login</button>
		</div>
	</form>
</body>
</html>