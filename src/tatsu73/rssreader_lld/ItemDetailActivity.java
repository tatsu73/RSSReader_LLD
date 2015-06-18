package tatsu73.rssreader_lld;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ItemDetailActivity extends ActionBarActivity {
	private TextView title;
	private TextView content;
	private TextView url1;
	private TextView url2;
	private TextView url3;
	private int position;
	private RelateParser rp;
	private Item item; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		//intent	
		Intent intent = getIntent();
		item = (Item)intent.getSerializableExtra("ITEM");
		position = (Integer)intent.getSerializableExtra("POSITION");
		
		if(item.getChash() == null){
			// パースタスクを起動する
			HTMLParser task = new HTMLParser(this,position);
			task.execute(item.getGuid());
		}else{
			title = (TextView)findViewById(R.id.item_detail_title);
			content = (TextView)findViewById(R.id.item_detail_descr);
			url1 = (TextView)findViewById(R.id.url1);
			url2 = (TextView)findViewById(R.id.url2);
			url3 = (TextView)findViewById(R.id.url3);
			title.setText(item.getChash().getdTitle().toString());
			content.setText(item.getChash().getdContent().toString());
			url1.setText(item.getChash().getRealte()[0].getTitle());
			url2.setText(item.getChash().getRealte()[1].getTitle());
			url3.setText(item.getChash().getRealte()[2].getTitle());
			
			rp = new RelateParser(this, position);
			url1.setOnClickListener(new OnClickListener() {			
				@Override
				public void onClick(View v) {
					rp.execute(item.getChash().getRealte()[0].getUrl().replace("topics", "article"));
				}
			});
			url2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					rp.execute(item.getChash().getRealte()[1].getUrl().replace("topics", "article"));
				}
			});
			url3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					rp.execute(item.getChash().getRealte()[2].getUrl().replace("topics", "article"));
				}
			});
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
