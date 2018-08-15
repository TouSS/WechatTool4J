package xx.wechat.tools.bean.menu;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 公众号菜单
 */
public class Menu {
    @JSONField(name = "menuid")
    private Integer id;
    @JSONField(name = "button")
    private List<Button> buttons;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    /**
     * 菜单匹配规则
     */
    protected class MatchRule {
        /**
         * 用户标签的id，可通过用户标签管理接口获取
         */
        @JSONField(name = "tag_id")
        private String tag;
        /**
         * 性别：男（1）女（2），不填则不做匹配
         */
        @JSONField(name = "sex")
        private String sex;
        /**
         * 国家信息，是用户在微信中设置的地区，具体请参考地区信息表
         */
        @JSONField(name = "country")
        private String country;
        /**
         * 省份信息，是用户在微信中设置的地区，具体请参考地区信息表
         */
        @JSONField(name = "province")
        private String province;
        /**
         * 城市信息，是用户在微信中设置的地区，具体请参考地区信息表
         */
        @JSONField(name = "city")
        private String city;
        /**
         * 客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)，不填则不做匹配
         */
        @JSONField(name = "client_platform_type")
        private String platform;
        /**
         * 语言信息，是用户在微信中设置的语言，具体请参考语言表： 1、简体中文 "zh_CN" 2、繁体中文TW "zh_TW" 3、繁体中文HK "zh_HK" 4、英文 "en" 5、印尼 "id" 6、马来 "ms" 7、西班牙 "es" 8、韩国 "ko" 9、意大利 "it" 10、日本 "ja" 11、波兰 "pl" 12、葡萄牙 "pt" 13、俄国 "ru" 14、泰文 "th" 15、越南 "vi" 16、阿拉伯语 "ar" 17、北印度 "hi" 18、希伯来 "he" 19、土耳其 "tr" 20、德语 "de" 21、法语 "fr"
         */
        @JSONField(name = "language")
        private String language;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }
}
