package xx.wechat.tools.utils;

import java.util.*;

import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Convert {

    /**
     * 将XML转为Map
     *
     * @param xml XML字符串
     * @return map
     * @throws DocumentException
     */
    public static Map<String, Object> xmlToMap(String xml) throws DocumentException {
        Document doc = null;
        doc = DocumentHelper.parseText(xml); // 将字符串转为XML
        Element rootElt = doc.getRootElement(); // 获取根节点
        return elementToMap(rootElt);
    }

    /**
     * 节点转Map
     *
     * @param element XML DOM节点
     * @return MAP
     */
    private static Map<String, Object> elementToMap(Element element) {
        Map<String, Object> map = new HashMap<>();

        List<Element> elements = element.elements();
        for (Element e : elements) {
            if (e.isTextOnly()) {
                map.put(e.getName(), e.getData());
            } else {
                String name = e.getName();
                if (name.toLowerCase().indexOf("item") >= 0) {
                    //数组结构
                    List<Object> list = (List<Object>) map.get(name);
                    if (list == null) {
                        list = new ArrayList<>();
                        map.put(name, list);
                    }
                    list.add(elementToMap(e));
                } else {
                    map.put(e.getName(), elementToMap(e));
                }
            }
        }
        return map;
    }


    /**
     * 集合对象转XML, Map,List,Array集合或混合集合
     *
     * @param collection 混合集合
     * @param root       XML根节点
     * @return XML
     */
    public static String collectionToXml(Object collection, String root) {
        StringBuffer xml = new StringBuffer();
        if (collection instanceof Map) {
            Map<Object, Object> map = (Map) collection;
            xml.append("<").append(root).append(">");
            for (Object key : map.keySet()) {
                xml.append(collectionToXml(map.get(key), key.toString()));
            }
            xml.append("</").append(root).append(">");
        } else if (collection instanceof List) {
            List<Object> list = (List<Object>) collection;
            for (Object o : list) {
                xml.append(collectionToXml(o, root));
            }
        } else if (collection instanceof Object[]) {
            Object[] array = (Object[]) collection;
            for (Object o : array) {
                xml.append(collectionToXml(o, root));
            }
        } else {
            xml.append("<").append(root).append(">");
            if (collection instanceof Number) {
                xml.append(collection);
            } else {
                xml.append("<![CDATA[").append(collection).append("]]>");
            }
            xml.append("</").append(root).append(">");
        }
        return xml.toString();
    }

    /**
     * xml转对象, xml->Map->Json->Object
     *
     * @param xml   XML
     * @param clazz 对象类
     * @return 对象
     * @throws DocumentException
     */
    public static <T> T xmlToObject(String xml, Class<T> clazz) throws DocumentException {
        Map map = xmlToMap(xml);
        String json = JSON.toJSONString(map);
        return JSON.parseObject(json, clazz);
    }

    /**
     * 对象序列化为XML, Object->Json->Map->xml
     *
     * @param object 对象
     * @return XML
     */
    public static String objectToXml(Object object) {
        String json = JSON.toJSONString(object);
        Map map = JSON.parseObject(json, Map.class);
        return collectionToXml(map, "xml");
    }
}
