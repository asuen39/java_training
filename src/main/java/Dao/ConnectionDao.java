package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

//データベース接続情報

//ConnectionDaoの定義をする
public class ConnectionDao {
	//String型でDRIVER_NAMEを定義する。
	//com.mysql.cj.jdbc.Driverを格納する。
	//※finalとは？値を変更する事が無い変数を指す。
	//※定数定義時にfinalを使用することが多い。
	//※定数とは?決まった値の固定値。
	final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	
	//String型でDB_NAMEを定義する。
	//db_tableを格納する。
	final String DB_NAME = "db_table";
	
	//String型でDB_USERを定義する。
	//rootを格納する。
	final String DB_USER = "root";
	
	//String型でDB_PASSWORDを定義する。
	//c0mq69siEf.Oを格納する。
	final String DB_PASSWORD = "c0mq69siEf.O";

	//接続情報を格納する変数
	//メンバ変数とは？クラスの直下に書かれた変数の事。クラス内のメソッドから自由にアクセス出来る。
	//Connectionオブジェクトの変数にconのクラスがアクセスしている。
	public Connection con;

	//ConnectionDaoを定義する。
	public ConnectionDao() throws Exception {
		setConnection();
	}

	//setConnectionを定義する。
	public void setConnection() throws Exception{
		try {
			//String型のurlを定義する。
			//jdbc:mysql://127.0.0.1:3306/" + DB_NAME + "?ServerTimezone=JST&allowPublicKeyRetrieval=true&useSSL=false
			String url = "jdbc:mysql://127.0.0.1:3306/" + DB_NAME + "?ServerTimezone=JST&allowPublicKeyRetrieval=true&useSSL=false";
			
			//fornameとは?クラス名からインスタンスを生成する。
			Class.forName(DRIVER_NAME).newInstance();
			
			//conにDriverManagerのgetConnectionメソッドを格納する。
			con = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
		} catch(SQLException e) {
			e.printStackTrace();
			throw new Exception();
		} catch(Exception e) {
					e.printStackTrace();
					throw new Exception();
		}
	}
	protected String getStringCurrentTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/ddHH:mm:ss");
        String strTimestamp = sdf.format(timestamp);
        return strTimestamp;
	}
	public void close() throws SQLException{
		if(con != null) {
			con.close();
			con = null;
		}
	}
}
