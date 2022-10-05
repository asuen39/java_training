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
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
	    //confirmからエラー値を取得
	    String textarea_error = "textarea_edit_error";
	    String answer_error = "answer_error";
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/register/register.jsp");
		
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
