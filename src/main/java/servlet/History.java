package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.HistoriesBean;
import Dao.HistoriesDao;
import Dao.UsersBean;
import Dao.UsersDao;

/**
 * Servlet implementation class Top
 */
@WebServlet("/history")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public History() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの指定
		//request設定後にsetCharacterEncoding設置
		//文字コード: utf-8
	    request.setCharacterEncoding("utf-8");
	    
	    //login_idを定義する。
	    String login_id = "";
	    
	    //セッションの作成
	    HttpSession session = request.getSession();
	    
	    //login_idにsessionと引数Login_Idを格納。
	    login_id = (String) session.getAttribute("Login_Id");
	    
	    //例外処理の為try-catch文を使用する。
	    try {
	    	//・UsersDaoの型でdaoUsersを宣言。
	    	//1.new UsersDaoを定義する事でインスタンス化している。
	    	UsersDao daoUsers = new UsersDao();
	    	
	    	//login_id取得の判定処理を設置する。
	    	if( login_id != null ) {
	    		//dao.findを実行する。引数にはlogin_idをセットする。
		    	//UsersBeanの型にloginUserをセットする。
		    	//loginUserにdao.findの結果を格納する。
		    	UsersBean loginUser = daoUsers.find(login_id);
		    	
		    	//リクエストに対してhistoriesLoginUserにloginUserを格納してセットする。
		    	request.setAttribute("historiesLoginUser", loginUser);
		    	
	    	} else {
	    		String loginIderror = "login_Id_error";
				
	    		//login_idを取得出来ない場合、リダイレクトの設定をする。
	    		response.sendRedirect("/java_training/top?" + loginIderror);
	    		
	    		//結果を返しこの条件文での処理を止める。
				return;
	    	}
	    	
	    	//回答履歴一覧取得
	    	//1.HistoriesDaoの型でdaoを宣言。
	    	//2.newを使用してHistoriesDaoをインスタンス化させる。
	    	HistoriesDao dao = new HistoriesDao();
			
			//dao.findAllを実行する。引数にはInteger.parseInt(login_id)をセットする。
	    	//1.ArrayList<HistoriesBean>の型にlistをセットする。
	    	//2.listにdao.findAllの結果を格納する。
	    	ArrayList<HistoriesBean> list = (ArrayList<HistoriesBean>) dao.findAll(Integer.parseInt(login_id));
	    	
	    	//リクエストに対してhistoriesListにlistを格納してセットする。
	    	request.setAttribute("historiesList", list);
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	
	    }
	    
		// history.jspをリクエスト
	    //RequestDispatcherを型にdispatcherに代入する。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/history.jsp");
		
		//リクエストを実行する。
		//dispatcher.forwardを使用する。
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
