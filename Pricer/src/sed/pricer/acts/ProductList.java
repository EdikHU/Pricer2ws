package sed.pricer.acts;

import java.util.Date;
import java.util.List;
import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Product;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class ProductList extends Activity {

	protected static final int RC_SHOW_PRODUCT_DETAIL = 0;
	private ProductListAdapter productListAdapter;
	private ListView lv;
	private List<Product> productList;
	protected Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.product_list);
		DB.init(this);

		lv = (ListView) findViewById(R.id.product_list_list_view);
		productList = DB.inst.getProductDao().loadAll();
		productListAdapter = new ProductListAdapter(this,productList );
		lv.setAdapter(productListAdapter);
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(context,ProductDetail.class);
				intent.putExtra("prodItem", productList.get(arg2));
				startActivityForResult(intent , RC_SHOW_PRODUCT_DETAIL);
			}
		});
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Product prodItem = productList.get(arg2);
				productList.remove(arg2);
				DB.delete(prodItem);
				productListAdapter.notifyDataSetChanged();
				return false;
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RC_SHOW_PRODUCT_DETAIL == requestCode){
			Product prod = (Product)data.getSerializableExtra("prodItem");
			for ( Product elm : productList){
				if ( elm.getId().equals( prod.getId()) ){
					productList.remove(elm);
					DB.delete(elm);
					productList.add(prod);
					DB.insert(prod);
					productListAdapter.notifyDataSetChanged();
					break;
				}
			}
		}
	}
	
	public void onClick(View v){
		Product prod = new Product();
		prod.setName("<<"+((""+new Date()).split("\\s")[3])+">>");
		DB.insert(prod);
		productList.add(prod);
		if (v.getId() == R.id.product_list_btn_fd){
			DB.inst.getProductDao().deleteAll();
			productList.clear();
		}
		productListAdapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		DB.close();
	}

}

//Price price = new Price();
//
//price.setDate(new Date());
//price.setCost((float) 3.14);
//
//Shop shop = new Shop();
//shop.setName("s pricedao [" + (("" + new Date()).split("\\s")[3]) + "]");
//System.out.println("-->>>> "+shop);
//// shopDao.insert(shop);
//DB.insert(shop);
//
//price.setShop(shop);
////priceDao.insert(price);
////db1.getPriceDao().insert(price);
//DB.insert(price);
//
//System.out.println("--> " + DB.inst.getPriceDao().loadAll());
//
//tv = (TextView) findViewById(R.id.test_textView1);
//
//findViewById(R.id.test_button1).setOnClickListener(
//		new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				loaded = DB.inst.getShopDao().loadAll();
//				tv.setText(loaded.toString());
//			}
//		});

