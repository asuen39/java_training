<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.QuestionsBean" %>
<%@ page import="Dao.CorrectAnswersBean" %>
<%@ page import="Dao.UsersBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test/Result</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
<script type="text/javascript" src="../js/all.js"></script>

<!-- ログインのリクエストデータの取得 -->
<% UsersBean LoginUser = (UsersBean)request.getAttribute("loginUser"); %>

<!-- 問題数のリクエストデータの取得 -->
<% int TestId = (int)request.getAttribute("testId"); %>

<!-- 回答数のリクエストデータの取得 -->
<% int CountAnswer = (int)request.getAttribute("countAnswer"); %>

<!-- 点数のリクエストデータの取得 -->
<% int Score = (int)request.getAttribute("score"); %>

<!-- 日時のリクエストデータの取得 -->
<% String FormatNowDate = (String)request.getAttribute("formatNowDate"); %>

	<div class="global_area border">
		<div class="top_area">
			<div class="result_title_area">
				<h2 class="result_title">テスト結果</h2>
				<div class="top_box align_center">
					<button type="button" class="auto-right logout_button" onclick="Top()">Top</button>
					<button type="button" class="auto-right logout_button" onclick="Logout()">logout</button>
				</div>
			</div>
			<div class="main_area">
				<!-- ユーザー名表示 -->
				<p class="ml10"><%= LoginUser.getName() %></p>
				<!-- 問題数と合っている回答数の表示 -->
				<p class="ml10"><%= TestId %>問中<%= CountAnswer %>問正解です。</p>
				<!-- 点数の表示 -->
				<p class="ml10"><%= Score %>点でした。</p>
			</div>
			<div class="saiten_box">
				<!-- 日時の表示 -->
				<p><%= FormatNowDate %></p>
			</div>
			<button type="submit">採点結果履歴へ</button>
		</div>
	</div>
</body>
</html>