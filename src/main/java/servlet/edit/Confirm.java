package servlet.edit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Top
 */
@WebServlet("/edit/confirm")
public class Confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Confirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
	    // edit/edit.jspからのform値を取得
	    String edit_id = request.getParameter("edit_id");
	    String textarea_update = request.getParameter("textarea_update");
	    String[] answer_update_id = request.getParameterValues("answer_update_id");
		String[] answer_update = request.getParameterValues("answer_update");
		
		//問題番号のidを設置する。
		request.setAttribute("editId", edit_id);
		
		//答えのidを設置する。
		request.setAttribute("answerUpdateId", answer_update_id);
		
		//文字数チェック テキストエリア
		//エラーメッセージ
		String inputerror = null;
		if(textarea_update.length() < 500) {
			request.setAttribute("textAreaUpdate", textarea_update);
		} else {
			inputerror = "textarea_update_error";
			//入力画面へ遷移 ※edit_idも送らないと500エラーになる為おくる。 
			//&をつける事で複数のパラメータをつける事ができる。
			response.sendRedirect("/java_training/edit?edit_id=" + edit_id + "&inputerror=" + inputerror);
			return;
		}
		
		//文字数チェック 答え一覧
		for (String checkAnswer : answer_update) {
			if(checkAnswer.length() < 200) {
				//nullや空文字の影響の為java側で処理をせずパラメータとして設置する。jspで読む込む処理を設置する。
				request.setAttribute("answerListUpdate", answer_update);
			} else {
				inputerror = "answer_update_error";
				//入力画面へ遷移	    		   
				response.sendRedirect("/java_training/edit?edit_id=" + edit_id + "&inputerror=" + inputerror);
				return;
			}
		}
		
	    
		//	JSP読み込み	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit/confirm.jsp");
		
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
