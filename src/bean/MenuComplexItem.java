package bean;

import java.util.ArrayList;
import java.util.List;

public class MenuComplexItem extends MenuItem {

	private List<MenuComplexItemChoice> complexItemChoiceList;

	public MenuComplexItem(String menuItemPic, String menuItemName, String menuItemDescription, String menuItemPortion,
			int menuItemPrice, List<MenuComplexItemChoice> complexItemChoiceList) {
		this.menuItemPic = menuItemPic;
		this.menuItemName = menuItemName;
		this.menuItemDescription = menuItemDescription;
		this.menuItemPortion = menuItemPortion;
		this.complexItemChoiceList = complexItemChoiceList;
	}

	public MenuComplexItem() {
		super();
		complexItemChoiceList = new ArrayList<MenuComplexItemChoice>();
	}

	public List<MenuComplexItemChoice> getComplexItemChoiceList() {
		return complexItemChoiceList;
	}

	public void setComplexItemChoiceList(List<MenuComplexItemChoice> complexItemChoiceList) {
		this.complexItemChoiceList = complexItemChoiceList;
	}


	public class MenuComplexItemChoice {
		private String choiceName;
		private int price;

		public MenuComplexItemChoice(String choiceName, int price) {
			this.choiceName = choiceName;
			this.price = price;
		}

		public MenuComplexItemChoice() {
			super();
		}

		public String getChoiceName() {
			return choiceName;
		}

		public void setChoiceName(String choiceName) {
			this.choiceName = choiceName;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		@Override
		public String toString() {
			return "menuComplexItemChoice [choiceName=" + choiceName + ", price=" + price + "]";
		}

	}


	@Override
	public String toString() {
		return "MenuComplexItem [menuItemPic=" + menuItemPic + ", menuItemName=" + menuItemName
				+ ", menuItemDescription=" + menuItemDescription + ", menuItemPortion=" + menuItemPortion
				+ ", complexItemChoiceList=" + complexItemChoiceList + "]";
	}


}
