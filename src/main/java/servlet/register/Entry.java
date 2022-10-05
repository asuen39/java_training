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
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
	    //formから値を取得
  		String textarea_edit = request.getParameter("textarea_edit");
  		String[] answer = request.getParameterValues("answer");
  	    
  	    //formから値を取得 レコード登録	
  		try {
	    	//問題一覧取得
			QuestionsDao dao = new QuestionsDao();
	    	
			//回答一覧取得
	    	CorrectAnswersDao dao_answer = new CorrectAnswersDao();
	    	
	    	//登録実行 textarea_editを登録する。
	    	dao.entry(textarea_edit);
	    	
	    	//QuestionsDao.javaから抽出したtextarea_editを宣言した変数に代入。
	    	int questions_id = dao.getQuestionId();
	    	
	    	for (String Answer : answer) {
	    		String answer1 = Answer;
	    		
	    		//抽出したtextarea_editのIDと分割したanswerの答え一覧を登録実行させる
	    		dao_answer.entryAnswer(questions_id, answer1);
	    	}
	    	
	    	
	    } catch (Exception e) {
			e.printStackTrace();

		}
	    
		//	listサーブレットを読み込み	
		response.sendRedirect("../list");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
