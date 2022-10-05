<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>top</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">

</head>
<body>
	<!-- JS読み込み -->
	<script type="text/javascript" src="./js/top.js"></script>
	<div class="global_area">
		<div class="top_area">
			<button type="button" name="logout" class="auto-right logout_button" onclick="Logout()">logout</button>
			<form>
				<button type="button" class="top_button" onclick="Question()">問題と答えを確認・登録する ＞</button>
				<button type="button" class="top_button" onclick="Test()">テストをする ＞</button>
				<button type="button" class="top_button" onclick="PastGradeResults()">過去の採点結果をみる ＞</button>
			</form>
		</div>
	</div>
</body>
</html>