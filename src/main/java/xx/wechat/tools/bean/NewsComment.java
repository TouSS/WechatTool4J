package xx.wechat.tools.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 图文消息留言/评论
 */
public class NewsComment {
    /**
     * 总数
     */
    @JSONField(name = "total")
    private Integer total;
    /**
     * 评论列表
     */
    @JSONField(name = "comment")
    private List<Comment> comment;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    class Comment {
        /**
         * 用户评论id
         */
        @JSONField(name = "user_comment_id")
        int userCommentId;
        /**
         * openid
         */
        @JSONField(name = "openid")
        String openid;
        /**
         * 评论时间
         */
        @JSONField(name = "create_time")
        long createTime;
        /**
         * 评论内容
         */
        @JSONField(name = "content")
        String content;
        /**
         * 是否精选评论，0为非精选，1为精选
         */
        @JSONField(name = "comment_type")
        int commentType;
        /**
         * 作者回复
         */
        @JSONField(name = "reply")
        Reply reply;

        public int getUserCommentId() {
            return userCommentId;
        }

        public void setUserCommentId(int userCommentId) {
            this.userCommentId = userCommentId;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCommentType() {
            return commentType;
        }

        public void setCommentType(int commentType) {
            this.commentType = commentType;
        }

        public Reply getReply() {
            return reply;
        }

        public void setReply(Reply reply) {
            this.reply = reply;
        }
    }

    class Reply {
        /**
         * 作者回复内容
         */
        @JSONField(name = "content")
        String content;
        /**
         * 作者回复时间
         */
        @JSONField(name = "create_time")
        long createTime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
