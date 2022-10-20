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
		/**
		 * 文字コードの指定
		 * request設定後にsetCharacterEncoding設置
		 * 文字コード: utf-8
		 */
	    request.setCharacterEncoding("utf-8");
	    
	    /**
	     * edit/edit.jspからのform値を取得
	     * @param String edit_id: request.getParameterでedit_idを取得します。
	     * @param String textarea_update: request.getParameterでtextarea_updateを取得します。
	     * @param String[] answer_update_id: request.getParameterValuesでanswer_update_idを取得します。
	     * @param String[] answer_update: request.getParameterValuesでanswer_updateを取得します。
	     * 
	     */
	    String edit_id = request.getParameter("edit_id");
	    String textarea_update = request.getParameter("textarea_update");
	    String[] answer_update_id = request.getParameterValues("answer_update_id");
		String[] answer_update = request.getParameterValues("answer_update");
		
		/*
		 * 問題番号のidを設置する。
		 * リクエストに対してeditIdにedit_idを格納してセットする。
		 * request.setAttributeを利用する。
		 * */
		request.setAttribute("editId", edit_id);
		
		/*
		 * 答えのidを設置する。
		 * リクエストに対してanswerUpdateIdにanswer_update_idを格納してセットする。
		 * request.setAttributeを利用する。
		 * */
		request.setAttribute("answerUpdateId", answer_update_id);
		
		/*
		 * テキストエリアの文字数チェックを行う。
		 * エラーメッセージ
		 * @param String inputerror: 初期値にnullを設置する。
		 * */
		String inputerror = null;
		
		/*
		 * textarea_updateをlengthで数値に変換する。
		 * 500文字の数値と比較する条件文を作成する。
		 * */
		if(textarea_update.length() < 500) {
			//リクエストに対してtextAreaUpdateにtextarea_updateを格納してセットする。
			//request.setAttributeを利用する。
			request.setAttribute("textAreaUpdate", textarea_update);
		} else {
			//textarea_update_errorをinputerrorに代入する。
			inputerror = "textarea_update_error";
			
			//リダイレクトの設置をする。※edit_idも送らないと500エラーになる為。 
			//※&をつける事で複数のパラメータをつける事ができる。
			//url: /java_training/edit?edit_id=" + edit_id + "&inputerror=" + inputerror
			response.sendRedirect("/java_training/edit?edit_id=" + edit_id + "&inputerror=" + inputerror);
			
			//結果を返しこの条件文での処理を止める。
			return;
		}
		
		/*
		 * 答え一覧の文字数チェックを行う。
		 * for文をループさせて一つずつ取り出す。
		 * @param String checkAnswer: 定義する
		 * answer_updateでfor文の条件文を作成する。
		 * */
		for (String checkAnswer : answer_update) {
			//checkAnswerをlengthで数値に変換する。
			//200文字の数値と比較する条件文を作成する。
			if(checkAnswer.length() < 200) {
				//リクエストに対してanswerListUpdateにanswer_updateを格納してセットする。
				//request.setAttributeを利用する。
				request.setAttribute("answerListUpdate", answer_update);
			} else {
				//answer_update_errorをinputerrorに代入する。
				inputerror = "answer_update_error";
				
				//リダイレクトの設置をする。
				//※&をつける事で複数のパラメータをつける事ができる。
				//url: /java_training/edit?edit_id=" + edit_id + "&inputerror=" + inputerror
				response.sendRedirect("/java_training/edit?edit_id=" + edit_id + "&inputerror=" + inputerror);
				
				//結果を返しこの条件文での処理を止める。
				return;
			}
		}
		
	    
		//  /edit/confirm.jspをリクエスト		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit/confirm.jsp");
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
