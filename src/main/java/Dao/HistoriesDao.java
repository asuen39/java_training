package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//HistoriesDaoを定義する。
//extendsをする事でConnectionDaoの要素を継承している。
public class HistoriesDao extends ConnectionDao {
	
	/**
	 * コンストラクタ
	 */
	public HistoriesDao() throws Exception {
		setConnection();
	}
	
	//void scoreResultsを宣言する。
	//voidなのでscoreResultsメソッドの戻り値は無しとなる。
	//scoreResultsにString login_id, int scoreの引数をセットする。
	/**
	  * 指定のレコード登録する
	  * @param String login_id
	  * @param int score
	  * @throws(Exception)
	 **/
	public void scoreResults(int login_id, int score) throws Exception {
		
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
			//1. historiesテーブルからセットされたuser_id, point, created_atの列とvaluesで指定されたレコードから1件登録する。
			//※. sql文内にINSERTで指定の配列設置後、valuesで(?, ?, current_timestamp())の条件文を設置する。
			String sql = "INSERT INTO histories (user_id, point, created_at) values (?, ?, current_timestamp())";
			
			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にlogin_idを代入する。
			//※setStringを使用する。
			st.setInt(1, login_id);
			
			//sql文内で2つ目の？にscoreを代入する。
			//※setIntを使用する。
			st.setInt(2, score);
			
			//sqlの実行
			//executeUpdateで実行される。
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
	 * histories テーブルを全件取得
	 */
	//findAllメソッドの戻り値の型はList<HistoriesBean>と定義する。
	public List<HistoriesBean> findAll(int login_id) throws Exception {
		
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
			//1. historiesテーブルから全件取得する。
			//2. レコードの取得フィールドはid、user_id、point
			String sql = "SELECT id, user_id, point, created_at FROM histories WHERE user_id = ? ORDER BY created_at ASC";
			
			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にlogin_idを代入する。
			//※setStringを使用する。
			st.setInt(1, login_id);
			
			//sqlの実行。
			//executeQueryで実行されたsql文の結果をrsに格納する。
			rs = st.executeQuery();
			
			// 1.listをインスタンス化する。
			List<HistoriesBean> list = new ArrayList<HistoriesBean>();
			
			//実行されたsqlからレコードが抽出される。
			//rsをwhileで繰り返し処理を行う。nextをrsに続いて記述する。空白文字も対象にしたいから。
			//レコードの列が存在している順に繰り返し処理が行われる。
			//※動き: レコードを1件ずつ存在確認をしてループしていく。無くなったらループから抜ける。
			while (rs.next()) {
				//int idにレコードのidをセットする。
				//getIntとは？ResultSetオブジェクトでid列の値をintとして取得する。
				int id = rs.getInt("id");
				
				//int user_idにレコードのuser_idをセットする。
				int user_id = rs.getInt("user_id");
				
				//int pointにレコードのpointをセットする。
				int point = rs.getInt("point");
				
				//Timestamp created_atにレコードのcreated_atをセットする。
				Timestamp created_at = rs.getTimestamp("created_at");
				
				//id, user_id, point, created_atを格納した状態でインスタンス化する。
				HistoriesBean bean = new HistoriesBean(id, user_id, point, created_at);
				
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
}