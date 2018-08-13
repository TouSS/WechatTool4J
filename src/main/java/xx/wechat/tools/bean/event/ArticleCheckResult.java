package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 文章版权验证结果
 */
public class ArticleCheckResult {
    /**
     * 群发文章的序号，从1开始
     */
    @JSONField(name = "ArticleIdx")
    private Integer articleIdx;
    /**
     * 用户声明文章的状态
     */
    @JSONField(name = "UserDeclareState")
    private Integer userDeclareState;
    /**
     * 系统校验的状态
     */
    @JSONField(name = "AuditState")
    private Integer auditState;
    /**
     * 相似原创文的url
     */
    @JSONField(name = "OriginalArticleUrl")
    private String originalArticleUrl;
    /**
     * 相似原创文的类型
     */
    @JSONField(name = "OriginalArticleType")
    private Integer originalArticleType;
    /**
     * 是否能转载
     */
    @JSONField(name = "CanReprint")
    private Integer canReprint;
    /**
     * 是否需要替换成原创文内容
     */
    @JSONField(name = "NeedReplaceContent")
    private Integer needReplaceContent;
    /**
     * 是否需要注明转载来源
     */
    @JSONField(name = "NeedShowReprintSource")
    private Integer needShowReprintSource;

    public Integer getArticleIdx() {
        return articleIdx;
    }

    public void setArticleIdx(Integer articleIdx) {
        this.articleIdx = articleIdx;
    }

    public Integer getUserDeclareState() {
        return userDeclareState;
    }

    public void setUserDeclareState(Integer userDeclareState) {
        this.userDeclareState = userDeclareState;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public String getOriginalArticleUrl() {
        return originalArticleUrl;
    }

    public void setOriginalArticleUrl(String originalArticleUrl) {
        this.originalArticleUrl = originalArticleUrl;
    }

    public Integer getOriginalArticleType() {
        return originalArticleType;
    }

    public void setOriginalArticleType(Integer originalArticleType) {
        this.originalArticleType = originalArticleType;
    }

    public Integer getCanReprint() {
        return canReprint;
    }

    public void setCanReprint(Integer canReprint) {
        this.canReprint = canReprint;
    }

    public Integer getNeedReplaceContent() {
        return needReplaceContent;
    }

    public void setNeedReplaceContent(Integer needReplaceContent) {
        this.needReplaceContent = needReplaceContent;
    }

    public Integer getNeedShowReprintSource() {
        return needShowReprintSource;
    }

    public void setNeedShowReprintSource(Integer needShowReprintSource) {
        this.needShowReprintSource = needShowReprintSource;
    }
}
