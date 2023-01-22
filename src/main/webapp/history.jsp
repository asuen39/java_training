<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.HistoriesBean" %>
<%@ page import="Dao.UsersBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>history</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
<script type="text/javascript" src="./js/all.js"></script>

<!-- ログインのリクエストデータの取得 -->
<% UsersBean HistoriesLoginUser = (UsersBean)request.getAttribute("historiesLoginUser"); %>

<!-- 問題一覧のリクエストデータの取得 -->
<% ArrayList<HistoriesBean> HistoriesList = (ArrayList<HistoriesBean>) request.getAttribute("historiesList"); %>
	<div class="global_area">
		<div class="top_area">
			<div class="result_title_area">
				<h2 class="result_title">履歴</h2>
				<div class="top_box align_center">
					<button type="button" class="auto-right logout_button" onclick="Top()">Top</button>
					<button type="button" class="auto-right logout_button" onclick="Logout()">logout</button>
				</div>
			</div>
			<div class="main_area">
				<table class="history_table">
					<tr>
						<td>氏名</td>
						<td>得点</td>
						<td>採点時間</td>
					</tr>
					<!-- 取得してきた問題一覧をfor文で一つずつ取り出す  -->
					<%for(int i = 0; i < HistoriesList.size(); i++){%>
						<!-- HistoriesBean型にhistoriesBeanを定義する。1つずつ取り出されたHistoriesListを格納する。  -->
						<%HistoriesBean historiesBean = HistoriesList.get(i);%>
						<tr>
							<!-- ユーザー名表示 -->
							<td><%=HistoriesLoginUser.getName() %></td>
							<!-- 点数の表示 -->
							<td><%=historiesBean.getPoint()%></td>
							<!-- 採点時の日時の表示 -->
							<td><%=historiesBean.getCreatedAt()%></td>
						</tr>
					<% } %>
				</table>
			</div>
		</div>
	</div>
</body>
</html>