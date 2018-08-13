package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 回复图文消息
 */
public class ReplyNewsMessage extends Message {
    /**
     * 图文消息个数，限制为8条以内
     */
    @JSONField(name = "ArticleCount")
    private int articleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
     */
    @JSONField(name = "Articles")
    private Map<String, List<Article>> articles;

    public ReplyNewsMessage() {
        super(Message.MESSAGE_TYPE_NEWS);
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public Map<String, List<Article>> getArticles() {
        return articles;
    }

    public void setArticles(Map<String, List<Article>> articles) {
        this.articles = articles;
    }

    public void addArticle(Article article) {
        if (this.articles == null) {
            this.articles = new HashMap<>();
            this.articleCount = 0;
        }
        List<Article> items = this.articles.get("item");
        if (items == null) {
            items = new ArrayList<>();
            this.articles.put("item", items);
        }
        items.add(article);
        this.articleCount++;

    }

    public void removeArticle(Article article) {
        if (this.articleCount > 0) {
            List<Article> items = this.articles.get("item");
            items.remove(article);
            this.articleCount = items.size();
        }

    }

    public void clearArticle() {
        if (this.articleCount > 0) {
            this.articles.get("item").clear();
            this.articleCount = 0;
        }
    }
}
