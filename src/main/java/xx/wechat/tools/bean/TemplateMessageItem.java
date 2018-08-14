package xx.wechat.tools.bean;

/**
 * 模板数据最小节点数据元
 */
public class TemplateMessageItem {
    /**
     * 数据值
     */
    private String value;
    /**
     * 数据颜色
     */
    private String color;

    public TemplateMessageItem() {
    }

    public TemplateMessageItem(String value, String color) {

        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
