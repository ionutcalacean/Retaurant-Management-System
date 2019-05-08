package BusinessLayer;

public class ItemsChiefs {
	private String comandaNoua;

	public ItemsChiefs(String itemName) {
		super();
		this.comandaNoua = itemName;
	}

	public String getItemName() {
		return comandaNoua;
	}

	public void setItemName(String itemName) {
		this.comandaNoua = itemName;
	}

}
