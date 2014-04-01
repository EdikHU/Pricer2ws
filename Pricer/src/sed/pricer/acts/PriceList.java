package sed.pricer.acts;

import java.util.List;
import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Price;
import sed.pricer.data.Product;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class PriceList extends Activity implements OnClickListener{

	private Product prod;
	private List<Price> priceList;
	private PriceListAdapter priceListAdapter;
	private Price price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.price_list);
		
		prod = DB.inst.getProductDao().load(((Product)getIntent().getSerializableExtra(ProductDetail.FIELD_PRICE)).getId());
		priceList = prod.getPrices();
		
		ListView lv = (ListView)findViewById(R.id.price_list_list_view);
		priceListAdapter = new PriceListAdapter(this,priceList);
		lv.setAdapter(priceListAdapter );
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.price_list_btn_new){
			price = new Price();
			price.setProductId(prod.getId());
			DB.insert(price);
			fillViewFromPrice();
			priceList.add(price);
			DB.update(prod);
			priceListAdapter.notifyDataSetChanged();
		} else if(v.getId() == R.id.price_list_btn_modify){
//			Product tmpProd = DB.inst.getProductDao().load(prod.getId());
//			System.out.println("HERE (pricelist) * ["+tmpProd.getPrices()+"]");
			
		}
		
	}

	private void fillViewFromPrice() {
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
