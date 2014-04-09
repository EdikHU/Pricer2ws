package sed.pricer.acts;

import java.util.Date;
import java.util.List;

import sed.pricer.data.DB;
import sed.pricer.data.Shop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ShopList extends Activity{

	private List<Shop> shopList;
	private ArrayAdapter<Shop> shopListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		shopList = DB.inst.getShopDao().loadAll();
		
		//----------------
		Button btnNew = new Button(this);
		btnNew.setText("New");
		layout.addView(btnNew);
		btnNew.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Shop s = new Shop();
				s.setName("shop "+new Date());
				DB.insert(s);
				shopList.clear();
				shopList.addAll( DB.inst.getShopDao().loadAll());
				shopListAdapter.notifyDataSetChanged();
			}
		});

		Button btnShow = new Button(this);
		btnShow.setText("Show");
		layout.addView(btnShow);
		btnShow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("########## \n"+DB.inst.getShopDao().loadAll());
			}
		});
		
		//---------------
		ListView listView = new ListView(this);
		layout.addView(listView);
		shopListAdapter = new ArrayAdapter<Shop>(this, android.R.layout.simple_list_item_1, shopList); 
		listView.setAdapter(shopListAdapter);
		 
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				Shop shop = DB.inst.getShopDao().load(shopList.get(position).getId());
				System.out.println("----------> return to price \n"+shop);
				intent.putExtra(PriceDetail.PRICE_DETAIL_FIELD_SHOP, shop);
				setResult(PriceDetail.REQ_CODE_SHOW_SHOP_LIST, intent);
				finish();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		setResult(PriceDetail.REQ_CODE_SHOW_SHOP_LIST, new Intent());
		super.onBackPressed();
	}
}
