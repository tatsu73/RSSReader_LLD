package tatsu73.rssreader_lld;

import java.io.Serializable;

public class RelateUrl implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private String url;
	private String curl;
	

	//
	public String getTitle() {
		return title;
	}
	public String getUrl() {
		return url;
	}
	public String getCurl() {
		return curl;
	}
	
	//
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setCurl(String curl) {
		this.curl = curl;
	}
	
	
}
