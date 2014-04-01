package sed;


import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class Start {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, Exception {
		System.out.println("***");
		Schema schema = new Schema(1, "sed.pricer.data");
		createDB(schema);
		new DaoGenerator().generateAll(schema,"../Pricer/src");
		
	}

	private static void createDB(Schema s) {
		

		// shop
		Entity shop = s.addEntity("Shop");
		shop.setHasKeepSections(true);
		shop.setTableName("T_SHOPS");
		shop.addIdProperty().autoincrement();
		shop.addStringProperty("name").notNull();
		
		// price
		Entity price = s.addEntity("Price");
		price.setHasKeepSections(true);
		price.setTableName("T_PRICES");
		price.addIdProperty();
		price.addFloatProperty("cost").notNull();
		Property priceDateProp = price.addDateProperty("date").notNull().getProperty();
		Property shopIdProp = price.addLongProperty("shopID").getProperty();
		price.addToOne(shop, shopIdProp);

		// category
		Entity category = s.addEntity("Category");
		category.setHasKeepSections(true);
		category.setTableName("T_CATEGORY");
		category.addIdProperty();
		category.addStringProperty("name");
		
		// group
		Entity group = s.addEntity("Group");
		group.setHasKeepSections(true);
		group.setTableName("T_GROUP");
		group.addIdProperty();
		group.addStringProperty("name");
		Property categoryId = group.addLongProperty("categoryId").getProperty();
		group.addToOne(category, categoryId);

		
		//manufactured
		Entity factory = s.addEntity("Factory");
		factory.setHasKeepSections(true);
		factory.setTableName("T_FACTORY");
		factory.addIdProperty();
		factory.addStringProperty("name");
		
		// product
		Entity product = s.addEntity("Product");
		product.setHasKeepSections(true);
		product.setTableName("T_PRODUCT");
		product.addIdProperty();
		product.addStringProperty("name");
		
		Property factoryId = product.addLongProperty("factoryId").getProperty();
		product.addToOne(factory, factoryId);
		
		Property groupId = product.addLongProperty("groupId").getProperty();
		product.addToOne(group, groupId);

		Property priceId = product.addLongProperty("priceId").getProperty();
		product.addToMany(price, priceId).orderAsc(priceDateProp);
		
		
	}

}
