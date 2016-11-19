package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.A_weibo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 
 * @author Wheee
 *
 */
public class Jdbc {
	static String textstr = "";

	static String str = "��ȡ ����΢������\\n   <div class=\\\"WB_text W_f14\\\" node-type=\\\"feed_list_content\\\" nick-name=\\\"DwyaneWade\\\">\\n        Game day! - ������!   <\\/div>\\n   ";
	private static String REGEX_TEXT = "(?<=(<div\\Wclass=\\\\\"WB_text\\WW_f14\\\\\"\\Wnode-type=\\\\\"feed_list_content\\\\\"\\W((>\\\\\\w)|(.{10}\\\\\"\\w{0,50}\\\\\">\\\\n)))).*?(?=<(\\\\\\/div>))";

	// JDBC driver name and database URL
	final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost/weibodata";

	// Database credentials
	final static String USER = "root";
	final static String PASS = "";
	static String A_WEIBO_NODE = "WB_cardwrap WB_feed_type S_bg2";

	public static List<A_weibo> parseData(Object htmlOrjson) {
		A_weibo a_weibo = new A_weibo();
		List<A_weibo> listaweibo = new ArrayList<A_weibo>();

		String DATAKIND = (htmlOrjson instanceof Html ? "HTML" : "JSON");
		if (htmlOrjson instanceof Html || htmlOrjson instanceof Json) {
			System.out.println("HTML or Json");
			String strData = htmlOrjson.toString();

			// ��ָ��ģʽ���ַ�������
			String line = strData.toString();
			String pattern = REGEX_TEXT;

			// ���� Pattern ����
			Pattern r = Pattern.compile(pattern, Pattern.MULTILINE | Pattern.DOTALL);

			// ���ڴ��� matcher ����
			Matcher m = r.matcher(line);
			int cnt = 0;
			while (m.find()) {
				cnt++;
				// System.out.println("Found value0:" + m.group(0));
				// ȥ����β�ո�
				String cntnt_text = (m.group(0).replaceAll("^\\s*", "")).replaceAll("(\\s*)$", "");

				System.out.println("#" + cnt + " Content_text:" + cntnt_text);

				// String cntnt_chn = cntnt_text.replaceAll("^[A-Za-z0-9\\?!\\.
				// ]*", "");
				Pattern rr = Pattern.compile("^([A-Za-z0-9'!?,.;\\(\\) ]|[\u2018\u2019])*");
				Matcher mm = rr.matcher(cntnt_text);
				String cntnt_eng = mm.find() ? mm.group(0) : " ";
				listaweibo.add(new A_weibo(-1, "", "", "", "", "", "", "", cntnt_eng, -1, -1, -1, "", ""));
				System.out.println("-" + cnt + "  Content_eng:" + cntnt_eng);

			}
			System.out.println("ץȡ����list.size=" + listaweibo.size());
			if (0 == cnt) {
				System.out.println("NO MATCH");
			}

		} else {
			System.out.println("doubushi");
		}
		return listaweibo;
	}// parseData_end

	/**
	 * 
	 * @param htmlOrjson
	 * @return
	 */
	public static int saveToMysql(List<A_weibo> listaweibo) {

		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			// System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("�������ݿ�-�ɹ�");

			// STEP 4: Execute a query
			// System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			for (A_weibo aweibo : listaweibo) {
				System.out.println("��������-" + tidyString(aweibo.getCntnt_text()));

				String sql = "INSERT INTO cntnttext " + "VALUES (NULL,'" + tidyString(aweibo.getCntnt_text()) + "');";
				stmt.executeUpdate(sql);
			}
			System.out.println(listaweibo.size() + "�����ݲ���-�ɹ�");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return 0;
	}// saveToMysql_end

	/**
	 * 
	 * @param str
	 *            �������ַ���
	 * @return
	 */
	static String tidyString(String str) {
		// ���õ����ű�ʾһ���ַ�����ʱ������ַ����г��ֵ����ţ�Ҫ�����������Ų��ܱ�ʾһ�������ţ��� wang'xiaowei,�õ����ű�ʾ
		// 'wang''xiaowei'������˫���ű�ʾһ���ַ�����ʱ������ַ����г���˫���ţ�Ҫ������˫���Ų��ܱ�ʾһ��˫���ţ���
		// wang"xiaowei,��˫���ű�ʾ"wang""xiaowei"
		
		// mysql ��������ʱ ʵ�����ݵ� '��������ת����java�ַ��� \'\'��Ҳ����'������׼��������������''�� �������ȷ
		str = str.replace("��", "\'\'").replace("��", "\'\'").replace("\'", "\'\'").replace("\"", "\\\"");

		return str;
	}
}
