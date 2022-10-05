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
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
	    
	    //削除実行ボタンからの値取得
	    int execute_delete = Integer.parseInt(request.getParameter("execute_delete"));
	    
	    
	    try {
	    	//問題一覧取得
			QuestionsDao dao = new QuestionsDao();
	    	
			//回答一覧取得
	    	CorrectAnswersDao dao_answer = new CorrectAnswersDao();
	    	//ArrayList<CorrectAnswersBean> answerBean = (ArrayList<CorrectAnswersBean>) dao_answer.findByQuetionsId(delete_id);
	    	
	    	//削除実行ボタンからの値取得出来ているか確認
	    	//System.out.println(execute_delete);
	    	
	    	//削除実行 ※レコードの取得の失敗で実行されない。
	    	dao.delete(execute_delete);
	    	dao_answer.deleteAnswer(execute_delete);
	    	
	    	
	    } catch (Exception e) {
			e.printStackTrace();

		}
	
		//	JSP読み込み
	    response.sendRedirect("./list");
	   
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
