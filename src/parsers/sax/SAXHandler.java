package parsers.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import bean.MenuComplexItem;
import bean.MenuComplexItem.MenuComplexItemChoice;
import bean.MenuItem;
import bean.MenuSection;
import bean.MenuSimpleItem;
import bean.TagName;

public class SAXHandler extends DefaultHandler {

	private List<MenuSection> menu = new ArrayList<MenuSection>();
	private MenuSection menuSection;
	private MenuSimpleItem menuSimpleItem;
	private MenuComplexItem menuComplexItem;
	private MenuComplexItemChoice menuComplexItemChoice;
	private MenuItem menuItem;
	private StringBuilder text;

	public List<MenuSection> getMenu() { 
		return menu;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		text = new StringBuilder();

		TagName tag = TagName.valueOf(qName.toUpperCase());

		switch(tag) {

		case MENUSECTION:
			menuSection = new MenuSection(); 
			menuSection.setName(attributes.getValue("name"));
			break;
		case MENUSIMPLEITEM:
			menuSimpleItem = new MenuSimpleItem();
			break;
		case MENUCOMPLEXITEM:
			menuComplexItem = new MenuComplexItem();
			break;
		case MENUCOMPLEXITEMCHOICE:
			menuComplexItemChoice = menuComplexItem.new MenuComplexItemChoice();
			break;
		default:
			break;
		}

	}

	public void characters(char[] buffer, int start, int length){
		text.append(buffer, start, length);
		text.trimToSize();
	}

	public void endElement(String uri, String localName, String qName) 
			throws SAXException {

		TagName tag = TagName.valueOf(qName.toUpperCase());
		
		if (menuSimpleItem == null) {
			menuItem = menuComplexItem;
		}
		else {
			menuItem = menuSimpleItem;
		}

		switch (tag) {

		case MENUITEMPIC:
			menuItem.setMenuItemPic(text.toString());
			break;
		case MENUITEMNAME:
			menuItem.setMenuItemName(text.toString());
			break;
		case MENUITEMDESCRIPTION:
			menuItem.setMenuItemDescription(text.toString());
			break;
		case MENUITEMPORTION:
			menuItem.setMenuItemPortion(text.toString());
			break;
		case MENUITEMPRICE:
			menuSimpleItem.setMenuItemPrice(Integer.parseInt(text.toString()));;
			break;
		case CHOICENAME:
			menuComplexItemChoice.setChoiceName(text.toString());
			break;
		case PRICE:
			menuComplexItemChoice.setPrice(Integer.parseInt(text.toString()));
			break;
		case MENUSIMPLEITEM:
			menuSection.getSimpleItemList().add(menuSimpleItem);
			menuSimpleItem = null;
			break;
		case MENUCOMPLEXITEM:
			menuSection.getComplexItemList().add(menuComplexItem);
			menuComplexItem = null;
			break;
		case MENUCOMPLEXITEMCHOICE:
			menuComplexItem.getComplexItemChoiceList().add(menuComplexItemChoice);
			break;
		case MENUSECTION:
			menu.add(menuSection);
			break;
		default:
			break;
		}
	}

}
