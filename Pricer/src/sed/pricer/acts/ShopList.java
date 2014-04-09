package sed.pricer.acts;

import java.util.List;

import sed.pricer.data.DB;
import sed.pricer.data.Shop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ShopList extends Activity{

	private List<Shop> shopList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		shopList = DB.inst.getShopDao().loadAll();
		
		ListView listView = new ListView(this);
		setContentView(listView);
		
		ListAdapter shopListAdapter = new ArrayAdapter<Shop>(this, android.R.layout.simple_list_item_1, shopList); 
		listView.setAdapter(shopListAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra(PriceDetail.PRICE_DETAIL_FIELD_SHOP, shopList.get(position));
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
