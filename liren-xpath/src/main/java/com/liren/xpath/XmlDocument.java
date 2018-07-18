package com.liren.xpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlDocument {

	@Test
	public void createDom() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			File file = new File("D:/GitRepository/temp/study-demo/liren-xpath/src/main/resources/demo.xml");
//			File file = new File("F:/test.txt");
//			boolean exists = file.exists();
//			FileInputStream fileInputStream = new FileInputStream(file);
//			int read = fileInputStream.read();
			Document doc = db.parse("D:/GitRepository/temp/study-demo/liren-xpath/src/main/resources/demo.xml");
			
			Element documentElement = doc.getDocumentElement();
			String tagName = documentElement.getTagName();
			String version = documentElement.getAttribute("version");
			
			NodeList bookElements = doc.getElementsByTagName("book");
			for (int i = 0; i < bookElements.getLength(); i++) {
				Node item = bookElements.item(i);
				String nodeName = item.getNodeName();
				NodeList childNodes = item.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					Node item2 = childNodes.item(j);
					String nodeName2 = item2.getNodeName();
					if(item2.hasChildNodes()){
						String nodeValue = item2.getFirstChild().getNodeValue();
						System.out.println();
					}
					System.out.println();
				}
				
				Node firstChild = item.getFirstChild();
				String nodeName2 = firstChild.getNodeName();
				Node firstChild2 = firstChild.getFirstChild();
				String nodeName3 = firstChild2.getNodeName();
				System.out.println();
//				String title = item.getFirstChild().getFirstChild().getNodeValue();
//				String price = item.getLastChild().getFirstChild().getNodeValue();
//				System.out.println("title:" + title + ",price:" + price);
				
			}
			
//			NodeList elementsByTagName = doc.getElementsByTagName("channel");
//			for (int i = 0; i < elementsByTagName.getLength(); i++) {
//				Element node = (Element)elementsByTagName.item(i);
//				System.out.println("title: "+ node.getElementsByTagName("title").item(0).getFirstChild().getNodeValue());
//			}
			
//			Document doc = db.parse("D:/GitRepository/temp/study-demo/liren-xpath/src/main/resources/demo.xml");
//			String localName = doc.getLocalName();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
