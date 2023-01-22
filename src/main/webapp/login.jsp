<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<!-- Jquery読み込み -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>
	<!-- JS読み込み -->
	<script type="text/javascript" src="./js/login.js"></script>
	<!-- ID検証のメッセージ表示 -->
	<% String IdMsg = (String)request.getAttribute("idMsg"); %>
	
	<!-- ログインのメッセージ表示 -->
	<% String loginMsg = (String)request.getAttribute("loginMsg"); %>
	
	<!-- 画面が再読み込みされた時にログインメッセージがあるか判定 -->
	<% if (loginMsg != null) { %>
		<!-- ログインメッセージ表示 -->
		<%= loginMsg %>
	<% } %>
	
	<!-- エラー文章リクエスト -->
	<% String ErrorLoginId = (String)request.getAttribute("error_LoginId"); %>
	
	<% if( ErrorLoginId != null ) { %>
		<div style="color:red"><%= ErrorLoginId %></div>
	<% } %>
	
	<!-- inputに入力された値はlogin.javaにpostで送られる。 idとpassが送られる -->
	<form action="./login" method="post">
		<div class="login_area">
			<div id="text1"></div>
			<div class="input_area"><label class="input_label">ID:</label><input type="text" name="login_id" class="inputLoginId"></div>
			<div class="input_area"><label  class="input_label">pw:</label><input type="text" name="login_pw"></div>
			<button type="submit" class="submit">login</button>
		</div>
	</form>
</body>
</html>