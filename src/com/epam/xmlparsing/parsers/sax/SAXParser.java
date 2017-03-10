package com.epam.xmlparsing.parsers.sax;

import java.io.IOException;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.xmlparsing.menu_objects.MenuSection;

public class SAXParser {
	
public static List<MenuSection> parseMenu() {
		
		List<MenuSection> menu = null;
		
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			SAXHandler handler = new SAXHandler();
			reader.setContentHandler(handler);
			reader.parse(new InputSource("menu.xml"));
			menu = handler.getMenu();
		} 
		catch (IOException | SAXException e){
			e.printStackTrace();
		} 
		return menu;
	}
}
