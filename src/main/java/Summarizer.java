import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

// Based on https://www.baeldung.com/java-sax-parser
public class Summarizer extends DefaultHandler {
    String currentCategory;
    Float currentPrice;
    Map<String, Float> sumByCategory;

    private StringBuilder text;

    @Override
    public void startDocument() {
        System.err.println("Starting");
        sumByCategory = new HashMap<>();
        text = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        text.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        text.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "category": {
                currentCategory = text.toString();
                System.err.println("Category:" + currentCategory);
                break;
            }
            case "price": {
                currentPrice = Float.parseFloat(text.toString());
                System.err.println("Price:" + currentPrice);
                break;
            }
            case "item": {
                sumByCategory.putIfAbsent(currentCategory, 0.0F);
                sumByCategory.compute(currentCategory, (cat, sum) -> sum + currentPrice);
            }
        }
    }

    public void print() {
        sumByCategory.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
