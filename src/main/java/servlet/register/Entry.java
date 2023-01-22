package servlet.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CorrectAnswersDao;
import Dao.QuestionsDao;

/**
 * Servlet implementation class Top
 */
@WebServlet("/register/entry")
public class Entry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Entry() {
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
	     * entry.jspのform値を取得
	     * @param String textarea_edit: request.getParameterでtextarea_editを取得します。
	     * @param String[] answer: request.getParameterValuesでanswerを取得します。
	     */
  		String textarea_edit = request.getParameter("textarea_edit");
  		String[] answer = request.getParameterValues("answer");
  		
  		//例外処理の為try-catch文を使用する。
  		try {
  			/**
	    	 * 問題一覧取得
	    	 * QuestionsDaoの型でdaoを宣言。
	    	 * newを使用してQuestionsDaoをインスタンス化させる。
	    	 */
			QuestionsDao dao = new QuestionsDao();
	    	
			/**
			 * 回答一覧取得
			 * CorrectAnswersDaoの型でdao_answerを宣言。
			 * newを使用してCorrectAnswersDaoをインスタンス化させる。
			 */
	    	CorrectAnswersDao dao_answer = new CorrectAnswersDao();
	    	
	    	/**
	    	 * dao.entryに引数textarea_editを設置して実行する。
	    	 */
	    	dao.entry(textarea_edit);
	    	
	    	/**
	    	 * dao.getQuestionId()を実行する。
	    	 * @param int questions_id: dao.getQuestionIdの結果を格納する。
	    	 */
	    	int questions_id = dao.getQuestionId();
	    	
	    	/**
	    	 * for文を使用する。
	    	 * @param String Answer: 定義する 
	    	 * answerでfor文の条件文を作成する。
	    	 */
	    	for (String Answer : answer) {
	    		//String answer1を定義しAnswerを代入する。
	    		String answer1 = Answer;
	    		
	    		//dao_answer.entryAnswerを実行する。引数にquestions_id、answer1をセットする。
	    		dao_answer.entryAnswer(questions_id, answer1);
	    	}
	    } catch (Exception e) {
			e.printStackTrace();

		}
	    
		/**
		 * リダイレクトを実行する。
		 * url: ../listでセットする。
		 * response.sendRedirectを使用する。
		 */
		response.sendRedirect("../list");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGetに対してリクエストを実行する。
		doGet(request, response);
	}

}
