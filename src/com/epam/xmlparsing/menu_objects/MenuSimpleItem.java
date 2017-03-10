package com.epam.xmlparsing.menu_objects;

public class MenuSimpleItem extends MenuItem {
	
	private int menuItemPrice;
	
	public MenuSimpleItem(String menuItemPic, String menuItemName, String menuItemDescription, String menuItemPortion,
			int menuItemPrice) {
		super();
		this.menuItemPic = menuItemPic;
		this.menuItemName = menuItemName;
		this.menuItemDescription = menuItemDescription;
		this.menuItemPortion = menuItemPortion;
		this.menuItemPrice = menuItemPrice;
	}
	
	public MenuSimpleItem() {
		super();
	}
	
	public int getMenuItemPrice() {
		return menuItemPrice;
	}
	public void setMenuItemPrice(int menuItemPrice) {
		this.menuItemPrice = menuItemPrice;
	}

	@Override
	public String toString() {
		return "MenuSimpleItem [menuItemPic=" + menuItemPic + ", menuItemName=" + menuItemName
				+ ", menuItemDescription=" + menuItemDescription + ", menuItemPortion=" + menuItemPortion
				+ ", menuItemPrice=" + menuItemPrice + "]";
	}

	
}
