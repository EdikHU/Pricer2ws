package sed.pricer.data;

import sed.pricer.data.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table T_PRICES.
 */
public class Price {

    private Long id;
    private float cost;
    /** Not-null value. */
    private java.util.Date date;
    private Long shopID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient PriceDao myDao;

    private Shop shop;
    private Long shop__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Price() {
    }

    public Price(Long id) {
        this.id = id;
    }

    public Price(Long id, float cost, java.util.Date date, Long shopID) {
        this.id = id;
        this.cost = cost;
        this.date = date;
        this.shopID = shopID;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPriceDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    /** Not-null value. */
    public java.util.Date getDate() {
        return date;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public Long getShopID() {
        return shopID;
    }

    public void setShopID(Long shopID) {
        this.shopID = shopID;
    }

    /** To-one relationship, resolved on first access. */
    public Shop getShop() {
        Long __key = this.shopID;
        if (shop__resolvedKey == null || !shop__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ShopDao targetDao = daoSession.getShopDao();
            Shop shopNew = targetDao.load(__key);
            synchronized (this) {
                shop = shopNew;
            	shop__resolvedKey = __key;
            }
        }
        return shop;
    }

    public void setShop(Shop shop) {
        synchronized (this) {
            this.shop = shop;
            shopID = shop == null ? null : shop.getId();
            shop__resolvedKey = shopID;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
	@Override
	public String toString() {
		return "Price [id=" + id + ", cost=" + cost + ", date=" + date
				+ ", shopID=" + shopID + ", shop=" + getShop()
				+ ", shop__resolvedKey=" + shop__resolvedKey + "]\n ";
	}
    // KEEP METHODS END

}