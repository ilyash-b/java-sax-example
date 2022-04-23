import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    void run() throws ParserConfigurationException, SAXException, IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream("test.xml")) {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            Summarizer summarizer = new Summarizer();
            parser.parse(stream, summarizer);
            summarizer.print();
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        new Main().run();
    }
}
