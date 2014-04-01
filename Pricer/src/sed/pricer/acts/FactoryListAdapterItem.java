package sed.pricer.acts;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FactoryListAdapterItem extends LinearLayout {

	public FactoryListAdapterItem(Context context) {
		super(context);

		TextView tv = new TextView(context);
		tv.setTag("factory_list_adapter_item_tv");
		this.addView(tv);

	}

}
