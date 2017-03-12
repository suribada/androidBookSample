package com.suribada.androidbook.chap7;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.suribada.androidbook.chap7.DictionaryOpenHelper.WordTable;

/**
 *  Created by Noh.Jaechun on 2017. 3. 2..
 */
public class WordTransactionDAO {
	
	private DictionaryOpenHelper dbHelper;
	
	public WordTransactionDAO(Context context) {
		dbHelper = new DictionaryOpenHelper(context);
	}
	
	public void bulkInsert(List<Word> words) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.beginTransaction(); // 트랜잭션 시작
		try {
			for (Word word : words) { // 단어 리스트 순회
				ContentValues values = new ContentValues();
				values.put(WordTable.COLUMN_KEYWORD, word.keyword);
				values.put(WordTable.COLUMN_DEFINITION, word.definition);
				values.put(WordTable.COLUMN_PRONUNCIATION, word.pronunciation);
				values.put(WordTable.COLUMN_LAST_UPDATE_TIME, System.currentTimeMillis());
				
				db.insert(WordTable.TABLE_NAME, null, values); 
			}
			db.setTransactionSuccessful(); // 정상적으로 완료됐다고 marking
		} finally {
			db.endTransaction(); // 정상적으로 완료됐으면 commit, 아니면 rollback
		}
	}
	
}
