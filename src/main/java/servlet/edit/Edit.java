package servlet.edit;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CorrectAnswersBean;
import Dao.CorrectAnswersDao;
import Dao.QuestionsBean;
import Dao.QuestionsDao;

/**
 * Servlet implementation class Top
 */
@WebServlet("/edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 文字コードの指定
		 * request設定後にsetCharacterEncoding設置
		 * 文字コード: utf-8
		 */
	    request.setCharacterEncoding("utf-8");
	    
	    
	    /**
	     * 削除ボタンからの値を取得
	     * @param int edit_id
	     * 数値の取得
	     * 
	     */
	    //Integer.parseIntでedit_idを取得する。
	    int edit_id = Integer.parseInt(request.getParameter("edit_id"));
	
	    //例外処理の為try-catch文を使用する。
	    try {
	    	//※ID取得させてる関係で他のページのような扱いをすると500エラーになる。
	    	//※常に問題の番号を保持させないといけない。
	    	
	    	//問題番号のIDを設置。
	    	//リクエストに対してeditIdにedit_idを格納してセットする。
	    	request.setAttribute("editId", edit_id);
	    	
	    	/*
	    	 * 問題一覧取得
	    	 * */
	    	//1.QuestionsDaoの型でdaoを宣言。
	    	//2.newを使用してQuestionsDaoをインスタンス化させる。
	    	QuestionsDao dao = new QuestionsDao();
	    	
	    	//dao.findに引数edit_idを設置して実行する。
	    	//1.QuestionsBeanの型にquestionsBeanをセットする。
	    	//2.questionsBeanにdao.findの結果を格納する。
	    	QuestionsBean questionsBean = dao.find(edit_id);
	    	
	    	//リクエストに対してquestionListにquestionsBeanを格納してセットする。
	    	request.setAttribute("questionList", questionsBean);
	    	
	    	/*
	    	 * 回答一覧取得
	    	 * */
	    	//1.CorrectAnswersDaoの型でdao_answerを宣言。
	    	//2.newを使用してCorrectAnswersDaoをインスタンス化させる。
	    	CorrectAnswersDao dao_answer = new CorrectAnswersDao();
	    	
	    	//dao_answer.findByQuetionsIdに引数edit_idを設置して実行する。
	    	//1.ArrayList<CorrectAnswersBean>の型にanswerBeanをセットする。
	    	//2.answerBeanにdao_answer.findByQuetionsIdの結果を格納する。
	    	ArrayList<CorrectAnswersBean> answerBean = (ArrayList<CorrectAnswersBean>) dao_answer.findByQuetionsId(edit_id);
	    	
	    	//リクエストに対してanswerListにanswerBeanを格納してセットする。
	    	request.setAttribute("answerList", answerBean);
	    	
	    	
	    } catch (Exception e) {
			e.printStackTrace();
		}
	    
	    //confirmからエラー値を取得
	    String textarea_error = "textarea_update_error";
	    String answer_error = "answer_update_error";
	    String inputerror = request.getParameter("inputerror");
	    if(inputerror != null) {
			if( inputerror.equals(textarea_error)) {
				//エラー文設定
				String error_textarea = "問題の文字数が指定より多いです";
				request.setAttribute("error_Textarea", error_textarea);
				
			} else if( inputerror.equals(answer_error)) {
				//エラー文設定
				String error_answer = "答えの文字数が指定より多いです";
				request.setAttribute("error_Answer", error_answer);
			} else {
				
			}
		}
	    
		//  /edit/edit.jspをリクエスト	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit/edit.jsp");
		//リクエストを実行
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGetに対してリクエストを実行する。
		doGet(request, response);
	}

}
