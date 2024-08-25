package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;

public class SAXParser {
    Path path;
    String firstName;
    String lastName;
    String title;
    String text;
    String thisElement = " ";


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


    public SAXParser(Path path) {
        this.path = path;
    }

    public void parseXML() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
        StringBuilder firstNames = new StringBuilder();
        StringBuilder lastNames = new StringBuilder();
        StringBuilder titles = new StringBuilder();
        StringBuilder texts = new StringBuilder();
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes)  {
                thisElement = qName;
            }
            @Override
            public void characters(char[] ch, int start, int length)  {
                if (thisElement.equalsIgnoreCase("firstName")) {
                    firstNames.append(new String(ch, start, length));
                } else if (thisElement.equalsIgnoreCase("lastName")) {
                    lastNames.append(new String(ch, start, length));
                } else if (thisElement.equalsIgnoreCase("title")) {
                    titles.append(new String(ch, start, length));
                } else if (thisElement.equalsIgnoreCase("line")) {
                    texts.append(new String(ch, start, length)).append('\n');
                }
            }
            @Override
            public void endElement(String uri, String localName, String qName)  {
                thisElement = " ";
            }
        };
        saxParser.parse(path.toAbsolutePath().toString(), handler);
        firstName = firstNames.toString();
        lastName = lastNames.toString();
        title = titles.toString();
        text = texts.toString();
    }
}
