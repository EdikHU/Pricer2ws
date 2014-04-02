package sed.pricer.acts;

import sed.pricer.data.DB;
import sed.pricer.data.Relation;
import sed.pricer.data.TheHolder;
import sed.pricer.data.TheItem;
import android.app.Activity;
import android.os.Bundle;

public class TheHolderAct extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DB.init(this);
		
		
		
		TheHolder h1 = new TheHolder();
		DB.insert(h1);

		TheHolder h2 = new TheHolder();
		DB.insert(h2);
		
		TheHolder h3 = new TheHolder();
		DB.insert(h3);
		
		TheItem item1 = new TheItem();
		item1.setName("Uno");
		DB.insert(item1);
		addToHolder(h1,item1);
		addToHolder(h3,item1);
		
		TheItem item2 = new TheItem();
		item2.setName("Dos");
		DB.insert(item2);
		addToHolder(h2,item2);
		addToHolder(h3,item2);

		TheItem item3 = new TheItem();
		item3.setName("Tres");
		DB.insert(item3);
		addToHolder(h1,item3);
		addToHolder(h2,item3);
		addToHolder(h3,item3);
		addToHolder(h3,item3);
		addToHolder(h3,item3);
		
		
		
		
//		item = new TheItem();
//		item.setName("Dos");
//		item.setTheHolderId(holder.getId());
//		DB.insert(item);
//		items.add(item);
//
//		item = new TheItem();
//		item.setName("Tres");
//		item.setTheHolderId(holder.getId());
//		DB.insert(item);
//		items.add(item);
		
		//DB.update(holder);

		System.out.println("\n--\n "+DB.inst.getTheHolderDao().loadAll());
		System.out.println("\n\n"+DB.inst.getTheItemDao().loadAll());
		System.out.println("\n\n"+DB.inst.getRelationDao().loadAll());
		
		DB.close();
		finish();
	}

	private void addToHolder(TheHolder holder, TheItem item) {
//		List<Relation> items = holder.getItems();
		Relation rel = new Relation();
		rel.setRelation_theHolderId(holder.getId());
		rel.setRelation_theItemId(item.getId());
//		items.add(rel);
		DB.insert(rel);
	}
}
