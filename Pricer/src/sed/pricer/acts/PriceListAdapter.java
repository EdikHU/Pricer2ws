package sed.pricer.acts;

import java.util.List;

import sed.pricer.data.Price;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PriceListAdapter extends BaseAdapter {

	private Context context;
	private List<Price> priceList;

	public PriceListAdapter(List<Price> priceList) {
	}

	public PriceListAdapter(Context context, List<Price> priceList) {
		this.context = context;
		this.priceList = priceList;
	}

	@Override
	public int getCount() {
		return priceList.size();
	}

	@Override
	public Object getItem(int position) {
		return priceList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return priceList.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null){
			convertView = new PriceListAdapterItem(context);
		}
		((TextView)convertView.findViewWithTag("price_list_adapter_item_tv")).setText(priceList.get(position).toString());
		return convertView;
	}

}
