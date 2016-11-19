package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;
import utils.Jdbc;

import java.io.*;
import java.util.Map;

/**
 * @author code4crafer@gmail.com
 */
public class OneFilePipeline extends FilePersistentBase implements Pipeline {

	public static int cnt = 0;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private PrintWriter printWriter;

	/**
	 * create a FilePipeline with default path"/data/webmagic/"
	 */
	public OneFilePipeline() throws FileNotFoundException, UnsupportedEncodingException {
		this("/data/webmagic/");
		// this("H:\php\wamp\wamp\www/_webmagicdata/");
	}

	public OneFilePipeline(String path) throws FileNotFoundException, UnsupportedEncodingException {
		setPath(path);
		printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFile(path)), "UTF-8"));
	}

	@Override
	public synchronized void process(ResultItems resultItems, Task task) {
		printWriter.println("url:\t" + resultItems.getRequest().getUrl());
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			if (entry.getValue() instanceof Iterable) {
				Iterable value = (Iterable) entry.getValue();
				printWriter.println(entry.getKey() + ":");
				for (Object o : value) {
					printWriter.println(o);
				}
			} else {
				new Jdbc();

				printWriter.println(entry.getKey() + ":\t" + entry.getValue());
				// �ȱ��� �ٴ�����
				Jdbc.saveToMysql(Jdbc.parseData(entry.getValue()));
			}
		}
		printWriter.flush();
	}

	@Override
	public java.io.File getFile(java.lang.String fullName) {
		cnt++;
		System.out.println("fullname=" + fullName + cnt);
		return new File(fullName + cnt);

	}
}