package sed.pricer.acts;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GroupListAdapterItem extends LinearLayout {

	public GroupListAdapterItem(Context context) {
		super(context);
		
		TextView tv = new TextView(context);
		tv.setTag("group_list_adapter_item_tv");
		this.addView(tv);
		
		
	}

}
