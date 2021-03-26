package model;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ParserHandler extends DefaultHandler {
    private ArrayList<Item> items = new ArrayList<Item>();

    private Stack elementStack = new Stack();
    private Stack objectStack = new Stack();

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.elementStack.push(qName);

        if ("Item".equals(qName)) {
            Item newItem = new Item();
            this.objectStack.push(newItem);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();

        if (value.length() == 0) return;

        if ("name".equals(currentElement())) {
            Item newItem = (Item) this.objectStack.peek();
            newItem.setName(value);
        }
        else if ("manufactName".equals(currentElement())) {
            Item newItem = (Item) this.objectStack.peek();
            newItem.setManufactName(value);
        }
        else if ("manufactNumber".equals(currentElement())) {
            Item newItem = (Item) this.objectStack.peek();
            newItem.setManufactNumber(Integer.parseInt(value));
        }
        else if ("amount".equals(currentElement())) {
            Item newItem = (Item) this.objectStack.peek();
            newItem.setAmount(Integer.parseInt(value));
        }
        else if ("address".equals(currentElement())) {
            Item newItem = (Item) this.objectStack.peek();
            newItem.setAddress(value);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.elementStack.pop();

        if ("Item".equals(qName)) {
            Item object = (Item) this.objectStack.pop();
            this.items.add(object);
        }
    }

    private String currentElement() {
        return (String) this.elementStack.peek();
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
