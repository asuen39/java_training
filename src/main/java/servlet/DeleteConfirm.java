package servlet;

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
@WebServlet("/delete_confirm")
public class DeleteConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteConfirm() {
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
	     * list.jspからform値を取得
	     * @param int delete_id: Integer.parseIntで数値を指定しrequest.getParameterでdelete_idを取得します。
	     */
	    int delete_id = Integer.parseInt(request.getParameter("delete_id"));
	    
	    //例外処理の為try-catch文を使用する。
	    try {
	    	/**
	    	 * 問題一覧取得
	    	 * QuestionsDaoの型でdaoを宣言。
	    	 * newを使用してQuestionsDaoをインスタンス化させる。
	    	 * */
			QuestionsDao dao = new QuestionsDao();
	    	
			/**
	    	 * 削除のIDを問題一覧のIDと比較
	    	 * dao.findに引数delete_idを設置して実行する。
	    	 * QuestionsBeanの型にquestionsBeanをセットする。
	    	 * questionsBeanにdao.findの結果を格納する。
	    	 * */
	    	QuestionsBean questionsBean = dao.find(delete_id);
	    	
	    	
	    	/**
	    	 * リクエストに対してquestionListにquestionsBeanを格納してセットする。
	    	 * request.setAttributeを利用する。
	    	 */
	    	request.setAttribute("questionList", questionsBean);
	    	
	    	/**
			 * 回答一覧取得
			 * CorrectAnswersDaoの型でdao_answerを宣言。
			 * newを使用してCorrectAnswersDaoをインスタンス化させる。
			 */
	    	CorrectAnswersDao dao_answer = new CorrectAnswersDao();
	    	
	    	/**
	    	 * dao_answer.findByQuetionsIdに引数delete_idを設置して実行する。
	    	 * ArrayList ＜CorrectAnswersBean＞ の型にanswerBeanをセットする。
	    	 * answerBeanにdao_answer.findByQuetionsIdの結果を格納する。
	    	 */
	    	ArrayList<CorrectAnswersBean> answerBean = (ArrayList<CorrectAnswersBean>) dao_answer.findByQuetionsId(delete_id);
	    	
	    	/**
	    	 * リクエストに対してanswerListにanswerBeanを格納してセットする。
	    	 * request.setAttributeを利用する。
	    	 */
	    	request.setAttribute("answerList", answerBean);
	    	
	    	
	    	
	    } catch (Exception e) {
			e.printStackTrace();

		}
	
		/**
		 * /delete_confirm.jspをリクエスト
		 * RequestDispatcherを型にdispatcherに代入する。
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/delete_confirm.jsp");
		
		/**
		 * リクエストを実行する。
		 * dispatcher.forwardを使用する。
		 */
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
