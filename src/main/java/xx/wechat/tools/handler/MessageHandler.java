package xx.wechat.tools.handler;

import xx.wechat.tools.WechatContext;
import xx.wechat.tools.annotation.MessageController;
import xx.wechat.tools.annotation.MessageMapping;
import xx.wechat.tools.bean.message.TextMessage;
import xx.wechat.tools.utils.Convert;

/**
 * 处理接收到的消息
 */
@MessageController
public class MessageHandler {

    @MessageMapping(name = "message", type = "text", clazz = TextMessage.class)
    public String handleTextMessage(WechatContext wechatContext, TextMessage textMessage) {
        TextMessage replyTextMessage = new TextMessage();
        replyTextMessage.setFromUserName(textMessage.getToUserName());
        replyTextMessage.setToUserName(textMessage.getFromUserName());
        replyTextMessage.setCreateTime(System.currentTimeMillis());
        replyTextMessage.setContent(textMessage.getContent());
        return Convert.objectToXml(replyTextMessage);
    }
}
