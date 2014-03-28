package sed.pricer.acts;

import java.util.List;

import sed.pricer.data.Product;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProductListAdapter extends BaseAdapter{

	private Context context;
	private List<Product> productList;

	public ProductListAdapter(Context context, List<Product> productList) {
		this.context = context;
		this. productList = productList;
	}

	@Override
	public int getCount() {
		return productList.size();
	}

	@Override
	public Object getItem(int position) {
		return productList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return productList.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null){
			convertView = new ProductListItem(context);
		}
//		System.out.println("--->>> ["+position+"]["+getItem(position)+"]");
		( (TextView) convertView.findViewWithTag("product_list_item_tv") ).setText(getItem(position).toString());
		
		return convertView;
	}


}
