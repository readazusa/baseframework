package cn.com.oceansoft.base.dao;



import cn.com.oceansoft.base.entity.BasePageReqEntity;

import java.util.List;

/**
 * Created by smc on 2015/11/19.
 * 基础dao
 */
public interface IDao<T> {

    public T queryObjectById(String id);

    public T queryObjectById(int id);

    public List<T> queryAll();

    public void save(T obj);   //保存信息

    public void deleteById(String uid);

    public void deleteById(int uid);

    public void update(T t);

    public int getTotalCount(T t);

    public List<T> queryPage(BasePageReqEntity<T> param);


}
