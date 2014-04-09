package sed.pricer.acts;

import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Shop;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ShopDetail extends Activity implements OnClickListener{

	private Shop shop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.shop_detail);
		
		shop = (Shop)getIntent().getSerializableExtra(PriceDetail.PRICE_DETAIL_FIELD_SHOP);
		shop = DB.inst.getShopDao().load(shop.getId());
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.shop_detail_btn_ok){
			
		} else if (v.getId() == R.id.shop_detail_btn_cancel){
			
		}
	}

}
