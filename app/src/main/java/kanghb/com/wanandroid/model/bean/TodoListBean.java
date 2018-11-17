package kanghb.com.wanandroid.model.bean;

import java.util.List;

/**
 * 创建时间：2018/11/15
 * 编写人：kanghb
 * 功能描述：
 */
public class TodoListBean {

    /**
     * doneList : [{"date":1532793600000,"todoList":[{"completeDate":1533052800000,"completeDateStr":"2018-08-01","content":"这里可以记录笔记，备忘信息等。","date":1532793600000,"dateStr":"2018-07-29","id":82,"status":1,"title":"已经完成的事情","type":0,"userId":2}]}]
     * todoList : [{"date":1532016000000,"todoList":[{"completeDate":null,"completeDateStr":"","content":"","date":1532016000000,"dateStr":"2018-07-20","id":73,"status":0,"title":"第一件未完成的事情","type":0,"userId":2}]},{"date":1532448000000,"todoList":[{"completeDate":null,"completeDateStr":"","content":"","date":1532448000000,"dateStr":"2018-07-25","id":80,"status":0,"title":"第二件未完成的事情","type":0,"userId":2}]}]
     * type : 0
     */

    private int type;
    private List<DoneListBean> doneList;
    private List<TodoListBeanXX> todoList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<DoneListBean> getDoneList() {
        return doneList;
    }

    public void setDoneList(List<DoneListBean> doneList) {
        this.doneList = doneList;
    }

    public List<TodoListBeanXX> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoListBeanXX> todoList) {
        this.todoList = todoList;
    }

    public static class DoneListBean {
        /**
         * date : 1532793600000
         * todoList : [{"completeDate":1533052800000,"completeDateStr":"2018-08-01","content":"这里可以记录笔记，备忘信息等。","date":1532793600000,"dateStr":"2018-07-29","id":82,"status":1,"title":"已经完成的事情","type":0,"userId":2}]
         */

        private long date;
        private List<ToDoBean> todoList;

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public List<ToDoBean> getTodoList() {
            return todoList;
        }

        public void setTodoList(List<ToDoBean> todoList) {
            this.todoList = todoList;
        }

    }

    public static class TodoListBeanXX {
        /**
         * date : 1532016000000
         * todoList : [{"completeDate":null,"completeDateStr":"","content":"","date":1532016000000,"dateStr":"2018-07-20","id":73,"status":0,"title":"第一件未完成的事情","type":0,"userId":2}]
         */

        private long date;
        private List<ToDoBean> todoList;

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public List<ToDoBean> getTodoList() {
            return todoList;
        }

        public void setTodoList(List<ToDoBean> todoList) {
            this.todoList = todoList;
        }
    }
}
