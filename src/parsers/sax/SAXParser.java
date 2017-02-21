package parsers.sax;

import java.io.IOException;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import bean.MenuSection;

public class SAXParser {
	
public static List<MenuSection> parseMenu() {
		
		List<MenuSection> menu = null;
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			SAXHandler handler = new SAXHandler();
			reader.setContentHandler(handler);
		
			reader.parse(new InputSource("menu.xml"));
			reader.setFeature("http://xml.org/sax/features/validation", true);
			reader.setFeature("http://xml.org/sax/features/namespaces", true);
			reader.setFeature("http://xml.org/sax/features/string-interning", true);
			reader.setFeature("http://apache.org/xml/features/validation/schema", false);
			
			menu = handler.getMenu();
		} 
		catch (IOException e){
			e.printStackTrace();
		} 
		catch (SAXException e){
			e.printStackTrace();
		}
		return menu;
	}

}
