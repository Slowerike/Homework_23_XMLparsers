package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class DOMParser {
    Path path;
    String firstName;
    String lastName;
    String title;
    String text;

    public DOMParser(Path path) {
        this.path = path;
    }

    public void readXML() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new File(path.toAbsolutePath().toString()));
        document.getDocumentElement().normalize();
        firstName = getNodes(document, "firstName");
        lastName = getNodes(document, "lastName");
        title = getNodes(document, "title");
        text = getNodes(document, "line");
    }

    private String getNodes(Document document, String nameOfElement) {
        StringBuilder sb = new StringBuilder();
        NodeList nodes = document.getDocumentElement().getElementsByTagName(nameOfElement);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (nodes.getLength() > 1) {
                    sb.append(element.getTextContent()).append('\n');
                } else {
                    sb.append(element.getTextContent());
                }
            }
        }
        return sb.toString();
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
