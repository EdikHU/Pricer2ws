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
		setContentView(R.layout.group_list);

		group = (Group)getIntent().getSerializableExtra(ProductDetail.FIELD_GROUP);
		groupList = DB.inst.getGroupDao().loadAll();

		if (group == null){
			if(groupList.size() == 0){
				groupList.add(new Group());
			}
			group = groupList.get(0);
		}

		
		((EditText)findViewById(R.id.group_list_item_name)).setText(  group.getName() );
		
		
		ListView lv = (ListView) findViewById(R.id.group_list_list_view);
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
			group.setName(((EditText)findViewById(R.id.group_list_item_name)).getText().toString());
			if (DB.inst.getGroupDao().getKey(group) != null){
				DB.update(group);
			} else {
				DB.insert(group);
			}
		} else if (v.getId() == R.id.group_list_btn_remove){
			groupList.remove(group);
			if (DB.inst.getGroupDao().getKey(group) != null){
				DB.delete(group);
			}
		} else if (v.getId() == R.id.group_list_btn_new){
			group = new Group();
			group.setName("***");
			((EditText)findViewById(R.id.group_list_item_name)).setText(group.getName());
			DB.insert(group);
			groupList.add(group);
		}
		groupListAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra(ProductDetail.FIELD_GROUP, group);
		setResult(RESULT_OK, intent);
		super.onBackPressed();
	}
}
