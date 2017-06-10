package cn.com.oceansoft.base.entity;

/**
 * Created by ty on 2017/6/10.
 * 分页查询请求实体类
 */
public class BasePageReqEntity<T> {

    private int limit;

    private int offset;

    private String order;

    private String search;

    private T obj;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
