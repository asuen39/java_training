package servlet.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CorrectAnswersBean;
import Dao.CorrectAnswersDao;
import Dao.UsersBean;
import Dao.UsersDao;

/**
 * Servlet implementation class Top
 */
@WebServlet("/test/result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
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
	    
	    //test.jspのform値を取得
	    //@param String[] test_id: request.getParameterでtest_idを取得します。
	    //@param String[] test_answer: request.getParameterでtest_answerを取得します。
	    String[] test_id = request.getParameterValues("test_id");
	    String[] test_answer = request.getParameterValues("test_answer");
	    
	    //login_idを定義する。
	    String login_id = "";
	    
	    //セッションの作成
	    HttpSession session = request.getSession();
	    
	    //login_idにsessionと引数Login_Idを格納。
	    login_id = (String) session.getAttribute("Login_Id");
	    
	    //例外処理の為try-catch文を使用する。
	    try {
	    	//・UsersDaoの型でdaoを宣言。
	    	//1.new UsersDaoを定義する事でインスタンス化している。
	    	UsersDao dao = new UsersDao();
	    	
	    	//dao.findを実行する。
	    	//UsersBeanの型にlogin_userをセットする。
	    	//login_userにdao.findの結果を格納する。
	    	UsersBean login_user = dao.find(login_id);
	    	
	    	//リクエストに対してloginUserにlogin_userを格納してセットする。
	    	request.setAttribute("loginUser", login_user);
	    	
	    	//int型でnumber_of_questionsを定義する。
	    	//test_idをlengthしてnumber_of_questionsに格納する。
	    	int number_of_questions = test_id.length;
	    	
	    	//リクエストに対してtestIdにsum_test_idを格納してセットする。
	    	request.setAttribute("testId", number_of_questions);
	    	
	    	//int型にcount_answerを定義する。カウント変数を用意する。
	    	int count_answer = 0;
	    	
	    	/**
	    	 * for文を使用する。
	    	 * @param int i: 定義する 
	    	 * test_idをlengthしてfor文の条件文を作成する。
	    	 */
	    	for(int i = 0; i < test_id.length; i++) {
	    		
	    		//回答一覧取得
	    		//CorrectAnswersDaoの型でdao_answerを宣言
	    		//newを使用してCorrectAnswersDaoをインスタンス化させる。
		    	CorrectAnswersDao dao_answer = new CorrectAnswersDao();
		    	
		    	//dao_answer.findByQuetionsIdに引数test_idを設置して実行する。
		    	//ArrayList ＜CorrectAnswersBean＞ の型にanswerBeanをセットする。
		    	//answerBeanにdao_answer.findByQuetionsIdの結果を格納する。
		    	ArrayList<CorrectAnswersBean> answerBean = (ArrayList<CorrectAnswersBean>) dao_answer.findByQuetionsId(Integer.parseInt(test_id[i]));
		    	
		    	/**
		    	 * for文を使用する。
		    	 * @param int a: 定義する 
		    	 * answerBeanをsizeしてfor文の条件文を作成する。
		    	 */
		    	for(int a = 0; a < answerBean.size(); a++) {
		    		
		    		//CorrectAnswersBeanを型にrecode_listを定義する。
		    		//answerBean.get(a)で結果をrecode_listに格納する。
		    		CorrectAnswersBean recode_list = answerBean.get(a);
		    		
		    		//入力画面から送られてきたtest_answerを使用する。
		    		//test_answerにiを使用する
		    		//equalsでrecode_list.getAnswerと比較する。
		    		if(test_answer[i].equals(recode_list.getAnswer()) ) {
		    			//入力画面とレコードの回答が合っていたらカウント変数に1増やす。
		    			count_answer++;
		    			
		    			//リクエストに対してcountAnswerにcount_answerを格納してセットする。
		    			request.setAttribute("countAnswer", count_answer);
		    			
		    		} else {
		    			//入力画面とレコードの回答が1つも合っていなかったら実行する。
		    			if(count_answer == 0) {
		    				//リクエストに対してcountAnswerにcount_answerを格納してセットする。
		    				request.setAttribute("countAnswer", count_answer);
		    			}
		    		}
		    		
		    	}
		    	
	    	}
	    	
	    	//点数計算
	    	int score = 100 * count_answer / number_of_questions;
	    	
	    	//小数点第1位で四捨五入する。
	    	//Math.roundを利用する。roundメソッドの返り値は四捨五入された値になる。
	    	Math.round(score);
	    	
	    	//リクエストに対してscoreにscoreを格納してセットする。
	    	request.setAttribute("score", score);
	    	
	    	//現在日時を取得
	    	Date nowDate = new Date();
	    	
	    	//表示形式を指定
	    	//SimpleDateFormatを型にchange_shapeを定義する。
	    	SimpleDateFormat change_shape = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    	
	    	//取得された日時を日付文字列にフォーマットする。
	    	//※formatメソッドを利用する。
	    	String format_nowdate = change_shape.format(nowDate);
	    	
	    	//リクエストに対してscoreにformatNowDateを格納してセットする。
	    	request.setAttribute("formatNowDate", format_nowdate);
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	
	    }
	    
		//	JSP読み込み	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/test/result.jsp");
		
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
