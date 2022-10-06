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
<% String TextAreaUpdate = (String)request.getAttribute("textAreaUpdate"); %>
<% String[] AnswerListUpdate = (String[])request.getAttribute("answerListUpdate"); %>

<input type="hidden" class="questionId" value="<%= EditId %>">
	<div class="global_area">
		<div class="top_area">
			<div class="top_box">
				<button type="button" class="auto-right logout_button" onclick="Top()">Top</button>
				<button type="button" class="auto-right logout_button" onclick="Logout()">logout</button>
			</div>
			<form action="./update" method="post" class="top_area" style="width:100%;">
				<div class="main_area">
					<ul>
						<li class="edit_area">問題番号:<span class="toi_number"><%= EditId %></span><input type="hidden" name="toi_number_up" value="<%= EditId %>"></li>
						<li class="edit_area flex_end">問題: 
							<label class="label_textarea"><%= TextAreaUpdate %></label>
							<input type="hidden" name="textarea_edit" value="<%= TextAreaUpdate %>">
						</li>
						<li class="edit_area flex_end">
							<ul class="edit_area flex_end" style="width: 96%;">
								<li class="edit_area">答え: </li>
								<li style="width: 94%;">
									<ul>
									<!-- 配列の処理 -->
									<%for( int i = 0; i < AnswerListUpdate.length; i++){ %>
										<!-- nullや空文字の処理 -->
								      <% if (AnswerListUpdate[i] != null && !"".equals(AnswerListUpdate[i])) { %>
								         <li class="edit_area flex_end edit_area_answer"><label class="label_long"><%= AnswerListUpdate[i] %></label></li>
								         <input type="hidden" name="answer" value="<%= AnswerListUpdate[i] %>">
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
					<button type="submit" class="auto-right logout_button">更新</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>