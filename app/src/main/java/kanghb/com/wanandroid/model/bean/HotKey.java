package kanghb.com.wanandroid.model.bean;

/**
 * 创建时间：2018/11/9
 * 编写人：kanghb
 * 功能描述：搜索热词类
 */
public class HotKey {

    /**
     * id : 9
     * link :
     * name : Studio3
     * order : 1
     * visible : 1
     */

    private int id;
    private String link;
    private String name;
    private int order;
    private int visible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
