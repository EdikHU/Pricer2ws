package sed.pricer.acts;

import java.util.List;

import sed.pricer.data.Group;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GroupListAdapter extends BaseAdapter {

	private List<Group> groupList;
	private Context context;

	public GroupListAdapter(Context context, List<Group> groupList) {
		this.groupList = groupList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return groupList.size();
	}

	@Override
	public Object getItem(int position) {
		return groupList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return groupList.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null){
			convertView = new GroupListAdapterItem(context);
		}
		((TextView)convertView.findViewWithTag("group_list_adapter_item_tv")).setText(  ((Group)getItem(position)).toString()  );
		return convertView;
	}


}
