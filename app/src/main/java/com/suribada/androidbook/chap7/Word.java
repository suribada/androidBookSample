package com.suribada.androidbook.chap7;

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
