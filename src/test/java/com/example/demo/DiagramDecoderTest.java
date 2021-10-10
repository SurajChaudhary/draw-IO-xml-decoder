package com.example.demo;


import com.example.demo.model.MxGraphModel;
import com.mxgraph.util.mxXmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class DiagramDecoderTest {
    public static void main(String[] args) throws DataFormatException, IOException, JAXBException {
        MxGraphModel document = getDiagramData();
        System.out.println(document);

    }

    private static MxGraphModel getDiagramData() throws DataFormatException, IOException, JAXBException {
        final String xmlFilePath = "src/test/java/com/example/demo/test.xml";
        Document xmlDocument = convertXMLFileToXMLDocument(xmlFilePath);
        if (xmlDocument == null) {
            return null;
        }

        Node diagram = xmlDocument.getElementsByTagName("diagram").item(0);
        String textContent = diagram.getTextContent();

        if (!textContent.startsWith("<")) {
            //content is compressed, following steps need to take place

            byte[] compressed = base64Decode(textContent);
            byte[] decompressed = inflate(compressed);
            textContent = urlDecode(new String(decompressed));
            // Writing content to a file on disk
            /*Path path = Paths.get("src/test/java/com/example/demo/dig.xml");
            byte[] strToBytes = textContent.getBytes();
            Files.write(path, strToBytes);
            String read = Files.readAllLines(path).get(0);
            System.out.println(read);*/
            // Writing content to a temp file.
            /*
            File tmpFile = File.createTempFile("diagram", ".tmp");
            FileWriter writer = new FileWriter(tmpFile);
            writer.write(textContent);
            writer.close();

            BufferedReader reader = new BufferedReader(new FileReader(tmpFile));
            System.out.println(reader.readLine());
            reader.close();
            */

            JAXBContext context = JAXBContext.newInstance(MxGraphModel.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(textContent);
            MxGraphModel response = (MxGraphModel) unmarshaller.unmarshal(sr);
            // Reading content from a temp file.
            //MxGraphModel response = (MxGraphModel) unmarshaller.unmarshal(tmpFile);
            System.out.println(response);
            return response;

        }
        return null;
    }

    private static Document getDiagramDataLocal() throws DataFormatException, IOException {
        final String xmlFilePath = "src/test/java/com/example/demo/test.xml";
        Document xmlDocument = convertXMLFileToXMLDocument(xmlFilePath);

        Node diagram = xmlDocument.getElementsByTagName("diagram").item(0);
        String textContent = diagram.getTextContent();

        if (!textContent.startsWith("<")) {
            //content is compressed, following steps need to take place

            byte[] compressed = base64Decode(textContent);
            byte[] decompressed = inflate(compressed);
            textContent = urlDecode(new String(decompressed));

            JAXBContext context = null;
            try {
                context = JAXBContext.newInstance(MxGraphModel.class);
                StringReader sr = new StringReader(textContent);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                MxGraphModel response = (MxGraphModel) unmarshaller.unmarshal(sr);
                System.out.println(response);
            } catch (JAXBException e) {
                e.printStackTrace();
            }

        }

        return mxXmlUtils.parseXml(textContent);
    }

    private static Document convertXMLFileToXMLDocument(String filePath) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new File(filePath));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] inflate(byte[] compressed) throws IOException, DataFormatException {
        int compressedDataLength = 0;
        Inflater compresser = new Inflater(true);
        compresser.setInput(compressed, 0, compressed.length);
        ByteArrayOutputStream o = new ByteArrayOutputStream(compressed.length);
        byte[] result = new byte[1024];
        try {
            while (!compresser.finished()) {
                compressedDataLength = compresser.inflate(result);
                if (compressedDataLength == 0) {
                    break;
                }
                o.write(result, 0, compressedDataLength);
            }
        } finally {
            o.close();
        }
        compresser.end();
        return o.toByteArray();
    }

    private static byte[] base64Decode(String input) {
        return Base64.getDecoder().decode(input);
    }

    private static String urlDecode(String input) {
        return URLDecoder.decode(input, Charset.defaultCharset());
    }
}
