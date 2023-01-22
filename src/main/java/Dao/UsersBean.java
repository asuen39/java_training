package Dao;

//Timestampをインポートする。
import java.sql.Timestamp;
public class UsersBean {
	private int id;
	private String name;
	private String password;
	private Timestamp created_at;
	private Timestamp updated_at;
	private byte deleteflag; 
	private Timestamp deleted_at;

/**
 * コンストラクタ
 */
	//コンストラクタとは？クラスの変数を初期化する。
	//UsersBeanを定義する。newが実行された時に処理をする定義
	//※コンストラクタはnewの演算子を使用して生成された時に自動で呼び出される。
	//※他で使用出来るようにid, name, passwordを設置する。thisで設置できる
	public UsersBean(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	/** 引数無しのコンストラクタ **/
	//※オーバーライドとは?  継承した親クラスのメソッドを再定義する
	//※引数有り無しを定義する
	public UsersBean() {

	}
	
	//setIdでセットされたidを取得する。
	public int getId() {
		return this.id;
	}
	//他から呼ばれたらidをセットする。
	//※bean等に代入することから入れ物というイメージが良い。
	public void setId(int id) {
		this.id = id;
	} 
	//setNameでセットされたnameを取得する。
	public String getName() {
		return this.name;
	}
	//他から呼ばれたらnameをセットする。
	//※voidとはメソッド値を返さない定義になる
	public void setName(String name) {
		this.name = name;
	}
	//setPasswordでセットされたpasswordを取得する。
	public String getPassword() {
		return this.password;
	}
	//他から呼ばれたらpasswordをセットする。
	public void setPassword(String password) {
		this.password = password;
	}
	//setCreatedAtでセットされたcreated_atを取得する。
	public Timestamp getCreatedAt() {
		return this.created_at;
	}
	//他から呼ばれたらcreated_atをセットする。
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	//setUpdatedAtでセットされたupdated_atを取得する
	public Timestamp getUpdatedAt() {
		return this.updated_at;
	}
	//他から呼ばれたらupdated_atをセットする。
	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	//setDeleteflagでセットされたdeleteflagを取得する
	public byte getDeleteflag() {
		return this.deleteflag;
	}
	//他から呼ばれたらdeleteflagをセットする。
	public void setDeleteflag(byte deleteflag) {
		this.deleteflag = deleteflag;
	}
}