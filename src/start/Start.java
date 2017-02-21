package start;

import java.util.List;


import bean.MenuSection;
import parsers.dom.DOMMenuParser;
import parsers.sax.SAXParser;
import parsers.stax.StAXParser;

public class Start {

	public static void printMenu(List<MenuSection> menu){
		for (int i = 0; i < menu.size(); i++){
			System.out.println(menu.get(i).toString());
		}
	}

	public static void main(String[] args) {

		List<MenuSection> menu;

		menu = SAXParser.parseMenu();
		System.out.println("By SAX:");
		printMenu(menu);

		menu = StAXParser.parseMenu();
		System.out.println("By StAX:");
		printMenu(menu);

		menu = DOMMenuParser.parseMenu();
		System.out.println("By DOM:");
		printMenu(menu);

	}

}

