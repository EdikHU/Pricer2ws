package sed.pricer.data;

import java.io.Serializable;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table T_FACTORY.
 */
public class Factory implements Serializable{

    private Long id;
    private String name;

    // KEEP FIELDS - put your custom fields here
	private static final long serialVersionUID = 4341726176126605924L;
    // KEEP FIELDS END

    public Factory() {
    }

    public Factory(Long id) {
        this.id = id;
    }

    public Factory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // KEEP METHODS - put your custom methods here
	@Override
	public String toString() {
		return "" + name + " ("+id+")";
	}
    // KEEP METHODS END

}
