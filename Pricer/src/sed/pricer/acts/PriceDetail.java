package sed.pricer.acts;

import java.util.Date;
import java.util.List;
import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Price;
import sed.pricer.data.Product;
import sed.pricer.modview.ButtonDate;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class PriceDetail extends Activity implements OnClickListener{

	private Product prod;
	private List<Price> priceList;
	private PriceListAdapter priceListAdapter;
	private Price price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.price_detail);
		
		prod = DB.inst.getProductDao().load(((Product)getIntent().getSerializableExtra(ProductDetail.FIELD_PRICE)).getId());
		priceList = prod.getPrices();
		
		fillViewFromPrice();
		
		findViewById(R.id.price_detail_shop_lbl).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// show new detail Shop
			}
		});
		
		//list shops here
		// click - edit (show detail shop)
		// long click remove
		//
		
	}

	
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.price_detail_btn_new){
			price = new Price();
			price.setProductId(prod.getId());
			DB.insert(price);
			fillViewFromPrice();
			priceList.add(price);
			DB.update(prod);
			priceListAdapter.notifyDataSetChanged();
		} else if(v.getId() == R.id.price_detail_btn_modify){
//			Product tmpProd = DB.inst.getProductDao().load(prod.getId());
//			System.out.println("HERE (pricelist) * ["+tmpProd.getPrices()+"]");
			fillPriceFromView();
			System.out.println("["+prod+"]["+price+"]");
			price.setProductId(prod.getId());
			DB.update(price);
			priceList.add(price);
			DB.update(prod);
			priceListAdapter.notifyDataSetChanged();
		}
		
	}

	private void fillPriceFromView() {
		// TODO Auto-generated method stub
		
	}

	private void fillViewFromPrice() {
	}

	@Override
	public void onBackPressed() {
		ButtonDate bd = (ButtonDate) findViewById(R.id.price_detail_btn_date);
		Date ss = bd.getDate();
		System.out.println(ss);
		//
		fillViewFromPrice();
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
