package com.suribada.androidbook.chap7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.suribada.androidbook.chap7.DictionaryOpenHelper.WordTable;

/**
 * 일반적으로 DAO 패턴을 적용.
 * 컴포넌트에서는 DAO를 통해서 DB에 접근한다.
 *
 *  Created by Noh.Jaechun on 2017. 3. 2..
 */
public class WordDAO {
	
	private DictionaryOpenHelper dbHelper;
	
	public WordDAO(Context context) {
		dbHelper = new DictionaryOpenHelper(context);
	}
	
	public List<Word> selectAll() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(WordTable.TABLE_NAME,
				null, // columns. null이면 모든 칼럼 * 조회
				null, // selection. where절에 들어가는 내용. 예) "_id=?" 
				null, // selectionArgs. selection의 ?에 들어가는 값. 문자열 배열이 들어간다.
				null, // groupBy 
				null, // having 
				WordTable.COLUMN_KEYWORD // orderBy
				);
		if (cursor.getCount() == 0) {
			return Collections.emptyList();
		}
		List<Word> words = new ArrayList<Word>();
		if (cursor.moveToFirst()) { //  Cursor 첫번째 행으로 이동한다.
			do {
				Word word = new Word();
				/* Cursor에는 getDouble, getFloat, getInt, getLong, getShort, getBlob 메소드가 있다. */
				word.id = cursor.getLong(cursor.getColumnIndex(WordTable._ID)); // 인덱스로 가져와야만 한다. 인덱스를 안다면 cursor.getLong(1)로 해도 된다.
				word.keyword = cursor.getString(cursor.getColumnIndex(WordTable.COLUMN_KEYWORD));
				word.definition = cursor.getString(cursor.getColumnIndex(WordTable.COLUMN_DEFINITION));
				words.add(word);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return words;
	}
	
	public int getCount() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(WordTable.TABLE_NAME, 
				new String[] {"COUNT(*)"}, // 조회하고자 하는 칼럼을 문자열 배열로 전달한다.
				null, 
				null, 
				null, 
				null, 
				null
				);
		cursor.moveToNext();
		int count = cursor.getInt(0);
		cursor.close();
		return count;
	}
	
	public Word getWord(long id) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(WordTable.TABLE_NAME, 
				null, // 조회하고자 하는 칼럼을 문자열 배열로 전달한다.
				"_id=?", 
				new String[] {String.valueOf(id)}, 
				null, 
				null, 
				null
				);
		if (cursor.getCount() == 0) {
			return null;
		}
		cursor.moveToNext();
		Word word = new Word();
		/* Cursor에는 getDouble, getFloat, getInt, getLong, getShort, getBlob 메소드가 있다. */
		word.id = cursor.getLong(cursor.getColumnIndex(WordTable._ID)); // 인덱스로 가져와야만 한다. 인덱스를 안다면 cursor.getLong(1)로 해도 된다.
		word.keyword = cursor.getString(cursor.getColumnIndex(WordTable.COLUMN_KEYWORD));
		word.definition = cursor.getString(cursor.getColumnIndex(WordTable.COLUMN_DEFINITION));
		cursor.close();
		return word;
	}
	
	public long insert(Word word) {
		SQLiteDatabase db = dbHelper.getWritableDatabase(); // 쓰기 용도이기 때문에 getWritableDatabase 메소드 적용
		ContentValues values = new ContentValues(); // 칼럼에 매핑하는 값을 넣을 때 ContentValues를 사용한다.
		values.put(WordTable.COLUMN_KEYWORD, word.keyword);
		values.put(WordTable.COLUMN_DEFINITION, word.definition);
		values.put(WordTable.COLUMN_PRONUNCIATION, word.pronunciation);
		values.put(WordTable.COLUMN_LAST_UPDATE_TIME, System.currentTimeMillis());
		
		/* insert 후 rowId 리턴 */
		return db.insert(WordTable.TABLE_NAME, 
				null, // 두번째는 대부분 null을 넣는다. nullColumnHack은 ContentValues가 비어있을 때에 대한 대책
				values
				); 
	}
	
	public int update(Word word) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues(); // 칼럼에 매핑하는 값을 넣을 때 ContentValues를 사용한다.
		values.put(WordTable.COLUMN_KEYWORD, word.keyword);
		values.put(WordTable.COLUMN_DEFINITION, word.definition);
		values.put(WordTable.COLUMN_LAST_UPDATE_TIME, System.currentTimeMillis());
		
		/* update한 row 갯수 리턴 */
		return db.update(WordTable.TABLE_NAME, 
				values, 
				WordTable._ID + "=?", 
				new String[] {String.valueOf(word.id)}
				);
	}
	
	public int delete(long id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		/* delete한 row 갯수 리턴 */
		return db.delete(WordTable.TABLE_NAME, 
				WordTable._ID + "=?", 
				new String[] {String.valueOf(id)} // 문자열 배열이 들어가므로, 다른 타입이라면 String.valueOf로 감싸서 배열에 넣는다.
				);
	}
	
}
