package com.epam.xmlparsing.parsers.dom;

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

import com.epam.xmlparsing.menu_objects.MenuComplexItem;
import com.epam.xmlparsing.menu_objects.MenuItem;
import com.epam.xmlparsing.menu_objects.MenuSection;
import com.epam.xmlparsing.menu_objects.MenuSimpleItem;
import com.epam.xmlparsing.menu_objects.MenuComplexItem.MenuComplexItemChoice;
import com.epam.xmlparsing.utils.TagName;

public class DOMMenuParser {

	public static List<MenuSection> parseMenu() {

		List<MenuSection> menu = new ArrayList<MenuSection>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String filePath = "menu.xml";

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File f = new File(filePath);
			Document doc = builder.parse(f);
			Element root = doc.getDocumentElement();
			NodeList menuSectionList = root.getChildNodes();
			MenuSection menuSection = null;

			for (int i = 0; i < menuSectionList.getLength(); i++) {
				Node child = menuSectionList.item(i);
				if (child.getNodeType() == Node.ELEMENT_NODE) {
					Element menuSectionElement = (Element) child;
					menuSection = parseMenuSection(menuSectionElement);
					menu.add(menuSection);
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return menu;
	}

	public static MenuSection parseMenuSection(Element menuSectionElement) {

		MenuSection menuSection = new MenuSection();
		menuSection.setName(menuSectionElement.getAttribute("name"));

		for (Node childNode = menuSectionElement.getFirstChild(); childNode != null; childNode = childNode.getNextSibling()) {
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element menuItemElement = (Element) childNode;
				String tag = menuItemElement.getTagName().toUpperCase();
				
				if (TagName.valueOf(tag).equals(TagName.MENUSIMPLEITEM)) {
					
					MenuSimpleItem menuItem = new MenuSimpleItem();
					parseMenuCommonItemTags(menuItem, menuItemElement);
					menuSection.getSimpleItemList().add(menuItem);
				}
				else if (TagName.valueOf(tag).equals(TagName.MENUCOMPLEXITEM)) {
					
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

			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element menuItemField = (Element) childNode;
				String text = menuItemField.getTextContent();
				TagName tag = TagName.valueOf(menuItemField.getTagName().toUpperCase());

				switch (tag) {

				case MENUITEMPIC:
					menuItem.setMenuItemPic(text);
					break;
				case MENUITEMNAME:
					menuItem.setMenuItemName(text);
					break;
				case MENUITEMDESCRIPTION:
					menuItem.setMenuItemDescription(text);
					break;
				case MENUITEMPORTION:
					menuItem.setMenuItemPortion(text);
					break;
				case MENUITEMPRICE:
					parseMenuSimpleItemTags(menuItem, menuItemField);
					break;
				case MENUCOMPLEXITEMCHOICE:
					parseMenuComplexItemTags(menuItem, menuItemField);
					break;
				}
			}
		}
	}

	public static void parseMenuSimpleItemTags(MenuItem menuItem, Element menuItemField) {

		MenuSimpleItem simpleItem = (MenuSimpleItem) menuItem;
		try {
			simpleItem.setMenuItemPrice(Integer.parseInt(menuItemField.getTextContent()));
		} catch (NumberFormatException e) {
			//assigning default value
			simpleItem.setMenuItemPrice(0);
			e.printStackTrace(); 
		}
	} 

	public static void parseMenuComplexItemTags(MenuItem menuItem, Element menuItemField) {

		MenuComplexItem complexItem = (MenuComplexItem) menuItem;
		MenuComplexItemChoice choice = complexItem.new MenuComplexItemChoice();

		for (Node childNode = menuItemField.getFirstChild(); childNode != null; childNode = childNode.getNextSibling()) {

			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element menuComplexItemField = (Element) childNode;
				String text = menuComplexItemField.getTextContent();
				TagName tag = TagName.valueOf(menuComplexItemField.getTagName().toUpperCase());

				switch (tag) {

				case CHOICENAME:
					choice.setChoiceName(text);
					break;
				case PRICE:
					choice.setPrice(Integer.parseInt(menuComplexItemField.getTextContent()));
					break;
				}
			}
		}
		complexItem.getComplexItemChoiceList().add(choice);
	}
}
