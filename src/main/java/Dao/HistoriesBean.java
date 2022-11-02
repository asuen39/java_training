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
	//QuestionsBeanを定義する。newが実行された時に処理をする定義
	//※他で使用出来るようにid, questionを設置する。thisで設置できる
	//※thisとは自分自身を指す言葉。自分自身のインスタンスを明示的に指し示し、そのフィールドやメソッドを使う為の変数。
	public HistoriesBean(int id, int user_id, int point) {
		this.id = id;
		this.point = point;
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
	//setIdでセットされたidを取得する。
	//※returnセット後、this.idの指定
	public int getUserId() {
		return this.user_id;
	}
	//他から呼ばれたらidをセットする。
	//※引数でint idをセット。
	//※this.idにidをセットする。
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	//setQuestionでセットされたquestionを取得する。
	//※returnセット後、this.questionの指定
	public int getPoint() {
		return this.point;
	}
	//他から呼ばれたらquestionをセットする。
	//※引数でstring questionをセット
	//※this.questionにquestionをセット
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