package cn.com.oceansoft.base.entity;



import java.util.List;

/**
 * Created by ty on 2017/6/10
 * 树形展示的实体类
 */
public class TreeEntity {

    private String text="新区城管局";

    private  String id="0";

    private List<TreeEntity> children;



    private boolean child;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TreeEntity> children) {
        this.children = children;
    }
}
