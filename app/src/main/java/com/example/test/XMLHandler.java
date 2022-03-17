package com.example.test;


import java.util.ArrayList;

import javax.xml.namespace.QName;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
	
	boolean isWord = false;
	
	String word = new String();
	
	@Override
	public void startDocument() {
		
		
		
	}
	
	@Override
    public void endDocument() throws SAXException {
		
    }
	
	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if(qName.equals("word")) {
			MainActivity.words.add(attributes.getValue(0));
		}
		
//		if(qName.equals("word")) {
//			isWord = true;
//		}
//		else {
//			isWord = false;
//		}
		
    }
	
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//    	Main.words.add(word);
//    	word = "";
//    	isWord = false;
    }
    

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    	
//    	if(isWord && word == "") {
//    		for(int i = 0; i < ch.length; i++) {
//    			word += ch[i];
//    		}
//    		System.out.println(word);
//    		
//    	}
    	
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    	
    }
    
}
