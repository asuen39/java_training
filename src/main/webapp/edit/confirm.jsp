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
<!-- テキストエリアをリクエスト -->
<% String TextAreaUpdate = (String)request.getAttribute("textAreaUpdate"); %>
<!-- 回答一覧の配列をリクエスト -->
<% String[] AnswerUpdateId = (String[])request.getAttribute("answerUpdateId"); %>
<!-- 回答一覧のidをリクエスト -->
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
						<li class="edit_area">問題番号:
							<!-- li配下の問題番号欄にEditIdの値を設置する。 -->
							<span class="toi_number"><%= EditId %></span>
							<!--submitでEditIdを送る。hiddenタイプのinput valueを設置する。 -->
							<input type="hidden" name="toi_number_up" value="<%= EditId %>">
						</li>
						<li class="edit_area flex_end">問題: 
							<!-- 問題テキスト欄にTextAreaUpdateを設置する。 -->
							<label class="label_textarea"><%= TextAreaUpdate %></label>
							<!--submitでTextAreaUpdateを送る。hiddenタイプのinput valueを設置する。 -->
							<input type="hidden" name="textarea_edit" value="<%= TextAreaUpdate %>">
						</li>
						<li class="edit_area flex_end">
							<ul class="edit_area flex_end" style="width: 96%;">
								<li class="edit_area">答え: </li>
								<li style="width: 94%;">
									<ul>
									<!-- 取得してきた回答一覧をfor文で一つずつ取り出す  -->
									<%for( int i = 0; i < AnswerListUpdate.length; i++){ %>
										<!-- 1つずつ取り出したAnswerListUpdateでnullチェックを行う -->
								      <% if (AnswerListUpdate[i] != null) { %>
								      	<!-- 条件文を通過したAnswerListUpdateの値を設置する。  -->
								         <li class="edit_area flex_end edit_area_answer"><label class="label_long"><%= AnswerListUpdate[i] %></label></li>
								         <!--submitでAnswerListUpdateを送る。hiddenタイプのinput valueを設置する。 -->
								         <input type="hidden" name="answer" value="<%= AnswerListUpdate[i] %>">
								      <% } %>
								    <% } %>
								    
								    <!-- 取得してきた回答一覧のidをfor文で一つずつ取り出す  -->
								    <%for( int i = 0; i < AnswerUpdateId.length; i++){ %>
										<!-- 1つずつ取り出したidでnullチェックを行う -->
								      <% if (AnswerUpdateId[i] != null) { %>
								      	 <!--submitでAnswerUpdateIdを送る。hiddenタイプのinput valueを設置する。 -->
								         <input type="hidden" name="answer_Id" value="<%= AnswerUpdateId[i] %>">
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