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
@WebServlet("/list")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
	    //・例外処理の為try-catch文を使用する。
	    try {
	    	
	    	//・問題一覧取得
	    	//1.QuestionsDaoの型でdaoを宣言。
	    	//2.newを使用してQuestionsDaoをインスタンス化させる。
			QuestionsDao dao = new QuestionsDao();
			
			//・dao.findAllを実行する。
	    	//1.ArrayList<QuestionsBean>の型にlistをセットする。
	    	//2.listにdao.findAllの結果を格納する。
	    	ArrayList<QuestionsBean> list = (ArrayList<QuestionsBean>) dao.findAll();
	    	
	    	//・回答一覧取得
	    	//1.CorrectAnswersDaoの型にdao_answerをセットする。
	    	//2.newを使用してCorrectAnswersDaoをインスタンス化させる。
	    	CorrectAnswersDao dao_answer = new CorrectAnswersDao();
	    	
	    	//dao_answer.findAllを実行する。
	    	//1.ArrayList<CorrectAnswersBean>の型にlist_answerをセットする。
	    	//2.list_answerにdao_answer.findAllの結果を格納する。
	    	ArrayList<CorrectAnswersBean> list_answer = (ArrayList<CorrectAnswersBean>) dao_answer.findAll();
	    	
	    	//リクエストに対してquestionListにlistを格納してセットする。
	    	request.setAttribute("questionList", list);
	    	
	    	//リクエストに対してanswerListにlist_answerを格納してセットする。
	    	request.setAttribute("answerList", list_answer);
	    	
	    	
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		//list.jsqpをリクエスト	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
		//リクエストを実行
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
	  //doGetに対してリクエストを実行する。
		doGet(request, response);
	}

}
