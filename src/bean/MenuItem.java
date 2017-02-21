package bean;

public abstract class MenuItem {

	protected String menuItemPic;
	protected String menuItemName;
	protected String menuItemDescription;
	protected String menuItemPortion;

	public String getMenuItemPic() {
		return menuItemPic;
	}
	public void setMenuItemPic(String menuItemPic) {
		this.menuItemPic = menuItemPic;
	}
	public String getMenuItemName() {
		return menuItemName;
	}
	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}
	public String getMenuItemDescription() {
		return menuItemDescription;
	}
	public void setMenuItemDescription(String menuItemDescription) {
		this.menuItemDescription = menuItemDescription;
	}
	public String getMenuItemPortion() {
		return menuItemPortion;
	}
	public void setMenuItemPortion(String menuItemPortion) {
		this.menuItemPortion = menuItemPortion;
	}
}
