package sed.pricer.data;

import android.content.Context;
import sed.pricer.data.DaoMaster.DevOpenHelper;

public class DB {
	public static DaoSession inst;
	private static DevOpenHelper dbHelper;

	public static void init(Context context) {
		if (dbHelper == null ) {
			dbHelper = new DaoMaster.DevOpenHelper(context, "pricer-db", null);
		}
		inst = new DaoMaster(dbHelper.getWritableDatabase()).newSession();
	}

	public static void insert(Object obj) {
		inst.insert(obj);
	}

	public static void close() {
		if (inst != null){
			inst.clear();
			if (dbHelper != null){
				dbHelper.close();
			}
		}
	}

	public static void delete(Object item) {
		inst.delete(item);
	}

	public static void update(Object item) {
		inst.update(item);
	}
	
}
