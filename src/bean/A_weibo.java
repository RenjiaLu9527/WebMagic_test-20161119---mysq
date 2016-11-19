package bean;

public class A_weibo {
	/**
	 * @param id
	 * @param usrname
	 * @param unq_id
	 * @param pblshtime
	 * @param frmphone
	 * @param cntnt_all
	 * @param cntnt_imgurl
	 * @param cntnt_videourl
	 * @param cntnt_text
	 * @param nmbrof_liked
	 * @param nmbrof_comment
	 * @param nmbrof_transmit
	 * @param unknow_1
	 * @param unknow_2
	 */
	public A_weibo(int id, String usrname, String unq_id, String pblshtime, String frmphone, String cntnt_all,
			String cntnt_imgurl, String cntnt_videourl, String cntnt_text, int nmbrof_liked, int nmbrof_comment,
			int nmbrof_transmit, String unknow_1, String unknow_2) {
		super();
		this.id = id;// 1
		this.usrname = usrname;
		this.unq_id = unq_id;// 3
		this.pblshtime = pblshtime;
		this.frmphone = frmphone;// 5
		this.cntnt_all = cntnt_all;
		this.cntnt_imgurl = cntnt_imgurl;// 7
		this.cntnt_videourl = cntnt_videourl;
		this.cntnt_text = cntnt_text;// 9
		this.nmbrof_liked = nmbrof_liked;
		this.nmbrof_comment = nmbrof_comment;// 11
		this.nmbrof_transmit = nmbrof_transmit;
		this.unknow_1 = unknow_1;// 13
		this.unknow_2 = unknow_2;// 14
	}

	public A_weibo() {

	}

	private int id;

	private String usrname;

	private String unq_id;

	private String pblshtime;

	private String frmphone;

	private String cntnt_all;

	private String cntnt_imgurl;

	private String cntnt_videourl;

	private String cntnt_text;

	private int nmbrof_liked;

	private int nmbrof_comment;

	private int nmbrof_transmit;

	private String unknow_1;

	private String unknow_2;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public String getUsrname() {
		return this.usrname;
	}

	public void setUnq_id(String unq_id) {
		this.unq_id = unq_id;
	}

	public String getUnq_id() {
		return this.unq_id;
	}

	public void setPblshtime(String pblshtime) {
		this.pblshtime = pblshtime;
	}

	public String getPblshtime() {
		return this.pblshtime;
	}

	public void setFrmphone(String frmphone) {
		this.frmphone = frmphone;
	}

	public String getFrmphone() {
		return this.frmphone;
	}

	public void setCntnt_all(String cntnt_all) {
		this.cntnt_all = cntnt_all;
	}

	public String getCntnt_all() {
		return this.cntnt_all;
	}

	public void setCntnt_imgurl(String cntnt_imgurl) {
		this.cntnt_imgurl = cntnt_imgurl;
	}

	public String getCntnt_imgurl() {
		return this.cntnt_imgurl;
	}

	public void setCntnt_videourl(String cntnt_videourl) {
		this.cntnt_videourl = cntnt_videourl;
	}

	public String getCntnt_videourl() {
		return this.cntnt_videourl;
	}

	public void setCntnt_text(String cntnt_text) {
		this.cntnt_text = cntnt_text;
	}

	public String getCntnt_text() {
		return this.cntnt_text;
	}

	public void setNmbrof_liked(int nmbrof_liked) {
		this.nmbrof_liked = nmbrof_liked;
	}

	public int getNmbrof_liked() {
		return this.nmbrof_liked;
	}

	public void setNmbrof_comment(int nmbrof_comment) {
		this.nmbrof_comment = nmbrof_comment;
	}

	public int getNmbrof_comment() {
		return this.nmbrof_comment;
	}

	public void setNmbrof_transmit(int nmbrof_transmit) {
		this.nmbrof_transmit = nmbrof_transmit;
	}

	public int getNmbrof_transmit() {
		return this.nmbrof_transmit;
	}

	public String getUnknow_1() {
		return unknow_1;
	}

	public void setUnknow_1(String unknow_1) {
		this.unknow_1 = unknow_1;
	}

	public String getUnknow_2() {
		return unknow_2;
	}

	public void setUnknow_2(String unknow_2) {
		this.unknow_2 = unknow_2;
	}

}