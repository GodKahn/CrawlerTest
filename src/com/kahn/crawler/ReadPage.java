package com.kahn.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

public class ReadPage {

	private String dirname;
	private Grabber frame;
	
	ReadPage(Grabber frame){
		this.frame = frame;
	}

	void readPage(String url) {
		HashSet<String> jpgurl = new HashSet<String>();
		HashSet<String> nojpgurl = new HashSet<String>();
		Iterator<String> iterator;

		try {
			URL myURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
			conn.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			String keyword = "(.*).(jpg|jepg|png)(.*)";
			String title = "<title>(.*)</title>";

			String pattern1 = "(.*)href=\"http(.*)i.imgur.com/[a-zA-Z0-9]*" + keyword + "\"(.*)";
			String pattern2 = "(.*)href=\"http://imgur.com/([a-zA-Z0-9]{7})\"(.*)";

			String pattern = "(.*)span class=\"f2\"(.*)";
			String pattern3 = "(.*)application/ld+json(.*)";

			String s;
			while ((s = br.readLine()) != null) {

				if (s.matches(title)) {
					dirname = s.substring("<title>".length(), s.indexOf(" - "));
					File dir = new File("./" + dirname);
					if (!dir.exists()) {
						dir.mkdir();
					} else {
						dirname += "new";
						dir = new File("./" + dirname);
						dir.mkdir();
					}
				}

				if (s.matches(pattern1)) {
					String ss = s.substring(s.indexOf("\"http"), s.indexOf("\"", s.indexOf("\"http") + 1));
					String URL = ss.substring(ss.indexOf("http"));
					if (URL != url) {
						System.out.println(URL + " P1");
						jpgurl.add(URL);
					}

				} else if (s.matches(pattern2)) {
					String ss = s.substring(s.indexOf("\"http"), s.indexOf("\"", s.indexOf("\"http") + 1));
					String URL = ss.substring(ss.indexOf("http"));
					if (URL != url) {
						System.out.println(URL + " P2");
						nojpgurl.add(URL);
					}
				}

				if (s.matches(pattern) || s.matches(pattern3)) {
					System.out.println("======================================");
					break;
				}
			}

			if (!jpgurl.isEmpty()) {
				iterator = jpgurl.iterator();
				GetPicture scanner = new GetPicture(dirname, iterator);
				if (jpgurl.size() > 10) {
					Thread th1 = new Thread(scanner);
					Thread th2 = new Thread(scanner);
					Thread th3 = new Thread(scanner);
					th1.start();
					th2.start();
					th3.start();
					try {
						th1.join();
						th2.join();
						th3.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					scanner.getPictures();
				}

			} else if (!nojpgurl.isEmpty()) {
				iterator = nojpgurl.iterator();
				while (iterator.hasNext()) {
					readPage(iterator.next().toString());
				}
			}

		} catch (MalformedURLException e) {
			Notice2 notice = new Notice2();
			notice.newwindow(frame);
		} catch (IOException e) {
			Notice2 notice = new Notice2();
			notice.newwindow(frame);
		} finally {
			jpgurl.clear();
			nojpgurl.clear();
		}
	}

}
