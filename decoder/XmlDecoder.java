package test.decoder;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlDecoder {
	public static void init(String toDecodeXML) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(toDecodeXML));
		Element root = document.getRootElement();
		traverseNodes(root);
	}

	public static void traverseNodes(Element node) {
		System.out.println("当前遍历节点名称：" + node.getName());
		List<Attribute> attributes = node.attributes();
		for (Attribute attribute : attributes) {
			System.out.println("属性名：" + attribute.getName() + "\t属性值：" + attribute.getValue());
		}
		if (!node.getTextTrim().equals("")) {
			System.out.println("节点名：" + node.getName() + "\t节点内容：" + node.getText());
		}
		Iterator<Element> it = node.elementIterator();
		while (it.hasNext()) {
			Element element = it.next();
			traverseNodes(element);
		}
	}

	public static void main(String[] args) throws DocumentException {
		// TODO Auto-generated method stub
		init("src/test/xml/NewFile.xml");
	}

}
