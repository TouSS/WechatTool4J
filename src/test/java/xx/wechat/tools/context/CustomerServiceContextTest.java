package xx.wechat.tools.context;

import org.junit.Before;
import org.junit.Test;
import xx.wechat.tools.Token;
import xx.wechat.tools.bean.token.AccessToken;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

public class CustomerServiceContextTest {
    CustomerServiceContext customerServiceContext;

    @Before
    public void setUp() throws Exception {
        AccessToken accessToken = Token.getAccessToken();
        customerServiceContext = new CustomerServiceContext(accessToken);
    }

    @Test
    public void addCustomerServiceAccount() {
        try {
            customerServiceContext.addCustomerServiceAccount("s1@gh_e331cea6274b", "cs001");
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inviteCustomerService() {
    }

    @Test
    public void updateCustomerServiceAccount() {
    }

    @Test
    public void removeCustomerServiceAccount() {
    }

    @Test
    public void setCustomerServiceAccountHeadImg() {
    }

    @Test
    public void setCustomerServiceAccountHeadImg1() {
    }

    @Test
    public void getAllCustomerServiceAccount() {
    }

    @Test
    public void getAllOnlineCustomerServiceAccount() {
    }

    @Test
    public void createSession() {
    }

    @Test
    public void closeSession() {
    }

    @Test
    public void getUserSession() {
    }

    @Test
    public void getAccountSession() {
    }

    @Test
    public void getWaitCase() {
    }

    @Test
    public void getRecords() {
    }

    @Test
    public void sendTextMessage() {
        try {
            customerServiceContext.sendTextMessage("onkEz1qkUBEPuSy1kyuAyKhuBNqQ", "你好，你好 。。。", null);
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendImageMessage() {
    }

    @Test
    public void sendVoiceMessage() {
    }

    @Test
    public void sendVideoMessage() {
    }

    @Test
    public void sendMusicMessage() {
    }

    @Test
    public void sendNewsMessage() {
    }

    @Test
    public void sendNewsMessage1() {
    }

    @Test
    public void sendCardMessage() {
    }

    @Test
    public void sendMiniProgramPageMessage() {
    }

    @Test
    public void sendTypingStateMessage() {
    }

    @Test
    public void sendTemplateMessage() {
    }
}