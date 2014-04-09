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

	protected static final String PRODUCT_DETAIL_FIELD_GROUP = "groupId";
	protected static final int RC_SHOW_GROUP = 23643456;
	protected static final String FIELD_FACTORY = "factory";
	protected static final int RC_SHOW_FACTORY = 894678;
	protected static final String ELEMENT_PRICE = "price";
	protected static final int REQUEST_CODE_SHOW_PRICE_DETAIL = 567678789;
	private Product prod;
	private Context context;
	private List<Price> priceList;
	private EditText name;
	private TextView factory;
	private TextView group;
	private ListView prices;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.product_detail);
		
		prod = ((Product)getIntent().getSerializableExtra(ProductList.PROD_ITEM));

		// get view's
		
		name = ((EditText)findViewById(R.id.product_detail_name));
		factory = ((TextView)findViewById(R.id.product_detail_factory));
		group = ((TextView)findViewById(R.id.product_detail_group));
		prices = ((ListView)findViewById(R.id.product_detail_prices_view));
		
		reloadProductAndFillingViewFromProd();
		
		//set click's
		//name

		// factory
//		if (prod.getFactory() != null)((TextView)findViewById(R.id.product_detail_factory)).setText(prod.getFactory().getName());
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
		//if (prod.getGroup()!=null)((TextView)findViewById(R.id.product_detail_group)).setText(prod.getGroup().getName());
		TextView groupLbl = ((TextView)findViewById(R.id.product_detail_group_lbl));
		groupLbl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,GroupList.class);
				intent.putExtra(PRODUCT_DETAIL_FIELD_GROUP, prod.getGroup());
				startActivityForResult(intent , RC_SHOW_GROUP);
			}
		});
		
		// pricelist
//		ListView lv = ((ListView)findViewById(R.id.product_detail_prices_view));//.setText(prod.getGroup().getName());

		// onclick and longonclick for modify / remove
		
		//add new price
		((TextView)findViewById(R.id.product_detail_prices_lbl)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 
				
				Price price = new Price();
				price.setProduct(prod);
				DB.insert(price);

				Intent intent = new Intent(context,PriceDetail.class);
				intent.putExtra(ELEMENT_PRICE, price);
				startActivityForResult(intent , REQUEST_CODE_SHOW_PRICE_DETAIL);
			}
		});
	}
	
	private void reloadProductAndFillingViewFromProd() {

		prod = DB.inst.getProductDao().load(prod.getId());

		//name
		name.setText(prod.getName());

		// factory
		factory.setText(""+(prod.getFactory() != null ? prod.getFactory().getName(): null) );
		
		//group
		group.setText( ""+   (prod.getGroup()!=null ? prod.getGroup().getName(): null)  );
		
		//prices
		priceList = prod.getPrices();
		ListAdapter priceListAdapter = new PriceListAdapter(this,priceList);
		prices.setAdapter(priceListAdapter);

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
			prod.setGroup((Group) data.getSerializableExtra(PRODUCT_DETAIL_FIELD_GROUP));
			((TextView)findViewById(R.id.product_detail_group)).setText(prod.getGroup().getName());
			DB.update(prod);
		} else if (requestCode == RC_SHOW_FACTORY){
			prod.setFactory((Factory) data.getSerializableExtra(FIELD_FACTORY));
			((TextView)findViewById(R.id.product_detail_factory)).setText(prod.getFactory().getName());
			DB.update(prod);
		} else if(requestCode == REQUEST_CODE_SHOW_PRICE_DETAIL){
			reloadProductAndFillingViewFromProd();
		}
	}

	
}
