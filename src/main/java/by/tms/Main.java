package org.example;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Path pathToFile = Path.of("src/main/resources/sonnet.xml");
        System.out.println("Введите способ для распарсинга : 1-Распарсить с помощь SAX ,2-Распарсить с помощью DOM ");
        String typeOfParser = scanner.nextLine().trim();
        switch (typeOfParser) {
            case "1" -> {
                try{SAXParser saxParser= new SAXParser(pathToFile);
                    saxParser.parseXML();
                    new FileWriterXml(saxParser.getFirstName(), saxParser.getLastName(), saxParser.getTitle(), saxParser.getText()).write();
                } catch (ParserConfigurationException | IOException | SAXException e) {
                    throw new RuntimeException(e);
                }
            }
            case "2" -> {
                try{DOMParser domParser= new DOMParser(pathToFile);
                    domParser.readXML();
                    new FileWriterXml(domParser.getFirstName(), domParser.getLastName(), domParser.getTitle(), domParser.getText()).write();
                } catch (ParserConfigurationException | IOException | SAXException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> System.out.println("Invalid type");
        }
    }
}