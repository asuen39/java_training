package servlet;

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
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
	     * delete.jspのform値を取得
	     *  @param int execute_delete: Integer.parseIntで数値を指定しrequest.getParameterでexecute_deleteを取得します。
	     */
	    int execute_delete = Integer.parseInt(request.getParameter("execute_delete"));
	    
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
	    	 * 削除実行 ※レコードの取得失敗時にはで実行されない。
	    	 * dao.deleteに引数execute_deleteを設置して実行する。
	    	 */
	    	dao.delete(execute_delete);
	    	/**
	    	 * dao_answer.deleteAnswerに引数execute_deleteを設置して実行する。
	    	 */
	    	dao_answer.deleteAnswer(execute_delete);
	    	
	    	
	    } catch (Exception e) {
			e.printStackTrace();

		}
	
	    /**
		 * リダイレクトを実行する。
		 * url: ./listでセットする。
		 * response.sendRedirectを使用する。
		 */
	    response.sendRedirect("./list");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		/**
		 * 文字コードの指定
		 * request設定後にsetCharacterEncoding設置
		 * 文字コード: utf-8
		 */
	    request.setCharacterEncoding("utf-8");
	    
	    //doGetに対してリクエストを実行する。
		doGet(request, response);
	}

}
