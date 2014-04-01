package sed.pricer.acts;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PriceListAdapterItem extends LinearLayout {

	public PriceListAdapterItem(Context context) {
		super(context);
		
		TextView tv = new TextView(context);
		tv.setTag("price_list_adapter_item_tv");
		this.addView(tv);
		
	}

}
