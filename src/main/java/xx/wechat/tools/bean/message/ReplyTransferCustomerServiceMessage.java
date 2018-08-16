package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 转发客户回复消息
 */
public class ReplyTransferCustomerServiceMessage extends Message {

    /**
     * 会话接入的客服账号
     */
    @JSONField(name = "TransInfo")
    private TransInfo transInfo;

    public ReplyTransferCustomerServiceMessage() {
        super(Message.MESSAGE_TYPE_TRANSFER_CUSTOMER_SERVICE);
    }

    public TransInfo getTransInfo() {
        return transInfo;
    }

    public void setTransInfo(TransInfo transInfo) {
        this.transInfo = transInfo;
    }

    public void setKFAccount(String account) {
        this.transInfo = new TransInfo(account);
    }

    class TransInfo {
        /**
         * 客服账号
         */
        @JSONField(name = "KfAccount")
        String account;

        public TransInfo() {
        }

        public TransInfo(String account) {

            this.account = account;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }
}
