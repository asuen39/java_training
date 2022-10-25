package servlet.register;

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
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
	     * confirm.jspからエラー値を取得
	     * @param String textarea_error: textarea_edit_errorでエラー変数を取得する。
	     * @param String answer_error: answer_errorでエラー変数を取得する。
	     * @param String inputerror: request.getParameterでinputerrorを取得します。
	     */
	    String textarea_error = "textarea_edit_error";
	    String answer_error = "answer_error";
  		String inputerror = request.getParameter("inputerror");
  		
  		/**
  		 * if文を作成する。
  		 * inputerrorの値がnullかどうか判定処理を作成する。
  		 */
  		if(inputerror != null) {
  			//エラー変数が同じか判定する。
  			//if文を使用する。
  			//inputerrorにequalsを使用する。
  			if( inputerror.equals(textarea_error)) {
  				//テキストエリアのエラー時
  				//error_textareaを定義する。初期値はエラー時の文章。
  	  			String error_textarea = "問題の文字数が指定より多いです";
  	  			
  	  			//リクエストに対してerror_Textareaにerror_textareaを格納してセットする。
  	  			//request.setAttributeを利用する。
  	  			request.setAttribute("error_Textarea", error_textarea);
  	  			
  	  		} else if( inputerror.equals(answer_error)) {
  	  			//回答エリアのエラー時
  	  			//error_answerを定義する。初期値はエラー時の文章。
  	  			String error_answer = "答えの文字数が指定より多いです";
  	  			
  	  			//リクエストに対してerror_Answerにerror_answerを格納してセットする。
  	  			//request.setAttributeを利用する。
  	  			request.setAttribute("error_Answer", error_answer);
  	  		} else {
  	  			
  	  		}
  		}
  		/**
		 * /register/register.jspをリクエスト
		 * RequestDispatcherを型にdispatcherに代入する。
		 */	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/register/register.jsp");
		
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
		//doGetに対してリクエストを実行する。
		doGet(request, response);
	}

}
