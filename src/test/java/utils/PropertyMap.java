//package utils;
//
//import lombok.Getter;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.PropertyResourceBundle;
//
//@Getter
//public class PropertyMap {
//    private HashMap keyvalues = new HashMap();
//
//    public PropertyMap(String propertyFile) {
//        try {
//            PropertyResourceBundle prBundle = new PropertyResourceBundle(new FileInputStream(propertyFile));
//
//            Enumeration keys = prBundle.getString();
//
//            while(keys.hasMoreElements()) {
//                String key = (String)keys.nextElement();
//                String value = (String)prBundle.handleGetObject(key);
//                keyvalues.put(key, value);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getValue(String key) {
//        return (String)this.keyvalues.get(key);
//    }
//}
