package com.github.martinfrank.sdvedit;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

public class SdvDocumentProperty {

    public static final String UNDEFINED_STRING = "";
    public final String identifier;
    private final String xpathExpression;
    private final String undefined;
    private String original;
    private String current;

    public SdvDocumentProperty(String identifier, String xpathExpression) {
        this(identifier, xpathExpression, UNDEFINED_STRING);
    }

    public SdvDocumentProperty(String identifier, String xpathExpression, String undefinedValue) {
        this.identifier = identifier;
        this.xpathExpression = xpathExpression;
        this.undefined = undefinedValue;
    }

    public void init(Document xmlDocument, XPath xPath) {
        try {
            original = (String) xPath.evaluate(xpathExpression, xmlDocument, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            original = undefined;
        }
        current = original;
    }

    public void update(Document xmlDocument, XPath xPath) {
        try {
            Node node = (Node) xPath.evaluate(xpathExpression, xmlDocument, XPathConstants.NODE);
            node.setTextContent(current);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentValue() {
        return current;
    }

    public void setCurrent(String newValue) {
        current = newValue;
    }

    public String getOriginalValue() {
        return original;
    }
}
