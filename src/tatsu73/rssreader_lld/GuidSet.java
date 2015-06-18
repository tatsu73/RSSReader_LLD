package tatsu73.rssreader_lld;

public class GuidSet {
	private String currentGuid = "";
	private final String main = "http://news.livedoor.com/topics/rss/top.xml";
	private final String internal = "http://news.livedoor.com/topics/rss/dom.xml";
	private final String foreign= "http://news.livedoor.com/topics/rss/int.xml";
	private final String it_economy= "http://news.livedoor.com/topics/rss/eco.xml";
	private final String entertainment = "http://news.livedoor.com/topics/rss/ent.xml";
	private final String sport = "http://news.livedoor.com/topics/rss/spo.xml";
	private final String movie= "http://news.livedoor.com/rss/summary/52.xml";
	private final String gourmet= "http://news.livedoor.com/topics/rss/gourmet.xml";
	private final String women= "http://news.livedoor.com/topics/rss/love.xml";
	private final String trend= "http://news.livedoor.com/topics/rss/trend.xml";
	
	//
	public GuidSet() {
		currentGuid = main;
	}
	public void setCurrent(String currentUrl){
		currentGuid = currentUrl;
	}
	
	//
	public String getCurrent() {
		return currentGuid;
	}
	public String getMain() {
		return main;
	}
	public String getInternal() {
		return internal;
	}
	public String getForeign() {
		return foreign;
	}
	public String getItEconomy() {
		return it_economy;
	}
	public String getEntertainment() {
		return entertainment;
	}
	public String getSport() {
		return sport;
	}
	public String getMovie() {
		return movie;
	}
	public String getGourmet() {
		return gourmet;
	}
	public String getWomen() {
		return women;
	}
	public String getTrend() {
		return trend;
	}
}
