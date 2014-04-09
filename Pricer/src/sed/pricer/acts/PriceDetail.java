package sed.pricer.acts;

import java.util.List;

import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Price;
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

public class PriceDetail extends Activity implements OnClickListener{

	protected static final int REQ_CODE_SHOW_SHOP_DETAIL = 83786283;
	protected static final String PRICE_DETAIL_FIELD_SHOP = "fieldShop";
	protected static final String PRICE_DETAIL_SHOP_LIST = "shopList";
	protected static final int REQ_CODE_SHOW_SHOP_LIST = 768998234;
	private Price price;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.price_detail);
		
		price = (Price)getIntent().getSerializableExtra(ProductDetail.ELEMENT_PRICE);
		price = DB.inst.getPriceDao().load(price.getId());
		
		fillViewFromPrice();
		
		findViewById(R.id.price_detail_shop_lbl).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				List<Shop> shopList = DB.inst.getShopDao().loadAll();
				if (shopList.size() == 0){
					Shop shop = new Shop();
					shop.setName("asahan");
					DB.insert(shop);
					//DB.refresh();
				}
				
				Intent intent = new Intent(context,ShopList.class);
				//intent.putExtra(PRICE_DETAIL_SHOP_LIST, shopList);
				startActivityForResult(intent , REQ_CODE_SHOW_SHOP_LIST);
			}
		});
		
		//list shops here
		// click - edit (show detail shop)
		// long click remove
		//
		
	}

	private void fillViewFromPrice() {
		 ((ButtonDate)findViewById(R.id.price_detail_btn_date)).setDate(price.getDate());
		 ((EditText)findViewById(R.id.price_detail_cost)).setText(""+( price.getCost() != null ? price.getCost(): "") );
		 ((TextView)findViewById(R.id.price_detail_shop)).setText(""+price.getShop());
	}

	private void fillPriceFromView() {
		price.setDate(((ButtonDate)findViewById(R.id.price_detail_btn_date)).getDate());
		try{
			float cost = Float.parseFloat(  ""+((EditText)findViewById(R.id.price_detail_cost)).getText()  );
			price.setCost(cost);
		}catch(Exception e){
			price.setCost((float) 0.0);
		}
//		price.setShop(null);
		price.update();
		DB.refresh();
		price = DB.inst.getPriceDao().load(price.getId());
		System.out.println("##################\n fillPriceFromView price.getShop() = "+ price.getShop());
	}

	@Override
	public void onBackPressed() {
		price.delete();
		super.onBackPressed();
	}



	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.price_detail_btn_ok) {
			fillPriceFromView();
			setResult(ProductDetail.REQUEST_CODE_SHOW_PRICE_DETAIL);
			finish();
		} else if (v.getId() == R.id.price_detail_btn_cancel) {
			price.delete();
			finish();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQ_CODE_SHOW_SHOP_LIST){
			System.out.println("");
			Shop shop = (Shop)intent.getSerializableExtra(PRICE_DETAIL_FIELD_SHOP);
			if (shop != null){
				shop = DB.inst.getShopDao().load(shop.getId());
				price.setShop(shop);
				price.setShopID(shop.getId());
				System.out.println("["+shop.getId()+"]["+shop+"]  ["+price.getShopID()+"]["+price.getShop()+"]");
				price.update();

				DB.close();
				DB.init(context);
				
				price = DB.inst.getPriceDao().load(price.getId());
				System.out.println("##################\n onActivityResult price.getShop() = "+ price.getShop());
				DB.refresh();
				fillViewFromPrice();
			}
		}
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
