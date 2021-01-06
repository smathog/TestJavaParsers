package com.Parsers.XMLComponents;

import java.util.ArrayList;

public class XMLTag extends XMLContent {
    private String element;
    private ArrayList<XMLAttribute> attributes;
    private XMLContent content;

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public ArrayList<XMLAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<XMLAttribute> attributes) {
        this.attributes = attributes;
    }

    public XMLContent getContent() {
        return content;
    }

    public void setContent(XMLContent content) {
        this.content = content;
    }
}
