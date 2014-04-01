package sed.pricer.acts;

import java.util.List;

import sed.pricer.data.Factory;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FactoryListAdapter extends BaseAdapter{

	private Context context;
	private List<Factory> factoryList;

	public FactoryListAdapter(Context context,
			List<Factory> factoryList) {
		this.context = context;
		this.factoryList = factoryList;
	}

	@Override
	public int getCount() {
		return factoryList.size();
	}

	@Override
	public Object getItem(int position) {
		return factoryList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return factoryList.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null){
			convertView = new FactoryListAdapterItem(context);
		}
		((TextView)convertView.findViewWithTag("factory_list_adapter_item_tv")).setText(  ((Factory)getItem(position)).toString()  );

		return convertView;
	}

}
