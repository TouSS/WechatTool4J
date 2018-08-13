package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 扫描信息
 */
public class ScanCodeInfo {
    /**
     * 扫描类型，一般是qrcode
     */
    @JSONField(name = "ScanType")
    private String type;
    /**
     * 扫描结果，即二维码对应的字符串信息
     */
    @JSONField(name = "ScanResult")
    private String result;

    public ScanCodeInfo() {
    }

    public ScanCodeInfo(String type, String result) {
        this.type = type;
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
