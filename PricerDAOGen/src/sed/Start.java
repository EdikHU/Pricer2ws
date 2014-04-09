package sed;


import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

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
		shop.addStringProperty("name");
		
		
		
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

		// price
		Entity price = s.addEntity("Price");
		price.setHasKeepSections(true);
		price.setTableName("T_PRICES");
		price.addIdProperty();
		price.addFloatProperty("cost");
		Property priceDateProp = price.addDateProperty("date").getProperty();
		
		Property shopId = price.addLongProperty("shopId").getProperty();
		price.addToOne(shop, shopId);
		
		Property productId = price.addLongProperty("productId").getProperty();
		price.addToOne(product, productId);

		ToMany productToPrices = product.addToMany(price, productId);
		productToPrices.setName("prices");
		productToPrices.orderAsc(priceDateProp);
		
	}

}

//----------------
//Entity relation = s.addEntity("Relation");
//relation.setHasKeepSections(true);
//relation.setTableName("T_THE_RELATION");
//relation.addIdProperty();
//Property relation_theHolderId = relation.addLongProperty("relation_theHolderId").getProperty();
//Property relation_theItemId = relation.addLongProperty("relation_theItemId").getProperty();
//
//Entity theItem = s.addEntity("TheItem");
//theItem.setHasKeepSections(true);
//theItem.setTableName("T_THE_ITEM");
//theItem.addIdProperty();
//theItem.addStringProperty("name");
////Property item_theHolderId = theItem.addLongProperty("item_theHolderId").getProperty();
//
//Entity theHolder = s.addEntity("TheHolder");
//theHolder.setHasKeepSections(true);
//theHolder.setTableName("T_THE_HOLDER");
//theHolder.addIdProperty();
//theHolder.addStringProperty("name");
//
////theItem.addToOne(theHolder, item_theHolderId);
//
////theHolder.addToMany(theItem, item_theHolderId);
//
//relation.addToOne(theItem, relation_theItemId);
//relation.addToOne(theHolder, relation_theHolderId);
//
//ToMany itemToHolder = theItem.addToMany(relation, relation_theItemId);
//itemToHolder.setName("holders");
//ToMany holderToItems = theHolder.addToMany(relation, relation_theHolderId);
//holderToItems.setName("items");

