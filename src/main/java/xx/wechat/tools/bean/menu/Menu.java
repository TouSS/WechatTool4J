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
}
