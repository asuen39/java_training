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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの指定
	    request.setCharacterEncoding("utf-8");
	    
		//formから値を取得
		String login_id = request.getParameter("login_id");
		String login_pw = request.getParameter("login_pw");
		//コンソールに結果表示		
	    System.out.println(login_id);
	    System.out.println(login_pw);
	    
	    
	    try {
	    	UsersDao dao = new UsersDao();
	    	ArrayList<UsersBean> list = (ArrayList<UsersBean>) dao.findAll();
	    	
	    	//データベースから取得されたレコードを1件ずつループする。
	    	for (UsersBean bean : list) {
	    		//入力値とレコードの値を比較する。
	    		String loginMsg = null;
	    		if (bean.getId() == Integer.parseInt(login_id) && login_pw.equals(bean.getPassword())) {
	    		   loginMsg = "ログイン成功";
	    		   
	    		   //TOP画面へ遷移	    		   
	    		   response.sendRedirect("./top");
	    		   return;
	    		   
	    		} else {
	    		   loginMsg = "ログイン失敗";
	    		}
	    		request.setAttribute("loginMsg", loginMsg);
    		}
	    } catch (Exception e) {
			e.printStackTrace();

		}
	    
	    
	    //入力データの    
	    request.setAttribute("userName", login_id);
		
		
		doGet(request, response);
	}

}
