package Dao;

import java.sql.Timestamp;
public class HistoriesBean {
	private int id;
	private int user_id;
	private int point;
	private Timestamp created_at;

/**
 * コンストラクタ
 */
	//コンストラクタとは？クラスの変数を初期化する。
	//HistoriesBeanを定義する。newが実行された時に処理をする定義
	//※他で使用出来るようにid, questionを設置する。thisで設置できる
	//※thisとは自分自身を指す言葉。自分自身のインスタンスを明示的に指し示し、そのフィールドやメソッドを使う為の変数。
	public HistoriesBean(int id, int user_id, int point, Timestamp created_at) {
		this.id = id;
		this.user_id = user_id;
		this.point = point;
		this.created_at = created_at;
	}
	/** 引数無しのコンストラクタ **/
	public HistoriesBean() {

	}
	//setIdでセットされたidを取得する。
	//※returnセット後、this.idの指定
	public int getId() {
		return this.id;
	}
	//他から呼ばれたらidをセットする。
	//※引数でint idをセット。
	//※this.idにidをセットする。
	public void setId(int id) {
		this.id = id;
	}
	//setUserIdでセットされたuser_idを取得する。
	//※returnセット後、this.user_idの指定
	public int getUserId() {
		return this.user_id;
	}
	//他から呼ばれたらuser_idをセットする。
	//※引数でint user_idをセット。
	//※this.user_idにuser_idをセットする。
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	//setPointでセットされたpointを取得する。
	//※returnセット後、this.pointの指定
	public int getPoint() {
		return this.point;
	}
	//他から呼ばれたらpointをセットする。
	//※引数でint pointをセット
	//※this.pointにpointをセット
	public void setPoint(int point) {
		this.point = point;
	}
	//setCreatedAtでセットされたcreated_atを取得する。
	//※returnセット後、this.create_atの指定
	public Timestamp getCreatedAt() {
		return this.created_at;
	}
	//他から呼ばれたらcreated_atをセットする。
	//※引数でTimestamp created_atの指定
	//※this.created_atにcreated_atをセット
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
}