package sed.pricer.acts;

import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Price;
import sed.pricer.data.Product;
import sed.pricer.data.Shop;
import sed.pricer.modview.ButtonDate;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class PriceDetail extends Activity {

	protected static final int REQ_CODE_SHOW_SHOP_DETAIL = 83786283;
	protected static final String PRICE_DETAIL_FIELD_SHOP = "";
//	private PriceListAdapter priceListAdapter;
	private Price price;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.price_detail);
		
//		prod = DB.inst.getProductDao().load(((Product)getIntent().getSerializableExtra(ProductDetail.ELEMENT_PRICE)).getId());
//		priceList = prod.getPrices();
		price = (Price)getIntent().getSerializableExtra(ProductDetail.ELEMENT_PRICE);
		price = DB.inst.getPriceDao().load(price.getId());
		
		Product prod = DB.inst.getProductDao().load(price.getProduct().getId());
		int ps = prod.getPrices().size();
		System.out.println("############\n start processing PriceDetail for product id = "+price.getProduct().getId()+" prod.getPrices().size() = "+ps);
		fillViewFromPrice();
		
		findViewById(R.id.price_detail_shop_lbl).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Shop shop = new Shop();
				
				Intent intent = new Intent(context,ShopDetail.class);
				intent.putExtra(PRICE_DETAIL_FIELD_SHOP, shop);
				startActivityForResult(intent , REQ_CODE_SHOW_SHOP_DETAIL);
			}
		});
		
		//list shops here
		// click - edit (show detail shop)
		// long click remove
		//
		
	}

	
	
//	@Override
//	public void onClick(View v) {
//		if (v.getId() == R.id.price_detail_btn_new){
//			price = new Price();
//			price.setProductId(prod.getId());
//			DB.insert(price);
//			fillViewFromPrice();
//			priceList.add(price);
//			DB.update(prod);
//			priceListAdapter.notifyDataSetChanged();
//		} else if(v.getId() == R.id.price_detail_btn_modify){
////			Product tmpProd = DB.inst.getProductDao().load(prod.getId());
////			System.out.println("HERE (pricelist) * ["+tmpProd.getPrices()+"]");
//			fillPriceFromView();
//			System.out.println("["+prod+"]["+price+"]");
//			price.setProductId(prod.getId());
//			DB.update(price);
//			priceList.add(price);
//			DB.update(prod);
//			priceListAdapter.notifyDataSetChanged();
//		}
//		
//	}

	private void fillViewFromPrice() {
		 ((ButtonDate)findViewById(R.id.price_detail_btn_date)).setDate(price.getDate());
		 ((EditText)findViewById(R.id.price_detail_cost)).setText(""+price.getCost());
		 ((TextView)findViewById(R.id.price_detail_shop)).setText(""+price.getShop());
	}

	private void fillPriceFromView() {
		price.setDate(((ButtonDate)findViewById(R.id.price_detail_btn_date)).getDate());
		price.setCost( Float.parseFloat(  ""+((EditText)findViewById(R.id.price_detail_cost)).getText()  ) );
//		price.setShop(null);
		price.update();
	}

	@Override
	public void onBackPressed() {
//		ButtonDate bd = (ButtonDate) findViewById(R.id.price_detail_btn_date);
//		Date ss = bd.getDate();
//		//
		fillPriceFromView();
//		DB.close();
//		DB.init(context);
		DB.refresh();
		Product prod = DB.inst.getProductDao().load(price.getProduct().getId());
		int ps = prod.getPrices().size();
		System.out.println("############\n exit processing PriceDetail for product id = "+price.getProduct().getId()+" prices.size = "+ps);
		setResult(ProductDetail.REQUEST_CODE_SHOW_PRICE_DETAIL);
		super.onBackPressed();
	}
	
}

//priceList.clear();
//Shop s = new Shop();
//s.setName("aushan3");
//DB.insert(s);
//
//Price p = new Price();
//p.setDate(new Date());
//p.setCost((float) 3.14);
//p.setShop(s);
//
//DB.insert(p);
//priceList.add(p);
//DB.update(prod);
//
//System.out.println("-----\n"+priceList);
