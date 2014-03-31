package sed.pricer.acts;

import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Product;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ProductDetail extends Activity{

	private Product prod;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Long prodId = (Long)getIntent().getSerializableExtra("prodItemID");
		prod = DB.inst.getProductDao().load(prodId);
		System.out.println("here2 "+prod);
		
		setContentView(R.layout.product_detail);
		
		((EditText)findViewById(R.id.product_detail_name)).setText(prod.getName());
		if (prod.getGroup()!=null)((TextView)findViewById(R.id.product_detail_group)).setText(prod.getGroup().getName());
		
	}
	
	@Override
	public void onBackPressed() {
		DB.delete(prod);
		prod.setName(((TextView)findViewById(R.id.product_detail_name)).getText().toString());
		DB.insert(prod);
		setResult(RESULT_OK, new Intent());
		super.onBackPressed();
	}
	
}
