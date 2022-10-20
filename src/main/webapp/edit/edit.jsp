<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.QuestionsBean" %>
<%@ page import="Dao.CorrectAnswersBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
<!-- JS読み込み -->
<script type="text/javascript" src="./js/all.js"></script>
<script type="text/javascript" src="./js/edit.js"></script>
<!-- 問題番号のIDをリクエスト -->
<% int EditId = (int)request.getAttribute("editId"); %>

<!-- 問題一覧のリクエストデータの取得 -->
<%QuestionsBean questionsBean = (QuestionsBean)request.getAttribute("questionList"); %>

<!-- 回答データ一覧のリクエスト取得 -->
<% ArrayList<CorrectAnswersBean> answerBean = (ArrayList<CorrectAnswersBean>) request.getAttribute("answerList"); %>

<!-- エラー文章リクエスト -->
<% String ErrorTextArea = (String)request.getAttribute("error_Textarea"); %>
<% String ErrorAnswer = (String)request.getAttribute("error_Answer"); %>
	<div class="global_area">
		<div class="top_area">
			<div class="top_box">
				<button type="button" class="auto-right logout_button" onclick="Top()">Top</button>
				<button type="button" class="auto-right logout_button" onclick="Logout()">logout</button>
			</div>
			<form action="./edit/confirm" method="post" class="top_area">
				<div class="main_area">
					<ul>
						<li class="edit_area">問題番号:
							<!-- li配下の問題番号欄にquestionsBean.getId()から値を設置する。 -->
							<span class="toi_number"><%= EditId %></span>
						</li>
						<!-- confirmに問題番号のidを送る。hiddenタイプのinput valueを設置する。valueには EditIdを設置する。 -->
						<input type="hidden" name="edit_id" value="<%= EditId %>">
						<li class="edit_area">問題: 
							<!-- 問題テキスト欄にquestionsBean.getQuestion()で文字列を設置する。 -->
							<textarea name="textarea_update" rows="6" cols="85"><%=questionsBean.getQuestion()%></textarea>
						</li>
						<li class="edit_area">
							<ul class="edit_area" style="width: 100%;">
								<li class="edit_area">答え: </li>
								<li style="width: 94%;">
									<ul id="ul_list">
									<!-- 取得してきた回答一覧をfor文で一つずつ取り出す  -->
									<% for(int a = 0; a < answerBean.size(); a++){ %>
										<!-- ul_answerBeanを定義して一つずつ取り出されたanswerBeanを代入する。  -->
										 <% CorrectAnswersBean ul_answerBean = answerBean.get(a); %>
										<li class="edit_area">
											<!-- ループから選出されたidを答え一覧の文字列をを設置する。 -->
											<input type="text" name="answer_update" style="width: 92%;" value="<%= ul_answerBean.getAnswer() %>">
											<button type="button" onclick="delete_btn(this)">削除</button>
											<!-- confirmに回答一覧の文字列を配列で送る。hiddenタイプのinput valueを設置する。valueには ul_answerBean.getId()を設置する。 -->
											<input type="hidden" name="answer_update_id" value="<%= ul_answerBean.getId() %>">
										</li>
									<% } %>
									</ul>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="top_box">
					<button type="button" class="auto-right logout_button" onclick="Return()">戻る</button>
					<button type="submit" class="auto-right logout_button">確認</button>
					<button type="button" id="addition_btn" class="auto-right logout_button">追加</button>
				</div>
			</form>
			<!-- 追加ボタン押し時にli要素をクローンする為に  -->
			<ul class="close_area">
				<li class="edit_area addition_content">
					<input type="text" name="answer_update" style="width: 92%;">
					<button type="button" onclick="delete_btn(this)">削除</button>
					<input type="hidden" name="answer_update_id" value="">
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