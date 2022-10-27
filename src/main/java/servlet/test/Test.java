package servlet.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.QuestionsBean;
import Dao.QuestionsDao;

/**
 * Servlet implementation class Top
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの指定
		//request設定後にsetCharacterEncoding設置
		//文字コード: utf-8
	    request.setCharacterEncoding("utf-8");
	    
	    //例外処理の為try-catch文を使用する。
	    try {
	    	
	    	//問題一覧取得
	    	//1.QuestionsDaoの型でdaoを宣言。
	    	//2.newを使用してQuestionsDaoをインスタンス化させる。
			QuestionsDao dao = new QuestionsDao();
			
			//dao.findAllを実行する。
	    	//1.ArrayList<UsersBean>の型にlistをセットする。
	    	//2.listにdao.findAllの結果を格納する。
	    	ArrayList<QuestionsBean> list = (ArrayList<QuestionsBean>) dao.findAll();
	    	
	    	//listの配列をシャッフルする。
	    	//※Collectionsを型にshuffleメソッドを使用する。
	    	Collections.shuffle(list);
	    	
	    	//リクエストに対してquestionListにlistを格納してセットする。
	    	request.setAttribute("questionList", list);
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	
	    }
	    
		// /test/test.jspをリクエスト
	    //RequestDispatcherを型にdispatcherに代入する。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/test/test.jsp");
		
		//リクエストを実行する。
		//dispatcher.forwardを使用する。
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
