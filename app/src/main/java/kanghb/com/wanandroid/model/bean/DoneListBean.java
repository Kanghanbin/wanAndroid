package kanghb.com.wanandroid.model.bean;

import java.util.List;

/**
 * 创建时间：2018/11/16
 * 编写人：kanghb
 * 功能描述：
 */
public class DoneListBean {

    /**
     * curPage : 1
     * datas : [{"completeDate":1542297600000,"completeDateStr":"2018-11-16","content":"哈哈哈","date":1542297600000,"dateStr":"2018-11-16","id":4178,"status":1,"title":"哈哈哈","type":0,"userId":12097}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 1
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<ToDoBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ToDoBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ToDoBean> datas) {
        this.datas = datas;
    }
}
