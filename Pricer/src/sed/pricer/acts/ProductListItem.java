package sed.pricer.acts;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductListItem extends LinearLayout {

	public ProductListItem(Context context) {
		super(context);
		
		TextView tv = new TextView(context);
		tv.setTag("product_list_item_tv");
		this.addView(tv);
	}

}
