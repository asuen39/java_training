<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.QuestionsBean" %>
<%@ page import="Dao.CorrectAnswersBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
<!-- JS読み込み -->
<script type="text/javascript" src="./js/list.js"></script>

<!-- 問題一覧のリクエストデータの取得 -->
<% ArrayList<QuestionsBean> Questionslist = (ArrayList<QuestionsBean>) request.getAttribute("questionList"); %>

<!-- 回答データ一覧のリクエスト取得 -->
<% ArrayList<CorrectAnswersBean> AnswerList = (ArrayList<CorrectAnswersBean>) request.getAttribute("answerList"); %>

	<div class="global_area">
		<div class="top_area">
			<div class="top_box">
				<button type="button" class="auto-right logout_button" onclick="Top()">Top</button>
				<button type="button" class="auto-right logout_button" onclick="Logout()">logout</button>
			</div>
			<div class="center_auto">
				<button type="button" class="logout_button" onclick="NewQuestion()">新規登録</button>
			</div>
			<div class="main_area">
				<!-- 取得してきた問題一覧をfor文で一つずつ取り出す  -->
				 <%for(int i = 0; i < Questionslist.size(); i++){%>
				 	<!-- questionsBeanを定義して一つずつ取り出されたQuestionslistを代入する。  -->
				    <%QuestionsBean questionsBean = Questionslist.get(i);%>
				    
				    <ul>
				    	<!-- li配下の問題番号欄にquestionsBean.getId()から値を設置する。 -->
						<li class="list_area">問題:<span class="toi_number"><%=questionsBean.getId()%></span>
							<!-- 問題テキスト欄にquestionsBean.getQuestion()で文字列を設置する。 -->
							<label class="list_label"><%=questionsBean.getQuestion()%></label>
							
							<!-- 編集画面に問題番号をurlパラメータとしてsubmitで送る。 -->
							<form action="./edit?edit_id=<%=questionsBean.getId()%>" method="post">
								<!-- 編集画面に問題番号をsubmitで送る。valueにidを設置 -->
								<button type="submit" name="edit_id" value="<%=questionsBean.getId()%>" >編集</button>
							</form>
							
							<form action="./delete_confirm" method="post">
								<!-- 削除画面に問題番号をsubmitで送る。valueにidを設置 -->
								<button type="submit" name="delete_id" value="<%=questionsBean.getId()%>">削除</button>
							</form>
							
						</li>
						<!-- int cntを定義する。カウント用の変数を用意する。 -->
						<!-- レコードに問題のid0が無いので1からセットする。 -->
						<% int cnt = 1; %>
						
						<!-- 取得してきた回答一覧をfor文で一つずつ取り出す -->
						<%for(int a = 0; a < AnswerList.size(); a++){%>
							<!-- ul_AnswerListを定義して一つずつ取り出されたAnswerListを代入する。  -->
							<%CorrectAnswersBean ul_AnswerList = AnswerList.get(a);%>
							
							<!-- レコードから取得してきたidと1件ずつ取り出されたul_AnswerListのid値を比較する。 等しい場合は通す -->
							<%if(questionsBean.getId() == ul_AnswerList.getQuestionId() ) { %>
								<li class="list_area flex_start mt10">
									
									<!-- ループでカウントした数を設置する。 -->
									答え: <%= cnt %>
									
									<!-- ループで合致したidの答え一覧を設置する。 -->
									<label class="list_label ml25"><%=ul_AnswerList.getAnswer()%></label>
								</li>
								<!-- カウントを1増やす親元のfor文に戻る。 -->
								<% cnt++; %>
								
							<% } %>
						<% } %>
					</ul>
				<% } %>
			
			</div>
		</div>
	</div>
</body>
</html>