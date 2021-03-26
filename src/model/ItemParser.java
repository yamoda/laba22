package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.ArrayList;

public class ItemParser {
    public void parseAndWrite(String rootTag, String nodeTag, ArrayList<Item> parsedModelArray, String writePath) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document writtenDocument = documentBuilder.newDocument();

            Element rootElement = writtenDocument.createElement(rootTag);
            writtenDocument.appendChild(rootElement);


            for (Item currentEntry : parsedModelArray) {
                String name = currentEntry.getName();
                String manufactName = currentEntry.getManufactName();
                String manufactNumber = String.valueOf(currentEntry.getManufactNumber());
                String amount = String.valueOf(currentEntry.getAmount());
                String address = currentEntry.getAddress();

                rootElement.appendChild(getEntry(writtenDocument, nodeTag, name, manufactName, manufactNumber, amount, address));
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource writtenSource = new DOMSource(writtenDocument);

            StreamResult streamResult = new StreamResult(new File(writePath));
            transformer.transform(writtenSource, streamResult);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    private Node getEntry(Document writtenDocument, String nodeTag, String name, String manufactName, String manufactNumber,
                          String amount, String address) {

        Element newEntry = writtenDocument.createElement(nodeTag);

        newEntry.appendChild(getEntryElements(writtenDocument, newEntry, "name", name));
        newEntry.appendChild(getEntryElements(writtenDocument, newEntry, "manufactName", manufactName));
        newEntry.appendChild(getEntryElements(writtenDocument, newEntry, "manufactNumber", manufactNumber));
        newEntry.appendChild(getEntryElements(writtenDocument, newEntry, "amount", amount));
        newEntry.appendChild(getEntryElements(writtenDocument, newEntry, "address", address));

        return newEntry;
    }

    private Node getEntryElements(Document writtenDocument, Element element, String name, String value) {
        Element node = writtenDocument.createElement(name);
        node.appendChild(writtenDocument.createTextNode(value));
        return node;
    }

    public ArrayList<Item> readAndParse(String parsePath) {
        ArrayList<Item> patients=null;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            var documentModelReader = factory.newSAXParser();
            var writerHandler = new ParserHandler();

            documentModelReader.parse(parsePath, writerHandler);
            patients = writerHandler.getItems();
        }
        catch (Exception e) { e.printStackTrace(); }
        return patients;
    }
}
