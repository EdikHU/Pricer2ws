package sed.pricer.acts;

import java.util.Date;
import java.util.List;

import sed.pricer.R;
import sed.pricer.data.DaoMaster;
import sed.pricer.data.DaoMaster.DevOpenHelper;
import sed.pricer.data.DaoSession;
import sed.pricer.data.Shop;
import sed.pricer.data.ShopDao;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActProdCardList extends Activity {

	private TextView tv;
	private List<Shop> loaded;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_layout);
		
		DevOpenHelper db = new DaoMaster.DevOpenHelper(this, "pricer-db", null);
		
		DaoMaster dm = new DaoMaster(db.getWritableDatabase());
		DaoSession sess = dm.newSession();
		
		ShopDao shopDao = sess.getShopDao();
		shopDao.insert(new Shop(null, "some ["+( (""+new Date()).split("\\s")[3]  )+"]"));
		
		loaded = shopDao.loadAll();
		
		System.out.println("--> "+ loaded);
		
		tv = (TextView)findViewById(R.id.test_textView1); 
		
		findViewById(R.id.test_button1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText(loaded.toString());
			}
		}); 
		
	}
}
