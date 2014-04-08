package sed.pricer.acts;

import java.util.List;
import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Factory;
import sed.pricer.data.Group;
import sed.pricer.data.Price;
import sed.pricer.data.Product;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ProductDetail extends Activity {

	protected static final String FIELD_GROUP = "groupId";
	protected static final int RC_SHOW_GROUP = 23643456;
	protected static final String FIELD_FACTORY = "factory";
	protected static final int RC_SHOW_FACTORY = 894678;
	protected static final String FIELD_PRICE = "price";
	protected static final int RC_SHOW_PRICE = 567678789;
	private Product prod;
	private Context context;
	private List<Price> priceList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.product_detail);
		
		prod = DB.inst.getProductDao().load(((Product)getIntent().getSerializableExtra(ProductList.PROD_ITEM)).getId());

		//name
		((EditText)findViewById(R.id.product_detail_name)).setText(prod.getName());

		// factory
		if (prod.getFactory() != null)((TextView)findViewById(R.id.product_detail_factory)).setText(prod.getFactory().getName());
		View factoryLbl = findViewById(R.id.product_detail_factory_lbl);
		factoryLbl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,FactoryList.class);
				intent.putExtra(FIELD_FACTORY, prod.getFactory());
				startActivityForResult(intent , RC_SHOW_FACTORY);
			} 
		});
		
		// group
		if (prod.getGroup()!=null)((TextView)findViewById(R.id.product_detail_group)).setText(prod.getGroup().getName());
		TextView groupLbl = ((TextView)findViewById(R.id.product_detail_group_lbl));
		groupLbl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,GroupList.class);
				intent.putExtra(FIELD_GROUP, prod.getGroup());
				startActivityForResult(intent , RC_SHOW_GROUP);
			}
		});
		
		// pricelist
		priceList = prod.getPrices();
		ListView lv = ((ListView)findViewById(R.id.product_detail_price_list_view));//.setText(prod.getGroup().getName());
		ListAdapter priceListAdapter = new PriceListAdapter(this,priceList);
		lv.setAdapter(priceListAdapter);
		
		//add new price
		((TextView)findViewById(R.id.product_detail_price_lbl)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 
				Intent intent = new Intent(context,PriceDetail.class);
				//intent.putExtra(FIELD_PRICE, null);
				startActivityForResult(intent , RC_SHOW_PRICE);
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		prod.setName(((TextView)findViewById(R.id.product_detail_name)).getText().toString());
		DB.update(prod);

//		Product tmpProd = DB.inst.getProductDao().load(prod.getId());
//		System.out.println("HERE ** (productDetail return)["+tmpProd.getPrices()+"]");

		setResult(RESULT_OK, new Intent());
		super.onBackPressed();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RC_SHOW_GROUP){
			prod.setGroup((Group) data.getSerializableExtra(FIELD_GROUP));
			((TextView)findViewById(R.id.product_detail_group)).setText(prod.getGroup().getName());
			DB.update(prod);
		} else if (requestCode == RC_SHOW_FACTORY){
			prod.setFactory((Factory) data.getSerializableExtra(FIELD_FACTORY));
			((TextView)findViewById(R.id.product_detail_factory)).setText(prod.getFactory().getName());
			DB.update(prod);
		} else if(requestCode == RC_SHOW_PRICE){
			//System.out.println("here");
		}
	}

	
}
