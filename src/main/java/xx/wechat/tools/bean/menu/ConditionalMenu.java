package xx.wechat.tools.bean.menu;

import com.alibaba.fastjson.annotation.JSONField;

public class ConditionalMenu extends Menu {
    /**
     * 菜单匹配规则
     */
    @JSONField(name = "matchrule")
    private MatchRule matchRule;

    public MatchRule getMatchRule() {
        return matchRule;
    }

    public void setMatchRule(MatchRule matchRule) {
        this.matchRule = matchRule;
    }
}
