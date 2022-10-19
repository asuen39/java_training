package Dao;

import java.sql.Timestamp;
public class QuestionsBean {
	private int id;
	private String question;
	private Timestamp created_at;
	private Timestamp updated_at;

/**
 * コンストラクタ
 */
	//コンストラクタとは？クラスの変数を初期化する。
	//QuestionsBeanを定義する。newが実行された時に処理をする定義
	//※他で使用出来るようにid, questionを設置する。thisで設置できる
	public QuestionsBean(int id, String question) {
		this.id = id;
		this.question = question;
	}
	/** 引数無しのコンストラクタ **/
	public QuestionsBean() {

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
	//setQuestionでセットされたidを取得する。
	//※returnセット後、this.questionの指定
	public String getQuestion() {
		return this.question;
	}
	//他から呼ばれたらquestionをセットする。
	//※引数でstring questionをセット
	//※this.questionにquestionをセット
	public void setQuestion(String question) {
		this.question = question;
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
	//setUpdatedAtでセットされたupdated_atを取得する
	//※returnセット後、this.updated_atの指定
	public Timestamp getUpdatedAt() {
		return this.updated_at;
	}
	//他から呼ばれたらupdated_atをセットする。
	//※引数でTimestamp updated_atの指定
	//※this.updated_atにupdated_atをセット
	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	//	一旦かいてだけおく
	// 問い番号の数値等jspでカウンタ用の項目を設置しなくてもここで宣言出来る
	// みたい。jspに調査しながら設置なので一通り作成後にこちらをやってみる。
	// その時にはlist.jspで書いている56行目のカウンタ変数等必要無くなるかもしれない。
	public void outputData() {
	  System.out.println("ID:" + String.valueOf(this.id));
	  System.out.println("問題:" + this.question);
	}
}