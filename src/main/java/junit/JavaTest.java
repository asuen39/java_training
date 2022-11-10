package junit;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Dao.CorrectAnswersBean;
import Dao.CorrectAnswersDao;
import Dao.HistoriesBean;
import Dao.HistoriesDao;
import Dao.QuestionsDao;
import Dao.UsersDao;

public class JavaTest {
	
	//usersDaoTest
	@Test
	public void usersDaoTest_findメソッドにDBに存在するIDを割り当てる() throws Exception {
		String login_id = "1";
		
		UsersDao usersDao = new UsersDao();
		
		assertThat(usersDao.find(login_id).getName(), is("satou") );
	}
	
	@Test
	public void usersDaoTest_findメソッドにDBに存在しないIDを割り当てる_数値() throws Exception {
		String login_id = "20";
		
		UsersDao usersDao = new UsersDao();
		
		assertThat(usersDao.find(login_id).getName(), nullValue() );
	}
	
	@Test
	public void usersDaoTest_findメソッドにDBに存在しないIDを割り当てる_マイナス数値() throws Exception {
		String login_id = "-50";
		
		UsersDao usersDao = new UsersDao();
		
		assertThat(usersDao.find(login_id).getName(), nullValue() );
	}
	
	@Test
	public void usersDaoTest_findメソッドにDBに存在しないIDを割り当てる_英単語() throws Exception {
		String login_id = "task";
		
		UsersDao usersDao = new UsersDao();
		
		assertThat(usersDao.find(login_id).getName(), nullValue() );
	}
	
	@Test
	public void usersDaoTest_findメソッドにDBに存在しないIDを割り当てる_ひらがな() throws Exception {
		String login_id = "あいうえお";
		
		UsersDao usersDao = new UsersDao();
		
		assertThat(usersDao.find(login_id).getName(), nullValue() );
	}
	
	//QuestionsDao
	@Test
	public void questionsDaoTest_findメソッドにDBに存在するIDを割り当てる() throws Exception {
		int id = 1;
		
		QuestionsDao questionsDao = new QuestionsDao();
		
		assertThat(questionsDao.find(id).getQuestion(), is("問題の1問目です。適当なところで") );
	}
	
	@Test
	public void questionsDaoTest_findメソッドにDBに存在しないIDを割り当てる() throws Exception {
		int id = 10;
		
		QuestionsDao questionsDao = new QuestionsDao();
		
		assertThat(questionsDao.find(id).getQuestion(), nullValue() );
	}
	
	//CorrectAnswersDao
	@Test
	public void CorrectAnswerDaoTest_findメソッドにDBに存在するIDを割り当てる() throws Exception {
		int id = 1;
		
		CorrectAnswersDao correctAnswersDao = new CorrectAnswersDao();
		
		assertThat(correctAnswersDao.find(id).getAnswer(), is("みかん") );
	}
	
	@Test
	public void CorrectAnswerDaoTest_findメソッドにDBに存在しないIDを割り当てる() throws Exception {
		int id = 25;
		
		CorrectAnswersDao correctAnswersDao = new CorrectAnswersDao();
		
		assertThat(correctAnswersDao.find(id).getAnswer(), nullValue() );
	}
	
	@Test
	public void CorrectAnswerDaoTest_findByQuetionsIdメソッドにDBに存在するIDを割り当てる() throws Exception {
		int questions_id = 1;
		
		CorrectAnswersDao correctAnswersDao = new CorrectAnswersDao();
		
		ArrayList<CorrectAnswersBean> answerBean = (ArrayList<CorrectAnswersBean>) correctAnswersDao.findByQuetionsId(questions_id);
		
		assertThat(answerBean.get(0).getAnswer(), is("みかん"));
		assertThat(answerBean.get(1).getAnswer(), is("りんご"));
	}
	
	@Test
	public void CorrectAnswerDaoTest_findByQuetionsIdメソッドにDBに存在しないIDを割り当てる() throws Exception {
		int questions_id = 30;
		
		CorrectAnswersDao correctAnswersDao = new CorrectAnswersDao();
		
		//30の値が入ってそれの返り値を確認する。
		ArrayList<CorrectAnswersBean> answerBean = (ArrayList<CorrectAnswersBean>) correctAnswersDao.findByQuetionsId(questions_id);
		
		//テスト対象の値が返ってくるのはArrayList<CorrectAnswersBean> listまで。
		ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
		
		//期待値をArrayList<CorrectAnswersBean> listにする。
		assertThat(answerBean, is(list) );
	}
	
	//HistoriesDao
	@Test
	public void HistoriesDaoTest_findAllメソッドにDBに存在するIDを割り当てる() throws Exception {
		int login_id = 1;
		
		HistoriesDao historiesDao = new HistoriesDao();
		
		ArrayList<HistoriesBean> historiesBean = (ArrayList<HistoriesBean>) historiesDao.findAll(login_id);
		
		assertThat(historiesBean.get(0).getPoint(), is(67));
		assertThat(historiesBean.get(2).getPoint(), is(0));
		assertThat(historiesBean.get(3).getPoint(), is(33));
	}
	
	@Test
	public void HistoriesDaoTest_findAllメソッドにDBに存在しないIDを割り当てる() throws Exception {
		int login_id = 30;
		
		HistoriesDao historiesDao = new HistoriesDao();
		
		ArrayList<HistoriesBean> historiesBean = (ArrayList<HistoriesBean>) historiesDao.findAll(login_id);
		
		List<HistoriesBean> list = new ArrayList<HistoriesBean>();
		
		assertThat(historiesBean, is(list) );
	}
	
}
