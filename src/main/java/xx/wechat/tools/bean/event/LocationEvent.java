package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 上报地理位置事件
 */
public class LocationEvent extends Event {
    /**
     * 地理位置纬度
     */
    @JSONField(name = "Latitude")
    private Float latitude;
    /**
     * 地理位置经度
     */
    @JSONField(name = "Longitude")
    private Float longitude;
    /**
     * 地理位置精度
     */
    @JSONField(name = "Precision")
    private Float precision;

    public LocationEvent() {
        super(Event.EVENT_TYPE_LOCATION);
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getPrecision() {
        return precision;
    }

    public void setPrecision(Float precision) {
        this.precision = precision;
    }
}
