package sed.pricer.acts;

import java.util.List;

import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Factory;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class FactoryList extends Activity implements OnClickListener{

	private Factory factory;
	private List<Factory> factoryList;
	private FactoryListAdapter factoryListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.factory_list);
		
		factory = (Factory)getIntent().getSerializableExtra(ProductDetail.FIELD_FACTORY);
		factoryList = DB.inst.getFactoryDao().loadAll();

		if (factory == null){
			if(factoryList.size() == 0){
				factoryList.add(new Factory());
			}
			factory = factoryList.get(0);
		}

		fillingCurrentViewInformationFromCurrentData();
		
		ListView lv = (ListView) findViewById(R.id.factory_list_list_view);
		factoryListAdapter = new FactoryListAdapter(this, factoryList);
		lv.setAdapter(factoryListAdapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				factory = factoryList.get(position);
				fillingCurrentViewInformationFromCurrentData();
			}
		});
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra(ProductDetail.FIELD_FACTORY, factory);
		setResult(RESULT_OK, intent);
		super.onBackPressed();
	}
	
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.factory_list_btn_modify){
			fillingCurrentDataFromView();
			if (DB.inst.getFactoryDao().getKey(factory) != null){
				DB.update(factory);
			} else {
				DB.insert(factory);
			}
		} else if (v.getId() == R.id.factory_list_btn_delete){
			factoryList.remove(factory);
			if (DB.inst.getFactoryDao().getKey(factory) != null) DB.delete(factory);
		} else if (v.getId() == R.id.factory_list_btn_new){
			factory = new Factory();
			factory.setName("#####");
			fillingCurrentViewInformationFromCurrentData();
			DB.insert(factory);
			factoryList.add(factory);
		}
		factoryListAdapter.notifyDataSetChanged();
	}

	
	
	private void fillingCurrentDataFromView() {
		factory.setName(((EditText)findViewById(R.id.factory_list_name)).getText().toString());
	}

	protected void fillingCurrentViewInformationFromCurrentData() {
		((EditText)findViewById(R.id.factory_list_name)).setText(  factory.getName() );
	}
	
	
	
}
