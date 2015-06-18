package tatsu73.rssreader_lld;

import java.io.Serializable;

public class DetailItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private String dTitle;
	private String dContent;
	private RelateUrl[] Relate;
	
	public DetailItem(){
		dTitle = "";
		dContent = "";	
	}
	
	//
	public String getdTitle() {
		return dTitle;
	}
	public String getdContent() {
		return dContent;
	}
	public RelateUrl[] getRealte(){
		return Relate;
	}
	
	
	//
	public void setdTitle(String dTitle) {
		this.dTitle = dTitle;
	}
	public void setdContent(String dContent) {
		this.dContent = dContent;
	}
	public void setRelate(RelateUrl[] relate){
		this.Relate = relate;
	}
	
	
}
