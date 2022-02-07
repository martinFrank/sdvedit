package com.github.martinfrank.sdvedit;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SdvDocument {

    private static final String BLANK_LINE_XPATH = "//text()[normalize-space(.)='']";

    private final File xmlFile;
    private final XPath xPath = XPathFactory.newInstance().newXPath();
    private final List<SdvDocumentProperty> properties = new ArrayList<>();

    public SdvDocument(File file) {
        xmlFile = file;
    }

    public void readProperties(){
        try {
            Document doc = parseDocument();
            properties.forEach(p -> readProperty(p, doc));
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    private void readProperty(SdvDocumentProperty prop, Document doc) {
        prop.init(doc, xPath);
    }

    public void saveChanges() {
        try {
            Document doc = parseDocument();
            properties.forEach(p -> p.update(doc, xPath));
            removeBlankLines(doc);
            saveXmlDocument(doc);
        } catch (TransformerException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }

    private void saveXmlDocument(Document doc) throws TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        transformer.transform(source, result);
    }

    protected Document parseDocument() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    private void removeBlankLines(Document xmlDocument) {
        try {
            NodeList nl = (NodeList) xPath.evaluate(BLANK_LINE_XPATH, xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nl.getLength(); ++i) {
                Node node = nl.item(i);
                node.getParentNode().removeChild(node);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public void register(SdvDocumentProperty property){
        properties.add(property);
    }

}
