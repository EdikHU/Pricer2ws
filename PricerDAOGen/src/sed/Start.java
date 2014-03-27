package sed;


import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Start {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, Exception {
		System.out.println("**");
		Schema schema = new Schema(1, "sed.pricer.data");
		createDB(schema);
		new DaoGenerator().generateAll(schema,"../Pricer/src");
		
	}

	private static void createDB(Schema s) {
		
		// product
		
		// group/category
		
		// price
		
		// shop
		Entity shop = s.addEntity("Shop");
		shop.setTableName("SHOPS");
		shop.addIdProperty();
		shop.addStringProperty("name").notNull();
		
	}

}
