package com.kahn.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

public class GetPicture implements Runnable {

	private String dirname;
	private Iterator<String> iterator;

	GetPicture(String dirname, Iterator<String> iterator) {
		this.dirname = dirname;
		this.iterator = iterator;
	}

	void getPictures() {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmssSS");

		try {
			while (iterator.hasNext()) {
				String keyword;
				String filename;
				File file;
				URL myURL;

				synchronized (this) {
					Calendar cal = Calendar.getInstance();
					keyword = iterator.next().toString();
					myURL = new URL(keyword);
					iterator.remove();

					filename = formatter.format(cal.getTimeInMillis()) + keyword.substring(keyword.lastIndexOf("."));
					file = new File("./" + dirname, filename);
				}

				HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
				conn.addRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				InputStream is = conn.getInputStream();
				FileOutputStream fos = new FileOutputStream(file);

				int length = 0;
				int bytes = 1024;
				byte[] b = new byte[bytes * 4];
				while ((length = is.read(b)) != -1) {
					fos.write(b, 0, length);
				}

				fos.flush();
				fos.close();
				is.close();

				System.out.println(Thread.currentThread().getName() + "Get" + filename + " Done!");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		getPictures();
	}
}