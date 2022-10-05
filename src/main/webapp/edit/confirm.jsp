<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit/confirm</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
<!-- JS読み込み -->
<script type="text/javascript" src="/java_training/js/all.js"></script>
<script type="text/javascript" src="/java_training/js/edit/confirm.js"></script>
<!-- 問題番号のIDをリクエスト -->
<% String EditId = (String)request.getAttribute("editId"); %>
<!-- テキストエリアのデータをリクエスト -->
<% String TextAreaEdit = (String)request.getAttribute("textAreaEdit"); %>
<% String[] AnswerList = (String[])request.getAttribute("answerList"); %>

<input type="hidden" class="questionId" value="<%= EditId %>">
	<div class="global_area">
		<div class="top_area">
			<div class="top_box">
				<button type="button" class="auto-right logout_button" onclick="Top()">Top</button>
				<button type="button" class="auto-right logout_button" onclick="Logout()">logout</button>
			</div>
			<div class="main_area">
				<ul>
					<li class="edit_area">問題番号:<span class="toi_number"><%= EditId %></span></li>
					<li class="edit_area flex_end">問題: 
						<label class="label_textarea"></label>
					</li>
					<li class="edit_area flex_end">答え: 
						<label class="label_long"></label>
					</li>
					<li class="edit_area flex_end">
						<label class="label_long"></label>
					</li>
					<li class="edit_area flex_end">
						<label class="label_long"></label>
					</li>
				</ul>
			</div>
			<div class="top_box">
				<button type="button" class="auto-right logout_button" onclick="Return()">戻る</button>
				<button class="auto-right logout_button">更新</button>
			</div>
		</div>
	</div>
</body>
</html>