package xx.wechat.tools.utils;

import org.dom4j.DocumentException;
import org.junit.Test;
import xx.wechat.tools.annotation.MessageController;
import xx.wechat.tools.bean.event.MassSendFinishEvent;
import xx.wechat.tools.bean.message.Message;
import xx.wechat.tools.bean.message.ReplyTransferCustomerServiceMessage;

public class ConvertTest {

    @Test
    public void xmlToMap() {
    }

    @Test
    public void collectionToXml() {
    }

    @Test
    public void xmlToObject() {
        try {
            String xml = "<xml>\n" +
                    "     <ToUserName>< ![CDATA[touser] ]></ToUserName>\n" +
                    "     <FromUserName>< ![CDATA[fromuser] ]></FromUserName>\n" +
                    "     <CreateTime>1399197672</CreateTime>\n" +
                    "     <MsgType>< ![CDATA[transfer_customer_service] ]></MsgType>\n" +
                    "     <TransInfo>\n" +
                    "         <KfAccount>< ![CDATA[test1@test] ]></KfAccount>\n" +
                    "     </TransInfo>\n" +
                    " </xml>";
            Message event = Convert.xmlToObject(xml.replaceAll("\\s+", ""), ReplyTransferCustomerServiceMessage.class);
            System.out.println(Convert.objectToXml(event));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void objectToXml() {

    }
}