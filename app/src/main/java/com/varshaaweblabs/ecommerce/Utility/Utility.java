package com.varshaaweblabs.ecommerce.Utility;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by dinesh on 24/8/17.
 */

public class Utility {

    Context mContex;

    public Utility(Context mContex) {
        this.mContex = mContex;
    }

    /**
     * Getting XML DOM element

     * */
    public static String getDomElement(String xml){

        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

//            InputSource is = new InputSource();
            InputStream in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
//            in.setCharacterStream(new StringReader(xml));
            doc = db.parse(in);
            Log.e("Doc ",doc.getDocumentElement().getElementsByTagName("prestashop").toString());

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }

        return doc.toString();
    }

    public static void stringToDom(String xmlSource) throws SAXException, ParserConfigurationException, IOException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));
        // Use a Transformer for output
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("c:/temp/test.xml"));
        transformer.transform(source, result);
    }

    public String getUDID() {
        TelephonyManager telephonyManager = (TelephonyManager) mContex
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }




}
