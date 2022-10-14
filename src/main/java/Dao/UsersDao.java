package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//UsersDaoというクラスを定義する。
//extendsをする事でConnectionDaoの要素を継承している。
public class UsersDao extends ConnectionDao {
	
	/**
	 * コンストラクタ
	 */
	public UsersDao() throws Exception {
		setConnection();
	}

	/**
	 * users テーブルを全件取得
	 */
	//Daoとは? データベースの接続情報を持ち、データベースにアクセスしてデータの取得や操作を行える
	//publicでアクセス可能にする。
	
	//findAllメソッドの戻り値の型はList<UsersBean>と定義する。
	public List<UsersBean> findAll() throws Exception {
		
		//データベースに繋がっているか判定をだす。
		if (con == null) {
			//データベースに接続する。
			setConnection();
		}
		
		//PreparedStatementでstを定義する。
		PreparedStatement st = null;
		
		//ResultSetでrsを定義する。
		ResultSet rs = null;
		
		//例外処理のtry-catch文を使用する。
		try {
			//・変数sqlにsql文を格納する。
			//1. usersテーブルからWHERE delete_flagが0の物を全件取得する。
			//2. レコードの取得フィールドはid、name、password
			//※. sql文内にwhere でdelete_flag = 0の条件文を設置する。
			String sql = "SELECT id, name, password FROM users WHERE delete_flag = 0";
			
			//stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			//※conとprepareStatementは連結ができる。
			st = con.prepareStatement(sql);
			
			//sqlの実行。
			//executeQueryで実行されたsql文の結果をrsに格納する。
			rs = st.executeQuery();
			
			// returnで返ってきたlistの結果を格納する。
			// 1.listをインスタンス化する。
			List<UsersBean> list = new ArrayList<UsersBean>();
			
			//実行されたsqlからレコードが抽出される。
			//rsをwhileで繰り返し処理を行う。nextは空白文字も含む
			while (rs.next()) {
				// idにレコードのidをセットする。
				//getIntとは？ResultSetオブジェクトでid列の値をintとして取得する。
				int id = rs.getInt("id");
				
				// nameにレコードのnameをセットする。
				//getStringとは？ResultSetオブジェクトでname列の値をstringとして取得する。
				String name = rs.getString("name");
				
				// passにレコードのパスワードをセットする。
				//getStringとは？ResultSetオブジェクトでpassword列の値をstringとして取得する。
				String pass = rs.getString("password");
				
				//beanにid,name,passを設置する。UsersBean beanで呼び出し可能にする。
				UsersBean bean = new UsersBean(id, name, pass);
				list.add(bean);
			}
			
			//listクラスに処理結果返す。
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
	//UsersBean findを宣言する。
	//findメソッドの戻り値の型はUsersBeanと定義する。
	//findにuser_idの引数をセットする。
	public UsersBean find(int user_id) throws Exception {
		//データベースに繋がっているかの判定をだす
		if (con == null) {
			setConnection();
		}
		//PreparedStatementでstを定義する。
		PreparedStatement st = null;
		//ResultSetでrsを定義する。
		ResultSet rs = null;
		try {
			//・変数sqlにsql文を格納する。
			//1. usersテーブルからWHERE idで指定された値の物を1件取得する。
			//2. レコードの取得フィールドはid、name、password
			//※. sql文内にwhere でid = ?の条件文を設置する。
			String sql = "SELECT id, name, password FROM users WHERE id = ?";
			
			//・stに情報を格納。
			//1.con はデータベースの情報格納。
			//2. データベースに情報を送る為にprepareStatementにsql文を格納する。
			st = con.prepareStatement(sql);
			
			//sql文内で1つ目の？にuser_idを代入する。
			st.setInt(1, user_id);
			
			//sqlの実行
			//executeQueryで実行されたsql文の結果をrsに格納する。
			rs = st.executeQuery();
			
			//・UsersDaoの型でbeanを宣言。
	    	//new UsersDaoを定義する事でインスタンス化している。
			UsersBean bean = new UsersBean();
			
			//・実行されたsqlからレコードが抽出される。
			//rsをwhileで繰り返し処理を行う。nextは空白文字も含む
			while (rs.next()) {
				
				// idにレコードのidをセットする。
				int id = rs.getInt("id");
				
				// nameにレコードのnameをセットする。
				String name = rs.getString("name");
				
				// passにレコードのpassをセットする。
				String pass = rs.getString("password");
				
				//beanにid、name、passをセットする。
				bean.setId(id);
				bean.setName(name);
				bean.setPassword(pass);
			}
			//beanクラスに処理結果を返す
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
}