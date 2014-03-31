package sed.pricer.acts;

import java.util.List;

import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Group;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class GroupList extends Activity{

	private Group group;
	private GroupListAdapter groupListAdapter;
	private List<Group> groupList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		group = (Group)getIntent().getSerializableExtra(ProductDetail.GROUP_FIELD);
		if (group == null){
			group = new Group();
		}
		
		setContentView(R.layout.group_list);
		
		((EditText)findViewById(R.id.group_list_item_name)).setText(  group.getName() );
		
		
		ListView lv = (ListView) findViewById(R.id.group_list_list_view);
		groupList = DB.inst.getGroupDao().loadAll();
		groupListAdapter = new GroupListAdapter(this, groupList);
		lv.setAdapter(groupListAdapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				group = groupList.get(position);
				((EditText)findViewById(R.id.group_list_item_name)).setText(  group.getName() );
			}
		});
	}
	
	
	public void onClick(View v){
		if (v.getId() == R.id.group_list_btn_modify){
			groupList.remove(group);
			group.setName(((EditText)findViewById(R.id.group_list_item_name)).getText().toString());
			groupList.add(group);
			if (DB.inst.getGroupDao().getKey(group) != null){
				DB.update(group);
			} else {
				DB.insert(group);
			}
			groupListAdapter.notifyDataSetChanged();
		} else if (v.getId() == R.id.group_list_btn_remove){
			groupList.remove(group);
			if (DB.inst.getGroupDao().getKey(group) != null){
				DB.delete(group);
			}
			groupListAdapter.notifyDataSetChanged();
		}
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		//group = new Group();
		intent.putExtra(ProductDetail.GROUP_FIELD, group);
		setResult(RESULT_OK, intent);
		super.onBackPressed();
	}
}
