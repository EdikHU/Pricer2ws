package sed.pricer.acts;

import java.util.ArrayList;
import java.util.List;
import sed.pricer.data.DB;
import sed.pricer.data.Shop;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ShopList extends Activity{

	protected static final String EXTR_SHOP_DETAIL = "shopDetail";
	protected static final int RQ_SHOP_DETAIL = 87234672;
	private List<Shop> shopList = new ArrayList<Shop>();
	private ArrayAdapter<Shop> shopListAdapter;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context = this; 
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		ListView listView = new ListView(this);
		layout.addView(listView);
		shopListAdapter = new ArrayAdapter<Shop>(this, android.R.layout.simple_list_item_1, shopList); 
		listView.setAdapter(shopListAdapter);

		shopListReload();
		 
		// select and return to price detail
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent();
				Shop shop = DB.inst.getShopDao().load(shopList.get(position).getId());
				intent.putExtra(PriceDetail.PRICE_DETAIL_FIELD_SHOP, shop);
				
				setResult(PriceDetail.REQ_CODE_SHOW_SHOP_LIST, intent);
				finish();
			}
		});
		
		//select and call detail shop for EDITING or REMOVING
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(context,ShopDetail.class);
				Shop shop = DB.inst.getShopDao().load(shopList.get(position).getId());
				intent.putExtra(EXTR_SHOP_DETAIL, shop);
				
				startActivityForResult(intent, RQ_SHOP_DETAIL);
				return false;
			}
		});
	}

	private void shopListReload() {
		shopList.clear();
		shopList.addAll(DB.inst.getShopDao().loadAll());
		shopListAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onBackPressed() {
		setResult(PriceDetail.REQ_CODE_SHOW_SHOP_LIST, new Intent());
		super.onBackPressed();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

		if (requestCode == RQ_SHOP_DETAIL){
			shopListReload();
		}
	}
	
}
