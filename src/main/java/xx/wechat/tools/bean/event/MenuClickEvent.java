package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 菜单点击事件
 */
public class MenuClickEvent extends Event {
    /**
     * 菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击
     */
    @JSONField(name = "MenuID")
    private String MenuID;

    public MenuClickEvent() {
        super(Event.EVENT_TYPE_CLICK);
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }

    /**
     * 点击菜单跳转链接时的事件
     */
    public static class View extends MenuClickEvent {
        public View() {
            this.setEvent(Event.EVENT_TYPE_VIEW);
        }
    }
}
