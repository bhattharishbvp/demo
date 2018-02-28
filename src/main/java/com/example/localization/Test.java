package com.example.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class Test {

	public static void main(String[] args) {
		ResourceBundle resBun_en = ResourceBundle.getBundle("properties", new Locale("en"));
		
		System.out.println(resBun_en.getString("ok"));
		
		
		ResourceBundle resBun_de = ResourceBundle.getBundle("properties", new Locale("de"));
		
		System.out.println(resBun_de.getString("ok"));
	}
}
