package tatsu73.rssreader_lld;

import java.io.Serializable;

public class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private String guid;
	private DetailItem chash;
	

	public Item(){
		title = "";
		content = "";
		guid = "";
		chash = null;
	}
	
	
	//getter
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}	
	public String getGuid() {
		return guid;
	}
	public DetailItem getChash() {
		return chash;
	}
	
	//setter
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public void setChash(DetailItem chash) {
		this.chash = chash;
	}
}
