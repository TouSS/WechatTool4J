package xx.wechat.tools.handler;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xx.wechat.tools.WechatContext;
import xx.wechat.tools.annotation.MessageController;
import xx.wechat.tools.annotation.MessageMapping;
import xx.wechat.tools.bean.message.Message;
import xx.wechat.tools.exception.MessageControllerNotFoundException;
import xx.wechat.tools.utils.Convert;
import xx.wechat.tools.utils.LocalPackage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息处理器
 */
public class ReceiveHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<String, Map<String, Map<String, Object>>> handlers;

    public ReceiveHandler(String handlerPackage) throws IOException, ClassNotFoundException {
        //初始化处理方法
        handlers = new HashMap<>();
        List<Class<?>> classes = LocalPackage.getAnnotationClasses(MessageController.class, handlerPackage);
        classes.stream().forEach(clazz -> {
            List<Method> methods = LocalPackage.getAnnotationMethods(MessageMapping.class, clazz);
            methods.stream().forEach(method -> {
                MessageMapping mapping = method.getAnnotation(MessageMapping.class);
                String name = mapping.name();
                String type = mapping.type();
                Class<?> messageClass = mapping.clazz();
                Map<String, Map<String, Object>> map1 = handlers.get(name);
                if (map1 == null) {
                    map1 = new HashMap<>();
                    handlers.put(name, map1);
                }
                map1.put(type, new HashMap<String, Object>() {{
                    put("messageClass", messageClass);
                    put("messageControllerClass", clazz);
                    put("invokeMethod", method);
                }});
            });
        });
    }

    /**
     * 处理请求
     *
     * @param req  请求
     * @param resp 回复
     * @throws IOException
     * @throws DocumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws MessageControllerNotFoundException
     */
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            DocumentException, IllegalAccessException, InstantiationException, InvocationTargetException,
            MessageControllerNotFoundException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        log.debug("RECEIVE: " + stringBuffer.toString());
        Map postData = Convert.xmlToMap(stringBuffer.toString());
        String msgType = (String) postData.get("MsgType");
        Map<String, Object> handler;
        if (Message.MESSAGE_TYPE_EVENT.equals(msgType)) {
            //事件消息
            String eventType = (String) postData.get("Event");
            if (this.handlers.get("event") != null && this.handlers.get("event").get(eventType) != null) {
                handler = this.handlers.get("event").get(eventType);
            } else {
                throw new MessageControllerNotFoundException("Message controller no found: event/" + eventType);
            }
        } else {
            //非事件消息
            if (this.handlers.get("message") != null && this.handlers.get("message").get(msgType) != null) {
                handler = this.handlers.get("message").get(msgType);
            } else {
                throw new MessageControllerNotFoundException("Message controller no found: message/" + msgType);
            }
        }
        Class<?> messageClass = (Class<?>) handler.get("messageClass");
        Class<?> messageControllerClass = (Class<?>) handler.get("messageControllerClass");
        Method method = (Method) handler.get("invokeMethod");
        WechatContext wechatContext = (WechatContext) req.getSession().getServletContext().getAttribute("wechatContext");
        String result = (String) method.invoke(messageControllerClass.newInstance(), wechatContext, Convert.mapToObject(postData, messageClass));
        resp.getWriter().write(result);
    }
}
