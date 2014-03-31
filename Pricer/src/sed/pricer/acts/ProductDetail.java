package sed.pricer.acts;

import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Group;
import sed.pricer.data.Product;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class ProductDetail extends Activity {

	private static final int RC_SHOW_GROUP = 23643456;
	public static final String GROUP_FIELD = "groupId";
	private Product prod;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.product_detail);
		
//		Long prodId = (Long)getIntent().getSerializableExtra(ProductList.PROD_ITEM_ID);
//		prod = DB.inst.getProductDao().load(prodId);
		
		prod = (Product)getIntent().getSerializableExtra(ProductList.PROD_ITEM_ID);
		System.out.println("["+prod+"]");
		
		((EditText)findViewById(R.id.product_detail_name)).setText(prod.getName());

		if (prod.getGroup()!=null)((TextView)findViewById(R.id.product_detail_group)).setText(prod.getGroup().getName());
		TextView groupTv = ((TextView)findViewById(R.id.product_detail_tv_group));
		groupTv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,GroupList.class);
				intent.putExtra(GROUP_FIELD, prod.getGroup());
				startActivityForResult(intent , RC_SHOW_GROUP);
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		prod.setName(((TextView)findViewById(R.id.product_detail_name)).getText().toString());
		DB.update(prod);
		setResult(RESULT_OK, new Intent());
		super.onBackPressed();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RC_SHOW_GROUP){
			prod.setGroup((Group) data.getSerializableExtra(GROUP_FIELD));
			((TextView)findViewById(R.id.product_detail_group)).setText(prod.getGroup().getName());
			DB.update(prod);
		}
	}
	
}
