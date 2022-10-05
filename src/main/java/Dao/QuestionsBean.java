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
	public QuestionsBean(int id, String question) {
		this.id = id;
		this.question = question;
	}
	
	/** 引数無しのコンストラクタ **/
	public QuestionsBean() {

	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	public String getQuestion() {
		return this.question;
	}	
	public void setQuestion(String question) {
		this.question = question;
	}
	public Timestamp getCreatedAt() {
		return this.created_at;
	}
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdatedAt() {
		return this.updated_at;
	}
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