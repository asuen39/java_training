package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//CorrectAnswersDaoを定義する。
//extendsをする事でConnectionDaoの要素を継承している。
public class CorrectAnswersDao extends ConnectionDao {
	
	/**
	 * コンストラクタ
	 */
	public CorrectAnswersDao() throws Exception {
		setConnection();
	}

	/**
	 * correct_answers テーブルを全件取得
	 */
	//findAllメソッドの戻り値の型はList<CorrectAnswersBean>と定義する。
	public List<CorrectAnswersBean> findAll() throws Exception {
		//データベースに繋がっているか判定をだす。
		//※conで条件文を作成する。
		if (con == null) {
			setConnection();
		}
		
		//PreparedStatementでstを定義する。
		PreparedStatement st = null;
		
		//ResultSetでrsを定義する。
		ResultSet rs = null;
		
		//例外処理のtry-catch文を使用する。
		try {
			//・変数sqlにsql文を格納する。
			//1. questionsテーブルから全件取得する。
			//2. レコードの取得フィールドはid、questions_id、 answer
			String sql = "SELECT id, questions_id, answer FROM correct_answers";
			
			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sqlの実行。
			//executeQueryで実行されたsql文の結果をrsに格納する。
			rs = st.executeQuery();
			
			// 1.listをインスタンス化する。
			List<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			
			//実行されたsqlからレコードが抽出される。
			//rsをwhileで繰り返し処理を行う。nextをrsに続いて記述する。空白文字も対象にしたいから。
			//レコードの列が存在している順に繰り返し処理が行われる。
			//※動き: レコードを1件ずつ存在確認をしてループしていく。無くなったらループから抜ける。
			while (rs.next()) {
				//int idにレコードのidをセットする。
				//getIntとは？ResultSetオブジェクトでid列の値をintとして取得する。
				int id = rs.getInt("id");
				
				//int questions_idにレコードのquestions_idをセットする。
				//getIntとは？ResultSetオブジェクトでquestion列の値をintとして取得する。
				int questions_id = rs.getInt("questions_id");
				
				//String answerにレコードのanswerをセットする。
				//getStringとは？ResultSetオブジェクトでquestion列の値をstringとして取得する。
				String answer = rs.getString("answer");
				
				//id,questions_id,answerを格納した状態でインスタンス化する。
				CorrectAnswersBean bean = new CorrectAnswersBean(id, questions_id, answer);
				
				//list配列にbeanを追加する。
				//※addで追加をする。
				list.add(bean);
			}
			
			//findallメソッドを呼んだ項目に対してlistを返す。
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}
					
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");

			}
		}
	}

	/**
	 * 指定IDのレコードを取得する
	 */
	//findメソッドの戻り値の型はListCorrectAnswersBeanと定義する。
	/**
	  * 指定IDのレコードを取得する
	  * @param int id
	  * @throws(Exception)
	  * @return CorrectAnswersBean
	 **/
	//※@param は引数、@returnは返り値、@throwsは発生する可能性のある例外
	public CorrectAnswersBean find(int id) throws Exception {
		
		//データベースに繋がっているか判定をだす。
		if (con == null) {
			setConnection();
		}
		
		//PreparedStatementでstを定義する。
		PreparedStatement st = null;
		
		//ResultSetでrsを定義する。
		ResultSet rs = null;
		
		//例外処理のtry-catch文を使用する。
		try {
			//・変数sqlにsql文を格納する。
			//1. questionsテーブルからWHERE idで指定された値の物を1件取得する。
			//2. レコードの取得フィールドはid、question
			//※. sql文内にwhere でid = ?の条件文を設置する。
			String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE id = ?";
			
			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にidを代入する。
			//※seTintを使用する。
			st.setInt(1, id);
			
			//sqlの実行
			//executeQueryで実行されたsql文の結果をrsに格納する。
			rs = st.executeQuery();
			
			//・QuestionsBeanの型でbeanを宣言。
	    	//new QuestionsBeanを定義する事でインスタンス化している。
			CorrectAnswersBean bean = new CorrectAnswersBean();
			
			//・実行されたsqlからレコードが抽出される。
			//rsをwhileで繰り返し処理を行う。nextは空白文字も含む
			//レコードの列が存在している順に繰り返し処理が行われる。
			//※動き: レコードを1件ずつ存在確認をしてループしていく。無くなったらループから抜ける。
			while (rs.next()) {
				// int id_numberにレコードのidをセットする。
				// ※getIntを使用する。
				int id_number = rs.getInt("id");
				
				// questions_idにレコードのquestions_idをセットする。
				// getIntを使用する。
				int questions_id = rs.getInt("questions_id");
				
				// answerにレコードのanswerをセットする。
				// getStringを使用する。
				String answer = rs.getString("answer");
				
				// beanにid_number、questions_id、answerをセットする。
				// ※QuestionsBean.javaで使用しているsetId、setQuestion、setAnswerを使用する。
				bean.setId(id_number);
				bean.setQuestionId(questions_id);
				bean.setAnswer(answer);

			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}				
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}
	
	/**
	 * questions_id
	 */
	//findByQuetionsIdメソッドの戻り値の型はList<CorrectAnswersBean>と定義する。
	/**
	  * 指定IDのレコードを取得する
	  * @param int questions_id
	  * @throws(Exception)
	  * @return ArrayList CorrectAnswersBean
	 **/
	public ArrayList<CorrectAnswersBean> findByQuetionsId(int questions_id) throws Exception {
		
		//データベースに繋がっているか判定をだす。
		if (con == null) {
			setConnection();
		}
		
		//PreparedStatementでstを定義する。
		PreparedStatement st = null;
		
		//ResultSetでrsを定義する。
		ResultSet rs = null;
		
		//例外処理のtry-catch文を使用する。
		try {
			//・変数sqlにsql文を格納する。
			//1. questionsテーブルからWHERE questions_idで指定された値の物を1件取得する。
			//2. レコードの取得フィールドはid、questions_id、answer
			//※. sql文内にwhere でid = ?の条件文を設置する。
			String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE questions_id = ?";
			
			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にquestions_idを代入する。
			//※seTintを使用する。
			st.setInt(1, questions_id);
			
			//sqlの実行
			//executeQueryで実行されたsql文の結果をrsに格納する。
			rs = st.executeQuery();
			
			// 1.listをインスタンス化する。
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			
			//実行されたsqlからレコードが抽出される。
			//rsをwhileで繰り返し処理を行う。nextをrsに続いて記述する。空白文字も対象にしたいから。
			//レコードの列が存在している順に繰り返し処理が行われる。
			//※動き: レコードを1件ずつ存在確認をしてループしていく。無くなったらループから抜ける。
			while (rs.next()) {
				//int id_numberにレコードのidをセットする。
				//getIntとは？ResultSetオブジェクトでid列の値をintとして取得する
				int id_number = rs.getInt("id");
				
				//int questions_Idにレコードのquestions_idをセットする。
				//getIntとは？ResultSetオブジェクトでquestions_id列の値をintとして取得する。
				int questions_Id = rs.getInt("questions_id");
				
				//String answerにレコードのanswerをセットする。
				//getStringとは？ResultSetオブジェクトでanswer列の値をstringとして取得する。
				String answer = rs.getString("answer");
				
				//id_number、 questions_Id、answerを格納した状態でインスタンス化する。
				CorrectAnswersBean bean = new CorrectAnswersBean(id_number, questions_Id, answer);
				
				//list配列にbeanを追加する。
				//※addで追加をする。
				list.add(bean);
			}
			
			//findByQuetionsIdメソッドを呼んだ項目に対してlistを返す。
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}				
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}
	
	/**
	 * 指定questions_idのレコードを削除する
	 */
	//deleteAnswerメソッドを定義する。
	//※voidなのでdeleteAnswerメソッドの戻り値は無しとなる。
	//※deleteAnswerにint questions_idの引数をセットする。
	/**
	  * 指定questions_idのレコードを削除する
	  * @param int questions_id
	  * @throws(Exception)
	 **/
	public void deleteAnswer(int questions_id) throws Exception {
		
		//データベースに繋がっているか判定をだす。
		if (con == null) {
			setConnection();
		}
		
		//PreparedStatementでstを定義する。
		PreparedStatement st = null;
		
		//ResultSetでrsを定義する。
		ResultSet rs = null;
		
		//例外処理のtry-catch文を使用する。
		try {
			//・変数sqlにsql文を格納する。
			//1. correct_answersテーブルからWHERE questions_idで指定された値の物を1件削除する。
			//※. sql文内にwhere でquestions_id = ?の条件文を設置する。
			String sql = "DELETE FROM correct_answers WHERE questions_id = ?";

			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にquestions_idを代入する。
			//※seTintを使用する。
			st.setInt(1, questions_id);
			
			//sqlの実行
			//executeUpdateで実行されたsql文の更新結果を返します。
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}				
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}
	
	
	/**
	 * 指定のレコード登録する
	 */
	//int entryAnswerを宣言する。
	//※entryAnswerにint questions_id、 String answer1の引数をセットする。
	/**
	  * 指定のレコード登録する
	  * @param int questions_id、 String answer1
	  * @throws(Exception)
	  * @return entryAnswer
	 **/
	public int entryAnswer(int questions_id, String answer1) throws Exception {
		
		//データベースに繋がっているか判定をだす。
		if (con == null) {
			setConnection();
		}
		
		//PreparedStatementでstを定義する。
		PreparedStatement st = null;
		
		//ResultSetでrsを定義する。
		ResultSet rs = null;
		
		//例外処理のtry-catch文を使用する。
		try {
			//・変数sqlにsql文を格納する。
			//1. correct_answersテーブルからセットされたcorrect_answersとvaluesで指定された値の物を登録する。
			//※. sql文内にINSERTで指定の配列設置後、valuesで((?, ?, current_timestamp(),current_timestamp())の条件文を設置する。
			String sql = "INSERT INTO correct_answers (questions_id, answer, created_at, updated_at) values (?, ?, current_timestamp(),current_timestamp())";

			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にquestions_idを代入する。
			//※seTintを使用する。
			st.setInt(1, questions_id);
			
			//sql文内で2つ目の？にanswer1を代入する。
			//※setStringを使用する。
			st.setString(2, answer1);
			
			//int resultを宣言する。
			//sqlの実行。
			//executeUpdateで実行されたsql文の結果をresultに格納する。
			int result = st.executeUpdate();
			
			//entryAnswerメソッドを呼んだ項目に対してresultを返す。
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}				
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}
	
	/**
	 * 指定IDのレコードを更新する
	 */
	//void updateAnswerを宣言する。
	//※voidなのでupdateAnswerメソッドの戻り値は無しとなる。
	//※updateAnswerにint Answer_Id、 String answerTextの引数をセットする。
	/**
	  * 指定IDのレコードを更新する
	  * @param int Answer_Id、 String answerText
	  * @throws(Exception)
	 **/
	public void updateAnswer(int Answer_Id, String answerText) throws Exception {
		
		//データベースに繋がっているか判定をだす。
		if (con == null) {
			setConnection();
		}
		
		//PreparedStatementでstを定義する。
		PreparedStatement st = null;
		
		//ResultSetでrsを定義する。
		ResultSet rs = null;
		
		//例外処理のtry-catch文を使用する。
		try {
			//・変数sqlにsql文を格納する。
			//1. correct_answersテーブルからセットされたanswerとWHERE idで指定された値の物を1件更新する。
			//※. sql文内にanswer = ?の条件文を設置する。
			//※. sql文内にwhere でid = ?の条件文を設置する。
			String sql = "UPDATE correct_answers SET answer = ? where id = ?";

			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にanswerTextを代入する。
			//※setStringを使用する。
			st.setString(1, answerText);
			
			//sql文内で2つ目の？にAnswer_Idを代入する。
			//※setIntを使用する。
			st.setInt(2, Answer_Id);
			
			//sqlの実行。
			//executeUpdateで実行されたsql文の結果を返す。
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}				
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}

	/**
	 * 指定IDのレコードを追加する
	 */
	//void insertAnswerを宣言する。
	//※voidなのでinsertAnswerメソッドの戻り値は無しとなる。
	//※insertAnswerにint edit_id、 String answer1の引数をセットする。
	/**
	  * 指定IDのレコードを追加する
	  * @param int edit_id, String answer1
	  * @throws(Exception)
	 **/
	public void insertAnswer(int edit_id, String answer1) throws Exception {
		
		//データベースに繋がっているか判定をだす。
		if (con == null) {
			setConnection();
		}
		
		//PreparedStatementでstを定義する。
		PreparedStatement st = null;
		
		//ResultSetでrsを定義する。
		ResultSet rs = null;
		
		//例外処理のtry-catch文を使用する。
		try {
			//・変数sqlにsql文を格納する。
			//1. correct_answersテーブルからセットされたcorrect_answersとvaluesで指定された値の物を登録する。
			//※. sql文内にINSERTで指定の配列設置後、valuesで(?, ?)の条件文を設置する。
			String sql = "INSERT INTO correct_answers (questions_id, answer) values(?, ?)";

			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にedit_idを代入する。
			//※setIntを使用する。
			st.setInt(1, edit_id);
			
			//sql文内で2つ目の？にanswer1を代入する。
			//※setStringを使用する。
			st.setString(2, answer1);
			
			//sqlの実行。
			//executeUpdateで実行されたsql文の結果を返す。
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}				
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}
	
	/**
	 * 指定IDのレコードを削除する
	 */
	//void deleteIdAnswerを宣言する。
	//※voidなのでinsertAnswerメソッドの戻り値は無しとなる。
	//※deleteIdAnswerにint idの引数をセットする。
	/**
	  * 指定IDのレコードを削除する
	  * @param int id
	  * @throws(Exception)
	 **/
	public void deleteIdAnswer(int id) throws Exception {
		
		//データベースに繋がっているか判定をだす。
		if (con == null) {
			setConnection();
		}
		
		//PreparedStatementでstを定義する。
		PreparedStatement st = null;
		
		//ResultSetでrsを定義する。
		ResultSet rs = null;
		
		//例外処理のtry-catch文を使用する。
		try {
			//・変数sqlにsql文を格納する。
			//1. correct_answersテーブルからWHERE idで指定された値の物を1件削除する。
			//※. sql文内にwhere でid = ?の条件文を設置する。
			String sql = "DELETE FROM correct_answers WHERE id = ?";

			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にedit_idを代入する。
			//※setIntを使用する。
			st.setInt(1, id);
			
			//sqlの実行。
			//executeUpdateで実行されたsql文の結果を返す。
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}				
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}

	
}