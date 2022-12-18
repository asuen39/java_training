package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // doGetとは？ サーバー側に繋げた時にデータの要求がある場合に呼び出す
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// loginjsからパラメータで渡された値を変数してnumに格納する。
		// 一応Ajaxも利用している。
        String num = request.getParameter("num");
        
        //例外処理の為try-catch文を使用する。
        try {
        	//・UsersDaoの型でdaoを宣言。
	    	//1.new UsersDaoを定義する事でインスタンス化している。
	    	UsersDao dao = new UsersDao();
	    	
	    	//dao.findAllを実行する。
	    	//ArrayList<UsersBean>の型にlistをセットする。
	    	//listにdao.findAllの結果を格納する。
	    	ArrayList<UsersBean> list = (ArrayList<UsersBean>) dao.findAll();
	    	
	    	//intの型でidMsgをnullで宣言する。
    		int idMsg = 0;
    		
	    	for (UsersBean bean : list) {
	    		
    			//レコードのgetIdをstringに変換する。numと比較する。
	    		//※理由:入力画面からのinputに対して数値以外が入力されても判定出来るようにする。
    			if(num.equals(Integer.valueOf(bean.getId()).toString()) ) {
	    			idMsg = 1;
	    			
	    		}
	    	}
	    	
	    	PrintWriter out = response.getWriter();
			out.print(idMsg);
			
        } catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// doPostとは？ サーバー側に繋げた時にデータが送られてくる場合に呼び出す
	// formでmethod="post"を指定している為dopostで受け取る。
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの指定
		request.setCharacterEncoding("utf-8");
	}

}
