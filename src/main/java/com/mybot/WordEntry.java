package com.mybot;
import java.util.*;
public class WordEntry {
	private final String word;
	private final String phonetic;
	private final List<Meaning> meanings;
	public WordEntry(String word,String phonetic,List<Meaning> meanings) {
		this.word=word;
		this.phonetic=phonetic;
		this.meanings=meanings;
	}
	 public String getWord() {
	        return word;
	    }

	    public String getPhonetic() {
	        return phonetic;
	    }

	    public List<Meaning> getMeanings() {
	        return meanings;
	    }
}
