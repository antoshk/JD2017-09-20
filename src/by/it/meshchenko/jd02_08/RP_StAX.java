package by.it.meshchenko.jd02_08;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class RP_StAX {

    private static String tab = "";

    public static void parse(String path) {
        try {
            FileInputStream input = new FileInputStream(path);
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            while(reader.hasNext()){
                printEvent(reader);
                reader.next();
            }
        } catch (Exception e) {
            System.out.print("StAX: Ошибка! " + e.toString());
        }
    }

    private static void printEvent(XMLStreamReader xmlr) {
        switch (xmlr.getEventType()) {
            case XMLStreamConstants.START_ELEMENT:
                System.out.print(tab + "<");
                tab = tab + "\t";
                printName(xmlr);
                printNamespaces(xmlr);
                printAttributes(xmlr);
                System.out.println(">");
                break;
            case XMLStreamConstants.END_ELEMENT:
                tab = tab.substring(1);
                System.out.print(tab + "</");
                printName(xmlr);
                System.out.println(">");
                break;
            case XMLStreamConstants.SPACE:
            case XMLStreamConstants.CHARACTERS:
                int start = xmlr.getTextStart();
                int length = xmlr.getTextLength();
                String text = new String(xmlr.getTextCharacters(), start, length).trim();
                if(!text.isEmpty()) System.out.println(tab + text);
                break;
            case XMLStreamConstants.PROCESSING_INSTRUCTION:
                System.out.print("<?");
                if (xmlr.hasText())
                    System.out.print(xmlr.getText());
                System.out.println("?>");
                break;
            case XMLStreamConstants.CDATA:
                System.out.print("<![CDATA[");
                start = xmlr.getTextStart();
                length = xmlr.getTextLength();
                System.out.print(new String(xmlr.getTextCharacters(),
                        start,
                        length));
                System.out.println("]]>");
                break;
            case XMLStreamConstants.COMMENT:
                System.out.print("<!--");
                if (xmlr.hasText())
                    System.out.print(xmlr.getText());
                System.out.println("-->");
                break;
            case XMLStreamConstants.ENTITY_REFERENCE:
                System.out.print(xmlr.getLocalName()+"=");
                if (xmlr.hasText())
                    System.out.print("["+xmlr.getText()+"]");
                break;
            case XMLStreamConstants.START_DOCUMENT:
                System.out.print("<?xml");
                System.out.print(" version='"+xmlr.getVersion()+"'");
                System.out.print(" encoding='"+xmlr.getCharacterEncodingScheme()+"'");
                if (xmlr.isStandalone())
                    System.out.print(" standalone='yes'");
                else
                    System.out.print(" standalone='no'");
                System.out.println("?>");
                break;
        }
    }
    private static void printName(XMLStreamReader xmlr){
        if(xmlr.hasName()){
            String prefix = xmlr.getPrefix();
            String uri = xmlr.getNamespaceURI();
            String localName = xmlr.getLocalName();
            printName(prefix,uri,localName);
        }
    }
    private static void printName(String prefix,
                                  String uri,
                                  String localName) {
        if (localName != null) System.out.print(localName);
    }
    private static void printAttributes(XMLStreamReader xmlr){
        for (int i=0; i < xmlr.getAttributeCount(); i++) {
            printAttribute(xmlr,i);
        }
    }
    private static void printAttribute(XMLStreamReader xmlr, int index) {
        String prefix = xmlr.getAttributePrefix(index);
        String namespace = xmlr.getAttributeNamespace(index);
        String localName = xmlr.getAttributeLocalName(index);
        String value = xmlr.getAttributeValue(index);
        System.out.print(" ");
        printName(prefix,namespace,localName);
        System.out.print("='"+value+"'");
    }
    private static void printNamespaces(XMLStreamReader xmlr){
        for (int i=0; i < xmlr.getNamespaceCount(); i++) {
            printNamespace(xmlr,i);
        }
    }
    private static void printNamespace(XMLStreamReader xmlr, int index) {
        String prefix = xmlr.getNamespacePrefix(index);
        String uri = xmlr.getNamespaceURI(index);
        System.out.print(" ");
        if (prefix == null)
            System.out.print("xmlns='"+uri+"'");
        else
            System.out.print("xmlns:"+prefix+"='"+uri+"'");
    }

}
