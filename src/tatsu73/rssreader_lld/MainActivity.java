package tatsu73.rssreader_lld;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.text.style.RelativeSizeSpan;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	private ArrayList<Item> itemList;
	private RSSListAdapter Adapter;
	public String RSS_FEED_URL = "";
	private GuidSet gs;
	public static final int MENU_ID_RELOAD = 0;
	public static final int MENU_ID_MAIN = 1; 
	public static final int MENU_ID_INTERNAL = 2;
	public static final int MENU_ID_FOREIGN = 3; 
	public static final int MENU_ID_IT_ECONOMY = 4;
	public static final int MENU_ID_ENTERTAINMENT = 5;
	public static final int MENU_ID_SPORT = 6;
	public static final int MENU_ID_MOVIE = 7;
	public static final int MENU_ID_GOULMET = 8;
	public static final int MENU_ID_WOMEN = 9;
	public static final int MENU_ID_TREND = 10;
	DataManager dm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gs = new GuidSet();
		itemList = new ArrayList<Item>();
		Adapter = new RSSListAdapter(this, itemList);
		RSS_FEED_URL = gs.getCurrent();

		// �p�[�X�^�X�N���N������
		RSSParser task = new RSSParser(this, Adapter);
		task.execute(RSS_FEED_URL);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		dm = (DataManager)this.getApplication();
		dm.set(itemList);
		Item item = dm.get().get(position);
		Intent intent = new Intent(this, ItemDetailActivity.class);
		intent.putExtra("ITEM", item);
		intent.putExtra("POSITION", position);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		boolean result = super.onCreateOptionsMenu(menu);
		// �f�t�H���g�ł̓A�C�e����ǉ��������Ԓʂ�ɕ\������
		menu.add(0, MENU_ID_RELOAD, 0, "�X�V");
		menu.add(0, MENU_ID_MAIN, 0, "��v");
		menu.add(0, MENU_ID_INTERNAL, 0, "����");
		menu.add(0, MENU_ID_FOREIGN, 0, "�C�O");
		menu.add(0, MENU_ID_IT_ECONOMY, 0, "IT �o��");
		menu.add(0, MENU_ID_ENTERTAINMENT, 0, "�|�\");
		menu.add(0, MENU_ID_SPORT, 0, "�X�|�[�c");
		menu.add(0, MENU_ID_MOVIE, 0, "�f��");
		menu.add(0, MENU_ID_GOULMET, 0, "�O����");
		menu.add(0, MENU_ID_WOMEN, 0, "���q");
		menu.add(0, MENU_ID_TREND, 0, "�g�����h");
		return result;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		// �X�V
			case MENU_ID_RELOAD:
				RSS_FEED_URL = gs.getCurrent();
				load(RSS_FEED_URL);
				return true;
			case MENU_ID_MAIN:
				RSS_FEED_URL = gs.getMain();
				load(RSS_FEED_URL);
				return true;
			case MENU_ID_INTERNAL:
				RSS_FEED_URL = gs.getInternal();
				load(RSS_FEED_URL);
				return true;
			case MENU_ID_FOREIGN:
				RSS_FEED_URL = gs.getForeign();
				load(RSS_FEED_URL);
				return true;
			case MENU_ID_IT_ECONOMY:
				RSS_FEED_URL = gs.getItEconomy();
				load(RSS_FEED_URL);
				return true;
			case MENU_ID_ENTERTAINMENT:
				RSS_FEED_URL = gs.getEntertainment();
				load(RSS_FEED_URL);
				return true;	
			case MENU_ID_SPORT:
				RSS_FEED_URL = gs.getSport();
				load(RSS_FEED_URL);
				return true;
			case MENU_ID_MOVIE:
				RSS_FEED_URL = gs.getMovie();
				load(RSS_FEED_URL);
				return true;
			case MENU_ID_GOULMET:
				RSS_FEED_URL = gs.getGourmet();
				load(RSS_FEED_URL);
				return true;	
			case MENU_ID_WOMEN:
				RSS_FEED_URL = gs.getWomen();
				load(RSS_FEED_URL);
				return true;
			case MENU_ID_TREND:
				RSS_FEED_URL = gs.getTrend();
				load(RSS_FEED_URL);
				return true;	
		}
		return super.onOptionsItemSelected(item);
	}

	private void load(String URL) {
		// �A�_�v�^�����������A�^�X�N���N������
		gs.setCurrent(URL);
		itemList = new ArrayList<Item>();
		Adapter = new RSSListAdapter(this, itemList);
		// �^�X�N�͂��̓s�x��������
		RSSParser task = new RSSParser(this, Adapter);
		task.execute(URL);
	}
}
