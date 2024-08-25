package org.example;

import java.io.FileWriter;
import java.io.IOException;


public class FileWriterXml {
    String firstName;
    String lastName;
    String title;
    String text;

    public FileWriterXml(String firstName, String lastName, String title, String text) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.text = text;
    }

    public void write() {
        String path = String.format("src/main/resources/%s_%s_%s.txt", firstName, lastName, title);
        try (FileWriter fileWriter = new FileWriter(path)) {
           fileWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
