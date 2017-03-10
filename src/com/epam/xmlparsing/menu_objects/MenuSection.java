package com.epam.xmlparsing.menu_objects;

import java.util.ArrayList;
import java.util.List;

public class MenuSection {
	private String name;
	private List<MenuSimpleItem> SimpleItemList;
	private List<MenuComplexItem> ComplexItemList;
	
	public MenuSection(String name, List<MenuSimpleItem> simpleItemList, List<MenuComplexItem> complexItemList) {
		super();
		this.name = name;
		SimpleItemList = simpleItemList;
		ComplexItemList = complexItemList;
	}

	public MenuSection() {
		super();
		name = "";
		SimpleItemList = new ArrayList<MenuSimpleItem>();
		ComplexItemList = new ArrayList<MenuComplexItem>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MenuSimpleItem> getSimpleItemList() {
		return SimpleItemList;
	}

	public void setSimpleItemList(List<MenuSimpleItem> simpleItemList) {
		SimpleItemList = simpleItemList;
	}

	public List<MenuComplexItem> getComplexItemList() {
		return ComplexItemList;
	}

	public void setComplexItemList(List<MenuComplexItem> complexItemList) {
		ComplexItemList = complexItemList;
	}

	@Override
	public String toString() {
		return "MenuSection [name=" + name + ", SimpleItemList=" + SimpleItemList + ", ComplexItemList="
				+ ComplexItemList + "]";
	}
	
	

}
