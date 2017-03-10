package com.epam.xmlparsing.parsers.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.epam.xmlparsing.menu_objects.MenuComplexItem;
import com.epam.xmlparsing.menu_objects.MenuItem;
import com.epam.xmlparsing.menu_objects.MenuSection;
import com.epam.xmlparsing.menu_objects.MenuSimpleItem;
import com.epam.xmlparsing.menu_objects.MenuComplexItem.MenuComplexItemChoice;
import com.epam.xmlparsing.utils.TagName;

public class StAXParser {

	public static List<MenuSection> parseMenu(){

		List<MenuSection> menu = null;
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try{
			InputStream input = new FileInputStream("menu.xml");
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);

			menu = process(reader);
		}
		catch(XMLStreamException | FileNotFoundException e){
			e.printStackTrace();
		}
		return menu;
	}

	private static List<MenuSection> process(XMLStreamReader reader) throws XMLStreamException{
		
		List<MenuSection> menu = new ArrayList<MenuSection>();
		
		MenuSection menuSection = null;
		MenuSimpleItem menuSimpleItem = null;
		MenuComplexItem menuComplexItem = null;
		MenuComplexItemChoice menuComplexItemChoice = null;
		MenuItem menuItem = null;

		TagName tag = null;
		String localName = null;

		while(reader.hasNext()){
			int type = reader.next();

			switch(type){

			case XMLStreamConstants.START_ELEMENT:

				localName = reader.getLocalName();
				tag = TagName.valueOf(localName.toUpperCase());

				switch (tag) {

				case MENUSECTION:
					menuSection = new MenuSection(); 
					menuSection.setName(reader.getAttributeValue(null, "name"));
					break;
				case MENUSIMPLEITEM:
					menuSimpleItem = new MenuSimpleItem();
					menuItem = menuSimpleItem;
					break;
				case MENUCOMPLEXITEM:
					menuComplexItem = new MenuComplexItem();
					menuItem = menuComplexItem;
					break;
				case MENUCOMPLEXITEMCHOICE:
					menuComplexItemChoice = menuComplexItem.new MenuComplexItemChoice();
					break;
				default:
					break;
				}
				break;

			case XMLStreamConstants.CHARACTERS:

				String text = reader.getText().trim();
				
				if(text.isEmpty()){
					break;
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
				default:
					break;
				}
				break;

			case XMLStreamConstants.END_ELEMENT:

				localName = reader.getLocalName();
				tag = TagName.valueOf(localName.toUpperCase());

				switch (tag) {

				case MENUSIMPLEITEM:
					menuSection.getSimpleItemList().add(menuSimpleItem);
					menuSimpleItem = null;
					menuItem = null;
					break;
				case MENUCOMPLEXITEM:
					menuSection.getComplexItemList().add(menuComplexItem);
					menuComplexItem = null;
					menuItem = null;
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
		return menu;
	}
}
