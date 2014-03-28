package sed.pricer.acts;

import java.util.List;

import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Product;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
public class ProductList extends Activity {


	private ProductListAdapter productListAdapter;
	private ListView lv;
	private List<Product> productList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_list);
		DB.init(this);

		lv = (ListView) findViewById(R.id.product_list_list_view);
		productList = DB.inst.getProductDao().loadAll();
		productListAdapter = new ProductListAdapter(this,productList );
		lv.setAdapter(productListAdapter);
	}

	public void onClick(View v){
		
		Product prod = new Product();
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

