package tatsu73.rssreader_lld;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Xml;

public class RSSParser extends AsyncTask<String, Integer, RSSListAdapter>{
	private MainActivity Activity;
	private RSSListAdapter Adapter;
	private ProgressDialog ProgressDialog;
	
	public RSSParser(MainActivity activity, RSSListAdapter adapter) {
		Activity = activity;
		Adapter = adapter;
	}
	
	@Override
	protected void onPreExecute() {
		ProgressDialog = new ProgressDialog(Activity);
		ProgressDialog.setMessage("しばし待たれよ");
		ProgressDialog.show();
	}
	
	@Override
	protected RSSListAdapter doInBackground(String... params) {
		RSSListAdapter result = null;
		try {
			// HTTP経由でアクセスし、InputStreamを取得する
			URL url = new URL(params[0]);
			InputStream is = url.openConnection().getInputStream();
			result = parseXml(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ここで返した値は、onPostExecuteメソッドの引数として渡される
		return result;
	}
	
	@Override
	protected void onPostExecute(RSSListAdapter result) {
		ProgressDialog.dismiss();
		Activity.setListAdapter(result);
	}
	
	public RSSListAdapter parseXml(InputStream is) throws IOException, XmlPullParserException {
		String reg = "<li>.+?</li>";
		
		Pattern p = Pattern.compile(reg,Pattern.DOTALL);
		Matcher m;
		
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(is, null);
			int eventType = parser.getEventType();
			Item currentItem = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tag = null;
				switch (eventType) {
					case XmlPullParser.START_TAG:
						tag = parser.getName();
						if (tag.equals("item")) {
							currentItem = new Item();
						} else if (currentItem != null) {
							//タグ毎の処理
							if (tag.equals("title")) {
								currentItem.setTitle(parser.nextText());
							} else if (tag.equals("description")) {
								m = p.matcher(parser.nextText());
								if(m.find()){
									String des = m.group();
									currentItem.setContent(des.replaceAll("<li>|</li>",""));
								}
							} else if (tag.equals("guid")){
								currentItem.setGuid(parser.nextText());
							}
						}
						break;
					case XmlPullParser.END_TAG:
						tag = parser.getName();
						if (tag.equals("item")) {
							Adapter.add(currentItem);
						}
						break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Adapter;
	}
	
}
