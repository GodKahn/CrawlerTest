package com.kahn.crawler;

public class Crawler_Ver1 {
	
	private Grabber frame;
	
	Crawler_Ver1(Grabber frame){
		this.frame = frame;
	}
	
	void getPic(String url){
		ReadPage reader = new ReadPage(frame);
		reader.readPage(url);
	}
}
