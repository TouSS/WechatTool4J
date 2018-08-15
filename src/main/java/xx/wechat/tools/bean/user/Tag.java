package xx.wechat.tools.bean.user;

/**
 * 用户标签
 */
public class Tag {
    private int id;
    private String name;
    private Integer count;

    public Tag() {
    }

    public Tag(int id) {
        this.id = id;
    }

    public Tag(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
