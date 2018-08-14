package xx.wechat.tools.utils;

import org.dom4j.DocumentException;
import org.junit.Test;
import xx.wechat.tools.annotation.MessageController;
import xx.wechat.tools.bean.event.MassSendFinishEvent;
import xx.wechat.tools.bean.message.Message;

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
                    "<ToUserName>< ![CDATA[gh_4d00ed8d6399] ]></ToUserName>\n" +
                    "<FromUserName>< ![CDATA[oV5CrjpxgaGXNHIQigzNlgLTnwic] ]></FromUserName>\n" +
                    "<CreateTime>1481013459</CreateTime>\n" +
                    "<MsgType>< ![CDATA[event] ]></MsgType>\n" +
                    "<Event>< ![CDATA[MASSSENDJOBFINISH] ]></Event>\n" +
                    "<MsgID>1000001625</MsgID>\n" +
                    "<Status>< ![CDATA[err(30003)] ]></Status>\n" +
                    "<TotalCount>0</TotalCount>\n" +
                    "<FilterCount>0</FilterCount>\n" +
                    "<SentCount>0</SentCount>\n" +
                    "<ErrorCount>0</ErrorCount>\n" +
                    "<CopyrightCheckResult>\n" +
                    "<Count>2</Count>\n" +
                    "<ResultList>\n" +
                    "<item>\n" +
                    "<ArticleIdx>1</ArticleIdx>\n" +
                    "<UserDeclareState>0</UserDeclareState>\n" +
                    "<AuditState>2</AuditState>\n" +
                    "<OriginalArticleUrl>< ![CDATA[Url_1] ]></OriginalArticleUrl>\n" +
                    "<OriginalArticleType>1</OriginalArticleType>\n" +
                    "<CanReprint>1</CanReprint>\n" +
                    "<NeedReplaceContent>1</NeedReplaceContent>\n" +
                    "<NeedShowReprintSource>1</NeedShowReprintSource>\n" +
                    "</item>\n" +
                    "<item>\n" +
                    "<ArticleIdx>2</ArticleIdx>\n" +
                    "<UserDeclareState>0</UserDeclareState>\n" +
                    "<AuditState>2</AuditState>\n" +
                    "<OriginalArticleUrl>< ![CDATA[Url_2] ]></OriginalArticleUrl>\n" +
                    "<OriginalArticleType>1</OriginalArticleType>\n" +
                    "<CanReprint>1</CanReprint>\n" +
                    "<NeedReplaceContent>1</NeedReplaceContent>\n" +
                    "<NeedShowReprintSource>1</NeedShowReprintSource>\n" +
                    "</item>\n" +
                    "</ResultList>\n" +
                    "<CheckState>2</CheckState>\n" +
                    "</CopyrightCheckResult>\n" +
                    "</xml>";
            Message event = Convert.xmlToObject(xml.replaceAll("\\s+", ""), MassSendFinishEvent.class);
            System.out.println(Convert.objectToXml(event));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void objectToXml() {

    }
}