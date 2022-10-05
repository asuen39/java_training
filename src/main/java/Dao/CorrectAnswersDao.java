package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	public List<CorrectAnswersBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, questions_id, answer FROM correct_answers";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				CorrectAnswersBean bean = new CorrectAnswersBean(id, questions_id, answer);
				list.add(bean);
			}
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
	public CorrectAnswersBean find(int id) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE id = ?";
			
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			CorrectAnswersBean bean = new CorrectAnswersBean();
			while (rs.next()) {
				int id_number = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
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
	//	find名の指定。ArrayListの指定もpublicの箇所で出来るみたい。
	public ArrayList<CorrectAnswersBean> findByQuetionsId(int questions_id) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE questions_id = ?";
			
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			//questions_idをセット
			//1番目の？を置き換える。
			st.setInt(1, questions_id);
			
			rs = st.executeQuery();
			//CorrectAnswersBean bean = new CorrectAnswersBean();
			
			//listの指定をここで行う
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			
			while (rs.next()) {
				int id_number = rs.getInt("id");
				int questions_Id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				
				CorrectAnswersBean bean = new CorrectAnswersBean(id_number, questions_Id, answer);
				
				//	bean.setId(id_number);
				//	bean.setQuestionId(questions_id);
				//	bean.setAnswer(answer);
				
				list.add(bean);
			}
			
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
	public void deleteAnswer(int questions_id) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "DELETE FROM correct_answers WHERE questions_id = ?";

			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, questions_id);
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
	public int entryAnswer(int questions_id, String answer1) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO correct_answers (questions_id, answer, created_at, updated_at) values (?, ?, current_timestamp(),current_timestamp())";

			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, questions_id);
			st.setString(2, answer1);
			int result = st.executeUpdate();
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

	
}