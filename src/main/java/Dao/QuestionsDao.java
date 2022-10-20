package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//QuestionsDaoを定義する。
//extendsをする事でConnectionDaoの要素を継承している。
public class QuestionsDao extends ConnectionDao {
	
	/**
	 * コンストラクタ
	 */
	public QuestionsDao() throws Exception {
		setConnection();
	}

	/**
	 * questions テーブルを全件取得
	 */
	
	//findAllメソッドの戻り値の型はList<QuestionsBean>と定義する。
	public List<QuestionsBean> findAll() throws Exception {
		
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
			//2. レコードの取得フィールドはid、question
			String sql = "SELECT id, question FROM questions";
			
			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sqlの実行。
			//executeQueryで実行されたsql文の結果をrsに格納する。
			rs = st.executeQuery();
			
			// 1.listをインスタンス化する。
			List<QuestionsBean> list = new ArrayList<QuestionsBean>();
			
			//実行されたsqlからレコードが抽出される。
			//rsをwhileで繰り返し処理を行う。nextをrsに続いて記述する。空白文字も対象にしたいから。
			//レコードの列が存在している順に繰り返し処理が行われる。
			//※動き: レコードを1件ずつ存在確認をしてループしていく。無くなったらループから抜ける。
			while (rs.next()) {
				//int idにレコードのidをセットする。
				//getIntとは？ResultSetオブジェクトでid列の値をintとして取得する。
				int id = rs.getInt("id");
				
				//String questionにレコードのquestionをセットする。
				//getStringとは？ResultSetオブジェクトでquestion列の値をstringとして取得する。
				String question = rs.getString("question");
				
				//id,questionを格納した状態でインスタンス化する。
				QuestionsBean bean = new QuestionsBean(id, question);
				
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
	//QuestionsBean findを宣言する。
	//findメソッドの戻り値の型はQuestionsBeanと定義する。
	//findにint idの引数をセットする。
	/**
	  * 指定IDのレコードを取得する
	  * @param int id
	  * @throws(Exception)
	  * @return QuestionsBean
	 **/
	//※@param は引数、@returnは返り値、@throwsは発生する可能性のある例外
	public QuestionsBean find(int id) throws Exception {
		
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
			String sql = "SELECT id, question FROM questions WHERE id = ?";

			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にuser_idを代入する。
			//※seTintを使用する。
			st.setInt(1, id);
			
			//sqlの実行
			//executeQueryで実行されたsql文の結果をrsに格納する。
			rs = st.executeQuery();
			
			//・QuestionsBeanの型でbeanを宣言。
	    	//new QuestionsBeanを定義する事でインスタンス化している。
			QuestionsBean bean = new QuestionsBean();
			
			//・実行されたsqlからレコードが抽出される。
			//rsをwhileで繰り返し処理を行う。nextは空白文字も含む
			//レコードの列が存在している順に繰り返し処理が行われる。
			//※動き: レコードを1件ずつ存在確認をしてループしていく。無くなったらループから抜ける。
			while (rs.next()) {
				// int id_questionにレコードのidをセットする。
				// ※getIntを使用する。
				int id_question = rs.getInt("id");
				
				// questionにレコードのquestionをセットする。
				// getStringを使用する。
				String question = rs.getString("question");
				
				// beanにid_question、questionをセットする。
				// ※QuestionsBean.javaで使用しているsetId、setQuestionを使用する。
				bean.setId(id_question);
				bean.setQuestion(question);
			}
			
			//findメソッドを呼んだ項目に対してbeanを返す。
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
	 * 指定IDのレコードを削除する
	 */
	//void deleteを宣言する。
	//voidなのでdeleteメソッドの戻り値は無しとなる。
	//deleteにint idの引数をセットする。
	//※引数とはメソッドに渡すデータ。「この値を使ってデータを処理して欲しい」というときに、渡す値のこと
	/**
	  * 指定IDのレコードを削除する
	  * @param int id
	  * @throws(Exception)
	 **/
	public void delete(int id) throws Exception {
		
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
			//1. questionsテーブルからWHERE idで指定された値の物を1件削除する。
			//2. レコードの取得フィールドはquestion
			//※. sql文内にwhere でid = ?の条件文を設置する。
			String sql = "DELETE FROM questions WHERE id = ?";

			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にidを代入する。
			//※seTintを使用する。
			st.setInt(1, id);
			
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
	 * 指定IDのレコードを更新する
	 */
	//void updateを宣言する。
	//voidなのでupdateメソッドの戻り値は無しとなる。
	//updateにint edit_id、String textarea_editの引数をセットする。
	/**
	  * 指定IDのレコードを更新する
	  * @param int edit_id
	  * @param String textarea_edit
	  * @throws(Exception)
	 **/
	public void update(int edit_id, String textarea_edit) throws Exception {
		
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
			//1. questionsテーブルからセットされたquestionとWHERE idで指定された値の物を1件更新する。
			//※. sql文内にSETの後にquestion = ?の条件文を設置する。
			//※. sql文内にwhere でid = ?の条件文を設置する。
			String sql = "UPDATE questions SET question = ? where id = ?";

			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にquestionを代入する。
			//※setStringを使用する。
			st.setString(1, textarea_edit);
			
			//sql文内で2つ目の？にedit_idを代入する。
			//※setIntを使用する。
			st.setInt(2, edit_id);
			
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
	//void entryを宣言する。
	//voidなのでentryメソッドの戻り値は無しとなる。
	//entryにString textarea_editの引数をセットする。
	/**
	  * 指定のレコード登録する
	  * @param String textarea_edit
	  * @throws(Exception)
	 **/
	public void entry(String textarea_edit) throws Exception {
		
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
			//1. questionsテーブルからセットされたquestion, created_at, updated_atの列とvaluesで指定されたレコードから1件登録する。
			//※. sql文内にINSERTで指定の配列設置後、valuesで(?, current_timestamp(),current_timestamp())の条件文を設置する。
			String sql = "INSERT INTO questions (question, created_at, updated_at) values (?, current_timestamp(),current_timestamp())";
			
			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にtextarea_editを代入する。
			//※setStringを使用する。
			st.setString(1, textarea_edit);
			
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
	 * 問題のidの抽出
	 * 新規登録の処理に値を渡す。
	 */
	//int getQuestionIdを宣言する。
	//intは整数扱った型になる。
	public int getQuestionId() throws Exception {
		
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
			//1. questionsテーブルからid列の最大値を取得する。
			//※. sql文内にMAX(id)の条件文を設置する。
			String sql = "SELECT MAX(id) as id From questions";
			
			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sqlの実行。
			//executeQueryで実行されたsql文の結果をrsに格納する。
			rs = st.executeQuery();
			
			//int idを0で宣言する。
			int id = 0;
			
			//・実行されたsqlからレコードが抽出される。
			//rsをwhileで繰り返し処理を行う。nextは空白文字も含む
			//レコードの列が存在している順に繰り返し処理が行われる。
			//※動き: レコードを1件ずつ存在確認をしてループしていく。無くなったらループから抜ける。
			while (rs.next()) {
				
				// idにレコードのidをセットする。
				// ※getIntを使用する。
				id = rs.getInt("id");
			}
			
			//getQuestionIdメソッドを呼んだ項目に対してidを返す。
			return id;
			
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