package tatsu73.rssreader_lld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RelateParser extends AsyncTask<String, Integer, DetailItem>{
	private ItemDetailActivity Activity;
	private ProgressDialog ProgressDialog;
	private TextView title;
	private TextView content;
	private TextView url1;
	private TextView url2;
	private TextView url3;
	int position;
	private RelateParser rp;
	
	public RelateParser(ItemDetailActivity activity,int Position){
		Activity = activity;
		position = Position;
	}
	
	@Override
	protected void onPreExecute() {
		ProgressDialog = new ProgressDialog(Activity);
		ProgressDialog.setMessage("���΂��҂����");
		ProgressDialog.show();
	}
	
	@Override
	protected DetailItem doInBackground(String... params) {
		DetailItem result = new DetailItem();
		
		//pattern�R���p�C��
		String reg = "<br>|<p>";
		String reg2 = "<.+?>|�E|�y�֘A�L���z";
		Pattern p = Pattern.compile(reg,Pattern.DOTALL);
		Pattern p2 = Pattern.compile(reg2,Pattern.DOTALL);
		Matcher m;
		
		//dom����
		try {
			// HTML�̃h�L�������g���擾
			Document document = Jsoup.connect(params[0]).get();
			
			// title�擾
			Elements eTitle = document.select("h1.articleTtl");
			result.setdTitle(eTitle.text().toString());
			
			// �{��
			Elements eContent = document.select("div.articleBody span");
			//link�폜
			if(eContent.select("a[href]").last() != null){
				eContent.select("a").remove();
			}
			
			//
			String contents = eContent.toString();
			//���s�̍����ւ��Ȃ�
			m = p.matcher(contents);
			if(m.find()){
				contents = m.replaceAll("\n");
			}
			m = p2.matcher(contents);
			if(m.find()){
				contents = m.replaceAll("");
			}
			//content�Z�b�g!!Yes!!!
			result.setdContent(contents+"\n");
			
			
			//�֘Aurl
			List<RelateUrl> relateUrls = new ArrayList<RelateUrl>();
			Elements relate = document.select("ul.articleHeadKeyword li a[href]");
			String url = relate.attr("href").toString();
			//
			Document reDocument = Jsoup.connect(url).get();
			Elements reElements = reDocument.select("li.hasImg a");
			for(Element reErement :reElements){
				RelateUrl ru = new RelateUrl();
				ru.setTitle(reErement.select("div.articleListBody h3").text().toString());
				ru.setUrl(reErement.attr("href").toString());
				relateUrls.add(ru);
			}
			Collections.shuffle(relateUrls); 
			for(int i=relateUrls.size();i>3;i--){
				if(relateUrls.get(i-1) != null){
					relateUrls.remove(i-1);
				}
			}
			RelateUrl[] urls =(RelateUrl[])relateUrls.toArray(new RelateUrl[relateUrls.size()]);
			result.setRelate(urls);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	protected void onPostExecute(DetailItem result) {
		final DetailItem dt = result;
		ProgressDialog.dismiss();
		title = (TextView)Activity.findViewById(R.id.item_detail_title);
		content = (TextView)Activity.findViewById(R.id.item_detail_descr);
		url1 = (TextView)Activity.findViewById(R.id.url1);
		url2 = (TextView)Activity.findViewById(R.id.url2);
		url3 = (TextView)Activity.findViewById(R.id.url3);
		title.setText(dt.getdTitle().toString());
		content.setText(dt.getdContent().toString());
		url1.setText(dt.getdTitle().toString());
		url2.setText(dt.getRealte()[1].getTitle());
		url3.setText(dt.getRealte()[2].getTitle());
		rp = new RelateParser(Activity, position);
		url1.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				rp.execute(dt.getRealte()[0].getUrl().replace("topics", "article"));
			}
		});
		url2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rp.execute(dt.getRealte()[0].getUrl().replace("topics", "article"));
			}
		});
		url3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rp.execute(dt.getRealte()[0].getUrl().replace("topics", "article"));
			}
		});
	}
}
