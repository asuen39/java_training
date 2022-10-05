<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<!-- JS読み込み -->
<script type="text/javascript" src="./js/all.js"></script>
<script type="text/javascript" src="./js/register.js"></script>
<body>
	<!-- エラー文章設置 -->
	<% String ErrorTextArea = (String)request.getAttribute("error_Textarea"); %>
	<% String ErrorAnswer = (String)request.getAttribute("error_Answer"); %>
	<div class="global_area">
		<div class="top_area">
			<div class="top_box">
				<button type="button" class="auto-right logout_button" onclick="Top()">Top</button>
				<button type="button" class="auto-right logout_button" onclick="Logout()">logout</button>
			</div>
			<form action="./register/confirm" method="post" class="top_area">
				<div class="main_area">
					<ul id="ul_list">
						<li class="edit_area flex_end">問題: 
							<textarea name="textarea_edit" rows="6" cols="85"></textarea>
						</li>
						<li class="edit_area flex_end">答え: 
							<input type="text" name="answer" style="width:89%;">
						</li>
						<li class="edit_area flex_end">
							<span style="width:40px;"></span>
							<input type="text" name="answer" style="width: 84%;">
							<button type="button" onclick="delete_btn(this)">削除</button>
						</li>
						<li class="edit_area flex_end">
							<span style="width:40px;"></span>
							<input type="text" name="answer" style="width: 84%;">
							<button type="button" onclick="delete_btn(this)">削除</button>
						</li>
					</ul>
				</div>
				<div class="top_box">
					<button type="button" class="auto-right logout_button" onclick="Return()">戻る</button>
					<button type="submit" class="auto-right logout_button">確認</button>
					<button type="button" id="addition_btn" class="auto-right logout_button">追加</button>
				</div>
			</form>
			<!-- form内にあると無記入の値が送られてしまうのでform外に設置 -->
			<ul class="close_area">
				<li class="edit_area flex_end addition_content">
					<span style="width:40px;"></span>
					<input type="text" name="answer" style="width: 84%;">
					<button type="button" onclick="delete_btn(this)">削除</button>
				</li>
			</ul>
		</div>
		<% if( ErrorTextArea != null ) { %>
			<div style="color:red"><%= ErrorTextArea %></div>
		<% } %>
		<% if( ErrorAnswer != null ) { %>
			<div style="color:red"><%= ErrorAnswer %></div>
		<% } %>
	</div>
</body>
</html>