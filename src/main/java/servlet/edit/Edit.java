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
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
	    
	    //list 削除ボタンからの値を取得
	    int edit_id = Integer.parseInt(request.getParameter("edit_id"));
	    //System.out.println(edit_id);
	    try {
	    	//ID取得させてる関係で他のページのような扱いをすると500エラーになる。
	    	//常に問題の番号を保持させないといけない。
	    	
	    	//問題番号のIDを設置。
	    	request.setAttribute("editId", edit_id);
	    	
	    	//問題一覧取得
	    	QuestionsDao dao = new QuestionsDao();
	    	
	    	//削除のIDを問題一覧のIDと比較
	    	QuestionsBean questionsBean = dao.find(edit_id);
	    	
	    	//問題一覧設置
	    	request.setAttribute("questionList", questionsBean);
	    	
	    	//回答一覧取得
	    	CorrectAnswersDao dao_answer = new CorrectAnswersDao();
	    	ArrayList<CorrectAnswersBean> answerBean = (ArrayList<CorrectAnswersBean>) dao_answer.findByQuetionsId(edit_id);
	    	
	    	
	    	//回答一覧設置
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
	    
		//	JSP読み込み	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit/edit.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
