package tatsu73.rssreader_lld;

import java.util.ArrayList;
import android.app.Application;

public class DataManager extends Application{
	private ArrayList<Item> list = new ArrayList<>();
	private DetailItem CurrentItem = new DetailItem();
	
	public ArrayList<Item> get(){
		return list;
	}
	public DetailItem getCurrent(){
		return CurrentItem;
	}
	
	
	public void set(ArrayList<Item> itemList){
		list = itemList;
	}
	public void setCurrent(DetailItem citem) {
		this.CurrentItem = citem;
	}
}
