package servlet.edit;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CorrectAnswersBean;
import Dao.CorrectAnswersDao;
import Dao.QuestionsDao;

/**
 * Servlet implementation class Top
 */
@WebServlet("/edit/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
	    int edit_id = Integer.parseInt(request.getParameter("toi_number_up"));
  		String textarea_edit = request.getParameter("textarea_edit");
  		String[] answer_Id = request.getParameterValues("answer_Id");
  		String[] answer = request.getParameterValues("answer");
  	    
  	    //formから値を取得 レコード登録	
  		try {
	    	//問題一覧取得
			QuestionsDao dao = new QuestionsDao();
	    	
			//回答一覧取得
	    	CorrectAnswersDao dao_answer = new CorrectAnswersDao();
	    	//idや諸々全部取得
	    	ArrayList<CorrectAnswersBean> answerBean = (ArrayList<CorrectAnswersBean>) dao_answer.findByQuetionsId(edit_id);
	    	
	    	//問題文の登録実行 textarea_editを登録する。
	    	dao.update(edit_id, textarea_edit);
	    	
	    	//答えの追加・削除・更新
	    	
	    	//flag変数を宣言
	    	int i = 0;
	    	//入力画面からの答え一覧のidをループさせる。
    		for ( i = 0; i < answer_Id.length; i++) {
    			
    			//1つ取り出された答え一覧のidをnullか空文字か判定させる。
    			if(answer_Id[i] == null || answer_Id[i].equals("")) {
    				
    				//nullか空文字があてはまる場合、問題番号のidと該当する答えを実行する
    				//新規に追加する。
    				//※登録は出来るけどコンソールでエラー文が表示される。「For input string: ""」
    				dao_answer.insertAnswer(edit_id,  answer[i]);
    				
    			} else {
    				
    				//答え一覧のidが条件文に当てはまらない場合、入力画面の答えのidと答えの文字列を実行する。
    				//更新する。
    				dao_answer.updateAnswer(Integer.parseInt(answer_Id[i]),  answer[i]);
    			}
	    		
	    	}
    		
	    	//削除処理 入力画面から送られてこなかったidがある場合、
    		//データベースから取得してきたデータをループさせて1件ずつ値を取り出す
	    	for(CorrectAnswersBean answerBean_number : answerBean) {
	    		
	    		//※一回登録後に再度inputを未入力にして更新ボタンがおされたらレコードを削除する処理が必要。
    			//※答えの文字列の有無をする判定処理が必要。
	    		//修正課題:後で処理を追加する。
	    		
	    		//flag変数を宣言
	    		int flag = 0;
	    		
	    		//入力画面から送られてきたidを1件ずつとりだす
	    		for (String Answer_Id : answer_Id) {
	    			
	    			//データベースの答え一覧のidと入力画面から送られてきたidを比較する
	    			if(!Answer_Id.equals("") && answerBean_number.getId() == Integer.parseInt(Answer_Id) ) {
	    				//条件文を通過する場合はflagに1を追加して返す。
	    				flag++;
	    				
	    			}
	    		}
	    		
	    		//上記の条件文でフラグが加算されず0で来た場合。
	    		if( flag == 0) {
	    			//0の時にループさせていた値のレコードを削除する。
	    			dao_answer.deleteIdAnswer(answerBean_number.getId());
	    		}
	    		
    		}
	    	
	    	
	    } catch (Exception e) {
			e.printStackTrace();

		}
	    
		//	listサーブレットを読み込み	
		response.sendRedirect("../list");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
