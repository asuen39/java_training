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
		/**
		 * 文字コードの指定
		 * request設定後にsetCharacterEncoding設置
		 * 文字コード: utf-8
		 */
	    request.setCharacterEncoding("utf-8");
	    
	    /**
	     * confirm.jspからform値を取得
	     * @param int edit_id: Integer.parseIntで数値を指定しrequest.getParameterでtoi_number_upを取得します。
	     * @param String textarea_edit: request.getParameterでtextarea_editを取得します。
	     * @param String[] answer_Id: request.getParameterValuesでanswer_Idを取得します。
	     * @param String[] answer: request.getParameterValuesでanswerを取得します。
	     */
	    int edit_id = Integer.parseInt(request.getParameter("toi_number_up"));
  		String textarea_edit = request.getParameter("textarea_edit");
  		String[] answer_Id = request.getParameterValues("answer_Id");
  		String[] answer = request.getParameterValues("answer");
  	    
  		//例外処理の為try-catch文を使用する。
  		try {
  			
  			/*
	    	 * 問題一覧取得
	    	 * QuestionsDaoの型でdaoを宣言。
	    	 * newを使用してQuestionsDaoをインスタンス化させる。
	    	 * */
			QuestionsDao dao = new QuestionsDao();
	    	
			/*
			 * 回答一覧取得
			 * CorrectAnswersDaoの型でdao_answerを宣言。
			 * newを使用してCorrectAnswersDaoをインスタンス化させる。
			 * */
	    	CorrectAnswersDao dao_answer = new CorrectAnswersDao();
	    	
	    	/*
	    	 * dao_answer.findByQuetionsIdに引数edit_idを設置して実行する。
	    	 * ArrayList<CorrectAnswersBean>の型にanswerBeanをセットする。
	    	 * answerBeanにdao_answer.findByQuetionsIdの結果を格納する。
	    	 * */
	    	ArrayList<CorrectAnswersBean> answerBean = (ArrayList<CorrectAnswersBean>) dao_answer.findByQuetionsId(edit_id);
	    	
	    	/**
	    	 * dao.updateに引数edit_id、 textarea_editを設置して実行する。
	    	 */
	    	dao.update(edit_id, textarea_edit);
	    	
	    	/**
	    	 * 答え一覧のレコード追加・削除・更新
	    	 * flag変数を宣言
	    	 * @param int i: 初期値に0を設置する。
	    	 * */
	    	int i = 0;
	    	
	    	/**
	    	 * for文を使用する。
	    	 * answer_Idをlengthとして数値化する。
	    	 * iとの比較文を作成する。
	    	 */
    		for ( i = 0; i < answer_Id.length; i++) {
    			
    			//1つずつ取り出されたanswer_Idの値をnullか空文字か判定させる。
    			if(answer_Id[i] == null || answer_Id[i].equals("")) {
    				
    				//nullか空文字があてはまる場合、dao_answer.insertAnswerを実行する
    				//引数にはedit_id、 answerを設置する。
    				dao_answer.insertAnswer(edit_id,  answer[i]);
    				
    			} else {
    				
    				//条件文に当てはまらない場合、dao_answer.updateAnswerを実行する。
    				//引数にはInteger.parseIntで数値していanswer_Id、 answerを設置する。
    				dao_answer.updateAnswer(Integer.parseInt(answer_Id[i]),  answer[i]);
    			}
	    		
	    	}
    		
    		/**
    		 * 削除処理 入力画面から送られてこなかったidがある場合
    		 * データベースから取得してきたデータをループさせて1件ずつ値を取り出す。
    		 * 
    		 * for文を使用する。
    		 * CorrectAnswersBeanの型でanswerBean_numberを宣言する。
    		 * answerBeanをanswerBean_numberに格納する。
    		 */
	    	for(CorrectAnswersBean answerBean_number : answerBean) {
	    		
	    		//※一回登録後に再度inputを未入力にして更新ボタンがおされたらレコードを削除する処理が必要。
    			//※答えの文字列の有無をする判定処理が必要。
	    		//修正課題:後で処理を追加する。
	    		
	    		//int flagを宣言する。初期値は0とする。
	    		int flag = 0;
	    		
	    		//入力画面から送られてきたidを1件ずつとりだす
	    		//for文を使用する。
	    		//String Answer_Idを宣言する。
	    		//answer_IdをAnswer_Idに格納する。
	    		for (String Answer_Id : answer_Id) {
	    			
	    			//データベースの答え一覧のidと入力画面から送られてきたidを比較する
	    			//Answer_Id.equals(""): idに空文字があるか比較。
	    			//nswerBean_number.getId() == Integer.parseInt(Answer_Id): idが等しいか比較
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
