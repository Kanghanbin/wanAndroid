package kanghb.com.wanandroid.model.bean;

import java.io.Serializable;

/**
 * 创建时间：2018/11/15
 * 编写人：kanghb
 * 功能描述：
 */
public class ToDoBean implements Serializable{
    /**
     * completeDate : null
     * completeDateStr :
     * content :
     * date : 1532016000000
     * dateStr : 2018-07-20
     * id : 73
     * status : 0
     * title : 第一件未完成的事情
     * type : 0
     * userId : 2
     */

    private Object completeDate;
    private String completeDateStr;
    private String content;
    private long date;
    private String dateStr;
    private int id;
    private int status;
    private String title;
    private int type;
    private int userId;

    public Object getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Object completeDate) {
        this.completeDate = completeDate;
    }

    public String getCompleteDateStr() {
        return completeDateStr;
    }

    public void setCompleteDateStr(String completeDateStr) {
        this.completeDateStr = completeDateStr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
