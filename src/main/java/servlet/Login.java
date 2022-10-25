package servlet;



import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UsersBean;
import Dao.UsersDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // doGetとは？ サーバー側に繋げた時にデータの要求がある場合に呼び出す
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//システムのスタート地点
		//login.jspを読み込む宣言をする。
		// ※loginというページにアクセスされた時に実行される。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		
		//login.jsp読み込みを実行する。
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// doPostとは？ サーバー側に繋げた時にデータが送られてくる場合に呼び出す
	// formでmethod="post"を指定している為dopostで受け取る。
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
		//・formからの値を取得
	    //1.stringで取得するようにする。取得はrequest.getParameterを使用する。
		String login_id = request.getParameter("login_id");
		String login_pw = request.getParameter("login_pw");
		//コンソールに結果表示		
	    //System.out.println(login_id);
	    //System.out.println(login_pw);
	    
		//・例外処理の為try-catch文を使用する。
		//※理由: javaでは例外が発生しうる箇所で何かしらの対処をしないとコンパイルエラーになる。
		//※例外処理に対する処理をしないとエラーが発生する。
	    try {
	    	
	    	//・UsersDaoの型でdaoを宣言。
	    	//1.new UsersDaoを定義する事でインスタンス化している。
	    	UsersDao dao = new UsersDao();
	    	
	    	//dao.findAllを実行する。
	    	//ArrayList<UsersBean>の型にlistをセットする。
	    	//listにdao.findAllの結果を格納する。
	    	ArrayList<UsersBean> list = (ArrayList<UsersBean>) dao.findAll();
	    	
	    	//インスタンス化させたUsersBean beanを使用している。
	    	//データベースから取得されたレコードを1件ずつループする。
	    	for (UsersBean bean : list) {
	    		
	    		//Stringの型でloginMsgをnullで宣言する。
	    		String loginMsg = null;
	    		
	    		//入力値とレコードの値を比較する。
	    		//1.レコードidと入力値のidは等しい。レコードの文字列パスワードと入力値の文字列パスワードは等しい。
	    		if (bean.getId() == Integer.parseInt(login_id) && login_pw.equals(bean.getPassword())) {
	    			
	    			//ログイン成功時のメッセージをセット
	    		   loginMsg = "ログイン成功";
	    		   
	    		   //TOP画面へ遷移	    		   
	    		   response.sendRedirect("./top");
	    		   
	    		   //returnで処理を止める。
	    		   return;
	    		   
	    		} else {
	    			
	    			//ログイン失敗時のメッセージをセット
	    		   loginMsg = "ログイン失敗";
	    		}
	    		
	    		//if文から抜け出て来たログイン情報をセットする。
	    		request.setAttribute("loginMsg", loginMsg);
    		}
	    } catch (Exception e) {
			e.printStackTrace();

		}
	    
	    //login_idの設置。
	    //※login.jspで現在は使用していない。
	    request.setAttribute("userName", login_id);
		
		//doGetに対してリクエストを実行する。
		doGet(request, response);
	}

}
