package sed.pricer.acts;

import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Shop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class ShopDetail extends Activity implements OnClickListener{

	private Shop shop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.shop_detail);
		
		shop = (Shop)getIntent().getSerializableExtra(ShopList.EXTR_SHOP_DETAIL);
		
		fillViewFromShop();
		
	}

	private void fillViewFromShop() {
		shop = DB.inst.getShopDao().load(shop.getId());
		((EditText)findViewById(R.id.shop_detail_name)).setText(shop.getName());
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.shop_detail_btn_ok){
			fillShopFromView();
			Intent intent = new Intent();
			intent.putExtra(ShopList.EXTR_SHOP_DETAIL, shop);
			setResult(0,intent);
			finish();
		} else if (v.getId() == R.id.shop_detail_btn_cancel){
			setResult(0, new Intent());
			finish();
		} else if (v.getId() == R.id.shop_detail_btn_remove){
			DB.inst.delete(shop);
			setResult(0, null);
			finish();
		}
	}

	private void fillShopFromView() {
		shop.setName(  ((EditText)findViewById(R.id.shop_detail_name)).getText().toString() );
		DB.update(shop);
	}

	@Override
	public void onBackPressed() {
		setResult(0, new Intent());
		super.onBackPressed();
	}
}
