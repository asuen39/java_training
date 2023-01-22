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
@WebServlet("/register/confirm")
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
	     * confirm.jspのform値を取得
	     * @param String textarea_edit: request.getParameterでtextarea_editを取得します。
	     * @param String[] answer: request.getParameterValuesでanswerを取得します。
	     */
  		String textarea_edit = request.getParameter("textarea_edit");
  		String[] answer = request.getParameterValues("answer");
  		
  		/**
		 * テキストエリアの文字数チェックを行う。
		 * エラーメッセージ
		 * @param String inputerror: 初期値にnullを設置する。
		 */
  		String inputerror = null;
  		
  		/*
  		 * if文を使用する。
		 * textarea_updateをlengthメソッドで文字数を取得する。
		 * 500文字の数値と比較する条件文を作成する。
		 * */
  		if(textarea_edit.length() < 500) {
  			//リクエストに対してtextAreaEditにtextarea_editを格納してセットする。
			//request.setAttributeを利用する。
  			request.setAttribute("textAreaEdit", textarea_edit);
  		} else {
  			//textarea_edit_errorをinputerrorに代入する。
  			inputerror = "textarea_edit_error";
  			
  			//リダイレクトの設置をする。
			//url: /java_training/register?inputerror=" + inputerror	    		   
  			response.sendRedirect("/java_training/register?inputerror=" + inputerror);
  			
  			//結果を返しこの条件文での処理を止める。
  			return;
  		}
  	    
  		/**
		 * 答え一覧の文字数チェックを行う。
		 * for文をループさせて一つずつ取り出す。
		 * for文を使用する。
		 * @param String checkAnswer: 定義する
		 * answerでfor文の条件文を作成する。
		 */
  	    for (String checkAnswer : answer) {
  	    	//checkAnswerをlengthメソッドで文字数を取得する。。
  	    	//200文字の数値と比較する条件文を作成する。
  	    	if(checkAnswer.length() < 200) {
  	    		//リクエストに対してanswerListにanswerを格納してセットする。
  	    		//request.setAttributeを利用する。
  	    		request.setAttribute("answerList", answer);
  	    	} else {
  	    		//answer_errorをinputerrorに代入する。
  	    		inputerror = "answer_error";
  	    		
  	    		//リダイレクトの設置をする。
  	    		//url: /java_training/register?inputerror=" + inputerror   		   
  	    		response.sendRedirect("/java_training/register?inputerror=" + inputerror);
  	    		
  	    		//結果を返しこの条件文での処理を止める。
  	  			return;
  	    	}
  	    }
  	    
  	    /**
  	     *エラー文章
  	     *@param String errorMsgTextarea: 初期値をnullにする。
  	     *@param String errorMsgAnswer: 初期値をnullにする。
  	     */
  	    String errorMsgTextarea = null;
  	    String errorMsgAnswer = null;
  	    
  	    /**
  	     * 文字数未入力 問題エラーチェック
  	     * if文を作成する。
  	     * textarea_editをnullか判定
  	     * textarea_editが空文字か判定
  	     * どちらかが通るかの条件文を作成する。
  	     */
  	    if (textarea_edit == null || "".equals(textarea_edit)) {
  	    	
  	    	//errorMsgTextareaに文字列を代入する。
  	    	errorMsgTextarea = "問題が未入力です。";
  	    	
  	    	//リクエストに対してerrorMsgTextareaにerrorMsgTextareaを格納してセットする。
  	    	//request.setAttributeを利用する。
  	  	   request.setAttribute("errorMsgTextarea", errorMsgTextarea);
  		}
  	    
  	    /**
  	     * 文字数未入力 答えエラーチェック
  	     * for文を使用する。
  	     * @parsn int i: 初期値を0にする。
  	     * answerをlengthメソッドで文字数を取得する。
  	     * iとanswerで条件文を作成する。
  	     */
	  	for( int i = 0; i < answer.length; i++){
	  		
	  		//if文を使用する。
	  		//answerをequalsで空文字と比較する。
	  		if(!answer[i].equals("")) {
	  			
	  			// /register/confirm.jspをリクエスト
	  			//RequestDispatcherを型にdispatcherに代入する。
	  			RequestDispatcher dispatcher = request.getRequestDispatcher("/register/confirm.jsp");
	  			
	  			//リクエストを実行する。
	  			//dispatcher.forwardを使用する。
	  			dispatcher.forward(request, response);
	  			
	  			//結果を返しこの条件文での処理を止める。
	  			return;
	  		}
	  	}
	  	
	  	/**
	  	 *  答えのfor文から未入力の値が来た場合に実行する。
	  	 *  答えエラーチェックで文字入力が確認されていればjspに移動させる為こちらが実行される事はない。
	  	 *  errorMsgAnswerにエラー文章を代入する。
	  	 */
	  	errorMsgAnswer = "答えが未入力です。";
	  	
	  	/**
	  	 * リクエストに対してerrorMsgAnswerにerrorMsgAnswerを格納してセットする。
	  	 * request.setAttributeを利用する。
	  	 */
	  	request.setAttribute("errorMsgAnswer", errorMsgAnswer);
	    
		/**
		 * /register/confirm.jspをリクエスト
		 * RequestDispatcherを型にdispatcherに代入する。
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/register/confirm.jsp");
		
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
