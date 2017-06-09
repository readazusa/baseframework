package cn.com.oceansoft.base.entity;

/**
 * Created by 念梓  on 2017/6/9.
 * Email:sunmch@163.com
 * author: 念梓
 * des:基础的bootstrap table 通过post请求发出来的数据
 */
public class BaseReqEntity {

    private String order;

    private int limit;

    private int offset;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseReqEntity{");
        sb.append("order='").append(order).append('\'');
        sb.append(", limit=").append(limit);
        sb.append(", offset=").append(offset);
        sb.append('}');
        return sb.toString();
    }
}
