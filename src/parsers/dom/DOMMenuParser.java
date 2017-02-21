package parsers.dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import bean.MenuComplexItem;
import bean.MenuComplexItem.MenuComplexItemChoice;
import bean.MenuItem;
import bean.MenuSection;
import bean.MenuSimpleItem;

public class DOMMenuParser {

	public static List<MenuSection> parseMenu() {

		List<MenuSection> menu = new ArrayList<MenuSection>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		File f = null;
		Document doc = null;

		try {
			builder = factory.newDocumentBuilder();
			f = new File("menu.xml");
			doc = builder.parse(f);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}


		Element root = doc.getDocumentElement();

		NodeList menuSectionList = root.getChildNodes();
		MenuSection menuSection = null;

		for (int i = 0; i < menuSectionList.getLength(); i++) {
			Node child = menuSectionList.item(i);
			if (child instanceof Element) {
				Element menuSectionElement = (Element) child;
				menuSection = parseMenuSection(menuSectionElement);
				menu.add(menuSection);
			}

		}
		return menu;
	}

	public static MenuSection parseMenuSection(Element menuSectionElement) {

		MenuSection menuSection = new MenuSection();
		menuSection.setName(menuSectionElement.getAttribute("name"));

		for (Node childNode = menuSectionElement.getFirstChild(); childNode != null; childNode = childNode.getNextSibling()) {
			if (childNode instanceof Element) {
				Element menuItemElement = (Element) childNode;
				if (menuItemElement.getTagName().toUpperCase().equals("MENUSIMPLEITEM")) {
					MenuSimpleItem menuItem = new MenuSimpleItem();
					parseMenuCommonItemTags(menuItem, menuItemElement);
					menuSection.getSimpleItemList().add(menuItem);
				}
				else if (menuItemElement.getTagName().toUpperCase().equals("MENUCOMPLEXITEM")) {
					MenuComplexItem menuItem = new MenuComplexItem();
					parseMenuCommonItemTags(menuItem, menuItemElement);
					menuSection.getComplexItemList().add(menuItem);
				}
			}
		}


		return menuSection;
	}

	public static void parseMenuCommonItemTags(MenuItem menuItem, Element menuItemElement) {

		for (Node childNode = menuItemElement.getFirstChild(); childNode != null; childNode = childNode.getNextSibling()) {

			if (childNode instanceof Element) {
				Element menuItemField = (Element) childNode;
				String text = menuItemField.getTextContent();

				switch (menuItemField.getTagName().toUpperCase()) {

				case "MENUITEMPIC":
					menuItem.setMenuItemPic(text);
					break;
				case "MENUITEMNAME":
					menuItem.setMenuItemName(text);
					break;
				case "MENUITEMDESCRIPTION":
					menuItem.setMenuItemDescription(text);
					break;
				case "MENUITEMPORTION":
					menuItem.setMenuItemPortion(text);
					break;
				case "MENUITEMPRICE":
					parseMenuSimpleItemTags(menuItem, menuItemField);
					break;
				case "MENUCOMPLEXITEMCHOICE":
					parseMenuComplexItemTags(menuItem, menuItemField);
					break;
				}
			}
		}
	}

	public static void parseMenuSimpleItemTags(MenuItem menuItem, Element menuItemField) {
		MenuSimpleItem simpleItem = (MenuSimpleItem) menuItem;
		simpleItem.setMenuItemPrice(Integer.parseInt(menuItemField.getTextContent()));
	}

	public static void parseMenuComplexItemTags(MenuItem menuItem, Element menuItemField) {
		
		MenuComplexItem complexItem = (MenuComplexItem) menuItem;
		MenuComplexItemChoice choice = complexItem.new MenuComplexItemChoice();
		
		for (Node childNode = menuItemField.getFirstChild(); childNode != null; childNode = childNode.getNextSibling()) {

			if (childNode instanceof Element) {
				Element menuComplexItemField = (Element) childNode;
				String text = menuComplexItemField.getTextContent();
				switch (menuComplexItemField.getTagName().toUpperCase()) {
				case "CHOICENAME":
					choice.setChoiceName(text);
					break;
				case "PRICE":
					choice.setPrice(Integer.parseInt(menuComplexItemField.getTextContent()));
					break;
				}
			}
		}
		complexItem.getComplexItemChoiceList().add(choice);
	}
}
