package tatsu73.rssreader_lld;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RSSListAdapter extends ArrayAdapter<Item>{
	private LayoutInflater inflater;
	private TextView tvTitle;
	private TextView tvContent;
	
	public RSSListAdapter(Context context, List<Item> objects) {
		super(context, 0, objects);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (convertView == null) {
			view = inflater.inflate(R.layout.item_row, null);
		}

		// 現在参照しているリストの位置からItemを取得する
		Item item = this.getItem(position);
		if (item != null) {
			//nullじゃなければTextViewにセットする
			String title = item.getTitle().toString();
			tvTitle = (TextView) view.findViewById(R.id.item_title);
			tvTitle.setText(title);
			
			String content = item.getContent().toString();
			tvContent = (TextView) view.findViewById(R.id.item_descr);
			tvContent.setText(content);				
			
			/*
			String content = item.getGuid().toString();
			tvContent = (TextView) view.findViewById(R.id.item_descr);
			tvContent.setText(content);		
			*/
		}
		return view;
	}
}
