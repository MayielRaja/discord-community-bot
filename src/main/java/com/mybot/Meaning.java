package com.mybot;
import java.util.*;
public class Meaning {
	private final String partOfSpeech;
	private final List<Definition> definitions;
	public Meaning(String partOfSpeech,List<Definition> definitions) {
		this.partOfSpeech=partOfSpeech;
		this.definitions=definitions;
	}
	public String getPartOfSpeech() {
		return partOfSpeech;
	}
	public List<Definition> getDefinitions(){
		return definitions;
	}
}
