
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import us.codecraft.webmagic.downloader.Downloader;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author code4crafter@gmail.com <br>
 */
public class SinaBlogProcessor implements PageProcessor {
	public static String PATHNAME = "H:/php/wamp/wamp/www/_webmagicdata/weibodata_jdbc/jdbc_weibodata_page";
	private static long userNum = 0;
	private static String userId = "";

	private Site siteold = Site.me().setSleepTime(3000);// .setUserAgent(
	// "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like
	// Gecko) Chrome/45.0.2454.101 Safari/537.36");
	private volatile static int maini = 0;
	private Site site = new Site().setRetryTimes(3).setSleepTime(2000).setTimeOut(10000)
			// 添加cookie之前一定要先设置主机地址，否则cookie信息不生效
			.setDomain(".weibo.com")

			// 添加抓包获取的cookie信息

			.addCookie("Apache", "6047605616040.527.1448080352314")
			.addCookie("SCF",
					"AlgxpR2USqF6Fji7UTnm3WPAXSxPvaTHOieYqWN7MyJy1v5yxV2Q-hY2tE5UNVwsFxBnzMn5WcgiNsH1rjhNTag.")
			.addCookie("SINAGLOBAL", "9783050660043.955.1444436429273").addCookie("SSOLoginState", "1479121602")
			.addCookie("SUB", "_2AkMvdZnXdcNhrAZSkPwdyGLlbIpH-jzEiebBAn7uJhMyAxh87lggqSWzzXgljRwFaadrMYlAstBpNdzWwA..")

			.addCookie("SUBP", "0033WrSXqPxfM72wWs9jqgMF55529P9D9WWl1Rubc6Ms11SFjwUxOq6g5JpVF02fSo-E1KqEeK.p")
			.addCookie("SUHB", "079jAltW0-55k3").addCookie("TC-V5-G0", "c427b4f7dad4c026ba2b0431d93d839e")
			.addCookie("ULV", "1448080352379:5:3:2:6047605616040.527.1448080352314:1447737055439")
			.addCookie("UOR", "www.doc88.com,widget.weibo.com,login.sina.com.cn")

			.addCookie("WBtopGlobal_register_version", "8a840560e41b693d")
			.addCookie("YF-Page-G0", "0acee381afd48776ab7a56bd67c2e7ac")

			.addCookie("YF-Ugrow-G0", "3a02f95fa8b3c9dc73c74bc9f2ca4fc6")

			.addCookie("YF-V5-G0", "82f55bdb504a04aef59e3e99f6aad4ca")

			.addCookie("__gads", "ID=d91a3b6697e9a922:T=1464099918:S=ALNI_MZqz9VgWhRKVtCwoCF64EvtkXAOUg")
			.addCookie("_ga", "GA1.2.194882159.1464099827").addCookie("_s_tentry", "blog.csdn.net")
			.addCookie("appkey", "").addCookie("un", "15652962191")
			.addCookie("login_sid_t", "3095e286b4630e49b1722a9acc4bdc71")

			// 添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的
			.addHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.245")
			.addHeader("Accept", "*/*").addHeader("Accept-Encoding", "gzip, deflate, sdch")
			.addHeader("Accept-Language", "zh-CN,zh;q=0.8").addHeader("Connection", "keep-alive").addHeader("Referer",
					"http://weibo.com/p/1003062264358493/home?is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1&page=1#feedtop");

	@Override
	public void process(Page page) {
		String str = "" + page.getUrl().toString();
		// System.out.println("#1");
		if (str.indexOf("#feedtop") >= 0) {
			// System.out.println("#2");
			if (page.getHtml().toString().indexOf("抱歉，你访问的页面地址有误，或者该页面不存在") < 0
					&& page.getHtml().toString().indexOf("请检查输入的网址是否正确") < 0
					&& page.getHtml().toString().indexOf("网络繁忙") < 0
					&& page.getHtml().toString().indexOf("请稍后再试") < 0) {
				// System.out.println("#3");

				str = str.substring(0, str.indexOf("#feedtop"));
				str = str.substring(str.indexOf("&page=") + 6, str.length());
				int cnt = 0;
				int pagenum = Integer.parseInt(str);
				System.out.println("pagenum=" + pagenum);
				// 获取页数
				String xialaURL1 = "http://weibo.com/p/aj/v6/mblog/mbloglist?ajwvr=6&domain=100505&is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1"
						+ "&page=" + pagenum + "&pagebar=0" + "&pl_name=Pl_Official_MyProfileFeed__23&id=" + userId
						+ "&script_uri=/p/" + userId + "/home&feed_type=0" + "&pre_page=" + pagenum
						+ "&domain_op=100505&__rnd=1479123380183";
				String xialaURL2 = "http://weibo.com/p/aj/v6/mblog/mbloglist?ajwvr=6&domain=100505&is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1"
						+ "&page=" + pagenum + "&pagebar=1" + "&pl_name=Pl_Official_MyProfileFeed__23&id=" + userId
						+ "&script_uri=/p/" + userId + "/home&feed_type=0" + "&pre_page=" + pagenum
						+ "&domain_op=100505&__rnd=1479123380183";
				List<String> listurl = new ArrayList<String>();
				listurl.add(xialaURL1);
				listurl.add(xialaURL2);

				page.addTargetRequests(listurl);

				page.putField("[Chushiyemian]", page.getHtml());
			} else {
				System.out.println("userId=" + userId + "页面不存在  404");
				// userId++;
				maini = -1;
			}

		} else {
			// 两个下拉刷新页面
			page.putField("[Xialashuaxin]", page.getJson());
			if (page.getJson().toString().indexOf("WB_feed_detail clearfix") < 0) {
				maini = -1;
				System.out.println("userId=" + userId + "到达最后一页");
			}

		}

	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		// http://weibo.com/p/1005055317970558/home?
		// http://weibo.com/kevindurant?is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1&page=2#feedtop
		// http://weibo.com/p/1003061735538085/home?from=page_100306_profile&wvr=6&mod=data&is_all=1#place
		for (userNum = 93; userNum < 99; userNum++) {
			userId = "10030622643584" + userNum;

			PATHNAME = "H:/php/wamp/wamp/www/_webmagicdata/weibodata_jdbc/" + userId + "jdbc_weibodata_page";
			OneFilePipeline.cnt = 0;// 置0
			String pageURLs = "http://weibo.com/p/" + userId
					+ "/home?is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1&page=";// 1#feedtop";
			String pageURLe = "#feedtop";
			String xialaPagebars = "";
			for (maini = 1; maini <= 999 && maini > 0; maini++) {

				try {
					Spider.create(new SinaBlogProcessor())
							// .addUrl("http://weibo.com/languageexchange?refer_flag=1001030201_&is_all=1")
							// .addPipeline(new
							// FilePipeline("H:/php/wamp/wamp/www/_webmagicdata"))
							.addPipeline(new OneFilePipeline(PATHNAME)).addUrl(pageURLs + maini + pageURLe).thread(50)
							.run();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			System.out.println("userId=" + userId + "处理完毕/n传送至watson分析并返回显示");
		}
	}

	// @Override
	// public void process(ResultItems arg0, Task arg1) {
	// // TODO 自动生成的方法存根
	// System.out.println("process 2参数函数"+arg0.get("content1"));
	//
	// }1005053610038332

}

/////////////////////////////////
// 获取页数
// String xialaURL1 =
///////////////////////////////// "http://weibo.com/p/aj/v6/mblog/mbloglist?ajwvr=6&domain=100505&is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1"
// + "&page=" + pagenum + "&pagebar=0"
// +
///////////////////////////////// "&pl_name=Pl_Official_MyProfileFeed__23&id=1005055317970558&script_uri=/p/1005055317970558/home&feed_type=0"
// + "&pre_page=" + pagenum + "&domain_op=100505&__rnd=1479123380183";
// String xialaURL2 =
///////////////////////////////// "http://weibo.com/p/aj/v6/mblog/mbloglist?ajwvr=6&domain=100505&is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1"
// + "&page=" + pagenum + "&pagebar=1"
// +
///////////////////////////////// "&pl_name=Pl_Official_MyProfileFeed__23&id=1005055317970558&script_uri=/p/1005055317970558/home&feed_type=0"
// + "&pre_page=" + pagenum + "&domain_op=100505&__rnd=1479123380183";