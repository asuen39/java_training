package Dao;

import java.sql.Timestamp;
public class CorrectAnswersBean {
	private int id;
	private int questions_id;
	private String answer;
	private Timestamp created_at;
	private Timestamp updated_at;

/**
 * コンストラクタ
 */
	//CorrectAnswersBeanを定義する。newが実行された時に処理をする定義
	//※他で使用出来るようにid、questions_id、answerを設置する。thisで設置できる。
	//※thisとは自分自身を指す言葉。自分自身のインスタンスを明示的に指し示し、そのフィールドやメソッドを使う為の変数。
	public CorrectAnswersBean(int id, int questions_id, String answer) {
		this.id = id;
		this.questions_id = questions_id;
		this.answer = answer;
	}
	
	/** 引数無しのコンストラクタ **/
	public CorrectAnswersBean() {

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
	//setQuestionでセットされたquestions_idを取得する。
	//※returnセット後、this.questions_idの指定
	public int getQuestionId() {
		return this.questions_id;
	}
	//他から呼ばれたらquestions_idをセットする。
	//※引数でstring questions_idをセット
	//※this.questions_idにquestions_idをセット
	public void setQuestionId(int questions_id) {
		this.questions_id = questions_id;
	}
	//setAnswerでセットされたanswerを取得する。
	//※returnセット後、this.answerの指定
	public String getAnswer() {
		return this.answer;
	}
	//他から呼ばれたらanswerをセットする。
	//※引数でstring answerをセット
	//※this.answerにanswerをセット
	public void setAnswer(String answer) {
		this.answer = answer;
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
}