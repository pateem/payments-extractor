import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import us.codecraft.xsoup.Xsoup;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

public class JsoupTest {

    public static void main(String... args) {

        File file = Paths.get("C:\\Users\\IBM_ADMIN\\learning\\java\\paymentsdetailextractor\\src\\test\\resources\\vub_form.html").toFile();

        try {
          // Document document =  Jsoup.parse(file, Charset.defaultCharset().name());
//           Xsoup.compile("//label/div[contains(span, 'jemcu')]").evaluate(document).getElements().forEach(e -> System.out.println(e));
            //Xsoup.compile("//label/div/span[contains(.,'u')]").evaluate(document).getElements();

            FileInputStream fileIS = new FileInputStream(file);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(fileIS);
            XPath xPath = XPathFactory.newInstance().newXPath();
            xPath.compile("/").evaluate(xmlDocument);



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
