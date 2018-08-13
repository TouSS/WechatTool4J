package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 发送的位置信息
 */
public class SendLocationInfo {
    /**
     * X坐标信息
     */
    @JSONField(name = "Location_X")
    private Integer locationX;
    /**
     * Y坐标信息
     */
    @JSONField(name = "Location_Y")
    private Integer locationY;
    /**
     * 精度，可理解为精度或者比例尺、越精细的话 scale越高
     */
    @JSONField(name = "Scale")
    private Integer scale;
    /**
     * 地理位置的字符串信息
     */
    @JSONField(name = "Label")
    private String label;
    /**
     * 朋友圈POI的名字，可能为空
     */
    @JSONField(name = "Poiname")
    private String poiName;

    public SendLocationInfo() {
    }

    public SendLocationInfo(Integer locationX, Integer locationY, Integer scale, String label, String poiName) {

        this.locationX = locationX;
        this.locationY = locationY;
        this.scale = scale;
        this.label = label;
        this.poiName = poiName;
    }

    public Integer getLocationX() {
        return locationX;
    }

    public void setLocationX(Integer locationX) {
        this.locationX = locationX;
    }

    public Integer getLocationY() {
        return locationY;
    }

    public void setLocationY(Integer locationY) {
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

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }
}
