package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 地理位置消息
 */
public class LocationMessage extends Message {
    /**
     * 地理位置维度
     */
    @JSONField(name = "Location_X")
    private Float locationX;
    /**
     * 地理位置经度
     */
    @JSONField(name = "Location_Y")
    private Float locationY;
    /**
     * 地图缩放大小
     */
    @JSONField(name = "Scale")
    private Integer scale;
    /**
     * 地理位置信息
     */
    @JSONField(name = "Label")
    private String label;

    public LocationMessage() {
        super(Message.MESSAGE_TYPE_LOCATION);
    }

    public Float getLocationX() {
        return locationX;
    }

    public void setLocationX(Float locationX) {
        this.locationX = locationX;
    }

    public Float getLocationY() {
        return locationY;
    }

    public void setLocationY(Float locationY) {
        this.locationY = locationY;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
