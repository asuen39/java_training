<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register/confirm</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
<!-- JS読み込み -->
<script type="text/javascript" src="/java_training/js/all.js"></script>
<script type="text/javascript" src="/java_training/js/register/confirm.js"></script>

<% String TextAreaEdit = (String)request.getAttribute("textAreaEdit"); %>

<% String[] AnswerList = (String[])request.getAttribute("answerList"); %>

<% String ErrorMsgTextarea = (String)request.getAttribute("errorMsgTextarea"); %>

<% String ErrorMsgAnswer = (String)request.getAttribute("errorMsgAnswer"); %>

	<div class="global_area">
		<div class="top_area">
			<div class="top_box">
				<button type="button" class="auto-right logout_button" onclick="Top()">Top</button>
				<button type="button" class="auto-right logout_button" onclick="Logout()">logout</button>
			</div>
			<form action="./entry" method="post" class="top_area" style="width:100%;">
				<div class="main_area">
					<ul>
						<li class="edit_area flex_end">問題: 
							<label class="label_textarea"><%= TextAreaEdit %></label>
							<input type="hidden" name="textarea_edit" value="<%= TextAreaEdit %>">
						</li>
						<li class="edit_area flex_end">
							<ul class="edit_area flex_end" style="width: 96%;">
								<li class="edit_area">答え: </li>
								<li style="width: 94%;">
									<ul>
									<!-- 配列の処理 -->
									<%for( int i = 0; i < AnswerList.length; i++){ %>
										<!-- nullや空文字の処理 -->
								      <% if (AnswerList[i] != null && !"".equals(AnswerList[i])) { %>
								         <li class="edit_area flex_end edit_area_answer"><label class="label_long"><%= AnswerList[i] %></label></li>
								         <input type="hidden" name="answer" value="<%= AnswerList[i] %>">
								      <% } %>
								    <% } %>
									</ul>
								</li>							
							</ul>
						</li>
					</ul>
				</div>
				<div class="top_box">
					<button type="button" class="auto-right logout_button" onclick="Return()">戻る</button>
					<!-- エラーメッセージが確認された場合、登録ボタンを隠す -->
					<% if(ErrorMsgTextarea == null && ErrorMsgAnswer == null ) { %>
						<button type="submit" class="auto-right logout_button">登録</button>
					<% } %>
				</div>
			</form>
		</div>
	</div>
</body>
</html>