package com.suribada.androidbook.chap7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Noh.Jaechun on 2017. 3. 2..
 */
public class DictionaryOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = "DictionaryOpenHelper";
	
	/** 
	 * 단어 테이블
	 * 
	 * 테이블별로 테이블명과 칼럼명을 상수로 많이 쓴다. 
	 */
	public static final class WordTable implements BaseColumns {
		public static final String TABLE_NAME = "word"; // 테이블 이름. DB 명령어에서 사용하는 경우가 많아 상수로 많이 뺀다.
		
		public static final String COLUMN_KEYWORD = "keyword"; // 칼럼명도 자주 사용될 가능성이 있기 때문에 상수로 많이 뺀다.
		public static final String COLUMN_DEFINITION = "definition";
		public static final String COLUMN_PRONUNCIATION = "pronunciation";
		public static final String COLUMN_LAST_UPDATE_TIME = "lastUpdateTime";
	}
	
	/**
	 * 동의어 테이블
	 */
	public static final class SynonymTable implements BaseColumns {
		public static final String TABLE_NAME = "synonym";
		
		public static final String COLUMN_WORD_ID = "wordId"; // 칼럼명도 자주 사용될 가능성이 있기 때문에 상수로 많이 뺀다.
		public static final String COLUMN_SYNONYM_ID = "synonymId";
	}
	
    private static final String DATABASE_NAME = "dictionary.db"; // DB 파일명
    private static final int DATABASE_VERSION = 3; // 현재 사용하는 버전. 처음 DB 생성시에는 version=0이 되어 비교를 하게 된다.

    /**
     * Context는 리소스에 접근하는 경우에는 항상 필요하다.
     */
    public DictionaryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // 세번째 파라미터가 CursorFactory로 보통 null 전달
    }

    /**
     * getReadableDatabase() / getWritableDatabase()로 처음 접근할 때 호출된다. 
     * 처음 생성시에는 onCreate만 호출된다.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
    	Log.d(TAG, "Database onCreate");
    	/* 버전이 1일때는 아래 내용뿐이었음 */
    	/*
        db.execSQL("CREATE TABLE IF NOT EXISTS " // word 테이블 생성 
				+ WordTable.TABLE_NAME + " (" 
				+ WordTable._ID +  " INTEGER PRIMARY KEY, "
        		+ WordTable.COLUMN_KEYWORD + " TEXT, "
		        + WordTable.COLUMN_DEFINITION + " TEXT);");
		*/
    	/* 버전업됨에 따라 최신 사항을 여기에 반영해야만 한다. 
    	 * 기존 버전 사용자 뿐 아니라 처음 이 버전을 사용하는 경우에 해당.
    	 */
        db.execSQL("CREATE TABLE IF NOT EXISTS " // word 테이블 생성 
				+ WordTable.TABLE_NAME + " (" 
				+ WordTable._ID +  " INTEGER PRIMARY KEY, "
        		+ WordTable.COLUMN_KEYWORD + " TEXT, "
		        + WordTable.COLUMN_DEFINITION + " TEXT, "
		        + WordTable.COLUMN_PRONUNCIATION + " TEXT, "
		        + WordTable.COLUMN_LAST_UPDATE_TIME + " TIMESTAMP CURRENT_TIMESTAMP "
		        + ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS " 
				+ SynonymTable.TABLE_NAME 
				+ "( " + SynonymTable._ID + " INTEGER PRIMARY KEY, "
				+ SynonymTable.COLUMN_WORD_ID + " INTEGER, "
		        + SynonymTable.COLUMN_SYNONYM_ID + " INTEGER" 
				+ ");");
    }

    /**
     * DB의 현재 버전과 기존 버전이 차이가 있다면, 
     * 버전에 따른 테이블 변경이나, 데이터 마이그레이션, 필요한 데이터 추가를 진행한다.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	Log.d(TAG, "Database onUpgrade: from " + oldVersion + " to " + newVersion);
        for (int i = oldVersion + 1; i <= newVersion; i++) {
            processUpgrade(db, i);
        }
    }
    
    private void processUpgrade(SQLiteDatabase db, int version) {
    	switch (version) {
    		case 2:
    			/* 단어 테이블에 발음 칼럼이 추가 */
    			db.execSQL("ALTER TABLE " + WordTable.TABLE_NAME + " ADD COLUMN " + WordTable.COLUMN_PRONUNCIATION + " TEXT;");
    			/* 동의어에 대한 기능이 추가되면서 테이블도 추가됨 */
    			db.execSQL("CREATE TABLE IF NOT EXISTS " 
					+ SynonymTable.TABLE_NAME 
					+ "( " + SynonymTable._ID + " INTEGER PRIMARY KEY, "
					+ SynonymTable.COLUMN_WORD_ID + " INTEGER, "
			        + SynonymTable.COLUMN_SYNONYM_ID + " INTEGER" 
					+ ");");
    			break;
    		case 3:
    			/* 단어 테이블에 마지막 업데이트일 칼럼 추가 */
    			db.execSQL("ALTER TABLE " + WordTable.TABLE_NAME + " ADD COLUMN " + WordTable.COLUMN_LAST_UPDATE_TIME 
    					+ " TIMESTAMP CURRENT_TIMESTAMP;");
    			break;
    	}
    }

}