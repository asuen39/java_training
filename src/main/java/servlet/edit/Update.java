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
  		
  		//System.out.println(edit_id);
  		//System.out.println(textarea_edit);
  		//System.out.println(answer_Id);
  		//System.out.println(answer);
  	    
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
	    	
	    	//65行目 入力画面からの答え一覧のidをループさせて1件ずつ値を取り出す
    		for (String Answer_Id : answer_Id) {
	    	
	    		//68行目は1件ずつ取り出された答え一覧のidをintとして変換する。
	    		int AnswerId = Integer.parseInt(Answer_Id);
	    		
	    		//データベースから取得してきたデータをループさせて1件ずつ値を取り出す
	    		for(CorrectAnswersBean answerBean_number : answerBean) {
	    			
	    			//データベースから取り出した答え一覧のidと入力画面から送られてきたidと比較する
	    			if(answerBean_number.getId() == AnswerId) {
	    				
	    				//入力画面から送られてきた答えと合致しているidを更新させたい ※答えとidを設定したいがどういう構文にしたらよいか不明
	    				for (String Answer : answer) {
	    					
	    					//1件ずつループさせた答えをstringに入れる。
	    		    		String answerText = Answer;
	    		    		
	    		    		//1件ずつとりだされたidと答えを更新する
		    				dao_answer.updateAnswer(AnswerId,  answerText);
	    		    	}
	    			}
	    		}
	    		
	    		//inputで追加された値が送られるくる。
	    		//情報がないのでnullで入力画面から送られてくる
//	    		if(AnswerId == null) {
	    		
//	    			//問題のquestion_idと新規の答えを登録させる
//	    		}
	    	}
    		
	    	//削除処理 入力画面から送られてこなかったidがある場合、
    		//データベースから取得してきたデータをループさせて1件ずつ値を取り出す
	    	for(CorrectAnswersBean answerBean_number : answerBean) {
	    		
	    		//入力画面から送られてきたidを1件ずつとりだす
	    		for (String Answer_Id : answer_Id) {
	    			
	    			//データベースの答え一覧のidと入力画面から送られてきたidを比較する
//	    			if(answerBean_number.getId() == AnswerId) {
	    			
	    				//一致しないidがある場合はデータベースの答えを削除する
//	    				//dao_answer.updateAnswer()
//	    			}
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
