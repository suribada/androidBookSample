package com.suribada.androidbook.chap7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.naver.android.sample.R;

public class DictionaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.four_buttons);
	}
	
	public void onClickButton1(View view) { 
		/* 
		 * 단순한 조회인데도 시간이 소요가 된다. 
		 * thread 작업으로 변경하는 것을 고려해야 한다.
		 */
		WordDAO wordDAO = new WordDAO(this);
		List<Word> words = wordDAO.selectAll();
		Toast.makeText(this, "words=" + words, Toast.LENGTH_LONG).show();
	}
	
	public void onClickButton2(View view) {
		Word word = new Word();
		word.keyword = "nut";
		word.definition = "땅콩";
		word.pronunciation = "넛";
		
		WordDAO wordDAO = new WordDAO(this);
		long rowId = wordDAO.insert(word); // 데이터 추가
		
		Toast.makeText(this, "insert RowId=" + rowId + "/count=" + wordDAO.getCount(), Toast.LENGTH_LONG).show();
		
		word.id = rowId;
		word.definition = "땅콩이라네";
		wordDAO.update(word); // 데이터 수
		
		Toast.makeText(this, "current word=" + word, Toast.LENGTH_LONG).show();
		
		wordDAO.delete(rowId); // 데이터 삭제

		Toast.makeText(this, "current count=" + wordDAO.getCount(), Toast.LENGTH_LONG).show();
	}
	
	public void onClickButton3(View view) {
		WordTransactionDAO wordDAO = new WordTransactionDAO(this);
		
		ArrayList<Word> words = new ArrayList<Word>();
		for (int i = 0; i < 100; i++) {
			words.add(makeSomeWord());
		}
		wordDAO.bulkInsert(words);
	}

	private Word makeSomeWord() {
		Word word = new Word();
		Random random = new Random();
		int num = random.nextInt(1024);
		word.keyword = "apple" + num;
		word.definition = "사과" + num;
		word.pronunciation = "애쁠" + num;
		return word;
	}
	
	public void onClickButton4(View view) {
		WordDAO wordDAO = new WordDAO(this);
		Toast.makeText(this, "current count=" + wordDAO.getCount(), Toast.LENGTH_LONG).show();
	}
	
}
