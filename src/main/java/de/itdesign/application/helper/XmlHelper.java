package de.itdesign.application.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.itdesign.application.bean.City;
import de.itdesign.application.bean.Operation;
import de.itdesign.application.bean.Result;

public class XmlHelper {

	private DocumentBuilder documentBuilder;
	private final static XmlHelper XML_HELPER = new XmlHelper();

	private XmlHelper(){
		try {
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (Exception ex) {

		}
	}

	public static XmlHelper newInstance() {
		return XML_HELPER;
	}

	public List<City> readData(String src, Operation operation) throws SAXException, IOException {
		ArrayList<City> data = new ArrayList<City>();

		Document document = documentBuilder.parse(new File(src));

		NodeList nList = document.getElementsByTagName("city");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			Element eElement = (Element) nNode;

			String name = eElement.getAttribute("name");

			if (!name.matches(operation.getFilter())) {
				continue;
			}

			double attribValue = parseDouble(eElement.getAttribute(operation.getAttrib()));
			double area = this.getArea(eElement);

			data.add(new City(name, attribValue, area));
		}
		return data;
	}

	private double parseDouble(String attribute) {

		if (attribute == null)
			return 0;

		try {
			return Double.parseDouble(attribute);

		} catch (NumberFormatException nfe) {
			return 0.0;
		}
	}

	public List<Operation> readOpearations(String src) throws SAXException, IOException {
		ArrayList<Operation> operations = new ArrayList<Operation>();

		Document document = documentBuilder.parse(new File(src));

		NodeList nList = document.getElementsByTagName("operation");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			Element eElement = (Element) nNode;

			String name = eElement.getAttribute("name");
			String type = eElement.getAttribute("type");
			String func = eElement.getAttribute("func");
			String attrib = eElement.getAttribute("attrib");
			String filter = eElement.getAttribute("filter");

			operations.add(new Operation(name, type, func, attrib, filter));
		}
		return operations;
	}

	public void saveResult(List<Result> results, String des) throws TransformerException {
		Document document = documentBuilder.newDocument();

		Element root = document.createElement("results");
		document.appendChild(root);

		for (Result result : results) {
			Element eResult = document.createElement("result");
			eResult.setAttribute("name", result.getName());
			eResult.setTextContent(String.valueOf(result.getValue()));
			root.appendChild((Node) eResult);
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new File(des));

		transformer.transform(domSource, streamResult);
	}

	private double getArea(Element element) {
		double area = 0.0;

		NodeList childNodes = element.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item = childNodes.item(i);

			if (item == null) {
				continue;
			}

			String nodeName = item.getNodeName();

			if (nodeName.equalsIgnoreCase("area")) {
				String nodeValue = item.getTextContent();
				area = this.parseDouble(nodeValue);
				break;
			}
		}

		return area;
	}

}
