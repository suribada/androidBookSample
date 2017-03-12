package com.suribada.androidbook.chap7;

/**
 * Created by Noh.Jaechun on 2017. 3. 2..
 */
public class Word {

	public long id;
	public String keyword;
	public String definition;
	public String pronunciation;
	
	@Override
	public String toString() {
		return "id=" + id
				+ ",keyword=" + keyword
				+ ",definition=" + definition
				+ ",pronunciation=" + pronunciation;
	}
}
