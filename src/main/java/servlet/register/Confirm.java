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
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
	    //formから値を取得
  		String textarea_edit = request.getParameter("textarea_edit");
  		String[] answer = request.getParameterValues("answer");
  		
  		//System.out.println(textarea_edit);
  		//System.out.println(answer);
  		
  		//文字数チェック テキストエリア
  		//エラーメッセージ
  		String inputerror = null;
  		if(textarea_edit.length() < 5) {
  			request.setAttribute("textAreaEdit", textarea_edit);
  		} else {
  			inputerror = "textarea_edit_error";
  			//入力画面へ遷移	    		   
  			response.sendRedirect("/java_training/register?inputerror=" + inputerror);
  			return;
  		}
  	    
  	    //文字数チェック 答え一覧
  	    for (String checkAnswer : answer) {
  	    	if(checkAnswer.length() < 200) {
  	    		//nullや空文字の影響の為java側で処理をせずパラメータとして設置する。jspで読む込む処理を設置する。
  	    		request.setAttribute("answerList", answer);
  	  	  	    
  	    		//残しておく
  	  	  	    //String[] answerAry = new String[answer.length];
  	  	  	    //for (int i = 0; i < answer.length; i++) {
  	  	  	    //	if (answer[i] != null && !"".equals(answer[i])) {
	  	  	  	//	  answerAry[i] = answer[i];
		  	  	//    }
  	  	  	    //}
  	  	  	    //request.setAttribute("answerList", answerAry);
  	    	} else {
  	    		inputerror = "answer_error";
  	  			//入力画面へ遷移	    		   
  	    		response.sendRedirect("/java_training/register?inputerror=" + inputerror);
  	  			return;
  	    	}
  	    }

  	    //エラー文章 宣言。
  	    String errorMsgTextarea = null;
  	    String errorMsgAnswer = null;
  	    
  	    //文字数未入力 問題エラーチェック
  	    if (textarea_edit == null || "".equals(textarea_edit)) {
  	    	errorMsgTextarea = "問題が未入力です。";
  		   
  		   //問題のエラーのパラメータを設置する。jspで読み込む処理を設定する。
  	  	   request.setAttribute("errorMsgTextarea", errorMsgTextarea);
  		}
  	    
  	    
  	    //文字数未入力 答えエラーチェック
	  	for( int i = 0; i < answer.length; i++){
	  		//答え欄3つあるが1つだけ入力された状態等の空きinputが送られてくる場合、
	  		//空きinputをここで排除する。全部空きinputだったらfor文から抜けてエラー文が実行される。
	  		//equalsの判定にする事で文字の判定に抜けが無いようにする。
	  		if(!answer[i].equals("")) {
	  			System.out.println(answer[i]);
	  			
	  			//答えが入力を確認出来たら実行する。
	  			RequestDispatcher dispatcher = request.getRequestDispatcher("/register/confirm.jsp");
	  			dispatcher.forward(request, response);
	  			//答えが入力を確認出来たらretunで止める。
	  			return;
	  		}
	  	}
	  	
	  	//答えのfor文から未入力の値が来た場合に実行する。
	  	//答えエラーチェックで文字入力が確認されていればjspに移動させる為こちらが実行される事はない。
	  	errorMsgAnswer = "答えが未入力です。";
	  	
	  	//答えのエラーをパラメータを設置する。
	  	request.setAttribute("errorMsgAnswer", errorMsgAnswer);
	    
		//	JSP読み込み	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/register/confirm.jsp");
		
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
