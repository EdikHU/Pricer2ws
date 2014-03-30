package sed.pricer.acts;

import java.util.Date;

import sed.pricer.data.Product;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ProductDetail extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Product prod = (Product)getIntent().getSerializableExtra("prodItem");
		System.out.println("here2 "+prod);
	}
	
	@Override
	public void onBackPressed() {
		Product prod = (Product)getIntent().getSerializableExtra("prodItem");
		prod.setName("<<<"+((""+new Date()).split("\\s")[3])+">>>");
		Intent intent = new Intent();
		intent.putExtra("prodItem", prod);
		setResult(RESULT_OK, intent);
		super.onBackPressed();
	}
	
}
