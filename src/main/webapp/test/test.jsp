<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.QuestionsBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
<script type="text/javascript" src="./js/all.js"></script>
<!-- 問題一覧のリクエストデータの取得 -->
<% ArrayList<QuestionsBean> Questionslist = (ArrayList<QuestionsBean>) request.getAttribute("questionList"); %>

	<div class="global_area">
		<div class="top_area">
			<div class="top_box">
				<button type="button" class="auto-right logout_button" onclick="Top()">Top</button>
				<button type="button" class="auto-right logout_button" onclick="Logout()">logout</button>
			</div>
			<form action="./test/result" method="post" style="width:100%;">
				<div class="main_area">
					
					<!-- 取得してきた問題一覧をfor文で一つずつ取り出す  -->
					<%for(int i = 0; i < Questionslist.size(); i++){%>
						<!-- questionsBeanを定義して一つずつ取り出されたQuestionslistを代入する。  -->
						<%QuestionsBean questionsBean = Questionslist.get(i);%>
						<ul>
							<li class="edit_area space_between">
							<!-- iが通過時に1プラスで増やす。番号とする-->
								<%= i + 1 %>
								
								<!-- 問題テキスト欄にquestionsBean.getQuestion()で文字列を設置する。 -->
								<label class="label_textarea"><%=questionsBean.getQuestion()%></label>
								
								<!--submitでquestionsBean.getIdを送る。hiddenタイプのinput valueを設置する。 -->
								<input type="hidden" name="test_id" value="<%= questionsBean.getId() %>">
							</li>
							<li class="edit_area space_between">回答
								<input type="text" name="test_answer" style="width:93%;">
							</li>
						</ul>
					<% } %>
				</div>
				<div class="saiten_box">
					<button type="submit" class="auto-right logout_button">採点</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>